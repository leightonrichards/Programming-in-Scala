package ch19.typeParameterization

import org.scalatest.FunSuite

class Queue[+T] private( private[this] var leading: List[T],
                  private[this] var trailing: List[T])
{
  private def mirror() =
    if (leading.isEmpty)
    {
      while(!trailing.isEmpty)
      {
        leading = trailing.head :: leading
        trailing = trailing.tail
      }
    }

  def head: T = {
    mirror()
    leading.head
  }

  def tail: Queue[T] = {
    mirror()
    new Queue(leading.tail, trailing)
  }

  //Lower bounds operator [U >: T] says U must be a supertype of T, without this you would not be able to add a dog to a queue of mammals
  def enqueue[U >: T](x: U) =
    new Queue[U](leading, x::trailing)
}

object Queue{
  def apply[T](xs: T*): Queue[T]= new Queue(xs.toList,Nil)
}

class TestFunctionalQueue extends FunSuite{

  sealed abstract class Organism(val name: String)
  case class Animal(override val name: String) extends Organism(name)
  case class Fish(override val name: String) extends Animal(name)
  case class Mammal(override val name: String) extends Animal(name)
  case class Dog(override val name: String) extends Mammal(name)

  test("Functional queue is covariant")
  {
    var mammals: Queue[Animal] = Queue(new Mammal("rabbit"),new Mammal("beaver"))
    //This will compile because Queue is covariant, ie Queue[Mammal] is a subtype of Queue[Animal]

    mammals= mammals.enqueue(new Mammal("cat"))
    mammals = mammals.enqueue(new Dog("Greyhound"))
    //goldfish in a Mammal Queue wont compile
    mammals = mammals.enqueue(new Fish("GoldFish"))
    mammals = mammals.enqueue(new Animal("Animal"))

    assert(mammals.head.name === "rabbit")
    mammals = mammals.tail
    assert(mammals.head.name === "beaver")
    mammals = mammals.tail
    assert(mammals.head.name === "cat")
    mammals = mammals.tail
    assert(mammals.head.name === "Greyhound")
  }

}


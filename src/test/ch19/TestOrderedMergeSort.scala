package ch19

import org.scalatest.FunSuite


/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 14/10/12
 * Time: 10:52
 * To change this template use File | Settings | File Templates.
 */
class TestOrderedMergeSort extends FunSuite{
  test("Ordered Merge Sort")
  {
    val personList = List(Person("Nathalie","Richards"),Person("Mei","Richards"),Person("Leighton","Richards"),Person("Charlize","Richards"),Person("Keira","Richards"))

    val sortedPersonList = OrderedMergeSort.orderedMergeSort(personList)
    println(sortedPersonList.mkString(", "))
  }
}

object OrderedMergeSort
{
  //Lower bounds operator [U >: T] says U must be a supertype of T
  //Upper Bounds operator [T <: Ordered[T]] says T must be a subtype of Ordered
  def orderedMergeSort[T <: Ordered[T]](xs: List[T]): List[T] =
  {
    def merge(xs: List[T], ys: List[T]): List[T] =
      (xs,ys) match {
        case(Nil,_) => ys
        case(_,Nil) => xs
        case(x :: xs1, y::ys1) => if (x < y) x:: merge(xs1,ys) else y :: merge(xs,ys1)
      }

    val n = xs.length / 2
    if (n == 0) xs
    else{
      val(ys,zs) = xs splitAt n
      merge(orderedMergeSort(ys), orderedMergeSort(zs))
    }
  }
}

class Person(val firstName: String, val lastName: String) extends Ordered[Person] {
  def compare(that: Person) = {
    def compareLastName = lastName.compareToIgnoreCase(that.lastName)
    def compareFirstName = firstName.compareToIgnoreCase(that.firstName)

    if (compareLastName != 0) compareLastName else compareFirstName
  }

  override def toString = firstName + " " + lastName
}

object Person {
  def apply(firstName: String, lastName: String) = new Person(firstName, lastName)
}
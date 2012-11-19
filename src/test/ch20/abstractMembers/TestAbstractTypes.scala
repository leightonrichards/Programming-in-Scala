package abstractMembers

import org.scalatest.FunSuite

/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 11/11/12
 * Time: 17:05
 * To change this template use File | Settings | File Templates.
 */
class TestAbstractTypes extends FunSuite{

  class Food
  abstract class Animal
  {
    type SuitableFood <: Food
    def eat(food: SuitableFood)
  }

  class Grass extends Food
  class Fish extends Food
  class Cow extends Animal
  {
    type SuitableFood = Grass

    def eat(food: Grass)
    {
      println("eat " + food)
    }
  }

  test("Cows eat grass not fish")
  {
    val cow = new Cow
    cow.eat(new Grass)
  }

}



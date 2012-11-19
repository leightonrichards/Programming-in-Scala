package ch21.implicitConversions

import org.scalatest.FunSuite

/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 19/11/12
 * Time: 06:15
 * To change this template use File | Settings | File Templates.
 */
class ImplicitConversionsTest extends FunSuite {

  //Only definitions marked implicit are available
  //the implicit conversion must be in scope
  //Only one implicit is tried
  //Only code that has compilation fail will have implicits applied

  class Dog(val name: String)
  class Cat(val name: String)
  object DogKennel{
    def house(dog: Dog ) = {"housed " + dog.name}
  }

  implicit def catToDog(cat: Cat): Dog = new Dog(cat.name)

  test("Implicit conversion to an expected type") {

    DogKennel.house(new Cat("moggie"))
  }

}

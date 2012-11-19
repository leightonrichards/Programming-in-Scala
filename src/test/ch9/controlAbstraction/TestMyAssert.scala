package ch9.controlAbstraction

import org.scalatest.FunSuite


class TestMyAssert extends FunSuite {
  test("MyAssert") {
    val myAssert = new MyAssert(true)
    println("start")
    def predicate = {
      println("predicate 5>3")
      5 > 3
    }
    println("middle")
    myAssert.myAssert(predicate)
    println("end")
  }
}

class MyAssert(b: Boolean) {
  val assertionsEnabled: Boolean = b;

  def myAssert(predicate: => Boolean) = {
    if (assertionsEnabled && !predicate) {
      throw new AssertionError
    }
  }
}

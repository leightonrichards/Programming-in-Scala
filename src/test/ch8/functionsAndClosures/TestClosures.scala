package ch8.functionsAndClosures

import org.scalatest.FunSuite

/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 30/09/12
 * Time: 14:12
 * To change this template use File | Settings | File Templates.
 */
class TestClosures extends FunSuite {

  test("closures") {
    var x = 2
    // this closure closes the variable x
    def printX = { "x = " + x }
    assert(printX == "x = 2")
    //if the value of x later changes, the closure reflects that change
    x = 3
    assert(printX == "x = 3")
  }

  test("closures 2") {
    def closure1(op: => Unit) { op }

    var x = 2
    def printClosure = println("closure = " + x)
    closure1(printClosure)
    x = 3
    closure1(printClosure)
    x = 4
    closure1(printClosure)
  }
}

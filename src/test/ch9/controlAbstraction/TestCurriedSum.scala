package ch9.controlAbstraction

import org.scalatest.FunSuite

/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 13/11/12
 * Time: 06:59
 * To change this template use File | Settings | File Templates.
 */
class TestCurriedSum extends FunSuite{
  test("curried sum")
  {
    def curriedSum (a: Int)(b: Int) = a + b
    val sum1: (Int) => Int = curriedSum(1)
    assert(sum1(10) === 11)
  }
}

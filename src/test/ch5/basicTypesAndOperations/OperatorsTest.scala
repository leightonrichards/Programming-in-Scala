package test.ch5.basicTypesAndOperations

import org.scalatest.FunSuite

/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 12/11/12
 * Time: 07:19
 * To change this template use File | Settings | File Templates.
 */
class OperatorsTest extends FunSuite
{
  test("")
  {
    //operators are methods
    assert(1 + 2 === (1).+(2))

    val d1 = (2.0).unary_-
    val d2 = (-2.0)
    assert(d1 === d2)

    //scala's == does an equality comparison, not just a reference comparison,
    val s1 = "he"
    val s2 = "llo"
    val s3 = "hello"
    assert (s1 != s2)
    assert (s1+s2 === s3)

    // it also has an automatic null check
    assert (null != 1)
    val l1 = List(1,2,3)
    //List (and other classes) defines a factory method named apply on the companion object which are called automatically
    val l2 = List.apply(4,5,6)
    val l3 = List(1,2,3)
    assert(l1 != l2)
    assert(l1 === l3)
  }
}

package ch9.controlAbstraction

import org.scalatest.FunSuite

/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 29/09/12
 * Time: 16:20
 * To change this template use File | Settings | File Templates.
 */
class TestLists extends FunSuite {

  test("ListExists") {
    val list = List(-10, -5, -1, 0, 1, 5, 10)
    assert(list.exists(_ < 0))
  }

  test("ListReduceLeft") {
    val numbers = 0 to 10

    print(numbers)

    val sum = numbers.reduceLeft((a, b) => a + b)
    println(sum)

    val sum1 = numbers.reduceLeft(_ + _)
    println(sum1)

    val sum2 = numbers.sum
    println(sum2)
  }

  test("Twice") {
    def twice(op: Float => Float, x: Float) = op(op(x))

    val float1 = twice(_ + 1, 5)
    assert(float1 == 7.0000)

    val float2 = twice(_ * 2, 10)
    assert(float2 == 40.0000)
  }
}

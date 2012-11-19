package ch16.workingWithLists

import org.scalatest.FunSuite

/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 17/11/12
 * Time: 12:42
 * To change this template use File | Settings | File Templates.
 */
class ReverseListTest extends FunSuite {
  test("reverse a list") {
    val l = 1 :: 2 :: 3 :: 4 :: 5 :: 6 :: 7 :: 8 :: 9 :: Nil
    assert(reverse(l) == l.reverse)
  }

  def reverse(list: List[Int]): List[Int] = list match {
    case List() => list
    case listHead :: listTail => reverse(listTail) ::: List(listHead)
  }
}

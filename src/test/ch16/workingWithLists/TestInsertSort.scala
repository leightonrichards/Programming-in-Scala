package ch16.workingWithLists

import org.scalatest.FunSuite

/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 17/11/12
 * Time: 08:48
 * To change this template use File | Settings | File Templates.
 */
class TestInsertSort extends FunSuite{

  test("Insertion Sort") {
    val l = List(9, 1, 8, 2, 7, 3, 6, 4, 5, 7, 3)
    val l2: List[Int] = InsertSort.isort(l)

    assert(l2 === List(1,2,3,3,4,5,6,7,7,8,9))
  }

}

object InsertSort {

  /**
   * Insert the int at appropriate place in the list
   * @param x the int to be inserted
   * @param xs the list of integers
   * @return the result of the list after the int has been inserted
   */
  private def insert(x: Int, xs: List[Int]): List[Int] =
    if (xs.isEmpty || x <= xs.head) x :: xs
    else xs.head :: insert(x, xs.tail)

  /**
   * Sort a list of integers
   * Insert sort have a complexity proportional to the square of the input list
   * @param xs
   * @return
   */
  def isort(xs: List[Int]): List[Int] = {
    if (xs.isEmpty) Nil
    else insert(xs.head, isort(xs.tail))
  }
}
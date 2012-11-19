package ch16.workingWithLists

import org.scalatest.FunSuite

/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 09/10/12
 * Time: 06:40
 * To change this template use File | Settings | File Templates.
 */
class TestMergeSort extends FunSuite {

  test("Merge Sort") {
    val l = List(9, 1, 8, 2, 7, 3, 6, 4, 5, 7, 3)

    def less(x: Int, y: Int) = x < y
    val intSort = MergeSort.msort(less)(_)
    val l2: List[Int] = intSort(l)
    val l3 = l.sortWith(less)
    assert(l2 === List(1,2,3,3,4,5,6,7,7,8,9))
    assert(l2===l3)
  }
}

object MergeSort {
  /**
   *
   * @param comparison function to compare 2 objects, returns a boolean
   * @param xs list to be sorted according to the comparison
   * @tparam T the object type
   * @return the sorted list
   */
  def msort[T](comparison: (T, T) => Boolean)(xs: List[T]): List[T] =
  {
    /**
     * Merge two lists
     * @param xs
     * @param ys
     * @return
     */
    def merge(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
          //divide and conquer find the head of both lists and compare
        case (xHead :: xTail, yHead :: yTail) =>
          //if x is less than y append x to the front and merge the remainder
          if (comparison(xHead,yHead)) xHead :: merge(xTail, ys)
          //if y is less than x append y at the frond and merge the remainder
          else yHead:: merge(xs, yTail)
      }

    val n = xs.length / 2
    if (n == 0) xs
    else
    {
      val(ys,zs) = xs splitAt n
      merge(msort(comparison)(ys), msort(comparison)(zs))
    }
  }
}

package ch16.workingWithLists

import org.scalatest.FunSuite

/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 17/11/12
 * Time: 12:55
 * To change this template use File | Settings | File Templates.
 */
class TestFoldingLists extends FunSuite{
  test("sum lists usig fold left")
  {
    val l = 1::2::3::4::5::Nil
    assert(sum(l) === 15)
  }

  def sum(l: List[Int]): Int = (0 /: l)(_+_)
}

package ch4.classesAndObjects

import org.scalatest.FunSuite
import collection.mutable

/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 12/11/12
 * Time: 06:55
 * To change this template use File | Settings | File Templates.
 */
class CheckSumTest extends FunSuite
{
  test("test checksum")
  {
    assert(CheckSumAccumulator.calculate("leighton") === -90)
  }

}

class CheckSumAccumulator
{
  //default access level in scala is public
  private var sum = 0
  //this is a procedure, a method which is only executed for its side effects, it has a return type of Unit
  def add(b: Byte) { sum += b}
  def checksum(): Int = ~(sum & 0xFF) + 1
}

//this is a singleton companion object
object CheckSumAccumulator
{
  private val cache = mutable.WeakHashMap[String, Int]()
  def calculate(s: String): Int =
  {
    if (cache.contains(s))
      cache(s)
    else
    {
      val acc = new CheckSumAccumulator
      for (c <- s)
        acc.add(c.toByte)
      val cs = acc.checksum()
      cache += (s -> cs)
      cs
    }
  }

  //CheckSumAccumulator can be run as a script because it ends in a result expression
  def main(args: Array[String])
  {
    CheckSumAccumulator.calculate(args(0))
  }
}
package ch20.abstractMembers

import org.scalatest.FunSuite

/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 18/11/12
 * Time: 18:10
 * To change this template use File | Settings | File Templates.
 */


class TestEnumerations extends FunSuite{
  import Color._
  test("Enumerations")
  {
    assert(show(Color.Red)==="Red")
  }

  def show(col: Value) = col match{
    case Red => "Red"
    case Green => "Green"
    case Blue => "Blue"
  }



}
object Color extends Enumeration
{
  val  Red, Green, Blue = Value
}

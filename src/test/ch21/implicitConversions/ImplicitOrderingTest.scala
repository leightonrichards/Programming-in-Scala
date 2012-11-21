package ch21.implicitConversions

import org.scalatest.FunSuite

/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 20/11/12
 * Time: 07:00
 * To change this template use File | Settings | File Templates.
 */
class ImplicitOrderingTest extends FunSuite{
  def maxListImpParm[T](elements: List[T])(implicit orderer: T => Ordered[T]): T = {
    elements match {
      case List() =>
        throw new IllegalArgumentException("empty list!")
      case List(x) => x
      case x:: rest =>
        val maxRest = maxListImpParm(rest)(orderer)
        if (orderer(x) > maxRest) x
        else maxRest
    }
  }

  test("implicit orderer")
  {
    val list = List(1,5,10,3)
    assert(maxListImpParm(list)===10)
  }
}

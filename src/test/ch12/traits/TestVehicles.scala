package ch12.traits

import org.scalatest.FunSuite

/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 30/09/12
 * Time: 16:02
 * To change this template use File | Settings | File Templates.
 */
class TestVehicles extends FunSuite{
  test("Traits")
  {
    //mix in traits
    val vehicle1: BasicVehicle = new RealVehicle with Float with Fly with Sink with Swim
    val proceed: String = vehicle1.proceed
    println(proceed)
    assert(proceed == "swimming sinking flying floating proceeding floating flying sinking swimming ")
  }
}

abstract class BasicVehicle {
  def proceed: String
}

trait Float extends BasicVehicle {
  abstract override def proceed = "floating " + super.proceed + "floating "
}

trait Fly extends BasicVehicle {
  abstract override def proceed = "flying " + super.proceed + "flying "
}

class RealVehicle extends BasicVehicle {
  def proceed = "proceeding "
}

trait Sink extends BasicVehicle {
  abstract override def proceed = "sinking " + super.proceed + "sinking "
}

trait Swim extends BasicVehicle {
  abstract override def proceed = "swimming " + super.proceed + "swimming "
}
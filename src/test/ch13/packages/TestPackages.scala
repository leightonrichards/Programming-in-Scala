package ch13.packages

import org.scalatest.FunSuite


/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 14/11/12
 * Time: 06:17
 * To change this template use File | Settings | File Templates.
 */
class TestPackages extends FunSuite {
  test("chained packages in one file") {
    val navigator = new navigation.Navigator()
    val ship = new Ship()
    val fleet = new fleets.Fleet()
  }
}

package navigation {
  class Navigator {

  }
}

class Ship

package fleets {
  class Fleet
}
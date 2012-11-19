package ch13.packages

import org.scalatest.FunSuite

/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 14/11/12
 * Time: 06:31
 * To change this template use File | Settings | File Templates.
 */
class AccessModifiersTest extends FunSuite {

  test("access to package object")
  {
    assert(showFruit()==="fruit")
  }

}

class Outer {

  class Inner {
    private def f() { println("f") }

    class InnerMost {
      //Inner classes can access private members of there outer classes
      f()
    }

  }

  //Outer classes dont have access to private members of inner classes
  //f()
}

package p {

class Super {
  protected def f() { println("f") }
}

//protected can only be accessed by subclasses
class Sub extends Super {
  f()
}

class Duper {
  //classes in the same package cant access protected members
  //f()
}

}

//Qualified access Modifiers
package sports {
  package athletics {

    //Access within this class only
    private class Running {
      def jump(){println("run")}
    }

    //access within athletics package
    private[athletics] class Jumping {
      def jump(){
        run()
        println("jump")
      }
      private[Jumping] def run(){println("run")}
    }

  }

  package swimming {
    object Swimming{
      def race() {swim()}
      //Access only from same object
      private[this] def swim(){println("swim")}
    }

  }

}

package ch16.workingWithLists

import org.scalatest.FunSuite

/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 17/11/12
 * Time: 08:50
 * To change this template use File | Settings | File Templates.
 */
class TestListHigherOrderMethods extends FunSuite{
  test("List Map") {
    val l = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val l2 = l map (_ + 1)
    assert(l2.mkString(", ") == "2, 3, 4, 5, 6, 7, 8, 9, 10")

    val l3 = List("how", "now", "brown", "cow")
    val l4 = l3 flatMap (_.toList)
    val string4: String = l4.mkString(", ")
    println(string4)
    assert(string4 == "h, o, w, n, o, w, b, r, o, w, n, c, o, w")

    val tuples = List.range(1, 5) flatMap (i => List.range(1, i) map (j => (i, j)))
    val tupleString: String = tuples.mkString(", ")
    println(tupleString)
    assert(tupleString == "(2,1), (3,1), (3,2), (4,1), (4,2), (4,3)")

    var sum = 0
    l.foreach(sum += _)
    assert(sum == 45)
  }
}

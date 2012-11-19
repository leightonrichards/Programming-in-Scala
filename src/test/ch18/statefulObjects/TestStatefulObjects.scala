package ch18.statefulObjects

import org.scalatest.FunSuite

class TestStatefulObjects extends FunSuite{

  test("In Scala every var that this non private implicitly defines a getter and setter")
  {
    val t = new Time(8,30)
    assert(t.hour===8)
    t.hour_=(6)
    assert(t.hour === 6)

  }
}

class Time(h:Int, m:Int)
{
  var hour: Int = h
  var minute: Int = m
}
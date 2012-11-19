package ch8.functionsAndClosures

import org.scalatest.FunSuite

/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 26/09/12
 * Time: 19:21
 * To change this template use File | Settings | File Templates.
 */
class TestFunctions extends FunSuite {
  val functions: Functions = new Functions()

  def testFirstClass() {
    val str: String = functions.firstClass(10)
    println(str)
    assert(str == "Hello 10")
  }

  test("ForEach") {
    val numbers1: List[Int] = functions.someNumbers

    numbers1.foreach((x: Int) => println(x))
    numbers1.foreach(println _)
    numbers1.foreach(println)

    val numbers = numbers1.mkString(", ")
    println(numbers)

    assert(numbers == "-11, -10, -5, 0, 5, 10")
  }

  test("Filter") {
    val numbers: List[Int] = functions.someNumbers
    val filteredNumbers = functions.filter(0, numbers).mkString(", ")
    println(filteredNumbers)
    assert(filteredNumbers == "5, 10")
  }

  test("Filter2") {
    val numbers: List[Int] = functions.someNumbers
    val filteredNumbers = functions.filter2(0, numbers).mkString(", ")
    println(filteredNumbers)
    assert(filteredNumbers == "5, 10")
  }

  test("Partially Applied Functions") {
    def x = functions.createList _
    println(x(1, 2, 3))

    def y = functions.createList(1, _: Int, 3)
    println(y(2))
  }

  test("Closures") {
    //This is a function that makes closures more is the open variable being captured, x is the defined variable
    def makeIncreaser(more: Int) = (x: Int) => x + more

    //this makes a closure x + 1
    val inc1 = makeIncreaser(1)
    println(inc1(10))
    assert(inc1(10)===11)

    val inc2 = makeIncreaser(10)
    println(inc2(100))
    assert(inc2(100)===110)
  }

  test("Repeated Params") {
    val echo: String = functions.echo("Leighton", "Haroon", "Jo")
    println(echo)
    assert(echo == "Leighton Haroon Jo")

    val arr = Array("Leighton", "Haroon", "Jo")
    val arrEcho = functions.echo(arr: _*)
    println(arrEcho)
    assert(arrEcho == "Leighton Haroon Jo")
  }

  test("Named arguments")
  {
    val speed = functions.speed(100, 10)
    assert(speed == 10)

    val speed2 = functions.speed(time = 20, distance = 100)
    assert(speed2 == 5)

    val speed3 = functions.speed()
    println(speed3)
    assert(speed3 == 16.666666f)

    val speed4 = functions.speed(distance = 2000)
    println(speed4)
    assert(speed4 == 33.333332f)

    val speed5 = functions.speed(time = 5)
    println(speed5)
    assert(speed5 == 200.0f)
  }
}

class Functions {
  def firstClass(int: Int): String = {
    val increase = (x: Int) => "Hello " + x
    increase(int)
  }

  def someNumbers = List(-11, -10, -5, 0, 5, 10)

  def filter(x: Int, list: List[Int]) = list.filter(x => x > 0)

  def filter2(x: Int, list: List[Int]) = list.filter(_ > 0)

  def createList(x1: Int, x2: Int, x3: Int) = List(x1, x2, x3)

  def echo(args: String*) = args.mkString(" ")

  def speed(distance: Float = 1000, time: Float = 60) = distance / time
}


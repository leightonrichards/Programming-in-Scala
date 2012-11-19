package ch6.functionalObjects

import org.scalatest.FunSuite

/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 22/09/12
 * Time: 19:15
 * To change this template use File | Settings | File Templates.
 */

class TestRational extends FunSuite{

  def testThis(){
    val rational = new Rational(11)
    assert(rational.toString == "1/11")
  }

  def testConstructor()
  {
    val rational = new Rational(7,11)
    assert(rational.toString == "7/11")
  }

  def testPlus()
  {
    val rational1 = new Rational(1,2)
    val rational2 = new Rational(3,5)
    val rational3 = rational1 + rational2
    assert(rational3.toString=="11/10")
  }

}

class Rational(n: Int, d: Int) {
  require(d != 0)
  private val g = gcd(n.abs, d.abs)
  val numer = n / g
  val denom = d / g
  println(toString)

  def this(d: Int) = this(1, d)

  def +(that: Rational): Rational = new Rational( numer * that.denom + that.numer * denom, denom * that.denom)

  def +(i: Int): Rational = new Rational(numer + i * denom, denom)

  def -(that: Rational): Rational = new Rational( numer * that.denom - that.numer * denom, denom * that.denom)

  def -(i: Int): Rational = new Rational(numer - i * denom, denom)

  def *(that: Rational): Rational = new Rational(numer * that.numer, denom * that.denom)

  def * (i: Int): Rational = new Rational(numer * i, denom)

  def / (that: Rational): Rational = new Rational(numer * that.denom, denom * that.numer)

  def /(i: Int): Rational = new Rational(numer, denom * i)

  override def toString = numer + "/" + denom

  private def gcd(a:Int, b: Int): Int = if (b==0) a else gcd(b, a % b)

}
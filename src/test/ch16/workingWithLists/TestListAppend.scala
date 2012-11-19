package ch16.workingWithLists

import org.scalatest.FunSuite

/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 09/10/12
 * Time: 06:40
 * To change this template use File | Settings | File Templates.
 */
class TestListAppend extends FunSuite{

  //Lists are covariant, immutable, homgeneous(elements have the same type)
  //Lists are constructed by adding appending to the front
  test("Constructing Lists")
  {
    val l = List("a","b","c")
    //Nil represents the Empty List
    val l2 = "a"::"b"::"c"::Nil
    assert(l === l2)
  }

  //The divide and conquer principle
  test("Append to lists")
  {
    def append[T](xs: List[T], ys: List[T]): List[T] =
      xs match {
        case List() => ys
        case x :: xs1 => x :: append(xs1, ys)
      }

    val l = List(0,1,9,7,8)
    val l2 = List(8,2,3,4,8,9)

    //List concatenation
    val l3 = l ::: l2
    val l4 = append(l,l2)

    assert(l3 === 0::1::9::7::8::8::2::3::4::8::9::Nil)
    assert(l4 === 0::1::9::7::8::8::2::3::4::8::9::Nil)
  }

  //The fundamental operations are head, tail and empty
  //head and tail run in constant time, they don't depend on the size of the list
  test("Basic Operations on Lists")
  {
    val l = List("a","b","c")
    assert(l.head === "a")
    assert(l.tail === List("b","c"))
    assert(l.isEmpty === false)
    assert(l.size === 3)

    val List(x,y,z) = l
    assert(x === "a")
    assert(y === "b")
    assert(z === "c")

  }

  test("Accessing the ending of a list")
  {
    val abcde = List("a","b","c","d","e")
    assert(abcde.last==="e")
    assert(abcde.init === List("a","b","c","d"))
    assert(abcde.reverse === List("e","d","c","b","a"))
  }



}


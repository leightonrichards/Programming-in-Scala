package ch15.caseClassesPatternMatching

import org.scalatest.FunSuite

//Different kinds of patterns in this example are
//* Wildcard patterns
//* Constant Patterns
//* Variable Patterns
//* Constructor Patterns
//* Sequence Patterns
//* Tuple Patterns
//* Typed Patterns
class TestPatterns extends FunSuite {

  //Make the super class of the case classes sealed, to protected against further case classes being added
  //Also the scala compiler will then flag missing combinations
  sealed abstract class Expr

  //case classes automatically get factory methods Var("france")
  //All arguments implicitly get val prefix so are maintained as fields
  //Compiler automatically adds natural implementations of toString, hashCode and equals
  case class Var(name: String) extends Expr

  case class Number(num: Double) extends Expr

  case class UnOp(operator: String, arg: Expr) extends Expr

  case class BinOp(operator: String, left: Expr, right: Expr) extends Expr
  val varExpr = new Var("var")
  val number0 = new Number(0)
  val number2 = new Number(2)
  val number3 = new Number(3)
  val binopExpr = new BinOp("=", varExpr, varExpr)
  val unopExpr = new UnOp("abs", number3)
  val binOpExpr2 = BinOp("+", number2, number2)
  val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")

  test("Check Binary")
  {
    def checkbinary(expr: Expr) = {
      expr match {
        //* Variable Patterns
        case BinOp(op, left, right) => println("op=" + op + ", left=" + left + ", right=" + right); true
        //* Wildcard patterns
        case _ => false
      }
    }

    assert(checkbinary(binOpExpr2))
  }

  test("Check Binary 2")
  {
    def checkbinary2(expr: Expr) = {
      expr match {
        case BinOp(_, _, _) => true
        case _ => false
      }
    }
    assert(checkbinary2(binOpExpr2))
  }

  test("describe ")
  {
    def describe(e: Expr): String = e match {
      case Number(_) => "a number"
      case Var(_) => "a variable"
      case _ => throw new RuntimeException // Should not happen
    }
    assert(describe(number2)==="a number")
  }

  test("Deep Match")
  {
    //This is a Deep Match
    def binOpAddNumberZero(expr: Expr) = {
      expr match {
        //* Constructor Patterns
        case BinOp("+", e, Number(0)) => true
        case _ => false
      }
    }

    val op =BinOp("+", Number(1), Number(0))
    assert(binOpAddNumberZero(op) === true)
  }

  test("starts with zero")
  {
    def startsWithZero1(expr: List[Int]) =
      expr match {
        case List(0, _, _) => true
        case _ => false
      }

    def startsWithZero2(expr: List[Int]) =
      expr match {
        //* Sequence Patterns
        case List(0, _*) => true
        case _ => false
      }

    assert(startsWithZero1(List(1,2,3))===false)
    assert(startsWithZero1(List(0,1,2))===true)

    assert(startsWithZero2(List(1,2,3))===false)
    assert(startsWithZero2(List(0,1,2))===true)
  }

  test("Other Describe")
  {
    //Doing it this way switches off the exhaustiveness check
    object OtherDescribe {
      def describe(e: Expr): String = (e: @unchecked) match {
        case Number(_) => "a number"
        case Var(_) => "a variable"
      }
    }

    assert(OtherDescribe.describe(Number(2))==="a number")
  }

  test("get general size")
  {
    //type tests and casts are verbose in scala
    //You are better off using a pattern match
    def getLength(expr: Any) = if (expr.isInstanceOf[String]) expr.asInstanceOf[String].length else 0

    def generalSize(x: Any) = x match {
      case s: String => s.length
      case m: Map[_, _] => m.size
      case _ => -1
    }

    assert(getLength("hello")==5)
    assert(generalSize("hello")==5)
  }

  test("type erasure")
  {
    //Type Erasure, This wont work because scala uses erasure model of generics, no information about type is maintained at runtime
    def isIntMap(x: Any) = x match {
      case m: Map[Int, Int] => true
      case _ => false
    }

    assert(isIntMap(Map(0->1,2->3))===true)
    assert(isIntMap(Map("france"->"paris","england"->"london"))===true)
  }

  test("string array type")
  {
    //Arrays are a special case as the element type is stored with the array value
    def isStringArray(x: Any) = x match {
      case a: Array[String] => true
      case _ => false
    }

    assert(isStringArray(Array(0,1))===false)
    assert(isStringArray(Array("paris","london"))===true)
  }

  test("variable binding")
  {
    //Variable Binding: if the entire Pattern Match succeeds the variable e is binded to UnOp("abs", _)
    def matchUnOp(expr: Expr) = expr match {
      case UnOp("abs", e@UnOp("abs", _)) => e
      case _ =>
    }

    val op1 = UnOp("abs", new Number(2))
    val op2= UnOp("abs", op1)

    assert(matchUnOp(op2)===op1)
  }

  test("pattern guard")
  {
    //Pattern Guard
    def simplifyAdd(e: Expr) = e match {
      case BinOp("+", x, y) if (x == y) => BinOp("*", x, Number(2))
      case _ => e
    }

    val number3 = new Number(3)
    val number2 = new Number(2)
    val binOpExpr = new BinOp("+", number3, number3)
    val binOpExpr2 = BinOp("*", number3, number2)

    assert(simplifyAdd(binOpExpr)===binOpExpr2)
  }

  test("option type")
  {
    def show(x: Option[String]) = x match {
      case Some(s) => s
      case None => "?"
    }
    assert(show(capitals.get("France"))==="Paris")
    assert(show(capitals.get("Germany"))==="?")
  }

  test("Patterns in variable definitions")
  {
    val myTuple=(123,"abc")
    val(num,str)=myTuple
    assert(num===123)
    assert(str==="abc")
  }

  test("case sequences as partial functions")
  {
    val withDefault: Option[Int] => Int = {
      case Some(x) => x
      case None => 0
    }
    val ten: Any = withDefault(Some(10))
    assert(ten===10)
    val none: Any = withDefault(None)
    assert(none===0)
  }

  test("partial functions completeness")
  {
    val second: PartialFunction[List[Int],Int]={
      case x :: y :: _ => y
    }

    val third: PartialFunction[List[Int],Int]={
      case x :: y :: z if (z != Nil) => y
    }

    assert(second.isDefinedAt(List(1,2,3)) === true)
    assert(second.isDefinedAt(List(5,6)) === true)

    assert(third.isDefinedAt(List(1,2,3)) === true)
    assert(third.isDefinedAt(List(5,6)) === false)

    assert(second(List(1,2,3)) === 2)
    try {
      assert(second(List(5, 6)) === 6)
      assert(false)
    }
    catch {
      case ex:Throwable => print(ex)
    }
  }

  test("Patterns in for expressions")
  {
    val x = for((country,city) <- capitals) yield (country)

    assert(x.mkString(",")==="France,Japan")

    val fruits = List(Some("apple"), None, Some("orange"))
    val y = for(Some(fruit) <- fruits) yield fruit

    assert(y.mkString(",")==="apple,orange")
  }
}

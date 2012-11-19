package ch7.controlStructures

import org.scalatest.FunSuite

class TestPrintMultiTable extends FunSuite
{
  test("test MakeRow Zero")
  {
    val table = new PrintMultiTable()
    val rowString: String = table.makeRow(0)
    println(rowString)
    assert(rowString == "   0   0   0   0   0   0   0   0   0   0")
  }

  test("Test MakeRow One")
  {
    val table = new PrintMultiTable()
    val rowString: String = table.makeRow(1)
    println(rowString)
    assert(rowString == "   1   2   3   4   5   6   7   8   9  10")
  }
}

class PrintMultiTable
{
  def makeRowSeq(row: Int) =
    for (col <- 1 to 10) yield
    {
      val prod = (row * col).toString
      val padding = " " * (4 - prod.length)
      padding + prod
    }

  def makeRow(row: Int) = makeRowSeq(row).mkString

  def multiTable() =
  {
    val tableSeq = for (row <- 1 to 10) yield makeRow(row)

    tableSeq.mkString("\n")
  }
}

package ch3.firstSteps

import org.scalatest.{BeforeAndAfter, FunSuite}
import io.Source

/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 11/11/12
 * Time: 18:52
 * To change this template use File | Settings | File Templates.
 */
class ReadFromFileTest extends FunSuite with BeforeAndAfter
{
  val width: Width = new Width("/home/leighton/IdeaProjects/scala/test/src/ch3/firstSteps/ReadFromFileTest.scala")

  test("First Class")
  {
    val widths: String = width.getLineWidths()
    //assert(widths === "")
  }
}

class Width(fileName: String)
{
  //this is a closure
  def getListOfLines(fileName: String): List[String] = Source.fromFile(fileName).getLines().toList

  def widthOfLength(s: String) = s.length.toString.length

  def findTheLongestLine(lines: List[String]): String = lines.reduceLeft((a, b) => if (a.length > b.length) a else b)

  def getLineWidths(): String =
  {
    val lines = getListOfLines(fileName)
    val longestLine = findTheLongestLine(lines)
    val maxWidth = widthOfLength(longestLine)
    val lengths: List[String] =
      for
      {
        line <- lines
        numSpaces = maxWidth - widthOfLength(line)
        padding = " " * numSpaces
      } yield (padding + line.length + " | " + line)
    lengths.mkString("\n")
  }
}


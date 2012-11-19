package ch8.functionsAndClosures

import org.scalatest.FunSuite
import io.Source


/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 26/09/12
 * Time: 07:03
 * To change this template use File | Settings | File Templates.
 */
class TestLongLines extends FunSuite {

  def testProcessFile() {
    val longLine = new LongLines()
    //find lines in this file with a length of 45 characters or longer
    val file: String = longLine.processFile("/home/leighton/IdeaProjects/scala/src/ch8/LongLines.scala", 45)
    println(file)
    assert(file === "    def processLine(line: String): String =  if (line.length > width)  (fileName + \": \" + line) else \"\"")
  }

}

class LongLines {
  def processFile(fileName: String, width: Int): String = {

    def processLine(line: String): String =  if (line.length > width)  (fileName + ": " + line) else ""

    val source = Source.fromFile(fileName)
    val fileSeq = for (newLine <- source.getLines()) yield processLine(newLine)

    fileSeq.mkString("\n")
  }
}


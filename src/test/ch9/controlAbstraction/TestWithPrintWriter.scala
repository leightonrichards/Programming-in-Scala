package ch9.controlAbstraction

import java.io.{PrintWriter, File}
import org.scalatest.FunSuite

/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 29/09/12
 * Time: 17:05
 * To change this template use File | Settings | File Templates.
 */
class TestWithPrintWriter extends FunSuite {
  def testWithPrintWriter {
    val file = new File("/home/leighton/IdeaProjects/scala/src/ch8/date.txt")
    val withP = new WithPrintWriter()
    withP.withPrintWriter(file) {writer => writer.println(new java.util.Date)}
  }
}

class WithPrintWriter {

  def withPrintWriter(file: File)(op: PrintWriter => Unit) {
    val writer = new PrintWriter(file)

    try { op(writer) } finally { writer.close() }
  }
}

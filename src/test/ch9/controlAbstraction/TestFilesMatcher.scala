package ch9.controlAbstraction

import java.io.File
import org.scalatest.FunSuite

/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 29/09/12
 * Time: 09:37
 * To change this template use File | Settings | File Templates.
 */
class TestFilesMatcher extends FunSuite {
  val fileMatcher = new FileMatcher("/home/leighton/IdeaProjects/scala/src/test/ch8/functionsAndClosures")

  test("Files Ending") {
    val ending: Array[File] = fileMatcher.filesEnding(".scala")
    assert(ending.length == 3)
  }

  test("Files Containing") {
    val ending: Array[File] = fileMatcher.filesContaining("unc")
    assert(ending.length == 1 && ending(0).getName == "TestFunctions.scala")
  }

}

class FileMatcher(path: String) {
  private def filesHere = (new File(path)).listFiles()

  private def filesMatching(matcher: String => Boolean) =
    for (file <- filesHere if matcher(file.getName)) yield file

  def filesEnding(query: String) = filesMatching(_.endsWith(query))

  def filesContaining(query: String) = filesMatching(_.contains(query))

  def filesRegex(query: String) = filesMatching(_.matches(query))
}


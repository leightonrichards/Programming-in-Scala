package ch7.controlStructures

import org.scalatest.FunSuite
import java.io.File

/**
 * Created with IntelliJ IDEA.
 * User: leighton
 * Date: 23/09/12
 * Time: 08:25
 * To change this template use File | Settings | File Templates.
 */
class TestNestedIf extends FunSuite{
  test("test nested ifs")
  {
    val nestedIf = new NestedIf("/home/leighton/IdeaProjects/scala/src/test/ch7/controlStructures")
    val forLines = nestedIf.grep(".*taffy.*")
    println(forLines)
    assert(forLines.size===1)
  }
}

// the main loop contains a nested if
class NestedIf (p: String){
  val path = p

  def fileLines(file: java.io.File) = scala.io.Source.fromFile(file).getLines().toList

  def filesHere() = new java.io.File(path).listFiles()

  def matches(trimmed: String, pattern: String) = trimmed.matches(pattern)

  def grep(pattern: String): Array[String] =
  {
    val here: Array[File] = filesHere()

    //this for loop yields Array[String]
    val forLines =
      for
      {
        file <- here
        if file.getName.endsWith(".scala")
        lines = fileLines(file)
        line <- lines
        trimmed = line.trim
        if matches(trimmed,pattern)
      } yield trimmed
    forLines
  }
}
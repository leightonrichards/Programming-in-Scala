// extract.scala

import java.io._
import java.util.jar._

def copyStream(istream: InputStream, ostream: OutputStream) {
  val bytes = new Array[Byte](1024)
  var len = -1
  while ( {len = istream.read(bytes, 0, 1024); len != -1})
    ostream.write(bytes, 0, len)

  ostream.close
  istream.close
}

def getEntryPath(en: String, basename: String): String = {
  if (en.startsWith(basename)) en.substring(basename.length)
  else en
}

def extractJar(file: File) {
  val baseName = file.getName.substring(0, file.getName.lastIndexOf("."))
  val todir = new File(file.getParentFile, baseName)
  todir.mkdirs()

  println("Extracting " + file + " to " + todir)
  val jar = new JarFile(file)
  for (entry: JarEntry <-jar.entries) {
    val entryPath = getEntryPath(entry.getName, baseName)

    println("Extracting to " + todir + "/" + entryPath)
    if (entry.isDirectory) {
      new File(todir, entryPath).mkdirs
    }
    else {
      val istream = jar.getInputStream(entry)
      val ostream = new FileOutputStream(new File(todir, entryPath))
      copyStream(istream, ostream)
    }
  }
}

def accept(file: File) = List("gz", ".zip", ".jar").find {ext => file.getName.endsWith(ext)} != None

def extractFile(fileName: String ) {  extractFile(new File(fileName)) }

def extractFile(fn: File) {
  if (fn.isDirectory) extractFolder(fn) else if (accept(fn)) extractJar(fn)
}

def extractFolder(fn: File) { for (f <- fn.listFiles if (accept(f)) ) extractJar(f) }

if (args == null || args.size<1) extractFile("") else for (arg <- args)  extractFile(arg)

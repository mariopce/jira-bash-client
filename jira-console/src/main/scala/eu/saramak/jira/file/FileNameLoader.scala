package eu.saramak.jira.file

import java.io.File;

/**
 * Created by mario on 02.07.16.
 */
trait FileNameLoader {
  val filename: File

  def loadUser(): Array[String] = {
    import scala.io._
    println(filename.getAbsolutePath)
    try {
         Source.fromFile(filename).getLines().toArray
    } catch {
        case e: Exception =>
          Source.fromFile("~/.config/" + filename.getName).getLines().toArray
    }
  }
}

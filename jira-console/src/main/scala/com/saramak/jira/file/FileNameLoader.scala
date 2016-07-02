package com.saramak.jira.file
import java.io.File;
/**
 * Created by mario on 02.07.16.
 */
trait FileNameLoader {
  val filename : File
  def loadUser(): Array[String] = {
    import scala.io._
    Source.fromFile(filename).getLines().toArray
  }
}

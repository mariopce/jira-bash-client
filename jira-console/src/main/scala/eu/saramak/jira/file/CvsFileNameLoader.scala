package eu.saramak.jira.file

import java.io.File

/**
 * Created by mario on 02.07.16.
 */
class CvsFileNameLoader(vfileName: File) extends FileNameLoader {
  override val filename: File = vfileName

}

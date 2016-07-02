package eu.saramak.jira

import java.io.File

/**
 * Created by mario on 02.07.16.
 */
case class Config(var username: String = "username", var password: String = "password",
                  var url: String = "http://www.jira.com", var project: String = "", configFile: File = new File("conf.prop"), mode: String = "",
                  infoUser: String = "", report: String = "",
                  task: String = "", filter: String = "", addComment: String = "", newStatus: String = "",
                  usersFile: File = new File("users.cvs"), params: Map[String, String] = Map()
                   ) {

}

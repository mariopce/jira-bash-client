package eu.saramak.jira

import java.nio.Buffer

import net.rcarz.jiraclient.Issue

import scala.collection.mutable

/**
 * Created by mario on 02.07.16.
 */
class UserProblem(issues: mutable.Buffer[Issue], description: String) {

  def display() = {
    println(description)
    for (issue <- issues) {
      println(issue + ":" + issue.getAssignee.getName + ":" + issue.getAssignee.getEmail + ":" + issue.getSummary + ":" + issue.getStatus)
    }
    println("====================")
  }


}

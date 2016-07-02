package eu.saramak.jira.helper

import net.rcarz.jiraclient.{Field, Issue}

/**
 * Created by mario on 02.07.16.
 */
class IssueUpdater(issue: Issue) {
  def addSubtask(nextParam: Map[String, String]): String = {
    val fl = issue.createSubtask()
    for ((k, v) <- nextParam) {
      fl.field(k, v)
    }
    fl.execute().toString
  }

  def updateNewStatus(newStatus: String) = {
    /* Now let's start progress on this issue. */
    println(issue.getStatus.getName)
    issue.transition()
      .execute(newStatus);
    issue.refresh()
  }


  def addComment(addComment: String) = {
    issue.addComment(addComment)
  }


}

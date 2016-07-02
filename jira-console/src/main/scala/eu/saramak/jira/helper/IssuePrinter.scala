package eu.saramak.jira.helper

import net.rcarz.jiraclient.Issue
import net.rcarz.jiraclient._

import scala.collection.JavaConverters._

// Feature warning if you don't enable implicit conversions...

import scala.language.implicitConversions
import scala.collection.convert.WrapAsScala.enumerationAsScalaIterator

/**
 * Created by mario on 02.07.16.
 */
class IssuePrinter(issue: Issue) {
  def printWorkLogs() = {
    println("Worklog")
    for (work <- issue.getAllWorkLogs.asScala) {
      println(work.toString);
      println("comment: " + work.getComment);
      println("time " + work.getTimeSpentSeconds);
    }
    println("endWorklog")
  }

  def printGeneralInfo() = {
    println(issue);
    println("description: " + issue.getDescription);
    println("sumary: " + issue.getSummary);
  }

  def printAllComments(): Unit = {
    println("comments")
    for (com <- issue.getComments.asScala) {
      println("author: " + com.getAuthor)
      println("date:" + com.getCreatedDate)
      println(com.getBody);
    }
    println("endcomments")
  }

}

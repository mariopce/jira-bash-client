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
    println("<worklogs>")
    for (work <- issue.getAllWorkLogs.asScala) {
      println("<work>")
      println(work.toString);
      println("comment: " + work.getComment);
      println("time " + work.getTimeSpentSeconds);
      println("</work>")
    }
    println("</worklogs>")
  }

  def printGeneralInfo() = {
    printIssue(issue)
  }

  def printIssue(i: Issue): Unit = {
    print(i);
    print(":" + i.getSummary);
    print(":" + i.getDescription);
    print(":")
    printParent()
    println()
  }

  def printAllComments(): Unit = {
    println("<comments>")
    for (com <- issue.getComments.asScala) {
      print("<comment")
      print(" author=\"" + com.getAuthor+"\"")
      print(" date=\"" + com.getCreatedDate+"\"")
      println(">")
      println(com.getBody);
      println("</comment>")
    }
    println("</comments>")
  }
  def printParent(): Unit ={
    print(issue.getParent)
  }

  def printSubtasks(): Unit = {
     for (s <- issue.getSubtasks.asScala){
       printIssue(s)
     }
  }
}

package com.saramak.jira

import java.io.File
import java.util
import java.util.Properties
import eu.saramak.jira.Config
import eu.saramak.jira.file.CvsFileNameLoader
import eu.saramak.jira.helper.{UserAnomalyAnalyzer, IssuePrinter, IssueUpdater}
import net.rcarz.jiraclient._

import scala.collection.JavaConverters._
import scala.collection.mutable

// Feature warning if you don't enable implicit conversions...

import scala.language.implicitConversions
import scala.collection.convert.WrapAsScala.enumerationAsScalaIterator


object Jira {


  def getJiraClient(config: Config): JiraClient = {
    val creds = new BasicCredentials(config.username, config.password);
    val jira = new JiraClient(config.url, creds);
    jira
  }

  def main(args: Array[String]): Unit = {

    val parser = new scopt.OptionParser[Config]("jira") {

      head("Jira console client version " + "1.0")

      opt[String]('u', "username").action((v, c) =>
        c.copy(username = v)).text("put username by this option or by config file")
      opt[String]('p', "password").action((v, c) =>
        c.copy(password = v)).text("put password by this option or by config file")
      opt[String]('h', "url").action((v, c) =>
        c.copy(url = v)).text("put hostname by this option or by config file. Ex http://jira.com")
      opt[String]('k', "project").abbr("pk").action((v, c) =>
        c.copy(project = v)).text("project key")
      opt[File]('c', "config").action((f, c) =>
        c.copy(configFile = f)).text("path to propKey=value file, with username, password and url. ")

      cmd("getinfo").action((_, c) => c.copy(mode = "getinfo")).text("getinfo is a command.").children(
        opt[String]("user").action((v, c) => c.copy(infoUser = v)).text("About which user you want get info"),
        opt[String]("report").action((v, c) => c.copy(report = v)).text("Reports. --report useranomaly, or userstasks"),
        opt[String]("task").action((v, c) => c.copy(task = v)).text("About which task you want get info"),
        opt[String]("filter").action((v, c) => c.copy(filter = v)).text("Filter ex status==inprogress or status==done")

      )

      cmd("update").action((_, c) => c.copy(mode = "update")).text("update is a command.").children(
        opt[String]("task").action((v, c) => c.copy(task = v)).text("Which task you want update"),
        opt[String]("addcomment").action((v, c) => c.copy(addComment = v)).text("add comment to task you want update"),
        opt[String]("status").action((v, c) => c.copy(newStatus = v)).text("change status task you want update"),
        opt[Map[String, String]]("addsubtask").action((s, c) => c.copy(params = s)).text("additional params title description")
      )
      note("=================")


    }


    //  parser.parse returns Option[C]
    parser.parse(args, Config()) match {

      case Some(config) =>

        if (config.configFile.exists()) {
          val prop = new Properties()
          prop.load(scala.io.Source.fromFile(config.configFile).reader())
          val propMap = prop.asScala
          config.username = propMap.get("username").get
          config.password = propMap.get("password").get
          config.url = propMap.get("url").get
          config.project = propMap.get("project").get

        }
        var jiraClient = getJiraClient(config);
        //println("Connecting to " + config.url + " as " + config.username)
        if (config.mode == "getinfo") {
          if (!config.task.isEmpty) {

            val issue = jiraClient.getIssue(config.task);
            val ip = new IssuePrinter(issue)
            config.filter match {
              case "" =>
                ip.printGeneralInfo()
                ip.printAllComments()
                ip.printWorkLogs()
                ip.printSubtasks()
              case "comments" =>
                ip.printAllComments()
              case "general" =>
                ip.printGeneralInfo()
              case "work" =>
                ip.printWorkLogs()
              case "parent" =>
                ip.printParent()
              case "subtasks" =>
                ip.printSubtasks()
            }
          }
          if (!config.infoUser.isEmpty) {
            val issues = new IssueSearcher(jiraClient).searchIssuesAssigned(config.infoUser, config.project, config.filter)
            for (issue <- issues) {
              println(issue + ":" + issue.getAssignee.getName + ":" + issue.getAssignee.getEmail + ":" + issue.getSummary + ":" + issue.getStatus)
            }
          }
          if (!config.report.isEmpty) {
            if (config.report == "useranomaly") {
              if (config.usersFile.exists()) {
                val problems = new UserAnomalyAnalyzer(jiraClient, config).getReportAboutUserWrongStatus();
                for (problem <- problems) {
                  problem.display()
                }
              } else {
                println(config.usersFile + " non exist")
              }
            }
          }
        }
        if (config.mode == "update") {
          if (!config.task.isEmpty) {
            val issue = jiraClient.getIssue(config.task);
            val issueUpdater = new IssueUpdater(issue)
            if (!config.addComment.isEmpty) {
              issueUpdater.addComment(config.addComment)
            }
            if (!config.newStatus.isEmpty) {
              issueUpdater.updateNewStatus(config.newStatus)
            }
            if (!config.params.isEmpty) {
              println(issueUpdater.addSubtask(config.params))
            }
          }
        }

      case None =>
        // arguments are bad, error message will have been displayed
        println("Maybe some params are wrong " + args)
//        println(parser.usage)
    }

  }
}


class IssueSearcher(jiraClient: JiraClient) {

  def searchIssuesAssigned(username: String, project: String, query: String): mutable.Buffer[Issue] = {
    val q = "assignee=" + username + " AND project=" + project + " " + query
    println("query is " + q)
    return jiraClient.searchIssues(q).issues.asScala
  }
}
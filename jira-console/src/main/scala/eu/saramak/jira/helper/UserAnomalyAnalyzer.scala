package eu.saramak.jira.helper

import com.saramak.jira.IssueSearcher
import eu.saramak.jira.{Config, UserProblem}
import eu.saramak.jira.file.CvsFileNameLoader
import net.rcarz.jiraclient.JiraClient

import scala.collection.mutable.ListBuffer

/**
 * Created by mario on 02.07.16.
 */
class UserAnomalyAnalyzer(jiraClient: JiraClient, config: Config) {
  def getReportAboutUserWrongStatus(): List[UserProblem] = {
    var listOfProblems = new ListBuffer[UserProblem]
    val users = new CvsFileNameLoader(config.usersFile).loadUser()
    for (user <- users) {
      val issuesInProgress = new IssueSearcher(jiraClient).searchIssuesAssigned(user, config.project, " AND status=\"In Progress\"");
      if (issuesInProgress.size != 1) {
        //it is a problem
        val problem = new UserProblem(issuesInProgress, "User " + user + " have more then 1 or 0 In Progress")
        listOfProblems += problem
      }
    }
    return listOfProblems.toList
  }


}

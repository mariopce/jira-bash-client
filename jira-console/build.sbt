name := """jira-console-client"""

lazy val commonSettings = Seq(
  version := "0.1-SNAPSHOT",
  organization := "eu.saramak",
  scalaVersion := "2.11.8",
  test in assembly := {}
)


lazy val app = (project in file("app")).
  settings(commonSettings: _*).
  settings(
    mainClass in assembly := Some("eu.saramak.jira.Jira")
    // more settings here ...
  )

lazy val utils = (project in file("utils")).
  settings(commonSettings: _*).
  settings(
    assemblyJarName in assembly := "utils.jar"
    // more settings here ...
  )


libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

libraryDependencies += "com.github.scopt" %% "scopt" % "3.5.0"

libraryDependencies += "net.rcarz" % "jira-client" % "0.5"

resolvers += Resolver.sonatypeRepo("public")
resolvers += DefaultMavenRepository

fork in run := true
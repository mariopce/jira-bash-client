name := """jira-console-client"""

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

libraryDependencies += "com.github.scopt" %% "scopt" % "3.5.0"

libraryDependencies += "net.rcarz" % "jira-client" % "0.5"

resolvers += Resolver.sonatypeRepo("public")
resolvers += DefaultMavenRepository

fork in run := true
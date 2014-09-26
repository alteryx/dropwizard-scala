// Basic project information
name          := "dropwizard-scala"

version       := "0.7.1"

organization  := "com.clearstorydata"

scalaVersion := "2.11.1"

crossScalaVersions := Seq("2.10.0", "2.11.0")

scalacOptions <<= scalaVersion map { sv: String =>
  sv match {
    case s: String if s.startsWith("2.9") => {
      Seq("-encoding", "UTF-8", "-deprecation", "-unchecked", "-target:jvm-1.5")
    }
    case _ => {
      Seq("-encoding", "UTF-8", "-deprecation", "-unchecked", "-target:jvm-1.6", "-feature", "-Ywarn-adapted-args")
    }
  }
}

resolvers += "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository"

libraryDependencies ++= Seq(
    "io.dropwizard" % "dropwizard-core" % "0.7.1",
    "nl.grons" %% "metrics-scala" % "3.2.0",
    "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.4.1",
    "org.scalatest" %% "scalatest" % "2.2.0" % "test",
    "org.mockito" % "mockito-core" % "1.9.5" % "test",
    "com.sun.jersey.jersey-test-framework" % "jersey-test-framework-core" % "1.18.1" % "test",
    "com.sun.jersey.jersey-test-framework" % "jersey-test-framework-inmemory" % "1.18.1" % "test",
    "com.sun.jersey" % "jersey-client" % "1.18.1" % "test"
)

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

publishTo <<= version { (v: String) =>
  val maven = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at maven + "content/repositories/snapshots/")
  else
    Some("releases"  at maven + "service/local/staging/deploy/maven2/")
}

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { x => false }

pomExtra := (
  <url>https://github.com/clearstorydata/dropwizard-scala</url>
  <licenses>
    <license>
      <name>Apache License 2.0</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:clearstorydata/dropwizard-scala.git</url>
    <connection>scm:git:git@github.com:clearstorydata/dropwizard-scala.git</connection>
  </scm>
  <developers>
    <developer>
      <id>bretthoerner</id>
      <name>Brett Hoerner</name>
      <url>http://bretthoerner.com</url>
      <timezone>-6</timezone>
    </developer>
    <developer>
      <id>markhamstra</id>
      <name>Mark Hamstra</name>
      <url>http://clearstorydata.com</url>
      <timezone>-8</timezone>
    </developer>
  </developers>
)

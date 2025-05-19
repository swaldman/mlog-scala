val nexus = "https://oss.sonatype.org/"
val nexusSnapshots = nexus + "content/repositories/snapshots"
val nexusStaging = nexus + "service/local/staging/deploy/maven2"

ThisBuild / resolvers += ("releases" at nexusStaging)
ThisBuild / resolvers += ("snapshots" at nexusSnapshots)
ThisBuild / resolvers += ("Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/")

ThisBuild / publishTo := {
  if (isSnapshot.value) Some("snapshots" at nexusSnapshots ) else Some("staging" at nexusStaging )
}

ThisBuild / organization       := "com.mchange"
ThisBuild / version            := "0.4.0-SNAPSHOT"
ThisBuild / scalaVersion       := "3.3.6"
ThisBuild / scalacOptions      := Seq("-deprecation", "-feature")

val mainProjectName     = "mlog-scala"
val zioProjectName      = "mlog-scala-zio"

def pomExtraXml(projectName : String) = (
      <url>https://github.com/swaldman/{projectName}</url>
      <licenses>
        <license>
        <name>GNU Lesser General Public License, Version 2.1</name>
        <url>http://www.gnu.org/licenses/lgpl-2.1.html</url>
          <distribution>repo</distribution>
        </license>
        <license>
        <name>Eclipse Public License, Version 1.0</name>
        <url>http://www.eclipse.org/org/documents/epl-v10.html</url>
          <distribution>repo</distribution>
        </license>
      </licenses>
      <scm>
        <url>git@github.com:swaldman/{projectName}.git</url>
        <connection>scm:git:git@github.com:swaldman/{projectName}</connection>
      </scm>
      <developers>
        <developer>
          <id>swaldman</id>
          <name>Steve Waldman</name>
          <email>swaldman@mchange.com</email>
        </developer>
      </developers>
)

lazy val root = project
  .in(file("."))
  .settings(
    name                :=  mainProjectName,
    crossScalaVersions  :=  Seq("2.10.7","2.11.12","2.12.20","2.13.16","3.3.6"),
    libraryDependencies +=  "com.mchange" % "mchange-commons-java" % "0.3.2",
    pomExtra            :=  pomExtraXml(mainProjectName)
  )

// for now, no crossScalaVersions, we've implemented mlog-scala-zio in Scala 3 style.
// it could easily be backported to 2.12 and 2.13 if that seems worth it someday
lazy val zio = project
  .dependsOn(root)
  .in(file("zio"))
  .settings(
    name                :=  zioProjectName,
    libraryDependencies +=  "dev.zio" %% "zio" % "2.1.18",
    pomExtra            :=  pomExtraXml(zioProjectName)
  )



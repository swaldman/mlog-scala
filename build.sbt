val nexus = "https://oss.sonatype.org/"
val nexusSnapshots = nexus + "content/repositories/snapshots"
val nexusStaging = nexus + "service/local/staging/deploy/maven2"

ThisBuild / resolvers += ("releases" at nexusStaging)
ThisBuild / resolvers += ("snapshots" at nexusSnapshots)
ThisBuild / resolvers += ("Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/")

ThisBuild / publishTo := {
  if (isSnapshot.value) Some("snapshots" at nexusSnapshots ) else Some("staging" at nexusStaging )
}

val projectName = "mlog-scala"

val pomExtraXml = (
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
    organization        :=  "com.mchange",
    name                :=  projectName,
    version             :=  "0.3.15-SNAPSHOT",
    scalaVersion        :=  "2.12.18",
    crossScalaVersions  :=  Seq("2.10.7","2.11.12","2.12.18","2.13.12","3.3.1"),
    scalacOptions       ++= Seq("-deprecation", "-feature"),
    libraryDependencies +=  "com.mchange" % "mchange-commons-java" % "0.2.20",
    pomExtra            :=  pomExtraXml
  )


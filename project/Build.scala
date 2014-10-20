import sbt._

object MLogScalaBuild extends Build {

  val nexus = "https://oss.sonatype.org/"
  val nexusSnapshots = nexus + "content/repositories/snapshots";
  val nexusReleases = nexus + "service/local/staging/deploy/maven2";

  val projectName = "mlog-scala";

  val mySettings = Seq( 
    Keys.organization := "com.mchange",
    Keys.name := projectName, 
    Keys.version := "0.3.4", 
    Keys.crossScalaVersions := Seq("2.10.3","2.11.2"),
    Keys.publishTo <<= Keys.version { 
      (v: String) => {
	if (v.trim.endsWith("SNAPSHOT"))
	  Some("snapshots" at nexusSnapshots )
	else
	  Some("releases"  at nexusReleases )
      }
    },
    Keys.resolvers += ("snapshots" at nexusSnapshots ),
    Keys.scalacOptions ++= Seq("-deprecation", "-feature"),
    Keys.pomExtra := pomExtraXml
  );

  val dependencies = Seq(
    "com.mchange" % "mchange-commons-java" % "0.2.8" 
  );

  override lazy val settings = super.settings ++ mySettings;

  lazy val mainProject = Project(
    id = projectName,
    base = file("."),
    settings = Project.defaultSettings ++ (Keys.libraryDependencies ++= dependencies)
  ); 

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
  );
}


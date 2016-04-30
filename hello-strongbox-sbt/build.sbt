organization := "org.carlspring.strongbox.examples"

name := "hello-strongbox-sbt"

version := "1.0-SNAPSHOT"

publishMavenStyle := true

credentials += Credentials("Strongbox",
                           "localhost",
                           sys.props.getOrElse("strongbox.username", ""),
                           sys.props.getOrElse("strongbox.password", ""))

resolvers += "Strongbox" at "http://localhost:48080/storages/storage0/group-releases/"

publishTo := {
  val distributionRepository = "http://localhost:48080/storages/storage0/"
  if (isSnapshot.value)
    Some("snapshots" at distributionRepository + "snapshots")
  else
    Some("releases" at distributionRepository + "releases")
}

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.11"
)

organization := "org.carlspring.strongbox.examples"

name := "hello-strongbox-sbt"

// GPR-friendly artifact version when deploying to GitHub Package Registry
// The version string is generated via a GitHub action that deploys the artifact to the GitHub Package Registry
// This is necessary as GitHub does not support overriding of published artifacts
// See https://docs.github.com/en/free-pro-team@latest/packages/publishing-and-managing-packages/deleting-a-package#about-public-package-deletion
val gprVersion = sys.props.getOrElse("gpr.version", "1.0-SNAPSHOT")
version := gprVersion

publishMavenStyle := true

// needed for publishing to GitHub Package Registry
githubOwner := "strongbox"
githubRepository := "strongbox-examples"

credentials += Credentials("Strongbox Repository Manager",
                           "localhost",
                           sys.props.getOrElse("strongbox.username", "admin"),
                           sys.props.getOrElse("strongbox.password", "password"))

resolvers += "Strongbox" at "http://localhost:48080/storages/storage0/group-releases/"

publishTo := {
  val distributionRepository = "http://localhost:48080/storages/storage0/"
  val githubPackageRegistry = githubPublishTo.value

  if (sys.env.get("GITHUB_TOKEN").isEmpty) {
    if (isSnapshot.value) {
      Some("snapshots" at distributionRepository + "snapshots")
    } else {
      Some("releases" at distributionRepository + "releases")
    }
  } else {
    githubPackageRegistry
  }
}

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.11"
)

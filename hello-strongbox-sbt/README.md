This is an example of how to use the Strongbox artifact repository manager with SBT.

# The `build.sbt` file

This file is used to define your project's information about things like your dependencies, which repositories to use to resolve and deploy artifacts, which plugins to use, etc.

The key things that each project needs to have are:

* A `organization` : A logical prefix where other similar projects reside.
* A `name` : The artifact's name.
* A `version`: The version of the artifact.
* A `publishMavenStyle := true` : Used to specify that the remote repository's layout format is Maven-based.
* A `credentials` : Defines your credentials for the remote repositories.
* A `resolvers` : Defines where to resolve the dependencies from (if this is not specified, or the required artifacts cannot be located in the remote, SBT will attempt to resolve it from it's built-in default list of remotes.
* A `publishTo` : Defines where to deploy the artifact(s) produced by this build to.

# The `repositories` file

This file lists the remote repositories (and their layouts) to use when resolving artifacts.

It is perhaps most-practical and portable to include the `repositories` file as part of your project.

For more details check the "See also" section below.

# The credentials

These are plain-text. One way to do it is by specifying these as system properties such as `-Dstrongbox.username=maven -Dstrongbox.password=password` and reading them in from the `build.sbt` script.

For more details check the "See also" section below.

# How to deploy

Execute the following command:

    sbt compile publish

This will build the code and deploy it to Strongbox.

Via the SBT console you can do:

    carlspring@linux-70e2:/home/carlspring/strongbox-examples/hello-strongbox-sbt> sbt
    [info] Set current project to hello-strongbox-sbt (in build file:/home/carlspring/strongbox-examples/hello-strongbox-sbt/)
    > compile
    [success] Total time: 0 s, completed Apr 30, 2016 4:54:22 AM
    > run
    [info] Running Main 
    Hello, Strongbox!
    [success] Total time: 0 s, completed Apr 30, 2016 4:54:23 AM
    > publish
    [info] Wrote /home/carlspring/strongbox-examples/hello-strongbox-sbt/target/scala-2.10/hello-strongbox-sbt_2.10-1.0-SNAPSHOT.pom
    [info] :: delivering :: org.carlspring.strongbox.examples#hello-strongbox-sbt_2.10;1.0-SNAPSHOT :: 1.0-SNAPSHOT :: integration :: Sat Apr 30 04:54:30 BST 2016
    [info] 	delivering ivy file to /home/carlspring/strongbox-examples/hello-strongbox-sbt/target/scala-2.10/ivy-1.0-SNAPSHOT.xml
    [info] 	published hello-strongbox-sbt_2.10 to http://localhost:48080/storages/storage0/snapshots/org/carlspring/strongbox/examples/hello-strongbox-sbt_2.10/1.0-SNAPSHOT/hello-strongbox-sbt_2.10-1.0-SNAPSHOT.pom
    [info] 	published hello-strongbox-sbt_2.10 to http://localhost:48080/storages/storage0/snapshots/org/carlspring/strongbox/examples/hello-strongbox-sbt_2.10/1.0-SNAPSHOT/hello-strongbox-sbt_2.10-1.0-SNAPSHOT.jar
    [info] 	published hello-strongbox-sbt_2.10 to http://localhost:48080/storages/storage0/snapshots/org/carlspring/strongbox/examples/hello-strongbox-sbt_2.10/1.0-SNAPSHOT/hello-strongbox-sbt_2.10-1.0-SNAPSHOT-sources.jar
    [info] 	published hello-strongbox-sbt_2.10 to http://localhost:48080/storages/storage0/snapshots/org/carlspring/strongbox/examples/hello-strongbox-sbt_2.10/1.0-SNAPSHOT/hello-strongbox-sbt_2.10-1.0-SNAPSHOT-javadoc.jar
    [success] Total time: 2 s, completed Apr 30, 2016 4:54:30 AM

# See also

* [SBT: Publishing](http://www.scala-sbt.org/0.13/docs/Publishing.html)
* [SBT: Repositories](http://www.scala-sbt.org/0.13/docs/Proxy-Repositories.html)

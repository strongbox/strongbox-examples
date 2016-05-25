This is an example of how to use the Strongbox artifact repository manager with Maven.

# The `pom.xml` file

This file is used to define your project's information about things like youre dependencies, repositories to use to resolve and deploy artifacts, plugins to use, etc.

The key things each project needs to have are:

* A `groupId` : A logical prefix where other similar projects reside.
* A `artifactId` : The artifact's name.
* A `version`: The version of the artifact.
* A `packaging` (optional, defaults to `jar`, if not specified): The packaging type (jar, war, etc...)
* A `<repositories/>` section: Defines where to resolve dependencies from.
* A `<distributionManagement/>` section: Defines where to deploy the artifact(s) produced by this build to.

# The `settings.xml` file

This is the Maven configuration file used to define global settings, such as remote repositories, preferred mirrors, proxy settings, repository credentials, etc.

This file normally needs to reside under `~/.m2` or `C:\Users\youruser\.m2`.

It is important to note that the `<repositories/>` and `<distributionManagement/>` sections in your `pom.xml` file always have an `<id/>`. This `<id/>` needs to match the `<id/>` of a corresponding `<server/>` in your `settings.xml` file.

# How to deploy

Execute the following command:

    mvn clean deploy

This will build the code and deploy it to Strongbox.

    carlspring@linux-70e2:/home/carlspring/strongbox-examples/hello-strongbox-maven> mvn clean deploy
    [INFO] Scanning for projects...
    [INFO]                                                                         
    [INFO] ------------------------------------------------------------------------
    [INFO] Building hello-strongbox-maven 1.0-SNAPSHOT
    [INFO] ------------------------------------------------------------------------
    [INFO] 
    [INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ hello-strongbox-maven ---
    [INFO] Deleting /home/carlspring/strongbox-examples/hello-strongbox-maven/target
    [INFO] 
    [INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ hello-strongbox-maven ---
    [WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
    [INFO] skip non existing resourceDirectory /home/carlspring/strongbox-examples/hello-strongbox-maven/src/main/resources
    [INFO] 
    [INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ hello-strongbox-maven ---
    [INFO] Changes detected - recompiling the module!
    [WARNING] File encoding has not been set, using platform encoding UTF-8, i.e. build is platform dependent!
    [INFO] Compiling 1 source file to /home/carlspring/strongbox-examples/hello-strongbox-maven/target/classes
    [INFO] 
    [INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ hello-strongbox-maven ---
    [WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
    [INFO] skip non existing resourceDirectory /home/carlspring/strongbox-examples/hello-strongbox-maven/src/test/resources
    [INFO] 
    [INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ hello-strongbox-maven ---
    [INFO] No sources to compile
    [INFO] 
    [INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ hello-strongbox-maven ---
    [INFO] No tests to run.
    [INFO] 
    [INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ hello-strongbox-maven ---
    [INFO] Building jar: /home/carlspring/strongbox-examples/hello-strongbox-maven/target/hello-strongbox-maven-1.0-SNAPSHOT.jar
    [INFO] 
    [INFO] --- maven-install-plugin:2.4:install (default-install) @ hello-strongbox-maven ---
    [INFO] Installing /home/carlspring/strongbox-examples/hello-strongbox-maven/target/hello-strongbox-maven-1.0-SNAPSHOT.jar to /home/carlspring/.m2/repository/org/carlspring/strongbox/examples/hello-strongbox-maven/1.0-SNAPSHOT/hello-strongbox-maven-1.0-SNAPSHOT.jar
    [INFO] Installing /home/carlspring/strongbox-examples/hello-strongbox-maven/pom.xml to /home/carlspring/.m2/repository/org/carlspring/strongbox/examples/hello-strongbox-maven/1.0-SNAPSHOT/hello-strongbox-maven-1.0-SNAPSHOT.pom
    [INFO] 
    [INFO] --- maven-deploy-plugin:2.7:deploy (default-deploy) @ hello-strongbox-maven ---
    Downloading: http://localhost:48080/storages/storage0/snapshots/org/carlspring/strongbox/examples/hello-strongbox-maven/1.0-SNAPSHOT/maven-metadata.xml
    Uploading: http://localhost:48080/storages/storage0/snapshots/org/carlspring/strongbox/examples/hello-strongbox-maven/1.0-SNAPSHOT/hello-strongbox-maven-1.0-20160430.031713-1.jar
    Uploaded: http://localhost:48080/storages/storage0/snapshots/org/carlspring/strongbox/examples/hello-strongbox-maven/1.0-SNAPSHOT/hello-strongbox-maven-1.0-20160430.031713-1.jar (3 KB at 11.5 KB/sec)
    Uploading: http://localhost:48080/storages/storage0/snapshots/org/carlspring/strongbox/examples/hello-strongbox-maven/1.0-SNAPSHOT/hello-strongbox-maven-1.0-20160430.031713-1.pom
    Uploaded: http://localhost:48080/storages/storage0/snapshots/org/carlspring/strongbox/examples/hello-strongbox-maven/1.0-SNAPSHOT/hello-strongbox-maven-1.0-20160430.031713-1.pom (2 KB at 41.6 KB/sec)
    Downloading: http://localhost:48080/storages/storage0/snapshots/org/carlspring/strongbox/examples/hello-strongbox-maven/maven-metadata.xml
    Uploading: http://localhost:48080/storages/storage0/snapshots/org/carlspring/strongbox/examples/hello-strongbox-maven/1.0-SNAPSHOT/maven-metadata.xml
    Uploaded: http://localhost:48080/storages/storage0/snapshots/org/carlspring/strongbox/examples/hello-strongbox-maven/1.0-SNAPSHOT/maven-metadata.xml (798 B at 21.1 KB/sec)
    Uploading: http://localhost:48080/storages/storage0/snapshots/org/carlspring/strongbox/examples/hello-strongbox-maven/maven-metadata.xml
    Uploaded: http://localhost:48080/storages/storage0/snapshots/org/carlspring/strongbox/examples/hello-strongbox-maven/maven-metadata.xml (312 B at 8.7 KB/sec)
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time: 2.220 s
    [INFO] Finished at: 2016-04-30T04:17:13+01:00
    [INFO] Final Memory: 21M/311M
    [INFO] ------------------------------------------------------------------------

# See also
* [Maven: POM Reference](https://maven.apache.org/pom.html)
* [Maven: Settings Reference](https://maven.apache.org/settings.html)
* [Maven: Credentials Encryption Guide](https://maven.apache.org/guides/mini/guide-encryption.html)

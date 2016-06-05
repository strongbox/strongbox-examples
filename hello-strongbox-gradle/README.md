This is an example of how to use the Strongbox artifact repository manager with [Gradle](http://gradle.org/).

# Before you start
Make sure that your Strongbox instance is up and running. If you are new to Strongbox, please visit the [Installation](https://github.com/strongbox/strongbox#installation) page first.

# Requirements
You will need the following software installed on your machine to make this example working:
* Java Development Kit (JDK) version 1.8.x or higher
* [Gradle](http://gradle.org/) version 2.11 or higher

# The `gradle.build` file
This is a build script written in Groovy. Artifact-related properties could be defined and customized in the following way:
* Add `group` property to specify logical prefix where other similar projects reside (like the `groupId` in a Maven `pom.xml` file)
* Add `version` to specify artifact's version
* Customize the `repositories` section to add any artifact repositories such as Maven Central (included by default)
* Customize the `dependencies` section to add any dependencies to your project; dependency management could be implemented using `ext` properties object and applying common settings from single separate gradle file, [read more here](https://docs.gradle.org/current/userguide/plugins.html) 
* Customize `uploadArchives` to specify artifact-to-repository uploading process, matches Maven `distributionManagement` section

Read more on [gradle scripting here](https://docs.gradle.org/current/userguide/tutorial_using_tasks.html).

# The `settings.gradle` file
Use this file to specify project-specific settings of gradle build as well as the artifact's name under the `rootProject.name` property.

# The `gradle.properties` file
This file should be created manually by every developer, or it's perfectly fine to have global settings located in `.gradle/gradle.properties`. For more details please visit [Gradle Build environment page](https://docs.gradle.org/current/userguide/build_environment.html). Here are the properties for this example project:

    mavenUser=maven
    mavenPassword=password

# Credentials

## Via `gradle.properties`:
As illustrated in the section above, you should place your credentials in the `gradle.properties` in your project's root, or in the `~/.gradle` directory (which will have a global impact). Add the following properties:

    mavenUser=maven
    mavenPassword=password

The down side here is that you would need to have this file on the file system.

## Via command-line:
Specify the following Java args:

    -Dcredentials.username=maven -Dcredentials.password=password

The benefit of doing it like this is that you don't need to have the credentials stored in a file on the file system. Most CI servers would allow you to pass in a secured value for credential properties.

# How to build from source code
When you clone this example project sources, go into that directory and execute `gradle clean upload`. This will try to deploy artifacts to the Strongbox Repository.

# How to customize target artifact repository during upload
Change the `repositories.mavenDeployer.repository` property and specify the URL and credentials of any repository you would like to upload to. Make sure that you don't have `SNAPSHOT` in your version if you are uploading to the release repository.

# Example output

    >:hello-strongbox-gradle neo$ gradle clean upload
    Starting a new Gradle Daemon for this build (subsequent builds will be faster).
    :clean
    :compileJava
    :processResources UP-TO-DATE
    :classes
    :jar
    :javadoc
    :javadocJar
    :sourcesJar
    :uploadArchives
    Could not find metadata upload:hello-strongbox-gradle:1.0-SNAPSHOT/maven-metadata.xml in remote (http://localhost:48080/storages/storage0/snapshots/)
    Could not find metadata upload:hello-strongbox-gradle/maven-metadata.xml in remote (http://localhost:48080/storages/storage0/snapshots/)
           
    BUILD SUCCESSFUL
           
    Total time: 13.035 secs

For the second time when `maven-metadata.xml` will be placed in the remote (in this case local) repository you will not see the warning:

    >:hello-strongbox-gradle neo$ gradle clean upload
    :clean
    :compileJava
    :processResources UP-TO-DATE
    :classes
    :jar
    :javadoc
    :javadocJar
    :sourcesJar
    :uploadArchives
    
    BUILD SUCCESSFUL
    
    Total time: 2.034 secs

# Troubleshooting

* If you see error about missing metadata.xml or something similar for the FIRST time when you upload an artifact - don't worry, because this simply said that you are new to this repository entry and there is no existing metadata for your artifact. It will be created.

# See Also
* [Gradle: Tutorial Using Tasks](https://docs.gradle.org/current/userguide/tutorial_using_tasks.html)
* [Gradle: Artifact Management](https://docs.gradle.org/current/userguide/artifact_management.html)
* [Gradle: Build Environment](https://docs.gradle.org/current/userguide/build_environment.html)
* [Spring: Building Java Projects with Gradle](https://spring.io/guides/gs/gradle/)

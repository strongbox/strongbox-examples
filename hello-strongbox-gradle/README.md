This is an example of how to use the Strongbox artifact repository manager with [Gradle](http://gradle.org/).

# Before you start
Make sure that your Strongbox instance is up and running. If you are new to Strongbox, please visit the [Installation](https://github.com/strongbox/strongbox#installation) page first.

# Requirements
You will need the following software installed on your machine to make this example working:
* Java Development Kit (JDK) version 1.8.x or higher
* [Gradle](http://gradle.org/) version 2.11 or higher

# The `gradle.build` file
This file is a build script written in Groovy language. Artifact-related properties could be defined and customized in the following way:
* add `group` property to specify logical prefix where other similar projects reside (like `groupId` in maven pom.xml)
* add `version` to specify artifact version
* customise `repositories` section to add any artifact repositories like Maven Central (included by default)
* customise `dependencies` section to add any dependencies to your project; dependency management could be implemented using `ext` properties object and applying common settings from single separate gradle file, [read more here](https://docs.gradle.org/current/userguide/plugins.html) 
* customise `uploadArchives` to specify artifact-to-repository uploading process, matches Maven `distributionManagement` section

Read more on [gradle scripting here](https://docs.gradle.org/current/userguide/tutorial_using_tasks.html).

# The `settings.gradle` file
Use this file to specify project-specific settings of gradle build as well as artifact name that resides as `rootProject.name` property.

# The `gradle.properties` file
This file should be created manually by every user, or it' perfectly fine to have global settings located in .gradle/gradle.properties. For more details please visit [Gradle Build environment page](https://docs.gradle.org/current/userguide/build_environment.html). Example of properties for this project:

   mavenUser=maven
   mavenPassword=password

# Credentials
Just take a look at the previous section. You could place credentials in the `gradle.properties` in your project root or in the .gradle directory (last will have global impact).

# How to build from source code
When you clone this example project sources, go into that directory and simply type `gradle clean upload`. This will try to deploy artifacts to the Strongbox Repository.

# How to customize target artifact repository during upload
Change `repositories.mavenDeployer.repository` and specify URL and auth credentials of any repo you would like to upload to. Make sure that you don' have SNAPSHOT in your version if you are uploading to the release repo.

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

For the second time when maven-metadata.xml will be placed in the remote (in this case local) repository you will not see the warning:

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
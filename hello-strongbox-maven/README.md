This is an example of how to use the Strongbox artifact repository manager with Maven.

# The `pom.xml` file

This file is used to define your project's information about things like youre dependencies, repositories to use to resolve and deploy artifacts, plugins to use, etc.

The key things each project needs to have are:

* A `groupId` : A logical prefix where other similar projects reside.
* A `artifactId` : The artifact's name.
* A `version`: The version.
* A `packaging` (optional, defaults to `jar`, if not specified): The packaging type (jar, war, etc...)
* A `<repositories/>` section: Defines where to resolve dependencies from.
* A `<distributionManagement/>` section: Defines where to deploy the artifact(s) produced by this build to.

# The `settings.xml` file

This is the Maven configuration file used to define global settings, such as remote repositories, preferred mirrors, proxy settings, repository credentials, etc.

This file normally needs to reside under ~/.m2/ or C:\Users\youruser\.m2

It is important to note that the `<repositories/>` and `<distributionManagement/>` sections in your `pom.xml` file always have an `<id/>`. This `<id/>` needs to match the `<id/>` of a corresponding `<server/>` in your `settings.xml` file.



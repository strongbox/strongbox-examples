This is an example of how to use the Strongbox artifact repository manager with NuGet.

## Pre-requisites
The following is a minimal configuration in order to be able to use this tutorial:

* `Windows`
* `nuget.exe`
* `Visual Studio`

## Create NuGet configuration
Append the -Source option to your NuGet.exe commands to access your storages in Strongbox:
```sh
nuget <command> -Source http://localhost:48080/storages/storage-nuget/nuget-releases
```

### Add your repository to NuGet package sources
To manage packages, you'll need to configure NuGet to acccess your storages by performing following steps:

1. In Visual Studio Go to Tools --> Nuget Package Manager --> Package Manager Settings
2. Select Package Sources  in the navigation menu
3. To add a source, select +, edit the name, enter the URL or path in the Source control, and select Update. The source now appears in the selector drop-down.
4. The strongbox group repository should be at the top of the list.

![NuGet Package Resources](https://github.com/strongbox/strongbox/wiki/resources%2Fimages%2Fnuget%2FNuGet-Package-Resources.png "Add new NuGet Package Resources")

* You can not deploy to group repositories as they are only for resolving artifacts. Also, the group includes the nuget.org repository itself
* The URL when deploying artifacts is the one for the hosted repository (`nuget-releases`, `nuget-snapshot`, etc)



### Get api key to use with your repository

NuGet protocol assumes that users need to be authenticated with `API Key` to be able to deploy or delete your packages.
Strongbox provides the REST API to get an API Key for specified user, you can use a browser for this like follows:
    
    http://localhost:48080/users/user/admin/generate-security-token

Enter your Strongbox credentials. (Default: admin:password)

The output should be like follows:

    eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTdHJvbmdib3giLCJqdGkiOiJtU3N0TGVOMGpabzJNcmdleGdWSUVRIiwic3ViIjoiYWRtaW4iLCJzZWN1cml0eS10b2tlbi1rZXkiOiJhZG1pbi1zZWNyAMQifQ.SgpKb4yUidK8ATbGxDOfjGjHfEF22PIFyzlpk-Rpad0


### Set api key to use with your repository

```sh
nuget setApiKey {apiKey} -Source {repositoryUrl}
```

The output should be like follows:
```sh
nuget.exe setApiKey eyJhbGciOiJIUzI1NiJ9. -Source http://localhost:48080/storages/storage-nuget/nuget-releases

The API Key 'eyJhbGciOiJIUzI1NiJ9.' was saved for 'http://localhost:48080/storages/storage-nuget/nuget-releases'.
```

## How to build

In visual studio create a class library project. 

Add dependencies to your package from other NuGet repositories. In Solution Explorer, right-click either References or a project and select Manage NuGet Packages.

In the browse tab search for log4net and install stable version.

Right-click on the resulting project file and select Build to make sure the project was created properly. The DLL is found within the Debug folder (or Release if you build that configuration instead).

Open a command prompt and navigate to the folder containing your solution(.sln) file
```sh
nuget spec
```

This will create the `Package.nuspec` XML file. The manifest looks something like the code below, where tokens in the form <token> (such as $id$) are be replaced during the packaging process with values from the project's Properties/AssemblyInfo.cs file.

```xml
<?xml version="1.0"?>
<package >
  <metadata>
    <id>Package</id>
    <version>1.0.2</version>
    <authors>gokhan.kuyucak</authors>
    <owners>gokhan.kuyucak</owners>
    <licenseUrl>http://LICENSE_URL_HERE_OR_DELETE_THIS_LINE</licenseUrl>
    <projectUrl>http://PROJECT_URL_HERE_OR_DELETE_THIS_LINE</projectUrl>
    <iconUrl>http://ICON_URL_HERE_OR_DELETE_THIS_LINE</iconUrl>
    <requireLicenseAcceptance>false</requireLicenseAcceptance>
    <description>An example to generate package and publish with strongbox</description>
    <releaseNotes>Summary of changes made in this release of the package.</releaseNotes>
    <copyright>Copyright 2018</copyright>
    <tags>Tag1 Tag2</tags>
    <dependencies>
      <dependency id="SampleDependency" version="1.0" />
    </dependencies>
  </metadata>
</package>

```

You can edit the `Package.nuspec` file in text editor. Remember that `authors` and `description` entities are mandatory.

## How to make NuGet package

Execute the following command:
```sh
nuget pack ./hello-strongbox-nuget-visual-studio/hello-strongbox-nuget-visual-studio.csproj
```

The output should be like follows:
```log
Attempting to build package from 'hello-strongbox-nuget-visual-studio.csproj'.
MSBuild auto-detection: using msbuild version '15.5.180.51428' from 'C:\Program Files (x86)\Microsoft Visual Studio\2017\Community\MSBuild\15.0\bin'.
Packing files from 'hello-strongbox-nuget-visual-studio\hello-strongbox-nuget-visual-studio\bin\Release'.
WARNING: Description was not specified. Using 'Description'.
WARNING: Author was not specified. Using 'gokhan.kuyucak'.
Successfully created package 'hello-strongbox-nuget-visual-studio.1.0.0.nupkg'.
```

## How to push NuGet package into Strongbox repository

Execute the following command:
```sh
nuget push hello-strongbox-nuget-visual-studio.1.0.0.nupkg -Source http://localhost:48080/storages/storage-nuget/nuget-releases
```

The output should be like follows:
```log
Pushing hello-strongbox-nuget-visual-studio.1.0.0.nupkg to 'http://localhost:48080/storages/storage-nuget/nuget-releases'...
PUT http://localhost:48080/storages/storage-nuget/nuget-releases
Created http://localhost:48080/storages/storage-nuget/nuget-releases 3153ms
Your package was pushed.
```
## How to search for NuGet packages in Strongbox repositories

To list the current versions for your packages, execute the following command:
```sh
nuget.exe list -Source http://localhost:48080/storages/storage-nuget/nuget-releases
```

The output should be as follows:
```log
HelloNuget 1.0.2
NoSleep 1.0.0
NugetWorld.Hello 1.0.0
hello-strongbox-nuget-visual-studio 1.0.0
```

To search a keyword in repository, execute the following command:
```sh
nuget.exe list strongbox -Source http://localhost:48080/storages/storage-nuget/nuget-releases
```

The output should be like follows:
```log
hello-strongbox-nuget-visual-studio 1.0.0
```

For example, you can look up log4net like this:
![Search log4net](https://github.com/strongbox/strongbox/wiki/resources%2Fimages%2Fnuget%2FNuGet-Package-log4net.png "Search result with Strongbox NuGet Layout provider")

## How to delete a NuGet package

Execute the following command:
```sh
nuget.exe delete NugetWorld.Hello 1.0.0 -Source http://localhost:48080/storages/storage-nuget/nuget-releases
```
The output should be like follows:
```log
NugetWorld.Hello 1.0.0 will be deleted from the 'http://localhost:48080/storages/storage-nuget/nuget-releases'. Would you like to continue? (y/N) y
WARNING: Deleting NugetWorld.Hello 1.0.0 from the 'http://localhost:48080/storages/storage-nuget/nuget-releases'.
  DELETE http://localhost:48080/storages/storage-nuget/nuget-releases/NugetWorld.Hello/1.0.0
  OK http://localhost:48080/storages/storage-nuget/nuget-releases/NugetWorld.Hello/1.0.0 536ms
NugetWorld.Hello 1.0.0 was deleted successfully.
```

## How to install a NuGet package with Visual Studio Nuget Manager
Open your project in Visual Studio and in Solution Explorer, right-click either References or a project and select Manage NuGet Packages.

![Manage NuGet Packages](https://github.com/strongbox/strongbox/wiki/resources%2Fimages%2Fnuget%2FNuGet-Manage-Packages.png "Manage NuGet Packages")

You should switch to StrongBox repository in source tab and search for the package that you built in previous steps. 

![Switch to Strongbox Repo](https://github.com/strongbox/strongbox/wiki/resources%2Fimages%2Fnuget%2FNuGet-Switch-Repository.png "Switch to Strongbox Repo")



Search for the NuGet package, select and install the version that is available.

![Install NuGet Package](https://github.com/strongbox/strongbox/wiki/resources%2Fimages%2Fnuget%2FNuGet-Search-Repository.png "Install NuGet Package")


You can check that all of the dependencies are installed that you packed in [How to make NuGet package](#How-to-make-NuGet-package) section.


## How to install a NuGet package with Package Manager Console
The NuGet Package Manager Console is built into Visual Studio. To find and install a package, you should execute following commands:

* Open the project/solution in Visual Studio, and open the console using the Tools > NuGet Package Manager > Package Manager Console command.
* Switch to Strongbox repository in Package Source tab.
* To find a package execute
```sh
Find-Package hello-strongbox-nuget-visual-studio
```

The output should be like follows:
```log
| Id                                  | Versions | Description |
|-------------------------------------|----------|-------------|
| hello-strongbox-nuget-visual-studio | {1.0.0}  | Description |
|                                     |          |             |                                       
Time Elapsed: 00:00:01.3270740

```

* To install the package execute 
```sh
Install-Package hello-strongbox-nuget-visual-studio
```
The output should be like follows:

```log
Attempting to gather dependency information for package 'hello-strongbox-nuget-visual-studio.1.0.0' with respect to project 'hello-strongbox-nuget-visual-studio', targeting '.NETFramework,Version=v4.6.1'
Gathering dependency information took 614,18 ms
Attempting to resolve dependencies for package 'hello-strongbox-nuget-visual-studio.1.0.0' with DependencyBehavior 'Lowest'
Resolving dependency information took 0 ms
Resolving actions to install package 'hello-strongbox-nuget-visual-studio.1.0.0'
Resolved actions to install package 'hello-strongbox-nuget-visual-studio.1.0.0'
Retrieving package 'hello-strongbox-nuget-visual-studio 1.0.0' from 'Strongbox Packages'.
  GET http://localhost:48080/storages/storage-nuget/nuget-releases/download/hello-strongbox-nuget-visual-studio/1.0.0
  OK http://localhost:48080/storages/storage-nuget/nuget-releases/download/hello-strongbox-nuget-visual-studio/1.0.0 195ms
Installing hello-strongbox-nuget-visual-studio 1.0.0.
Adding package 'hello-strongbox-nuget-visual-studio.1.0.0' to folder 'hello-strongbox-nuget-visual-studio\packages'
Added package 'hello-strongbox-nuget-visual-studio.1.0.0' to folder 'hello-strongbox-nuget-visual-studio\packages'
Added package 'hello-strongbox-nuget-visual-studio.1.0.0' to 'packages.config'
Successfully installed 'hello-strongbox-nuget-visual-studio 1.0.0' to hello-strongbox-nuget-visual-studio
Executing nuget actions took 837,38 ms
Time Elapsed: 00:00:02.4972295
```
![Install with Package Manager Console](https://github.com/strongbox/strongbox/wiki/resources%2Fimages%2Fnuget%2FNuGet-Package-Manager-Console.png "Install with Package Manager Console")


# See also:
* [Strongbox NuGet Mono Example](https://github.com/strongbox/strongbox-examples/tree/master/hello-strongbox-nuget-mono)
* [NuGet Package Manager UI](https://docs.microsoft.com/en-us/nuget/tools/package-manager-ui)
* [NuGet CLI Reference](https://docs.microsoft.com/en-us/nuget/tools/nuget-exe-cli-reference)
* [Package Manager Console](https://docs.microsoft.com/en-us/nuget/tools/package-manager-console)
* [Nuget: Dependency Resolution In Nuget 3.x](https://docs.microsoft.com/en-us/nuget/consume-packages/dependency-resolution#dependency-resolution-in-nuget-3x)

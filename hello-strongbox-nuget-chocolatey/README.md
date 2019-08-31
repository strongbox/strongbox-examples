This is an example of how to use the Strongbox artifact repository manager with Chocolatey using Nuget.

## Pre-requisites
The following is a minimal configuration in order to be able to use this tutorial:

* `Chocolatey`

Chocolatey is available natively on Windows, running on Mono, or in Docker. Currently, for both Mono and Docker, only source code is available, see https://github.com/chocolatey/choco for build instructions. Additionally, for the short term, you may want to build using https://github.com/chocolatey/choco/pull/1827 until it is merged into master as master uses a old version of Mono.

It is OK to ignore warnings about a system reboot being requested since the nothing here is affected by it.

### Get api key to use with your repository

The NuGet protocol assumes that users need to be authenticated with `API Key` to be able to deploy or delete your packages.
Strongbox provides the REST API to get an API Key for specified user, you can use a browser for this like follows:
    
    http://localhost:48080/api/users/admin/generate-security-token

Enter your Strongbox credentials. (Default: admin:password)

The output should be like follows:

    eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTdHJvbmdib3giLCJqdGkiOiJtU3N0TGVOMGpabzJNcmdleGdWSUVRIiwic3ViIjoiYWRtaW4iLCJzZWN1cml0eS10b2tlbi1rZXkiOiJhZG1pbi1zZWNyAMQifQ.SgpKb4yUidK8ATbGxDOfjGjHfEF22PIFyzlpk-Rpad0


### Set api key to use with your repository

```sh
choco apikey -k {apiKey} -s {repositoryUrl}
```

The output should be like follows:
```log
Chocolatey <version>
2 validations performed. 1 success(es), 0 warning(s), and 0 error(s).

Updated ApiKey for http://localhost:48080/storages/storage-nuget/nuget-releases
```

* This needs to be run in an administrative command prompt or powershell

### Add your repository to Chocolatey package sources
To manage packages, you'll need to configure Chocolatey to access your storages by running the following command:

```sh
choco source add -n=strongbox -u={username} -p={password} -s "{repositoryUrl}" --priority=1
``` 

The output should be like follows:
```log
Chocolatey v0.10.13
2 validations performed. 1 success(es), 0 warning(s), and 0 error(s).

Added strongbox - http://localhost:48080/storages/storage-nuget/nuget-releases (Priority 1)
```

* This needs to be run in an administrative command prompt or powershell
* You cannot deploy to group repositories, as they are only for resolving artifacts. Also, please note that the `nuget-public` group includes the `nuget.org` repository itself
* The URL when deploying artifacts is the one for the hosted repository (`nuget-releases`, `nuget-snapshot`, etc)


## How to create the package source

Open a command prompt in the folder you want to create the package in.

1. Run the following command:
```sh
choco new -name=hello-chocolatey -version=1.0.0
```

The output should be like
```log
Chocolatey <version>
2 validations performed. 1 success(es), 0 warning(s), and 0 error(s).

Creating a new package specification at C:\Users\User\Documents\hello-chocolatey
Generating template to a file
 at 'C:\Users\User\Documents\hello-chocolatey\hello-chocolatey.nuspec'
Generating template to a file
 at 'C:\Users\User\Documents\hello-chocolatey\tools\chocolateyinstall.ps1'
Generating template to a file
 at 'C:\Users\User\Documents\hello-chocolatey\tools\chocolateybeforemodify.ps1'
Generating template to a file
 at 'C:\Users\User\Documents\hello-chocolatey\tools\chocolateyuninstall.ps1'
Generating template to a file
 at 'C:\Users\User\Documents\hello-chocolatey\tools\LICENSE.txt'
Generating template to a file
 at 'C:\Users\User\Documents\hello-chocolatey\tools\VERIFICATION.txt'
Generating template to a file
 at 'C:\Users\User\Documents\hello-chocolatey\ReadMe.md'
Generating template to a file
 at 'C:\Users\User\Documents\hello-chocolatey\_TODO.txt'
Successfully generated hello-chocolatey package specification files
 at 'C:\Users\User\Documents\hello-chocolatey'
```

It will create a folder named `hello-chocolatey` with some files including a `.nuspec` and a folder called tools.

2. Delete `_TODO.txt` and `ReadMe.md` in the hello-chocolatey directory and also  

3. Delete `chocolateybeforemodify.ps1` and `chocolateyuninstall.ps1` in the tools sub-directory

3. Edit `chocolateyinstall.ps1` in the tools sub-directory with a text editor

4. Remove all text in the file and replace with `write-host Package would install here`


## How to make Chocolatey NuGet package

Execute the following command in the same directory as hello-chocolatey.nuspec:
```sh
choco pack
```

The output should be like follows:
```log
Chocolatey <version>
2 validations performed. 1 success(es), 0 warning(s), and 0 error(s).

Attempting to build package from 'hello-chocolatey.nuspec'.
Successfully created package 'C:\Users\User\Documents\hello-chocolatey\hello-chocolatey.1.0.0.nupkg'
```

## How to push NuGet package into Strongbox repository

Execute the following command in the directory with hello-chocolatey.1.0.0.nupkg:
```sh
choco push --source {repositoryUrl} --force
```

The output should be like follows:
```log
Chocolatey <version>
2 validations performed. 1 success(es), 0 warning(s), and 0 error(s).

Attempting to push hello-chocolatey.1.0.0.nupkg to http://localhost:48080/storages/storage-nuget/nuget-releases
hello-chocolatey 1.0.0 was pushed successfully to http://localhost:48080/storages/storage-nuget/nuget-releases
```

## How to search for Chocolatey packages in Strongbox repositories

Execute the following command:
```sh
choco search -s {repositoryUrl}
```

The output should be like follows:
```log
Chocolatey <version>
2 validations performed. 1 success(es), 0 warning(s), and 0 error(s).

hello-chocolatey 1.0.0
1 packages found.
```
## How to delete a Chocolatey package from strongbox

Chocolatey does not have a built in way to delete packages from the server.

Either
1. Delete packages server-side
2. Use nuget.exe to delete packages, see hello-strongbox-nuget-visual-studio for details


## How to install a Chocolatey package

Execute the following command as a administrator:
```sh
choco install hello-chocolatey
```

The output should be like follows:

```log
Chocolatey <version>
2 validations performed. 1 success(es), 0 warning(s), and 0 error(s).

Installing the following packages:
hello-chocolatey.1.0.0.nupkg
By installing you accept licenses for the packages.

hello-chocolatey v1.0.0
hello-chocolatey package files install completed. Performing other installation steps.
Package would install here
 The install of hello-chocolatey was successful.
  Software install location not explicitly set, could be in package or
  default install location if installer.

Chocolatey installed 1/1 packages.
 See the log for details (C:\ProgramData\chocolatey\logs\chocolatey.log).
```

## How to uninstall a Chocolatey package

Execute the following command as a administrator:
```sh
choco uninstall hello-chocolatey
```

The output should be like follows:
```log
Chocolatey <version>
2 validations performed. 1 success(es), 0 warning(s), and 0 error(s).

Uninstalling the following packages:
hello-chocolatey

hello-chocolatey v1.0.0
 Skipping auto uninstaller - No registry snapshot.
 hello-chocolatey has been successfully uninstalled.

Chocolatey uninstalled 1/1 packages.
 See the log for details (C:\ProgramData\chocolatey\logs\chocolatey.log).
```

# See also:

* [Chocolatey commands reference](https://chocolatey.org/docs/commands-reference)
* [Chocolatey docs](https://chocolatey.org/docs)
* [Install Chocolatey](https://chocolatey.org/docs/installation#installing-chocolatey)

This is an example of how to use the Strongbox artifact repository manager with NuGet.

## Pre-requisites
The following is a minimal configuration in order to be able to use this tutorial:

* `Linux` OS
* `Mono` cli environment
* `nuget.exe` and `Microsoft.Build.dll` files

## Prepare project workspace

Go to the root project folder and execute the following commands:

    $ mkdir -p ./bin
    $ mkdir -p ./.nuget
    $ echo '<?xml version="1.0" encoding="utf-8"?><configuration></configuration>' > ./.nuget/NuGet.config

*There should be no output if success*
> Note that `bin` and `.nuget` folders must be added to ignore list of your `VCS`

## Create NuGet configuration

### Add default push repository URL

    $ mono --runtime=v4.0 nuget.exe config -set DefaultPushSource={repositoryUrl} -ConfigFile ./.nuget/NuGet.config

Example below (*there should be no output if success*):
    
    carlspring@linux-70e2:/home/carlspring/strongbox-examples/hello-strongbox-nuget-mono> mono --runtime=v4.0 nuget.exe config -set DefaultPushSource=http://localhost:8080/nuget/storages/MySource -ConfigFile ./.nuget/NuGet.config

### Set api key to use with your repository

    $ mono --runtime=v4.0 nuget.exe setApiKey {apiKey} -Source {repositoryUrl} -ConfigFile ./.nuget/NuGet.config

The output should be like follows:

    carlspring@linux-70e2:/home/carlspring/strongbox-examples/hello-strongbox-nuget-mono> mono --runtime=v4.0 nuget.exe setApiKey bXktYXBpLWtleQ== -Source http://localhost:8080/nuget/storages/MySource -ConfigFile ./.nuget/NuGet.config
    The API Key 'bXktYXBpLWtleQ==' was saved for 'http://localhost:8080/nuget/storages/MySource'.


## How to build

Execute the following command:

     $ mcs -t:library -out:./bin/HelloWorld.dll ./src/HelloWorld.cs

This will build the code and make `dll` library.

## How to make NuGet package

Execute the following command:

    $ mono --runtime=v4.0 nuget.exe pack ./Hello.Strongbox.Nuget.Mono.nuspec
    
The output should be like follows:

    carlspring@linux-70e2:/home/carlspring/strongbox-examples/hello-strongbox-nuget-mono> mono --runtime=v4.0 nuget.exe pack ./Hello.Strongbox.Nuget.Mono.nuspec 
    Attempting to build package from 'Hello.Strongbox.Nuget.Mono.nuspec'.
    Successfully created package '/home/carlspring/strongbox-examples/hello-strongbox-nuget-mono/Org.Carlspring.Strongbox.Examples.Nuget.Mono.1.0.nupkg'.

## How to push NuGet package into Strongbox repository

Execute the following command:
    
    mono --runtime=v4.0 nuget.exe push ./Org.Carlspring.Strongbox.Examples.Nuget.Mono.1.0.nupkg -ConfigFile ./.nuget/NuGet.config

The output should be like follows:

    carlspring@linux-70e2:/home/carlspring/strongbox-examples/hello-strongbox-nuget-mono> mono --runtime=v4.0 nuget.exe push ./Org.Carlspring.Strongbox.Examples.Nuget.Mono.1.0.nupkg -ConfigFile ./.nuget/NuGet.config
    Pushing Org.Carlspring.Strongbox.Examples.Nuget.Mono 1.0 to 'http://localhost:8080/nuget/storages/MySource'...
    Your package was pushed.

# See also:
* [Install Mono on Linux](http://www.mono-project.com/docs/getting-started/install/linux/)
* [NuGet CLI Reference](https://docs.nuget.org/ndocs/tools/nuget.exe-cli-reference)
* [Running NuGet command-line on Linux](http://headsigned.com/article/running-nuget-command-line-on-linux)
* [Nuget: Dependency Resolution In Nuget 3.x](https://docs.microsoft.com/en-us/nuget/consume-packages/dependency-resolution#dependency-resolution-in-nuget-3x)

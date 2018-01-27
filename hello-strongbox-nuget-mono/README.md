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
    
    carlspring@linux-70e2:/home/carlspring/strongbox-examples/hello-strongbox-nuget-mono> mono --runtime=v4.0 nuget.exe config -set DefaultPushSource=http://localhost:48080/storages/storage-nuget/nuget-releases -ConfigFile ./.nuget/NuGet.config

### Get api key to use with your repository

NuGet protocol assumes that users need to be authenticated with `API Key` to be able to deploy or delete your packages.
Strongbox provides the REST API to get an API Key for specified user, you can use `curl` for this like follows:
    
    $ curl -X GET --user admin:password http://localhost:48080/users/user/admin/generate-security-token
    eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJTdHJvbmdib3giLCJqdGkiOiJCdU85OU8xV2VzQ1NkYWcyT3k0eHh3Iiwic3ViIjoiYWRtaW4iLCJzZWN1cml0eS10b2tlbi1rZXkiOiJhZG1pbi1zZWNyZXQifQ.Yzq5zYlDZVCVSxRmSgclRCHW_KojZw-iFGfkWWnTTEw


### Set api key to use with your repository

    $ mono --runtime=v4.0 nuget.exe setApiKey {apiKey} -Source {repositoryUrl} -ConfigFile ./.nuget/NuGet.config

The output should be like follows:

    carlspring@linux-70e2:/home/carlspring/strongbox-examples/hello-strongbox-nuget-mono> mono --runtime=v4.0 nuget.exe setApiKey bXktYXBpLWtleQ== -Source http://localhost:48080/storages/storage-nuget/nuget-releases -ConfigFile ./.nuget/NuGet.config
    The API Key 'bXktYXBpLWtleQ==' was saved for 'http://localhost:48080/storages/storage-nuget/nuget-releases'.

### Provide storage authentication (if needed)

Strongbox is using HTTP Basic Authentication to access storages by default, so you need to configure your credentials in the `<packageSourceCredentials>` section of your `NuGet.config` file.

Finally, your `NuGet.config` file should look like follows:

    $ cat .nuget/NuGet.config 
    <?xml version="1.0" encoding="utf-8"?>
    <configuration>
      <config>
        <add key="DefaultPushSource" value="http://localhost:48080/storages/storage-nuget/nuget-releases" />
      </config>
      <apikeys>
        <add key="http://localhost:48080/storages/nuget-common-storage/nuget-releases" value="YpDSPr0yOqTjEPuaG6+aTOV6QJWI0X4MliV/yARLTZXb4cb55LaZF8jOhWvg+Zqnkn8ykhHtj3byEwKL60GWbsaeZZJdPHeP4OgFftPSmGkJSovyMRh1bbATPi6hx6eRpquP8daWKhfAvca0RjnPA22s3KtcdDlI3dV6IQzTLOfANkdmyhH95A+LHc51BXQKVWQPJ6B94TEBonEqWIt2bNti66Pd4sbDvKZJAA1GRjDprFxukg4EUz8YD++JYWP6X+BNCu2jYNXBS6tbw6Zx1o9HwOd/9eUC+1lP9Sbvj4tGSB/D5MwKhNabKwElhjikDNg5TaI4Il6R3sw9zJXyDdsGIfpKg4ICwBt6suuqEOQZQIWJKum3NuFYOocke6BsHpHC2Iz/hMkCjQz3v8DNaKLU+9pr6qOOaEsfyJCkj313AWxigkHqKcFMlJPfhGcUjZX6wq1vmPMO2erYBiE89IFCdAadBWpB2J6s79YoWwb5Elvf7SiLlU6lDEq9D8mOQLTeWrEkoD3S9h/CiV2qug==" />
      </apikeys>
      <packageSources>
        <add key="strongbox" value="http://localhost:48080/storages/storage-nuget/nuget-releases" />
      </packageSources>
      <packageSourceCredentials>
        <strongbox>
            <add key="Username" value="admin" />
            <add key="ClearTextPassword" value="password" />
        </strongbox>
      </packageSourceCredentials>
    </configuration>

## How to build

Execute the following command:

     $ mcs -t:library -out:./bin/HelloWorld.dll ./src/HelloWorld.cs

This will build the code and make a `dll` library.

## How to make NuGet package

Execute the following command:

    $ mono --runtime=v4.0 nuget.exe pack ./Hello.Strongbox.Nuget.Mono.nuspec
    
The output should be like follows:

    carlspring@linux-70e2:/home/carlspring/strongbox-examples/hello-strongbox-nuget-mono> mono --runtime=v4.0 nuget.exe pack ./Hello.Strongbox.Nuget.Mono.nuspec 
    Attempting to build package from 'Hello.Strongbox.Nuget.Mono.nuspec'.
    Successfully created package '/home/carlspring/strongbox-examples/hello-strongbox-nuget-mono/Org.Carlspring.Strongbox.Examples.Nuget.Mono.1.0.nupkg'.

## How to push NuGet package into Strongbox repository

Execute the following command:
    
    $ mono --runtime=v4.0 nuget.exe push ./Org.Carlspring.Strongbox.Examples.Nuget.Mono.1.0.nupkg -ConfigFile ./.nuget/NuGet.config

The output should be like follows:

    carlspring@linux-70e2:/home/carlspring/strongbox-examples/hello-strongbox-nuget-mono> mono --runtime=v4.0 nuget.exe push ./Org.Carlspring.Strongbox.Examples.Nuget.Mono.1.0.nupkg -ConfigFile ./.nuget/NuGet.config
    Pushing Org.Carlspring.Strongbox.Examples.Nuget.Mono 1.0 to 'http://localhost:48080/storages/storage-nuget/nuget-releases'...
    Your package was pushed.

## How to search for NuGet packages in Strongbox repositories

Execute the following command:
    
    $ mono --runtime=v4.0 nuget.exe list Org.Carlspring -ConfigFile ./.nuget/NuGet.config

The output should be like follows:

    carlspring@linux-70e2:/home/carlspring/strongbox-examples/hello-strongbox-nuget-mono> mono --runtime=v4.0 nuget.exe list Org.Carlspring  -ConfigFile ./.nuget/NuGet.config
    Org.Carlspring.Strongbox.Examples.Nuget.Mono 1.0.0

## How to delete a NuGet package

Execute the following command:
    
    $ mono --runtime=v4.0 nuget.exe delete Org.Carlspring.Strongbox.Examples.Nuget.Mono 1.0.1 -Source strongbox -ConfigFile ./.nuget/NuGet.config

The output should be like follows:

    carlspring@linux-70e2:/home/carlspring/strongbox-examples/hello-strongbox-nuget-mono> mono --runtime=v4.0 nuget-4_1_0.exe delete Org.Carlspring.Strongbox.Examples.Nuget.Mono 1.0.1 -Source strongbox -ConfigFile ./.nuget/NuGet.config
    Org.Carlspring.Strongbox.Examples.Nuget.Mono 1.0.1 will be deleted from the 'http://localhost:48080/storages/storage-nuget/nuget-releases'. Would you like to continue? (y/N) y
    WARNING: Deleting Org.Carlspring.Strongbox.Examples.Nuget.Mono 1.0.1 from the 'http://localhost:48080/storages/storage-nuget/nuget-releases'.
    DELETE http://localhost:48080/storages/storage-nuget/nuget-releases/Org.Carlspring.Strongbox.Examples.Nuget.Mono/1.0.1
    OK http://localhost:48080/storages/storage-nuget/nuget-releases/Org.Carlspring.Strongbox.Examples.Nuget.Mono/1.0.1 7277ms
    Org.Carlspring.Strongbox.Examples.Nuget.Mono 1.0.1 was deleted successfully.

# See also:
* [Install Mono on Linux](http://www.mono-project.com/docs/getting-started/install/linux/)
* [NuGet CLI Reference](https://docs.nuget.org/ndocs/tools/nuget.exe-cli-reference)
* [Running NuGet command-line on Linux](http://headsigned.com/article/running-nuget-command-line-on-linux)
* [Nuget: Dependency Resolution In Nuget 3.x](https://docs.microsoft.com/en-us/nuget/consume-packages/dependency-resolution#dependency-resolution-in-nuget-3x)

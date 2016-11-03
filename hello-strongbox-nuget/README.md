This is an example of how to use the Strongbox artifact repository manager with NuGet.

# How to build

Execute the following command:

     mcs -t:library -out:./bin/HelloWorld.dll ./src/HelloWorld.cs

This will build the code and make `dll` library.

# How to make NuGet package

Execute the following command:

    mono --runtime=v4.0.30319 nuget.exe pack ./Mono.HelloWorld.nuspec
    
The output should be like follows:

    carlspring@linux-70e2:/home/carlspring/strongbox-examples/hello-strongbox-nuget> mono --runtime=v4.0.30319 nuget.exe pack ./Mono.HelloWorld.nuspec 
    Attempting to build package from 'Mono.HelloWorld.nuspec'.
    Successfully created package '/home/carlspring/strongbox-examples/hello-strongbox-nuget/Strongbox.Nuget.HelloWorld.1.0.nupkg'.

# How to push NuGet package into Carlspring repository

Execute the following command:
    
    mono --runtime=v4.0.30319 nuget.exe push ./Strongbox.Nuget.HelloWorld.1.0.nupkg -s {repositoryUrl} {apiKey}

The output should be like follows:

    carlspring@linux-70e2:/home/carlspring/strongbox-examples/hello-strongbox-nuget> mono --runtime=v4.0.30319 nuget.exe push ./Strongbox.Nuget.HelloWorld.1.0.nupkg -s http://localhost:8080/nuget/storages/MySource bXktYXBpLWtleQ==
    Pushing Strongbox.Nuget.HelloWorld 1.0 to 'http://localhost:8080/nuget/storages/MySource'...
    Your package was pushed.



To create the package:
```
carlspring@carlspring:/java/opensource/carlspring/strongbox-examples/hello-strongbox-pip> python3 setup.py sdist bdist_wheel
running sdist
running egg_info
creating hello_strongbox_pip.egg-info
writing hello_strongbox_pip.egg-info/PKG-INFO
writing dependency_links to hello_strongbox_pip.egg-info/dependency_links.txt
writing top-level names to hello_strongbox_pip.egg-info/top_level.txt
writing manifest file 'hello_strongbox_pip.egg-info/SOURCES.txt'
reading manifest file 'hello_strongbox_pip.egg-info/SOURCES.txt'
writing manifest file 'hello_strongbox_pip.egg-info/SOURCES.txt'
running check
creating hello-strongbox-pip-1.0
creating hello-strongbox-pip-1.0/hello-strongbox-pip
creating hello-strongbox-pip-1.0/hello_strongbox_pip.egg-info
copying files to hello-strongbox-pip-1.0...
copying README.md -> hello-strongbox-pip-1.0
copying setup.py -> hello-strongbox-pip-1.0
copying hello-strongbox-pip/__init__.py -> hello-strongbox-pip-1.0/hello-strongbox-pip
copying hello-strongbox-pip/hello-strongbox-pip.py -> hello-strongbox-pip-1.0/hello-strongbox-pip
copying hello_strongbox_pip.egg-info/PKG-INFO -> hello-strongbox-pip-1.0/hello_strongbox_pip.egg-info
copying hello_strongbox_pip.egg-info/SOURCES.txt -> hello-strongbox-pip-1.0/hello_strongbox_pip.egg-info
copying hello_strongbox_pip.egg-info/dependency_links.txt -> hello-strongbox-pip-1.0/hello_strongbox_pip.egg-info
copying hello_strongbox_pip.egg-info/top_level.txt -> hello-strongbox-pip-1.0/hello_strongbox_pip.egg-info
Writing hello-strongbox-pip-1.0/setup.cfg
creating dist
Creating tar archive
removing 'hello-strongbox-pip-1.0' (and everything under it)
running bdist_wheel
running build
running build_py
creating build
creating build/lib
creating build/lib/hello-strongbox-pip
copying hello-strongbox-pip/__init__.py -> build/lib/hello-strongbox-pip
copying hello-strongbox-pip/hello-strongbox-pip.py -> build/lib/hello-strongbox-pip
installing to build/bdist.linux-x86_64/wheel
running install
running install_lib
creating build/bdist.linux-x86_64
creating build/bdist.linux-x86_64/wheel
creating build/bdist.linux-x86_64/wheel/hello-strongbox-pip
copying build/lib/hello-strongbox-pip/__init__.py -> build/bdist.linux-x86_64/wheel/hello-strongbox-pip
copying build/lib/hello-strongbox-pip/hello-strongbox-pip.py -> build/bdist.linux-x86_64/wheel/hello-strongbox-pip
running install_egg_info
Copying hello_strongbox_pip.egg-info to build/bdist.linux-x86_64/wheel/hello_strongbox_pip-1.0-py3.6.egg-info
running install_scripts
creating build/bdist.linux-x86_64/wheel/hello_strongbox_pip-1.0.dist-info/WHEEL
creating 'dist/hello_strongbox_pip-1.0-py3-none-any.whl' and adding 'build/bdist.linux-x86_64/wheel' to it
adding 'hello-strongbox-pip/__init__.py'
adding 'hello-strongbox-pip/hello-strongbox-pip.py'
adding 'hello_strongbox_pip-1.0.dist-info/METADATA'
adding 'hello_strongbox_pip-1.0.dist-info/WHEEL'
adding 'hello_strongbox_pip-1.0.dist-info/top_level.txt'
adding 'hello_strongbox_pip-1.0.dist-info/RECORD'
removing build/bdist.linux-x86_64/wheel

```

To upload:
```

carlspring@carlspring:/java/opensource/carlspring/strongbox-examples/hello-strongbox-pip> python setup.py sdist upload -r hello-strongbox-pip
running sdist
running egg_info
writing hello_strongbox_pip.egg-info/PKG-INFO
writing top-level names to hello_strongbox_pip.egg-info/top_level.txt
writing dependency_links to hello_strongbox_pip.egg-info/dependency_links.txt
reading manifest file 'hello_strongbox_pip.egg-info/SOURCES.txt'
writing manifest file 'hello_strongbox_pip.egg-info/SOURCES.txt'
running check
creating hello-strongbox-pip-1.0
creating hello-strongbox-pip-1.0/hello-strongbox-pip
creating hello-strongbox-pip-1.0/hello_strongbox_pip.egg-info
copying files to hello-strongbox-pip-1.0...
copying README.md -> hello-strongbox-pip-1.0
copying setup.py -> hello-strongbox-pip-1.0
copying hello-strongbox-pip/__init__.py -> hello-strongbox-pip-1.0/hello-strongbox-pip
copying hello-strongbox-pip/hello-strongbox-pip.py -> hello-strongbox-pip-1.0/hello-strongbox-pip
copying hello_strongbox_pip.egg-info/PKG-INFO -> hello-strongbox-pip-1.0/hello_strongbox_pip.egg-info
copying hello_strongbox_pip.egg-info/SOURCES.txt -> hello-strongbox-pip-1.0/hello_strongbox_pip.egg-info
copying hello_strongbox_pip.egg-info/dependency_links.txt -> hello-strongbox-pip-1.0/hello_strongbox_pip.egg-info
copying hello_strongbox_pip.egg-info/top_level.txt -> hello-strongbox-pip-1.0/hello_strongbox_pip.egg-info
Writing hello-strongbox-pip-1.0/setup.cfg
Creating tar archive
removing 'hello-strongbox-pip-1.0' (and everything under it)
running upload
Submitting dist/hello-strongbox-pip-1.0.tar.gz to http://localhost:48080/storages/storage-pypi/pypi-releases/
Server response (200): OK
```

// TODO: Add a section on how to configure things
// TODO: Add the output of a successful install

To install:
```
pip install http://localhost:48080/storages/storage-pypi/pypi-releases/#egg=HelloWorld
```


# Useful Link

* [Python: Packaging Projects](https://packaging.python.org/tutorials/packaging-projects/)

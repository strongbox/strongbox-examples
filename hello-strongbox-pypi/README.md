This is an example project illustrating how to use the Strongbox artifact repository manager with pip.


   python3 setup.py sdist bdist_wheel
running sdist
running egg_info
creating hello_world_pypi.egg-info
writing hello_world_pypi.egg-info/PKG-INFO
writing dependency_links to hello_world_pypi.egg-info/dependency_links.txt
writing requirements to hello_world_pypi.egg-info/requires.txt
writing top-level names to hello_world_pypi.egg-info/top_level.txt
writing manifest file 'hello_world_pypi.egg-info/SOURCES.txt'
reading manifest file 'hello_world_pypi.egg-info/SOURCES.txt'
writing manifest file 'hello_world_pypi.egg-info/SOURCES.txt'
running check
creating hello_world_pypi-1.0
creating hello_world_pypi-1.0/hello_world_pypi
creating hello_world_pypi-1.0/hello_world_pypi.egg-info
copying files to hello_world_pypi-1.0...
copying README.md -> hello_world_pypi-1.0
copying setup.cfg -> hello_world_pypi-1.0
copying setup.py -> hello_world_pypi-1.0
copying hello_world_pypi/__init__.py -> hello_world_pypi-1.0/hello_world_pypi
copying hello_world_pypi/helloworld.py -> hello_world_pypi-1.0/hello_world_pypi
copying hello_world_pypi.egg-info/PKG-INFO -> hello_world_pypi-1.0/hello_world_pypi.egg-info
copying hello_world_pypi.egg-info/SOURCES.txt -> hello_world_pypi-1.0/hello_world_pypi.egg-info
copying hello_world_pypi.egg-info/dependency_links.txt -> hello_world_pypi-1.0/hello_world_pypi.egg-info
copying hello_world_pypi.egg-info/requires.txt -> hello_world_pypi-1.0/hello_world_pypi.egg-info
copying hello_world_pypi.egg-info/top_level.txt -> hello_world_pypi-1.0/hello_world_pypi.egg-info
Writing hello_world_pypi-1.0/setup.cfg
creating dist
Creating tar archive
removing 'hello_world_pypi-1.0' (and everything under it)
running bdist_wheel
running build
running build_py
creating build
creating build/lib
creating build/lib/hello_world_pypi
copying hello_world_pypi/__init__.py -> build/lib/hello_world_pypi
copying hello_world_pypi/helloworld.py -> build/lib/hello_world_pypi
installing to build/bdist.macosx-10.12-x86_64/wheel
running install
running install_lib
creating build/bdist.macosx-10.12-x86_64
creating build/bdist.macosx-10.12-x86_64/wheel
creating build/bdist.macosx-10.12-x86_64/wheel/hello_world_pypi
copying build/lib/hello_world_pypi/__init__.py -> build/bdist.macosx-10.12-x86_64/wheel/hello_world_pypi
copying build/lib/hello_world_pypi/helloworld.py -> build/bdist.macosx-10.12-x86_64/wheel/hello_world_pypi
running install_egg_info
Copying hello_world_pypi.egg-info to build/bdist.macosx-10.12-x86_64/wheel/hello_world_pypi-1.0-py3.7.egg-info
running install_scripts
adding license file "LICENSE" (matched pattern "LICEN[CS]E*")
creating build/bdist.macosx-10.12-x86_64/wheel/hello_world_pypi-1.0.dist-info/WHEEL
creating 'dist/hello_world_pypi-1.0-py3-none-any.whl' and adding 'build/bdist.macosx-10.12-x86_64/wheel' to it
adding 'hello_world_pypi/__init__.py'
adding 'hello_world_pypi/helloworld.py'
adding 'hello_world_pypi-1.0.dist-info/LICENSE'
adding 'hello_world_pypi-1.0.dist-info/METADATA'
adding 'hello_world_pypi-1.0.dist-info/WHEEL'
adding 'hello_world_pypi-1.0.dist-info/top_level.txt'
adding 'hello_world_pypi-1.0.dist-info/RECORD'
removing build/bdist.macosx-10.12-x86_64/wheel





python3 -m twine upload --repository-url https://test.pypi.org/legacy/ dist/* --verbose
Uploading distributions to https://test.pypi.org/legacy/



 python3 -m pip install --index-url https://test.pypi.org/simple/ --no-deps hello_world_pypi
Looking in indexes: https://test.pypi.org/simple/


[Python Packages User Guide](https://docs.python.org/3/distributing/index.html#publishing-python-packages)                                            

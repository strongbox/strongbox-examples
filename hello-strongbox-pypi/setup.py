from setuptools import setup

with open("README.md", "r") as fh:
    long_description = fh.read()

setup(
    name="hello-strongbox-pypi",
    packages = ['hello-world-pypi'],
    license='Apache 2.0',
    version="1.0.0",
    author="Ankit Tomar",
    author_email="dummy@gmail.com",
    description="Sample Hello world package",
    long_description="This is long description",
    long_description_content_type="text/markdown",
    url="https://github.com/strongbox/strongbox-examples",
    keywords = ['strongbox', 'pypi'],
    classifiers=[
        'Development Status :: 3 - Alpha',
        'Intended Audience :: Developers',
        "Programming Language :: Python :: 3",
        "Operating System :: OS Independent",
        'Programming Language :: Python :: 3',
        'Programming Language :: Python :: 3.4',
        'Programming Language :: Python :: 3.5',
        'Programming Language :: Python :: 3.6',
    ],
    install_requires=[
        'pip-hello-world',
    ],
)
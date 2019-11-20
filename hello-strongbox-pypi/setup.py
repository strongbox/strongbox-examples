from setuptools import setup

with open("README.md", "r") as fh:
    long_description = fh.read()

setup(
    name="hello_world_pypi",
    packages = ['hello_world_pypi'],
    license='MIT',
    version="1.3",
    author="Ankit Tomar",
    author_email="dummy@gmail.com",
    description="Sample Hello world package",
    long_description=long_description,
    long_description_content_type="text/markdown",
    url="https://github.com/anki2189/strongbox-examples",
    keywords = ['Hello', 'world', 'pypi'],
    classifiers=[
        'Development Status :: 3 - Alpha',
        'Intended Audience :: Developers',
        "Programming Language :: Python :: 3",
        'License :: OSI Approved :: MIT License',
        "Operating System :: OS Independent",
        'Programming Language :: Python :: 3',
        'Programming Language :: Python :: 3.4',
        'Programming Language :: Python :: 3.5',
        'Programming Language :: Python :: 3.6',
    ],
    install_requires=[
        'time',
    ],
)
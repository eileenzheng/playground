Selenium Suite
==============

This test suite contains Selenium tests for Capital, Core, UCHC, and Vitals. The suites contained in here use a mixture
of TestNG and Cucumber to drive the tests. Currently only Core is using Cucumber to drive tests.

## Directory Structure
Suite directories should follow the following structure:
```
root
`--src
   `--test
      |--java
      |  |--pages
      |  `--test
      `--resources
```
The java directory will contain the page model classes and test classes. Test classes can either be in Cucumber
defs format or TestNG format.

The resources directory will contain Cucumber feature files or TestNG xml configuration files.

**Note:** The platform-qa folder uses the "it" folder as the testing class file and resource folder

## pom.xml
The parent pom.xml controls all the dependencies for the suites. Each suite will inherit the version number
that's used in the parent pom when that dependency is used.

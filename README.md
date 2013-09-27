Selenium Suite
==============

This com.capital.test suite contains Selenium tests for Capital, Core, UCHC, PRS, and Vitals. The suites contained in here use a mixture of TestNG and Cucumber to drive the tests. Currently only Core is using Cucumber to drive tests.

Directory Structure
-------------------
Suite directories should follow the following structure:

```
For testNG

root
`--src
   `--main
   |  `--java
   |     `--com.capital
   |        `--helpers  # helper classes if needed
   |        `--pages    # page object models
   `--test
      `--java
      |  `--com.capital
      |     `--test     # test files
      `--resources      # testNG xml files
```

```
For cucumber

root
`--src
   `--main
   |  `--java
   |     `--com.core
   |        `--helpers  # helper classes if needed
   |        `--pages    # page object models
   `--it
   |  `--java
   |  |  `--com.core
   |  |     `--test     # Step definitions
   |  `--resources      # testNG xml files
   |     `--com.core
   |        `--features # cucumber .feature files
   `--test              # would be for unit tests unused right now
         `--java
         `--resources
```

pom.xml
-------
The parent pom.xml controls all the dependencies for the suites. Each suite will inherit the version number that's used in the parent pom when that dependency is used.

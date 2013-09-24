# PRS QA Test suite

The PRS QA suite uses Cucumber BDD feature scripts and runs those tests through Selenium.

## To run the tests

Clone the repository and also make sure you have maven to run.

```bash
git clone git@github.com:mdx-dev/selenium-suite.git
cd platform-qa
mvn clean verify -P PRS-it_tests
```


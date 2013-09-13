Capital Suite
=============
This suite contains tests for Captial's QA, UAT, and production suites. Uses TestNG.

## Running the Suite
To run QA:
`mvn clean test -Denv=qa`

To run UAT:
`mvn clean test -Denv=uat`

To run Production:
`mvn clean test -Denv=prod`

### Current issues
Since the NOC has problems routing UAT and Production you may have to run the tests locally. If that is the case
go into the appropriate suite xml file in the resources directory and comment out the "testLocation" parameter.


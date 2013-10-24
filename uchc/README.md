UCHC Suite
=============
This suite contains tests for UCHC's Staging and Production suites. Uses TestNG.

## Running the Suite
To run Staging:
`mvn clean test -Denv=stg`

To run Production:
`mvn clean test -Denv=prod`
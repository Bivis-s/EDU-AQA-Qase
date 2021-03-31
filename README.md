# EDU-AQA-Qase
A small test framework for the Qase.io site written using BDD approach, my coursework on Automation QA courses

## Development

**Technology stack:**
- Java 8
  - Maven
  - Lombok
  - Log4j
  - Gson
  - Javafaker
- TestNG
- Cucumber
- Picocontainer
- Selenium
- WebDriverManager
- REST Assured
- Hamcrest

**Patterns:**
- Strategy
- Factory
- Wrapper
- Proxy
- Page Object
- Loadable Component
- Page Element
- Chain of invocation

I wrote a driver factory, it is now possible to configure the WebDriver from a separate properties file

`src/main/resources/driver.properties`
```properties
# type is required (chrome or firefox), others are optional (default false)

type=chrome
headless=false
incognito=true
maximize=true
```

And I wrote a mini-adapter for qase-api, now it's possible to create projects and suites by methods

`src/main/java/api/adapters/QaseApiAdapter.java`

Also, I wrote a proxy for WebDriver, now all its actions are logged

`src/main/java/setups/PropertyDriver.java`

## Tests themselves
Gherkin scenarios are located in `src/test/resources/features`

There are 10 tests in total, 1 for sign in, 1 for sign out (`authorization.feature`) and 8 for the main functionality (`app.feature`)

**For example, one of them:**

```feature
@caseId=5
Scenario: Create a case without a suite with all valid data in a private project
  Given The 'existing_user' was created
  And A private project is created via api
  And The user is logged in
  And Open the created project
  When Click the `Create new case` button
  And Fill out case title, description, pre-conditions, post-conditions
  And Select random status, severity, priority, type, behavior, automation status
  And Click `Add step` button 2 times
  And Fill upt step number 1 action, input data, expected result with valid data
  And Fill upt step number 2 action, input data, expected result with valid data
  And Click the `Save` button
  And Open the created project
  And Enter a case name into search case field
  Then There is 1 the case without suite on the project page
```

## Run

### Before running

Before run tests you need to set environment variables or properties in the `src/test/resources/properties/accounts.properties` like this:

Please do not use special characters except _ in the property titles or environment variable names

Naming example for account with name accountname:
- for login: accountname_login
- for password: accountname_password
- for api-token: accountname_api_token

Only an account with the name `existing_user` uses in the project presently

Example:
```properties
existing_user_login=test@gmail.com
existing_user_password=str0ngPassword
existing_user_api_token=914f28738384ak54e753da3569t37a0578i8c143
```
Similar environment variables in the test-system will be valid and have a higher priority

### Running

To run tests execute the command `mvn test` or `mvn clean test` (the last command clears the target before tests)

By default, tests run in parallel on two threads, but you can change this by adding a parameter `-Ddataproviderthreadcount=n` to the mvn command where n = count of threads

Also, you can run tests by tags, adding a parameter `-Dcucumber.options="--tags t"` to the mvn command where t = tag name,
for example `mvn clean test -Dcucumber.options="--tags @caseId=4"` will run only the test with the tag `@caseId=4`

### After running

Cucumber report will be stored after tests in the path `target/cucumber-html-reports`

![img](https://user-images.githubusercontent.com/66497536/111713635-175b5980-8861-11eb-886b-235322cf75d5.png)

---

Execute command `mvn allure:report` to generate Allure report in the path `target/site/allure-maven-plugin`

![img](https://user-images.githubusercontent.com/66497536/111816680-2a683b00-88ee-11eb-8282-c8b3f01ce5c9.png)

(the test is specially made failed for showing screenshot attachment)

![img](https://user-images.githubusercontent.com/66497536/111816550-0573c800-88ee-11eb-8ace-83a72a70d0e3.png)

---

Also, you can attach Qase.io TMS test run to maven test run by adding next parameters to the test run command:

`-Dqase.enable=true -Dqase.project.code=c -Dqase.run.id=i -Dqase.api.token=t`

**Where:**
- c - project code (from 2 to 6 latin upper chars)
- i - test run id (integer)
- t - Qase API token of the account that start test run

After that, the test results will be visible in Qase.io TMS test run

![img](https://user-images.githubusercontent.com/66497536/111731258-21428400-8884-11eb-96db-74bf239e92a2.png)

To link auto-tests to test cases in Qase.io TMS, the scenario tags @caseId should have valid id

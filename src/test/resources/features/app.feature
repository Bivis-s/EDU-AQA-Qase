Feature: App

  Scenario: Create a private project with all valid data
    Given The 'existing-user' was created
    And The user is logged in
    And Open the projects page
    When Click the `Create new project` button in the upper left corner
    And Enter a valid project name
    And Enter a valid project code
    And Enter a valid description
    And Set the project access type `private`
    And Click the `Create project` button
    Then The project is opened

  Scenario: Delete an empty project using project search
    Given The 'existing-user' was created
    And A private project is created via api
    And The user is logged in
    And Open the projects page
    And Enter a project name into search project field
    And Click the three-dotted button to the right of project name on the projects page
    And Click the `Delete` text in the drop-down under three-dotted button
    When Click the `Delete project` button on the delete project page
    Then Open the projects page
    And Enter a project name into search project field
    And There is no such project on the projects page

  Scenario: Create a case without a suite with all valid data in a private project
    Given The 'existing-user' was created
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

  Scenario: Delete a case without suite
    Given The 'existing-user' was created
    And A private project is created via api
    And The user is logged in
    And Open the created project
    And 1 case without suite is created in the project via gui
    And The project is opened
    And Check the checkbox near the case name
    And Click the `Delete` button in the controls block at the top
    And Enter 'CONFIRM' into field in the delete test case modal
    When Click the `Delete` button in the delete test case modal
    Then Open the created project
    And There are 0 cases without suite on the project page

  Scenario: Create a suite with all valid data
    Given The 'existing-user' was created
    And A private project is created via api
    And The user is logged in
    And Open the created project
    And Click the `Create new suite` button
    And Fill out suite name, description, precondition on create suite modal with valid data
    When Click the `Create` button on create suite modal
    Then Open the created project
    And There are 1 suites on the project page

  Scenario: Delete an empty suite
    Given The 'existing-user' was created
    And A private project is created via api
    And The user is logged in
    And A suite is created in the project via api
    And Open the created project
    And Hover over the suite name, then click the trash icon to the right of the suite name
    When Click the `Delete suite` button in the delete suite modal
    Then Open the created project
    And There are 0 suites on the project page

  Scenario: Delete two cases by clicking a suite checkbox
    Given The 'existing-user' was created
    And A private project is created via api
    And The user is logged in
    And 2 cases without suite are created in the project via gui
    And Open the created project
    And Check the checkbox near the suite name `Test cases without suite`
    And Click the `Delete` button in the controls block at the top
    And Enter 'CONFIRM' into field in the delete test case modal
    When Click the `Delete` button in the delete test case modal
    Then Open the created project
    And There are 0 cases without suite on the project page

  Scenario: Clone a suite
    Given The 'existing-user' was created
    And A private project is created via api
    And A suite is created in the project via api
    And The user is logged in
    And Open the created project
    And Hover over the suite name, then click the copy icon to the right of the suite name
    When Click the `Clone` button in the clone suite modal
    Then Open the created project
    And There is 2 suites on the project page with same name
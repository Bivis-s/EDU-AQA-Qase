Feature: App

  Scenario: Create a private project with all valid data
    Given The 'existing_user' was created
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
    Given The 'existing_user' was created
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
    Given The 'existing_user' was created
    And A private project is created via api
    And The user is logged in
    And Open the project
    When Click the `Create new case` button
    And Fill out the case title field with valid data
    And Fill out the case description field with valid data
    And Fill out the case pre-conditions field with valid data
    And Fill out the case post-conditions field with valid data
    And Select random status
    And Select random severity
    And Select random priority
    And Select random type
    And Select random behavior
    And Select random automation status
    And Click `Add step` button 2 times
    And Fill out step number 1 action with valid data
    And Fill out step number 1 input data with valid data
    And Fill out step number 1 expected result with valid data
    And Fill out step number 2 action with valid data
    And Fill out step number 2 input data with valid data
    And Fill out step number 2 expected result with valid data
    And Click the `Save` button
    And Open the project
    And Enter a case name into search case field
    Then There is 1 the case without suite on the project page

  Scenario: Delete a case without suite
    Given The 'existing_user' was created
    And A private project is created via api
    And The user is logged in
    And Open the project
    And A case without suite is created in the project via gui
    And The project is opened
    And Check the checkbox near the case name
    And Click the gray `Delete` button in the controls block at the top
    And Enter 'CONFIRM' into field in the modal
    When Click the `Delete` button in the modal
    Then Open the project
    And There are 0 cases without suite on the project page

  Scenario: Create a suite with all valid data
    Given The 'existing_user' was created
    And A private project is created via api
    And The user is logged in
    And Open the project
    And Click the `Create new suite` button
    And Fill out the suite name on create suite modal with valid data
    And Fill out the suite description on create suite modal with valid data
    And Fill out the suite precondition on create suite modal with valid data
    When Click the `Create` button on create suite modal
    Then Open the project
    And There are 1 suites on the project page

  Scenario: Delete an empty suite
    Given The 'existing_user' was created
    And A private project is created via api
    And The user is logged in
    And A suite is created in the project via api
    And Open the project
    And Hover over the suite name, then click the trash icon to the right of the suite name
    When Click the `Delete suite` button in the modal
    Then Open the project
    And There are 0 suites on the project page

  Scenario: Delete two cases by clicking a suite checkbox
    Given The 'existing_user' was created
    And A private project is created via api
    And The user is logged in
    And A case without suite is created in the project via gui
    And A case without suite is created in the project via gui
    And Open the project
    And Check the checkbox near the suite name `Test cases without suite`
    And Click the gray `Delete` button in the controls block at the top
    And Enter 'CONFIRM' into field in the modal
    When Click the `Delete` button in the modal
    Then Open the project
    And There are 0 cases without suite on the project page

  Scenario: Clone a suite
    Given The 'existing_user' was created
    And A private project is created via api
    And A suite is created in the project via api
    And The user is logged in
    And Open the project
    And Hover over the suite name, then click the copy icon to the right of the suite name
    When Click the `Clone` button in the modal
    Then Open the project
    And There is 2 suites on the project page with same name
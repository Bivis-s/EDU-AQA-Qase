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

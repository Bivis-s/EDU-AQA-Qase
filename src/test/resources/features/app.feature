Feature: App

  Scenario Template: Create a private project with all valid data
    Given The '<Username>' was created
    And The user is logged in
    And Open the projects page
    When Click the 'Create new project' button in the upper left corner
    And Enter a valid project name
    And Enter a valid project code
    And Enter a valid description
    And Set the project access type 'private'
    And Click the 'Create project' button
    Then The project is opened

    Examples:
      | Username      |
      | existing-user |

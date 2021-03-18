Feature: Authorization

  Scenario: Login via existing-user user data
    Given The 'existing-user' was created
    And Home page is opened
    When Click the login button in the upper right corner
    And Enter email to the email field
    And Enter password to the field
    And Click the login button under fields
    Then The projects page is opened

  Scenario:
    Given The 'existing-user' was created
    And The user is logged in
    When Open the projects page
    And Click the user's avatar in the upper right corner
    And Click the `Sign out` text
    Then Login page is opened
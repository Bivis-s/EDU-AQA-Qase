Feature: Authorization

  @caseId=1
  Scenario: Login via existing_user user data
    Given The 'existing_user' was created
    And Home page is opened
    And Click the login button in the upper right corner
    And Enter email to the email field
    And Enter password to the field
    When Click the login button under fields
    Then The projects page is opened

  @caseId=2
  Scenario: Sign out through user menu dropdown
    Given The 'existing_user' was created
    And The user is logged in
    And Open the projects page
    And Click the user's avatar in the upper right corner
    When Click the `Sign out` text
    Then Login page is opened

Feature: Authorization

  Scenario Template: Login via <Username> user data
    Given The '<Username>' was created
    And Home page is opened
    When Click the login button in the upper right corner
    And Enter email to the email field
    And Enter password to the field
    And Click the login button under fields
    Then The projects page is opened

    Examples:
      | Username      |
      | existing-user |

  Scenario Template:
    Given The '<Username>' was created
    And The user is logged in
    When Open the projects page
    And Click the user's avatar in the upper right corner
    And Click the 'Sign out' text
    Then Login page is opened

    Examples:
      | Username      |
      | existing-user |

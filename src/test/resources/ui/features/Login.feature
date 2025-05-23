Feature: LogIn

  Scenario: Open login modal
    Given the user is on the main page
    When the user click login
    Then login modal will be shown

  Scenario Outline: login Success
    Given the user is on the main page
    #And the user already sign up with username "<username>" and password "<password>"
    When the user click login
    And the user enters "<test_type>" username "<username>" and password "<password>" for "<input_type>"
    And clicks the login button
    Then the user "<username>" successfully login

    Examples:
      | test_type | username            | password | input_type |
      | existing  | test1745728254292   | random   | login      |

  Scenario Outline: login Failed
    Given the user is on the main page
    When the user click login
    And the user enters "<test_type>" username "<username>" and password "<password>" for "<input_type>"
    And clicks the login button
    Then the error message "<message>" should be displayed

    Examples:
      | test_type | username      | password | message                                | input_type |
      | invalid   |               | fhbyduji | Please fill out Username and Password. | login      |
      | invalid   | random        |          | Please fill out Username and Password. | login      |
      | invalid   |               |          | Please fill out Username and Password. | login      |
      | invalid   | existing_user | fhbyduji | Wrong password.                        | login      |

  Scenario: User Logout
    Given user already logged in
    When user click logout button
    Then user is logged out from the account
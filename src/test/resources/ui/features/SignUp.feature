Feature: SignUp

  Scenario: Open signup modal
    Given the user is on the main page
    When the user click sign up
    Then sign up modal will be shown

  Scenario Outline: SignUp Success
    Given the user is on the main page
    When the user click sign up
    And the user enters "<test_type>" username "<username>" and password "<password>" for "<input_type>"
    And clicks the sign up button
    Then the success message "<message>" should be displayed

    Examples:
      | test_type | username | password | input_type | message |
      | valid     | random   | random   | signup     | Sign up successful. |

  Scenario Outline: SignUp Failed
    Given the user is on the main page
    When the user click sign up
    And the user enters "<test_type>" username "<username>" and password "<password>" for "<input_type>"
    And clicks the sign up button
    Then the error message "<message>" should be displayed

    Examples:
      | test_type | username | password | message |  input_type |
      | invalid   |          | fhbyduji | Please fill out Username and Password. | signup     |
      | invalid   | random   |          | Please fill out Username and Password. | signup     |
      | invalid   |          |          | Please fill out Username and Password. | signup     |
      | existing  | existing_user | fhbyduji | This user already exist.          | signup     |
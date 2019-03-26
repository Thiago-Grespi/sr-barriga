Feature: Example feature 01

  Background: Example background
    Given i do something that all steps will use
    And can do some more that all steps will use too


  Scenario Outline: Scenario 01 that do the feature by a certain way
    When i type an <example_message> in the example imput field
    And click in the example save button
    Then the <example_message< must be displayed in a text field

    Examples:
      | example_message                                |
      | This is literally the message you want to send |


  Scenario: Scenario 02 that do the feature by another way
    When i click the example save button without any exemple message inserted
    Then an alert must be displayed with the <error_message>

    Examples:
      | error_message                       |
      | This is literally the error message |
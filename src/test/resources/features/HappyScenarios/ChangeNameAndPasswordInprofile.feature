@ChangeNameAndPasswordInprofile
Feature: Change in user profile date

  @EgyptSmokeTest
    @MoroccoSmokeTest
  Scenario Outline: changing name and password in user profile
    Given portal and browser open in "<language>", user login
    When open profile screen
    When change the name and click save
    When change the password click save
    When user log out and login again
    Then verify that data changed
    Examples:
      | language     |
      | Arabic_Egypt |
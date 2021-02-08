@changeNameAndPasswordInprofile
Feature: Change in user profile date

  Scenario: changing name and password in user profile
    Given portal and browser open, user login
    When open profile screen
    When change the name and click save
    When change the password click save
    When user log out and login again
    Then verify that data changed
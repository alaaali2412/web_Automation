@GoogleLogin

  Feature: Login with user google account

    Scenario Outline: User try to login via google
      Given browser open,navigate to the portal
      When close the pop up click login with google
      And in the opened popup add "<gmail>" and "<pass>", click login
      Then verify that user login can login via google

      Examples:
        | gmail           | pass   |
        | QATestlucky2021@gmail.com | LUcky@2123 |
package com.lucky.qa.steps;

import com.lucky.qa.common.BasePage;
import com.lucky.qa.pages.HomePage;
import com.lucky.qa.pages.LoginPage;
import com.lucky.qa.pages.PageGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ForgetPasswordTest {
    @Given("user click login")
    public void user_click_login() {
        PageGenerator.getInstance(HomePage.class).clickSignInBtn();
    }

    @When("user add Email and wrong password and click login")
    public void user_add_email_and_wrong_password_and_click_login() {
        PageGenerator.getInstance(LoginPage.class).loginWithInvalidPass();
    }

    @When("validation message displayed")
    public void validation_message_displayed() {
        PageGenerator.getInstance(LoginPage.class).checkErrorMessageIsDisplayed(PageGenerator
                .getInstance(BasePage.class).detectLanguage("InvalidPassErrorMsg"));
    }

    @When("click forget password")
    public void click_forget_password() {
        PageGenerator.getInstance(LoginPage.class).clickForgetPasswordLink();
    }

    @When("user will add his email and click Email me")
    public void user_will_add_his_email_and_click_email_me() {
        PageGenerator.getInstance(LoginPage.class).sendEmailResetPass();
    }

    @When("user will open his email account and open the received email")
    public void user_will_open_his_email_account_and_open_the_received_email() {
        PageGenerator.getInstance(LoginPage.class).OpenGmail();
        PageGenerator.getInstance(LoginPage.class).checkTheUnreadEmails();
        PageGenerator.getInstance(LoginPage.class).openResetPassEmail();
    }

    @When("user click reset password and add the new password and confirm password")
    public void user_click_reset_password_and_add_the_new_password_and_confirm_password() {
        PageGenerator.getInstance(LoginPage.class).addNewPass();
    }

    @Then("user can log with the new password")
    public void user_can_log_with_the_new_password() {
        PageGenerator.getInstance(LoginPage.class).login("LoginData.properties",
                "GoogleEmail", "NewPassword");
    }

}

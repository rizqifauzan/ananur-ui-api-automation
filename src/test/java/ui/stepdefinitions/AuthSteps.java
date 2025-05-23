
package ui.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ui.pages.MainPage;
import ui.utils.GlobalFunction;

public class AuthSteps {
    MainPage mainPage = new MainPage();
    String savedUsername = "test1745728254292";
    @Given("the user is on the main page")
    public void theUserIsOnTheMainPage() {
        mainPage.navigateToMainPage();
        mainPage.verifyPageLoaded();
    }

    @When("the user click sign up")
    public void theUserClickSignUp() {
        mainPage.clickOpenModalSignupButton();
    }

    @And("the user enters {string} username {string} and password {string} for {string}")
    public void enterCredentials(String testType, String username, String password, String inputType) {
        boolean isLogin = inputType.equalsIgnoreCase("login");
        boolean isExistingOrInvalid = testType.equalsIgnoreCase("invalid") || testType.equalsIgnoreCase("existing");

        if (isExistingOrInvalid) {
            fillCredentials(isLogin, username, password);
        } else {
            savedUsername = new GlobalFunction().GenerateRandomUsername();
            fillCredentials(isLogin, savedUsername, password);
        }
    }

    private void fillCredentials(boolean isLogin, String username, String password) {
        if (isLogin) {
            mainPage.fillUsernameLoginModal(username);
            mainPage.fillPasswordLoginModal(password);
        } else {
            mainPage.fillUsernameSignupModal(username);
            mainPage.fillPasswordSignupModal(password);
        }
    }



    @And("clicks the sign up button")
    public void clicksTheSignUpButton() {
        mainPage.clickSignupButton();
    }

    @Then("the error message {string} should be displayed")
    public void theErrorMessageShouldBeDisplayed(String message) {
        mainPage.verifyAlertMessage(message);
    }

    @Then("sign up modal will be shown")
    public void signUpModalWillBeShown() {
        mainPage.verifySignupModalLoaded();
    }

    @And("user cancel the sign up process")
    public void userCancelTheSignUpProcess() {
        mainPage.clickCloseSignupModalButton();
    }

    @Then("sign up modal will be closed")
    public void signUpModalWillBeClosed() {
        mainPage.verifySignupModalClosed();
    }

    @Then("the success message {string} should be displayed")
    public void theSuccessMessageShouldBeDisplayed(String message) {
        mainPage.verifyAlertMessage(message);
    }

    @When("the user click login")
    public void theUserClickLogin() {
        mainPage.clickOpenModalLoginButton();
    }

    @Then("login modal will be shown")
    public void loginModalWillBeShown() {
        mainPage.verifyLoginModalLoaded();
    }

    @And("clicks the login button")
    public void clicksTheLoginButton() {
        mainPage.clickLoginButton();
    }

    @And("the user already sign up with username {string} and password {string}")
    public void theUserAlreadySignUpWithUsernameAndPassword(String username, String password) {
        mainPage.clickOpenModalSignupButton();
        fillCredentials(false, username, password);
        mainPage.clickSignupButton();
        mainPage.verifyAlertMessage("Sign up successful.");
    }

    @Then("the user {string} successfully login")
    public void theUserSuccessfullyLogin(String username) {
        mainPage.verifyLoginSuccess(username);
    }

    @Given("user already logged in")
    public void userAlreadyLoggedIn() {
        mainPage.loginProcess();
    }

    @When("user click logout button")
    public void userClickLogoutButton() {
        mainPage.clickLogoutButton();
    }

    @Then("user is logged out from the account")
    public void userIsLoggedOutFromTheAccount() {
        mainPage.verifyPageLoaded();
    }
}

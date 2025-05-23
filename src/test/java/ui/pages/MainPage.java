package ui.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class MainPage extends BasePageClass {

    private static final String URL = "https://www.demoblaze.com/index.html";

    private final By navBar = By.xpath("//nav");
    private final By logoNav = By.xpath("//a[@id='nava']");
    private final By btnNavLogin = By.xpath("//a[@id='login2']");
    private final By btnNavSignup = By.xpath("//a[@id='signin2']");
    private final By btnNavHome = By.xpath("//a[@class='nav-link'][@href='index.html']");
    private final By btnNavCart = By.xpath("//a[@href='cart.html']");
    private final By btnNavContact = By.xpath("//a[normalize-space()='Contact']");
    private final By btnNavAboutUs = By.xpath("//a[normalize-space()='About us']");
    private final By btnNavLogout = By.xpath("//a[@id='logout2']");
    private final By btnNavNameUser = By.xpath("//a[@id='nameofuser']");

    private final By footer = By.xpath("//footer");
    private final By footerContent = By.xpath("//div[@id='fotcont']");

    // MODAL LOGIN
    private final By mdlLogin = By.xpath("//div[@id='logInModal']");
    private final By mdlLoginTitle = By.xpath("//h5[@id='logInModalLabel']");
    private final By txtLoginUsername = By.xpath("//input[@id='loginusername']");
    private final By txtLoginPassword = By.xpath("//input[@id='loginpassword']");
    private final By btnLogin = By.xpath("//button[@onclick='logIn()']");
    private final By btnLoginCancal = By.xpath("//div[@id='logInModal']//button[@type='button'][normalize-space()='Close']");

    // MODAL SIGN UP
    private final By mdlSignUp = By.xpath("//div[@id='signInModal']");
    private final By mdlSignUpTitle = By.xpath("//h5[@id='signInModalLabel']");
    private final By txtSignUpUsername = By.xpath("//input[@id='sign-username']");
    private final By txtSignUpPassword = By.xpath("//input[@id='sign-password']");
    private final By btnSignUp = By.xpath("//button[@onclick='register()']");
    private final By btnSignUpCancel = By.xpath("//div[@id='signInModal']//button[@type='button'][normalize-space()='Close']");

    public void navigateToMainPage() {
        navigateToPage(URL);
    }

    public void verifyPageLoaded() {
        verifyElementsAreVisible(navBar, btnNavHome, btnNavContact, btnNavAboutUs, logoNav);
        scrollByPixel(driver, 500);
        verifyElementsAreVisible(footer, footerContent);
        scrollByPixel(driver, -500);
    }

    public void verifyLoginModalLoaded() {
        verifyElementsAreVisible(mdlLogin, mdlLoginTitle, txtLoginUsername, txtLoginPassword, btnLogin, btnLoginCancal);
    }

    public void verifySignupModalLoaded() {
        verifyElementsAreVisible(mdlSignUp, mdlSignUpTitle, txtSignUpUsername, txtSignUpPassword, btnSignUp, btnSignUpCancel);
    }

    public void verifySignupModalClosed() {
        verifyElementsAreNotVisible(mdlSignUp, mdlSignUpTitle, txtSignUpUsername, txtSignUpPassword, btnSignUp, btnSignUpCancel);
    }

    public void verifyLoginSuccess(String userProfileText) {
        verifyElementsAreVisible(btnNavLogout, btnNavNameUser);
        String btnUserProfileText = getText(btnNavNameUser);
        Assert.assertTrue(btnUserProfileText.contains(userProfileText), "User profile text not match");
    }

    public void verifyAlertMessage(String message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String actualMessage = alert.getText();
        alert.accept();
        Assert.assertEquals(message, actualMessage, "Alert message not match");
    }

    // LOGIN INTERACTION
    public void clickOpenModalLoginButton() {
        click(btnNavLogin);
    }

    public void fillUsernameLoginModal(String username) {
        type(txtLoginUsername, username);
    }

    public void fillPasswordLoginModal(String password) {
        type(txtLoginPassword, password);
    }

    public void clickLoginButton() {
        click(btnLogin);
    }

    public void clickCloseLoginModalButton() {
        click(btnLoginCancal);
    }

    // SIGN UP INTERACTION
    public void clickOpenModalSignupButton() {
        click(btnNavSignup);
    }

    public void fillUsernameSignupModal(String username) {
        type(txtSignUpUsername, username);
    }

    public void fillPasswordSignupModal(String password) {
        type(txtSignUpPassword, password);
    }

    public void clickSignupButton() {
        click(btnSignUp);
    }

    public void clickCartButton() {
        click(btnNavCart);
    }

    public void clickCloseSignupModalButton() {
        click(btnSignUpCancel);
    }

    public void clickLogoutButton() {
        click(btnNavLogout);
    }

    public void clickMainPageButton() {
        click(btnNavHome);
    }

    public void loginProcess() {
        loginProcess("test1745728254292", "random");
    }

    public void loginProcess(String username, String password) {
        navigateToMainPage();
        clickOpenModalLoginButton();
        fillUsernameLoginModal(username);
        fillPasswordLoginModal(password);
        clickLoginButton();
        verifyLoginSuccess(username);
    }

}
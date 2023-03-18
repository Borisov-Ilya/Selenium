package ru.appline.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Стартовая страница приложения
 */
public class LoginPage extends BasePage {
    @FindBy(xpath = "//form[@id='login-form']")
    private WebElement loginForm;

    @FindBy(xpath = "//input[@name='_username']")
    private WebElement loginInput;

    @FindBy(xpath = "//input[@name='_password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitBtn;

    public LoginPage waitingLoginForm() {
        waitUtilElementToBeVisible(loginForm);
        return this;
    }

    public LoginPage enterLoginAndPassword(String login, String password) {
        fillInputField(loginInput, login);
        fillInputField(passwordInput, password);
        return this;
    }

    public MainPage clickBtnLogin() {
        waitUtilElementToBeClickable(submitBtn).click();
        return pageManager.getMainPage();
    }
}

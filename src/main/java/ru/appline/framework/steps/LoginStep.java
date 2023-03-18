package ru.appline.framework.steps;

import io.cucumber.java.ru.И;
import ru.appline.framework.managers.PageManager;

public class LoginStep {
    PageManager pageManager = PageManager.getPageManager();

    @И("^Ожидание появления формы авторизации$")
    public void waitingLoginForm() {
        pageManager.getLoginPage().waitingLoginForm();
    }

    @И("^Ввод логина \"(.+)\" и пароля \"(.+)\"$")
    public void enterLoginAndPassword(String login, String password) {
        pageManager.getLoginPage().enterLoginAndPassword(login, password);
    }

    @И("Нажимаем кнопку 'Войти'")
    public void clickBtnLogin() {
        pageManager.getLoginPage().clickBtnLogin();
    }
}

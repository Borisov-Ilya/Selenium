package ru.appline.framework.steps;

import io.cucumber.java.ru.И;
import ru.appline.framework.managers.PageManager;

public class MainStep {
    PageManager pageManager = PageManager.getPageManager();

    @И("^Проверяем заголовок на главной странице$")
    public void checkMainPageTitle() {
        pageManager.getMainPage().checkMainPageTitle();
    }

    @И("^Выбираем \"(.+)\" в главном меню$")
    public void selectBaseMenu(String nameBaseMenu) {
        pageManager.getMainPage().selectBaseMenu(nameBaseMenu);
    }

    @И("^Выбираем \"(.+)\" в подменю главного меню$")
    public void selectSubMenu(String nameBaseMenu) {
        pageManager.getMainPage().selectSubMenu(nameBaseMenu);
    }
}

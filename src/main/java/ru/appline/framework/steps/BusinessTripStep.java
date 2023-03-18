package ru.appline.framework.steps;

import io.cucumber.java.ru.И;
import ru.appline.framework.managers.PageManager;

public class BusinessTripStep {
    PageManager pageManager = PageManager.getPageManager();

    @И("^Проверяем заголовок на странице командировок$")
    public void checkMainPageTitle() {
        pageManager.getBusinessTripPage().checkBusinessTripPageTitle();
    }

    @И("^Нажимаем на кнопку 'Создать командировку'$")
    public void createBusinessTrip() {
        pageManager.getBusinessTripPage().createBusinessTrip();
    }
}

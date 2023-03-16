package ru.appline.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BusinessTripPage extends BasePage {
    @FindBy(xpath = "//h1[@class='oro-subtitle']")
    private WebElement title;
    @FindBy(xpath = "//a[@title='Создать командировку']")
    private WebElement createBusinessTripBtn;

    @Step("Проверка заголовка на странице командировок")
    public BusinessTripPage checkBusinessTripPageTitle() {
        assertEquals("Все Командировки", title.getText(),
                "Заголовок отсутствует/не соответствует требуемому");
        return this;
    }

    @Step("Нажимаем кнопку 'Создать командировку'")
    public CreateBusinessTripPage createBusinessTrip() {
        waitUtilElementToBeClickable(createBusinessTripBtn).click();
        loading();
        return pageManager.getCreateBusinessTripPage();
    }
}

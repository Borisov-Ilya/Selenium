package ru.appline.framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CreateBusinessTripPage extends BasePage {
    @FindBy(xpath = "//h1[@class='user-name']")
    private WebElement title;

    @FindBy(xpath = "//select[@data-name='field__business-unit']")
    private WebElement subdivisionBtn;

    @FindBy(xpath = "//select[@data-name='field__business-unit']/option")
    private List<WebElement> listSubdivision;

    @FindBy(xpath = "//a[@id='company-selector-show']")
    private WebElement openListBtn;

    @FindBy(xpath = "//span[@class='select2-chosen']")
    private WebElement selectOrganizationBtn;

    @FindBy(xpath = "//input[contains(@class, 'select2-input')]")
    private WebElement organizationInput;

    @FindBy(xpath = "//ul[@class='select2-results']/li/div")
    private List<WebElement> listOrganizationsSearch;

    @FindBy(xpath = "//div[@class='oro-clearfix']/label")
    private List<WebElement> tasksLabel;

    @FindBy(xpath = "//input[contains(@id, 'crm_business_trip_departureCity')]")
    private WebElement departureCityInput;

    @FindBy(xpath = "//input[contains(@id, 'crm_business_trip_arrivalCity')]")
    private WebElement arrivalCityInput;

    @FindBy(xpath = "//input[contains(@id, 'date_selector_crm_business_trip_departureDatePlan')]")
    private WebElement departureDateInput;

    @FindBy(xpath = "//input[contains(@id, 'date_selector_crm_business_trip_returnDatePlan')]")
    private WebElement returnDateInput;

    @FindBy(xpath = "//button[contains(text(), 'Сохранить и закрыть')]")
    private WebElement saveAndCloseBtn;

    @FindBy(xpath = "//span[@class='validation-failed']")
    private WebElement validationFailedMessage;

    @Step("Проверка заголовка на странице создания командировки")
    public CreateBusinessTripPage checkCreateBusinessTripPageTitle() {
        assertEquals("Создать командировку", title.getText(),
                "Заголовок отсутствует/не соответствует требуемому");
        return this;
    }

    @Step("Нажимаем кнопку выбора подразделения")
    public CreateBusinessTripPage clickSubdivisionBtn() {
        subdivisionBtn.click();
        return this;
    }

    @Step("Выбираем подразделение {nameSubdivision}")
    public CreateBusinessTripPage selectSubdivision(String nameSubdivision) {
        for (WebElement subItem : listSubdivision) {
            if (subItem.getText().trim().equalsIgnoreCase(nameSubdivision)) {
                waitUtilElementToBeClickable(subItem).click();
                return this;
            }
        }
        fail("Подразделение '" + nameSubdivision + "' не было найдено!");
        return this;
    }

    @Step("Нажимаем кнопку 'Открыть список'")
    public CreateBusinessTripPage clickOpenListBtn() {
        waitUtilElementToBeClickable(openListBtn).click();
        return this;
    }

    @Step("Нажимаем кнопку 'Укажите организацию'")
    public CreateBusinessTripPage clickSelectOrganizationBtn() {
        waitUtilElementToBeClickable(selectOrganizationBtn).click();
        return this;
    }

    @Step("Заполняем поле {nameField}, значением {value}")
    public CreateBusinessTripPage fillField(String nameField, String value) {
        WebElement element = null;
        switch (nameField) {
            case "Организация":
                fillInputField(organizationInput, value);
                element = organizationInput;
                break;
            case "Город выбытия":
                fillInputField(departureCityInput, value);
                element = departureCityInput;
                break;
            case "Город прибытия":
                fillInputField(arrivalCityInput, value);
                element = arrivalCityInput;
                break;
            case "Планируемая дата выезда":
                fillDateField(departureDateInput, value);
                element = departureDateInput;
                break;
            case "Планируемая дата возвращения":
                fillDateField(returnDateInput, value);
                element = returnDateInput;
                break;
            default:
                fail("Поле с наименованием '" + nameField + "' отсутствует на странице " +
                        "'Создания командировки'");
        }
        assertEquals(value, element.getAttribute("value"),
                "Поле '" + nameField + "' было заполнено некорректно");
        return this;
    }

    @Step("Выбираем организацию из списка")
    public CreateBusinessTripPage selectOrganization(String organization) {
        for (WebElement organizationItem : listOrganizationsSearch) {
            if (organizationItem.getText().trim().equalsIgnoreCase(organization)) {
                waitUtilElementToBeClickable(organizationItem).click();
                return this;
            }
        }
        fail("Организация '" + organization + "' не найдена!");
        return this;
    }

    @Step("Выбираем задачу {task}")
    public CreateBusinessTripPage selectTask(String task) {
        for (WebElement element : tasksLabel) {
            if (element.getText().trim().equalsIgnoreCase(task)) {
                scrollToElementJs(element);
                waitUtilElementToBeClickable(element).click();
                return this;
            }
        }
        fail(String.format("Чекбокс [%s] не найден", task));
        return this;
    }

    @Step("Нажимаем кнопку 'Сохранить и выйти'")
    public CreateBusinessTripPage clickSaveAndClose() {
        waitUtilElementToBeClickable(saveAndCloseBtn).click();
        loading();
        return this;
    }

    @Step("Проверяем сообщение об ошибке {message}")
    public CreateBusinessTripPage checkValidationFailedMessage(String message) {
        waitUtilElementToBeVisible(validationFailedMessage);
        assertEquals(message, validationFailedMessage.getText(),
                String.format("Текст не совпадает. Ожидаемый результат [%s]", message));
        return this;
    }
}

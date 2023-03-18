package ru.appline.framework.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import ru.appline.framework.managers.PageManager;

public class CreateBusinessTripStep {
    PageManager pageManager = PageManager.getPageManager();

    @И("Проверяем заголовок на странице создания командировки")
    public void checkCreateBusinessTripPageTitle() {
        pageManager.getCreateBusinessTripPage().checkCreateBusinessTripPageTitle();
    }

    @И("Нажимаем на кнопку выбора подразделения")
    public void clickSubdivisionBtn() {
        pageManager.getCreateBusinessTripPage().clickSubdivisionBtn();
    }

    @И("^Выбираем подразделение \"(.+)\"$")
    public void selectSubdivision(String nameSubdivision) {
        pageManager.getCreateBusinessTripPage().selectSubdivision(nameSubdivision);
    }

    @И("^Нажимаем на кнопку 'Открыть список'$")
    public void clickOpenListBtn() {
        pageManager.getCreateBusinessTripPage().clickOpenListBtn();
    }

    @И("^Нажимаем на кнопку выбора организации$")
    public void clickSelectOrganizationBtn() {
        pageManager.getCreateBusinessTripPage().clickSelectOrganizationBtn();
    }

    @И("^Заполняем поля формы:$")
    public void fillFields(DataTable mapFieldsAndValue) {
        mapFieldsAndValue.asMap(String.class, String.class).forEach((key, value) ->
                pageManager.getCreateBusinessTripPage().fillField((String) key, (String) value));
    }

    @И("^Выбираем организацию \"(.+)\"$")
    public void selectOrganization(String organization) {
        pageManager.getCreateBusinessTripPage().selectOrganization(organization);
    }

    @И("^Выбираем задачу \"(.+)\"$")
    public void selectTask(String task) {
        pageManager.getCreateBusinessTripPage().selectTask(task);
    }

    @И("^Нажимаем на кнопку 'Сохранить и закрыть'$")
    public void clickSaveAndClose() {
        pageManager.getCreateBusinessTripPage().clickSaveAndClose();
    }

    @И("^Проверяем, что на странице появилось сообщение об ошибке \"(.+)\"$")
    public void checkValidationFailedMessage(String message) {
        pageManager.getCreateBusinessTripPage().checkValidationFailedMessage(message);
    }


}

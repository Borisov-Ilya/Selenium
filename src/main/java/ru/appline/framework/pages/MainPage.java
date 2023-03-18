package ru.appline.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MainPage extends BasePage {
    @FindBy(xpath = "//h1[@class='oro-subtitle']")
    private WebElement title;

    @FindBy(xpath = "//ul[contains(@class, 'main-menu')]/li/a")
    private List<WebElement> listBaseMenu;

    @FindBy(xpath = "//ul[contains(@class, 'dropdown-menu')]/li[contains(@class, 'single')]/a")
    private List<WebElement> listSubMenu;

    public MainPage checkMainPageTitle() {
        assertEquals("Панель быстрого запуска", title.getText(),
                "Заголовок отсутствует/не соответствует требуемому");
        return this;
    }

    /**
     * Функция наведения мыши на любой пункт меню
     *
     * @param nameBaseMenu - наименование меню
     * @return HomePage - т.е. остаемся на этой странице
     */
    public MainPage selectBaseMenu(String nameBaseMenu) {
        for (WebElement menuItem : listBaseMenu) {
            if (menuItem.getText().trim().equalsIgnoreCase(nameBaseMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                return this;
            }
        }
        fail("Меню '" + nameBaseMenu + "' не было найдено на стартовой странице!");
        return this;
    }

    /**
     * Функция клика на любое подменю
     *
     * @param nameSubMenu - наименование подменю
     * @return InsurancePage - т.е. переходим на страницу {@link MainPage}
     */
    public BusinessTripPage selectSubMenu(String nameSubMenu) {
        for (WebElement subMenuItem : listSubMenu) {
            if (subMenuItem.getText().trim().equalsIgnoreCase(nameSubMenu)) {
                waitUtilElementToBeClickable(subMenuItem).click();
                loading();
                return pageManager.getBusinessTripPage().checkBusinessTripPageTitle();
            }
        }
        fail("Подменю '" + nameSubMenu + "' не было найдено на стартовой странице!");
        return pageManager.getBusinessTripPage();
    }
}

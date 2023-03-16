package ru.appline.framework.managers;

import ru.appline.framework.pages.BusinessTripPage;
import ru.appline.framework.pages.CreateBusinessTripPage;
import ru.appline.framework.pages.LoginPage;
import ru.appline.framework.pages.MainPage;

/**
 * @author Arkadiy_Alaverdyan
 * Класс для управления страничками
 */
public class PageManager {

    /**
     * Менеджер страничек
     */
    private static PageManager pageManager;

    /**
     * Страничка логина
     */
    private LoginPage loginPage;

    /**
     * Главная страничка
     */
    private MainPage mainPage;

    /**
     * Страничка командировок
     */
    private BusinessTripPage businessTripPage;

    /**
     * Страничка создания командировки
     */
    private CreateBusinessTripPage createBusinessTripPage;

    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @see PageManager#getPageManager()
     */
    private PageManager() {
    }

    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Ленивая инициализация {@link LoginPage}
     *
     * @return StartPage
     */
    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    /**
     * Ленивая инициализация {@link MainPage}
     *
     * @return MainPage
     */
    public MainPage getMainPage() {
        if (mainPage == null) {
            mainPage = new MainPage();
        }
        return mainPage;
    }

    /**
     * Ленивая инициализация {@link BusinessTripPage}
     *
     * @return MainPage
     */
    public BusinessTripPage getBusinessTripPage() {
        if (businessTripPage == null) {
            businessTripPage = new BusinessTripPage();
        }
        return businessTripPage;
    }

    /**
     * Ленивая инициализация {@link CreateBusinessTripPage}
     *
     * @return TariffPage
     */
    public CreateBusinessTripPage getCreateBusinessTripPage() {
        if (createBusinessTripPage == null) {
            createBusinessTripPage = new CreateBusinessTripPage();
        }
        return createBusinessTripPage;
    }
}

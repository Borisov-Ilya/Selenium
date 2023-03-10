package TravelRequest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

/**
 * Задание №1.
 * Написать автотест по заданному сценарию
 * Сценарий "Оформление заявки на командировку"
 * Перейти на страницу <a href="http://training.appline.ru/user/login">...</a>
 * Пройти авторизацию (варианты имен пользователей даны после сценария)
 * Проверить наличие на странице заголовка Панель быстрого запуска
 * В выплывающем окне раздела "Расходы" нажать на "Командировки".
 * Нажать на "Создать командировку"
 * Проверить наличие на странице заголовка "Создать командировку"
 * На странице создания командировки заполнить или выбрать поля:
 * — Подразделение - выбрать Отдел внутренней разработки
 * — Принимающая организация - нажать "Открыть список" и в поле "Укажите организацию" выбрать любое значение
 * — В задачах поставить чекбокс на "Заказ билетов"
 * — Указать города выбытия и прибытия
 * — Указать даты выезда и возвращения
 * —!! Раздел Командированные сотрудники не заполнять
 * Проверить, что все поля заполнены правильно
 * Нажать "Сохранить и закрыть"
 * Проверить, что на странице появилось сообщение: "Список командируемых сотрудников не может быть пустым"
 */

public class TravelRequestTest {
    WebDriver driver = new FirefoxDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    @BeforeEach
    public void before() {
        System.setProperty("webdriver.firefox.driver", "src/test/resources/webdriver/geckodriver.exe");

        // 1. Перейти на страницу
        driver.manage().window().maximize();
        driver.get("http://training.appline.ru/user/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void test() {
        // 2. Пройти авторизацию
        wait.until(visibilityOf(driver.findElement(By.xpath(
                "//form[@id='login-form']"))));
        driver.findElement(By.xpath("//input[@name='_username']")).sendKeys("Taraskina Valeriya");
        driver.findElement(By.xpath("//input[@name='_password']")).sendKeys("testing");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // 3. Проверить наличие на странице заголовка "Панель быстрого запуска"
        wait.until(visibilityOf(driver.findElement(By.xpath(
                "//h1[@class='oro-subtitle']"))));

        // 4. В выплывающем окне раздела "Расходы" нажать на "Командировки"
        WebElement costsList = driver.findElement(By.xpath(
                "//ul[contains(@class, 'main-menu')]/li/a/span[text()='Расходы']"));
        costsList.click();
        wait.until(visibilityOf(costsList.findElement(
                By.xpath("./ancestor::li//ul[@class='dropdown-menu menu_level_1']"))));
        driver.findElement(By.xpath("//span[text()='Командировки']")).click();
        loading();

        // 5. Нажать на "Создать командировку"
        driver.findElement(By.xpath("//a[@title='Создать командировку']")).click();
        loading();

        // 6. Проверить наличие на странице заголовка "Создать командировку"
        WebElement BusinessTrip = driver.findElement(By.xpath("//h1[@class='user-name']"));
        wait.until(visibilityOf(BusinessTrip));
        assertEquals("Создать командировку", BusinessTrip.getText(),
                String.format("Мы попали на страницу %s", BusinessTrip.getText()));

        // 7. На странице создания командировки заполнить или выбрать поля
        // - Подразделение
        String subdivision = "Отдел внутренней разработки";
        driver.findElement(By.xpath("//select[@data-name='field__business-unit']")).click();
        driver.findElement(By.xpath("//option[text()='Отдел внутренней разработки']")).click();
        assertEquals(subdivision, driver.findElement(By.xpath(
                        "//select[@data-name='field__business-unit']/parent::div/span")).getText(),
                String.format("Текст подразделения не совпадает. Ожидаемое значение [%s]", subdivision));

        // - Принимающая организация
        String organization = "IBS";
        driver.findElement(By.xpath("//a[@id='company-selector-show']")).click();
        driver.findElement(By.xpath("//span[@class='select2-chosen']")).click();
        driver.findElement(By.xpath("//input[contains(@class, 'select2-input')]"))
                .sendKeys(organization);
        driver.findElement(By.xpath("//span[@class='select2-match'][1]")).click();
        assertEquals(organization, driver.findElement(By.xpath(
                        "//input[@data-name='field__company']")).getAttribute("value"),
                String.format("Текст подразделения не совпадает. Ожидаемое значение [%s]", organization));

        // - В задачах поставить чекбокс на "Заказ билетов"
        WebElement ticketBooking = driver.findElement(
                By.xpath("//input[contains(@id, 'crm_business_trip_tasks_1')]"));
        ticketBooking.click();
        assertTrue(ticketBooking.isSelected(), "Чекбокс [Заказ наличных] не выбран");

        // - Указать города выбытия и прибытия
        String departureCity = "Москва";
        WebElement departureCityInput = driver.findElement(
                By.xpath("//input[contains(@id, 'crm_business_trip_departureCity')]"));
        departureCityInput.clear();
        departureCityInput.sendKeys(departureCity);
        assertEquals(departureCity, departureCityInput.getAttribute("value"),
                String.format("Город выбытия не совпадает. Ожидаемое значение [%s]", departureCity));

        String arrivalCity = "Нижний-Новгород";
        WebElement arrivalCityInput = driver.findElement(
                By.xpath("//input[contains(@id, 'crm_business_trip_arrivalCity')]"));
        arrivalCityInput.clear();
        arrivalCityInput.sendKeys(arrivalCity);
        assertEquals(arrivalCity, arrivalCityInput.getAttribute("value"),
                String.format("Город прибытия не совпадает. Ожидаемое значение [%s]", arrivalCity));

        // - Указать даты выезда и возвращения
        driver.findElement(By.xpath(
                "//input[contains(@id, 'date_selector_crm_business_trip_departureDatePlan')]")).click();
        assertTrue(driver.findElement(By.xpath("//div[@id='ui-datepicker-div']")).isDisplayed(),
                "Календарь не отобразился");
        // Дата выезда = текущая дата
        driver.findElement(By.xpath("//a[contains(@class, 'highlight')]")).click();

        driver.findElement(By.xpath(
                "//input[contains(@id, 'date_selector_crm_business_trip_returnDatePlan')]")).click();
        assertTrue(driver.findElement(By.xpath("//div[@id='ui-datepicker-div']")).isDisplayed(),
                "Календарь не отобразился");
        driver.findElement(By.xpath("//tbody//tr//td//a[contains(text(), '25')]")).click();


        // - Нажать "Сохранить и закрыть"
        driver.findElement(By.xpath("//button[contains(text(), 'Сохранить и закрыть')]")).click();
        loading();

        // - Проверить, что на странице появилось сообщение: "Список командируемых сотрудников не может быть пустым"
        String message = "Список командируемых сотрудников не может быть пустым";
        assertTrue(driver.findElement(By.xpath("//span[@class='validation-failed']"))
                .isDisplayed(), "Сообщение не отобразилось");
        assertEquals(message, driver.findElement(By.xpath("//span[@class='validation-failed']")).getText(),
                String.format("Текст не совпадает. Ожидаемый результат [%s]", message));
    }

    @AfterEach
    public void after() {
        driver.quit();
    }

    public void loading() {
        wait.until(invisibilityOf(driver.findElement(
                By.xpath("//div[@class='loader-mask shown']"))));
    }
}

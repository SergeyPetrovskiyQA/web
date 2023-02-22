package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class ChromeTest {

    private WebDriver driver;
    private static ChromeOptions options;


    @BeforeAll
    static void setupAll() {
        options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void teardown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestV1() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=\"name\"] input")).sendKeys("Петров Евгений");
        driver.findElement(By.cssSelector("[data-test-id=\"phone\"] input")).sendKeys("+79001231212");
        driver.findElement(By.cssSelector("[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.cssSelector("[class=\"button__content\"]")).click();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=\"order-success\"]")).getText();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldTestV2() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=\"name\"] input")).sendKeys("Сазазонов-Петров Сергей");
        driver.findElement(By.cssSelector("[data-test-id=\"phone\"] input")).sendKeys("+79001231212");
        driver.findElement(By.cssSelector("[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.cssSelector("[class=\"button__content\"]")).click();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=\"order-success\"]")).getText();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void negativeTestV1() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=\"name\"] input")).sendKeys("Петров Семён");
        driver.findElement(By.cssSelector("[data-test-id=\"phone\"] input")).sendKeys("+79001231212");
        driver.findElement(By.cssSelector("[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.cssSelector("[class=\"button__content\"]")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.className("input__sub")).getText();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void negativeTestV2() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=\"name\"] input")).sendKeys("Li Chang");
        driver.findElement(By.cssSelector("[data-test-id=\"phone\"] input")).sendKeys("+7900123121");
        driver.findElement(By.cssSelector("[data-test-id=\"agreement\"]")).click();
        driver.findElement(By.cssSelector("[class=\"button__content\"]")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.className("input__sub")).getText();
        Assertions.assertEquals(expected, actual);
    }
}


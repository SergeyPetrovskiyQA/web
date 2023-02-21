package ru.netology.web;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class ChromeTest {

    private WebDriver driver;

    @BeforeAll
    static void setupAll() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\888\\Desktop\\selenium\\driver\\chromedriver.exe");
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
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
}


package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class ShopMenuTest {
    private WebDriver driver;
    private WebDriverWait wait;
    JavascriptExecutor js;

    /**
     * Rigorous Test :-)
     */
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, 20);
        //driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }

    private List<WebElement> getMenuItems() {
        return driver.findElements(By.cssSelector("#app-"));
    }

    private List<WebElement> getSubItems() {
        return driver.findElements(By.cssSelector("#box-apps-menu li li a"));
    }

    private boolean isExist(By located) {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        try {
            driver.findElement(located);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        }
    }

    @Test
    public void authorization() {  //входит в панель администратора http://localhost/litecart/admin
        driver.get("http://localhost/litecart/admin/login.php?redirect_url=%2Flitecart%2Fadmin%2F");
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void MenuTest() {
        authorization();

        Actions actions = new Actions(driver);

        List<WebElement> menuItems = getMenuItems();
        for (int i = 0; i < menuItems.size(); i++) {
            menuItems = getMenuItems();
            WebElement menuItem = menuItems.get(i);
            actions.moveToElement(menuItem).click().build().perform();

            if (!isExist(By.tagName("h1"))) {
                Assert.fail();
            }

            menuItems = getMenuItems();

            if ((driver.findElements(By.cssSelector("ul.docs li")).size() > 0)) {
                List<WebElement> menuSubItems = getSubItems();
                for (int j = 1; j < menuSubItems.size(); j++) {
                    menuSubItems = getSubItems();
                    WebElement subItem = menuSubItems.get(j);
                    actions.moveToElement(subItem).click().build().perform();

                    if (!isExist(By.tagName("h1"))) {
                        Assert.fail();
                    }

                }
            }

        }
    }

}



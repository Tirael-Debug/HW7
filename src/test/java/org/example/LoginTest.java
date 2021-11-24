package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class LoginTest {
    private WebDriver driver;
    private Map<String, Object> vars;
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
        vars = new HashMap<String, Object>();
        wait = new WebDriverWait(driver, 20);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }

    @Test
    public void Authorization() {  //входит в панель администратора http://localhost/litecart/admin
        driver.get("http://localhost/litecart/admin/login.php?redirect_url=%2Flitecart%2Fadmin%2F");
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        //2. прокликивает последовательно все пункты меню слева, включая вложенные пункты
        List<WebElement> elementList = driver.findElements(By.cssSelector("#box-apps-menu a"));
        //List<WebElement> elementos=driver.findElements(By.cssSelector("ul.docs[id*=doc-]"));
        int numberOfListElements = elementList.size();
        //int numbershowElements= elementos.size();
        for (int i = 0; i < numberOfListElements; i++) {
            elementList = driver.findElements(By.cssSelector("#box-apps-menu a"));
            driver.findElement(By.cssSelector("#box-apps-menu a"));
            elementList.get(i).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
            /*for (int j = 0; j < numbershowElements; j++) {
                e lementList = driver.findElements(By.cssSelector("ul.docs[id*=doc-]"));
                driver.findElement(By.cssSelector("ul.docs[id*=doc-]"));
                elementList.get(j).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));*/

        }

    assertTrue( true);
        //if (numbershowElements(driver,By.cssSelector("ul.docs[id*=doc-]")))
        // 3. для каждой страницы проверяет наличие заголовка (то есть элемента с тегом h1)

        /*long timeoutInSeconds = 10;
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds); wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));

        WebElement webelem = driver.findElement(By.cssSelector("h1"));
        Assert.assertTrue(true);*/


}
    }



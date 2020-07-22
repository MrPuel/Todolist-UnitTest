import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;


public class Selenium {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver","C:/Users/Hervé/Downloads/geckodriver-v0.26.0-win64/geckodriver.exe");
        // On instancie notre driver, et on configure notre temps d'attente
        driver = new FirefoxDriver();
        baseUrl = "http://localhost/TU/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testSeleniumCreate() throws Exception {
        // On se connecte au site
        driver.get(baseUrl + "index.php/");

        // On se rend page 1
        driver.findElement(By.id("inserer")).clear();
        driver.findElement(By.id("inserer")).sendKeys("tache 1");
        WebDriverWait wait = new WebDriverWait(driver, 3);
        driver.findElement(By.id("envoyer")).click();


    }

    @Test
    public void testSeleniumValidateAndDelete() throws Exception {
        // On se connecte au site
        driver.get(baseUrl + "index.php/");

        // On se rend page 1
        driver.findElement(By.id("validate")).click();
        driver.findElement(By.id("suppr")).click();

    }

    @Test
    public void testSeleniumDeleteWithoutValidate() throws Exception {
        // On se connecte au site
        driver.get(baseUrl + "index.php/");

        // On se rend page 1
        driver.findElement(By.id("inserer")).clear();
        driver.findElement(By.id("inserer")).sendKeys("tache 1");
        driver.findElement(By.id("envoyer")).click();
        driver.findElement(By.id("suppr")).click();

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alert.getText();
        } finally {
            acceptNextAlert = true;
        }
    }
}
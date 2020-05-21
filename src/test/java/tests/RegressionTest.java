package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class RegressionTest {

    private ChromeDriver driver;

    @Step("TestSetUp: Open new Chrome Browser and Open ClickDoc HomePage... ")
    @BeforeTest
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.get("https://demo.clickdoc.de/cms-de/");
    }


    @Description("Login Test login with test User successfully... ")
    @Test
    void loginTest() {
        WebDriverWait title = new WebDriverWait(driver, 15);
        title.until(ExpectedConditions.titleIs("CLICKDOC"));
        driver.findElement(By.xpath("//*[@id=\"menu-item-2004\"]/a")).click();

        WebDriverWait waitForIframe = new WebDriverWait(driver, 15);
        By locIframe = By.id("iframeDialog");
        waitForIframe.until(ExpectedConditions.visibilityOfElementLocated(locIframe));
        driver.switchTo().frame(driver.findElement(locIframe));

        By loginField = By.id("mat-input-0");
        By passwordField = By.id("mat-input-1");
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginField));
        driver.findElement(loginField).sendKeys("dirk.nonn@cgm.com#1111");
        driver.findElement(passwordField).sendKeys("abcdefg");
        WebElement loginButton = driver.findElement(By.xpath("//div[2]/button"));
        loginButton.click();
        WebDriverWait waitForValidationMessage = new WebDriverWait(driver, 15);
        waitForValidationMessage.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p")));
        driver.findElement(By.xpath("//p")).getText().equals(
                "Bitte überprüfen Sie Ihre E-Mail-Adresse, Passwort und probieren Sie es noch einmal.");
        driver.findElement(loginField).clear();
        driver.findElement(loginField).sendKeys("testmail.com");
        loginButton.click();
        WebDriverWait waitForInvalidEmailMessage = new WebDriverWait(driver, 15);
        waitForInvalidEmailMessage.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p")));
        driver.findElement(By.xpath("//p")).getText().equals(
                "Bitte überprüfen Sie Ihre E-Mail-Adresse, Passwort und probieren Sie es noch einmal.");
        driver.findElement(loginField).clear();

        driver.findElement(loginField).sendKeys("dirk.nonn@cgm.com#1111");
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys("recruitingTest1!");

        loginButton.click();
        WebDriverWait profilePageTitle = new WebDriverWait(driver, 15);
        profilePageTitle.until(ExpectedConditions.titleIs("Mein Profil | CLICKDOC"));

        this.logOut();
    }

    @Step("Logout from ClickDoc Website... ")
    public void logOut() {
        By profileImage = By.xpath("(//img[@alt='avatar-picture'])[2]");
        WebDriverWait waitForImage = new WebDriverWait(driver, 15);
        Assert.assertTrue(driver.findElement(profileImage).isDisplayed());
        waitForImage.until(ExpectedConditions.visibilityOfElementLocated(profileImage));
        driver.findElement(profileImage).click();
        driver.findElement(By.xpath("//a[2]/div/span[2]")).click();
    }

    @Step("TestCleanUp: Close Chrome Browser... ")
    @AfterTest
    void tearDown() {
        driver.quit();
    }

    @Test(enabled = false)
    void searchTest() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.clickdoc.de/cms-de/");
        WebDriverWait title = new
                WebDriverWait(driver, 15);
        title.until(ExpectedConditions.titleIs("CLICKDOC"));
        driver.findElement(By.linkText("Suchseite")).click();
        driver.findElement(By.xpath("//app-tracking/div/div/div[2]/div[2]")).click();
        driver.findElement(By.id("search-query-typeahead")).clear();
        driver.findElement(By.id("search-query-typeahead")).sendKeys("beate Edel");
        driver.findElement(By.id("search-query-typeahead")).sendKeys(Keys.DOWN);
        driver.findElement(By.xpath("//*[@id=\"search\"]/div/div[2]/div[2]/div[1]/app-filter/div/div/div[2]/div[6]/div/button")).click();

        By searchResult = By.xpath("//*[@id=\"search\"]/div/div[3]/div/div/app-physician-card[1]/div/div[1]/div[2]");
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResult));
        driver.findElement(searchResult).getText().contains("beate");
        WebElement pinCodeSearchField = driver.findElement(By.id("search-location-typeahead"));
        pinCodeSearchField.clear();
        pinCodeSearchField.sendKeys("56567");
        By pinCodeDropdown = By.xpath("//*[@id=\"search\"]/div/div[2]/div[2]/div[1]/app-filter/div/div/div[2]/div[2]/div/div/div[1]/typeahead-container");
        WebDriverWait waitForPinSearchDropdown = new WebDriverWait(driver, 15);
        waitForPinSearchDropdown.until(ExpectedConditions.visibilityOfElementLocated(pinCodeDropdown));
        driver.findElement(pinCodeDropdown).sendKeys(Keys.DOWN);
        driver.findElement(pinCodeDropdown).sendKeys(Keys.RETURN);

        driver.quit();
    }


    @Test(enabled = false)
    void qa() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        driver.get("https://www.eurotripadventures.com/tours/zurich-rhine-falls-4/");
        driver.findElement(By.name("name")).click();
        driver.findElement(By.xpath("//aside/div[2]")).click();
        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys("qa");
        driver.findElement(By.xpath("//aside/div[2]")).click();
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("asd@asd.qa");
        driver.findElement(By.xpath("//aside/div[2]")).click();
        driver.findElement(By.name("phone")).clear();
        driver.findElement(By.name("phone")).sendKeys("017626457895");
        driver.findElement(By.xpath("//form[@id='tourBookingForm']/div[6]/div/button/span")).click();
        driver.findElement(By.xpath("//form[@id='tourBookingForm']/div[6]/div/div/ul/li[4]/a/span")).click();
//        driver.findElement(By.name("quantity_adult")).clear();
//        driver.findElement(By.name("quantity_adult")).sendKeys("1");
//        driver.findElement(By.name("quantity_adult")).clear();
//        driver.findElement(By.name("quantity_adult")).sendKeys("3");
        Thread.sleep(1000);


        WebElement bookNowButton = driver.findElement(By.xpath("//input[@value='Book now']"));
        WebDriverWait waitForImage = new WebDriverWait(driver, 15);
        Assert.assertTrue(bookNowButton.isDisplayed());
        waitForImage.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Book now']")));
        bookNowButton.click();

//        WebDriverWait title = new WebDriverWait(driver, 15);
//        title.until(ExpectedConditions.titleIs("Cart - EuroTrip Adventures"));
//        assertEquals(driver.getTitle(), "Cart - EuroTrip Adventures");
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/main/div/div[3]/div/div/div/a")).click();
//        assertEquals(driver.getTitle(), "Checkout - EuroTrip Adventures");
        driver.findElement(By.id("billing_last_name")).click();
        driver.findElement(By.id("billing_last_name")).clear();
        driver.findElement(By.id("billing_last_name")).sendKeys("test");
        WebElement testDropDown = driver.findElement(By.id("select2-billing_howdidyouhear-container"));
        testDropDown.click();
        List<WebElement> options = driver.findElements(By.xpath("//ul[@class='select2-results__options']/li"));
        for (WebElement opt : options) {
            if (opt.getText().equals("Friend referral")) {
                opt.click();
                return;
            }
        }
    }

}

package com.qa.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage extends BasePage {

    //Constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    private static final String CLICK_DOC_TITLE = "CLICKDOC";
    private final String HOME_PAGE_URL = "https://demo.clickdoc.de/cms-de/";

    // Locators
    By unKnownBar = By.xpath("//p");
    By locIFrame = By.id("iframeDialog");
    By loginField = By.id("mat-input-0");
    By passwordField = By.id("mat-input-1");
    By loginButton = By.xpath("//div[2]/button");
    By logoutButton = By.xpath("//a[2]/div/span[2]");
    By profileImage = By.xpath("(//img[@alt='avatar-picture'])[2]");
    By menuItem = By.xpath("//*[@id=\"menu-item-2004\"]/a");

    @Step("Wait For LoginPage...")
    public String goToLoginPageAndFetchTitle() {
        driver.get(HOME_PAGE_URL);
        waitForTitle(CLICK_DOC_TITLE, 15);
        driver.findElement(menuItem).click();
        waitVisibility(locIFrame, 15);
        driver.switchTo().frame(driver.findElement(locIFrame));
        waitVisibility(loginField, 15);
        return fetchPageTitle();
    }

    @Step("Perform Login with Username: '{0}' and password:'{1}'")
    public void login(String username, String password) {
        driver.findElement(loginField).clear();
        driver.findElement(loginField).sendKeys(username);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    @Step("Invalid Login Scenario validation... ")
    public String invalidLogin() {
        waitVisibility(unKnownBar, 15);
        return readText(unKnownBar);
    }

    @Step("Logout from ClickDoc Website by clicking on ProfileImage: '{logoutImage}' ")
    public void logOut(By logoutImage) {
        waitVisibility(logoutImage, 15);
        Assert.assertTrue(driver.findElement(logoutImage).isDisplayed());
        driver.findElement(logoutImage).click();
        driver.findElement(logoutButton).click();
    }

    @Step("secondUserLogin... ")
    public void secondUserLogin() {
        driver.findElement(loginField).clear();
        driver.findElement(loginField).sendKeys("dirk.nonn@cgm.com#1111");
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys("recruitingTest1!");
        driver.findElement(loginButton).click();
        waitForTitle("Mein Profil | CLICKDOC",15);
        this.logOut(profileImage);
    }

}

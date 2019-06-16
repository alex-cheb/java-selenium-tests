package objects;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Anotepad {

    private static final By email = By.id("registerEmail");
    private static final By passw = By.id("password");
    private static final By submit = By.id("submit");
    private static final By delUser = By.id("confirmDeleteAccount");
    private static final By delButton = By.id("btnDeleteAccount");
    private static final By settingsEmail = By.id("email");
    private static final By noteTitle = By.id("edit_title");
    private static final By saveTitleButton = By.xpath("//*[@id='btnSaveNote']");
    private static final By searchLine = By.xpath("//input[@id = 'search']");
    private static final By searchButton = By.xpath("//button[@id = 'search']");
    private static final By loginEmail = By.id("loginEmail");
    private static final By noteSearch = By.className("note_content");
    private static final By errorDiv = By.xpath("//body/div");



    private WebDriver driver;
    private WebDriverWait wait;

    public Anotepad(WebDriver driver){
        this.driver = driver;
        //this.wait = WebDriverWait(this.driver, 5);

    }

    public String errorPage(){
        return driver.findElement(errorDiv).getText();
    }

    public Anotepad openLoginPage(){
        System.out.println("Go to login/register page");
        driver.get("https://anotepad.com/create_account");
        return this;
    }
    public Anotepad openHomePage(){
        System.out.println("Moving to home page");
        driver.get("https://anotepad.com/");
        return this;
    }
    public Anotepad addTitle(String title){
        System.out.println("Adding title");
        System.out.println(title);
        driver.findElement(noteTitle).sendKeys(title);
        return this;

    }
    public Anotepad addSearchQuery(String query){
        System.out.println(query);
        System.out.println("Fill in search");
        driver.findElement(searchLine).sendKeys(query);
        return this;
    }
    public Anotepad startSearch(){
        System.out.println("Go! Search");
        driver.findElement(searchButton).click();
        return this;
    }
    public Anotepad saveNote(){
        System.out.println("Save note");
        driver.findElement(saveTitleButton).click();

        return this;
    }
    public String searchNote() {
        return driver.findElement(noteSearch).getText();
    }
    public Anotepad enterUserName(String name){
        System.out.println("Fill in Username");
        driver.findElement(email).sendKeys(name);
        return this;

    }

    public Anotepad enterPassword(String pwd){
        System.out.println("Fill in password");
        driver.findElement(passw).sendKeys(pwd);
        return this;

    }
    public  Anotepad createUser(){
        System.out.println("Submit create user");
        driver.findElement(submit).click();
        return this;
    }
    public Anotepad goToSettings(){
        System.out.println("Move to settings");
        driver.get("https://anotepad.com/settings");
        return this;
    }
    public String getSettingsEmail(){
        System.out.println("get settings email started");
        return driver.findElement(settingsEmail).getAttribute("value");

            }
    public Anotepad  login(String userMail, String userPass){
        driver.findElement(loginEmail).sendKeys(userMail);
        System.out.println(driver.findElement(loginEmail));
        List<WebElement> passwordField = (ArrayList) driver.findElements(passw);
        System.out.println(passwordField.get(1));
        passwordField.get(1).sendKeys(userPass);
        List<WebElement> buttons = (ArrayList) driver.findElements(submit);
        buttons.get(1).click();
        this.wait = new WebDriverWait(driver, 15);


        return this;

    }
    public String nothingFound(){
        return "No result found. Please try again.";
    }

    public String errorMsg(){
        return "An error occurred while processing your request. Please contact Support@Anotepad.com for help.";
    }

    public  Anotepad deleteUser(){
        System.out.println("delete user method called");
        driver.findElement(delUser).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnDeleteAccount")));
        driver.findElement(delButton).click();
        //System.out.println();
        return this;

    }
}

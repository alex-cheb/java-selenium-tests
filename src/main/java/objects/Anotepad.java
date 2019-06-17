package objects;

import io.qameta.allure.Step;
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
    private static final By delUser = By.id("confirmDeleteAccount");
    private static final By settingsEmail = By.id("email");
    private static final By noteTitle = By.id("edit_title");
    private static final By searchLine = By.xpath("//input[@id = 'search']");
    private static final By loginEmail = By.id("loginEmail");
    private static final By noteSearch = By.className("note_content");
    private static final By errorDiv = By.xpath("//body/div");
    private static final By editContent = By.id("edit_textarea");
    private static final By previewTitle = By.xpath("//h2[@class = 'note_title']/a");

    private static final By submit = By.id("submit");
    private static final By delButton = By.id("btnDeleteAccount");
    private static final By saveTitleButton = By.id("btnSaveNote");
    private static final By searchButton = By.xpath("//button[@id = 'search']");
    private static final By delNote = By.className("delete");

    private WebDriver driver;
    private WebDriverWait wait;

    public Anotepad(WebDriver driver){
        this.driver = driver;
        //this.wait = WebDriverWait(this.driver, 5);

    }
    @Step
    public Anotepad openLoginPage(){
        System.out.println("Go to login/register page");
        driver.get("https://anotepad.com/create_account");
        return this;
    }
    @Step
    public Anotepad  login(String userMail, String userPass){
        driver.findElement(loginEmail).sendKeys(userMail);
        System.out.println(driver.findElement(loginEmail));
        List<WebElement> passwordField = (ArrayList) driver.findElements(passw);
        System.out.println(passwordField.get(1));
        passwordField.get(1).sendKeys(userPass);
        List<WebElement> buttons = (ArrayList) driver.findElements(submit);
        buttons.get(1).click();
        this.wait = new WebDriverWait(driver, 5);


        return this;

    }

    @Step
    public Anotepad openHomePage(){
        System.out.println("Go to home page");
        driver.get("https://anotepad.com/");
        return this;
    }
    @Step
    public Anotepad addTitle(String title){
        System.out.println(title + " Adding title");
        driver.findElement(noteTitle).sendKeys(title);
        return this;
    }
    @Step
    public Anotepad setContent(String content){
        System.out.println(content);
        driver.findElement(editContent).clear();
        driver.findElement(editContent).sendKeys(content);
        return this;

    }
    @Step
    public Anotepad saveNote(){
        System.out.println("Save note");
        driver.findElement(saveTitleButton).click();
        this.wait = new WebDriverWait(driver, 5);
        return this;
    }

    public String errorPage(){
        return driver.findElement(errorDiv).getText();
    }

    public String existingNoteConten(){
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut";

    }
   @Step
    public Anotepad addSearchQuery(String query){
        System.out.println(query + "Fill in search");
        driver.findElement(searchLine).clear();
        driver.findElement(searchLine).sendKeys(query);
        return this;
    }
    @Step
    public Anotepad startSearch(){
        //System.out.println("Go! Search");
        driver.findElement(searchButton).click();
        return this;
    }

    public String searchNote() {
        //returns note content of the found note in preview
        return driver.findElement(noteSearch).getText();
    }
    @Step
    public Anotepad goToNote(){
        driver.findElement(previewTitle).click();
        return this;
    }
    @Step
    public Anotepad deleteNote(){
        driver.findElement(delNote).click();
        wait.until(ExpectedConditions.alertIsPresent());
        System.out.println("Alert about note delete");
        driver.switchTo().alert().accept();
        this.wait = new WebDriverWait(driver, 15);
        return this;
    }

    public String nothingFound(){
        return "No result found. Please try again.";
    }

    public String errorMsg(){
        return "An error occurred while processing your request. Please contact Support@Anotepad.com for help.";
    }
 //Used in create account test case
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
        //used in createaccount
        System.out.println("get settings email started");
        return driver.findElement(settingsEmail).getAttribute("value");

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

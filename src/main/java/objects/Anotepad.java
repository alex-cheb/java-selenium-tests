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
    private static final By settingsButton = By.xpath("//a[@href = '/settings']");


    public static final String registeredUserEmail = "2@e.ee";
    public static final String registeredUserPassword = "2";
    public static final String newUserEmail = "3@r.com";
    public static final String newUserPassword = "VeryStrongPass11";


    public static final String nothingFound = "No result found. Please try again.";
    public static final String nonExistingNoteTitle = "Note that does not exist";
    public static final String existingNoteContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut";
    public static final String existingNoteTitle = "title";
    public static final String existingNoteTitleQuery = "Title";
    public static final String existingNoteContentQuery = "Lorem";
    public static final String noteContainingHtmlInjection = "'><a>";
    private WebDriver driver;
    private WebDriverWait wait;

    public Anotepad(WebDriver driver){
        this.driver = driver;
        //this.wait = WebDriverWait(this.driver, 5);
        }
     @Step
    public Anotepad openLoginPage(){
         System.out.println("Go to Login/Register");
         driver.get("https://anotepad.com/create_account");
         return this;
    }
    @Step
    public Anotepad login(String userMail, String userPass){
        driver.findElement(loginEmail).sendKeys(userMail);
        List<WebElement> passwordField = (ArrayList) driver.findElements(passw);
        passwordField.get(1).sendKeys(userPass);
        List<WebElement> buttons = (ArrayList) driver.findElements(submit);
        buttons.get(1).click();
        this.wait = new WebDriverWait(driver, 5);
        return this;
    }
    @Step
    public Anotepad openHomePage(){
        System.out.println("Go to Home Page");
        driver.get("https://anotepad.com/");
        return this;
    }

    @Step
    public Anotepad addTitle(String title){
        System.out.println(title + " Adding title");
        driver.findElement(noteTitle).clear();
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
    @Step
    public Anotepad setSearchQuery(String query){
        System.out.println(query + "Fill in search");
        driver.findElement(searchLine).clear();
        driver.findElement(searchLine).sendKeys(query);
        return this;
    }
    @Step
    public Anotepad startSearch(){
        System.out.println("Starting Search");
        driver.findElement(searchButton).click();
        return this;
    }
    public String foundNoteContent(){
        wait.until(ExpectedConditions.urlContains("keyword"));
        return driver.findElement(noteSearch).getText();
    }
    @Step
    public Anotepad goToNote(){
        System.out.println("Moving to the note");
        driver.findElement(previewTitle).click();
        return this;
    }
    @Step
    public Anotepad deleteNote(){
        driver.findElement(delNote).click();
        wait.until(ExpectedConditions.alertIsPresent());
        System.out.println("Alert about note delete");
        driver.switchTo().alert().accept();
        //this.wait = new WebDriverWait(driver, 5);
        return this;
    }
    @Step
    public Anotepad enterCredentials(String username, String password){
        driver.findElement(email).clear();
        driver.findElement(email).sendKeys(newUserEmail);
        System.out.println("entering login for a new user");
        driver.findElement(passw).clear();
        driver.findElement(passw).sendKeys(newUserPassword);
        System.out.println("entering password for a new user");
        return this;
    }
    public Anotepad creatingUser(){
        driver.findElement(submit).click();
        return this;
    }
    public Anotepad deleteUser(){
        driver.get("https://anotepad.com/settings");
        System.out.println("Deleting created user");
        wait.until(ExpectedConditions.elementToBeClickable(delUser));
        driver.findElement(delUser).click();
        System.out.println("Check button");
        wait.until(ExpectedConditions.elementToBeClickable(delButton));
        driver.findElement(delButton).click();
        return this;

    }
    public String settingsB(){
       return driver.findElement(settingsButton).getText();
    }

}


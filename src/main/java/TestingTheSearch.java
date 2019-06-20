import io.github.bonigarcia.wdm.WebDriverManager;
import objects.Anotepad;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static objects.Anotepad.registeredUserEmail;
import static objects.Anotepad.registeredUserPassword;


public class TestingTheSearch {
    WebDriver driver;
    Anotepad np;


    /*@Before
    public void openBrowser(){
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        np = new Anotepad(driver);
    }*/
    @Before
    public void setUpEnvironment(){
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        np = new Anotepad(driver);
        np.openLoginPage().login(registeredUserEmail, registeredUserPassword);
        np.addTitle(np.existingNoteTitle).setContent(np.existingNoteContent).saveNote();
    }
    
    /*public void logIn(){
        np.openLoginPage().login(registeredUserEmail, registeredUserPassword);
    }

    public void createNote(){
        np.addTitle(np.existingNoteTitle).setContent(np.existingNoteContent).saveNote();
    }
*/
    @After
    public void closeBrowser(){
        driver.quit();
    }

    @Test
    public void nonExistingNoteSearch(){
        np.setSearchQuery(np.nonExistingNoteTitle).startSearch();
        Assert.assertEquals(np.nothingFound, np.foundNoteContent());

    }
    /*@Test
    public void existingNoteSearch(){
        np.setSearchQuery(np.existingNoteTitleQuery).startSearch();
        Assert.assertEquals(np.existingNoteContent, np.foundNoteContent());
        np.goToNote().deleteNote();

    }
    @Test
    public void injectionSearch(){
        np.setSearchQuery(np.noteContainingHtmlInjection).startSearch();
        Assert.assertEquals(np.nothingFound, np.foundNoteContent());

    }*/

}

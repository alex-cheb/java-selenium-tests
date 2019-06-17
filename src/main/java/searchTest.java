import io.github.bonigarcia.wdm.WebDriverManager;
import objects.Anotepad;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class searchTest {
    WebDriver  driver;
    Anotepad np;

    //Not existing note
    String validSearch = "Note";
    //Search that crashes app
    String injectionSearch = "'><a>";
    //Existing Note
    String existingNote = "title";
    String existingNoteSearch = "Title";
    //existing user data
    String validEmail = "2@e.ee";
    String validPass = "2";
    @Before
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        np = new Anotepad(driver);
    }

    @After
    public void closeBrowser(){
        driver.quit();
    }

    @Test
    @DisplayName("GL-324:F-210: Search")
    public void searchingTest(){
        //logging in to be able to use search instantly
        np.openLoginPage().login(validEmail, validPass);
        //creating note
        np.addTitle(existingNote).setContent(np.existingNoteConten());
        np.saveNote();
        //search queries block
        np.addSearchQuery(validSearch).startSearch();//non existing note
        System.out.println(validSearch);
        Assert.assertEquals(np.nothingFound(), np.searchNote());
        np.openHomePage();
        np.addSearchQuery(injectionSearch).startSearch();//application returns error
        System.out.println(injectionSearch);
        Assert.assertEquals(np.errorMsg(), np.errorPage());
        np.openHomePage();
        np.addSearchQuery(existingNoteSearch).startSearch();//search for existing note and check its content
        System.out.println(existingNoteSearch);
        Assert.assertEquals(np.existingNoteConten(), np.searchNote());
        np.addSearchQuery("Lorem").startSearch();
        Assert.assertEquals(np.existingNoteConten(), np.searchNote());
        np.goToNote().deleteNote();//delete existing note



    }

}

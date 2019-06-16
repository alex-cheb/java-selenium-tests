import io.github.bonigarcia.wdm.WebDriverManager;
import objects.Anotepad;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class searchTest {
    WebDriver  driver;
    Anotepad np;

    String validSearch = "Note";
    String injectionSearch = "'><a>";
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
    public void searchingTest(){
        np.openLoginPage().login("2@e.ee","2");
        //np.openHomePage().addTitle(validSearch).saveNote();
        np.addSearchQuery(validSearch).startSearch();
        Assert.assertEquals(np.nothingFound(), np.searchNote());
        np.openHomePage();
        np.addSearchQuery(injectionSearch).startSearch();
        Assert.assertEquals(np.errorMsg(), np.errorPage());


    }

}

import io.github.bonigarcia.wdm.WebDriverManager;
import objects.Anotepad;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateUserPositiveTest {
    WebDriver driver;
    Anotepad np;
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
    public void creatingNewUser(){
        np.openLoginPage().enterCredentials(np.newUserEmail, np.newUserPassword).creatingUser();
        Assert.assertEquals("Settings", np.settingsB());
        np.deleteUser();


    }




}

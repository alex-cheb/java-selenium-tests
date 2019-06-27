import io.github.bonigarcia.wdm.WebDriverManager;
import objects.Anotepad;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateUserBlankFieldsTest {

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
        public void creatingNewUserBlankPass(){
            np.openLoginPage().enterCredentials(np.newUserEmail, "").creatingUser();
            Assert.assertEquals("Settings", np.settingsBtn());
            np.deleteUser();


        }
        public void creatingNewUserBlankMail(){
            np.openLoginPage().enterCredentials("", np.newUserPassword).creatingUser();
            Assert.assertEquals("Settings", np.settingsBtn());
            np.deleteUser();


        }
}

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

import static objects.Anotepad.*;

public class LoginPositiveTest {

    WebDriver driver;
    Anotepad np;


    @Before
    public void openBrowser(){
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        np = new Anotepad(driver);
    }
    @After
    public void closeBrowser()
    {
        driver.quit();

    }
    @Test
    public void logIn(){
        np.openLoginPage().login(registeredUserValidMail, registeredUserValidPassword);
        Assert.assertEquals("Settings", np.settingsBtn());

    }
}

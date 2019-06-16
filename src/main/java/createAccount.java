import io.github.bonigarcia.wdm.WebDriverManager;
import objects.Anotepad;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class createAccount {
    WebDriver driver;
    Anotepad np;

    String email = "4@e.rr";
    String pwd = "veryStrongPwd1";

    @Before
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        np = new Anotepad(driver);
    }
    /*@After
    public void closeBrowser(){
        driver.quit();
    }*/
    @Test
    public void createUserTest(){
        np.openLoginPage().enterUserName(email).enterPassword(pwd).createUser();
        np.goToSettings();
        Assert.assertEquals(email, np.getSettingsEmail());

    }


}

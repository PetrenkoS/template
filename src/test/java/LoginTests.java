import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    @Test
    public void loginSucceedTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginBtn();
        Assert.assertTrue(loginPage.isAvatarDisplayed());
    }


    @Test
    public void loginEmptyPasswordTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(email);
        loginPage.clickLoginBtn();
        Assert.assertTrue(loginPage.isSubmitLoginBtnDisplayed());
    }


}

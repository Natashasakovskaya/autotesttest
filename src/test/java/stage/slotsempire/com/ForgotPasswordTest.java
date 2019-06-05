package stage.slotsempire.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.ChromeDriverLoader;
import util.WebDriverExtension;

/**
 * @see "https://habr.com/ru/post/114772/"
 * @see "http://internetka.in.ua/xpath-start-part2/"
 */
public class ForgotPasswordTest
{
    private String SITE_URL = "https://stage.slotsempire.com/";

    @BeforeClass
    public void beforeClass() {
        ChromeDriverLoader.loadConfig();
    }

    @Test
    public void testFailedRequiredField()
    {
        WebDriver webPageInBrowser = WebDriverExtension.openWebPage(this.SITE_URL);

        WebElement loginBtn = webPageInBrowser.findElement(By.xpath("//a[@id='login-btn']"));
        loginBtn.click();

        By xpathSelectorShowForgotPassword = By.xpath("//div[@class='other-forms-btns']//span[contains(@onclick, 'showForgotPassword' )]");
        webPageInBrowser.findElement(xpathSelectorShowForgotPassword).click();

        WebElement okBtn = webPageInBrowser.findElement(By.id("forgot-password-submit"));
        okBtn.click();


        // "//input[@id='ii']/..//div[@class='t1']" === "//input[@id='ii']/parent::*//div[@class='t1']"
        Assert.assertEquals(
                webPageInBrowser.findElement(By.xpath("//input[@id='p-username']/parent::*//div[@class='s-form-error-message']")).getText(),
                "This field is required."
        );
        webPageInBrowser.close();
    }

    @Test(dataProvider="dataFailedValidationLogin")
    public void testFailedValidation(String username, String expectedError)
    {
        WebDriver webPageInBrowser = WebDriverExtension.openWebPage(this.SITE_URL);

        WebElement loginBtn = webPageInBrowser.findElement(By.xpath("//a[@id='login-btn']"));
        loginBtn.click();

        By xpathSelectorShowForgotPassword = By.xpath("//div[@class='other-forms-btns']//span[contains(@onclick, 'showForgotPassword' )]");
        webPageInBrowser.findElement(xpathSelectorShowForgotPassword).click();

        webPageInBrowser.findElement(By.id("p-username")).sendKeys(username);
        WebElement okBtn = webPageInBrowser.findElement(By.id("forgot-password-submit"));
        okBtn.click();

        WebDriverExtension.waitBrowserLoaded(2000);
        Assert.assertEquals(
                webPageInBrowser.findElement(By.cssSelector(".s-form-subtitle.s-form-subtitle-error.reddish")).getText(),
                expectedError
        );
        webPageInBrowser.close();
    }

    @DataProvider(name="dataFailedValidationLogin")
    public Object[][] dataFailedValidationLogin() {
        return new Object[][]{
            {
                "1",
                "Sorry. This heroname does not match any account."
            },
            {
                "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww",
                "Sorry. This heroname does not match any account."
            },
            {
                "FDFDFDFDFDFD DDFDFDF @@@dfsdfsd",
                "We beg your pardon, Hero, but something went wrong. Please try again later."
            }
        };
    }
}

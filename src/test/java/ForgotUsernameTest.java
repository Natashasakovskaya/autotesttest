import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.ChromeDriverLoader;
import util.WebDriverExtension;

public class ForgotUsernameTest {
    private String SITE_URL = "https://stage.slotsempire.com/";

    @BeforeClass
    public void beforeClass() {
        ChromeDriverLoader.loadConfig();
    }

    @Test
    public void testEmptyUsername()
    {
        WebDriver webPageInBrowser = WebDriverExtension.openWebPage(this.SITE_URL);

        WebElement loginBtn = webPageInBrowser.findElement(By.xpath("//a[@id='login-btn']"));
        loginBtn.click();

        By xpathLink = By.xpath("//div[@class='other-forms-btns']//span[contains(@onclick, 'showForgotUsernameform' )]");
        webPageInBrowser.findElement(xpathLink).click();

        WebElement okBtn = webPageInBrowser.findElement(By.xpath("//*[@id='forgot-username-submit']"));
        okBtn.click();

        WebElement divWrapper = webPageInBrowser.findElement(By.xpath("//input[@id='f-email']/parent::div"));


        Assert.assertTrue(WebDriverExtension.hasClass(divWrapper, "with-error"));
        webPageInBrowser.close();
    }

    @Test
    public void testUndefinedEmail()
    {
        WebDriver webPageInBrowser = WebDriverExtension.openWebPage(this.SITE_URL);

        WebElement loginBtn = webPageInBrowser.findElement(By.xpath("//a[@id='login-btn']"));
        loginBtn.click();

        By xpathLink = By.xpath("//div[@class='other-forms-btns']//span[contains(@onclick, 'showForgotUsernameform' )]");
        webPageInBrowser.findElement(xpathLink).click();

        webPageInBrowser.findElement(By.id("f-email")).sendKeys("xxxx@uni.com");

        WebElement okBtn = webPageInBrowser.findElement(By.xpath("//*[@id='forgot-username-submit']"));
        okBtn.click();

        WebDriverExtension.waitBrowserLoaded(2000);
        WebElement divWrapper = webPageInBrowser.findElement(By.xpath("//input[@id='f-email']/parent::div"));
        String text = webPageInBrowser.findElement(By.xpath("//*[@id='forgot-username-form-main']/../*/div[@class='s-form-subtitle']")).getText();


        Assert.assertFalse(WebDriverExtension.hasClass(divWrapper, "with-error"));
        Assert.assertEquals(text, "Sorry. We can't find any account with this email.");
        webPageInBrowser.close();
    }
}

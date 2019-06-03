import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.ChromeDriverLoader;
import util.WebDriverExtension;

/**
 * Check language switcher
 */
public class ChangeLanguageTest
{
    private String SITE_URL = "https://stage.slotsempire.com/";

    @BeforeClass
    public void beforeClass() {
        ChromeDriverLoader.loadConfig();
    }

    @Test
    public void testTopBtnChangeLanguage()
    {
        WebDriver webPageInBrowser = WebDriverExtension.openWebPage(this.SITE_URL);

        By xpathSelector = By.xpath("//*[contains(@class, 'lang-title')]");
        WebElement btn = webPageInBrowser.findElement(xpathSelector);
        String currentLang = btn.getText();
        btn.click();

        By xpathSelectorLangNo = By.xpath("//*[contains(@class, 'lang-title')]/parent::*//*[contains(@class, 'no')]");
        WebElement noBtn = webPageInBrowser.findElement(xpathSelectorLangNo);
        noBtn.click();

        WebDriverExtension.waitBrowserLoaded(500);
        Assert.assertEquals(currentLang, "EN");
        Assert.assertEquals(webPageInBrowser.getCurrentUrl(), this.SITE_URL + "no/");
        Assert.assertNotEquals(currentLang, webPageInBrowser.findElement(xpathSelector).getText());

        webPageInBrowser.close();
    }
}

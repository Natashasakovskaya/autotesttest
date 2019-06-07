package bovegas.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.ChromeDriverLoader;
import util.WebDriverExtension;

public class SwitcherLangTest
{
    private String SITE_URL = "https://bovegas.com/en";

    @BeforeClass
    public void beforeClass() {
        ChromeDriverLoader.loadConfig();
    }

    @Test(dataProvider="dataTopSwitcherBoVegasCasino")
    public void testTopSwitcherBoVegasCasino(String language, String expectedUrl)
    {
        WebDriver webPageInBrowser = WebDriverExtension.openWebPage(this.SITE_URL);
        By xpathSelector = By.xpath("//*[contains(@class, 'language-switcher')]");
        WebElement btn = webPageInBrowser.findElement(xpathSelector);
        String currentLang = btn.getText();
        btn.click();

        String selectorStr = "//*[contains(@class, 'language-switcher')]/parent::*//a[contains(@href, '/" + language  + "')]";

        By xpathSelectorLangNo = By.xpath(selectorStr);
        WebElement noBtn = webPageInBrowser.findElement(xpathSelectorLangNo);
        noBtn.click();

        Assert.assertEquals(webPageInBrowser.getCurrentUrl(), expectedUrl);
        Assert.assertNotEquals(currentLang, webPageInBrowser.findElement(xpathSelector).getText());

        webPageInBrowser.close();

    }

    @DataProvider(name="dataTopSwitcherBoVegasCasino")
    public Object[][] dataTopSwitcherBoVegasCasino() {
        return new Object[][]{
                {
                        "ar",
                        "https://bovegas.com/ar"
                },
                {
                        "fr",
                        "https://bovegas.com/fr"
                },
        };
    }

}

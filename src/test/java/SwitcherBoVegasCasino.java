import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.ChromeDriverLoader;
import util.WebDriverExtension;

public class SwitcherBoVegasCasino

{

    private String SITE_URL = "https://bovegas.com/en";
    @BeforeClass
    public void beforeClass() {
        ChromeDriverLoader.loadConfig();
    }

    @Test
    public void testTopSwitcherBoVegasCasino() {
        WebDriver webPageInBrowser = WebDriverExtension.openWebPage(this.SITE_URL);
        By xpathSelector = By.xpath("//*[contains(@class, 'language-switcher')]");
        WebElement btn = webPageInBrowser.findElement(xpathSelector);
        String currentLang = btn.getText();
        btn.click();

        By xpathSelectorLangNo = By.xpath("//*[contains(@class, 'class=\"language-switcher\"')]/parent::*//*[contains(@class, '/fr')]");
        WebElement noBtn = webPageInBrowser.findElement(xpathSelectorLangNo);
        noBtn.click();

}
}

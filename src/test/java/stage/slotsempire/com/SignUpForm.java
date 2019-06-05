package stage.slotsempire.com;

import org.openqa.selenium.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;
import util.ChromeDriverLoader;
import util.WebDriverExtension;

public class SignUpForm {

    private String SITE_URL = "https://stage.slotsempire.com/";

    @Test
    public void testSuccessSignUp() {

        WebDriver webPageInBrowser = WebDriverExtension.openWebPage(this.SITE_URL);

        WebElement loginBtn = webPageInBrowser.findElement(By.cssSelector("#register-btn"));
        loginBtn.click();

        WebElement formInputUsername = webPageInBrowser.findElement(By.cssSelector("#g-username"));
        formInputUsername.sendKeys("amarilis1777");

        WebElement formInputPassword = webPageInBrowser.findElement(By.cssSelector("#g-password"));
        formInputPassword.sendKeys("eeededede");

        WebElement formInputConfirnPassword = webPageInBrowser.findElement(By.cssSelector("#g-password2"));
        formInputConfirnPassword.sendKeys("eeededede");

        WebElement formInputEmail = webPageInBrowser.findElement(By.cssSelector("#g-email"));
        formInputEmail.sendKeys("qawsweeww@gmail.com");

        WebElement nextStepBtn = webPageInBrowser.findElement(By.cssSelector("#go-to-second"));
        WebDriverExtension.doubleClickIfItNeed(nextStepBtn, 3000);

        WebElement formFirstName = webPageInBrowser.findElement(By.cssSelector("#g-firstname"));
        formFirstName.sendKeys("amaris");

        WebElement formLastName = webPageInBrowser.findElement(By.cssSelector("#g-lastname"));
        formLastName.sendKeys("amaris17707");

        // @see https://www.softwaretestinghelp.com/selenium-select-class-selenium-tutorial-13/
        Select birthdateMonth = new Select (webPageInBrowser.findElement(By.id("g-month")));
        birthdateMonth.selectByVisibleText("August");

        Select birthdateDay = new Select(webPageInBrowser.findElement(By.id("g-day")));
        birthdateDay.selectByValue("22");

        Select birthdateYeah = new Select(webPageInBrowser.findElement(By.id("g-year")));
        birthdateYeah.selectByIndex(5);

        WebElement genderClick = webPageInBrowser.findElement(By.cssSelector("[for='g-female']"));
        genderClick.click();

        /*
         * CSS селектор [for="g-female"] выбирает элемент у которого аттрибут "for" заполнен значением "g-female"
         * (слеш перед кавычкой - экранирование кавычек)
         */

        WebElement nextStepBtn2 = webPageInBrowser.findElement(By.id("go-to-last"));
        nextStepBtn2.click();

    }

    @BeforeClass
    public void beforeClass() {
        ChromeDriverLoader.loadConfig();
    }
}

package stage.slotsempire.com;

import org.openqa.selenium.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;
import util.ChromeDriverLoader;
import util.WebDriverExtension;

public class SignUpFormTest {

    private String SITE_URL = "https://stage.slotsempire.com/";

    @Test
    public void testSuccessSignUp() {

        WebDriver webPageInBrowser = WebDriverExtension.openWebPage(this.SITE_URL);

        WebElement loginBtn = webPageInBrowser.findElement(By.cssSelector("#register-btn"));
        loginBtn.click();

        this.fillFirstStep(webPageInBrowser);
        this.fillSecondStep(webPageInBrowser);
        this.fillThirdStep(webPageInBrowser);
    }

    private void fillThirdStep(WebDriver signUpForm)
    {
        Select country = new Select (signUpForm.findElement(By.xpath("//select[@name='g-country']")));
        country.selectByVisibleText("United States");

        Select currency = new Select (signUpForm.findElement(By.xpath("//select[@name='g-currency']")));
        currency.selectByVisibleText("US Dollar");

        WebElement zip = signUpForm.findElement(By.xpath("//input[@name='g-zip']"));
        zip.sendKeys("1111");

        WebElement address = signUpForm.findElement(By.xpath("//input[@name='g-address']"));
        address.sendKeys("Evo str");

        WebElement phone = signUpForm.findElement(By.xpath("//input[@name='g-phone']"));
        phone.sendKeys("1235555555");

        WebDriverExtension.waitBrowserLoaded(2000);
        WebElement completeBtn = signUpForm.findElement(By.xpath("//button[@id='complete']"));
        completeBtn.click();
    }

    private void fillSecondStep(WebDriver signUpForm)
    {
        WebDriverExtension.waitBrowserLoaded(1000);
        WebElement formFirstName = signUpForm.findElement(By.cssSelector("#g-firstname"));
        formFirstName.sendKeys("amaris");

        WebElement formLastName = signUpForm.findElement(By.cssSelector("#g-lastname"));
        formLastName.sendKeys("amaris17707");

        // @see https://www.softwaretestinghelp.com/selenium-select-class-selenium-tutorial-13/
        Select birthdateMonth = new Select (signUpForm.findElement(By.id("g-month")));
        birthdateMonth.selectByVisibleText("August");

        Select birthdateDay = new Select(signUpForm.findElement(By.id("g-day")));
        birthdateDay.selectByValue("22");

        Select birthdateYeah = new Select(signUpForm.findElement(By.id("g-year")));
        birthdateYeah.selectByIndex(5);

        WebElement genderClick = signUpForm.findElement(By.cssSelector("[for='g-female']"));
        genderClick.click();

        /*
         * CSS селектор [for="g-female"] выбирает элемент у которого аттрибут "for" заполнен значением "g-female"
         * (слеш перед кавычкой - экранирование кавычек)
         */

        WebElement nextStepBtn2 = signUpForm.findElement(By.id("go-to-last"));
        nextStepBtn2.click();
    }


    private void fillFirstStep(WebDriver signUpForm)
    {
        String username = "qa" + (Long.toString(System.currentTimeMillis())).substring(5);

        WebElement formInputUsername = signUpForm.findElement(By.cssSelector("#g-username"));
        formInputUsername.sendKeys(username);

        WebElement formInputPassword = signUpForm.findElement(By.cssSelector("#g-password"));
        formInputPassword.sendKeys("eeededede");

        WebElement formInputConfirnPassword = signUpForm.findElement(By.cssSelector("#g-password2"));
        formInputConfirnPassword.sendKeys("eeededede");

        WebElement formInputEmail = signUpForm.findElement(By.cssSelector("#g-email"));
        formInputEmail.sendKeys(username + "@gmail.com");

        WebDriverExtension.waitBrowserLoaded(50);
        WebElement nextStepBtn = signUpForm.findElement(By.cssSelector("#go-to-second"));
        try {
            nextStepBtn.click();
        } catch (WebDriverException exception) {
            formInputUsername.sendKeys("f" + formInputUsername.getText());
            formInputEmail.sendKeys("f" + formInputUsername.getText());
            WebDriverExtension.waitBrowserLoaded(3000);
            nextStepBtn.click();
        }
    }

    @BeforeClass
    public void beforeClass() {
        ChromeDriverLoader.loadConfig();
    }
}

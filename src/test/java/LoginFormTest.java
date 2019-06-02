import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.ChromeDriverLoader;

public class LoginFormTest {

    @BeforeClass
    public void beforeClass() {
        ChromeDriverLoader.loadConfig();
    }

    @Test(dataProvider="dataFailedValidationLogin")
    public void testFailedValidationLogin(
            String username,
            String password,
            String expectedFormError,
            String expectedUsernameError,
            String expectedPasswordError
    ) {
        WebDriver webPageInBrowser = this.openWebPage();

        WebElement loginBtn = webPageInBrowser.findElement(By.cssSelector("#login-btn"));
        loginBtn.click();


        WebElement formInputUsername = webPageInBrowser.findElement(By.cssSelector("#l-username"));
        formInputUsername.sendKeys(username);

        WebElement formInputPassword = webPageInBrowser.findElement(By.cssSelector("#l-password"));
        formInputPassword.sendKeys(password);


        WebElement formLoginBtn = webPageInBrowser.findElement(By.cssSelector("#login"));
        formLoginBtn.click();

        this.waitBrowserLoaded();

        if (!expectedFormError.equals("")) { //Логічна перевірка на пустоту вхідного аргумента
            //Якщо вхідний аргумент не пучтий виконується ця частина коду
            WebElement errorForm = webPageInBrowser.findElement(By.cssSelector("#login-form-holder .s-form-subtitle"));

            /**
             * "#login-form-holder .s-form-subtitle" - композитный CSS селектор, который состоит из двух селекторов,
             * которые должны быть в двух иерархических элементах:
             *  - первый элемент (родительский) должен иметь идентификатор "login-form-holder"
             *  - второй (чайлд) должен иметь класс "ls-form-subtitle"
             */
            Assert.assertEquals(errorForm.getText(), expectedFormError);
        }

        if (!expectedPasswordError.equals("")) {
            WebElement errorPassword = webPageInBrowser.findElement(By.cssSelector(".with-error.ih-password .s-form-error-message"));
            /**
             * ".with-error.ih-password .s-form-error-message" - композитный CSS селектор, который состоит
             * из трёх селекторов (НО В ДВУХ ЭЛЕМЕНТАХ), которые должны быть в двух иерархических элементах:
             *  - первый элемент (родительский) должен иметь сразу два класса "with-error" и "ih-password"
             *  - второй (чайлд) должен иметь класс "s-form-error-message"
             */
            Assert.assertEquals(errorPassword.getText(), expectedPasswordError);

        }

        /* ----Рівень розуміння кода Наташою--- */
        if (!expectedUsernameError.equals("")) {
            WebElement usernameError = webPageInBrowser.findElement(By.cssSelector("#login-form-holder .s-form-error-message"));
            Assert.assertEquals(usernameError.getText(), expectedUsernameError);
        }

        webPageInBrowser.close();
    }

    @DataProvider(name="dataFailedValidationLogin")
    public Object[][] dataFailedValidationLogin() {
        return new Object[][]{
                {
                    "xxxxxxxxx", // вводимые значения в поле юзернейм
                    "yyyyyyyyy",  // вводимое значение в поле password
                    "You are not allowed to log in. Please contact customer service for more information.", // expectedFormError ожилаемій текст ошибки на форме ПОД ПОЛЯМИ
                    "", //expectedUsernameError ожидаемый текст ошибки(ПОД ПОЛЕМ) при ошибочном значении в поле юзернейм
                    "" //expectedPasswordError ожидаемый текст ошибки(ПОД ПОЛЕМ) при ошибочном значении в поле пассворд
                },
                {
                    "username",
                    "123123",
                    "Incorrect login or password. Please check it and try again.",
                    "",
                    ""
                },
                {
                    "qawsweeww",  // username
                    "1",// password
                    "", // expectedFormError
                    "", //expectedUsernameError
                    "6 to 15 symbols long. Latin letters, digits and special characters are allowed." //expectedPasswordError
                },
                {
                    "",  // username
                     "132@$%987456",// password
                     "", // expectedFormError
                     "This field is required.", //expectedUsernameError
                     "" //expectedPasswordError
                }
        };
    }

    @Test
    public void testSuccessLogin() {
        WebDriver webPageInBrowser = this.openWebPage();

        WebElement loginBtn = webPageInBrowser.findElement(By.cssSelector("#login-btn"));
        loginBtn.click();


        WebElement formInputUsername = webPageInBrowser.findElement(By.cssSelector("#l-username"));
        formInputUsername.sendKeys("qawsweeww");

        WebElement formInputPassword = webPageInBrowser.findElement(By.cssSelector("#l-password"));
        formInputPassword.sendKeys("eeededede");


        WebElement formLoginBtn = webPageInBrowser.findElement(By.cssSelector("#login"));
        formLoginBtn.click();

        this.waitBrowserLoaded();
        Assert.assertTrue(webPageInBrowser
                .getCurrentUrl()
                .contains("https://lobby.slotsempire.com:2572/Lobby.aspx?SkinId=1&login=qawsweeww&token="));


        webPageInBrowser.close();
    }

    private WebDriver openWebPage()
    {
        WebDriver browser = new ChromeDriver();
        browser.manage().window().maximize();

        browser.get("https://stage.slotsempire.com/");

        return browser;
    }

    private void waitBrowserLoaded() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
    }
}

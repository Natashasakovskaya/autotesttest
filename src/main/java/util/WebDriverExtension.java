package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverExtension {

    /**
     * Open page
     *
     * @param url - Site url to open
     * @return browser - ready to work Chrome window, with loaded page
     */
    public static WebDriver openWebPage(String url)
    {
        WebDriver browser = new ChromeDriver();
        browser.manage().window().maximize();

        browser.get(url);

        return browser;
    }

    /**
     * First click make after 50 milliseconds after method call. If we receive the error we suppose page is not ready.
     * Wait some time (value timeIntervalBeforeClick) after that repeat click.
     *
     * @param btn - form btn to click in
     * @param timeIntervalBeforeClick - in milliseconds
     */
    public static void doubleClickIfItNeed(WebElement btn, int timeIntervalBeforeClick) {
        try {
            waitBrowserLoaded(50);
            btn.click();
        } catch (WebDriverException exception) {
            WebDriverExtension.waitBrowserLoaded(timeIntervalBeforeClick);
            btn.click();
        }

        waitBrowserLoaded(50);
    }

    /**
     * Paused work and wait for data loading
     *
     * @param milliseconds - how many second need wait for load page
     */
    public static void waitBrowserLoaded(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {

        }
    }

    /**
     * Check element has some class
     *
     * @param element
     * @param active
     * @return true if element has class
     */
    public static boolean hasClass(WebElement element, String active) {
        return element.getAttribute("class").contains(active);
    }
}

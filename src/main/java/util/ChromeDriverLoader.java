package util;

public class ChromeDriverLoader {

    private static String os = System.getProperty("os.name").toLowerCase();

    private static String cwd = System.getProperty("user.dir");

    /**
     * Set path to load chrome driver/
     */
    public static void loadConfig()
    {
        if (!isWindows()) {
            System.setProperty("webdriver.chrome.driver", cwd + "/externalVendors/chromedriver");
        } else {
            System.setProperty("webdriver.chrome.driver", cwd + "/externalVendors/chromedriver.exe");
        }
    }

    /**
     * Check is windows OS running, If is Windows return true
     *
     * @return bool
     */
    private static boolean isWindows() {
        return os.contains("win");
    }
}

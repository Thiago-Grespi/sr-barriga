package core;

public class Properties {

    /*
    Here is a space to add many kinds of project configurations, feel free to abstract some fine tune, or great
     */

    public static boolean CLOSE_BROWSER_BETWEEN_TESTS = true;
    public static boolean CLOSE_BROWSER_AFTER_ALL_TESTS = false;
    public static Browsers browsers = Browsers.CHROME;
    public static boolean HEADLESS_BROWSER = false;

    public enum Browsers {
        CHROME,
        FIREFOX
    }

    public static String baseUrl = "http://seubarriga.wcaquino.me/";

}

package DATA.Other;

import java.io.File;
import java.math.BigInteger;
import java.security.SecureRandom;

public class Strings {

    public static String sTerminalColorRed = "\033[0;31m";
    public static String sTerminalColorGreen = "\033[0;32m";
    public static String sTerminalColorReset = "\033[0m";

    public static String sReportPath = System.getProperty("user.dir", ".")
            + File.separator + "src" + File.separator + "test" + File.separator + "java"
            + File.separator + "REPORTS" + File.separator;

    public static String getRandomVin() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(68, random).toString(16);
    }
}

package OTHER;

import DATA.Other.Strings;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ErrorHandler extends Strings {

    static SoftAssert softAssert = new SoftAssert();
    public static List<String> lsLogs = new ArrayList<>();

    public static class Success {

        public boolean bSuccess;
        public List<String> lsExceptions;
        private Exception exception;

        public Success() {
            bSuccess = true;
            lsExceptions = new ArrayList<>();
        }
    }

    public static Success errorWrapper(String sMethodDescription, boolean bFailTest, Function<Success, Success> doWork) {

        Success success = new Success();

        try {
            success = doWork.apply(success);
            if (!sMethodDescription.equals("*SKIP")) {
                if (success.lsExceptions.size() > 0)
                    success.exception = new Exception("\n" + String.join("\n", success.lsExceptions) + "\n");
                if (success.exception != null)
                    throw success.exception;
                String sLog = "PASSED: " + sMethodDescription;
                System.out.println(sTerminalColorGreen + sLog + sTerminalColorReset);
                lsLogs.add("\t" + sLog);
            }
        } catch (Exception e) {
            success.bSuccess = false;
            String sLog = "FAILED: " + sMethodDescription;
            System.out.println(sTerminalColorRed + sLog);
            success.exception = e;
            String sLog_2 = "\tDETAILS: " + success.exception.getMessage().replace("\n", "\n\t");
            System.out.println(sLog_2 + sTerminalColorReset);
            lsLogs.add("\t" + sLog + "\n\t" + sLog_2);
            softAssert.assertTrue(false, success.exception.getMessage());
            if (bFailTest)
                testEnd();
        }
        return success;
    }

    public static void addException(String sException) {
        ErrorHandler.errorWrapper("Exception", false, success -> {
            success.lsExceptions.add(sException);
            return success;
        });
    }

    public static void testEnd() {
        softAssert.assertAll();
    }
}

package OTHER;

import DATA.Other.Strings;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Date;

public class ReporterListener implements ITestListener {

    String sFullPath = "";

    @Override
    public void onStart(ITestContext contextStart) {
        String sDate = Tools.convertDateToStringFormat(new Date(), "yyyy_MM_dd_HH_mm_ss");
        String sFileName = "report_" + contextStart.getSuite().getName() + "_" + sDate + ".txt";
        if (sFullPath.equals("")) {
            sFullPath = Strings.sReportPath + sFileName;
            Tools.writeToFile(sFullPath, "");
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        String sTestName = "TEST: " + result.getTestClass().getName();
        String sTestDescription = result.getMethod().getDescription().replace("\n", "\n\t");
        ErrorHandler.lsLogs.add(sTestName + "//nDESCRIPTION: " + sTestDescription + "//nSTEPS:");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ErrorHandler.lsLogs.add("RESULT: TEST PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ErrorHandler.lsLogs.add("RESULT: TEST FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ErrorHandler.lsLogs.add("RESULT: TEST SKIPPED");
    }

    @Override
    public void onFinish(ITestContext contextFinish) {
        StringBuilder sReport = new StringBuilder();
        for (String sLog : ErrorHandler.lsLogs)
            sReport.append(sLog.replace("\n", "\n\t").replace("//n", "\n")).append("\n");
        sReport = new StringBuilder(sReport.toString().replace(Strings.sTerminalColorGreen, ""));
        sReport = new StringBuilder(sReport.toString().replace(Strings.sTerminalColorRed, ""));
        sReport = new StringBuilder(sReport.toString().replace(Strings.sTerminalColorReset, ""));
        Tools.writeToFile(sFullPath, sReport.toString());
        ErrorHandler.lsLogs.add("\n");
    }
}

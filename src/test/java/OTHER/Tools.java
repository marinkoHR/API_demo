package OTHER;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {

    public static void writeToFile(String sFilePath, String sText) {
        File file = new File(sFilePath);
        try {
            FileUtils.writeStringToFile(file, sText, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String convertDateToStringFormat(Date date, String sFormat) {
        DateFormat df = new SimpleDateFormat(sFormat);
        return df.format(date);
    }
}

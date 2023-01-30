package API;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

public class Common {

    public static class ExecutionResponse {

        public int iResponseCode;
        public String sResponse;
    }

    public static ExecutionResponse execute(HttpURLConnection request) throws IOException {
        InputStream is;
        ExecutionResponse response = new ExecutionResponse();
        response.iResponseCode = request.getResponseCode();
        if (response.iResponseCode < 400)
            is = request.getInputStream();
        else
            is = request.getErrorStream();
        if (is == null)
            response.sResponse = request.getResponseMessage();
        else
            response.sResponse = IOUtils.toString(is, StandardCharsets.UTF_8);
        return response;
    }
}

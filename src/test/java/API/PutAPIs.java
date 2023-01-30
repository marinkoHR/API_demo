package API;

import DATA.Other.Urls;
import DATA.Requests.CarRequest;
import DATA.Requests.DriverRequest;
import DATA.Responses.ErrorResponse;
import OTHER.ErrorHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

public class PutAPIs extends Urls {

    public static ErrorResponse updateCar(CarRequest carRequest, String sCarId) {
        AtomicReference<ErrorResponse> response = new AtomicReference<>(new ErrorResponse());
        ErrorHandler.errorWrapper("Update car with ID '" + sCarId + "' using /cars PUT API", true, success -> {
            Common.ExecutionResponse executionResponse;
            try {
                String sUri = sMainUrl + "/cars/" + sCarId;
                URL url = new URL(sUri);
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.serializeNulls();
                Gson gson = gsonBuilder.create();
                String sRequest = gson.toJson(carRequest);
                byte[] postDataBytes = sRequest.getBytes(StandardCharsets.UTF_8);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("PUT");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);
                connection.getOutputStream().write(postDataBytes);
                executionResponse = Common.execute(connection);
                response.get().error_code = executionResponse.iResponseCode;
                response.get().error = executionResponse.sResponse;
            } catch (Exception e) {
                success.lsExceptions.add("Method PutAPIs.updateCar failed: " + e.getMessage());
            }
            return success;
        });
        return response.get();
    }

    public static ErrorResponse updateDriver(DriverRequest driverRequest, String sDriverId) {
        AtomicReference<ErrorResponse> response = new AtomicReference<>(new ErrorResponse());
        ErrorHandler.errorWrapper("Update driver with ID '" + sDriverId + "' using /drivers PUT API", true, success -> {
            Common.ExecutionResponse executionResponse;
            try {
                String sUri = sMainUrl + "/drivers/" + sDriverId;
                URL url = new URL(sUri);
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.serializeNulls();
                Gson gson = gsonBuilder.create();
                String sRequest = gson.toJson(driverRequest);
                byte[] postDataBytes = sRequest.getBytes(StandardCharsets.UTF_8);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("PUT");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);
                connection.getOutputStream().write(postDataBytes);
                executionResponse = Common.execute(connection);
                response.get().error_code = executionResponse.iResponseCode;
                response.get().error = executionResponse.sResponse;
            } catch (Exception e) {
                success.lsExceptions.add("Method PutAPIs.updateDriver failed: " + e.getMessage());
            }
            return success;
        });
        return response.get();
    }
}

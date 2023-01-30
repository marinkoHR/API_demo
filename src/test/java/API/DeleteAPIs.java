package API;

import DATA.Other.Urls;
import DATA.Responses.ErrorResponse;
import OTHER.ErrorHandler;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;

public class DeleteAPIs extends Urls {

    public static ErrorResponse deleteCar(String sCarId) {
        AtomicReference<ErrorResponse> response = new AtomicReference<>(new ErrorResponse());
        ErrorHandler.errorWrapper("Delete car with ID '" + sCarId + "' using /cars DELETE API", false, success -> {
            Common.ExecutionResponse executionResponse;
            try {
                String sUri = sMainUrl + "/cars/" + sCarId;
                URL url = new URL(sUri);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("DELETE");
                connection.setRequestProperty("Content-Type", "application/json");
                executionResponse = Common.execute(connection);
                response.get().error_code = executionResponse.iResponseCode;
                response.get().error = executionResponse.sResponse;
            } catch (Exception e) {
                success.lsExceptions.add("Method DeleteAPIs.deleteCar failed: " + e.getMessage());
            }
            return success;
        });
        return response.get();
    }

    public static ErrorResponse deleteDriver(String sDriverId) {
        AtomicReference<ErrorResponse> response = new AtomicReference<>(new ErrorResponse());
        ErrorHandler.errorWrapper("Delete driver with ID '" + sDriverId + "' using /drivers DELETE API", false,
                success -> {
            Common.ExecutionResponse executionResponse;
            try {
                String sUri = sMainUrl + "/drivers/" + sDriverId;
                URL url = new URL(sUri);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("DELETE");
                connection.setRequestProperty("Content-Type", "application/json");
                executionResponse = Common.execute(connection);
                response.get().error_code = executionResponse.iResponseCode;
                response.get().error = executionResponse.sResponse;
            } catch (Exception e) {
                success.lsExceptions.add("Method DeleteAPIs.deleteDriver failed: " + e.getMessage());
            }
            return success;
        });
        return response.get();
    }
}

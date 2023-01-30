package API;

import DATA.Other.Urls;
import DATA.Requests.CarRequest;
import DATA.Requests.DriverRequest;
import DATA.Responses.CarResponse;
import DATA.Responses.DriverResponse;
import OTHER.ErrorHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

public class PostAPIs extends Urls {

    public static CarResponse createNewCar(CarRequest newCar) {
        AtomicReference<CarResponse> response = new AtomicReference<>(new CarResponse());
        ErrorHandler.errorWrapper("Create a new car using /cars POST API", true, success -> {
            Common.ExecutionResponse executionResponse;
            try {
                String sUri = sMainUrl + "/cars";
                URL url = new URL(sUri);
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.serializeNulls();
                Gson gson = gsonBuilder.create();
                String sRequest = gson.toJson(newCar);
                byte[] postDataBytes = sRequest.getBytes(StandardCharsets.UTF_8);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);
                connection.getOutputStream().write(postDataBytes);
                executionResponse = Common.execute(connection);
                response.set(new Gson().fromJson(executionResponse.sResponse, CarResponse.class));
            } catch (Exception e) {
                success.lsExceptions.add("Method PostAPIs.createNewCar failed: " + e.getMessage());
            }
            return success;
        });
        return response.get();
    }

    public static DriverResponse createNewDriver(DriverRequest newDriver) {
        AtomicReference<DriverResponse> response = new AtomicReference<>(new DriverResponse());
        ErrorHandler.errorWrapper("Create a new car using /cars POST API", true, success -> {
            Common.ExecutionResponse executionResponse;
            try {
                String sUri = sMainUrl + "/drivers";
                URL url = new URL(sUri);
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.serializeNulls();
                Gson gson = gsonBuilder.create();
                String sRequest = gson.toJson(newDriver);
                byte[] postDataBytes = sRequest.getBytes(StandardCharsets.UTF_8);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);
                connection.getOutputStream().write(postDataBytes);
                executionResponse = Common.execute(connection);
                response.set(new Gson().fromJson(executionResponse.sResponse, DriverResponse.class));
            } catch (Exception e) {
                success.lsExceptions.add("Method PostAPIs.createNewDriver failed: " + e.getMessage());
            }
            return success;
        });
        return response.get();
    }
}

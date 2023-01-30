package API;

import DATA.Other.Urls;
import DATA.Responses.CarResponse;
import DATA.Responses.DriverResponse;
import OTHER.ErrorHandler;
import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class GetAPIs extends Urls {

    //region Endpoint /cars
    public static List<CarResponse> getCars() {
        return getCars("");
    }

    private static List<CarResponse> getCars(String sId) {
        List<CarResponse> lsCars = new ArrayList<>();
        String sQuantity = " all cars ";
        if (!sId.equals("")) {
            sId = "/" + sId;
            sQuantity = " car ";
        }
        String sFinalId = sId;
        ErrorHandler.errorWrapper("Get" + sQuantity + "with GET API using endpoint /cars" + sFinalId, true, success -> {
            Common.ExecutionResponse response;
            try {
                String sUri = sMainUrl + "/cars" + sFinalId;
                URL url = new URL(sUri);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");
                response = Common.execute(connection);
                if (response.iResponseCode < 400) {
                    if (sFinalId.equals(""))
                        Collections.addAll(lsCars, new Gson().fromJson(response.sResponse, CarResponse[].class));
                    else
                        lsCars.add(new Gson().fromJson(response.sResponse, CarResponse.class));
                } else {
                    CarResponse carResponse = new CarResponse();
                    carResponse.error_code = response.iResponseCode;
                    carResponse.error = response.sResponse;
                    lsCars.add(carResponse);
                }
            } catch (Exception e) {
                success.lsExceptions.add("Method GetAPIs.getAllCars failed: " + e.getMessage());
            }
            return success;
        });
        return lsCars;
    }

    public static CarResponse getCar(String sId) {
        List<CarResponse> lsCars = getCars(sId);
        return lsCars.get(0);
    }
    //endregion

    //region Endpoint /drivers
    public static DriverResponse getDriver(String sId) {
        AtomicReference<DriverResponse> driverResponse = new AtomicReference<>(new DriverResponse());
        ErrorHandler.errorWrapper("Get driver with the ID '" + sId + "' using GET API for endpoint /drivers", true,
                success -> {
            Common.ExecutionResponse response;
            try {
                String sUri = sMainUrl + "/drivers/" + sId;
                URL url = new URL(sUri);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json");
                response = Common.execute(connection);
                if (response.iResponseCode < 400)
                    driverResponse.set(new Gson().fromJson(response.sResponse, DriverResponse.class));
                else {
                    driverResponse.set(new DriverResponse());
                    driverResponse.get().error_code = response.iResponseCode;
                    driverResponse.get().error = response.sResponse;
                }
            } catch (Exception e) {
                success.lsExceptions.add("Method GetAPIs.getAllCars failed: " + e.getMessage());
            }
            return success;
        });
        return driverResponse.get();
    }
    //endregion
}

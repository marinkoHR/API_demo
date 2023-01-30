package API;

import DATA.Requests.CarRequest;
import DATA.Requests.DriverRequest;
import DATA.Responses.CarResponse;
import DATA.Responses.DriverResponse;
import DATA.Responses.ErrorResponse;
import OTHER.ErrorHandler;

import java.util.Collections;
import java.util.List;

public class Verifications {

    private enum METHOD {POST, GET}

    private static class Common {

        protected static ErrorHandler.Success verifyCarResponse(METHOD method, CarResponse responseActual,
                                                                CarResponse responseExpected) {
            return ErrorHandler.errorWrapper("*SKIP", false, success -> {

                boolean bFail = false;

                //region Verify Car ID
                if (responseExpected._id != null) {
                    if (responseActual._id == null)
                        bFail = true;
                    else
                        bFail = !responseActual._id.equals(responseExpected._id);
                }
                if (bFail)
                    success.lsExceptions.add(
                            "Value for '_id' from /cars " + method + " API response isn't as expected!\nExpected: "
                                    + responseExpected._id + "\nActual: " + responseActual._id);
                //endregion

                //region Verify Car brand
                if (responseExpected.brand != null) {
                    if (responseActual.brand == null)
                        bFail = true;
                    else
                        bFail = !responseActual.brand.equals(responseExpected.brand);
                } else
                    bFail = responseActual.brand != null;
                if (bFail)
                    success.lsExceptions.add(
                            "Value for 'brand' from /cars " + method + " API response isn't as expected!\nExpected: "
                                    + responseExpected.brand + "\nActual: " + responseActual.brand);
                //endregion

                //region Verify Car model
                if (responseExpected.model != null) {
                    if (responseActual.model == null)
                        bFail = true;
                    else
                        bFail = !responseActual.model.equals(responseExpected.model);
                } else
                    bFail = responseActual.model != null;
                if (bFail)
                    success.lsExceptions.add(
                            "Value for 'model' from /cars " + method + " API response isn't as expected!\nExpected: "
                                    + responseExpected.model + "\nActual: " + responseActual.model);
                //endregion

                //region Verify Car VIN
                if (responseExpected.vin != null) {
                    if (responseActual.vin == null)
                        bFail = true;
                    else
                        bFail = !responseActual.vin.equals(responseExpected.vin);
                } else
                    bFail = responseActual.vin != null;
                if (bFail)
                    success.lsExceptions.add(
                            "Value for 'vin' from /cars " + method + " API response isn't as expected!\nExpected: "
                                    + responseExpected.vin + "\nActual: " + responseActual.vin);
                //endregion

                //region Verify Car driver ID
                if (responseExpected.driver_id != null) {
                    if (responseActual.driver_id == null)
                        bFail = true;
                    else
                        bFail = !responseActual.driver_id.equals(responseExpected.driver_id);
                } else
                    bFail = responseActual.driver_id != null;
                if (bFail)
                    success.lsExceptions.add("Value for 'driver_id' from /cars " + method + " API response isn't as "
                            + "expected!\nExpected: " + responseExpected.driver_id + "\nActual: "
                            + responseActual.driver_id);
                //endregion

                return success;
            });
        }

        protected static ErrorHandler.Success verifyCarResponseIsValid(List<CarResponse> lsActualResponse,
                                                                    boolean bShouldBeSuccessful) {
            return ErrorHandler.errorWrapper("*SKIP", false,
                    success -> {

                        if (lsActualResponse.size() == 0)
                            success.lsExceptions.add("Can't verify response from GET API for /cars endpoint "
                                    + "because API didn't return any car!");
                        else {
                            if (bShouldBeSuccessful) {
                                if (lsActualResponse.stream().anyMatch(x -> x.error != null))
                                    success.lsExceptions.add("GET API for /cars endpoint failed!\nError: "
                                            + lsActualResponse.get(0).error_code + " - "
                                            + lsActualResponse.get(0).error);
                            } else if (lsActualResponse.stream().noneMatch(x -> x.error != null))
                                success.lsExceptions.add("GET API for /cars endpoint was successful and "
                                        + "it shouldn't be!");
                        }

                        return success;
                    });
        }

        protected static ErrorHandler.Success verifyDriverResponse(METHOD method, DriverResponse responseActual,
                                                                   DriverResponse responseExpected) {
            return ErrorHandler.errorWrapper("*SKIP", false, success -> {

                boolean bFail = false;

                //region Verify Driver ID
                if (responseExpected._id != null) {
                    if (responseActual._id == null)
                        bFail = true;
                    else
                        bFail = !responseActual._id.equals(responseExpected._id);
                }
                if (bFail)
                    success.lsExceptions.add(
                            "Value for '_id' from /drivers " + method + " API response isn't as expected!\nExpected: "
                                    + responseExpected._id + "\nActual: " + responseActual._id);
                //endregion

                //region Verify Driver first name
                if (responseExpected.first_name != null) {
                    if (responseActual.first_name == null)
                        bFail = true;
                    else
                        bFail = !responseActual.first_name.equals(responseExpected.first_name);
                } else
                    bFail = responseActual.first_name != null;
                if (bFail)
                    success.lsExceptions.add(
                            "Value for 'first_name' from /drivers " + method + " API response isn't as "
                                    + "expected!\nExpected: " + responseExpected.first_name
                                    + "\nActual: " + responseActual.first_name);
                //endregion

                //region Verify Driver last name
                if (responseExpected.last_name != null) {
                    if (responseActual.last_name == null)
                        bFail = true;
                    else
                        bFail = !responseActual.last_name.equals(responseExpected.last_name);
                } else
                    bFail = responseActual.last_name != null;
                if (bFail)
                    success.lsExceptions.add(
                            "Value for 'last_name' from /drivers " + method + " API response isn't as "
                                    + "expected!\nExpected: " + responseExpected.last_name
                                    + "\nActual: " + responseActual.last_name);
                //endregion

                //region Verify Driver date of birth
                if (responseExpected.date_of_birth != null) {
                    if (responseActual.date_of_birth == null)
                        bFail = true;
                    else
                        bFail = responseActual.date_of_birth.compareTo(responseExpected.date_of_birth) != 0;
                } else
                    bFail = responseActual.date_of_birth != null;
                if (bFail)
                    success.lsExceptions.add(
                            "Value for 'date_of_birth' from /drivers " + method + " API response isn't as "
                                    + "expected!\nExpected: " + responseExpected.date_of_birth
                                    + "\nActual: " + responseActual.date_of_birth);
                //endregion

                //region Verify Driver driving licence number
                if (responseExpected.driving_licence_number != null) {
                    if (responseActual.driving_licence_number == null)
                        bFail = true;
                    else
                        bFail = !responseActual.driving_licence_number.equals(responseExpected.driving_licence_number);
                } else
                    bFail = responseActual.driving_licence_number != null;
                if (bFail)
                    success.lsExceptions.add("Value for 'driving_licence_number' from /drivers " + method + " API "
                            + "response isn't as expected!\nExpected: " + responseExpected.driving_licence_number
                            + "\nActual: " + responseActual.driving_licence_number);
                //endregion

                //region Verify Driver car ID
                if (responseExpected.car_id != null) {
                    if (responseActual.car_id == null)
                        bFail = true;
                    else
                        bFail = !responseActual.car_id.equals(responseExpected.car_id);
                } else
                    bFail = responseActual.car_id != null;
                if (bFail)
                    success.lsExceptions.add(
                            "Value for 'car_id' from /drivers " + method + " API response isn't as "
                                    + "expected!\nExpected: " + responseExpected.car_id
                                    + "\nActual: " + responseActual.car_id);
                //endregion

                return success;
            });
        }

        protected static ErrorHandler.Success verifyDriverResponseIsValid(DriverResponse driverResponse,
                                                                       boolean bShouldBeSuccessful) {
            return ErrorHandler.errorWrapper("*SKIP", false,
                    success -> {

                        if (bShouldBeSuccessful) {
                            if (driverResponse.error != null)
                                success.lsExceptions.add("GET API for /drivers endpoint failed!\nError: "
                                        + driverResponse.error_code + " - " + driverResponse.error);
                        } else if (driverResponse.error == null)
                            success.lsExceptions.add("GET API for /drivers endpoint was successful and "
                                    + "it shouldn't be!");

                        return success;
                    });
        }
    }

    public static class Post {

        //region Verifications for the /cars endpoint
        public static ErrorHandler.Success verifyCarResponseIsValid(CarResponse actualResponse,
                                                                    boolean bShouldBeSuccessful) {
            return ErrorHandler.errorWrapper("Verify response from POST API /cars endpoint is valid", false,
                    success -> {

                        success.lsExceptions =
                                Common.verifyCarResponseIsValid(Collections.singletonList(actualResponse),
                                        bShouldBeSuccessful).lsExceptions;

                        return success;
                    });
        }

        public static void verifyNewCarResponse(CarResponse response, CarRequest expectedValues) {
            ErrorHandler.errorWrapper("Compare values from POST API /cars endpoint response with expected values", false,
                    success -> {

                CarResponse expectedResponse = new CarResponse(expectedValues);
                success.lsExceptions = Common.verifyCarResponse(METHOD.POST, response, expectedResponse).lsExceptions;

                return success;
            });
        }
        //endregion

        //region Verifications for the /drivers endpoint
        public static ErrorHandler.Success verifyDriverResponseIsValid(DriverResponse actualResponse,
                                                                       boolean bShouldBeSuccessful) {
            return ErrorHandler.errorWrapper("Verify response from POST API /drivers endpoint is valid", false,
                    success -> {

                        success.lsExceptions =
                                Common.verifyDriverResponseIsValid(actualResponse, bShouldBeSuccessful).lsExceptions;

                        return success;
                    });
        }

        public static void verifyNewDriverResponse(DriverResponse response, DriverRequest expectedValues) {
            ErrorHandler.errorWrapper("Compare values from POST API /drivers endpoint response with expected values",
                    false, success -> {

                        DriverResponse expectedResponse = new DriverResponse(expectedValues);
                        success.lsExceptions =
                                Common.verifyDriverResponse(METHOD.POST, response, expectedResponse).lsExceptions;

                        return success;
                    });
        }
        //endregion
    }

    public static class Get {

        //region Verifications for the /cars endpoint
        public static ErrorHandler.Success verifyCarResponseIsValid(List<CarResponse> lsActualResponse,
                                                                    boolean bShouldBeSuccessful) {
            String sResult = "valid";
            if (!bShouldBeSuccessful)
                sResult = "in" + sResult;
            return ErrorHandler.errorWrapper("Verify response from GET API /cars endpoint is " + sResult, false,
                    success -> {

                        success.lsExceptions =
                                Common.verifyCarResponseIsValid(lsActualResponse, bShouldBeSuccessful).lsExceptions;

                        return success;
                    });
        }

        public static ErrorHandler.Success verifyCarResponseIsValid(CarResponse actualResponse,
                                                                    boolean bShouldBeSuccessful) {
            String sId = "validId";
            String sResult = "successful";
            if (!bShouldBeSuccessful) {
                sId = "in" + sId;
                sResult = "un" + sResult;
            }
            return ErrorHandler.errorWrapper("Verify response from GET API /cars/" + sId + " endpoint is " + sResult, false,
                    success -> {

                        success.lsExceptions =
                                Common.verifyCarResponseIsValid(Collections.singletonList(actualResponse),
                                        bShouldBeSuccessful).lsExceptions;

                        return success;
                    });
        }

        public static void verifyFailedCarResponse(CarResponse actualResponse, int iExpectedErrorCode,
                                                   String sExpectedErrorMessage) {
            ErrorHandler.errorWrapper("Verify error message and code from failed GET API response for /cars/invalidId "
                            + "endpoint", false, success -> {

                        if (actualResponse.error == null)
                            success.lsExceptions.add("Response from GET API /cars endpoint doesn't contain error "
                                    + "message!");
                        else {
                            if (actualResponse.error_code != iExpectedErrorCode)
                                success.lsExceptions.add("Error code from GET API /cars endpoint response "
                                        + "isn't as expected!\nExpected: " + iExpectedErrorCode
                                        + "\nActual: " + actualResponse.error_code);
                            if (!actualResponse.error.contains(sExpectedErrorMessage))
                                success.lsExceptions.add("Error message from GET API /cars endpoint response "
                                        + "isn't as expected!\nExpected: " + sExpectedErrorMessage
                                        + "\nActual: " + actualResponse.error);
                        }

                        return success;
                    });
        }

        public static void verifyCarResponse(CarResponse actualResponse, CarResponse expectedResponse) {
            ErrorHandler.errorWrapper("Compare values from GET API /cars endpoint response with expected values", false,
                    success -> {

                success.lsExceptions =
                        Common.verifyCarResponse(METHOD.GET, actualResponse, expectedResponse).lsExceptions;

                return success;
            });
        }
        //endregion

        //region Verifications for the /drivers endpoint
        public static ErrorHandler.Success verifyDriverResponseIsValid(DriverResponse driverResponse,
                                                                       boolean bShouldBeSuccessful) {
            return ErrorHandler.errorWrapper("Verify response from GET API /drivers endpoint is valid", false,
                    success -> {

                        success.lsExceptions =
                                Common.verifyDriverResponseIsValid(driverResponse, bShouldBeSuccessful).lsExceptions;

                        return success;
                    });
        }

        public static void verifyDriverResponse(DriverResponse actualResponse, DriverResponse expectedResponse) {
            ErrorHandler.errorWrapper("Compare values from GET API /drivers endpoint response with expected values",
                    false, success -> {

                        success.lsExceptions =
                                Common.verifyDriverResponse(METHOD.GET, actualResponse, expectedResponse).lsExceptions;

                        return success;
                    });
        }
        //endregion
    }

    public static class Delete {

        //region Verifications for the /cars endpoint
        public static ErrorHandler.Success verifyDeleteResponse(ErrorResponse response, boolean bShouldBeSuccessful,
                                                                String sEndpoint) {
            String sResult = " has failed";
            if (bShouldBeSuccessful)
                sResult = " was successful";
            return ErrorHandler.errorWrapper("Verify DELETE API for " + sEndpoint + " endpoint" + sResult, false,
                    success -> {

                if (bShouldBeSuccessful) {
                    if (response.error_code != 200)
                        success.lsExceptions.add("DELETE API for " + sEndpoint + " endpoint failed!\nError: "
                                + response.error_code + " - " + response.error);
                } else if (response.error_code == 200)
                    success.lsExceptions.add("DELETE API for " + sEndpoint + " endpoint was successful and "
                            + "it shouldn't be!");

                return success;
            });
        }
        //endregion
    }

    public static class Put {

        public static ErrorHandler.Success verifyUpdateResponse(ErrorResponse response, boolean bShouldBeSuccessful,
                                                                String sEndpoint) {
            String sResult = " has failed";
            if (bShouldBeSuccessful)
                sResult = " was successful";
            return ErrorHandler.errorWrapper("Verify PUT API for " + sEndpoint + " endpoint" + sResult, false,
                    success -> {

                if (bShouldBeSuccessful) {
                    if (response.error_code != 200)
                        success.lsExceptions.add("PUT API for " + sEndpoint + " endpoint failed!\nError: "
                                + response.error_code + " - " + response.error);
                } else if (response.error_code == 200)
                    success.lsExceptions.add("PUT API for " + sEndpoint + " endpoint was successful and "
                            + "it shouldn't be!");

                return success;
            });
        }
    }
}

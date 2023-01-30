package TESTS;

import API.*;
import DATA.Requests.CarRequest;
import DATA.Requests.DriverRequest;
import DATA.Responses.CarResponse;
import DATA.Responses.DriverResponse;
import DATA.Responses.ErrorResponse;
import OTHER.ErrorHandler;
import org.testng.annotations.Test;

import java.util.Calendar;

public class TEST5_VerifyUpdateCarWithDriverData {

    @Test(description = "TC-5 - Create a new car on the /cars endpoint and a new driver on the /drivers endpoint.\n"
            + "Update both of them.\n"
            + "Delete both of them.")
    public void TEST5() {

        String sCarsEndpoint = "/cars";
        String sDriversEndpoint = "/drivers";

        //region Prepare
        //region Car request
        String sBrand = "Rimac";
        String sModel = "Nevera";
        CarRequest carRequest = new CarRequest(sBrand, sModel);
        //endregion

        //region Driver request
        DriverRequest driverRequest = new DriverRequest();
        driverRequest.first_name = "John";
        driverRequest.last_name = "Doe";
        Calendar calDateOfBirth = Calendar.getInstance();
        calDateOfBirth.add(Calendar.YEAR, -40);
        calDateOfBirth.set(Calendar.SECOND, 0);
        calDateOfBirth.set(Calendar.MILLISECOND, 0);
        driverRequest.date_of_birth = calDateOfBirth.getTime();
        driverRequest.driving_licence_number = "xxxxxxxxx";
        //endregion
        //endregion

        //region Step 1: Send a request with the POST API method to /cars endpoint
        CarResponse carResponse = PostAPIs.createNewCar(carRequest);
        Verifications.Post.verifyCarResponseIsValid(carResponse, true);
        Verifications.Post.verifyNewCarResponse(carResponse, carRequest);
        CarResponse carGetResponse = GetAPIs.getCar(carResponse._id);
        Verifications.Get.verifyCarResponseIsValid(carGetResponse, true);
        //endregion

        //region Step 2: Send a request with the POST API method to /drivers endpoint
        DriverResponse driverResponse = PostAPIs.createNewDriver(driverRequest);
        Verifications.Post.verifyDriverResponseIsValid(driverResponse, true);
        Verifications.Post.verifyNewDriverResponse(driverResponse, driverRequest);
        DriverResponse driverResponseGet = GetAPIs.getDriver(driverResponse._id);
        Verifications.Get.verifyDriverResponseIsValid(driverResponseGet, true);
        //endregion

        //region Step 3: Update the driver_id of the car which was created in step 1 by using the PUT API method on
        // /cars endpoint and the ID of the car created in step 1
        carRequest.driver_id = driverResponse._id;
        ErrorResponse responseCarUpdate = PutAPIs.updateCar(carRequest, carResponse._id);
        Verifications.Put.verifyUpdateResponse(responseCarUpdate, true, sCarsEndpoint);
        //endregion

        //region Step 4: Update the car_id of the driver which was created in step 2 by using the PUT API method on
        // /drivers endpoint and the ID of the driver created in step 1
        driverRequest.car_id = carResponse._id;
        ErrorResponse responseDriverUpdate = PutAPIs.updateDriver(driverRequest, driverResponse._id);
        Verifications.Put.verifyUpdateResponse(responseDriverUpdate, true, sDriversEndpoint);
        //endregion

        //region Step 5: Verify a car with the ID of the car created in step 1 is updated by using the GET API method
        // on /cars endpoint
        carResponse.driver_id = driverResponse._id;
        CarResponse carGetResponseUpdated = GetAPIs.getCar(carResponse._id);
        Verifications.Get.verifyCarResponseIsValid(carGetResponseUpdated, true);
        Verifications.Get.verifyCarResponse(carGetResponseUpdated, carResponse);
        //endregion

        //region Step 6: Verify a driver with the ID of the driver created in step 2 is updated by using the GET API
        // method on /drivers endpoint
        driverResponse.car_id = carResponse._id;
        DriverResponse driverResponseActual = GetAPIs.getDriver(driverResponse._id);
        Verifications.Get.verifyDriverResponseIsValid(driverResponseActual, true);
        Verifications.Get.verifyDriverResponse(driverResponseActual, driverResponse);
        //endregion

        //region Step 7: Call DELETE API method on /cars endpoint using the ID of the car created in step 1
        ErrorResponse deleteCarResponse = DeleteAPIs.deleteCar(carResponse._id);
        Verifications.Delete.verifyDeleteResponse(deleteCarResponse, true, sCarsEndpoint);
        //endregion

        //region Step 8: Call DELETE API method on /drivers endpoint using the ID of the driver created in step 2
        ErrorResponse deleteDriverResponse = DeleteAPIs.deleteDriver(driverResponse._id);
        Verifications.Delete.verifyDeleteResponse(deleteDriverResponse, true, sDriversEndpoint);
        //endregion

        ErrorHandler.testEnd();
    }
}

package TESTS;

import API.GetAPIs;
import API.PostAPIs;
import API.PutAPIs;
import API.Verifications;
import DATA.Requests.CarRequest;
import DATA.Responses.CarResponse;
import DATA.Responses.ErrorResponse;
import OTHER.ErrorHandler;
import org.testng.annotations.Test;

public class TEST4_VerifyUpdateCars {

    @Test(description = "TC-4 - Write a test case that will verify PUT API method for /cars endpoint")
    public void TEST4() {

        String sBrand = "Mercedes";
        String sModel = "E Class";
        String sModelUpdated = "S Class";
        String sInvalidId = "invalidID";
        String sEndpoint = "/cars";

        CarRequest carRequest = new CarRequest(sBrand, sModel);

        //region Step 1: Send a request with the POST API method to /cars endpoint
        CarResponse carResponse = PostAPIs.createNewCar(carRequest);
        Verifications.Post.verifyCarResponseIsValid(carResponse, true);
        Verifications.Post.verifyNewCarResponse(carResponse, carRequest);
        CarResponse carGetResponse = GetAPIs.getCar(carResponse._id);
        Verifications.Get.verifyCarResponseIsValid(carGetResponse, true);
        //endregion

        //region Step 2: Update the car which was created in step 1 by using the PUT API method on /cars endpoint and
        // the ID of the car created in step 1
        carRequest.model = sModelUpdated;
        ErrorResponse response = PutAPIs.updateCar(carRequest, carResponse._id);
        Verifications.Put.verifyUpdateResponse(response, true, sEndpoint);
        //endregion

        //region Step 3: Verify a car with the ID of the car created in step 1 is updated by using the GET API method
        // on /cars endpoint
        carGetResponse.model = sModelUpdated;
        CarResponse carGetResponseUpdated = GetAPIs.getCar(carResponse._id);
        Verifications.Get.verifyCarResponseIsValid(carGetResponseUpdated, true);
        Verifications.Get.verifyCarResponse(carGetResponseUpdated, carGetResponse);
        //endregion

        //region Step 4: Update the car which was created in step 1 using the PUT API method on /cars endpoint and
        // invalid car ID
        carRequest.model = "C Class";
        ErrorResponse failedResponse = PutAPIs.updateCar(carRequest, sInvalidId);
        Verifications.Put.verifyUpdateResponse(failedResponse, false, sEndpoint);
        //endregion

        ErrorHandler.testEnd();
    }
}

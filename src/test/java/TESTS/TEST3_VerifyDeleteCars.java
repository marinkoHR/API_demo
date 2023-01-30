package TESTS;

import API.DeleteAPIs;
import API.GetAPIs;
import API.PostAPIs;
import API.Verifications;
import DATA.Requests.CarRequest;
import DATA.Responses.CarResponse;
import DATA.Responses.ErrorResponse;
import OTHER.ErrorHandler;
import org.testng.annotations.Test;

public class TEST3_VerifyDeleteCars {

    @Test(description = "TC-3 - Write a test case that will verify DELETE API method for /cars endpoint")
    public void TEST3() {

        String sBrand = "BMW";
        String sModel = "5";
        String sInvalidId = "invalidID";
        String sEndpoint = "/cars";

        CarRequest carRequest = new CarRequest(sBrand, sModel);

        //region Step 1: Send a request with the POST API method to /cars endpoint
        CarResponse carResponse = PostAPIs.createNewCar(carRequest);
        Verifications.Post.verifyCarResponseIsValid(carResponse, true);
        Verifications.Post.verifyNewCarResponse(carResponse, carRequest);
        //endregion

        //region Step 2: Verify car from the step 1 is created by using the GET API method on /cars endpoint with the
        // ID of the car created in step 1
        CarResponse carGetResponse = GetAPIs.getCar(carResponse._id);
        boolean bPassed = Verifications.Get.verifyCarResponseIsValid(carGetResponse, true).bSuccess;
        if (bPassed)
            Verifications.Get.verifyCarResponse(carGetResponse, carResponse);
        else
            ErrorHandler.addException("Can't verify response from GET API for /cars endpoint "
                    + "for ID of the car created in the step 1 because GET API failed!");
        //endregion

        //region Step 3: Call DELETE API method on /cars endpoint using the ID of the car created in step 1
        ErrorResponse response = DeleteAPIs.deleteCar(carResponse._id);
        Verifications.Delete.verifyDeleteResponse(response, true, sEndpoint);
        //endregion

        //region Step 4: Verify a car with the ID of the car created in step 1 doesn't exist by using the GET API
        // method on /cars endpoint
        CarResponse failedGetResponse = GetAPIs.getCar(carResponse._id);
        Verifications.Get.verifyCarResponseIsValid(failedGetResponse, false);
        Verifications.Get.verifyFailedCarResponse(failedGetResponse, 404, "Not Found");
        //endregion

        //region Step 5: Call DELETE API method on /cars endpoint using invalid ID
        ErrorResponse failedDeleteResponse = DeleteAPIs.deleteCar(sInvalidId);
        Verifications.Delete.verifyDeleteResponse(failedDeleteResponse, false, sEndpoint);
        //endregion

        ErrorHandler.testEnd();
    }
}

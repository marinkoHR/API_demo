package TESTS;

import API.GetAPIs;
import API.PostAPIs;
import API.Verifications;
import DATA.Requests.CarRequest;
import DATA.Responses.CarResponse;
import OTHER.ErrorHandler;
import org.testng.annotations.Test;

public class TEST2_VerifyPostCars {

    @Test(description = "TC-2 - Write a test case that will verify POST API method for /cars endpoint")
    public void TEST2() {

        String sBrand = "Audi";
        String sModel = "A7";

        CarRequest carRequest = new CarRequest(sBrand, sModel);

        //region Step 1: Send a request with the POST API method to /cars endpoint
        CarResponse carResponse = PostAPIs.createNewCar(carRequest);
        Verifications.Post.verifyCarResponseIsValid(carResponse, true);
        Verifications.Post.verifyNewCarResponse(carResponse, carRequest);
        //endregion

        //region Step 2: Call GET API method on /cars endpoint using the ID of the car created in step 1
        CarResponse carGetResponse = GetAPIs.getCar(carResponse._id);
        boolean bPassed = Verifications.Get.verifyCarResponseIsValid(carGetResponse, true).bSuccess;
        //endregion

        //region Step 3: Verify car in the response from step 2 matches the car used for creation in step 1
        if (bPassed)
            Verifications.Get.verifyCarResponse(carGetResponse, carResponse);
        else
            ErrorHandler.addException("Can't verify response from GET API for /cars endpoint "
                    + "for ID of the car created in the step 1 because GET API failed!");
        //endregion

        ErrorHandler.testEnd();
    }
}

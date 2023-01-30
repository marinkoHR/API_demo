package TESTS;

import API.GetAPIs;
import API.Verifications;
import DATA.Responses.CarResponse;
import OTHER.ErrorHandler;
import org.testng.annotations.Test;

import java.util.List;

public class TEST1_VerifyGetCars {

    @Test(description = "TC-1 - Write a test case that will verify GET API method for /cars endpoint")
    public void TEST1() {

        String sInvalidId = "invalidID";
        List<CarResponse> lsAllCars;
        boolean bPassed;

        //region Step 1: Call GET API method on /cars endpoint to get all cars
        lsAllCars = GetAPIs.getCars();
        bPassed = Verifications.Get.verifyCarResponseIsValid(lsAllCars, true).bSuccess;
        //endregion

        //region Step 2: Call GET API method on /cars endpoint using the ID of the car that doesn't exist
        CarResponse failedResponse = GetAPIs.getCar(sInvalidId);
        Verifications.Get.verifyCarResponseIsValid(failedResponse, false);
        Verifications.Get.verifyFailedCarResponse(failedResponse, 404, "Not Found");
        //endregion

        //region Step 3: "Call GET API method on /cars endpoint using ID of the car that exists"
        CarResponse carExpected;
        if (bPassed) {
            if (lsAllCars.size() > 3)
                carExpected = lsAllCars.get(1);
            else
                carExpected = lsAllCars.get(0);
            CarResponse carGetResponse = GetAPIs.getCar(carExpected._id);
            bPassed = Verifications.Get.verifyCarResponseIsValid(carGetResponse, true).bSuccess;
            if (bPassed)
                Verifications.Get.verifyCarResponse(carGetResponse, carExpected);
        } else
            ErrorHandler.addException("Can't verify response from GET API for /cars endpoint "
                    + "for valid ID because API didn't return any car!");
        //endregion

        ErrorHandler.testEnd();
    }
}

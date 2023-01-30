package TESTS;

import API.GetAPIs;
import API.PostAPIs;
import DATA.Requests.CarRequest;
import DATA.Responses.CarResponse;
import org.testng.annotations.Test;

import java.util.List;

public class PrepareTestData {

    @Test(description = "Prepare data for tests")
    public void Prepare() {

        //region Create new cars if there are less than 2 cars
        List<CarResponse> lsAllCars = GetAPIs.getCars();
        int iStart = lsAllCars.size();

        for (int i = iStart; i < 2; i++) {
            String sBrand = "Renault";
            String sModel = "Megane";
            if (i % 2 == 0)
                sModel = "Clio";
            CarRequest carRequest = new CarRequest(sBrand, sModel);
            PostAPIs.createNewCar(carRequest);
        }
        //endregion
    }
}

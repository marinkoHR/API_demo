package DATA.Requests;

import DATA.Other.Strings;

public class CarRequest {

    public String brand;
    public String model;
    public String vin;
    public String driver_id;

    public CarRequest(String sBrand, String sModel) {

        this.brand = sBrand;
        this.model = sModel;
        this.vin = Strings.getRandomVin();
        this.driver_id = "";
    }
}

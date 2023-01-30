package DATA.Responses;

import DATA.Requests.CarRequest;

public class CarResponse extends ErrorResponse {

    public String _id;
    public String brand;
    public String model;
    public String vin;
    public String driver_id;

    public CarResponse() {}

    public CarResponse(CarRequest request) {

        this.brand = request.brand;
        this.model = request.model;
        this.vin = request.vin;
        this.driver_id = request.driver_id;
    }
}

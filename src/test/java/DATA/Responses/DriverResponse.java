package DATA.Responses;

import DATA.Requests.DriverRequest;

import java.util.Date;

public class DriverResponse extends ErrorResponse {

    public String _id;
    public String first_name;
    public String last_name;
    public Date date_of_birth;
    public String driving_licence_number;
    public String car_id;

    public DriverResponse() {}

    public DriverResponse(DriverRequest driverRequest) {

        this.first_name = driverRequest.first_name;
        this.last_name = driverRequest.last_name;
        this.date_of_birth = driverRequest.date_of_birth;
        this.driving_licence_number = driverRequest.driving_licence_number;
        this.car_id = driverRequest.car_id;
    }
}

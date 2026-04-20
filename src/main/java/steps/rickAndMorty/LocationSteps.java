package steps.rickAndMorty;

import api.rickAndMorty.LocationRMApi;
import dto.rickAndMorty.LocationDto;
import org.apache.http.HttpStatus;

public class LocationSteps {

    private static final LocationRMApi locationApi = new LocationRMApi();


    public LocationDto getLocationById(long id) {
        return locationApi.getLocation(id)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(LocationDto.class);
    }
}

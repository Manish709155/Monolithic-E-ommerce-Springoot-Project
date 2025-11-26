package com.ecommerce.app.request;

import com.ecommerce.app.constant.City;
import com.ecommerce.app.constant.CountryEnum;
import com.ecommerce.app.constant.District;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressRequest {

    String streetOrRoadOrBuildingName;

    CountryEnum countryEnum;

    District district;

    City city;

    String zipCode;

    String nearByLocation;

    String houseNumber;

    String state;

}

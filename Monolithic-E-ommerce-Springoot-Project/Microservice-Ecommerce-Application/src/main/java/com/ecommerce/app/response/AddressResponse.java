package com.ecommerce.app.response;

import com.ecommerce.app.constant.City;
import com.ecommerce.app.constant.CountryEnum;
import com.ecommerce.app.constant.District;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class AddressResponse {

    String streetOrRoadOrBuildingName;

    CountryEnum countryEnum;

    District district;

    City city;

    String zipCode;

    String nearByLocation;

    String houseNumber;

    String state;
}

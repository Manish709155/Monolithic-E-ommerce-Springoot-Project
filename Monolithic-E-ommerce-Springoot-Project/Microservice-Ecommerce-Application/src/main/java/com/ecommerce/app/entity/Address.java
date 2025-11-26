package com.ecommerce.app.entity;

import com.ecommerce.app.constant.City;
import com.ecommerce.app.constant.CountryEnum;
import com.ecommerce.app.constant.District;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "address")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address extends SuperEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "street_or_road_or_building_name")
    String streetOrRoadOrBuildingName;

    @Enumerated(EnumType.STRING)
    @Column(name = "country")
    CountryEnum countryEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "district")
    District district;

    @Enumerated(EnumType.STRING)
    @Column(name = "city")
    City city;

    @Column(name="zip_code")
    String zipCode;

    @Column(name="near_by_location")
    String nearByLocation;

    @Column(name="house_number")
    String houseNumber;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    User user;

    @Column(name="state")
    String state;




}

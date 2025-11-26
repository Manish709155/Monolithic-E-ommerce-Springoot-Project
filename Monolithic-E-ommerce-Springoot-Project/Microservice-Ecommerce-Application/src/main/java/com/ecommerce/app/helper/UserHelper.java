package com.ecommerce.app.helper;

import com.ecommerce.app.constant.RoleTypeEnum;
import com.ecommerce.app.entity.Address;
import com.ecommerce.app.entity.User;
import com.ecommerce.app.request.UserRequest;
import com.ecommerce.app.response.AddressResponse;
import com.ecommerce.app.response.UserListResponse;
import com.ecommerce.app.response.UserResponse;
import com.ecommerce.app.service.AddressService;
import com.ecommerce.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserHelper {

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    public Long createUser(UserRequest userRequest){
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmailId(userRequest.getEmailId());
        user.setMiddleName(userRequest.getMiddleName());
        user.setMobileNo(userRequest.getMobileNo());
        user.setGenderType(userRequest.getGenderType());
        user.setCreatedOn(LocalDateTime.now());
        user.setUpdatedOn(LocalDateTime.now());
        user.setRoleType(RoleTypeEnum.USER);
        user.setPassword(userRequest.getPassword());
        // set address
        Address address = new Address();
        address.setCity(userRequest.getAddressRequest().getCity());
        address.setDistrict(userRequest.getAddressRequest().getDistrict());
        address.setCountryEnum(userRequest.getAddressRequest().getCountryEnum());
        address.setZipCode(userRequest.getAddressRequest().getZipCode());
        address.setHouseNumber(userRequest.getAddressRequest().getHouseNumber());
        address.setNearByLocation(userRequest.getAddressRequest().getNearByLocation());
        address.setState(userRequest.getAddressRequest().getState());
        address.setStreetOrRoadOrBuildingName(userRequest.getAddressRequest().getStreetOrRoadOrBuildingName());
        address.setCreatedOn(LocalDateTime.now());
        address.setUpdatedOn(LocalDateTime.now());
        address.setUser(user);
        addressService.createAddress(address);
        userService.createUser(user);
        return  user.getId();

    }
    public List<UserListResponse> getCompanyList() {
        List<User> users=userService.getUserList();
        List<UserListResponse> responses = new ArrayList<UserListResponse>();
        users.forEach(user -> {
            Address address =addressService.findAddressByUserId(user.getId());
            AddressResponse addressResponse= AddressResponse .builder()
                    .state(address.getState())
                    .city(address.getCity())
                    .district(address.getDistrict())
                    .houseNumber(address.getHouseNumber())
                    .nearByLocation(address.getNearByLocation())
                    .countryEnum(address.getCountryEnum())
                    .zipCode(address.getZipCode())
                    .streetOrRoadOrBuildingName(address.getStreetOrRoadOrBuildingName())
                    .build();
            responses.add(UserListResponse.builder()
                    .id(user.getId())
                            .firstName(user.getFirstName())
                            .middleName(user.getMiddleName())
                            .lastName(user.getLastName())
                            .emailId(user.getEmailId())
                            .mobileNo(user.getMobileNo())
                            .roleType(user.getRoleType().getDisplayName())
                            .genderType(user.getGenderType().getDisplayName())
                            .addressResponse(addressResponse)
                    .build());
        });
        return responses;
    }

    public UserResponse getUser(Long userId){
       User user= userService.findByIdAndActiveTrueAndDeletedFalse(userId);
       Address address =addressService.findAddressByUserId(userId);
       AddressResponse addressResponse= AddressResponse .builder()
               .state(address.getState())
               .city(address.getCity())
               .district(address.getDistrict())
               .houseNumber(address.getHouseNumber())
               .nearByLocation(address.getNearByLocation())
               .countryEnum(address.getCountryEnum())
               .zipCode(address.getZipCode())
               .streetOrRoadOrBuildingName(address.getStreetOrRoadOrBuildingName())
               .build();
       return UserResponse.builder()
               .id(user.getId())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .emailId(user.getEmailId())
                .mobileNo(user.getMobileNo())
                .roleType(user.getRoleType().getDisplayName())
                .genderType(user.getGenderType().getDisplayName())
               .addressResponse(addressResponse)
                .build()
               ;
    }
// or fetch single user by user id
    public Optional<UserResponse> fetchSingleUserByUserId(Long id){
        User user=   userService.fetchSingleUserByUserId(id).orElse(null);
        Address address =addressService.findAddressByUserId(id);
        AddressResponse addressResponse= AddressResponse .builder()
                .state(address.getState())
                .city(address.getCity())
                .district(address.getDistrict())
                .houseNumber(address.getHouseNumber())
                .nearByLocation(address.getNearByLocation())
                .countryEnum(address.getCountryEnum())
                .zipCode(address.getZipCode())
                .streetOrRoadOrBuildingName(address.getStreetOrRoadOrBuildingName())
                .build();
        assert user != null;
        return Optional.ofNullable(UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .emailId(user.getEmailId())
                .mobileNo(user.getMobileNo())
                .roleType(user.getRoleType().getDisplayName())
                .genderType(user.getGenderType().getDisplayName())
                        .addressResponse(addressResponse)
                .build());
    }
}

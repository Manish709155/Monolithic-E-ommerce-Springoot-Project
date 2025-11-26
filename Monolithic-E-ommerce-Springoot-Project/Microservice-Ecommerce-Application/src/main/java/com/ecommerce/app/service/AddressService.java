package com.ecommerce.app.service;

import com.ecommerce.app.entity.Address;

public interface AddressService {

    void createAddress(Address address);

    Address findAddressByUserId(Long userId);
}

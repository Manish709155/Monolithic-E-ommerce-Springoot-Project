package com.ecommerce.app.serviceImpl;

import com.ecommerce.app.entity.Address;
import com.ecommerce.app.repository.AddressRepository;
import com.ecommerce.app.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public void createAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public Address findAddressByUserId(Long userId) {
        return addressRepository.findAddressByUserId(userId);
    }
}

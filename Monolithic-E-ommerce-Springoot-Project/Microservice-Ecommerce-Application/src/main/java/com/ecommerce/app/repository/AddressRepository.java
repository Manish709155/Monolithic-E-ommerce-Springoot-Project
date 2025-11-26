package com.ecommerce.app.repository;

import com.ecommerce.app.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

    Address findAddressByUserId(Long userId);
}

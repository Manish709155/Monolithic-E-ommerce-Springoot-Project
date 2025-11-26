package com.ecommerce.app.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class UserResponse {

    Long id;

    String firstName;

    String middleName;

    String lastName;

    String emailId;

    String mobileNo;

    String roleType;

    String genderType;

    AddressResponse addressResponse;
}
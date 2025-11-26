package com.ecommerce.app.request;

import com.ecommerce.app.constant.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

    String firstName;

    String middleName;

    String lastName;

    String password;

    String emailId;

    String mobileNo;

    GenderEnum genderType;

    AddressRequest addressRequest;
}

package com.ecommerce.app.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {

    String password;

    String emailId;

    String mobileNo;
}

package com.ecommerce.app.entity;

import com.ecommerce.app.constant.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends SuperEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Size(min = 1, max = 255)
    @Column(name = "first_Name")
    String firstName;

    @Size(min = 1, max = 255)
    @Column(name = "middle_name")
    String middleName;

    @Size(min = 1, max = 255)
    @Column(name = "last_name")
    String lastName;

    @Size(min = 1, max = 255)
    @Column(name = "password")
    String password;

    @Size(min = 1, max = 255)
    @Column(name = "email_id")
    String emailId;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "mobile_no")
    String mobileNo;


    @Column(name = "role_type")
    @Enumerated(EnumType.STRING)
    RoleTypeEnum roleType;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender_type")
    private GenderEnum genderType;


}

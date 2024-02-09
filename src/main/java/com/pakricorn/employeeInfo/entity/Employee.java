package com.pakricorn.employeeInfo.entity;

import jakarta.persistence.*;

import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "employee")
@SQLDelete(sql = "update employee SET deleted = true WHERE emp_id=?")
@FilterDef(name = "deletedEmployeeFilter", parameters = @ParamDef(name = "isDeleted", type = org.hibernate.type.descriptor.java.BooleanJavaType.class))
@Filter(name = "deletedEmployeeFilter", condition = "deleted = :isDeleted")
public class Employee {

    @Id
    @Column(name = "emp_id")
    @GeneratedValue(generator = "custom_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "custom_id" , initialValue = 1)
    @GenericGenerator(name = "custom_id",strategy = "com.pakricorn.employeeInfo.entity.CustomIdGenerator" )
    private String id;
    private String firstname;
    private String lastname;
    private String address;
    private long mobileNumber;
    private String email;
    private String aadhaar;
    private String panNumber;
    private String projectId;
    private boolean deleted = Boolean.FALSE;
}

package com.pakricorn.employeeInfo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "project")
@SQLDelete(sql = "update project SET deleted = true WHERE id=?")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String projectTitle;

    private  String projectDescription;

    private  String teamMember;

    private String supervisor;


    private boolean deleted = Boolean.FALSE;

}

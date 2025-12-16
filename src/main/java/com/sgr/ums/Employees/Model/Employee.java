package com.sgr.ums.Employees.Model;


import com.sgr.ums.Employees.config.EmployeesStatusCode;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name="uuid",unique = true, nullable = false, updatable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="department")
    private String department;

    @Column(name="age")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false)
    private EmployeesStatusCode status;

    @Column(name="is_active", nullable = false)
    private Boolean isActive;

    @Column(name="created_by", nullable = false)
    private String createdBy;

    @Column(name="created_date")
    private LocalDateTime createdDate;

    @Column(name="created_date_utc")
    private LocalDateTime createdDateUtc;

    @Column(name="updated_by")
    private String updatedBy;

    @Column(name="updated_date")
    private LocalDateTime updatedDate;

    @Column(name="updated_date_utc")
    private LocalDateTime updatedDateUtc;

}
package com.sgr.ums.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "author")
public class Author extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "Id")
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "full_name")
    private String fullName;   // auto hunu parxa , combination of firstName  middleName  lastName (add update grda

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @PrePersist
    @PreUpdate
    public void setFullName() {
        this.fullName = String.join(" ",
                firstName,
                middleName == null ? "" : middleName,
                lastName
        ).replaceAll("\\s+", " ").trim();
    }
}


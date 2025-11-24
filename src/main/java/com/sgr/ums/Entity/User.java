package com.sgr.ums.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name="id")
    private Long id;

    @Column(name="uuid",unique = true, nullable = false, updatable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(name="user_name",unique = true,nullable = false,updatable = false)
    private String userName;

    @Column( name="email",unique = true, nullable = false,updatable = false)
    private String email;

    @Column(name ="password",nullable = false)
    private String password;

    @Column(name ="first_name",nullable =  false)
    private String firstName;


    @Column(name ="middle_name")
    private String middleName;

    @Column(name ="last_name", nullable = false)
    private String lastName;

    @Column(name ="full_name")
    private String fullName;


    @Column(name="nativeName")
    private String nativeName;

    @Column(name="age")
    private Integer age;

    @Column(name = "address")
    private String address;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "country")
    private String country;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "description")
    private String description;

    @Column(name = "isDeleted")
    private boolean isDeleted = false;

    @Column(name = "isActive")
    private boolean isActive = true;

    @CreationTimestamp
    @Column(name = "createdDate")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "updatedDate")
    private LocalDateTime updatedDate;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "updatedBy")
    private String updatedBy;


    @PrePersist
    public void onCreate() {
        this.fullName = String.format("%s %s %s",
                firstName, middleName != null ? middleName : "", lastName).trim();
    }

    public User(){

    }

    //getter


    public String getAddress() {return address;}

    public Integer getAge() {return age;}

    public String getCountry() {return country;}

    public String getCreatedBy() {return createdBy;}

    public LocalDateTime getCreatedDate() {return createdDate;}

    public String getDescription() {return description;}

    public String getEmail() {return email;}

    public String getFirstName() {return firstName;}

    public String getFullName() {return fullName;}

    public Long getId() {return id;}

    public boolean isActive() {return isActive;}

    public boolean isDeleted() {return isDeleted;}

    public String getLastName() {return lastName;}

    public String getMiddleName() {return middleName;}

    public String getNativeName() {return nativeName;}

    public String getPassword() {return password;}

    public String getPhoneNumber() {return phoneNumber;}

    public String getRemarks() {return remarks;}

    public String getUpdatedBy() {return updatedBy;}

    public LocalDateTime getUpdatedDate() {return updatedDate;}

    public String getUserName() {return userName;}

    public UUID getUuid() {return uuid;}



    //setter


    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }


}


package com.sgr.ums.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="student")
public class Student extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "ID")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "age", nullable = false)
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    //setter


    public void setAge(Integer age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student() {

    }

    //constructor
    public Student(String name, Integer age, String email) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Student(Long id, Integer age, String email, String name) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }


}

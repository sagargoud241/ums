package com.sgr.ums.Entity;

import jakarta.persistence.*;

    @Entity
    @Table(name="course")
    public class Course extends BaseEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="Id")
        private  long id;

        @Column(nullable = false, length = 100)
        private String title;

        @Column(columnDefinition = "TEXT")
        private String description;

        @Column(name = "credit_hours", nullable = false)
        private Integer creditHours;


        //getter

        public Integer getCreditHours() {
            return creditHours;
        }

        public String getDescription() {
            return description;
        }

        public long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }


        //setter


        public void setCreditHours(Integer creditHours) {
            this.creditHours = creditHours;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setId(long id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }


        public Course(){}

        public Course(Integer creditHours, String description, long id, String title) {
            this.creditHours = creditHours;
            this.description = description;
            this.id = id;
            this.title = title;
        }

        public Course(String title, String description, Integer creditHours) {
            this.creditHours = creditHours;
            this.description = description;
            this.title = title;
        }

    }



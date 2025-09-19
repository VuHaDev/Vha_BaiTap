package com.example.baitap.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Full name không được để trống")
    @Size(max = 100, message = "Full name tối đa 100 ký tự")
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Size(max = 255, message = "Address tối đa 255 ký tự")
    @Column(length = 255)
    private String address;

    @Pattern(regexp = "\\d{10,20}", message = "Phone phải là số, từ 10 đến 20 chữ số")
    @Column(length = 20)
    private String phone;

    @Past(message = "Ngày sinh phải là ngày trong quá khứ")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    private boolean status = true;

    public Employee() {
    }

    public Employee(Integer id, String fullName, String address, String phone, LocalDate dateOfBirth, boolean status) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

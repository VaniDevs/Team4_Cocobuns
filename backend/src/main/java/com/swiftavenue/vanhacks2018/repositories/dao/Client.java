package com.swiftavenue.vanhacks2018.repositories.dao;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "baby_date_of_birth")
    private Date babyDateOfBirth;

    @Enumerated(EnumType.STRING)
    private Demographic demographic;

    @OneToOne
    @JoinColumn(name = "referred_by_id")
    @JsonBackReference
    private Organization referredBy;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getBabyDateOfBirth() {
        return babyDateOfBirth;
    }

    public void setBabyDateOfBirth(Date babyDateOfBirth) {
        this.babyDateOfBirth = babyDateOfBirth;
    }

    public Demographic getDemographic() {
        return demographic;
    }

    public void setDemographic(Demographic demographic) {
        this.demographic = demographic;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Organization getReferredBy() {
        return referredBy;
    }

    public void setReferredBy(Organization referredBy) {
        this.referredBy = referredBy;
    }
}

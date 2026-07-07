package de.likeherotozero.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.math.BigDecimal;

@Entity
@Table(
        name = "co2_emission",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_country_year",
                        columnNames = {"country_id", "emission_year"}
                )
        }
)
public class Co2Emission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Column(name = "emission_year", nullable = false)
    private Integer year;

    @Column(name = "co2_kt", nullable = false, precision = 15, scale = 2)
    private BigDecimal co2Kt;

    public Co2Emission() {
    }

    public Co2Emission(Country country, Integer year, BigDecimal co2Kt) {
        this.country = country;
        this.year = year;
        this.co2Kt = co2Kt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public BigDecimal getCo2Kt() {
        return co2Kt;
    }

    public void setCo2Kt(BigDecimal co2Kt) {
        this.co2Kt = co2Kt;
    }
}
package com.ez.portal.core.entity;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class Country {
    private String code;
    private String name;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Continent")
    private Continent continent;
    private String region;
    private Double  surfaceArea;
    private Integer indepYear;
    private Long population;
    private Float lifeExpectancy;
    private Double gnp;
    private Double gnpOld;
    private String localName;
    private String governmentForm;
    private String headOfState;
    private Long capital;
    private String code2;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Continent getContinent() {
        return continent;
    }
    public void setContinent(Continent continent) {
        this.continent = continent;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public Double getSurfaceArea() {
        return surfaceArea;
    }
    public void setSurfaceArea(Double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }
    public Integer getIndepYear() {
        return indepYear;
    }
    public void setIndepYear(Integer indepYear) {
        this.indepYear = indepYear;
    }
    public Long getPopulation() {
        return population;
    }
    public void setPopulation(Long population) {
        this.population = population;
    }
    public Float getLifeExpectancy() {
        return lifeExpectancy;
    }
    public void setLifeExpectancy(Float lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }
    public Double getGnp() {
        return gnp;
    }
    public void setGnp(Double gnp) {
        this.gnp = gnp;
    }
    public Double getGnpOld() {
        return gnpOld;
    }
    public void setGnpOld(Double gnpOld) {
        this.gnpOld = gnpOld;
    }
    public String getLocalName() {
        return localName;
    }
    public void setLocalName(String localName) {
        this.localName = localName;
    }
    public String getGovernmentForm() {
        return governmentForm;
    }
    public void setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
    }
    public String getHeadOfState() {
        return headOfState;
    }
    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }
    public Long getCapital() {
        return capital;
    }
    public void setCapital(Long capital) {
        this.capital = capital;
    }
    public String getCode2() {
        return code2;
    }
    public void setCode2(String code2) {
        this.code2 = code2;
    }
}

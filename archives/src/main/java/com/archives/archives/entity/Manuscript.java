package com.archives.archives.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "manuscripts")
public class Manuscript {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String cote;
    private String theme;
    private String manuscriptName;
    private String date;
    private String manufacturingPlace;
    private String conservatingPlace;

    // Constructors, getters, and setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getCote() {
        return cote;
    }
    public void setCote(String cote) {
        this.cote = cote;
    }

    public String getTheme() {
        return theme;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }

        public String getManuscriptName() {
        return manuscriptName;
    }
    public void setManuscriptName(String manuscriptName) {
        this.manuscriptName = manuscriptName;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getManufacturingPlace() {
        return manufacturingPlace;
    }
    public void setManufacturingPlace(String manufacturingPlace) {
        this.manufacturingPlace = manufacturingPlace;
    }

    public String getConservatingPlace() {
        return conservatingPlace;
    }
    public void setConservatingPlace(String conservatingPlace) {
        this.conservatingPlace = conservatingPlace;
    }
}

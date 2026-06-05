package com.archives.archives.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "manuscripts")
public class Manuscript {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String cote;
    private String date;
    private String theme;
    private String support;
    private String dimension;
    private String manuscriptName;
    private String manufacturingPlace;
    private String conservationPlace;
    private String link;

    @OneToMany(mappedBy = "manuscript")
    private List<Folio> folios;

    @JsonIgnoreProperties("manuscripts")
    @ManyToMany(mappedBy = "manuscripts")
    private List<Tag> tags;

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

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
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

    public String getSupport() {
        return support;
    }
    public void setSupport(String support) {
        this.support = support;
    }

    public String getDimension() {
        return dimension;
    }
    public void setDimension(String dimension) {
        this.dimension = dimension;
    }
    
    public String getManufacturingPlace() {
        return manufacturingPlace;
    }
    public void setManufacturingPlace(String manufacturingPlace) {
        this.manufacturingPlace = manufacturingPlace;
    }

    public String getConservationPlace() {
        return conservationPlace;
    }
    public void setConservationPlace(String conservationPlace) {
        this.conservationPlace = conservationPlace;
    }

    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    public List<Folio> getFolios() {
        return folios;
    }
    public void setFolios(List<Folio> folios) {
        this.folios = folios;
    }

    public List<Tag> getTags() {
    return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

}

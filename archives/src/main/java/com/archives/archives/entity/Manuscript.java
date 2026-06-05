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
    private String link;
    private String manuscriptName;

    private Person author;
    private Person translator;
    private Person illuminator;
    private Person recipient;


    @ManyToOne
    private Place manufacturingPlace;

    @ManyToOne
    private Place conservationPlace;

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

    public Person getAuthor() {
        return author;
    }
    public void setAuthor(Person author) {
        this.author = author;
    }

    public Person getTranslator() {
        return translator;
    }
    public void setTranslator(Person translator) {
        this.translator = translator;
    }

    public Person getIlluminator() {
        return illuminator;
    }
    public void setIlluminator(Person illuminator) {
        this.illuminator = illuminator;
    }

    public Person getRecipient() {
        return recipient;
    }
    public void setRecipient(Person recipient) {
        this.recipient = recipient;
    }
    
    public Place getManufacturingPlace() {
        return manufacturingPlace;
    }
    public void setManufacturingPlace(Place manufacturingPlace) {
        this.manufacturingPlace = manufacturingPlace;
    }

    public Place getConservationPlace() {
        return conservationPlace;
    }
    public void setConservationPlace(Place conservationPlace) {
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

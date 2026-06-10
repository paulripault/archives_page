package com.archives.archives.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @Column(columnDefinition = "TEXT")
    private String theme;

    private String support;
    private String dimension;
    private String link;

    @Column(columnDefinition = "TEXT")
    private String manuscriptName;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Person author;

    @ManyToOne
    @JoinColumn(name = "translator_id")
    private Person translator;

    @ManyToOne
    @JoinColumn(name = "illuminator_id")
    private Person illuminator;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Person recipient;

    @ManyToOne
    private Place manufacturingPlace;

    @ManyToOne
    private Place conservationPlace;

    @OneToMany(mappedBy = "manuscript", fetch = FetchType.LAZY)
    private Set<Folio> folios;

    @ManyToMany
    @JoinTable(
        name = "manuscripts_person_tags", 
        joinColumns = @JoinColumn(name = "manuscript_id"), 
        inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> personTags = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "manuscripts_place_tags", 
        joinColumns = @JoinColumn(name = "manuscript_id"), 
        inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> placeTags = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "manuscripts_word_tags", 
        joinColumns = @JoinColumn(name = "manuscript_id"), 
        inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> wordTags = new ArrayList<>();

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

    public Set<Folio> getFolios() {
        return folios;
    }

    public void setFolios(Set<Folio> folios) {
        this.folios = folios;
    }

    public List<Tag> getPersonTags() {
        return personTags;
    }

    public void setPersonTags(List<Tag> personTags) {
        this.personTags = personTags;
    }

    public List<Tag> getPlaceTags() {
        return placeTags;
    }

    public void setPlaceTags(List<Tag> placeTags) {
        this.placeTags = placeTags;
    }

    public List<Tag> getWordTags() {
        return wordTags;
    }

    public void setWordTags(List<Tag> wordTags) {
        this.wordTags = wordTags;
    }

}

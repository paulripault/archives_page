package com.archives.archives.dto;

import java.util.List;

public class ManuscriptDTO {

    private Long id;

    private String title;
    private String cote;
    private String date;
    private String theme;
    private String manuscriptName;
    private String support;
    private String dimension;
    private String link;

    private PersonDTO author;
    private PersonDTO translator;
    private PersonDTO illuminator;
    private PersonDTO recipient;

    private PlaceDTO manufacturingPlace;
    private PlaceDTO conservationPlace;


    private List<TagDTO> personTags;
    private List<TagDTO> placeTags;
    private List<TagDTO> wordTags;
    private List<FolioDTO> folios;

    // Getters and setters (or use Lombok @Data for brevity)
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

    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    public PersonDTO getAuthor() {
        return author;
    }
    public void setAuthor(PersonDTO author) {
        this.author = author;
    }

    public PersonDTO getTranslator() {
        return translator;
    }
    public void setTranslator(PersonDTO translator) {
        this.translator = translator;
    }

    public PersonDTO getIlluminator() {
        return illuminator;
    }
    public void setIlluminator(PersonDTO illuminator) {
        this.illuminator = illuminator;
    }

    public PersonDTO getRecipient() {
        return recipient;
    }
    public void setRecipient(PersonDTO recipient) {
        this.recipient = recipient;
    }

    public PlaceDTO getManufacturingPlace() {
        return manufacturingPlace;
    }
    public void setManufacturingPlace(PlaceDTO manufacturingPlace) {
        this.manufacturingPlace = manufacturingPlace;
    }

    public PlaceDTO getConservationPlace() {
        return conservationPlace;
    }
    public void setConservationPlace(PlaceDTO conservationPlace) {
        this.conservationPlace = conservationPlace;
    }

    public List<TagDTO> getPersonTags() {
    return personTags;
    }
    public void setPersonTags(List<TagDTO> personTags) {
        this.personTags = personTags;
    }

    public List<TagDTO> getPlaceTags() {
    return placeTags;
    }
    public void setPlaceTags(List<TagDTO> placeTags) {
        this.placeTags = placeTags;
    }

    public List<TagDTO> getWordTags() {
    return wordTags;
    }
    public void setWordTags(List<TagDTO> wordTags) {
        this.wordTags = wordTags;
    }

    public List<FolioDTO> getFolios() {
        return folios;
    }
    public void setFolios(List<FolioDTO> folios) {
        this.folios = folios;
    }

}

package com.archives.archives.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "folios")
public class Folio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String page;
    private String folio;
    
    @Column(columnDefinition = "TEXT")
    private String sectionName;
    private String illuminationPosition;

    @Column(columnDefinition = "TEXT")
    private String transcription;
    private String zoom;
    private String illuminationType;

    @Column(columnDefinition = "TEXT")
    private String description;

    @JsonIgnoreProperties({"folios"})
    @ManyToOne
    @JoinColumn(name = "manuscript_id")
    private Manuscript manuscript;

    // Constructors, getters, and setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;   
    }

    public String getPage() {
        return page;
    }
    public void setPage(String page) {
        this.page = page;
    }

    public String getFolio() {
        return folio;
    }
    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getSectionName() {
        return sectionName;
    }
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getIlluminationPosition() {
        return illuminationPosition;
    }
    public void setIlluminationPosition(String illuminationPosition) {
        this.illuminationPosition = illuminationPosition;
    }

    public String getTranscription() {
        return transcription;
    }
    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public String getZoom() {
        return zoom;
    }
    public void setZoom(String zoom) {
        this.zoom = zoom;
    }

    public String getIlluminationType() {
        return illuminationType;
    }
    public void setIlluminationType(String illuminationType) {
        this.illuminationType = illuminationType;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Manuscript getManuscript() {
        return manuscript;
    }
    public void setManuscript(Manuscript manuscript) {
        this.manuscript = manuscript;
    }

}

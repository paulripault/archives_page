package com.archives.archives.dto;

public class FolioDTO {
    
    public Long id;
    public String page;
    public String folio;
    public String sectionName;
    public String illuminationPosition;
    public String transcription;
    public String zoom;
    public String illuminationType;
    public String description;

    // Getters and setters (or use Lombok @Data for brevity)

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

}

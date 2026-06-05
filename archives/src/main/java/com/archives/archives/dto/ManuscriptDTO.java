package com.archives.archives.dto;

import java.util.List;

public class ManuscriptDTO {
    
    public Long id;
    public String title;
    public String cote;
    public String date;
    public String theme;
    public String manuscriptName;
    public String support;
    public String dimension;
    public String manufacturingPlace;
    public String conservationPlace;
    public String link;

    public List<String> tags;

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

}

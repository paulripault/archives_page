package com.archives.archives.dto;

public class TagDTO {

    private Long id;
    private String name;

    // Getters and setters (or use Lombok @Data for brevity)

    public Long getId() {
        return id;
    }  
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}

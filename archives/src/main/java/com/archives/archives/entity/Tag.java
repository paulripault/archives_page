package com.archives.archives.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
        name = "manuscript_tags",
        joinColumns = @JoinColumn(name = "tag_id"),
        inverseJoinColumns = @JoinColumn(name = "manuscript_id")
    )
    private List<Manuscript> manuscripts;

    // Constructors, getters, and setters

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

    @JsonIgnore // To prevent infinite recursion during JSON serialization
    public List<Manuscript> getManuscripts() {
        return manuscripts;
    }
    public void setManuscripts(List<Manuscript> manuscripts) {
        this.manuscripts = manuscripts;
    }
}

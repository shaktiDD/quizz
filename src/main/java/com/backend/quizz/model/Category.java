package com.backend.quizz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;


@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;
    private String title;
    private String description;



    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Quiz> quizSet = new LinkedHashSet<>();

    public Category(){};

    public Category(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Set<Quiz> getQuizSet() {
        return quizSet;
    }

    public void setQuizSet(Set<Quiz> quizSet) {
        this.quizSet = quizSet;
    }
}

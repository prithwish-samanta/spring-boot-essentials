package dev.prithwish.spring_boot_thymeleaf_example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
public class Blog {
    @Id
    @UuidGenerator
    private UUID id;
    private String title;
    private String subTitle;
    private String createdBy;
    @CreationTimestamp
    private Date publishedDate;

    public Blog() {
    }

    public Blog(UUID id, String title, String subTitle, String createdBy, Date publishedDate) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.createdBy = createdBy;
        this.publishedDate = publishedDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }
}

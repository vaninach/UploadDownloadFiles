/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author vani
 */
@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length=512,nullable=false,unique=true)
    private String name;
    
    
    private Long size;
    private Date UploadDate;
    private byte[] content;

    public Document() {
    }

    public Document(Long id, String name, Long size) {
        super();
        this.id = id;
        this.name = name;
        this.size = size;
    }
    
    

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getSize() {
        return size;
    }

    public Date getUploadDate() {
        return UploadDate;
    }

    public byte[] getContent() {
        return content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public void setUploadDate(Date UploadDate) {
        this.UploadDate = UploadDate;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    
}

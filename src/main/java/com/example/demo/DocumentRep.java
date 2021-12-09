/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vani
 */
@Repository
public interface DocumentRep extends JpaRepository<Document, Long>{
//    @Query("SELECT d FROM document d ORDER BY d.UploadDate DESC")
//    List <Document> findAll(); 
}

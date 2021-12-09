/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;


import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author vani
 */

@Controller
public class DocumentControllar {
    
    @Autowired
    private DocumentRep rep;
    
    
    @GetMapping("/")
    public String Main(Model model){
        
        List<Document> listaDocs=rep.findAll();
        model.addAttribute("listaDocs",listaDocs);
        
        return "main.html";
    }
    
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("document")MultipartFile multipartfile,RedirectAttributes ra) throws IOException{
        String Filename=StringUtils.cleanPath(multipartfile.getOriginalFilename());
        
        Document document=new Document();
        document.setName(Filename);
        document.setContent(multipartfile.getBytes());
        document.setSize(multipartfile.getSize());
        document.setUploadDate(new Date());
        
        rep.save(document);
        
        ra.addFlashAttribute("mensaje", "The File has been uploaded succesfully.");
       
        
        return "redirect:/";
    }
    
    
    @GetMapping("/download")
    public void downloadFile(@Param("id") Long id, HttpServletResponse response) throws Exception {
        Optional<Document> result=rep.findById(id);
        
        if(!result.isPresent()){
            throw new Exception("Could not find Document with ID="+id);
        }
        
        Document document = result.get();
        
        response.setContentType("application/octet-stream");
        String headerKey="Content-Disposition";
        String headerValue="attachment; filename="+ document.getName() ;
        
        response.setHeader(headerKey, headerKey);
        
        
        ServletOutputStream outputStream= response.getOutputStream();
        
        outputStream.write(document.getContent());
        outputStream.close();
        
        
    } 
    
}

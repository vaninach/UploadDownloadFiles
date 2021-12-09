package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class DemoApplicationTests {

    @Autowired
    private DocumentRep rep;
    
    @Autowired
    private TestEntityManager entityManager;
    
    
	@Test
        @Rollback(false)
	void testInserDocument() throws IOException {
            File file= new File("/home/vani/Documents/discurso-egresados.pdf");
            Document document=new Document();
            document.setName(file.getName());
            
            byte[] bytes=Files.readAllBytes(file.toPath());
            
            document.setContent(bytes);
            Long fileSize=Long.valueOf(bytes.length);
            document.setSize(fileSize );
            document.setUploadDate(new Date());
            
            Document saveDoc=rep.save(document);
            Document existDoc=entityManager.find(Document.class, saveDoc.getId());
            
            assertThat(existDoc.getSize()).isEqualTo(fileSize);
	}

}

package tn.corilus.corilus;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.corilus.corilus.Controller.FileController;
import tn.corilus.corilus.Entities.File;
import tn.corilus.corilus.Entities.ResponseDataFile;
import tn.corilus.corilus.Services.FileService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.mockito.Mockito.when;
@SpringBootTest
@ExtendWith(MockitoExtension.class)

@WebMvcTest(controllers = FileController.class)
public class FileTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private FileService fileService;
    @Autowired
    private FileController fileController;

    @Mock
    private MultipartFile multipartFile;




    @Test
    public void testDownloadFile() {
        String url = "https://www.example.com/sample.pdf";

        String fileName = "sample.pdf";

        boolean success = fileService.downloadFile(url, fileName);

        assertTrue(success);
    }


}
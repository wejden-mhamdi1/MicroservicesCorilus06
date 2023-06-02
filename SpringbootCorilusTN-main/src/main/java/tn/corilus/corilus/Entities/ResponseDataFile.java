package tn.corilus.corilus.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ResponseDataFile {
    private String fileName;
    private String downloadURL;
    private String fileType;
    private String message;
    private long fileSize;

    public ResponseDataFile(String message) {
        this.message = message;
    }

    public ResponseDataFile(String fileName, String downloadURL, String fileType, long fileSize) {
        this.fileName = fileName;
        this.downloadURL = downloadURL;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }
}


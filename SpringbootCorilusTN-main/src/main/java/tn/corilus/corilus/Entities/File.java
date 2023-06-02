package tn.corilus.corilus.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor

public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String fileName;
    private boolean essa;
    private int scorefileErr;
    private String fileType;
    @NonNull
    @Column(name = "keyyy")
    private SecretKey key;

    @Enumerated(EnumType.STRING)
    Status status;
    @Enumerated(EnumType.STRING)
    ChiffrementEnum chiffrementEnum;

    @OneToMany(mappedBy = "fichier")
    @JsonIgnore
    private List<Enregistrement> enregistrements = new ArrayList<>();
    @OneToMany(mappedBy = "file",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Segment> segments = new ArrayList<>();
    @OneToMany(mappedBy = "file",fetch = FetchType.LAZY)

    @JsonIgnore
    private List<Zone> zones = new ArrayList<>();
    @OneToMany(mappedBy = "file",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RecordFooter> records = new ArrayList<>();
    @OneToMany(mappedBy = "file",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Erreur> erreurs = new ArrayList<>();

    @Lob
    @Column(length = 5000)
    private byte[] data;




    public File(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }


    public File(String fileName) {
        this.fileName = fileName;
    }

    public File( String fileName, byte[] data) {
        this.data = data;
        this.fileName = fileName;
    }
}


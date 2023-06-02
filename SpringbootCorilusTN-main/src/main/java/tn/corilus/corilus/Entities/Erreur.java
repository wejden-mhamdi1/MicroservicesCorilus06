package tn.corilus.corilus.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Erreur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double majBijw;
    private double modifType;
    @Lob
    @Column( length = 5000 )
    private String errorNature;

    @Column( length = 5000 )
    private String codeError;
    @Lob
    @Column( length = 5000 )
    private String belgeDescription;
    @Lob
    @Column( length = 5000 )
    private String frenshDescription;
    @Lob
    @Column( length = 5000 )
    private String  remarque;
    @Lob
    @Column( length = 5000 )
    private String data;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Zone zone;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private File file;
}

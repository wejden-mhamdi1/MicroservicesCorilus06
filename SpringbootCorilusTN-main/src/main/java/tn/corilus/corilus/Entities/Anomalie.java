package tn.corilus.corilus.Entities;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Anomalie {
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
    private String remarque;

}

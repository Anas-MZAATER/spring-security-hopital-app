package net.anas.jee_demo.entities;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import java.util.Date;

@Entity
@Table(name="patients")
@Data
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
@Builder
public class Patient {
    @Id
    //AUTO INCREMENT
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private int score;
    private Boolean malade;
}

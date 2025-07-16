package net.anas.jee_demo.entities;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name="patients")
//@Data //l'annotation data equivale les annotation getters et setters
// et toString et de methode de hashCode et autre methode
// se qui est defavorable parcequ'il peut rentrer en conflie avec des situation jpa

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString

//Builder une design pattern utiliser quand on a des objet complexe a inisialiser
//permet de ne pas savoir l'order de parametre
//permet de ne pas utiliser tout le parametre
@Builder

public class Patient {
    @Id
    //AUTO INCREMENT
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "First name is required")
    @Size(min = 3, max = 30)
    private String nom;

    @NotEmpty(message = "Last name is required")
    private String prenom;

    @NotNull(message = "Date of birth is required")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;

    @NotNull
    @DecimalMin("1")
    @DecimalMax("100")
    private int score;

    private Boolean malade;
}

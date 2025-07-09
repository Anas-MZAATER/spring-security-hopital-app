package net.anas.jee_demo.repository;

import net.anas.jee_demo.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


//pour travailler avec spring Data (les methode save,find,findAll,findBy)
//vous devez seulement de presiser l'entiter que vous voulez gerer de quel type et sont identifiant de quel type
//et par justement creer l'entite JPA et sa propre interface
//le travail de l'accee au donnee/le mapping objet relationnel se terminer
//aussi on est pas besoin de creer une fichier persistance.xml parceque spring genere deja
//un fichier application.properties ou on met tout les proprieter qu'on veut

public interface PatientRepository extends JpaRepository<Patient, Long> {


//POUR AJOUTER UNE METHOD QUI N'EXISTE PAS DANS SPRING DATA
// si son des convention
    public Page<Patient> findByNomContainingIgnoreCaseOrPrenomContainsIgnoreCase(String kw1,String prenom, Pageable pageable);
//si non
    @Query("select p from Patient p where p.nom like :x and p.score>:y")
    public Page<Patient> search(@Param("x") String x,@Param("y") String y, Pageable pageable);


////obligatoire d'ajouter un parametre de type Pageable puisque la methode return une Page
////pour faire la pagination(comme sorteable dans :spring Data)
////    Page<Patient> findByNomContainingIgnoreCase(String keyword, Pageable pageable);

}

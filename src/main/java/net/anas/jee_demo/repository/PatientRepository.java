package net.anas.jee_demo.repository;

import net.anas.jee_demo.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface PatientRepository extends JpaRepository<Patient, Long> {

    ////obligatoire d'ajouter un parametre de type Pageable puisque la methode return une Page
    ////pour faire la pagination(comme sorteable dans :spring Data).
    public Page<Patient> findByNomContainingIgnoreCaseOrPrenomContainsIgnoreCase(String kw1,String prenom, Pageable pageable);

    @Query("select p from Patient p where p.nom like :x and p.score>:y")
    public Page<Patient> search(@Param("x") String x,@Param("y") String y, Pageable pageable);



}

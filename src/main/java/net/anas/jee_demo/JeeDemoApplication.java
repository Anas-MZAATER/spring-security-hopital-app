package net.anas.jee_demo;

import net.anas.jee_demo.entities.Patient;
import net.anas.jee_demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

//@SpringBootApplication
//public class JeeDemoApplication implements CommandLineRunner {
//
//    @Autowired
//    //Injecter les dependance via la class springData, class creer par spring
//    // donc vous n'ai obliger pas de creer une class spring le creer pour vous
//    private PatientRepository patientRepository;
//
//    public static void main(String[] args) {
//        SpringApplication.run(JeeDemoApplication.class, args);
//        //par implimeter l'interface "CommandLineRunner" spring des le demarage execute la method run
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        //pour executer une code au demarage il suffit de l'ecrire ici
//
//// SPRING DATA
////ajouter des patient
//    //using AllArgConstrurture
//        patientRepository.save(new Patient(null, "reda", "kriat", new Date(), 10, false));
//
//        Patient p1 = new Patient(null, "walid", "Treref", new Date(), 10, true);
//        patientRepository.save(p1);
//
//    //using NoArgConstrurture
//        Patient p2 = new Patient();
//        p2.setNom("Anas");
//        p2.setPrenom("Kamal");
//        p2.setDateNaissance(new Date());
//        p2.setScore(10);
//        p2.setMalade(true);
//        patientRepository.save(p2);
//
//
//    //using the builder:une design pattern utiliser quand on a des objet complexe a inisialiser
//        Patient p3 = Patient.builder()
//                    .nom("Anas")
//                    .prenom("Mzaatar")
//                    .dateNaissance(new Date())
//                    .score(100)
//                    .malade(false)
//                    .build();
//        patientRepository.save(p3);
//
//
////returner des patient
//        List<Patient> patients = patientRepository.findAll();
//
//        for (Patient patient : patients) {
//            System.out.println(patient);
//        }
//
////        patients.forEach(p ->{
////            System.out.println(p.getId());
////            System.out.println(p.getNom());
////            System.out.println(p.getPrenom());
////            System.out.println("***********");
////        });
//
////            for (Patient p : patients) {
////                System.out.println(p.toString());
////            }
//
//
//// SI J'AI BESOIN DES METHOD QUE N'EXISTE PAS DANS SPRING DATA
////J'ALLER VERS L'INTERFACE patientRepository POUR L'AJOUTER
//        patientRepository.findByNomContainingIgnoreCaseOrPrenomContainsIgnoreCase("s","m", Pageable.ofSize(8));
//    }
//
//
//}



//=============================================2eme method=================================================//


@SpringBootApplication
public class JeeDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JeeDemoApplication.class, args);
        //par implimeter l'interface "CommandLineRunner" spring des le demarage execute la method run
    }

    @Bean
    public CommandLineRunner start(PatientRepository patientRepository) {


//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                System.out.println("Hello World!");
//                Patient p1 = new Patient();
//                    p1.setNom("Anas");
//                    p1.setPrenom("Kamal");
//                    p1.setDateNaissance(new Date());
//                    p1.setScore(10);
//                    p1.setMalade(true);
//
//                    patientRepository.save(p1);
//            }
//        };

        // using lamda expression
                return args -> {
                    List<Patient> patients = patientRepository.findAll();

//                    for (Patient p : patients) {
//                        System.out.println(p.toString());
//                    }
                    System.out.println("Hello Mr. Engineer!");
                };

    }
}
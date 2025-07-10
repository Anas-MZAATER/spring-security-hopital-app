package net.anas.jee_demo;

import net.anas.jee_demo.entities.Patient;
import net.anas.jee_demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class JeeDemoApplication implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(JeeDemoApplication.class, args);
        //par implimeter l'interface "CommandLineRunner" spring des le demarage execute la method run
    }

    @Override
    public void run(String... args) throws Exception {
        //ajouter des patient VIA SPRING DATA
        patientRepository.save(new Patient(null, "reda", "rachid", new Date(), 100, false));

        Patient p1 = new Patient(null, "walid", "hamid", new Date(), 200, false);
        patientRepository.save(p1);


        Patient p2 = Patient.builder()
                    .nom("Anas")
                    .prenom("kamal")
                    .dateNaissance(new Date())
                    .score(300)
                    .build();
        patientRepository.save(p2);

        //returner des patient VIA SPRING DATA
        List<Patient> patients = patientRepository.findAll();

//        for (Patient patient : patients) {
//            System.out.println(patient);
//        }

        patients.forEach(p->{
            System.out.println(p.toString());
        });




    }

//===============================================2EME METHODE=============================================================//
    //@Bean
    public CommandLineRunner start(PatientRepository patientRepository) {
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                System.out.println("Hello World!");
//            }
//        }
        ////WITH LMDA EXPRESSION
        return args -> {
            Patient p1 = new Patient();
            p1.setNom("walid");
            p1.setPrenom("hamid");
            p1.setDateNaissance(new Date());
            p1.setScore(10);
            p1.setMalade(true);

            Patient p2 = new Patient(null, "reda", "rachid", new Date(), 10, false);

            Patient p3 = Patient.builder()
                    .nom("Anas")
                    .prenom("Kamal")
                    .dateNaissance(new Date())
                    .malade(false)
                    .build();

            patientRepository.save(p1);
            patientRepository.save(p2);
            patientRepository.save(p3);

            List<Patient> patients = patientRepository.findAll();
            patients.forEach(p ->{
                System.out.println(p.getId());
                System.out.println(p.getNom());
                System.out.println(p.getPrenom());
                System.out.println("***********");
            });
        };
    }


}


package net.anas.jee_demo.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.anas.jee_demo.Exception.PatientNotFoundException;
import net.anas.jee_demo.entities.Patient;
import net.anas.jee_demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@AllArgsConstructor//injection des dependance via le controlleur ;annotation
public class PatientController {

//    @Autowired//injection des dependance via setter;annotation

////    injection des depondance via un constructeur avec paramertre
//    public PatientController(PatientRepository patientRepository) {
//        this.patientRepository = patientRepository;
//    }

    private PatientRepository patientRepository;


//// sans pagination
//    @GetMapping("/patients")
//    public String index(Model model){
//        List<Patient> patients = patientRepository.findAll();
//        model.addAttribute("listePatients", patients);
//        return "patients";
//    }

    ////ajouter la pagination
    @GetMapping("/patients")
    public String index(Model model,
                        @RequestParam(name ="page",defaultValue ="0") int p,
                        @RequestParam(name = "size", defaultValue = "7") int size,
                        @RequestParam(name = "keyword", defaultValue = "") String kw){
        model.addAttribute("patient", new Patient());
        Page<Patient> pagePatients = patientRepository.findByNomContainingIgnoreCaseOrPrenomContainsIgnoreCase(kw,kw,PageRequest.of(p,size));
        model.addAttribute("listePatients",pagePatients.getContent());
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",p);
        model.addAttribute("keyword",kw);
        return "patients";
    }
    @GetMapping("/")
    public String home(){
//        a revoire to make it index
        return "redirect:/patients";
    }

    @GetMapping("/deletePatient")
    //pas recomander de faire la supression par get puisque il peut suprimer des items par changer justement dans le lien
    //utilise put ou delete
    public String deletePatient(
            Model model,
            @RequestParam(name = "id")Long id,
            @RequestParam(name = "page" ,defaultValue = "0") int page,
            @RequestParam(name = "keyword",defaultValue = "") String keyword,
            RedirectAttributes redirectAttributes) {
        patientRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Patient deleted successfully");
        return "redirect:/patients?page="+page+"&keyword="+keyword;
    }


    @GetMapping("/formPatients")
    public String formPatients(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatients";
    }

//    a revoir pourquoi path
    @PostMapping( path = "/savePatient")
    public String savePatient(
//            When used with a method parameter
//            @ModelAttribute binds the incoming form data to an object.
//            This is particularly useful for handling form submissions/les formulaire.
//            @ModelAttribute("patient"),
            Model model,
            //a revoir what name represent
            @RequestParam(name = "keyword",defaultValue = "") String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @Valid Patient patient,
            BindingResult bindingResult, // BindingResult comes right after @ModelAttribute or @valid
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            ////with out model just pages
            model.addAttribute("errorMessage", "Veuillez respecter les tout les champs.");
            return "formPatients";
            ////with model
//            redirectAttributes.addFlashAttribute("errorMessage", "Veuillez respecter les tout les champs.");
////    ??????????????        model.addAttribute("hasErrors", true); // utile pour rouvrir modal
//            return "redirect:/patients"; // ne pas rediriger pour affiche le formulaire avec les erreurs
        }
        patientRepository.save(patient);
        redirectAttributes.addFlashAttribute("successMessage", "Action passed successfully");
        return "redirect:/patients?page="+page+"&keyword="+keyword;
    }




//// edit with out model
    @GetMapping("/editPatient")
    public String editPatient(
            @RequestParam Long id,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            Model model) {
//            if (id == null) {
//                throw new IllegalArgumentException("L'ID du patient est requis.");
//            }
            // Logique d'extraction du patient
            Patient patient = patientRepository.findById(id).orElse(null);
            if (patient == null) {
                throw new PatientNotFoundException("Patient introuvable!");
            }
            model.addAttribute("patient", patient);
            model.addAttribute("keyword", keyword);
            model.addAttribute("page", page);
            return "editPatient"; // Assurez-vous que 'editPatient.html' est présent
    }



}

package net.anas.jee_demo.web;

import lombok.AllArgsConstructor;
import net.anas.jee_demo.entities.Patient;
import net.anas.jee_demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@Controller
@AllArgsConstructor//injection des dependance
public class PatientController {
//    @Autowired//ou bien de ici:injection des dependance//ou bien via generer un constructeur avec paramertre
    private PatientRepository patientRepository;

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name ="page",defaultValue ="0") int p,
//                        int page =Integer.parseInt(request.getParameter("page"))
                        @RequestParam(name = "size", defaultValue = "5") int size,
                        @RequestParam(name = "keyword", defaultValue = "") String kw ){
//        List<Patient> patients = patientRepository.findAll();
//        model.addAttribute("listePatients", patients);
        //AJOUTER LA PAGINATION
        Page<Patient> pagePatients = patientRepository.findByNomContainingIgnoreCaseOrPrenomContainsIgnoreCase(kw,kw,PageRequest.of(p,size));
        model.addAttribute("listePatients",pagePatients.getContent());
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",p);
        model.addAttribute("keyword",kw);
        return "patients";
    }

//    @GetMapping("/index")
//    public String index(Model model){
////        List<Patient> patients = patientRepository.findAll();
////        model.addAttribute("listePatients", patients);
//        return "patients";
//    }

    @GetMapping("/")
    public String home(){
    return "redirect:/index";
}

    @GetMapping("/deletePatient")
//pas recomander de faire la supression par get puisque il peut suprimer des items par changer justement dans le lien
    //utilise put ou delete
    public String deletePatient(@RequestParam(name = "id")Long id,
                                @RequestParam(name = "page" ,defaultValue = "0") int page,
                                @RequestParam(name = "keyword",defaultValue = "") String keyword) {
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }


}

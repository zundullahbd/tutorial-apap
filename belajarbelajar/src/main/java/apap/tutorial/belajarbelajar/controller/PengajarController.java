package apap.tutorial.belajarbelajar.controller;

import apap.tutorial.belajarbelajar.model.CourseModel;
import apap.tutorial.belajarbelajar.model.PengajarModel;
import apap.tutorial.belajarbelajar.service.CourseService;
import apap.tutorial.belajarbelajar.service.PengajarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class
PengajarController {
    @Qualifier("pengajarServiceImpl")
    @Autowired
    private PengajarService pengajarService;

    @Qualifier("courseServiceImpl")
    @Autowired
    private CourseService courseService;

    @GetMapping("/pengajar/add/{code}")
    public String addPengajarFormPage(@PathVariable String code, Model model) {
        PengajarModel pengajar = new PengajarModel();
        CourseModel course = courseService.getCourseByCodeCourse(code);
        pengajar.setCourse(course);
        model.addAttribute("pengajar", pengajar);
        return "form-add-pengajar";
    }

    @PostMapping("/pengajar/add")
    public String addPengajarSubmitPage(@ModelAttribute PengajarModel pengajar, Model model) {
        pengajarService.addPengajar(pengajar);
        model.addAttribute("noPengajar", pengajar.getNoPengajar());
        return "add-pengajar";
    }
    @GetMapping("/pengajar/delete/{noPengajar}/{code}")
    public String deletePengajar(@PathVariable String noPengajar, @PathVariable String code, Model model) {
        CourseModel course = courseService.getCourseByCodeCourse(code);
        PengajarModel pengajar = pengajarService.findPengajar(Long.parseLong(noPengajar));
        if(courseService.courseIsClosed(course)) {
            pengajarService.deletePengajar(pengajar);
            model.addAttribute("noPengajar", pengajar.getNoPengajar());
            return "delete-pengajar";
        }else {
            String errorMsg = "Error: Course masih dibuka";
            model.addAttribute("errorMsg", errorMsg);
            return "error";
        }
    }

    @GetMapping("/pengajar/update/{noPengajar}/{code}")
    public String updatePengajarFormPage(@PathVariable Long noPengajar,@PathVariable String code , Model model) {
        CourseModel course = courseService.getCourseByCodeCourse(code);
        PengajarModel pengajar = pengajarService.findPengajar(noPengajar);
        if(courseService.courseIsClosed(course)) {
            model.addAttribute("pengajar", pengajar);
            return "form-update-pengajar";
        }else {
            String errorMsg = "Error: Course masih dibuka";
            model.addAttribute("errorMsg", errorMsg);
            return "error";
        }
    }

    @PostMapping("/pengajar/update")
    public String updatePengajarSubmitPage(@ModelAttribute PengajarModel pengajar, Model model) {
        pengajarService.updatePengajar(pengajar);
        model.addAttribute("pengajar", pengajar.getNoPengajar());
        return "update-pengajar";
    }

}

package apap.tutorial.belajarbelajar.controller;

import java.util.List;

import apap.tutorial.belajarbelajar.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tutorial.belajarbelajar.model.CourseModel;
import apap.tutorial.belajarbelajar.service.CourseService;
import apap.tutorial.belajarbelajar.model.PengajarModel;


@Controller
public class CourseController {
    @Qualifier("courseServiceImpl")
    @Autowired
    private CourseService courseService;

    @GetMapping("/course/add")
    public String addCourseFormPage(Model model) {
        model.addAttribute("course", new CourseModel());
        return "form-add-course";
    }

    @PostMapping("/course/add")
    public String addCourseSubmitPage(@ModelAttribute CourseModel course, Model model) {
        courseService.addCourse(course);
        model.addAttribute("code", course.getCode());
        return "add-course";
    }

    @GetMapping("/course/viewall")
    public String listCourse(Model model) {
        List<CourseModel> listCourse = courseService.getListCourse();
        model.addAttribute("listCourse", listCourse);
        return "viewall-course";
    }

    @GetMapping("/course/view-all-ordered")
    public String listCourseInOrder(Model model) {
        List<CourseModel> listCourse = courseService.getListCourseInOrder();
        model.addAttribute("listCourse", listCourse);
        return  "viewall-course";
    }

    @GetMapping("/course/view")
    public String viewDetailCoursePage(@RequestParam(value = "code") String code, Model model) {
        CourseModel course = courseService.getCourseByCodeCourse(code);
        List<PengajarModel> listPengajar = course.getListPengajar();
        model.addAttribute("listPengajar", listPengajar);
        model.addAttribute("course", course);

        return "view-course";
    }

    @GetMapping("/course/view-query")
    public String viewDetailCoursePageQuery(@RequestParam(value = "code") String code, Model model) {
        CourseModel course = courseService.getCourseByCodeCourseQuery(code);
        List<PengajarModel> listPengajar = course.getListPengajar();
        model.addAttribute("listPengajar", listPengajar);
        model.addAttribute("course", course);

        return "view-course";
    }

    @GetMapping("/course/update/{code}")
    public String updateCourseFormPage(@PathVariable String code, Model model) {
        CourseModel course = courseService.getCourseByCodeCourse(code);
        model.addAttribute("course", course);

        return "form-update-course";
    }

    @PostMapping("/course/update")
    public String updateCourseSubmitPage(@ModelAttribute CourseModel course, Model model) {
        CourseModel updatedCourse = courseService.updateCourse(course);
        model.addAttribute("code", updatedCourse.getCode());

        return "update-course";
    }

    @GetMapping("/course/delete/{code}")
    public String deleteCourse(@PathVariable(value = "code") String code, Model model) {
        CourseModel course = courseService.getCourseByCodeCourse(code);
        if (courseService.courseIsClosed(course)) {
            if (courseService.noPengajar(course)) {
                courseService.deleteCourse(course);
                model.addAttribute("course", course);
                return "delete-course";
            } else {
                String errorMsg = "Error: Course masih memiliki pengajar";
                model.addAttribute("errorMsg", errorMsg);
                return "error";
            }
        } else {
            String errorMsg = "Error: Course masih dibuka";
            model.addAttribute("errorMsg", errorMsg);
            return "error";
        }
    }

    //Routing URL yang diinginkan
    @RequestMapping("course/add")
    public String addCourse(
            @RequestParam(value = "code", required = true) String code,
            @RequestParam(value = "nameCourse", required = true) String nameCourse,
            @RequestParam(value = "description", required = true) String description,
            @RequestParam(value = "jumlahSks", required = true) String jumlahSks,
            Model model) {
            //Membatalkan penambahan course kedalam database karena sudah ada
            if(courseService.getCourseByCodeCourse(code) instanceof CourseModel) {
                model.addAttribute("code", code);
                return "course-reserved";
            } else {
                //Membuat Objek CourseModel
                CourseModel course = new CourseModel(code, nameCourse, description, Integer.parseInt(jumlahSks));

                //Memanggil Service addCourse
                courseService.addCourse(course);

                //Add variabel code course ke 'code' untuk di render di Thymeleaf
                model.addAttribute("code", code);

                model.addAttribute("nameCourse", nameCourse);

                return "add-course";
            }
    }

    @RequestMapping("course/view")
    public String detailCourse(@RequestParam(value = "code") String code, Model model) {
        //Mendapatkan courseModel sesuai dengan code
        CourseModel course = courseService.getCourseByCodeCourse(code);
        //Mengecek apakah course dengan code diminta ada atau tidak
        if(course == null) {
            model.addAttribute("code", code);
            return "course-notfound";
        }
        //Add variabel semua courseModel ke "course" untuk dirender di Thymeleaf
        model.addAttribute("course", course);
        return "view-course";
    }

    @RequestMapping("course/view/code-course/{code}")
    public String detailCourseWithPathVariable(@PathVariable(value = "code") String code, Model model) {
        CourseModel course = courseService.getCourseByCodeCourse(code);
        //Mengecek apakah course dengan code diminta ada atau tidak
        if(course == null) {
            model.addAttribute("code", code);
            return "course-notfound";
        }
        //Add variabel semua courseModel ke "course" untuk dirender di Thymeleaf
        model.addAttribute("course", course);
        return "view-course";
    }

    @RequestMapping("course/update/code-course/{code}/jumlah-sks/{jumlahSks}")
    public String updateSks(@PathVariable(value = "code") String code,
                            @PathVariable(value = "jumlahSks") String jumlahSks,
                            Model model){
        CourseModel course = courseService.getCourseByCodeCourse(code);
        //Mengecek apakah course dengan code diminta ada atau tidak
        if(course == null) {
            model.addAttribute("code", code);
            return "course-notfound";
        }
        //Mengubah jumlah sks dengan perintah setJumlahSks
        course.setJumlahSks(Integer.parseInt(jumlahSks));
        //Add variabel semua courseModel ke "course" untuk dirender di Thymeleaf
        model.addAttribute("course", course);
        return "update-course";
    }

    @RequestMapping("course/delete/code-course/{code}")
    public String deleteCourseCodeBased(@PathVariable(value = "code") String code, Model model) {
        //Mengambil list Course yang disimpan
        List<CourseModel> courseList = courseService.getListCourse();
        //Memilih Course berdasarkan Code
        CourseModel course = courseService.getCourseByCodeCourse(code);
        System.out.println("tes");
        //Mengecek apakah course dengan code diminta ada atau tidak
        if(course == null) {
            model.addAttribute("code", code);
            return "course-notfound";
        }
        else {
            //Menghapus course berdasarkan code
            courseList.remove(course);
            //Add variabel semua courseModel ke "course" untuk dirender di Thymeleaf
            model.addAttribute("course", course);
            return "delete-course";
        }
    }
}

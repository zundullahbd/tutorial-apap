package apap.tutorial.belajarbelajar.service;
import apap.tutorial.belajarbelajar.model.CourseModel;
import java.util.List;

public interface CourseService {
    //Method untuk menambahkan course
    void addCourse(CourseModel course);

    //Method untuk mendapatkan daftar course yang telah tersimpan
    List<CourseModel> getListCourse();

    //Method untuk mendapatkan data course berdasarkan code course
    CourseModel getCourseByCodeCourse(String code);

    CourseModel getCourseByCodeCourseQuery(String code);

    List<CourseModel> getListCourseInOrder();

    CourseModel updateCourse(CourseModel course);

    CourseModel deleteCourse(CourseModel course);

    boolean courseIsClosed(CourseModel course);

    boolean noPengajar(CourseModel course);
}

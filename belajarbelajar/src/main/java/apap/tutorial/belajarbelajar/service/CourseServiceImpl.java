package apap.tutorial.belajarbelajar.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import apap.tutorial.belajarbelajar.model.CourseModel;
import apap.tutorial.belajarbelajar.repository.CourseDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseDb courseDb;

    @Override
    public void addCourse(CourseModel course) {
        courseDb.save(course);
    }

    @Override
    public List<CourseModel> getListCourse() {
        return courseDb.findAll();
    }

    @Override
    public CourseModel getCourseByCodeCourse(String code) {
        Optional<CourseModel> course = courseDb.findByCode(code);
        if(course.isPresent()) {
            return course.get();
        }
        else return null;
    }

    @Override
    public CourseModel getCourseByCodeCourseQuery(String code) {
        Optional<CourseModel> course = courseDb.findByCodeUsingQuery(code);
        if(course.isPresent()) {
            return course.get();
        }
        else return null;
    }

    @Override
    public List<CourseModel> getListCourseInOrder() {
        return courseDb.findAll(Sort.by(Sort.Direction.ASC, "nameCourse"));
    }

    @Override
    public CourseModel updateCourse(CourseModel course) {
        courseDb.save(course);
        return course;
    }

    @Override
    public CourseModel deleteCourse(CourseModel course) {
        courseDb.delete(course);
        return course;
    }

    @Override
    public boolean courseIsClosed(CourseModel course) {
        if(course.getTanggalBerakhir().isBefore(LocalDateTime.now())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean noPengajar(CourseModel course) {
        if(course.getListPengajar().isEmpty()) {
            return true;
        }
        return false;
    }



}

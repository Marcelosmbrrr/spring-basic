package com.smbr.api.course;

import com.smbr.api.user.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CourseEntity saveCourse(CourseDTO courseData) {
        CourseEntity course = modelMapper.map(courseData, CourseEntity.class);
        return courseRepository.save(course);
    }

    public Page<CourseEntity> getPaginatedCourses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return courseRepository.findAll(pageable);
    }

    public List<CourseEntity> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<CourseEntity> getCourseById(int id) {
        return courseRepository.findById(id);
    }

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

    // Add a User to a Course
    public void addUserToCourse(int courseId, UserEntity user) {
        Optional<CourseEntity> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isPresent()) {
            CourseEntity course = courseOpt.get();
            course.getUsers().add(user);
            courseRepository.save(course);
        }
    }

    // Remove a User from a Course
    public void removeUserFromCourse(int courseId, UserEntity user) {
        Optional<CourseEntity> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isPresent()) {
            CourseEntity course = courseOpt.get();
            course.getUsers().remove(user);
            courseRepository.save(course);
        }
    }
}

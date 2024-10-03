package com.smbr.api.course;

import com.smbr.api.user.UserEntity;
import com.smbr.api.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @GetMapping
    public Object getCourses(
            @RequestParam(required = false, defaultValue = "false") boolean paginated,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        if (paginated) {
            return courseService.getPaginatedCourses(page, size);
        } else {
            return courseService.getAllCourses();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseEntity> getCourseById(@PathVariable int id) {
        Optional<CourseEntity> course = courseService.getCourseById(id);
        return course.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/users/{user_id}")
    public String getCoursesByUserId(@PathVariable int id) {
        return "courses by user_id";
    }

    @PostMapping
    public ResponseEntity<CourseEntity> createCourse(@RequestBody @Valid CourseDTO courseData) {
        CourseEntity savedCourse = courseService.saveCourse(courseData);
        return ResponseEntity.ok(savedCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseEntity> updateCourse(@PathVariable int id, @RequestBody @Valid CourseDTO courseData) {
        Optional<CourseEntity> existingCourse = courseService.getCourseById(id);
        if (existingCourse.isPresent()) {
            CourseEntity savedCourse = courseService.saveCourse(courseData);
            return ResponseEntity.ok(savedCourse);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int id) {
        Optional<CourseEntity> existingCourse = courseService.getCourseById(id);
        if (existingCourse.isPresent()) {
            courseService.deleteCourse(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{courseId}/users/{userId}")
    public ResponseEntity<Void> addUserToCourse(@PathVariable int courseId, @PathVariable int userId) {
        Optional<UserEntity> userOptional = userService.getUserById(userId);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            courseService.addUserToCourse(courseId, user);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{courseId}/users/{userId}")
    public ResponseEntity<Void> removeUserFromCourse(@PathVariable int courseId, @PathVariable int userId) {
        Optional<UserEntity> userOptional = userService.getUserById(userId);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            courseService.removeUserFromCourse(courseId, user);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
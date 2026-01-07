package com.example.elearning.controller;

import com.example.elearning.model.Course;
import com.example.elearning.service.CourseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Accessible par STUDENT et ADMIN
    @GetMapping("/courses")
    @PreAuthorize("hasAnyAuthority('ROLE_STUDENT','ROLE_ADMIN')")
    public List<Course> getCourses() {
        return courseService.findAll();
    }

    // Accessible uniquement par ADMIN
    @PostMapping("/courses")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Course addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    // Retourne les claims du token
    @GetMapping("/me")
    public Map<String, Object> me(@AuthenticationPrincipal Jwt jwt) {
        Map<String, Object> out = new HashMap<>();
        out.put("sub", jwt.getSubject());
        out.put("claims", jwt.getClaims());
        return out;
    }
}

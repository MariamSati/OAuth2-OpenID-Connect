package com.example.elearning.service;

import com.example.elearning.model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CourseService {
    private final List<Course> courses = Collections.synchronizedList(new ArrayList<>());
    private final AtomicLong idGen = new AtomicLong(1);

    public CourseService() {
        // Données initiales pour demo
        courses.add(new Course(idGen.getAndIncrement(), "Intro à Java", "Bases du langage Java"));
        courses.add(new Course(idGen.getAndIncrement(), "Sécurité Web", "Notions d'authentification et OAuth2"));
    }

    public List<Course> findAll() {
        return new ArrayList<>(courses);
    }

    public Course addCourse(Course c) {
        c.setId(idGen.getAndIncrement());
        courses.add(c);
        return c;
    }
}

package com.otac27.miproyectoapis.repository;

import com.otac27.miproyectoapis.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}

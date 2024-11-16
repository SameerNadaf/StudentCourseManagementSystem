package com.hibernate.StudentCourseManagementSystem;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String courseName;
	private int duration;
	private double fees;
	
	@ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL,  fetch = FetchType.EAGER)	
	private List<Student> students;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getFees() {
		return fees;
	}
	public void setFees(double fees) {
		this.fees = fees;
	}

	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}

}

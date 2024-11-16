package com.hibernate.StudentCourseManagementSystem;

import java.util.List;
import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class CourseDOA {

	public static void saveCourse(EntityManagerFactory emf, Scanner scanner) {

		EntityManager em = null;
		
		try {
			
			em = emf.createEntityManager();			
			em.getTransaction().begin();
			
			System.out.println();
			System.out.print("Enter Course Name: ");
	        String name = scanner.next();
	        System.out.print("Enter Course duration in weeks: ");
	        int duration = scanner.nextInt();
	        System.out.print("Enter Course Fees: ");
	        double fees = scanner.nextDouble();
	        
	        Course course = new Course();
	        course.setCourseName(name);
	        course.setDuration(duration);
	        course.setFees(fees);
	        
	        em.persist(course);
	        em.getTransaction().commit();
	        
	        System.out.println("Course saved successfully.");
	        
		} catch (Exception e) {
			if (em != null && em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	            System.out.println("Transaction rolled back due to an error.");
	        }
			e.printStackTrace();
		} finally {
			em.close();
		}		
		
	}
	
	public static void getCourses(EntityManagerFactory emf, Scanner scanner) {
		
		try (EntityManager em = emf.createEntityManager()) {
	        
	        List<Course> courses = em.createQuery("FROM Course", Course.class).getResultList();
	        
	        if (courses.isEmpty()) {
	        	System.out.println();
	            System.out.println("No courses found.");
	        } else {
	        	System.out.println();
	            System.out.println("List of Courses:");
	            for (Course course : courses) {
	            	System.out.println("-------------------------------------------");
                	System.out.println("Course ID: " + course.getId());
                	System.out.println("Course Name: " + course.getCourseName());
                	System.out.println("Course Duration: " + course.getDuration());
                	System.out.println("Course Fees: " + course.getFees());
	                System.out.println("-------------------------------------------");
	                System.out.println();
	            }
	        }	        
	        
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}

	public static void addStudent(EntityManagerFactory emf, Scanner scanner) {
		
		EntityManager em = null;

	    try {
	        em = emf.createEntityManager();
	        em.getTransaction().begin();

	        System.out.println();
	        System.out.print("Enter Student ID: ");
	        long id = scanner.nextLong();

	        Student student = em.find(Student.class, id);
	        if (student == null) {
	            System.out.println("Student with ID " + id + " does not exist.");
	            return;
	        }

	        List<Course> courses = em.createQuery("FROM Course", Course.class).getResultList();

	        if (courses.isEmpty()) {
	            System.out.println();
	            System.out.println("No courses found.");
	        } else {
	            System.out.println();
	            System.out.println("List of Courses:");
	            for (Course course : courses) {
	                System.out.println();
	                System.out.println("Course Name: " + course.getCourseName());
	                System.out.println("Course Duration: " + course.getDuration());
	                System.out.println("Course Fees: " + course.getFees());
	                System.out.println("Do you want to choose this course?");
	                System.out.println("1. Yes \n2. No");

	                int option = scanner.nextInt();

	                if (option == 1) {
	                    if (!student.getCourses().contains(course)) {
	                        student.getCourses().add(course);
	                        System.out.println("Course added successfully.");
	                    } else {
	                        System.out.println("Course already added.");
	                    }
	                } else if (option == 2) {
	                    System.out.println("Skipped course.");
	                } else {
	                    System.out.println("Invalid option! Skipping course.");
	                }
	            }

	            em.merge(student);
	            em.getTransaction().commit();
	            System.out.println("Transaction committed. Student's courses updated.");
	        }

	    } catch (Exception e) {
	        if (em != null && em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	            System.out.println("Transaction rolled back due to an error.");
	        }
	        e.printStackTrace();
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }
		
	}

}



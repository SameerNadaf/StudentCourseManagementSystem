package com.hibernate.StudentCourseManagementSystem;

import java.util.List;
import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class StudentDOA {

	public static void saveStudent(EntityManagerFactory emf, Scanner scanner) {
		
		EntityManager em = null;
		
		try {
			
			em = emf.createEntityManager();			
			em.getTransaction().begin();
			
			System.out.println();
			System.out.print("Enter Student Name: ");
	        String name = scanner.next();
	        System.out.print("Enter Student Email: ");
	        String email = scanner.next();
	        
	        Student student = new Student();
	        student.setName(name);
	        student.setEmail(email);
	        
	        em.persist(student);
	        em.getTransaction().commit();
	        
	        System.out.println("Student saved successfully.");
	        
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

	public static void getStudents(EntityManagerFactory emf, Scanner scanner) {
					
		try (EntityManager em = emf.createEntityManager()) {
	        
	        List<Student> students = em.createQuery("FROM Student", Student.class).getResultList();
	        
	        if (students.isEmpty()) {
	        	System.out.println();
	            System.out.println("No students found.");
	        } else {
	        	System.out.println();
	            System.out.println("List of Students:");
	            for (Student student : students) {
	            	System.out.println("-------------------------------------------");
	                System.out.println("Student ID: " + student.getId());
	                System.out.println("Student Name: " + student.getName());
	                System.out.println("Student Email: " + student.getEmail());
	                
	                for (Course course : student.getCourses()) {	
	                	System.out.println();
	                	System.out.println("  Course ID: " + course.getId());
	                	System.out.println("  Course Name: " + course.getCourseName());
	                	System.out.println("  Course Duration: " + course.getDuration());
	                	System.out.println("  Course Fees: " + course.getFees());	                	
	                }
	                System.out.println("-------------------------------------------");
	                System.out.println();
	            }
	        }	        
	        
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}

}

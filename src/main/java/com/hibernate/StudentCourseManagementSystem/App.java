package com.hibernate.StudentCourseManagementSystem;

import java.util.Scanner;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App 
{
	private static EntityManagerFactory emf;
	
    public static void main( String[] args )
    {
        try {
        	emf = Persistence.createEntityManagerFactory("pu");
        	Scanner scanner = new Scanner(System.in);
        	
        	System.out.println();
        	System.out.println("----- Welcome to Student Course Management System -----");
        	
        	while (true) {
            	System.out.println();
            	System.out.println("1. Add Student");
                System.out.println("2. Get Students");
                System.out.println("3. Add Course");
                System.out.println("4. Get Courses");
                System.out.println("5. Add Student to Course");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                    	StudentDOA.saveStudent(emf, scanner);
                        break;
                    case 2:
                    	StudentDOA.getStudents(emf, scanner);
                        break;
                    case 3:
                    	CourseDOA.saveCourse(emf, scanner);
                        break;
                    case 4:
                    	CourseDOA.getCourses(emf, scanner);
                        break;
                    case 5:
                    	CourseDOA.addStudent(emf, scanner);
                        break;  
                    case 6:
                    	exit();
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option! Try again.");
                        break;
                }
            }
            
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

	private static void exit() throws InterruptedException {
		System.out.println();
		System.out.print("Exiting");
        int i = 5;
        while (i!=0) {
            System.out.print(".");
            Thread.sleep(300);
            i--;
        }
        System.out.println();
        System.out.println("Thank you for using Student Course Management System.");
	}
	
}

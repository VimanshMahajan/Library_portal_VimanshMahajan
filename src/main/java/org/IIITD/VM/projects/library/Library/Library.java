package org.IIITD.VM.projects.library.Library;

import org.IIITD.VM.projects.library.Student.Student;

import java.util.*;

public class Library {
    public static ArrayList<Student> stu_obj = new ArrayList<>();
    public static HashMap<Integer,String[]> lib_books = new HashMap<>();

    public static Student member_login(){
        while(true){
            System.out.println("To go back enter 'x' in both name and phone number");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your name: ");
            String check_name = sc.nextLine();

            System.out.println("Enter phone number");
            String check_phone = sc.nextLine();
            if(check_phone.equals("x")&&check_name.equals("x")){
                break;
            }

            for (Student student : stu_obj) {
                if (student.getName().equals(check_name) && student.getPhone_num().equals(check_phone)) {
                    return student;
                }
            }
            System.out.println("Member not found");

        }
        return null;



    }


}


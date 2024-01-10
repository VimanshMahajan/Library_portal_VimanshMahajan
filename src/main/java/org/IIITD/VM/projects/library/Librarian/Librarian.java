package org.IIITD.VM.projects.library.Librarian;

import org.IIITD.VM.projects.library.Student.Student;

import java.time.LocalTime;
import java.util.*;

import static org.IIITD.VM.projects.library.Library.Library.lib_books;
import static org.IIITD.VM.projects.library.Library.Library.stu_obj;

public class Librarian {
    static int book_id_provider = 0;
    static int student_ID_provider = 1000;

    public void reg_mem() {
        student_ID_provider++;//1001
        Scanner name = new Scanner(System.in);
        System.out.println("Name: ");
        String name_in = name.nextLine();

        Scanner phone = new Scanner(System.in);
        System.out.println("Phone: ");
        String phone_in = phone.nextLine();
        for (Student student : stu_obj) {
            if (phone_in.equals(student.getPhone_num())) {
                System.out.println("Phone number already in use!");
                return;
            }
        }

        Scanner age = new Scanner(System.in);
        System.out.println("Age: ");
        int age_in = age.nextInt();

        Student s1 = new Student(name_in, age_in, phone_in,student_ID_provider);
        stu_obj.add(s1);
        System.out.println("Member registered successfully with member id " + student_ID_provider);

    }

    public void remove_mem() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Phone number to be removed: ");
        String num = sc.nextLine();
        int flag_mem = 0;
        int flag_fine = 0;
        for (int i = 0; i < stu_obj.size(); i++) {
            if (stu_obj.get(i).getPhone_num().equals(num) && stu_obj.get(i).getBooks_with_member() == 0) {
                stu_obj.remove(i);
                flag_mem  = 1;
                System.out.println("Member Removed.");
                break;
            }
            else if(stu_obj.get(i).getPhone_num().equals(num)&& stu_obj.get(i).getBooks_with_member() != 0){
                flag_fine = 1;
                System.out.println("Fine due on member.");
                break;
            }

        }
        if(flag_mem == 0 && flag_fine == 0){
            System.out.println("Member not found");
        }

    }

    public void add_book() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Book Name: ");
        String book_name = sc.nextLine();

        System.out.println("Book Author: ");
        String book_author = sc.nextLine();


        System.out.println("Total Copies: ");
        int copies = sc.nextInt();
        for (int i = 0; i < copies; i++) {
            book_id_provider++;
            lib_books.put(book_id_provider, new String[]{book_name, book_author, "1"});
        }

    }

    public void remove_book() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter book ID to be removed: ");
        int id = sc.nextInt();
        String[] remove = lib_books.get(id);
        String rem = remove[2];
        if(rem.equals("1")){
            lib_books.remove(id);
        }
        else{
            System.out.println("Book already issued. Try Later");
        }


    }

    public void view_all_books() {

        for (Map.Entry<Integer, String[]> entry : lib_books.entrySet()) {
            Integer bookID = entry.getKey();
            String[] values = entry.getValue();
            String bookTitle = values[0];
            String bookAuthor = values[1];

            System.out.println("Book ID: "+bookID);
            System.out.println("Book Title: "+bookTitle);
            System.out.println("Book Author: "+bookAuthor);
            System.out.println("\n");



        }


    }
    public void member_fines_and_books(){
        for(int i = 0;i<stu_obj.size();i++){
            System.out.println("Student ID: "+stu_obj.get(i).student_ID+" Name: "+stu_obj.get(i).getName()+" Fine: "+stu_obj.get(i).check_fine_for_lib());
            for (Map.Entry<Integer, LocalTime> entry : stu_obj.get(i).issued_books.entrySet()){
                System.out.println(entry.getKey());
            }
        }

    }


}
/*
for (int i = 0;i<stu_obj.size();i++){
    sout(stu_obj.get(i).name()
    }
 */






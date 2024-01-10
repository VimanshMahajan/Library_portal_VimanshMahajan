package org.IIITD.VM.projects.library.Student;

import java.time.temporal.ChronoUnit;
import java.util.*;

import java.time.LocalTime;

import static org.IIITD.VM.projects.library.Library.Library.lib_books;


public class Student  {
    //1001
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String phone_num;

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    private int books_with_member;

    public int getBooks_with_member() {
        return books_with_member;
    }

    public void setBooks_with_member(int books_with_member) {
        this.books_with_member = books_with_member;
    }

    public int student_ID;
    public HashMap<Integer, LocalTime> issued_books = new HashMap<>();


    public Student(String name, int age, String phone_num,int student_ID) {
        this.setName(name);
        this.setAge(age);
        this.setPhone_num(phone_num);
        this.setBooks_with_member(0);
        this.student_ID = student_ID;
    }


    public static long calculateTimeDifferenceInSeconds(LocalTime time1, LocalTime time2) {
        return ChronoUnit.SECONDS.between(time1, time2);
    }

    public void list_books() {
        for (Map.Entry<Integer, String[]> entry : lib_books.entrySet()) {
            Integer bookID = entry.getKey();
            String[] values = entry.getValue();
            String check = values[2];
            String book_author = values[1];
            String book_title = values[0];
            if (check.equals("1")) {
                System.out.println("Book ID: " + bookID + " Title: " + book_title + " Author: " + book_author);

            }
        }
    }

    public void issue_book() {   //ASSUMING USER ALWAYS INPUT CORRECT BOOK ID AS AVAILABLE BOOKS ARE GIVEN!
        Scanner sc = new Scanner(System.in);
        list_books();


        if (books_with_member == 0) {
            System.out.println("Enter Book ID from the available books: ");
            int issue_ID = sc.nextInt();
            String[] changer = lib_books.get(issue_ID);
            changer[2] = "0";
            lib_books.put(issue_ID, changer);
            issued_books.put(issue_ID, LocalTime.now().withNano(0));
            books_with_member++;
        } else if (books_with_member == 2) {
            System.out.println("Max book limit reached.Please return previous books.");

        } else {
            for (Map.Entry<Integer, LocalTime> entry : issued_books.entrySet()) {
                LocalTime values = entry.getValue();

                int interval = (int) calculateTimeDifferenceInSeconds(values, LocalTime.now());
                if (interval - 10 > 0) {
                    System.out.println("Pay pending fine for first book.");
                    return;
                }else{
                    System.out.println("Enter Book ID from the available books:");
                    int issue_ID = sc.nextInt();
                    String[] changer = lib_books.get(issue_ID);
                    changer[2] = "0";
                    lib_books.put(issue_ID, changer);
                    issued_books.put(issue_ID, LocalTime.now().withNano(0));
                    books_with_member++;
                    return;

                }

            }
        }
    }

    public int check_fine_for_lib() {
        int to_pay = 0;
        for (Map.Entry<Integer, LocalTime> entry : issued_books.entrySet()) {
            LocalTime values = entry.getValue();
            int interval = (int) calculateTimeDifferenceInSeconds(values, LocalTime.now());
            if(interval-10 > 0){
                to_pay += 3*(interval-10);
            }


        }
        return to_pay;
    }


    public void fine_per_book(){
        if(books_with_member>0){
            int fine = 0;
            System.out.println("Your book IDs:\n ");
            for (Map.Entry<Integer, LocalTime> entry : issued_books.entrySet()){
                System.out.println(entry.getKey());
            }
            Scanner sc = new Scanner(System.in);
            System.out.println("\nEnter book ID to check fine:\n");
            int book_ID = sc.nextInt();
            LocalTime check = issued_books.get(book_ID);
            int interval = (int) calculateTimeDifferenceInSeconds(check,LocalTime.now());
            if(interval-10>0){
                System.out.println("Your fine is: "+(interval-10)*3);
            }else{
                System.out.println("No fine due for you.");
            }

        }
        else{
            System.out.println("\nNo fine due");
        }
    }
    public void return_book() {
        int fine = 0;
        int flag = 0;
        if(books_with_member==0){
            System.out.println("No books to return");
            return;
        }
        System.out.println("Your issued books: \n");
        for (Map.Entry<Integer, LocalTime> entry : issued_books.entrySet()) {
            System.out.println(entry.getKey());
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter book ID to return");
        int book_ID = sc.nextInt();
        for (Map.Entry<Integer, LocalTime> entry : issued_books.entrySet()){
            if (book_ID == entry.getKey()) {
                flag = 1;
                break;
            }
        }
        if(flag == 0){
            System.out.println("Try again");
            return;
        }
        else{
            LocalTime check = issued_books.get(book_ID);
            int interval = (int) calculateTimeDifferenceInSeconds(check,LocalTime.now());
            if(interval-10>0){
                fine = (interval-10)*3;
                System.out.println("Pending fine: Rs."+fine+" for delay of "+(interval-10)+"days");

                while(true) {
                    System.out.println("Pay fine: ");
                    int inp = sc.nextInt();
                    if (inp - fine == 0) {
                        System.out.println("Fine paid and book returned");
                        books_with_member--;
                        String[] temp = lib_books.get(book_ID);
                        temp[2] = "1";
                        lib_books.put(book_ID, temp);
                        issued_books.remove(book_ID);
                        break;

                    } else {
                        System.out.println("Enter correct fine amount!");
                    }
                }
            }
            else{
                System.out.println("book returned");
                books_with_member--;
                String[] temp = lib_books.get(book_ID);
                temp[2] = "1";
                lib_books.put(book_ID, temp);
                issued_books.remove(book_ID);
            }

        }

    }
    public void see_your_books(){
        if(books_with_member>0){

            System.out.println("Your book IDs:\n ");
            for (Map.Entry<Integer, LocalTime> entry : issued_books.entrySet()){
                System.out.println("Your book-> "+entry.getKey());
            }

        }
        else{
            System.out.println("No books issued right now");
        }
    }







}


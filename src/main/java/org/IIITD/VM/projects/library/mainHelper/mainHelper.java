package org.IIITD.VM.projects.library.mainHelper;

import org.IIITD.VM.projects.library.Librarian.Librarian;
import org.IIITD.VM.projects.library.Library.Library;
import org.IIITD.VM.projects.library.Student.Student;

import java.util.Scanner;

public class mainHelper {

    public static void helper() {
        Librarian l = new Librarian();
        Student s = new Student("Null", 0, "x", -1);
        while (true) {
            System.out.println("Library Portal Initialised");
            System.out.println(".............................");
            System.out.println("1. Enter as a Librarian");
            System.out.println("2. Enter as a member");
            System.out.println("3. Exit");
            int flag = 0;
            System.out.println();
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            System.out.println();
            switch (choice) {
                case 1:
                    Scanner gfg = new Scanner(System.in);
                    System.out.println("Enter password: ");
                    String pass = gfg.nextLine();
                    if (pass.equals("Librarian_IIITD")) {
                        System.out.println("Librarian Interface Active\n");
                    } else {
                        System.out.println("Wrong password");
                        flag = 1;
                    }

                    break;
                case 2:
                    System.out.println("Student Interface Active");
                    s = Library.member_login();
                    break;
                case 3:
                    break;
            }
            if (choice == 3) {
                System.out.println("Thanks for visiting!");
                break;
            }

            if ((choice == 1 && flag == 0) || (choice == 2 && s != null)) {
                while (true) {
                    if (choice == 1) {
                        System.out.println();
                        System.out.println("1. Register Member");
                        System.out.println("2. Remove Member");
                        System.out.println("3. Add Book");
                        System.out.println("4. Remove Book");
                        System.out.println("5. View All Books");
                        System.out.println("6. View Members along with their fines and books");
                        System.out.println("7. Logout");
                        System.out.println();
                        Scanner input = new Scanner(System.in);
                        System.out.println("Enter your choice: ");
                        int select = input.nextInt();
                        switch (select) {
                            case 1:
                                l.reg_mem();
                                break;
                            case 2:
                                l.remove_mem();
                                break;
                            case 3:
                                l.add_book();
                                break;
                            case 4:
                                l.remove_book();
                                break;
                            case 5:
                                l.view_all_books();
                                break;
                            case 6:
                                l.member_fines_and_books();
                                break;
                            case 7:
                                break;
                            default:
                                System.out.println("Enter valid number");
                                break;
                        }
                        if (select == 7) {
                            break;
                        }


                    } else if (choice == 2) {
                        System.out.println();
                        System.out.println("1. List books available to issue");
                        System.out.println("2. Issue book");
                        System.out.println("3. View issued books");
                        System.out.println("4. Check fine for a book using book ID");
                        System.out.println("5. Return book");
                        System.out.println("6. Logout");
                        System.out.println();
                        Scanner random = new Scanner(System.in);
                        System.out.println("Enter command");
                        int command = random.nextInt();
                        switch (command) {
                            case 1:
                                s.list_books();
                                break;
                            case 2:
                                s.issue_book();
                                break;
                            case 3:
                                s.see_your_books();
                                break;
                            case 4:
                                s.fine_per_book();
                                break;
                            case 5:
                                s.return_book();
                                break;
                            case 6:
                                break;


                        }
                        if (command == 6) {
                            break;
                        }

                    }

                }
            }
        }
    }
}

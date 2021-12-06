package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final File bookStore = new File("Books.txt");
    private static final File logInDetails = new File("LogIn.txt");
    private static final ArrayList<String> bookList = new ArrayList<>();
    private static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        A: while(true) {
            System.out.println("Login or Register:");
            String response = input.next();
            if (response.equalsIgnoreCase("login")) {
                Login();
                break A;

            } else if (response.equalsIgnoreCase("register")) {
                Register();
                Login();
                break A;
            } else {
                System.out.println("Invalid response");
            }
        }
        CreateFile();
        MainMenu();
    }

    private static void Login(){
        try {
            A:while(true) {
                System.out.println("What is your login:");
                String username = input.next();
                String password = input.next();
                Scanner reader = new Scanner(logInDetails);
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    if ((data.contains(username)) && (data.contains(password))) {
                        System.out.println("Login Successful");
                        break A;
                    }
                }
                System.out.println("Email/Password not found");
            }

        }catch(Exception e){
            System.out.println(e);
        }

    }

    private static void Register(){
        String email;
        String password;
        while (true) {
            System.out.println("Input your email address:");
            email = input.next();
            if (!((email.contains("@")) && (email.contains(".com")))) {
                System.out.println("Invalid email");
            } else {
                break;
            }
        }
        //Checks password for specific format
        A : while(true) {
            System.out.println("Input your password:");
            password = input.next();
            for (char i : password.toCharArray()) {
                if (Character.isUpperCase(i)) {
                    if ((password.length() >= 8)) {
                        break A;
                    }
                    System.out.println("Invalid Password Length");
                }
            }
            System.out.println("Invalid Password, needs to contain capital letters");
        }
        //Writes to file
        try {
            FileWriter myWriter = new FileWriter(logInDetails.getName(), true);
            myWriter.write(email + " " + password + "\n");
            myWriter.close();

        }catch(Exception e){
            System.out.println(e);
        }
    }

    private static void MainMenu() {
        int i =0;
        while (true) {
            System.out.println("-Add a book (1)" +"\n" + "-Read File (2)" +"\n" + "-Delete file contents (3)" + "\n" + "-Delete file (4)" + "\n" + "-Search ISBN (5)");
            try {
                int menuChoice = input.nextInt();
                if (menuChoice == 1) {
                    addBookInfo();
                    WriteToFile(readBookInfo(i));
                    i++;
                }
                else if (menuChoice == 2){
                    ReadFile();
                }
                else if (menuChoice == 3) {
                    FileEmptier();
                }
                else if (menuChoice == 4) {
                    DeleteFile();
                }
                else if (menuChoice == 5){
                    System.out.println("What is the ISBN you are searching for:");
                    String answer = input.next();
                    SearchFile(answer);
                }
                else {
                    System.out.println("Invalid Answer");
                }
            }catch(Exception e){
                System.out.println(e);
                input.next(); //Clears scanner of error
            }

        }
    }

    private static void FileEmptier() {
        try {
            FileWriter delete = new FileWriter(bookStore, false);
            delete.close();
            System.out.println("File successfully emptied");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    private static void addBookInfo(){
        //Adds info into an array
        System.out.println("Type in your Book Title, ISBN, Author and Genre ");
        String title = input.next();
        int isbn = input.nextInt();
        String author = input.next();
        String genre = input.next();
        bookList.add(title + "," + Integer.toString(isbn) + "," + author + ","+genre);
    }

    public static String readBookInfo(int num) {
        //Splits book details at each comma
        String[] bookDetails = bookList.get(num).split(",");
        System.out.println(Arrays.toString(bookDetails));
        return (Arrays.toString(bookDetails));
    }
    public static void CreateFile() {
        try {
            if (bookStore.createNewFile()) {
                System.out.println("File created: " + bookStore.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void WriteToFile(String v) {
        try {
            FileWriter myWriter = new FileWriter(bookStore.getName(), true); //True means append to file contents, False means overwrite
            myWriter.write(v + "\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void DeleteFile() {
        if (bookStore.delete()) {
            System.out.println("Deleted the file: " + bookStore.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
    public static void ReadFile() {
        try {
            Scanner myReader = new Scanner(bookStore);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);

            }
            myReader.close();
            System.out.println(" ");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void SearchFile(String ISBN) {
        try {
            Scanner myReader = new Scanner(bookStore);
            boolean check = true;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.contains(ISBN)){
                    System.out.println(data);
                    check = false;
                }
            }
            myReader.close();
            if (check == true){
                System.out.println("ISBN not found");
            }
            System.out.println(" ");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

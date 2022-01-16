package com.company.objects;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static com.company.Main.bookStore;

public class fileModifiers {
    public static void FileEmptier() {
        try {
            FileWriter delete = new FileWriter(bookStore, false);
            delete.close();
            System.out.println("File successfully emptied");
        }catch(Exception e){
            System.out.println(e);
        }
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
                String[] temp = data.split(", ");
                if (temp[1].equals(ISBN)){
                    System.out.println(data);
                    check = false;
                }
            }
            myReader.close();
            if (check){
                System.out.println("ISBN not found");
            }
            System.out.println(" ");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

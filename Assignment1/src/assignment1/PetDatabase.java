/*
Name: Nick DeChane
Written: 11/1/2024
Assignment: 1 - pet database
Description: A program that allows a user to add pets to a database, view all pets added, update any existing pets,
             remove existing pers, and search for pets by name or age.
 */
package assignment1;

import java.util.ArrayList;
import java.util.Scanner;

//Base class of pets with methods to create a new pet, change a pets data, or retrieve a pets data
class Pet {
    private String name;
    private int age;
    
    //Intialize pet
    public Pet(String name, int age){
        this.name = name;
        this.age = age;
    }        
    
    //Retrieve pet's name
    public String getName() {
        return this.name;
    }
    
    //Retrieve pet's age
    public int getAge() {
        return this.age;
    }
    
    //Modify pet's name
    public void setName(String name) {
        this.name = name;
    }
    
    //Modify pet's age
    public void setAge(int age) {
        this.age = age;
    }
}

public class PetDatabase {
    //ArrayList used as database
    static ArrayList<Pet> pets = new ArrayList<>();
    static Scanner s = new Scanner(System.in);
    
    //method to display menu and retrieve input from user
    private static int getUserChoice() {
        int userChoice = 0;
        
        //print menu
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println(" 1) View all pets");
        System.out.println(" 2) Add new pets");
        System.out.println(" 3) Update a pet");
        System.out.println(" 4) Remove a pet");
        System.out.println(" 5) Search pets by name");
        System.out.println(" 6) Search pets by age");
        System.out.println(" 7) Exit program");
        System.out.print("Your choice: ");
        System.out.println();
        
        //Checks for if an int was entered
        if (s.hasNextInt()) {
            userChoice = s.nextInt();
        }
        else {
            s.next();
        }
        
        return userChoice;
    }
    
    //method to add pets to the ArrayList database
    private static void addPets(){
        String petInput = "";
        s.nextLine();
        int addedCount = 0;
        
        //continues until user enters done
        while (!petInput.equals("done")){
            System.out.print("add pet - name age - enter done to exit: ");
            petInput = s.nextLine();
        
            //checks for done, to exit, or attempts to add pet
            if (petInput.equals("done")) {
            }
            else {
                //breaks name and age apart
                String[] TempArray = petInput.split(" ");
                
                //checks that array contains only right number of entities to create a pet
                if (TempArray.length == 2) {
                pets.add(new Pet(TempArray[0], Integer.parseInt(TempArray[1])));
                
                //updates number of pets added this session
                addedCount += 1;
                }
            }
        }
        System.out.println(addedCount + " pets added");
    }
    
    //method to display all pets in database
    private static void showAllPets() {
        PetDatabase.printTableHeader();
        
        //loops through database to print all pets within it
        for (int i = 0; i < pets.size(); i++) {
           PetDatabase.printTableRow(i, pets.get(i).getName(), pets.get(i).getAge()); 
        }
        
        PetDatabase.printTableFooter(pets.size());

    }
    
    //method to print a properly formated table header
    private static void printTableHeader() {
        System.out.println("+----------------------+\n| ID | NAME      | AGE |\n+----------------------+");
    }
    
    //method to print a properly formated table row
    private static void printTableRow(int id, String name, int age) {
        System.out.printf("|%3d | %-10s|%4d |\n", id, name, age);
    }
    
    //method to print a properly formated table footer
    private static void printTableFooter(int rowCount) {
        System.out.println("+----------------------+\n" + rowCount + " rows in set.");
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        boolean isRunning = true;
        
        while(isRunning) {
            switch(PetDatabase.getUserChoice()) {
            case 1:
                PetDatabase.showAllPets();
            break;
            case 2:
                PetDatabase.addPets();
            break;
            case 3:
                System.out.println("Feature not added yet."); //Update pet case
            break;
            case 4:
                System.out.println("Feature not added yet."); //Remove a pet case
            break;
            case 5:
                System.out.println("Feature not added yet."); //Search by name case
            break;
            case 6:
                System.out.println("Feature not added yet."); //Search by age case
            break;
            case 7:
                System.out.println("Goodbye!");
                isRunning = false;
            break;
            default:
                System.out.println("----Invalid Input----");
            }
        }
    }
    
}

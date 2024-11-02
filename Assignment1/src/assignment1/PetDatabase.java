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
            System.out.print("add pet in format - name age - enter done to exit: ");
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
                else {
                    System.out.println("----Invalid Entry Format----");
                    System.out.println("Ensure the name and age are seperated by a space.");
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
    
    //method to find all pet names containing a user entered name, .contains used to capture similar names in the search too.
    private static void searchByName(){
        //Get pet name from user
        System.out.print("Enter a pet name to search: ");
        s.nextLine();
        String searchName = s.nextLine();
        int numPetsFound = 0;
        
        //print table with pets matching the search name
        PetDatabase.printTableHeader();
        for (int i = 0; i < pets.size(); ++i) {
            if(pets.get(i).getName().contains(searchName)) {
                PetDatabase.printTableRow(i, pets.get(i).getName(), pets.get(i).getAge());
                numPetsFound += 1;
            }
        }
        PetDatabase.printTableFooter(numPetsFound);
    }
    
    private static void searchByAge(){
        //Get pet age from user
        System.out.print("Enter a pet age to search: ");
        int searchAge = s.nextInt();
        int numPetsFound = 0;
        
        //print table with pets matching the search age
        PetDatabase.printTableHeader();
        for (int i = 0; i < pets.size(); ++i) {
            if(pets.get(i).getAge() == searchAge) {
                PetDatabase.printTableRow(i, pets.get(i).getName(), pets.get(i).getAge());
                numPetsFound += 1;
            }
        }
        PetDatabase.printTableFooter(numPetsFound);
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
                PetDatabase.searchByName(); //Search by name case
            break;
            case 6:
                PetDatabase.searchByAge(); //Search by age case
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

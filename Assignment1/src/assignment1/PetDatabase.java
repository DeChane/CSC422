/*
Name: Nick DeChane
Written: 11/1/2024
Assignment: pet database
Description: A program that allows a user to add pets to a database, view all pets added, update any existing pets,
             remove existing pers, and search for pets by name or age.

Revised: 11/9/2024 
 */
package assignment1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
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
    static String filename = "petDatabase.txt";
    
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
    private static void addPets() throws FileNotFoundException{
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
                PetDatabase.saveDatabase();
                
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
    
    //method to find all pet names equal to the user entered age
    private static void searchByAge(){
        //Get pet age from user
        System.out.print("Enter a pet age to search: ");
        int searchAge = -1;
        while(searchAge < 0){
            if (s.hasNextInt()) {
                searchAge = s.nextInt();
            }
            else {
                s.next();
                System.out.println("----Enter a valid age----");
            }
        }
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
    
    //method to update an exist pets age or name
    private static void updatePet() throws FileNotFoundException{
        int updateID = -1;
        
        //Display table to allow user to select the index of the pet they wish to update
        PetDatabase.showAllPets();
        
        //Get the ID of the pet to be updated and check to make sure it is a valid ID
        while(updateID < 0){
            System.out.print("Enter an ID from the above table to update that pet: ");
            if (s.hasNextInt()) {
                updateID = s.nextInt();
                if (updateID > pets.size()){
                    System.out.println("----Invalid ID----");
                    updateID = -1;
                }
            }
            else {
                s.next();
                System.out.println("----Invalid ID----");
            }
        }
        
        String oldName = pets.get(updateID).getName();
        int oldAge = pets.get(updateID).getAge();
        
        //Display pet that is being updated
        System.out.printf("Updating: %s %d\n", oldName, oldAge);
        
        //Get new pet information from user
        s.nextLine();
        System.out.print("Enter a new name and age: ");
        String updateInput = s.nextLine();
        
        String[] TempArray = updateInput.split(" ");
                
        //check that array contains right number of entities to update the pet information
        if (TempArray.length == 2) {
            //update name and age
            pets.get(updateID).setName(TempArray[0]);
            pets.get(updateID).setAge(Integer.parseInt(TempArray[1]));
            PetDatabase.saveDatabase();
            
            //display old information and what it was changed to.
            System.out.printf("Pet updated from: %s %d\n", oldName, oldAge);
            System.out.printf("to: %s %d\n", pets.get(updateID).getName(), pets.get(updateID).getAge());
        }
        else {
            System.out.println("----Invalid Entry Format----");
            System.out.println("Ensure the name and age are seperated by a space.");
        }
    }
    
    //method to remove pet from ArrayList based on idex/ID of pet, displays information of pet removed too
    private static void removePet() throws FileNotFoundException{
        int removeID = -1;

        //Display table to allow user to select the index of the pet they wish to remove
        PetDatabase.showAllPets();
        
          //Get the ID of the pet to be removed and check to make sure it is a valid ID
        while(removeID < 0){
            System.out.print("Enter an ID from the above table to remove that pet: ");
            if (s.hasNextInt()) {
                removeID = s.nextInt();
                if (removeID > pets.size()){
                    System.out.println("----Invalid ID----");
                    removeID = -1;
                }
            }
            else {
                s.next();
                System.out.println("----Invalid ID----");
            }
        }
        
        //Display pet that is being removed
        System.out.printf("%s %d has been removed\n", pets.get(removeID).getName(), pets.get(removeID).getAge());
        
        pets.remove(removeID);
        PetDatabase.saveDatabase();
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
        System.out.println("+----------------------+\n" + rowCount + " pets in database.");
    }
    
    //method loads all of the lines from the txt file assigned in filename
    private static void loadDatabase()throws FileNotFoundException {
        FileInputStream db = new FileInputStream(filename);
        Scanner scnr = new Scanner(db);
        
        while(scnr.hasNextLine()) {
            String[] TempArray = scnr.nextLine().split(" ");
            pets.add(new Pet(TempArray[0], Integer.parseInt(TempArray[1])));   
        }
    }
    
    //method saves all of the animals in the ArrayList to the txt file assigned in filename
    private static void saveDatabase() throws FileNotFoundException{
        FileOutputStream fileOut = new FileOutputStream(filename);
        PrintWriter outFile = new PrintWriter(fileOut);
        
        for(int i = 0; i < pets.size(); ++i) {
            outFile.println(pets.get(i).getName() + " " + pets.get(i).getAge());
        }
        outFile.close();
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        boolean isRunning = true;
        
        //initial call to load the database from the textfile
        PetDatabase.loadDatabase();
        
        while(isRunning) {
            switch(PetDatabase.getUserChoice()) {
            case 1:
                PetDatabase.showAllPets();
            break;
            case 2:
                PetDatabase.addPets();
            break;
            case 3:
                PetDatabase.updatePet(); //Update pet case
            break;
            case 4:
                PetDatabase.removePet(); //Remove a pet case
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

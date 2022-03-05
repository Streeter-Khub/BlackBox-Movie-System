/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.String;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Kenecia
 */
public class Main {

    public static void customerOptionMenu() {
        System.out.println("WELCOME TO BLACKBOX MOVIE RENTAL");
        System.out.println("[1]: New Customer");
        System.out.println("[2]: Existing Customer");
    }

    public static void newCustOptionMenu() {
        System.out.println("Welcome, Please Enter your name and you will be added to our system.");
        System.out.println("Q - Quit");
    }

    public static  void custTran(){
        //Ask for Sides
        System.out.println(" Customer Name Info --");
        System.out.println(" Input Your Name Below : ");
    }
    
    public static  void rentMoreMovies(){
    //Ask for Sides
        System.out.println(" Renting another movie? --");
        System.out.println(" Select another movie: ");
    }
    
      public static void rentQuest(){
    //Ask for Sides
        System.out.println(" Renting another movie? Select [1]: Yes or [2] No --");
    }
      
    public static  void rentmovie(){
    //Ask for Sides
        System.out.println(" What kind of movie would you like to rent? ");
        System.out.println(" Input You Selection Below : ");
    }
    public static void newCustOpt(){
    //Provide options
        System.out.println(" Here are your options: ");
        System.out.println(" 1 - Rent ");
        System.out.println(" 2 - Show All Movies ");
        System.out.println(" 3 - Show Current Movies ");
        /*System.out.println(" 4 - Show History ");*/
        System.out.println(" 5 - Create Movie(s) ");
        System.out.println(" 6 - Show All Customers ");
        System.out.println(" Q - Quit ");
    }
    
    public static void existCustOpt(){
    //Provide options
        System.out.println(" Here are your options: ");
        System.out.println(" 1 - Rent ");
        System.out.println(" 2 - Show All Movies ");
        System.out.println(" 3 - Show Current Movies ");
    /*    System.out.println(" 4 - Show History ");*/
        System.out.println(" 5 - Return Movie(s) ");
        System.out.println(" 6 - Create Movie(s) ");        
        System.out.println(" 7 - Delete Customer ");
        System.out.println(" 8 - Show All Customers ");
        System.out.println(" Q - Quit ");
    }
    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        String userIn;
   
        Rental_View view = new Rental_View();
        ClientController controller = null; // Customer/View
        
        customerOptionMenu();

        userIn = scan.nextLine();

        RentItem rent = new RentItem();
        Customer newcust = new Customer(userIn);
        ClientController show = new ClientController(rent, view);
         
         
        switch (userIn) {
            //NEW CUSTOMER
            case "1":
                newCustOptionMenu();
                
                userIn = scan.nextLine();
                newcust.addNewCust(userIn);
                
                newCustOpt();//available new customer options
                userIn = scan.nextLine();
                
                if (userIn.contentEquals("1")){
                    rentmovie();
                    
                    userIn = scan.nextLine();
                    rent.rentMovie(userIn);// first movie input
                    
                    rentQuest();//ask for more rentals
                    userIn = scan.nextLine();
                    
                    if(!userIn.equalsIgnoreCase("1")){
                        existCustOpt();// Existing Customer Menu
                         System.out.println(" See you next time! ");// exit system
                    }else{
                        rentMoreMovies(); 
                    
                        userIn = scan.nextLine();
                        rent.rentMovie(userIn);// second movie input
                        
                            rentQuest();//ask for more rentals
                            userIn = scan.nextLine();
                            
                            if(!userIn.equalsIgnoreCase("1")){
                                System.out.println(" See you next time! ");// exit system
                            }else{
                                rentMoreMovies(); // input another movie to rent
                    
                                userIn = scan.nextLine();
                                rent.rentMovie(userIn);
                            }
                            
                    }
                } else if (userIn.contentEquals("2")){
                    System.out.println("Movie List : ");
                    rent.movieList();
                } else if (userIn.contentEquals("3")){
                    System.out.println(" Current Rented Movies -- these are unavailable : ");
                    newcust.customerMovie.showCurrRent();
                } /*else if (userIn.contentEquals("4")){
                    System.out.println(" Rental History -- by rental date : ");
                    //controller for movie history
                }*/ else if (userIn.contentEquals("5")){
                   System.out.println(" Create New Movie - insert movie name: ");
                    userIn = scan.nextLine();
                    rent.addNewMovie(userIn);
                } else if (userIn.contentEquals("6")){
                    System.out.println(" Customer List : ");
                    newcust.custList();
                } else if (userIn.contentEquals("Q")){
                    System.out.println(" Exit System ");
                } else{
                    System.out.println(" See you next time! ");
                }
                break;
            //EXISTING CUSTOMER
            case "2":
                custTran();
                
                userIn = scan.nextLine();
                Customer custObj = new Customer(userIn);

                custObj.searchCust(custObj);
               //custObj.customerMovie.customerRentalSearch(custObj.getCustID());
                
                if (custObj.deleted != true){
                    
                    existCustOpt();
                
                    userIn = scan.nextLine();
                
                    if (userIn.contentEquals("1")){
                        rentmovie();
                    
                        userIn = scan.nextLine();
                        rent.rentMovie(userIn);
                        // first movie input
                    
                        rentQuest();//ask for more rentals
                        userIn = scan.nextLine();
                        
                        if(!userIn.equalsIgnoreCase("1")){
                            System.out.println(" Back to Main Menu ");
                            existCustOpt(); // Customer Main menu
                            userIn = scan.nextLine();
                        }                       
                        else{
                        rentMoreMovies(); 
                    
                        userIn = scan.nextLine();
                        rent.rentMovie(userIn);
                        // second movie input
                        
                            rentQuest();//ask for more rentals
                            userIn = scan.nextLine();
                            
                            if(!userIn.equalsIgnoreCase("1")){
                                System.out.println(" Back to Main Menu ");
                                xistCustOpt(); // Customer Main menu
                                userIn = scan.nextLine();
                            }else{
                                rentMoreMovies(); // input another movie to rent
                    
                                userIn = scan.nextLine();
                                rent.rentMovie(userIn);
                            }        
                    }
                        
                    }else if (userIn.contentEquals("2")){
                    System.out.println(" Movie List : ");
                    rent.movieList();
                    } else if (userIn.contentEquals("3")){
                    System.out.println(" Current Rented Movies -- these are unavailable : ");
                    rent.showCurrRent();
                    }else if (userIn.contentEquals("4")){
                    System.out.println(" Movie History -- by rental date : ");
                    userIn = scan.nextLine();
                    rent.showMovieHis(userIn);
                    //controller for movie history
                    }else if (userIn.contentEquals("5")){
                    System.out.println(" Return Movie : ");
                    userIn = scan.nextLine();
                    rent.returnMovie(userIn);
                    }else if (userIn.contentEquals("6")){
                    System.out.println(" Create New Movie - insert movie name: ");
                    userIn = scan.nextLine();
                    rent.addNewMovie(userIn);
                    }else if (userIn.contentEquals("7")){  
                    System.out.println(" Enter customer you would like to delete : ");
                    userIn = scan.nextLine();
                    custObj.delCust(userIn);     
                    }else if (userIn.contentEquals("8")){
                    System.out.println(" Customer List : ");
                    custObj.custList();
                    }else if (userIn.contentEquals("Q")){
                    System.out.println(" Exit System ");
                    }else{
                    System.out.println(" Invalid Options ");
                    }
                }    
                else{
                    System.out.println("Customer is Deleted! - Cannot Rent Movies");
                }
                break;
            case "Q": 
                System.out.println(" Quit System - See you next time! ");
            default:
                System.out.println("INVALID ENTRY");
        }

    }
}
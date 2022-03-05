/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Kenecia
 */
public class RentItem implements ModelInterface {
    private String custName;   
    private String movieName;
    private String movieID;
    private boolean rented;
    private String rentData;
    private String movieData;
    private String rentID;
    public String rent_dateOut_txt;
    public String rent_dateIn_txt;
    public String rent_clientID_txt;
    public String rent_movieID_txt;
    public String rentalID_txt;
    public String movie_title_txt;
    public String movie_id_txt;
    public boolean rented_txt;
    
    PrintWriter writer;
    Rental_View view;
    
    public RentItem(){}
    
    public RentItem(String movieName){
        this.movieName = movieName;

        genMovieID();
        this.rented = false;
    }
    
    public RentItem(String movieName, String movieID, Boolean rented){
        this.movieName = movieName;
        this.movieID = movieID;
        this.rented = rented;
    }
    
        //generate Movie ID
    public void genMovieID(){
        Random rannum = new Random();
        this.movieID = String.valueOf(rannum.nextInt(100));
    }
    
    public void getRentID() throws IOException{
        int rentNum;
        
        PrintWriter writer;
        try { 
            Scanner fileScanner = new Scanner(new File("rentID.txt"));
            while(fileScanner.hasNextLine()){
                  rentID = fileScanner.nextLine();
            }      
            
                  rentNum = Integer.parseInt(rentID);
                  rentNum++;
            
            //send lastes value to rentID file to use
            writer = new PrintWriter(new FileWriter("rentID.txt", true));
            writer.println(rentNum); 
            writer.close();            
        }
        catch (IllegalStateException e) { 
                  System.out.println("Exception thrown : " + e); 
        }     
    } 
    
    //add or create new movie
     public void addNewMovie(String movieName) throws IOException{
        this.movieName = movieName;
        
        genMovieID();
        this.rented = false;
       
        printMovieInfo();
    } 
    
     //show all movies starting with current rented movies
    public void movieList() throws IOException{
        String[] movie_hold = new String[20];//array for 20 blocks
        int arInt = 0;// variable for second array
        
        System.out.println("Current Rented movies :");
        try { 
                Scanner fileScanner = new Scanner(new File("movies.txt"));
                while(fileScanner.hasNextLine()){
                    movieData = fileScanner.nextLine();

                    String[] movie_data = movieData.split(";");
                    movie_title_txt = movie_data[0];
                    movie_id_txt = movie_data[1];
                    rented_txt = Boolean.parseBoolean(movie_data[2]);

                    if(rented_txt == true){ 
                        System.out.println(movie_title_txt);//print value of true for rented movies
                    }else{
                        movie_hold[arInt] = movie_title_txt;//hold value of false for rented movies
                        arInt++;
                    }
                }                            
                fileScanner.close();
                
                System.out.println("Movies available to rent :");
                arInt = 0;
                //loop thru array to find the false rented movies
                while(movie_hold[arInt] != null){    
                    System.out.println(movie_hold[arInt]);
                    arInt ++;//stop displaying at the end of array reading
                }   
            } 
            catch (IllegalStateException e) { 
                System.out.println("Exception thrown : " + e); 
            }    
    }
    
    //show current rented
    public void showCurrRent() throws IOException{
        try {
                Scanner fileScanner = new Scanner(new File("movies.txt"));
                while(fileScanner.hasNextLine()){
                    movieData = fileScanner.nextLine();

                    String[] movie_data = movieData.split(";");
                    movie_title_txt = movie_data[0];
                    movie_id_txt = movie_data[1];
                    rented_txt = Boolean.parseBoolean(movie_data[2]);
                
                    if(rented_txt == true){ 
                        System.out.println(movie_title_txt);//print value of true for rented movies
                    }
                }
            fileScanner.close();            
            }
        catch(IllegalStateException e){
            System.out.println("Exception thrown : " + e);
        }
    }
    
    //show movie history
    public void showMovieHis(String movieID) throws IOException{
        String[] movie_hold = new String[20];//array for 20 blocks
        int arInt = 0;// variable for second array
        
        try {
                Scanner fileScanner = new Scanner(new File("rental_info.txt"));
                while(fileScanner.hasNextLine()){
                    rentData = fileScanner.nextLine();

                    String[] rent_data = rentData.split(";");
                            rent_dateOut_txt = rent_data[0];
                            rent_dateIn_txt = rent_data[1];
                            rent_clientID_txt = rent_data[2];
                            rent_movieID_txt = rent_data[3];
                            rentalID_txt = rent_data[4];
                    
                    if(movieID.equals(rent_movieID_txt)){
                        movie_hold[arInt]= rent_dateOut_txt;
                        arInt++;
                   
                    }
                }
            fileScanner.close();
         
            Arrays.sort(movie_hold);
            
            arInt = 0;
                //loop thru array to find the false rented movies
            while(movie_hold[arInt] != null){    
                System.out.println(movie_hold[arInt]);
                arInt ++;//stop displaying at the end of array reading
            }
        }
        catch(IllegalStateException e){
            System.out.println("Exception thrown : " + e);
        }
    }    

    //search rental info text to store into a string
    //customer count have many rentals available
    public void customerRentalSearch(String custID) throws IOException{
            try { 
                    Scanner fileScanner = new Scanner(new File("rental_info.txt"));
                    while(fileScanner.hasNextLine()){
                            rentData = fileScanner.nextLine();
                            
                            //populate into to array
                            String[] rent_data = rentData.split(";");
                            rent_dateOut_txt = rent_data[0];
                            rent_dateIn_txt = rent_data[1];
                            rent_clientID_txt = rent_data[2];
                            rent_movieID_txt = rent_data[3];
                            rentalID_txt = rent_data[4];

                
                            if (custID.equals(rent_clientID_txt)){
                                System.out.println("matched");
                                rentCustomer.tallyCustomerRental();
                            }
                    }                                                     
                    fileScanner.close();
            } 
            catch (IllegalStateException e) { 
                System.out.println("Exception thrown : " + e); 
            }      
 
    }
    
    public void rentMovie(String movieName) throws IOException{
        if (rentCustomer.getCustRentalTally() > 3){
                System.out.println("Sorry You cannot rent anymore movies.");
        }
        else{
            try { 
                Scanner fileScanner = new Scanner(new File("movies.txt"));
                while(fileScanner.hasNextLine()){
                    movieData = fileScanner.nextLine();

                    String[] movie_data = movieData.split(";");
                    movie_title_txt = movie_data[0];
                    movie_id_txt = movie_data[1];
                    rented_txt = Boolean.parseBoolean(movie_data[2]);

                    //check if movie is already rented 
                    if (movieName.equalsIgnoreCase(movie_title_txt)){
                        if (rented_txt == true){
                                //movie unavailable
                            System.out.println("Movie is unavailable to rent at this time "); 
                        }else{
                            System.out.println("This selection is available to rent ");
                            this.movieName =  movie_title_txt;
                            this.movieID = movie_id_txt;
                            this.rented = true;
                            
                            fileScanner.close();
       
                            updtTextFile(this.movieName);
                            getRentID();
                            printRent();
                            }
                        }
                    }                           
                      
                } 
                catch (IllegalStateException e) { 
                    System.out.println("Exception thrown : " + e); 
                }     
            }
    } 
     
    public void returnMovie(String movieName) throws IOException{
            try { 
                Scanner fileScanner = new Scanner(new File("movies.txt"));
                while(fileScanner.hasNextLine()){
                    movieData = fileScanner.nextLine();

                    String[] movie_data = movieData.split(";");
                    movie_title_txt = movie_data[0];
                    movie_id_txt = movie_data[1];
                    rented_txt = Boolean.parseBoolean(movie_data[2]);

                    //delete the movies.txt file and recreate it in a method
                    if (movieName.equalsIgnoreCase(movie_title_txt)){
                        this.movieName = movie_title_txt;
                        this.movieID = movie_id_txt;
                        this.rented = false;
                        System.out.println("Thank You! For returning: " + movie_title_txt);
                    }
                }
                fileScanner.close();
                updtTextFile(this.movieName);
                getRentID();
                printReturn();
            } 
            catch (IllegalStateException e) { 
                System.out.println("Exception thrown : " + e); 
            }     
    }
    

    public void updtTextFile(String movieTitle){//modify list when a movies is returned/rented
        File fileToBeModified = new File("movies.txt");  
        String oldContent = "";
        String temp_textLine = "";                    
        BufferedReader reader = null;
        FileWriter writer = null;
             
        try{
            reader = new BufferedReader(new FileReader(fileToBeModified));
             
            //Reading all the lines of input text file into oldContent
            temp_textLine = reader.readLine();// movie_Name
             
            while (temp_textLine != null){
                String[] textLine_hold = temp_textLine.split(";");
                if(movieTitle.equalsIgnoreCase(textLine_hold[0])){

                    temp_textLine ="";
                    temp_textLine = this.movieName + ";" + this.movieID + ";" + this.rented;
                }
                oldContent = oldContent + temp_textLine + System.lineSeparator();
                temp_textLine = reader.readLine();
            }
            //Rewriting the input text file with newContent
             
            writer = new FileWriter(fileToBeModified);
             
            writer.write(oldContent);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally{
            try{               
                reader.close();                 
                writer.close();
            } 
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }   
     
    public void printMovieInfo() throws IOException{
        PrintWriter writer;
        
        writer = new PrintWriter(new FileWriter("movies.txt", true));
        writer.println(movieName + ";" + movieID + ";" + rented);
        System.out.println(movieName + " ; " + movieID + " ; " + rented);
        writer.close();
    }
      
    public void printRent() throws IOException{
        PrintWriter writer;
        DateFormat df = new SimpleDateFormat("MM/dd/yy");
        Date dateIn = new Date();
        Date dateOut = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        df.format(dateOut.getTime());
        
        c.add(Calendar.DATE, 5);
        
        writer = new PrintWriter(new FileWriter("rental_info.txt", true));
        writer.println(df.format(dateIn)+ " ; " + " " +  " ; " + 
                       rentCustomer.custID + " ; " + movieID + " ; " + rentID); 
        writer.close(); 
    }  
    
    //rent movie
    public void printReturn() throws IOException{
        PrintWriter writer;
        DateFormat df = new SimpleDateFormat("MM/dd/yy");
        Date dateIn = new Date();
        Date dateOut = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        df.format(dateOut.getTime());
        
        c.add(Calendar.DATE, 5);
        writer = new PrintWriter(new FileWriter("rental_info.txt", true));
        writer.println(df.format(dateIn)+ " ; " + df.format(dateOut) +  " ; " + 
                       rentCustomer.custID + " ; " + movieID + " ; " + rentID);
        writer.close();         
    }
    
}
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
import java.util.*;
/**
 *
 * @author Kenecia
 */
public class Customer implements ModelInterface, ViewInterface {
    boolean deleted;
    //membership activity - true or false;
    public String custName;
    public String custID;
    
    
    PrintWriter writer;
    RentItem customerMovie;
    
    String custData;
    String rentData;
  
    public int rent_num = 0;
    
    public Customer(){
    }
    
    public Customer(String custName) throws IOException{
        this.custName = custName;

        searchCust(this);
        rentCustomer.custName = this.custName;
        rentCustomer.custID = this.custID;
        rentCustomer.deleted = this.deleted;    
        // customer name; random customer number; membership activation    
    }
    
    public Customer(String custName, String custID, Boolean deleted){
        this.custName = custName;
        this.custID = custID;
        this.deleted = deleted;
        // customer name; random customer number; membership activation
        //System.out.println(this);
    }
    
    public void addNewCust(String custName) throws IOException{
        this.custName = custName;
        
        genNumber();
        this.deleted = false;
        
        rentCustomer.custName = this.custName;
        rentCustomer.custID = this.custID;
        rentCustomer.deleted = this.deleted;
        printCustInfo();
    }
    
    public void delCust(String custName) throws IOException{
        this.custName = custName;
        this.custID = custID;
        this.deleted = true;
        
        updtTextFile(custName);
        printCustInfo();
    }

    //generate cust ID
    private void genNumber(){
        Random ran = new Random();
        this.custID = String.valueOf(ran.nextInt(100));
    }
    
    public void custList() throws IOException{
        BufferedReader list = new BufferedReader(new FileReader("clients.txt"));
        
        String line;
        while((line = list.readLine()) != null)
            System.out.println(line);
    }
    
    public void updtTextFile(String custName){//modify list when a movies is returned/rented
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
                if(custName.equalsIgnoreCase(textLine_hold[0])){

                    temp_textLine ="";
                    temp_textLine = this.custName + ";" + this.custID + ";" + this.deleted;
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
    
    //search for excisting customer
      public void searchCust(Customer cusObj) throws IOException{

        try { 
                Scanner fileScanner = new Scanner(new File("clients.txt"));
                while(fileScanner.hasNextLine()){

                    if (fileScanner.hasNext(cusObj.custName) == true){
                        custData = fileScanner.nextLine();
                        String[] cust_data = custData.split(";");
                        this.custName = cust_data[0];
                        this.custID = cust_data[1];
                        this.deleted = Boolean.parseBoolean(cust_data[2]);
                    }else{
                        fileScanner.nextLine();
                    }
                }                           
                fileScanner.close();
        } 
        catch (IllegalStateException e) { 
            System.out.println("Exception thrown : " + e); 
        }      
    }

    public void showAllCustomers() throws IOException{
        try { 
            Scanner fileScanner = new Scanner(new File("clients.txt"));
            while(fileScanner.hasNextLine()){
                custData = fileScanner.nextLine();
                String[] cust_data = custData.split(";");
                System.out.println(cust_data[0]);
            }
            fileScanner.close();
        } 
        catch (IllegalStateException e) { 
            System.out.println("Exception thrown : " + e); 
        }      
    }

    public void tallyCustomerRental(){
        this.rent_num = rent_num + 1;
    }

    public int getCustRentalTally(){
        System.out.println(this.rent_num);
        return this.rent_num;
    }
   
    public void updtCustRentalTally(){
        this.rent_num = rent_num - 1;
    }
    
    public String getCustName(){
        System.out.println(this.custName);
        return this.custName;
    }
    
    public String getCustID(){
        System.out.println("cust id" + this.custID);
        return this.custID;
    }
    
    public void custID(){
        this.custID = custID;
        System.out.println("cust ID: " + custID);
    }
    
    public void printCustInfo() throws IOException{
        writer = new PrintWriter(new FileWriter("clients.txt", true));
        writer.println(custName + ";" + custID + ";" + deleted);
        System.out.println(custName + custID + " ; " + deleted);
        writer.close();
    }
    
    @Override
    public void displayCustomer(){
    }
    
    public void displayRentInfo(){}
    
    public void displayMovie(){}
    
}

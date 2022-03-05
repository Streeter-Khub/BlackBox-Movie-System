/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;


/**
 *
 * @author Kenecia
 */
public class ClientController implements ClientInterface, ModelInterface {
    
    Customer model;
    RentItem movie;
    Rental_View view;
  
    
    public ClientController(Customer model, Rental_View view) {
        this.model = model;
        this.view = view;
    }
    
    public ClientController(RentItem movie, Rental_View view){
        this.movie = movie;
        this.view = view;
    }
    
    @Override
    public void updtTextFile(String oldString){};
    
    @Override
    public void removeMovie(MovieObserver o){};
    
    @Override
    public void registerMovie(MovieObserver o){};
    
    @Override
    public void notifyMovie(){};    
    
    @Override
    public void removeObserver(Cust_InfoObserver o){};
    
    @Override
    public void notifyObserver(){};
    
    @Override
    public void registerObserver(Cust_InfoObserver o){};

}

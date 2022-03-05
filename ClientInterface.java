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
public interface ClientInterface {
    //void initialize();
    void registerObserver(Cust_InfoObserver o);
    void notifyObserver();
    void removeObserver(Cust_InfoObserver o);
    void registerMovie(MovieObserver o);
    void notifyMovie();
    void removeMovie(MovieObserver o);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/**
 *
 * @author 
 * @param <T> 
 */
public interface IService<T> {
   void ajouter(T t);
   void ajouter2(T t);
   void supprimer(int id);  
   void modifier(T t);
   void modifier2(T t);
    List<T>afficher();
    T readById(int id);
    
}

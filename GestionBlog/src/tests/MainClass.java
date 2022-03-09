/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import entities.Blog;
import services.BlogCRUD;
import entities.Rubrique;
import services.RubriqueCRUD;
import utils.MyConnection;

/**
 *
 * @author oussama
 */
public class MainClass {
    public static void main(String[] args) {
        //MyConnection mc = new MyConnection();
        //MyConnection mc = MyConnection.getInstance();
        //MyConnection mc2 = MyConnection.getInstance();
        //System.out.println(mc.hashCode()+ " - "+mc2.hashCode());
        RubriqueCRUD rb = new RubriqueCRUD();
        //pcd.ajouterRubrique();
        
        
       //Rubrique r1 = new Rubrique("abc");
       //rb.ajouter(r1);  
       
       
      //Rubrique r2= new Rubrique(9,"ToT");
       //rb.modifier(r2);
    
       //Rubrique r3 = new Rubrique(9);
       //rb.supprimer(r3);
       
       
       
       
       BlogCRUD bl = new BlogCRUD();
       
       //Blog b1 = new Blog("a","b","c");
       //bl.ajouter(b1);  
       
       
      //Blog b2= new Blog(23,"x","y","z");
      //bl.modifier(b2);
    
       //Blog b3 = new Blog(18);
       //bl.supprimer(b3);
       
       
       
for(Rubrique e:rb.afficher()){
           System.out.println(e.toString());
        }

for(Blog e:bl.afficher()){
           System.out.println(e.toString());
        }

    //System.out.println(pcd.afficherRubriques());
}
}
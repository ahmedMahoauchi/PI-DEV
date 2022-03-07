/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entites.Planning;
import entites.Programme;
import services.ServicePlanning;
import services.ServiceProgramme;
import util.MyDB;


/**
 *
 * @author ASUS
 */
public class Main {
    public static void main(String[] args) {
        MyDB.getInstance();
        ServiceProgramme prog=new ServiceProgramme();
            //prog.ajouter();  
        //Programme prog2=new Programme(1,2,3,"test","test","muscilation","cgtgeg");
        //prog.ajouter(prog2);
        //System.out.println(prog.afficher());
        //Programme prog3=new Programme(3,1,2,3,"test44","test","mu","cgtgeg");
                //prog.modifier(prog3);
                //prog.supprimer(21);
  //
  //
  //
  //
  //
  
  ServicePlanning plan= new ServicePlanning(); 
        //Planning plan1=new Planning(7,"test666","test666","1999/12/12","test666");
        //plan.ajouter(plan1);
        System.out.println(plan.afficher());
        //Planning plan2=new Planning(1,7,"test66","test66","27-05-22","test66");
       // plan.modifier(plan2);
       //plan.supprimer(3);
    }
}

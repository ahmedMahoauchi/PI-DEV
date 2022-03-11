/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author tab3ouch
 */
public class Session {
    
    private static Session instance;
     private Employe SessionUser;
     private client SessionClient;


     private Session() {
     }
     
      private Session(Employe SessionUser) {
          this.SessionUser=SessionUser;
     }
      private Session(client SessionClient) {
          this.SessionClient=SessionClient;
     }

     public static Session StartSession() {
        if(instance == null) {
            instance = new Session();
        }
        return instance;
    }
     

     public static Session StartSession(Employe SessionUser) {
        if(instance == null) {
            instance = new Session(SessionUser);
        }
        return instance;
    } 
     public static Session StartSession(client SessionClient) {
        if(instance == null) {
            instance = new Session(SessionClient);
        }
        return instance;
    } 
     public static Session getSession() {
        return instance;
    }
     public static Session getSession2() {
        return instance;
    }
      public  void clearSession() {
      SessionUser=null;

      }
      public  void clearSession2() {
      SessionClient=null;

      }
      
      
      public void setSessionUser(Employe SessionUser){
      this.SessionUser=SessionUser;
      }
      
      public void setSessionClient(client SessionClient){
      this.SessionClient=SessionClient;
      }
      
      public Employe getSessionUser(){
      return this.SessionUser;
      }
      
      public client getSessionClient(){
      return this.SessionClient;
      }

    
}

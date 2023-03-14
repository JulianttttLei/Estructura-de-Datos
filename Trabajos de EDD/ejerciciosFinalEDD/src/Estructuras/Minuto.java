/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author agust
 */
public class Minuto {
    
    private int minAct;
    private int min;
   
     
    public Minuto(int minAct,int min){
        this.minAct=minAct;
        this.min=min;
      
    }
    
    public int getMinAct(){
        return minAct;
    }
    public int getMin(){
        return min;
    }
   
    
    public void setMinAct(int minAct){
        this.minAct=minAct;
        
    }
    public void setMin(int min){
        this.min=min;
    }
   
}

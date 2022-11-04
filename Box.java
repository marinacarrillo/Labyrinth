/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curso.pairprogramming;

/**
 *
 * @author mjimen19
 */
public class Box {
    private char box;

    public Box(char box) {
        this.box = box;
    }
    
    public char getBox() {
        return box;
    }
    
    public void setBox(char character){
        box = character;
    }
    
    public boolean isOccupied(){
        boolean isOcu = true;
        if(box == ' ' || box == 'S'){
            isOcu = false;
        }
        
        return isOcu;
    }
    
    public boolean isFinish(){
        boolean isFin = false;
        if(box == 'S'){
            isFin = true;
        }
        
        return isFin;
    }
    
    public boolean isStart(){
        boolean isStart = false;
        if(box == 'E'){
            isStart = true;
        }
        
        return isStart;
    }
}

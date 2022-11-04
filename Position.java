/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curso.pairprogramming;

/**
 *
 * @author mjimen19
 */
public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Position (Position p){
        this.x = p.getX();
        this.y = p.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
   
    
    public Position getRight(){
        return new Position(x+1,y);
    }
    
    public Position getLeft(){
        return new Position(x-1,y);
    }
    
    public Position getUp(){
        return new Position(x,y-1);
    }
    
    public Position getDown(){
        return new Position(x,y+1);
    }
}

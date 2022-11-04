/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curso.pairprogramming;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mjimen19
 */
public class Labyrinth {
    private String filename;
    private int sizeX;
    private int sizeY;
    private Box[][] labyrinth = new Box[sizeX][sizeY];
    private ArrayDeque <Position> moves = new ArrayDeque();
    private Position currentPos = new Position(0,0);
    private Position lastMove = new Position(0,0);

    private enum Direction{
    RIGHT,LEFT,UP,DOWN}
    
    public Labyrinth(String filename, int maxX, int maxY) {
        this.filename = filename;
        sizeX = maxX;
        sizeY = maxY;
        labyrinth = new Box[sizeX][sizeY];
        for (int i = 0; i < labyrinth.length; i++) {
            for (int j = 0; j < labyrinth[0].length; j++) {
                labyrinth[i][j] = new Box(' ');
            }
        }
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    
    /*
        Set the labyrinth from the file stored at filename
    */
    public void setLabyrinth() throws FileNotFoundException{
        Scanner scanner = null;
        try{
            File file = new File(filename);
            scanner = new Scanner(file);
        }catch(FileNotFoundException e){
            throw new FileNotFoundException("Error al leer el fichero");
        }        
            int countX = 0;
            int countY = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for(char c : line.toCharArray()){
                    labyrinth[countX][countY] = new Box(c);
                    countX++;
//                    System.out.println(countX + "," + countY + " char: " + c);
                }
                countX = 0;
                countY++;
            }
            
            scanner.close();

    }
    
    /*
        Set the labyrinth and make all moves until the end
    */
    public ArrayDeque<Position> startGame(){
        try {
            setLabyrinth();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Labyrinth.class.getName()).log(Level.SEVERE, null, ex);
        }
        getStart();
        
        while(!hasFinished()){
            nextMove();
        }
        
        return moves;
    }
    
    /*
        Get the start position of the labyrinth, considering that all entrances are at the top of the matrix
    */
    public void getStart(){
        Position entry = null;
        
        for (int i = 0; i < labyrinth.length; i++) {
                if(labyrinth[i][0].isStart()){
                    entry = new Position(i,0);
                }
        }
        currentPos = entry;
        lastMove = new Position(currentPos);
        moves.push(new Position(currentPos));
    }
    
    /*
        This method calculates the next move by checking if it can move to the right, left,
        up and down.
    */
    public void nextMove(){
        if(canMove(Direction.RIGHT)){
            lastMove = currentPos;
            currentPos = currentPos.getRight();
            moves.push(currentPos);
        }else if(canMove(Direction.LEFT)){
            lastMove = currentPos;
            currentPos = currentPos.getLeft();
            moves.push(currentPos);
        }else if(canMove(Direction.UP)){
            lastMove = currentPos;
            currentPos = currentPos.getUp();
            moves.push(currentPos);
        }else if(canMove(Direction.DOWN)){
            lastMove = currentPos;
            currentPos = currentPos.getDown();
            moves.push(currentPos);
        }else{
            //No move left, so it goes back to the last position
            labyrinth[currentPos.getX()][currentPos.getY()].setBox('Z');
            System.out.println("casilla eliminada " + currentPos.getX() + "," + currentPos.getY() +labyrinth[currentPos.getX()][currentPos.getY()].getBox());
            currentPos = new Position(lastMove);
            moves.pop();
            Position aux = moves.pop();
            lastMove = new Position(moves.peekFirst());
            moves.push(new Position(aux));
        }
    }
    
    public boolean hasFinished(){
        return labyrinth[currentPos.getX()][currentPos.getY()].isFinish();
    }
    
    /*
        Checks if it can move to the direction given based on wether there is path unblocked and not explored
    */
    private boolean canMove(Direction direction){
        boolean canMove = false;
        int nextX = currentPos.getX();
        int nextY = currentPos.getY();
        
        switch(direction){
            case RIGHT -> nextX++;
            case LEFT -> nextX--;
            case UP -> nextY--;
            case DOWN -> nextY++;
        }
        
        
        if(nextX < 0 || nextY < 0){
            System.out.println("No puede mover porque se sale del borde");
        }else if(labyrinth[nextX][nextY].isOccupied()){
            System.out.println("No puede mover porque la casilla esta ocupada");
        }else if(lastMove.getX() == nextX && lastMove.getY() == nextY){
            System.out.println("No puede mover porque viene de ahí");
        }else{
            System.out.println("Puede mover");
            canMove = true;
        }
        
        return canMove;
    }
    
    /*
        Return the labyrinth with the path from the entrance to the exit
    */
    public Box[][] getSolution(){
        Box[][] solution = new Box[sizeX][sizeY];
        
        for(int i = 0; i < labyrinth.length; i++){
            for(int j = 0; j < labyrinth[0].length; j++){
                solution[i][j] = labyrinth[i][j];
                if(solution[i][j].getBox() == 'Z'){
                    solution[i][j].setBox(' ');
                }
            }
        }
        
        for(Position p : moves){
            solution[p.getX()][p.getY()].setBox('.');
        }
        
        return solution;
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.curso.pairprogramming;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 *
 * @author mjimen19
 */
public class PairProgramming {

    public static void main(String[] args) {
        Labyrinth lab = new Labyrinth("C:\\Users\\mjimen19\\Downloads\\laberinto.txt", 100,100);
        
        ArrayDeque <Position> route = new ArrayDeque();
        route = lab.startGame();
        
        
        System.out.println("The route of the labyrinth solved:");
//        for(Position pos : route){
//            System.out.println("(" + pos.getX() + "," + pos.getY() + ")");
//        }
        Box[][] solution = lab.getSolution();
        for(int i = 0; i < solution.length; i++){
            for(int j = 0; j < solution[0].length; j++){
                System.out.print(solution[j][i].getBox());
            }
            System.out.println("");
        }
    }
}

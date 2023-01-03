
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author RafikMedici
 */
public class Piece {

    //attributs
    BufferedImage img;
    int x;
    int y;
    public enum PieceType {
    King,Queen,Knight,Bishop,Rook,Soldier,voide;
 }
    public enum Color {
    White, Black,Nope;
 }
    PieceType id;
    Color colour;

    //construction
    public Piece(PieceType id, Color colour,int x,int y) {
        this.id = id;
        this.colour=colour;
        this.x=x;
        this.y=y;
    }
    //methods
    
 public boolean ismovevalid(Case[][] Board, Case case2) {
        return false;
    }
 public ArrayList<Case> legalmoves(Case[][] Board){
     ArrayList<Case> legalmoves= new ArrayList<Case>();
     for (int i=0; i<8;i++){
         for (int j=0;j<8;j++){
             if (this.ismovevalid(Board, Board[i][j])){
                 legalmoves.add(Board[i][j]);
             }
         }
     }
     return legalmoves;
 }


    public PieceType getId() {
        return id;
    }

    public Color getColour() {
        return colour;
    }
    public boolean canmove(Case[][] Board){
        return false;
    }
    public void draw(Graphics g, int x, int y){
    }
    public boolean isCastling(Case[][] Board, Case case2){
        return false;
    }
}

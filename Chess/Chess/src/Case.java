/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author RafikMedici
 */
public class Case {
    int x, y;
    boolean isOccupied;
    Piece Occpie;

    public Case(int x, int y, boolean isOccupied, Piece Occpie) {
        this.x = x;
        this.y = y;
        this.isOccupied = isOccupied;
        this.Occpie = Occpie;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public Piece getOccpie() {
        return Occpie;
    }

    public void setOccpie(Piece Occpie) {
        this.Occpie = Occpie;
    }
    public double distance(Case case1){
        return Math.sqrt(Math.pow((double)(this.x-case1.x),2.0)+Math.pow((double)(this.y-case1.y),2.0));
    }
}


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author RafikMedici
 */
public class King extends Piece{
    BufferedImage img;
    public King(Color color,int x,int y) throws IOException{
        super(PieceType.King,color,x,y);
        if (color == Piece.Color.Black) {
           img=ImageIO.read(new File("UI\\Bking.png"));
           //img=ImageIO.read(new File("Z:\\Mes documents\\java\\java\\Chess\\Chess\\UI\\Bking.png"));
        } else {
            img=ImageIO.read(new File("UI\\Wking.png"));
            //img=ImageIO.read(new File("Z:\\Mes documents\\java\\java\\Chess\\Chess\\UI\\Wking.png"));
        }
    }
    @Override
    public boolean ismovevalid(Case[][] Board,Case case2){
        boolean isinGamevalid = false;
        if (Board[this.x][this.y].distance(case2)==1.0||(Math.abs(x-case2.x)==1.0&& Math.abs(y-case2.y)==1.0)){
        isinGamevalid=true;
        if (case2.isOccupied&&case2.Occpie.colour==this.colour){
            isinGamevalid=false;
        }
    }
        
        return isinGamevalid;
    }
    @Override
    public boolean isCastling(Case[][] Board, Case case2){
        boolean iscastling=false;
        if(Board[x][y].distance(case2)==2.0&&y==case2.y&&!case2.isOccupied){
            if (case2.x>x&&!Board[x+1][y].isOccupied){
                iscastling=true;
            }
            if (case2.x<x&&!Board[x-1][y].isOccupied&&!Board[x-2][y].isOccupied){
               iscastling=true; 
            }
        }
        return iscastling;
    }
    @Override
    public boolean canmove(Case[][] Board){
        return !this.legalmoves(Board).isEmpty();
        
}
    @Override
    public void draw(Graphics g, int x, int y){
        g.drawImage(img, x, y, null);
    }
            
}

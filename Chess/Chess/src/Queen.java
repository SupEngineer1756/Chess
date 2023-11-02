
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author RafikMedici
 */
public class Queen extends Piece{
    BufferedImage img;
    public Queen(Piece.Color color,int x,int y) throws IOException{
        super(Piece.PieceType.Queen,color, x,y);
        if (color == Piece.Color.Black) {
           img=ImageIO.read(new File("UI\\Bqueen.png"));
           //img=ImageIO.read(new File("Z:\\Mes documents\\java\\java\\Chess\\Chess\\UI\\Bqueen.png"));
        } else {
           img=ImageIO.read(new File("UI\\Wqueen.png"));
          //img=ImageIO.read(new File("Z:\\Mes documents\\java\\java\\Chess\\Chess\\UI\\Wqueen.png"));
        }
    }
    @Override
    public boolean ismovevalid(Case[][] Board, Case case2){
        boolean isinGamevalid=false;
        if (Math.abs(x - case2.x) == Math.abs(case2.y - y) && case2.x != Board[x][y].x) {
            isinGamevalid = true;
            if (Board[case2.x][case2.y].isOccupied&&Board[case2.x][case2.y].Occpie.colour==this.colour){
                isinGamevalid = false;
            }
            int n =Math.abs(x - case2.x);
            int xd=(case2.x-x)/n;
            int yd=(case2.y-y)/n;
            for (int i=1;i<n;i++){
                if (Board[x+i*xd][y+i*yd].isOccupied){
                    isinGamevalid = false;
                }
            }
            

        }
        if (case2.x == Board[x][y].x && case2.y != Board[x][y].y) {
            isinGamevalid = true;
            for (int j = Math.min(Board[x][y].y, case2.y) + 1; j < Math.max(Board[x][y].y, case2.y); j++) {
                System.out.println("occupiedy= " + Board[case2.x][j].isOccupied);
                if (Board[case2.x][j].isOccupied) {
                    isinGamevalid = false;
                }

            }
            if (Board[case2.x][y].isOccupied&&case2.Occpie.colour==this.colour){
                isinGamevalid = false;
            }
        }
        if (case2.y == Board[x][y].y && case2.x != Board[x][y].x) {
            isinGamevalid = true;
            for (int i = Math.min(Board[x][y].x, case2.x) + 1; i < Math.max(Board[x][y].x, case2.x); i++) {
                System.out.println("occupiedx= " + Board[i][case2.y].isOccupied);
                if (Board[i][case2.y].isOccupied) {
                    isinGamevalid = false;
                }

            }
            if (Board[case2.x][y].isOccupied&&case2.Occpie.colour==this.colour){
                isinGamevalid = false;
            }

        }
        
        return (isinGamevalid);
    }
    @Override
    public void draw(Graphics g, int x, int y){
        g.drawImage(img, x, y, null);
    }
      
}

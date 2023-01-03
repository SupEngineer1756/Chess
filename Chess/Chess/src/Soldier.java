
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
public class Soldier extends Piece {

    BufferedImage img;

    public Soldier(Piece.Color color, int x, int y) throws IOException {
        super(Piece.PieceType.Soldier, color, x, y);
        if (color == Piece.Color.Black) {
            img = ImageIO.read(new File("C:\\Users\\RafikMedici\\Desktop\\Software Dev\\Java projects\\Chess\\Chess\\UI\\Bpawn.png"));
            //img = ImageIO.read(new File("Z:\\Mes documents\\java\\java\\Chess\\Chess\\UI\\Bpawn.png"));
        } else {
            img = ImageIO.read(new File("C:\\Users\\RafikMedici\\Desktop\\Software Dev\\Java projects\\Chess\\Chess\\UI\\Wpawn.png"));
            //img = ImageIO.read(new File("Z:\\Mes documents\\java\\java\\Chess\\Chess\\UI\\Wpawn.png"));
        }
    }

    @Override
    public boolean ismovevalid(Case[][] Board, Case case2) {
        boolean isinGamevalid = false;

        if (this.colour == Piece.Color.Black) {
                isinGamevalid = ( y == 6 &&(Board[x][y].distance(case2) == 2.0 && !case2.isOccupied)&& y > case2.y) || ((Board[x][y].distance(case2) == 1.0 && !case2.isOccupied) && y > case2.y) || (case2.isOccupied && case2.Occpie.colour != this.colour && Math.abs(x - case2.x) == 1.0 && Math.abs(y - case2.y) == 1.0);
            
        }
        if (this.colour == Piece.Color.White) {
                isinGamevalid = (y == 1 && (Board[x][y].distance(case2) == 2.0 && !case2.isOccupied) && y < case2.y)||((Board[x][y].distance(case2) == 1.0 && !case2.isOccupied) && y < case2.y) || (case2.isOccupied && case2.Occpie.colour != this.colour && Math.abs(x - case2.x) == 1.0 && Math.abs(y - case2.y) == 1.0);
         
        }
        //System.out.println("1=" + Board[x][y].x + "   " + Board[x][y].y + "  " + case2.x + "   " + case2.y + "   " + (Board[x][y].distance(case2) == 1.0));
        //System.out.println("2=" + (case2.isOccupied && case2.Occpie.colour != this.colour && case2.distance(case2) == Math.sqrt(2)));
        //System.out.println("isinGamevalid= " + isinGamevalid);
        return (isinGamevalid);
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        g.drawImage(img, x, y, null);
    }

}

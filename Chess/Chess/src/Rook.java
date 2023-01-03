
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
public class Rook extends Piece {

    BufferedImage img;

    public Rook(Piece.Color color, int x, int y) throws IOException {
        super(Piece.PieceType.Rook, color, x, y);
        if (color == Piece.Color.Black) {
            img = ImageIO.read(new File("C:\\Users\\RafikMedici\\Desktop\\Software Dev\\Java projects\\Chess\\Chess\\UI\\Brook.png"));
            //img = ImageIO.read(new File("Z:\\Mes documents\\java\\java\\Chess\\Chess\\UI\\Brook.png"));
        } else {
            img = ImageIO.read(new File("C:\\Users\\RafikMedici\\Desktop\\Software Dev\\Java projects\\Chess\\Chess\\UI\\Wrook.png"));
            //img = ImageIO.read(new File("Z:\\Mes documents\\java\\java\\Chess\\Chess\\UI\\Wrook.png"));
        }
    }

    @Override
    public boolean ismovevalid(Case[][] Board, Case case2) {
        boolean isinGamevalid = false;
        //System.out.println(Board[x][y].x +","+Board[x][y].y+ "    "+(case2.x)+","+case2.y);
        System.out.println(Board[x][y].distance(case2) + " " + (int) Board[x][y].distance(case2));
        System.out.println(Board[x][y].distance(case2) - (int) Board[x][y].distance(case2));
        System.out.println("case2.x == Board[x][y].x " + (case2.x == Board[x][y].x));
        System.out.println("case2.y == Board[x][y].y " + (case2.y == Board[x][y].y));
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
    public void draw(Graphics g, int x, int y) {
        g.drawImage(img, x, y, null);
    }

}

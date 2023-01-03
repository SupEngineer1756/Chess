
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
public class bishop extends Piece {

    BufferedImage img;

    public bishop(Piece.Color color, int x, int y) throws IOException {
        super(Piece.PieceType.Bishop, color, x, y);
        if (color == Piece.Color.Black) {
            img=ImageIO.read(new File("C:\\Users\\RafikMedici\\Desktop\\Software Dev\\Java projects\\Chess\\Chess\\UI\\Bbishop.png"));
            //img = ImageIO.read(new File("Z:\\Mes documents\\java\\java\\Chess\\Chess\\UI\\Bbishop.png"));
        } else {
            img=ImageIO.read(new File("C:\\Users\\RafikMedici\\Desktop\\Software Dev\\Java projects\\Chess\\Chess\\UI\\Wbishop.png"));
            //img = ImageIO.read(new File("Z:\\Mes documents\\java\\java\\Chess\\Chess\\UI\\Wbishop.png"));
        }
    }

    @Override
    public boolean ismovevalid(Case[][] Board, Case case2) {
        boolean isinGamevalid = false;
        //System.out.println(Math.abs(x-y)+"  "+Math.abs(case2.x-case2.y));
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

        return (isinGamevalid);
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        g.drawImage(img, x, y, null);
    }

}

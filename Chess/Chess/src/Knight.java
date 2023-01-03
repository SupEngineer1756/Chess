
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
public class Knight extends Piece {

    BufferedImage img;

    public Knight(Color color, int x, int y) throws IOException {
        super(PieceType.Knight, color, x, y);
        if (color == Piece.Color.Black) {
            img = ImageIO.read(new File("C:\\Users\\RafikMedici\\Desktop\\Software Dev\\Java projects\\Chess\\Chess\\UI\\Bknight.png"));
            //img=ImageIO.read(new File("Z:\\Mes documents\\java\\java\\Chess\\Chess\\UI\\Bknight.png"));
        } else {
            img = ImageIO.read(new File("C:\\Users\\RafikMedici\\Desktop\\Software Dev\\Java projects\\Chess\\Chess\\UI\\Wknight.png"));
            //img=ImageIO.read(new File("Z:\\Mes documents\\java\\java\\Chess\\Chess\\UI\\Wknight.png"));
        }
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        g.drawImage(img, x, y, null);
    }

    @Override
    public boolean ismovevalid(Case[][] Board, Case case2) {
        boolean isinGamevalid = false;
        if (((Math.abs(case2.x - Board[x][y].x) == 1 && (Math.abs(case2.y - Board[x][y].y) == 2)) || (Math.abs(case2.x - Board[x][y].x) == 2 && (Math.abs(case2.y - Board[x][y].y) == 1)))) {
            isinGamevalid = true;
            if ((case2.isOccupied && case2.Occpie.colour == this.colour)) {
                isinGamevalid = false;
            }
        }

        return isinGamevalid;
    }
}

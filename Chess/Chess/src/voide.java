
import java.awt.Graphics;
import java.awt.Graphics2D;
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
public class voide extends Piece {

    BufferedImage img;

    public voide(int x, int y) throws IOException {
        super(PieceType.voide, Color.Nope, x, y);
        img = ImageIO.read(new File("UI\\Bpawn.png"));
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        java.awt.Color Brown = new java.awt.Color(174, 108, 72);
        java.awt.Color Beige = new java.awt.Color(225, 203, 176);
        if ((x + y)/100 % 2 == 1) {
                    g2.setColor(Brown);
                    g2.fillRoundRect(x, y, 95, 95, 0, 0);
                    //System.out.println(i + "," + j + " " + "0");
                } else {
                    g2.setColor(Beige);
                    g2.fillRoundRect(x, y, 95, 95, 0, 0);
                    //System.out.println(i + "," + j + " " + "1");
                }
    }
}

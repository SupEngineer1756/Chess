/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author RafikMedici
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.event.MouseEvent;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GameFrame extends javax.swing.JFrame implements MouseListener {

    Game game;
    int x = -1, y = -1;

    /**
     * Creates new form GamePanel
     */
    public GameFrame() {
        super();
        this.game = new Game();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(null);
        this.setBounds(0, 0, 800, 800);
        this.addMouseListener(this);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        Color Brown = new Color(174, 108, 72);
        Color Beige = new Color(225, 203, 176);
        for (int i = 0; i < 800; i = i + 100) {
            for (int j = 0; j < 800; j = j + 100) {
                if (((i + j) / 100) % 2 == 1) {
                    g2.setColor(Color.BLACK);
                    g2.drawRect(i, j, 100, 100);
                    g2.setColor(Brown);
                    g2.fillRoundRect(i, j, 95, 95, 0, 0);
                    //System.out.println(i + "," + j + " " + "0");
                } else {
                    g2.setColor(Color.BLACK);
                    g2.drawRect(i, j, 100, 100);
                    g2.setColor(Beige);
                    g2.fillRoundRect(i, j, 95, 95, 0, 0);
                    //System.out.println(i + "," + j + " " + "1");
                }

            }
        }
        for (int i = 0; Math.round(i) < 800; i = i + 100) {
            for (int j = 0; Math.round(j) < 800; j = j + 100) {
                if (game.gameboard[i / 100][j / 100].Occpie.colour == Piece.Color.White) {
                    game.gameboard[i / 100][j / 100].Occpie.draw(g, i + 20, j + 20);
                }
                if (game.gameboard[i / 100][j / 100].Occpie.colour == Piece.Color.Black) {
                    game.gameboard[i / 100][j / 100].Occpie.draw(g, i + 20, j);
                }

            }
        }

    }

    public void mousedragged(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    ;

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    ;

    @Override
    public void mouseExited(MouseEvent e) {

    }

    ;

    @Override
    public void mousePressed(MouseEvent e) {
        int k = e.getX() / 100;
        int l = e.getY() / 100;
        if ((this.game.whiteturn && game.getBoard()[k][l].Occpie.colour == Piece.Color.White) || (!this.game.whiteturn && game.getBoard()[k][l].Occpie.colour == Piece.Color.Black)) {
            x = k;
            y = l;

            /*for (Case move : this.game.gameboard[x][y].Occpie.legalmoves(game.gameboard)) {
                Graphics g = getGraphics();
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.BLUE);
                g2.fillRoundRect(move.x * 100, move.y * 100, 95, 95, 0, 0);
                game.tmp.add(move);
            }*/
        }

//        System.out.println(game.gameboard[x][y].Occpie.id + "  " + game.gameboard[x][y].Occpie.colour);
        //    System.out.println("(" + x + "," + y + ")");
        //  System.out.println("(" + game.gameboard[x][y].x + "," + game.gameboard[x][y].y + ")");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x1, y1;
        x1 = e.getX() / 100;
        y1 = e.getY() / 100;
        //  System.out.println("(" + x1 + "," + y1 + ")");
        // System.out.println("(" + game.gameboard[x1][y1].x + "," + game.gameboard[x1][y1].y + ")");
        //  System.out.println("(" + game.gameboard[x][y].x + "-" + game.gameboard[x1][y1].x + ")^2" + "+" + "(" + game.gameboard[x][y].y + "-" + game.gameboard[x1][y1].y + ")^2" + " " + game.gameboard[x][y].distance(game.gameboard[x1][y1]));
        try {
            this.game.movewCheck(game.gameboard[x][y], game.gameboard[x1][y1]);
        } catch (IOException ex) {
            Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (game.BPawnReached) {
            try {
                String index = (String) (JOptionPane.showInputDialog(null, "Choice?"));
                game.gameboard[game.PawnX][game.PawnY].Occpie=game.tr(game.PawnX, game.PawnY, index, "Black");
                Object[] options = {"Queen",
                    "Bishop",
                    "Rook",
                    "Knight"};
                game.BPawnReached=!game.BPawnReached;
            } catch (IOException ex) {
                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (game.WPawnReached) {
            try {
                String index = (String) (JOptionPane.showInputDialog(null, "Choice?"));
                game.gameboard[game.PawnX][game.PawnY].Occpie=game.tr(game.PawnX, game.PawnY, index, "White");
                Object[] options = {"Queen",
                    "Bishop",
                    "Rook",
                    "Knight"};
                game.WPawnReached=!game.WPawnReached;
            } catch (IOException ex) {
                Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Graphics g = getGraphics();
        this.redraw(g, game.gameboard);
        this.Checkpaint(g);
        if (game.isBincheckmate(game.gameboard) || game.isWincheckmate(game.gameboard)) {
            System.out.println("CHECKMATE");
            //addwindow
        }
        //System.out.println(game.gameboard[x][y].Occpie.id + "  " + game.gameboard[x][y].Occpie.colour);

    }

    public void Checkpaint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (game.isBinCheck(game.gameboard)) {
            System.out.println("ISBINCHECK = " + game.isBinCheck(game.gameboard));
            g2.setColor(Color.RED);
            g2.fillRoundRect(game.BKingcurrcase.x * 100, game.BKingcurrcase.y * 100, 95, 95, 0, 0);
            game.gameboard[game.BKingcurrcase.x][game.BKingcurrcase.y].Occpie.draw(g, (game.BKingcurrcase.x * 100) + 20, (game.BKingcurrcase.y * 100));
        }

        if (game.isWinCheck(game.gameboard)) {
            System.out.println("ISWINCHECK = " + game.isWinCheck(game.gameboard));
            g2.setColor(Color.RED);
            g2.fillRoundRect(game.WKingcurrcase.x * 100, game.WKingcurrcase.y * 100, 95, 95, 0, 0);
            game.gameboard[game.WKingcurrcase.x][game.WKingcurrcase.y].Occpie.draw(g, (game.WKingcurrcase.x * 100) + 20, (game.WKingcurrcase.y * 100) + 20);

        } else {
            System.out.println("WKINGCURRCASE =" + game.WKingcurrcase.x + " " + game.WKingcurrcase.y);
            this.voidedraw(g, game.WKingcurrcase.x * 100, game.WKingcurrcase.y * 100);
            game.gameboard[game.WKingcurrcase.x][game.WKingcurrcase.y].Occpie.draw(g, (game.WKingcurrcase.x * 100) + 20, (game.WKingcurrcase.y * 100) + 20);
            this.voidedraw(g, game.BKingcurrcase.x * 100, game.BKingcurrcase.y * 100);
            game.gameboard[game.BKingcurrcase.x][game.BKingcurrcase.y].Occpie.draw(g, (game.BKingcurrcase.x * 100) + 20, (game.BKingcurrcase.y * 100));

        }
    }

    public void voidedraw(Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g;
        java.awt.Color Brown = new java.awt.Color(174, 108, 72);
        java.awt.Color Beige = new java.awt.Color(225, 203, 176);
        if ((x + y) / 100 % 2 == 1) {
            g2.setColor(Brown);
            g2.fillRoundRect(x, y, 95, 95, 0, 0);
            //System.out.println(i + "," + j + " " + "0");
        } else {
            g2.setColor(Beige);
            g2.fillRoundRect(x, y, 95, 95, 0, 0);
            //System.out.println(i + "," + j + " " + "1");
        }
    }

    public void relegalmoves(Graphics g, int x, int y, ArrayList<Case> tmp) {
        for (Case move : tmp) {
            System.out.println("TMP: " + move.x + "  " + move.y);
            Graphics2D g2 = (Graphics2D) g;
            if (move.Occpie.colour == Piece.Color.White) {
                this.voidedraw(g, (move.x * 100), (move.y * 100));
                move.Occpie.draw(g, (move.x * 100) + 20, (move.y * 100) + 20);
            }
            if (move.Occpie.colour == Piece.Color.Black) {
                this.voidedraw(g, (move.x * 100), (move.y * 100));
                move.Occpie.draw(g, (move.x * 100) + 20, (move.y * 100));
            }
            if (move.Occpie.colour == Piece.Color.Nope) {
                move.Occpie.draw(g, (move.x * 100), (move.y * 100));
            }

        }
    }

    public void drawer(Graphics g, int x, int y, int x1, int y1) {
        if (game.gameboard[x][y].Occpie.colour == Piece.Color.White) {
            this.voidedraw(g, (x * 100), (y * 100));
            game.gameboard[x][y].Occpie.draw(g, (x * 100) + 20, (y * 100) + 20);
        }
        if (game.gameboard[x][y].Occpie.colour == Piece.Color.Black) {
            this.voidedraw(g, (x * 100), (y * 100));
            game.gameboard[x][y].Occpie.draw(g, (x * 100) + 20, (y * 100));
        }
        if (game.gameboard[x][y].Occpie.colour == Piece.Color.Nope) {
            game.gameboard[x][y].Occpie.draw(g, (x * 100), (y * 100));
        }
        if (game.gameboard[x1][y1].Occpie.colour == Piece.Color.White) {
            this.voidedraw(g, (x1 * 100), (y1 * 100));
            game.gameboard[x1][y1].Occpie.draw(g, (x1 * 100) + 20, (y1 * 100) + 20);
        }
        if (game.gameboard[x1][y1].Occpie.colour == Piece.Color.Black) {
            this.voidedraw(g, (x1 * 100), (y1 * 100));
            game.gameboard[x1][y1].Occpie.draw(g, (x1 * 100) + 20, (y1 * 100));
        }
        if (game.gameboard[x1][y1].Occpie.colour == Piece.Color.Nope) {
            game.gameboard[x1][y1].Occpie.draw(g, (x1 * 100), (y1 * 100));
        }
    }

    public void redraw(Graphics g, Case[][] Board) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (Board[x][y].Occpie.colour == Piece.Color.White) {
                    this.voidedraw(g, (x * 100), (y * 100));
                    Board[x][y].Occpie.draw(g, (x * 100) + 20, (y * 100) + 20);
                }
                if (Board[x][y].Occpie.colour == Piece.Color.Black) {
                    this.voidedraw(g, (x * 100), (y * 100));
                    Board[x][y].Occpie.draw(g, (x * 100) + 20, (y * 100));
                }
                if (Board[x][y].Occpie.colour == Piece.Color.Nope) {
                    Board[x][y].Occpie.draw(g, (x * 100), (y * 100));
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 399, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 314, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
        new GameFrame();

    }

    // Variables declaration - do not modify                     
    // End of variables declaration                   
}

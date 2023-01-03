
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author RafikMedici
 */
public class Game {

    Case[][] gameboard = new Case[8][8];
    ArrayList<Piece> deadpieces = new ArrayList<Piece>();
    Piece[] blackpieces;
    Piece[] whitepieces;
    Case BKingcurrcase;
    Case WKingcurrcase;
    Boolean whiteturn = true;
    Boolean Wkingmoved = false;
    Boolean Bkingmoved = false;
    Boolean Wrookmoved = false;
    Boolean Brookmoved = false;
    Boolean Wcastling = !(Wkingmoved || Wrookmoved);
    Boolean Bcastling = !(Brookmoved || Bkingmoved);
    Stack states = new Stack();
    ArrayList<Case> tmp = new ArrayList<Case>();
    Boolean WPawnReached = false;
    Boolean BPawnReached = false;
    int PawnX, PawnY;

    public Game() {
        try {
            for (int i = 0; i < 8; i++) {
                for (int j = 2; j < 6; j++) {
                    gameboard[i][j] = new Case(i, j, false, new Piece(Piece.PieceType.voide, Piece.Color.Nope, i, j));
                }
            }
            blackpieces = new Piece[16];
            for (int i = 0; i < 8; i++) {
                try {
                    blackpieces[i] = new Soldier(Piece.Color.Black, i, 6);
                } catch (IOException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            whitepieces = new Piece[16];
            for (int i = 0; i < 8; i++) {
                try {
                    whitepieces[i] = new Soldier(Piece.Color.White, i, 1);
                } catch (IOException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            for (int i = 0; i < 8; i++) {
                gameboard[i][1] = new Case(i, 1, true, whitepieces[i]);
            }
            for (int i = 0; i < 8; i++) {
                gameboard[i][6] = new Case(i, 6, true, blackpieces[i]);
            }
            //rooks
            blackpieces[8] = new Rook(Piece.Color.Black, 7, 7);
            blackpieces[9] = new Rook(Piece.Color.Black, 0, 7);
            gameboard[7][7] = new Case(7, 7, true, blackpieces[8]);
            gameboard[0][7] = new Case(0, 7, true, blackpieces[9]);
            whitepieces[8] = new Rook(Piece.Color.White, 7, 0);
            whitepieces[9] = new Rook(Piece.Color.White, 0, 0);
            gameboard[7][0] = new Case(7, 0, true, whitepieces[8]);
            gameboard[0][0] = new Case(0, 0, true, whitepieces[9]);
            //knights

            blackpieces[10] = new Knight(Piece.Color.Black, 1, 7);
            blackpieces[11] = new Knight(Piece.Color.Black, 6, 7);
            gameboard[1][7] = new Case(1, 7, true, blackpieces[10]);
            gameboard[6][7] = new Case(6, 7, true, blackpieces[11]);
            whitepieces[10] = new Knight(Piece.Color.White, 1, 0);
            whitepieces[11] = new Knight(Piece.Color.White, 6, 0);
            gameboard[1][0] = new Case(1, 0, true, whitepieces[10]);
            gameboard[6][0] = new Case(6, 0, true, whitepieces[11]);
            //bishop
            blackpieces[12] = new bishop(Piece.Color.Black, 2, 7);
            blackpieces[13] = new bishop(Piece.Color.Black, 5, 7);
            gameboard[2][7] = new Case(2, 7, true, blackpieces[12]);
            gameboard[5][7] = new Case(5, 7, true, blackpieces[13]);
            whitepieces[12] = new bishop(Piece.Color.White, 2, 0);
            whitepieces[13] = new bishop(Piece.Color.White, 5, 0);
            gameboard[2][0] = new Case(2, 0, true, whitepieces[12]);
            gameboard[5][0] = new Case(5, 0, true, whitepieces[13]);
            //King/Queen
            blackpieces[14] = new King(Piece.Color.Black, 4, 7);
            blackpieces[15] = new Queen(Piece.Color.Black, 3, 7);
            gameboard[4][7] = new Case(4, 7, true, blackpieces[14]);
            gameboard[3][7] = new Case(3, 7, true, blackpieces[15]);
            whitepieces[14] = new King(Piece.Color.White, 4, 0);
            whitepieces[15] = new Queen(Piece.Color.White, 3, 0);
            gameboard[4][0] = new Case(4, 0, true, whitepieces[14]);
            gameboard[3][0] = new Case(3, 0, true, whitepieces[15]);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        WKingcurrcase = gameboard[4][0];
        BKingcurrcase = gameboard[4][7];
        System.out.println(this.FGametoStr(gameboard));
    }

    public String FGametoStr(Case[][] board) {
        String state = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                state += Integer.toString(i) + Integer.toString(j) + board[i][j].Occpie.colour + "@" + board[i][j].Occpie.id + ";";
            }
        }
        return state;

    }

    public Case[][] FstrtoGame(String state) throws IOException {
        Case[][] Board = new Case[8][8];
        String[] split = state.split(";");
        for (int n = 0; n < split.length; n++) {
            int i = Integer.parseInt(String.valueOf(split[n].charAt(0)));
            int j = Integer.parseInt(String.valueOf(split[n].charAt(1)));
            int k = split[n].indexOf("@");
            String colour = split[n].substring(2, k);
            String id = split[n].substring(k + 1);
            if (id.equals("voide")) {
                Board[i][j] = new Case(i, j, false, tr(i, j, id, colour));
            } else {
                Board[i][j] = new Case(i, j, true, tr(i, j, id, colour));
            }
        }
        return Board;
    }

    public int[] Fg(Case[][] Board) {
        int[] tab = new int[4];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Board[i][j].Occpie.id == Piece.PieceType.King && Board[i][j].Occpie.colour == Piece.Color.White) {
                    tab[0] = i;
                    tab[1] = j;
                }
                if (Board[i][j].Occpie.id == Piece.PieceType.King && Board[i][j].Occpie.colour == Piece.Color.Black) {
                    tab[2] = i;
                    tab[3] = j;
                }
            }
        }
        return tab;
    }

    public int[] FstrKing(String state) {
        int[] tab = new int[4];
        String[] split = state.split(";");
        for (int n = 0; n < split.length; n++) {
            int i = Integer.parseInt(String.valueOf(split[n].charAt(0)));
            int j = Integer.parseInt(String.valueOf(split[n].charAt(1)));
            int k = split[n].indexOf("@");
            String colour = split[n].substring(2, k);
            String id = split[n].substring(k + 1);
            if (id.equals("King") && colour == "White") {
                tab[0] = i;
                tab[1] = j;
            }
            if (id.equals("King") && colour == "Black") {
                tab[2] = i;
                tab[3] = j;
            }
        }
        return tab;
    }

    public void move(Case[][] Board, Case case1, Case case2) throws IOException {
        boolean isvalid = case1.Occpie.ismovevalid(Board, case2);
        System.out.println("ismovevalid100= " + isvalid);
        if (isvalid) {
            System.out.println("ismovevalid1= " + isvalid);
            if (case2.isOccupied && case1.Occpie.colour != case2.Occpie.colour) {
                this.deadpieces.add(case2.Occpie);
                case2.Occpie = case1.Occpie;
                case2.Occpie.x = case2.x;
                case2.Occpie.y = case2.y;
            }
            if (!case2.isOccupied) {
                case2.Occpie = case1.Occpie;
                case2.isOccupied = true;
                case2.Occpie.x = case2.x;
                case2.Occpie.y = case2.y;
            }
            if (case1.Occpie.id == Piece.PieceType.King) {
                if (case1.Occpie.colour == Piece.Color.White) {

                    this.WKingcurrcase = Board[case2.x][case2.y];
                    //System.out.println(case2.x+"  "+case2.y+"thiswcurrcase");
                    this.Wkingmoved = true;
                }
                if (case1.Occpie.colour == Piece.Color.Black) {

                    this.BKingcurrcase = Board[case2.x][case2.y];
                    this.Bkingmoved = true;

                }
            }
            if (case1.Occpie.id == Piece.PieceType.Rook) {
                if (case1.Occpie.colour == Piece.Color.Black) {
                    Brookmoved = true;
                }
                if (case1.Occpie.colour == Piece.Color.White) {
                    Wrookmoved = true;

                }
            }
            if (case1.Occpie.id == Piece.PieceType.Soldier) {
                if (case1.Occpie.colour == Piece.Color.White && case2.y == 7) {
                    this.WPawnReached = true;
                    PawnX = case2.x;
                    PawnY = case2.y;

                }
                if (case1.Occpie.colour == Piece.Color.Black && case2.y == 0) {
                    this.BPawnReached = true;
                    PawnX = case2.x;
                    PawnY = case2.y;
                }
            }
            case1.Occpie = new voide(case1.x, case1.y);
            case1.isOccupied = false;
            states.add(this.FGametoStr(Board));
            System.out.println(states.lastElement());
            whiteturn = !whiteturn;

        }
        if (case1.Occpie.id == Piece.PieceType.King && case1.Occpie.isCastling(Board,case2)) {
            if (case1.Occpie.colour == Piece.Color.Black && Bcastling) {
                case2.Occpie = case1.Occpie;
                case2.isOccupied = true;
                case2.Occpie.x = case2.x;
                case2.Occpie.y = case2.y;
                case1.Occpie = new voide(case1.x, case1.y);
                case1.isOccupied = false;
                if(case2.x>case1.x){
                Board[case1.x+1][case1.y].Occpie=new Rook(Piece.Color.Black,case1.x+1,case1.y);
                Board[case1.x+1][case1.y].isOccupied=true;
                Board[case1.x+3][case1.y].Occpie=new voide(case1.x+3,case1.y);
                Board[case1.x+3][case1.y].isOccupied = false;
            }
                if(case2.x<case1.x){
                Board[case1.x-1][case1.y].Occpie=new Rook(Piece.Color.Black,case1.x-1,case1.y);
                Board[case1.x-1][case1.y].isOccupied=true;
                Board[case1.x-4][case1.y].Occpie=new voide(case1.x-4,case1.y);
                Board[case1.x-4][case1.y].isOccupied = false;
            }
                states.add(this.FGametoStr(Board));
                System.out.println(states.lastElement());
                whiteturn = !whiteturn;

            }
            if (case1.Occpie.colour == Piece.Color.White && Wcastling) {
                case2.Occpie = case1.Occpie;
                case2.isOccupied = true;
                case2.Occpie.x = case2.x;
                case2.Occpie.y = case2.y;
                case1.Occpie = new voide(case1.x, case1.y);
                case1.isOccupied = false;
                if(case2.x>case1.x){
                Board[case1.x+1][case1.y].Occpie=new Rook(Piece.Color.White,case1.x+1,case1.y);
                Board[case1.x+1][case1.y].isOccupied=true;
                Board[case1.x+3][case1.y].Occpie=new voide(case1.x+3,case1.y);
                Board[case1.x+3][case1.y].isOccupied = false;
            }
                if(case2.x<case1.x){
                Board[case1.x-1][case1.y].Occpie=new Rook(Piece.Color.White,case1.x-1,case1.y);
                Board[case1.x-1][case1.y].isOccupied=true;
                Board[case1.x-4][case1.y].Occpie=new voide(case1.x-4,case1.y);
                Board[case1.x-4][case1.y].isOccupied = false;
            }
                states.add(this.FGametoStr(Board));
                System.out.println(states.lastElement());
                whiteturn = !whiteturn;
            }
        } else {
            System.out.println("ismovevalid2= " + isvalid);
        }

    }

    public void Undo(Case[][] Board) throws IOException {
        states.pop();
        Board = this.FstrtoGame((String) states.lastElement());
        whiteturn = !whiteturn;
        this.WKingcurrcase = Board[Fg(Board)[0]][Fg(Board)[1]];
        this.BKingcurrcase = Board[Fg(Board)[2]][Fg(Board)[3]];

    }

    public void movewCheck(Case case1, Case case2) throws IOException {
        if (case1.Occpie.colour == Piece.Color.White) {
            this.move(gameboard, case1, case2);
            boolean isWincheckintmp = isWinCheck(gameboard);
            System.out.println("isWiteincheckintmp= " + isWincheckintmp);
            if (isWincheckintmp) {
                System.out.println(gameboard[case1.x][case1.y].Occpie.id);
                this.Undo(gameboard);
            }

        }
        if (case1.Occpie.colour == Piece.Color.Black) {
            this.move(gameboard, case1, case2);
            boolean isBincheckintmp = isBinCheck(gameboard);
            System.out.println("isBlackincheckintmp= " + isBincheckintmp);
            if (isBincheckintmp) {
                System.out.println(gameboard[case1.x][case1.y].Occpie.id);
                this.Undo(gameboard);
            }

        }

    }

    public boolean isWinCheck(Case[][] board) {
        boolean isWincheck = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameboard[i][j].isOccupied) {
                    if (gameboard[i][j].Occpie.colour == Piece.Color.Black) {
                        boolean isvalid = gameboard[i][j].Occpie.ismovevalid(board, this.WKingcurrcase);
                        if (isvalid) {
                            System.out.println("checkingpiece= " + gameboard[i][j].Occpie.x + "    " + gameboard[i][j].Occpie.y + "    " + gameboard[i][j].Occpie.id);
                            isWincheck = true;
                        }
                    }
                }
            }
        }
        return isWincheck;
    }

    public boolean isBinCheck(Case[][] board) {
        boolean isBincheck = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameboard[i][j].isOccupied) {
                    if (gameboard[i][j].Occpie.colour == Piece.Color.White) {
                        boolean isvalid = gameboard[i][j].Occpie.ismovevalid(board, this.BKingcurrcase);
                        if (isvalid) {
                            System.out.println("checkingpiece= " + gameboard[i][j].Occpie.x + "    " + gameboard[i][j].Occpie.y + "    " + gameboard[i][j].Occpie.id);
                            isBincheck = true;
                        }
                    }
                }
            }

        }
        return isBincheck;
    }

    public boolean isBincheckmate(Case[][] board) {
        return this.BKingcurrcase.Occpie.canmove(board) && this.isBinCheck(board);
    }

    public boolean isWincheckmate(Case[][] board) {
        return this.WKingcurrcase.Occpie.canmove(board) && this.isWinCheck(board);
    }

    public Case[][] getBoard() {
        return gameboard;
    }

    public void setGameboard(Case[][] gameboard) {
        this.gameboard = gameboard;
    }

    public ArrayList<Piece> getDeadpieces() {
        return deadpieces;
    }

    public void setDeadpieces(ArrayList<Piece> deadpieces) {
        this.deadpieces = deadpieces;
    }

    /*public Boolean getIsBlackincheck() {
        return isBlackincheck;
    }

    public void setIsBlackincheck(Boolean isBlackincheck) {
        this.isBlackincheck = isBlackincheck;
    }

    public Boolean getIsWhiteincheck() {
        return isWhiteincheck;
    }

    public void setIsWhiteincheck(Boolean isWhiteincheck) {
        this.isWhiteincheck = isWhiteincheck;
    }
     */
    public Piece[] getBlackpieces() {
        return blackpieces;
    }

    public void setBlackpieces(Piece[] blackpieces) {
        this.blackpieces = blackpieces;
    }

    public Piece[] getWhitepieces() {
        return whitepieces;
    }

    public void setWhitepieces(Piece[] whitepieces) {
        this.whitepieces = whitepieces;
    }

    public Case getBKingcurrcase() {
        return BKingcurrcase;
    }

    public void setBKingcurrcase(Case BKingcurrcase) {
        this.BKingcurrcase = BKingcurrcase;
    }

    public Case getWKingcurrcase() {
        return WKingcurrcase;
    }

    public void setWKingcurrcase(Case WKingcurrcase) {
        this.WKingcurrcase = WKingcurrcase;
    }

    public Piece tr(int i, int j, String id, String color) throws IOException {
        if (id.equals("Rook")) {
            return new Rook(fstrC(color), i, j);
        }
        if (id.equals("Queen")) {
            return new Queen(fstrC(color), i, j);
        }
        if (id.equals("Bishop")) {
            return new bishop(fstrC(color), i, j);
        }
        if (id.equals("King")) {
            return new King(fstrC(color), i, j);
        }
        if (id.equals("Knight")) {
            return new Knight(fstrC(color), i, j);
        }
        if (id.equals("Soldier")) {
            return new Soldier(fstrC(color), i, j);
        } else {
            return new voide(i, j);
        }

    }

    public Piece.Color fstrC(String str) {
        if (str.equals("White")) {
            return Piece.Color.White;
        }
        if (str.equals("Black")) {
            return Piece.Color.Black;
        } else {
            return Piece.Color.Nope;
        }
    }

    public Piece.PieceType fstrPT(String str) {
        if (str.equals("King")) {
            return Piece.PieceType.King;
        }
        if (str.equals("Queen")) {
            return Piece.PieceType.Queen;
        }
        if (str.equals("Knight")) {
            return Piece.PieceType.Knight;
        }
        if (str.equals("Bishop")) {
            return Piece.PieceType.Bishop;
        }
        if (str.equals("Rook")) {
            return Piece.PieceType.Rook;
        }
        if (str.equals("Soldier")) {
            return Piece.PieceType.Soldier;
        } else {
            return Piece.PieceType.voide;
        }

    }
}

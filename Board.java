import java.util.ArrayList;

public class Board {

    // contains
    // 1. 10 x 10 board
    // 2. bombs - one step will automatically ends the game and lose
    // 3. treasure - one step will automatically ends the game and win
    private int[][] boardNum;
    private String[][] boardString;

    public Board() {
        this.boardNum = new int[10][10];
        this.boardString = new String[10][10];

    }


    public void displayEmptyBoard() {
        System.out.println("-------------------------------");
        for (int a = 0; a < 10; a++) {
            for (int b = 0; b < 10; b++) {
                boardNum[a][b] = (a * 10) + (b + 1);
                boardString[a][b] = "*";
                //System.out.print("| " + board[a][b] + "|");

                //conditional statement for displaying numbers
               /* if (a == 0 && boardNum[a][b] != 10)
                    System.out.print("| " + boardString[a][b] + "|");
                else if (boardNum[a][b] == 10)
                    System.out.print("|" + boardString[a][b] + "|");
                else
                    System.out.print("|" + boardString[a][b] + "|");
                */
                // System.out.print("-");
                if (b != 9)
                    System.out.print("| " + boardString[a][b] + "");
                else System.out.print("| " + boardString[a][b] + "|");
            }

            System.out.println("");
            if (a != 9)
                System.out.println("-------------------------------");
        }
        System.out.println("-------------------------------");
        //System.out.print(board.length);
    }

    public void PlantedBoard(ArrayList<Integer> bomba)
    {
        //System.out.println(bomba);
       /// System.out.println(forever);
       // System.out.println("-------------------------------");
        for (int a = 0; a < 10; a++) {
            for (int b = 0; b < 10; b++) {
                boardNum[a][b] = (a * 10) + (b + 1);
                // tanim bomba
                int w = 0;
                while(w<bomba.size())
                {
                    if(bomba.get(w) == boardNum[a][b])
                    {
                        // place that bomb
                        //System.out.print("Numbers are equal");
                        boardString[a][b] ="b";
                    }
                    w++;
                }
               /* if (b != 9)
                    System.out.print("| " + boardString[a][b] + "");
                else System.out.print("| " + boardString[a][b] + "|");*/
            }// end inner for
           // System.out.println("");
        }// end outer for

       // uncommenting #91 this will reveal secrets :O
        // if(game_over == 1)
       //  this.displayUpdateBoard(this.getBoardString());
        this.displayEmptyBoard();
    }// end method

    public void revealBoard(int pos, ArrayList<Integer> bombax)
    {
        //System.out.println(bomba);
        /// System.out.println(forever);
        // System.out.println("-------------------------------");
        for (int a = 0; a < 10; a++) {
            for (int b = 0; b < 10; b++) {
                boardNum[a][b] = (a * 10) + (b + 1);
                // tanim bomba
                int w = 0;
                while(w<bombax.size())
                {
                    if(bombax.get(w) == boardNum[a][b])
                    {
                        // place that bomb
                        //System.out.print("Numbers are equal");
                        boardString[a][b] ="X";
                    }
                    if(pos == boardNum[a][b])
                    {
                        boardString[a][b] = "B";
                    }

                    w++;
                }

               /* if (b != 9)
                    System.out.print("| " + boardString[a][b] + "");
                else System.out.print("| " + boardString[a][b] + "|");*/
            }// end inner for
            // System.out.println("");
        }// end outer for

        // uncommenting #91 this will reveal secrets :O
        // if(game_over == 1)
         this.displayUpdateBoard(this.getBoardString());
        //this.displayEmptyBoard();
    }// end method

    public void revealNothingPosition(int pos, ArrayList<Integer> bomba)
    {
        // displays an O meaning that position did not contain anything
        for (int a = 0; a < 10; a++) {
            for (int b = 0; b < 10; b++) {
                boardNum[a][b] = (a * 10) + (b + 1);
                // check if pos is equal to boardNum
                if(boardNum[a][b] == pos)
                    boardString[a][b] = "O";
            }
        }
        this.displayUpdateBoard(this.getBoardString());
    }

    public void displayUpdateBoard(String[][] baby) {
        System.out.println("-------------------------------");
        for (int a = 0; a < 10; a++) {
            for (int b = 0; b < 10; b++) {
                boardNum[a][b] = (a * 10) + (b + 1);
                if (b != 9)
                   System.out.print("| " + baby[a][b] + "");
                else System.out.print("| " + baby[a][b] + "|");
            }
            System.out.println("");
        }
        System.out.println("-------------------------------");
    }

    public int[][] getBoardNum() {
        return boardNum;
    }

    public void setBoardNum(int[][] boardNum) {
        this.boardNum = boardNum;
    }


    public String[][] getBoardString() {
        return boardString;
    }

    public void setBoardString(String[][] boardString) {
        this.boardString = boardString;
    }
}

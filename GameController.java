import java.util.ArrayList;
import java.util.Scanner;

public class GameController {

    private Board board;
    private Direction direction;
    private Bomb bomb;
    private String key;
    private Scanner xd;
    private String []  legit_directions = {"NORTH", "EAST", "WEST", "SOUTH"};
    private  String movement;
    private  int number;
    private String some_random;
    private ArrayList<String> bananaList ;

    public GameController()
    {
        bananaList = new ArrayList<>();
        board = new Board();
        direction = new Direction();
        bomb = new Bomb();
        key = "";
        xd = new Scanner(System.in);
        this.number = 0;

    }

    public void StartGame() {
        //board.displayEmptyBoard();
        // display objective and the user must agree
        this.disclaimer();
        String deal = xd.nextLine();
        boolean real = false;

        while (!deal.equalsIgnoreCase("I AGREE TO THIS CONDITION.")) {
            deal = xd.nextLine();
        }

        System.out.println("LOADING RESOURCES");
        this.loadingTextxd();
        System.out.println("GAME STARTS HERE");
        // from here are the commands for the game proper
        /*
        * START
    *       <Direction> <number from 1-100>
    *       [random]<Direction> <number from 1-100>
    *       <Direction> [random] <number from 1-100>
    *       [random] <Direction> [random] <number from 1-100>
        * */
        // this.combineStuff();
        int turns = 1;
        int legit = 0;
        while (turns <= 5) {
            // System.out.println(key);
            try {
                System.out.println("Turn: " + turns);
                key = xd.nextLine();

                //System.out.println(checkFormat(bananaList));
                while (!checkFormat(bananaList) || bananaList.isEmpty()) {

                    System.out.println("Try again!");
                    if (turns > 1)
                        turns--;
                    else
                        turns = 0;

                    System.out.println("Turn: " + turns);
                    key = xd.nextLine();
                    //   this.passParameterSplit(bananaList);
                }

            }catch (IndexOutOfBoundsException egg)
            {
                System.out.println("Error and modularized already!");
            }
            String[] banana = key.split(" ");
            bananaList = convertToArrayList(banana);
            this.passParameterSplit(bananaList);
            // pass paramater for checking if the given direction has a bomb or nothing

            legit = this.whatItContain(this.getMovement(), this.getNumber());
            System.out.println("Direction: " + this.getMovement());
            System.out.println("Number Given:  " + this.getNumber());
            System.out.println("Equivalent: " + legit);
            boolean x = this.bombChecker(legit);

            if(x==true) break;
            else {
                turns++;
                continue;
            }
            //System.out.println("Stored List: " + usedPositonList);
            //   turns++;
            // }
            //turns++;
        }
    }
    public void passParameterSplit(ArrayList<String> sample) {
        String getD = sample.get(0);
        String keyyyy = sample.get(1);

        if(keyyyy.equalsIgnoreCase("DIRECTION"))
        {
            if(getD.equalsIgnoreCase("NORTH") || getD.equalsIgnoreCase("EAST") ||
                    getD.equalsIgnoreCase("WEST") || getD.equalsIgnoreCase("SOUTH") ||
                    getD.equalsIgnoreCase("RANDOM"))
                {
                    if(getD.equalsIgnoreCase("RANDOM"))
                        this.setMovement(legit_directions[(int) (Math.random() * legit_directions.length)]);
                        else
                        {

                                //generate a fixed direction
                                    this.setMovement(getD);
                                // System.out.println("Fixed: " + this.getMovement());
                                // passing it immediately to the function
                                //sample.remove(1);

                    }// end else
                }// end larger if
        }// end big if
        String wahhh = sample.get(2);
        if(wahhh.equalsIgnoreCase("RANDOM"))
        {
            this.setNumber((int)(Math.random()*100)+1);
            // break;
        }

        else{
            int thisNumber = Integer.parseInt(wahhh);
            if(thisNumber > 0 && thisNumber < 101){
                this.setNumber(thisNumber);
            }
            else
                this.setNumber((thisNumber%100)+1);


        }
    }

    public boolean checkFormat (ArrayList<String> s)
    {
        //System.out.println(s);
        int score1, score2, score3 = 0;
        // check first word for directions
        String getD = s.get(0);
        if(getD.equalsIgnoreCase("NORTH") || getD.equalsIgnoreCase("EAST") ||
                getD.equalsIgnoreCase("WEST") || getD.equalsIgnoreCase("SOUTH") ||
                getD.equalsIgnoreCase("RANDOM"))
            {
                score1 = 100;
              //  System.out.println("Score Direction: " + score1);
            }
            else {
            score1 = -111;
          //  System.out.println("Score invalid Direction: " + score1);
            }

        // check second word
        String keyyyy = s.get(1);
         if(keyyyy.equalsIgnoreCase("DIRECTION"))
        {
            score2 = 200;
            //System.out.println("Score KEYWORD DIRECTION: " + score2);
        }
        else
        {
            score2 = -222;
           // System.out.println("Score invalid keyword Direction: " + score2);
        }
        // check values from 1-100: third word

        if(s.get(2).equalsIgnoreCase("RANDOM"))
       {
                score3= 300;
                   // System.out.println("Score Finding RANDOM: " + score3);

               // break;
            }

       else{
            int thisNumber = Integer.parseInt(s.get(2));
            if(thisNumber > 0 && thisNumber < 101){
            score3 = 301;
           // System.out.println("Score < 100: " + score3);
            }
            else {
                score3 = -333;
                //System.out.println("Score > 100: " + score3);
            }

         }

        // check for RANDOM DIRECTION
       // String random_direction = String.join(" ", s.get(0), s.get(1));

         if(score1 > 0 && score2 > 0 && score3 > 0)
            return true;
         else
             return false;
    }


    public boolean bombChecker(int position)
    {
        // this will check if a given position has a bomb, a treasure, and/or nothing.

        // 1. Bombs
        //System.out.println(this.bomb.getBombList());
        int stop = 0;
        for(int x=0;x<this.bomb.getBombList().size();x++) {
            if (this.bomb.getBombList().get(x).equals(position)) {
                //game over
                System.out.println("GAME OVER. A Bomb was exposed.");
                // reveal board :o
                System.out.println("REVEALING BOARD");
                board.revealBoard(position,bomb.getBombList());
                stop=1;
                return true;
            }
        }
        if(stop!=1)
            board.revealNothingPosition(position, bomb.getBombList());
        return false;
    }



    // this.getCombi().removeAll(this.getCombi());

    // System.out.println(usedPositonList);

    public ArrayList<String> convertToArrayList(String[] x)
    {
        ArrayList<String> list = new ArrayList<>();

        for(String a: x)
        {
            list.add(a);
        }
        return list;
    }




    public int whatItContain(String movement, int location)
    {
        // it will return a final number based on its directions
        int realLocation=0;

        switch (movement)
        {
            case "NORTH":
                realLocation = direction.getNorthValue(location);
                break;
            case "EAST":
                realLocation = direction.getEastValue(location);
                break;
            case "WEST":
                realLocation = direction.getWestValue(location);
                break;
            case "SOUTH":
                realLocation = direction.getSouthValue(location);
                break;
        }

        return realLocation;

    }

    public void disclaimer()
    {
        System.out.println("Welcome to MindSweeper!");
        System.out.println("The user of this simple game must agree that");
        System.out.println("anything shocking is always his/her fault.");
        System.out.println("Type this sentence to AGREE!");
        System.out.println("I AGREE TO THIS CONDITION.");
    }

    public void loadingTextxd()
    {
        System.out.println("Loading Board . . . ");
        this.board.displayEmptyBoard();
        System.out.println("Already Done!");
        System.out.println("Planting Bombs and Treasures");
        this.bomb.produceBomb();
        this.board.PlantedBoard(bomb.getBombList());
        System.out.println("Bombs and Treasures Planted: Done!");
    }


    public String getSome_random() {
        return some_random;
    }

    public void setSome_random(String some_random) {
        this.some_random = some_random;
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
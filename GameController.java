import java.util.ArrayList;
import java.util.Scanner;

public class GameController {

    private Board board;
    private Direction direction;
    private Bomb bomb;
    private String key;
    private Scanner xd;
    private String []  legit_directions = {"NORTH", "EAST", "WEST", "SOUTH"};
    private ArrayList<Integer> combi;
    private ArrayList<Integer> usedPositonList;
    private  String movement;
    private  int number;
    private String some_random;


    public GameController()
    {
        board = new Board();
        direction = new Direction();
        usedPositonList = new ArrayList<>();
        combi = new ArrayList<>();
        bomb = new Bomb();
        key = "";
        xd = new Scanner(System.in);
        this.number = 0;

    }

    public void StartGame()
    {
        //board.displayEmptyBoard();
        // display objective and the user must agree
        this.disclaimer();
        String deal = xd.nextLine();
        boolean real = false;

        while(!deal.equalsIgnoreCase("I AGREE TO THIS CONDITION."))
        {
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
        int legit=0;
        while(turns <= 5) {
           // System.out.println(key);
                System.out.println("Turn: " + turns);
                key = xd.nextLine();
                try {
                    for (int a = 0; a < legit_directions.length; a++) {
                        for (int c = 1; c <= 100; c++) {
                            while (!(key.contains("DIRECTION")) && !(key.contains(legit_directions[a]) ||
                                    !(key.contains("RANDOM")) && !(this.getNumber() == c))) {
                                key = xd.nextLine();
                            }
                        }
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                String[] banana = key.split(" ");
                ArrayList<String> bananaList = convertToArrayList(banana);
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

        }

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

    public void passParameterSplit(ArrayList<String> sample) {

        for (int x=0; x<sample.size(); x++) {
            // direction reading
                if(sample.get(0).equalsIgnoreCase("DIRECTION"))
                {
                    if(sample.get(1).equalsIgnoreCase("RANDOM")) {
                       // System.out.println("Casting a random direction!");
                        //generate a random direction
                        this.setMovement(legit_directions[(int) (Math.random() * legit_directions.length)]);
                        // System.out.println("Random: " + this.getMovement());
                        // passing it immediately to the function
                            sample.remove(1);
                    }// end if

                    else
                    {
                        for(int a=0; a<legit_directions.length;a++)
                        {
                            if(sample.get(1).contentEquals(legit_directions[a]))
                            {
                                //generate a fixed direction
                                this.setMovement(legit_directions[a]);
                               // System.out.println("Fixed: " + this.getMovement());
                                // passing it immediately to the function
                                sample.remove(1);
                            }// end if
                        }// end for
                    }// end else
                    sample.remove(0);
                }// end if detects DIRECTION

            // Number Reading
             //  System.out.println(sample.size());
            this.setNumber(Integer.parseInt(sample.get(0)));
            sample.remove(0);
            }// end for sample
            //

       // System.out.println(sample);
        //}// end for
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

    public ArrayList<Integer> getUsedPositonList() {
        return usedPositonList;
    }

    public void setUsedPositonList(ArrayList<Integer> usedPositonList) {
        this.usedPositonList = usedPositonList;
    }

    public ArrayList<Integer> getCombi() {
        return combi;
    }

    public void setCombi(ArrayList<Integer> combi) {
        this.combi = combi;
    }

}




import java.util.ArrayList;

public class Treasure {

    private ArrayList<Integer> treasureList;
    private boolean isThere = false;

    public Treasure()
    {
        this.treasureList = new ArrayList<>();
    }

    public void produceTreasure()
    {
        for(int a=0;a<5;a++)
        {
            treasureList.add((int)(Math.random()*100)+1);
        }
    }

    public int placeTreasure(int[][] boarder, String[][] boarderS)
    {
        for(int c=0; c< boarder.length; c++)
        {
            for(int b=0; b<boarder.length; b++)
            {
                boarder[c][b] = (c * 10) + (b + 1);
                // place bombs randomly
                int x = 0;

                while(x<treasureList.size())
                {
                    if(treasureList.get(x) == boarder[c][b])
                    {
                        // place that bomb
                        //System.out.print("Numbers are equal");
                        boarderS[c][b] ="t";
                    }
                    x++;
                }
                if (b!=9)
                    System.out.print("| " + boarderS[c][b] + "");
                else System.out.print("| " +boarderS[c][b] + "|");
            }
        }
        return 1;
    }


    public ArrayList<Integer> getTreasureList() {
        return treasureList;
    }

    public void setTreasureList(ArrayList<Integer> treasureList) {
        this.treasureList = treasureList;
    }

    public boolean isThere() {
        return isThere;
    }

    public void setThere(boolean there) {
        isThere = there;
    }
}

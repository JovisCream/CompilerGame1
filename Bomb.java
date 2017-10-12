import java.util.ArrayList;

public class Bomb {


    private ArrayList<Integer> bombList;
    private boolean isThere = false;
    public Bomb()
    {
        this.bombList = new ArrayList<>();
    }

    public void produceBomb()
    {
        for(int a=0;a<30;a++)
        {
            bombList.add((int)(Math.random()*100)+1);
        }
    }

    public int placeBomb(int[][] boarder, String[][] boarderS)
    {
        for(int a=0; a< boarder.length; a++)
        {
            for(int b=0; b<boarder.length; b++)
            {
                boarder[a][b] = (a * 10) + (b + 1);
                // place bombs randomly
                int x = 0;
                while(x<bombList.size())
                {
                    if(bombList.get(x) == boarder[a][b])
                    {
                        // place that bomb
                        //System.out.print("Numbers are equal");
                        boarderS[a][b] ="b";
                    }
                    x++;
                }
                if (b!=9)
                    System.out.print("| " + boarderS[a][b] + "");
                else System.out.print("| " +boarderS[a][b] + "|");
            }
        }
        return 1;
    }


    public ArrayList<Integer> getBombList() {
        return bombList;
    }

    public void setBombList(ArrayList<Integer> bombList) {
        this.bombList = bombList;
    }

    public boolean isThere() {
        return isThere;
    }

    public void setThere(boolean there) {
        isThere = there;
    }

}

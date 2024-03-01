package ca.mcmaster.se2aa4.island.team115;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void incrementX(){
        x++;
    }

    public void incrementY(){
        y++;
    }

    public void decrementX(){
        x--;
    }

    public void decrementY(){
        y--;
    }

}
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

    public void incrementX(int x){
        x++;
    }

    public void incrementY(int y){
        y++;
    }

    public void decrementX(int x){
        x--;
    }

    public void decrementY(int y){
        y--;
    }

}
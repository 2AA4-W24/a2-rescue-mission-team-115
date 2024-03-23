package ca.mcmaster.se2aa4.island.team115;

public class Coordinates {
    private Integer X;
    private Integer Y;
    private Direction direction;

    public Coordinates(Integer X, Integer Y){
        this.X = X;
        this.Y = Y;
    }

    public void setDirection (Direction direction){
        this.direction = direction;
    }
    public int getX(){
        return X;
    }

    public int getY(){
        return Y;
    }

    public void flyForward(){
        switch(direction){
            case Direction.N:
                Y++;
                break;
            case Direction.S:
                Y--;
                break;
            case Direction.E:
                X++;
                break;
            case Direction.W:
                X--;
                break;

        }
    }

    public void turnLeft(){
        switch(direction){
            case Direction.N:
                Y++;
                X--;
                break;
            case Direction.S:
                Y--;
                X++;
                break;
            case Direction.E:
                Y++;
                X++;
                break;
            case Direction.W:
                Y--;
                X--;
                break;
        }
        this.direction = direction.leftDir();

    }

    public void turnRight(){
        switch(direction){
            case Direction.N:
                Y++;
                X++;
                break;
            case Direction.S:
                Y--;
                X--;
                break;
            case Direction.E:
                Y--;
                X++;    
                break;
            case Direction.W:
                Y++;
                X--;
                break;
        }
        this.direction = direction.rightDir();
    }
}
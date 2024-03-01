package ca.mcmaster.se2aa4.island.team115;
public class Navigator {

    private Direction direction;
    private Coordinates coord = new Coordinates(0,0);

    public Navigator(){
        //empty constructor
    }

    public Direction stringToEnum(String dir){
        switch(dir){
            case "N":
                direction = Direction.N;
                break;
            case "S":
                direction = Direction.S;
                break;
            case "E":
                direction = Direction.E;
                break;
            case "W":
                direction = Direction.W;
                break;
        }
        return direction;
    }

    public void flyForward(){
        switch(direction){
            case Direction.N:
                coord.incrementY();
                break;
            case Direction.S:
                coord.decrementY();
                break;
            case Direction.E:
                coord.incrementX();
                break;
            case Direction.W:
                coord.decrementX();
                break;

        }
    }

    public void turnLeft(){
        this.direction = direction.leftDir();
    }

    public void turnRight(){
        this.direction = direction.leftDir();
    }

}

package ca.mcmaster.se2aa4.island.team115;

import org.json.JSONObject;

public class IslandFinder {
    private boolean findingComplete = false;
    private boolean turnRightOnUTurn;
    private IslandFinderStates state;
    private Direction currentDirection;
    private Coordinates coordinates;
    private Drone drone;
    private Info info;
    private Action action = new Action();

    public IslandFinder(){
        state = new Fly();
    }

    public boolean shouldTurnRightOnUTurn(){
        if(turnRightOnUTurn){
            return true;
        }
        return false;
    }

    public boolean isComplete(){
        return findingComplete;
    }

    public void setDrone(Drone drone, Info info, Coordinates coordinates) {
        this.drone = drone;
        this.info = info;
        this.coordinates = coordinates;
    }

    public void setState(IslandFinderStates state){
        this.state = state;
    }

    public JSONObject locateIsland(Direction currentDirection){
        action.reset();
        this.currentDirection = currentDirection;
        return state.handle(this);
    }

    private interface IslandFinderStates {
        public JSONObject handle(IslandFinder finder);
    }

    private class Fly implements IslandFinderStates{
        @Override
        public JSONObject handle(IslandFinder finder){
            action.echo(currentDirection);
            finder.setState(new EchoForward());
            return action.getDecision();
        }
    }
    private class EchoForward implements IslandFinderStates{
        @Override
        public JSONObject handle(IslandFinder finder){
            JSONObject extras = info.getExtras();
            String finding = extras.getString("found");
            if(finding.equals("GROUND")){
                Integer range = extras.getInt("range");
                action.fly();
                coordinates.flyForward();
                drone.updateCoordinates(coordinates);
                finder.setState(new FlyToIsland(range));
            }else{
                action.echo(currentDirection.rightDir());
                finder.setState(new EchoRight());
            }
            return action.getDecision();
        }
    }
    private class FlyToIsland implements IslandFinderStates{
        private Integer range;
        private Integer flyCount;
        private FlyToIsland(Integer range){
            this.range = range;
            flyCount = 0;
        }
        @Override
        public JSONObject handle(IslandFinder finder){

            if(flyCount<range-1){
                action.fly();
                coordinates.flyForward();
                drone.updateCoordinates(coordinates);
                flyCount++;
            }else{
                action.fly();
                findingComplete = true;
            }
            return action.getDecision();
        }
    }

    private class EchoRight implements IslandFinderStates{
        @Override
        public JSONObject handle(IslandFinder finder){
            JSONObject extras = info.getExtras();
            String finding = extras.getString("found");
            if(finding.equals("GROUND")){
                action.heading(currentDirection.rightDir());
                coordinates.turnRight();
                drone.updateDirection(currentDirection.rightDir());
                drone.updateCoordinates(coordinates);
                finder.setState(new TurnRight());
                turnRightOnUTurn = false;
            }else{
                action.echo(currentDirection.leftDir());
                finder.setState(new EchoLeft());

            }
            return action.getDecision();
        }
    }

    private class EchoLeft implements IslandFinderStates{
        @Override
        public JSONObject handle(IslandFinder finder){
            JSONObject extras = info.getExtras();
            String finding = extras.getString("found");
            if(finding.equals("GROUND")){
                action.heading(currentDirection.leftDir());
                coordinates.turnLeft();
                drone.updateDirection(currentDirection.leftDir());
                drone.updateCoordinates(coordinates);
                finder.setState(new TurnLeft());
                turnRightOnUTurn = true;
            }else{
                action.fly();
                coordinates.flyForward();
                drone.updateCoordinates(coordinates);
                finder.setState(new Fly());

            }
            return action.getDecision();
        }
    }

    private class TurnRight implements IslandFinderStates{
        @Override
        public JSONObject handle(IslandFinder finder){
            action.echo(currentDirection);
            finder.setState(new EchoForward());
            return action.getDecision();
        }
    }
    private class TurnLeft implements IslandFinderStates{
        @Override
        public JSONObject handle(IslandFinder finder){
            action.echo(currentDirection);
            finder.setState(new EchoForward());
            return action.getDecision();
        }
    }

}
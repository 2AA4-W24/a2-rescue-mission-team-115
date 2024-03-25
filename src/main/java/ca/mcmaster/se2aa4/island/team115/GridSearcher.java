package ca.mcmaster.se2aa4.island.team115;

import org.json.JSONArray;
import org.json.JSONObject;

public class GridSearcher implements POIFinder{
    private IslandFinder finder = new IslandFinder();
    private boolean turnRightOnUTurn = finder.shouldTurnRightOnUTurn(); //When UTurning, checks whether the direction should be to the right or left
    private boolean searchingComplete = false;
    private POIMap map = new POIMap();
    private GridSearcherStates state;
    private Direction currentDirection;
    private Coordinates coordinates;
    private Drone drone;
    private Info info;
    private Action action = new Action();
    private Integer scanCount = 0;

    public GridSearcher(){
        state = new Fly();
    }

    public boolean isComplete(){
        return searchingComplete;
    }

    public JSONObject stopExploration(){
        action.reset();
        action.stop();
        return action.getDecision();
    }

    public void setDrone(Drone drone, Info info, Coordinates coordinates) {
        this.drone = drone;
        this.info = info;
        this.coordinates = coordinates;
    }

    public void setState(GridSearcherStates state){
        this.state = state;
    }

    private void checkPOI(JSONObject extras){
        JSONArray creeks = extras.getJSONArray("creeks");
        JSONArray sites = extras.getJSONArray("sites");
        if(creeks.length()== 1){
            String creekID = creeks.getString(0);
            map.addPOI(creekID, coordinates);
        }
        if(sites.length()== 1){
            map.setEmergencySpot(coordinates);
        }
    }

    public String getClosestCreek(){
        return map.calculateClosestCreek();
    }

    @Override
    public JSONObject findPOIs(Direction currentDirection) {
        action.reset();
        this.currentDirection = currentDirection;
        return state.handle(this);
    }

    private interface GridSearcherStates{
        public JSONObject handle(GridSearcher searcher);
    }

    private class Fly implements GridSearcherStates{
        @Override
        public JSONObject handle(GridSearcher searcher){
            action.scan();
            searcher.setState(new Scan());
            return action.getDecision();
        }
    }
    private class Scan implements GridSearcherStates{
        @Override
        public JSONObject handle(GridSearcher searcher){
            JSONObject extras = info.getExtras();
            JSONArray biomes = extras.getJSONArray("biomes");
            checkPOI(extras);
            if(biomes.length()==1){
                Object bio = biomes.get(0);
                if (bio.equals("OCEAN")){
                    action.echo(currentDirection);
                    searcher.setState(new EchoForward());
                    return action.getDecision();
                }
            }
            action.fly();
            coordinates.flyForward();
            drone.updateCoordinates(coordinates);
            searcher.setState(new Fly());
            return action.getDecision();
        }
    }

    private class EchoForward implements GridSearcherStates{
        @Override
        public JSONObject handle(GridSearcher searcher){
            JSONObject extras = info.getExtras();
            String finding = extras.getString("found");
            Integer range = extras.getInt("range");
            if(finding.equals("GROUND")){
                action.fly();
                coordinates.flyForward();
                drone.updateCoordinates(coordinates);;
                searcher.setState(new FlyToIsland(range));
            }else{
                if(range<4){
                    if(turnRightOnUTurn){
                        action.heading(currentDirection.rightDir());
                        coordinates.turnRight();
                        drone.updateDirection(currentDirection.rightDir());
                        drone.updateCoordinates(coordinates);
                    }else{
                        action.heading(currentDirection.leftDir());
                        coordinates.turnLeft();
                        drone.updateDirection(currentDirection.leftDir());
                        drone.updateCoordinates(coordinates);
                    }
                    searcher.setState(new UTurn());

                }else{
                    action.fly();
                    coordinates.flyForward();
                    drone.updateCoordinates(coordinates);
                    searcher.setState(new FlyAwayFromIsland(range));
                }
            }
            return action.getDecision();
        }
    }
    private class FlyToIsland implements GridSearcherStates{
        private Integer range;
        private Integer flyCount;
        private FlyToIsland(Integer range){
            this.range = range;
            flyCount = 0;
        }
        @Override
        public JSONObject handle(GridSearcher searcher){

            if(flyCount<range){
                action.fly();
                coordinates.flyForward();
                drone.updateCoordinates(coordinates);;
                flyCount++;
            }else{
                action.scan();
                searcher.setState(new Scan());
            }
            return action.getDecision();
        }
    }

    private class FlyAwayFromIsland implements GridSearcherStates{
        private Integer range;
        private Integer flyCount;
        private FlyAwayFromIsland(Integer range){
            this.range = range-4;
            flyCount = 0;
        }
        @Override
        public JSONObject handle(GridSearcher searcher){

            if(flyCount<range){
                action.fly();
                coordinates.flyForward();
                drone.updateCoordinates(coordinates);;
                flyCount++;
            }else{
                if(turnRightOnUTurn){
                    action.heading(currentDirection.rightDir());
                    coordinates.turnRight();
                    drone.updateDirection(currentDirection.rightDir());
                    drone.updateCoordinates(coordinates);
                    searcher.setState(new UTurn());
                }else{
                    action.heading(currentDirection.leftDir());
                    coordinates.turnLeft();
                    drone.updateDirection(currentDirection.leftDir());
                    drone.updateCoordinates(coordinates);
                    searcher.setState(new UTurn());
                }

            }
            return action.getDecision();
        }
    }

    private class UTurn implements GridSearcherStates{
        private Integer turnCount;
        public UTurn(){
            turnCount = 1;
        }
        @Override
        public JSONObject handle(GridSearcher searcher){
            if (turnCount <2){
                if(turnRightOnUTurn){
                    action.heading(currentDirection.rightDir());
                    coordinates.turnRight();
                    drone.updateDirection(currentDirection.rightDir());
                    drone.updateCoordinates(coordinates);
                }else{
                    action.heading(currentDirection.leftDir());
                    coordinates.turnLeft();
                    drone.updateDirection(currentDirection.leftDir());
                    drone.updateCoordinates(coordinates);
                }
                turnCount++;
            } else {
                turnRightOnUTurn = !turnRightOnUTurn;
                action.echo(currentDirection);
                searcher.setState(new EchoToCheckInterlacedScanCompletion());
            }
            return action.getDecision();
        }
    }

    private class EchoToCheckInterlacedScanCompletion implements GridSearcherStates{
        @Override
        public JSONObject handle(GridSearcher searcher){
            JSONObject extras = info.getExtras();
            String finding = extras.getString("found");
            Integer range = extras.getInt("range");
            if (finding.equals("GROUND")){
                action.fly();
                coordinates.flyForward();
                drone.updateCoordinates(coordinates);;
                searcher.setState(new FlyToIsland(range));
            } else {
                scanCount++;
                if(scanCount == 3){
                    searchingComplete = true;
                }
                action.fly();
                coordinates.flyForward();
                drone.updateCoordinates(coordinates);
                searcher.setState(new FlyToPrepareForSpecialTurn(range));
            }
            return action.getDecision();
        }
    }

    private class FlyToPrepareForSpecialTurn implements GridSearcherStates{
        private Integer range;
        private Integer flyCount;
        private boolean turnRightOnSpecialTurn = !turnRightOnUTurn;
        private FlyToPrepareForSpecialTurn(Integer range){
            this.range = range-2;
            flyCount = 0;
        }
        public JSONObject handle(GridSearcher searcher){

            if(flyCount<range){
                action.fly();
                coordinates.flyForward();
                drone.updateCoordinates(coordinates);;
                flyCount++;
            }else{
                if(turnRightOnSpecialTurn){
                    action.heading(currentDirection.rightDir());
                    coordinates.turnRight();
                    drone.updateDirection(currentDirection.rightDir());
                    drone.updateCoordinates(coordinates);
                }else{
                    action.heading(currentDirection.leftDir());
                    coordinates.turnLeft();
                    drone.updateDirection(currentDirection.leftDir());
                    drone.updateCoordinates(coordinates);
                }
                searcher.setState(new SpecialTurn());
            }
            return action.getDecision();
        }
    }

    private class SpecialTurn implements GridSearcherStates{
        private Integer step;
        private boolean turnRightOnSpecialTurn = !turnRightOnUTurn;
        public SpecialTurn(){
            step = 1;
        }
        public JSONObject handle(GridSearcher searcher){
            switch(step){
                case 1:
                    action.fly();
                    coordinates.flyForward();
                    drone.updateCoordinates(coordinates);
                    step++;
                    break;
                case 2:
                    if(turnRightOnSpecialTurn){
                        action.heading(currentDirection.rightDir());
                        coordinates.turnRight();
                        drone.updateDirection(currentDirection.rightDir());
                        drone.updateCoordinates(coordinates);
                    }else{
                        action.heading(currentDirection.leftDir());
                        coordinates.turnLeft();
                        drone.updateDirection(currentDirection.leftDir());
                        drone.updateCoordinates(coordinates);
                    }
                    step++;
                    break;
                case 3:
                    if(turnRightOnSpecialTurn){
                        action.heading(currentDirection.rightDir());
                        coordinates.turnRight();
                        drone.updateDirection(currentDirection.rightDir());
                        drone.updateCoordinates(coordinates);
                    }else{
                        action.heading(currentDirection.leftDir());
                        coordinates.turnLeft();
                        drone.updateDirection(currentDirection.leftDir());
                        drone.updateCoordinates(coordinates);
                    }
                    step++;
                    break;
                case 4:
                    if(turnRightOnUTurn){
                        action.heading(currentDirection.rightDir());
                        coordinates.turnRight();
                        drone.updateDirection(currentDirection.rightDir());
                        drone.updateCoordinates(coordinates);
                    }else{
                        action.heading(currentDirection.leftDir());
                        coordinates.turnLeft();
                        drone.updateDirection(currentDirection.leftDir());
                        drone.updateCoordinates(coordinates);
                    }
                    step++;
                    break;
                case 5:
                    action.echo(currentDirection);
                    searcher.setState(new EchoForward());
                    break;

            }
            return action.getDecision();
        }
    }
}
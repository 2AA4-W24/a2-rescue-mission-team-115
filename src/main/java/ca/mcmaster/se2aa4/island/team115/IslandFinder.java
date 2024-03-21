package ca.mcmaster.se2aa4.island.team115;


import org.json.JSONArray;
import org.json.JSONObject;

public class IslandFinder {

    private boolean echoRight = true;
    private boolean changeHeading = false;


    private boolean foundGround = false;
    private int range;
    private boolean activateForwardEcho = true;
    private boolean activateFlyMode = false;
    private boolean activateFlyMode2 = false;
    private boolean activateSearchPhase = false;
    private boolean activateScanPhase = false;

    private boolean oddScan = true;
    private boolean shouldScan = false;

    private int i = 0;

    private Info info;

    private JSONObject extraInfo;


    private boolean test = true;

    private boolean start = true;

    private boolean groundForward = false;

    private int phase = 1;
    private int searchPhase = 1;
    private int j = 0;
    private int z = 1;
    private boolean scanOOB = false;
    // private boolean groundLeft = false;
    // private boolean groundRight = false;


    private Direction currentDirection;
    private Direction echoDirection;
    private Action action = new Action();
    private Drone drone;

    public void setDrone (Drone drone, Info info){
        this.drone = drone;
        this.info = info;
    }

    public JSONObject locateIsland(Direction currentDirection){
        
        action.reset();
        this.currentDirection = currentDirection;
        try{
            checkGround(info.getExtras());
            if (activateScanPhase && shouldScan){
                checkScan(info.getExtras());
            }
            
        }catch(NullPointerException ne){
            
        }

        if(foundGround || activateFlyMode || activateSearchPhase || activateScanPhase){
            if(currentDirection.equals(echoDirection) || activateScanPhase){
                activateFlyMode = true;
                if (i<range){
                    action.fly();
                    i++;
                }else if (i == range){
                    action.scan();
                    activateFlyMode = false;
                    activateScanPhase = true;
                    // phase = 4;
                    i++;
                }else {
                    searchIsland();
                } 
            }else{
                action.heading(echoDirection);
                drone.updateDirection(echoDirection);
                activateForwardEcho = true;
                foundGround = false;
                phase = 1;
            }
        }else{
            if(activateForwardEcho){
                action.echo(currentDirection);
                activateForwardEcho = false;
            }else{
                switch(phase){
                    case 1:
                        echoDirection = currentDirection.rightDir();
                        action.echo(echoDirection);
                        phase++;
                        break;
                    case 2:
                        echoDirection = currentDirection.leftDir();
                        action.echo(echoDirection);
                        phase++;
                        break;
                    case 3:
                        action.fly();
                        phase = 1;
                        break;
                    default:
                        action.stop();
                }
            }
        }
        
        return action.getDecision();
    
    }

    private void searchIsland(){
        switch (searchPhase){
            case 1:
                action.fly();
                searchPhase++;
                shouldScan = false;
                break;
            case 2:
                shouldScan = true;
                if (scanOOB){
                    action.echo(currentDirection);
                    searchPhase++;
                }else{
                    action.scan();
                    searchPhase--;
                    j++;
                }
                break;
            case 3: 
                if(foundGround || activateFlyMode2){
                    if(z<range){
                        activateFlyMode2 = true;
                        action.fly();
                        z++;
                        break;
                    }else{
                        activateFlyMode2 = false;
                        searchPhase = 1;
                        z=0;
                    }
                }else{
                    searchPhase++;
                }
            case 4:
                if (oddScan == true){
                    drone.updateDirection(currentDirection.rightDir());
                    action.heading(currentDirection.rightDir());
                } else{
                    drone.updateDirection(currentDirection.leftDir());
                    action.heading(currentDirection.leftDir());
                }
                searchPhase++;
                break;
            case 5: 
            // case 5 and 6 need to be changed depending on the direction of entry 
            if (oddScan == true){
                drone.updateDirection(currentDirection.rightDir());
                action.heading(currentDirection.rightDir());
                oddScan = false;
            } else{
                drone.updateDirection(currentDirection.leftDir());
                action.heading(currentDirection.leftDir());
                oddScan = true;
            }
                searchPhase++;
                break;
            case 6:
                action.echo(currentDirection);
                searchPhase++;
                break;
            case 7:
                if(foundGround || activateFlyMode2){
                    if(z<range){
                        activateFlyMode2 = true;
                        z++;
                    }else{
                        activateFlyMode2 = false;
                        searchPhase = 1;
                        z=0;
                    }
                }else{
                    action.stop();
                    break;
                }
                action.fly();
                break;
            default:
                break;
        }
    }
    public void updateState(Info info){
        
    }
    
    private void checkScan (JSONObject extras){
        if(extras.has("biomes")){
            JSONArray arr1 = extras.getJSONArray("biomes");
            if(arr1.length() == 1){
                Object bio = arr1.get(0);
                if (bio.equals("OCEAN")){
                    scanOOB = true;
                    return;
                }
            }
        }
        scanOOB = false;
    }

    private void checkGround(JSONObject extras){
        if(extras.has("found")){
            String finding = extras.getString("found");
            if(finding.equals("GROUND")){
                range = extras.getInt("range");
                foundGround = true;
                return;
            }else{
                foundGround = false;
                return;
            }
        }
        foundGround = false;
    }
}

        
package ca.mcmaster.se2aa4.island.team115;

import org.json.JSONObject;

public class Info {
    private Integer cost;
    private JSONObject extras;
    private String droneStatus;
    private Direction direction;

    public Info(Integer cost, JSONObject extras, String droneStatus) {
        this.cost = cost;
        this.extras = extras;
        this.droneStatus = droneStatus;

    }
    public Direction getDirection(){
        return direction;
    }
    public JSONObject getExtras() {
        return extras;
    }

    public Integer getCost() {
        return cost;
    }

    public String getStatus(){
        return droneStatus;
    }
}
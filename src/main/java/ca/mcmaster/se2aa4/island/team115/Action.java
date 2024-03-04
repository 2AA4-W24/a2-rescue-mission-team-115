package ca.mcmaster.se2aa4.island.team115;

import org.json.JSONObject;

public class Action {
    public String echo(Direction direction){
        String strDir = direction.toString();
        JSONObject decision = new JSONObject();
        decision.put("action", "echo");
        decision.put("parameters", (new JSONObject()).put("direction", strDir));
        return decision.toString();
    }
    public void fly(){
        JSONObject decision = new JSONObject();
        decision.put("action","fly");
    }
    public void scan(){
        JSONObject decision = new JSONObject();
        decision.put("action","scan");
    }
    public void heading(Direction direction){
        JSONObject decision = new JSONObject();
        JSONObject parameters = new JSONObject();
        String parameter = direction.toString();
        parameters.put("direction",parameter);
        decision.put("action","heading");
        decision.put("parameters", parameters);
    }
    
}

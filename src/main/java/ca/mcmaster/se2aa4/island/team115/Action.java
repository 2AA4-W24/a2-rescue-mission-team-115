package ca.mcmaster.se2aa4.island.team115;

import org.json.JSONObject;

public class Action {
    private Info actInfo;
    private String decString;
 
    private JSONObject decision = new JSONObject();
    private JSONObject parameters = new JSONObject();

    public void echo(Direction direction){
        String strDir = direction.toString();
        
        //JSONObject decision = new JSONObject();
        decision.put("action", "echo");
        decision.put("parameters", (new JSONObject()).put("direction", strDir));
        decString = decision.toString();
    }
    public void fly(){
        //JSONObject decision = new JSONObject();
        decision.put("action","fly");
        decString = decision.toString();
    }
    public void scan(){
        //JSONObject decision = new JSONObject();
        decision.put("action","scan");
        decString = decision.toString();
    }
    public void heading(Direction direction){
        //JSONObject decision = new JSONObject();
        //JSONObject parameters = new JSONObject();
        String parameter = direction.toString();
        parameters.put("direction",parameter);
        decision.put("action","heading");
        decision.put("parameters", parameters);

        decString = decision.toString();
    }
    public void stop(){
        //JSONObject decision = new JSONObject();
        decision.put("action","stop");
        decString = decision.toString();

    }

    public String getDecisionString(){
        return decString;
    }

    public JSONObject getDecision(){
        return decision;
    }
}


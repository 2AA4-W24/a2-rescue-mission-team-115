package ca.mcmaster.se2aa4.island.team115;

import java.io.IOException;
import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {
    private Drone drone;
    private Translator trans = new Translator();
    private final Logger logger = LogManager.getLogger();
    int i = 0;
    boolean scan = false;


    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");
        

        drone = new Drone (batteryLevel, direction);
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
        
        //Direction dir = Navigator.stringToEnum(direction);
    
    }

    @Override
    public String takeDecision(){
        //String decisionString = drone.beginExploration();
        JSONObject decision = new JSONObject();
        decision = drone.beginExploration(drone);
        // decision.put("action", "echo");
        // decision.put("parameters", (new JSONObject().put("direction", "E")));
        
        // decision.put("action", "echo");
        // decision.put("parameters", (new JSONObject()).put("direction", "E"));
        // decision.put("parameters", (new JSONObject()).put("direction", "S"));
        // if(scan){
        //     decision.put("action", "scan");
        //     scan = false;
        // }else if(i>10){
        //     decision.put("action", "stop");
        // }else{
        //     decision.put("action", "fly");
        //     scan = true;
        //     i++;
        // }
        //logger.info("** Decision: {}",decisionString);
        logger.info("** Decision: {}",decision.toString());
        //return decisionString;
        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {

        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n"+response.toString(2));
        Info information = trans.translate(response);

        Integer cost = information.getCost();
        logger.info("The cost of the action was {}", cost);

        String status = information.getStatus();
        logger.info("The status of the drone is {}", status);

        JSONObject extraInfo = information.getExtras();
        logger.info("Additional information received: {}", extraInfo);
        
        drone.updateStatus(cost , status);
        drone.receiveResponse(information);

    
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}

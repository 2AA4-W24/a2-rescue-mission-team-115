package ca.mcmaster.se2aa4.island.team115;

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
    
    }

    @Override
    public String takeDecision(){
        JSONObject decision = new JSONObject();
        decision = drone.beginExploration(drone);
        
        logger.info("** Decision: {}",decision.toString());
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

        drone.receiveResponse(cost, information);
    }

    @Override
    public String deliverFinalReport() {
        String creekID = drone.getClosestCreekID();
        logger.info("The ID of the closest creek is: " + creekID);
        return creekID;
    }

}
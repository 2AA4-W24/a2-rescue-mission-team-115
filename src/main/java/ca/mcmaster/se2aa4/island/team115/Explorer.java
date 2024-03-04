package ca.mcmaster.se2aa4.island.team115;

import java.io.IOException;
import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    int i = 0;
    boolean scan = false;

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        //JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        //logger.info("** Initialization info:\n {}",info.toString(2));
        // String direction = info.getString("heading");
        // Integer batteryLevel = info.getInt("budget");
        ExtractInfo extract = new ExtractInfo();
        try {
            extract.parser(s);
        } catch (IOException e) {
            e.printStackTrace();
        }


        
        Integer batteryLevel = BatteryTracker.getBatteryLevel();
        //Direction enumDir = extract.getInitialHeading();
        //String direction = Navigator.enumToString(extract.getInitialHeading()); 
        //logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
        
        //Direction dir = Navigator.stringToEnum(direction);
    
    }

    @Override
    public String takeDecision() {
        
        JSONObject decision = new JSONObject();
        // decision.put("action", "echo");
        // decision.put("parameters", (new JSONObject()).put("direction", "E"));
        // decision.put("parameters", (new JSONObject()).put("direction", "S"));
        if(scan){
            decision.put("action", "scan");
            scan = false;
        }else if(i>10){
            decision.put("action", "stop");
        }else{
            decision.put("action", "fly");
            scan = true;
            i++;
        }
        logger.info("** Decision: {}",decision.toString());
        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        ExtractInfo extract = new ExtractInfo();
        try {
            extract.parser(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n"+response.toString(2));
        //Integer cost = response.getInt("cost");
        Integer cost = extract.getCost();
        logger.info("The cost of the action was {}", cost);
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}

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

    public void checkCreek(){
        if (extras.has("creeks")){
            if(extras.getJSONArray("creeks").length()>0){
                //add Creek name and coordinates to hashmap
            }
        }
    }
    public void checkEmergencySite(){
        if (extras.has("creeks")){
            if(extras.getJSONArray("creeks").length()>0){
                //add EmergencySite name and coordinates to hashmap
            }
        }
    }
    
    public String echoFinding(){
        if(extras.has("found")){
            return extras.getString("found");
        }
        return "No Relevant Finding";
    }

    public Integer echoRange(){
        if(extras.has("found")){
            return extras.getInt("range");
        }
        return 0;
    }
}

//     if (data.has("cost")) {
//             cost = (Integer) data.get("cost");
//             if(data.has("extras")){
//                 JSONObject extra = data.getJSONObject("extras");
//                 if(extra.has("creeks")&&extra.getJSONArray("creeks").length()>0){
//                     JSONArray creekDetails = new JSONArray();
//                     creekDetails.put(new JSONObject().put("name","creek"));
//                     creekDetails.put(new JSONObject().put("coordinates", "xy"));
//                     result.put(creekDetails);
//                 }
//                 else if(extra.has("sites")&&extra.getJSONArray("sites").length()>0){
//                     JSONArray siteDetails = new JSONArray();
//                     siteDetails.put(new JSONObject().put("name","site"));
//                     siteDetails.put(new JSONObject().put("coordinates", "xy"));
//                     result.put(siteDetails);
//                 } else if(extra.has("found")){
//                     found = extra.getString("found");
//                     range = extra.getInt("range");
//             }
//         }
// }
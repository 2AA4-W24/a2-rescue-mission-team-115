package ca.mcmaster.se2aa4.island.team115;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;


public class ExtractInfo {
    private Direction initialheading;
    public Direction getInitialHeading(){
        return initialheading;
    }
    private Integer cost;
    public Integer getCost(){
        return cost;
    }
   

    public void parseJSON(String file) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(file)));
        JSONArray jsonArray = new JSONArray(content);
        JSONArray result = new JSONArray();

        
        JSONObject jsonObject = jsonArray.getJSONObject(jsonArray.length()-1);
        JSONObject data = jsonObject.getJSONObject("data");
        if(data.has("heading")){
            String heading = data.get("heading").toString();
            switch(heading){
                case "E":
                    initialheading = Direction.East;
                    break;
                case "W":
                    initialheading = Direction.West;
                    break;
                case "N":
                    initialheading = Direction.North;
                    break;
                case "S":
                    initialheading = Direction.South;
                    break;
            }
        }
        if(data.has("budget")){
            BatteryTracker.setBatteryLevel((Integer) data.get("budget"));
            //sets initial battery level
        }

        if (data.has("cost")) {
            cost = (Integer) data.get("cost");
            if(data.has("extras")){
                JSONObject extra = data.getJSONObject("extras");
                if(extra.has("creeks")&&extra.getJSONArray("creeks").length()>0){
                    JSONArray creekDetails = new JSONArray();
                    creekDetails.put(new JSONObject().put("name","creek"));
                    creekDetails.put(new JSONObject().put("coordinates", "xy"));
                    result.put(creekDetails);
                }
                else if(extra.has("sites")&&extra.getJSONArray("sites").length()>0){
                    JSONArray siteDetails = new JSONArray();
                    siteDetails.put(new JSONObject().put("name","site"));
                    siteDetails.put(new JSONObject().put("coordinates", "xy"));
                    result.put(siteDetails);
                }
            }
        }
       //Files.write(Paths.get("/Users/kunxing/Desktop/Y2/Semester2/2AA4/Assignments/a2-rescue-mission-team-115/outputs/info.json"), result.toString(4).getBytes());
    }
}
    
    

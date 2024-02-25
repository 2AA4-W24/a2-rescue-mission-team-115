package ca.mcmaster.se2aa4.island.team115;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;


public class ExtractInfo {
    private Direction initialheading;
    private Integer cost;
    private String found;
    private Integer range;

    public ExtractInfo() {

    }

    public ExtractInfo(Direction initialheading) {
        this.initialheading = initialheading;

    }

    public Direction getInitialHeading(){
        return initialheading;
    }
    
    public Integer getCost(){
        return cost;
    }

    public Integer getRange(){
        return range;
    }

    public String getFound(){
        return found;
    }
   
    //NOTE NOTE NOTE NOTE NOTE
    // WE NEED TO SPLIT UP THE PARSEJSON METHOD. ALSO WE NEED TO ADD SOMETHING FOR GETTING THE INFORMATION FOR ECHO.
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


        //We need to separate the extraction of cost from heading and budget. Heading and budget take care of the initialize Method in Explorer
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
                } else if(extra.has("found")){
                    found = extra.getString("found");
                    range = extra.getInt("range");
                }
            }
        }
       //Files.write(Paths.get("/Users/kunxing/Desktop/Y2/Semester2/2AA4/Assignments/a2-rescue-mission-team-115/outputs/info.json"), result.toString(4).getBytes());
    }
}
    
    

package ca.mcmaster.se2aa4.island.team115;
import org.json.JSONObject;
import org.json.JSONTokener;

public class IslandGridSearch {
    public String orientation;
    private boolean scanning = true;
    private boolean checkingforward = false;
    private Integer specialuturnleft = 0;
    private Integer specialuturnright =0;
    private Integer uturnright = 0; 
    private Integer uturnleft = 0;
    private boolean uturn = false;
    
    Translator translator = new Translator();
    JSONObject response = new JSONObject();
    Boolean RL;

    public Action action = new Action();
    private void findPoints(Direction currdirection){
        if(currdirection == Direction.N||currdirection == Direction.S){
            orientation = "vertical";
        }
        else if(currdirection == Direction.E||currdirection == Direction.W){
            orientation = "horizontal";
        }
        switch(orientation){
            case "vertical":

            case "horizontal":
       
    //logic not correct
    /*private void alignWithEdge(Direction currdirection){
        action.echo(currdirection.leftDir());
        Info leftresponse = translator.translate(response);
        action.echo(currdirection.rightDir());
        Info rightresponse = translator.translate(response);
        Integer leftrange = leftresponse.echoRange();
        Integer rightrange = rightresponse.echoRange();
        if(leftrange>rightrange){
            currdirection = currdirection.rightDir();
            action.heading(currdirection);
            for (int i=0; i<rightrange;i++){
                action.fly();
            }
        }
        else{
            currdirection = currdirection.leftDir();
            action.heading(currdirection);
            for (int i=0; i<leftrange;i++){
                action.fly();
            }
        }*/
    }}
        private void specialUTurnRight(Direction currdirection){
            if (specialuturnright == 0) {
                currdirection = currdirection.rightDir(); 
                action.heading(currdirection);
                specialuturnright++; 
            } else if (specialuturnright == 1) {
                action.fly(); 
                specialuturnright++; 
            } else if (specialuturnright >= 2 && specialuturnright <= 4) { 
                currdirection = currdirection.leftDir();
                action.heading(currdirection);
                specialuturnright++;
                if (specialuturnright > 4) {
                    specialuturnright = 0; 
                }
            }
        }
        private void specialUTurnLeft(Direction currdirection){
            if (specialuturnleft == 0) {
                currdirection = currdirection.leftDir(); 
                action.heading(currdirection);
                specialuturnleft++; 
            } else if (specialuturnleft == 1) {
                action.fly(); 
                specialuturnleft++; 
            } else if (specialuturnleft >= 2 && specialuturnleft <= 4) { 
                currdirection = currdirection.rightDir();
                action.heading(currdirection);
                specialuturnleft++;
                if (specialuturnleft > 4) {
                    specialuturnleft = 0; 
                }
            }
        }
        private void initiate(Direction currdirection){
            int[] coordinates = {0,0};
            action.echo(currdirection.rightDir());
            Info rightresponse = translator.translate(response);
            if(rightresponse.echoRange()>=1){
                action.heading(currdirection.rightDir());
            }
            action.heading(currdirection.leftDir());     
        }
        private void uTurnRight(Direction currdirection){
            if(uturnright == 0){
                currdirection = currdirection.rightDir();
                action.heading(currdirection);
                uturnright++;
            }
            else if(uturnright == 1){
                currdirection = currdirection.rightDir();
                action.heading(currdirection);
                uturnright++;
                if (uturnright>1){
                    uturnright = 0;

                }
            }      
        }
        private void uTurnLeft(Direction currdirection){
            if(uturnleft == 0){
                currdirection = currdirection.leftDir();
                action.heading(currdirection);
                uturnleft++;
            }
            else if(uturnleft == 1){
                currdirection = currdirection.leftDir();
                action.heading(currdirection);
                uturnleft++;
                if (uturnleft>1){
                    uturnleft = 0;
                }
            }
            
        }
        private JSONObject gridSearch(Direction currdirection) {
            if(scanning) {
                action.scan();
                Info forwardresponse = translator.translate(response);
                if(/* the scan is water */){
                    scanning = false ;
                    checkingforward = false;
                    uturn = false;  
                }
                else{
                    scanning = false;
                    checkingforward = true;
                    uturn = false; }
            } else if(checkingforward) {
                action.fly();
                scanning = true;
                checkingforward = false;
                uturn = false; 
                } 
            else if(uturn){
                /*call uturn functions, idk right or left 
                after you uturn you want to start the process again
                */
                scanning = true;
                checkingforward = false;
                uturn = false; 
            }
            else if(!scanning&&!checkingforward) {
                action.echo(currdirection);
                Info forwardresponse = translator.translate(response);
                if(forwardresponse.echoFinding()!=null){
                    checkingforward = true;
                    scanning = false;
                    uturn = false; 
                    }
                
                else{
                    uturn = true;
                    checkingforward = false;
                    scanning = false;
                }}
            
        return action.getDecision(); 
            }}


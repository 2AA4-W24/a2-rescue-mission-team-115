package ca.mcmaster.se2aa4.island.team115;
import org.json.JSONObject;
import org.json.JSONTokener;

public class IslandGridSearch {
    public String orientation;
    
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
        }
    }
    private void alignWithEdge(Direction currdirection){
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
        }
        }
        private void specialUTurnRight(Direction currdirection){
            currdirection = currdirection.rightDir();
            action.heading(currdirection);
            action.fly();
            for(int i=0; i<=2;i++){
                currdirection = currdirection.leftDir();
                action.heading(currdirection);
            }
        }
        private void specialUTurnLeft(Direction currdirection){
            currdirection = currdirection.leftDir();
            action.heading(currdirection);
            action.fly();
            for(int i=0; i<=2;i++){
                currdirection = currdirection.rightDir();
                action.heading(currdirection);
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
            for(int i=0; i<=1;i++){
                action.heading(currdirection.rightDir());
            }
        }
        private void uTurnLeft(Direction currdirection){
            for(int i=0; i<=1;i++){
                action.heading(currdirection.leftDir());
            }
        }
        private void gridSearch(Direction currdirection){
            action.fly();
            action.scan();
            while(/*scan is not water*/){
                action.fly();
                action.scan();
                }
            action.echo(currdirection);
            Info forwardresponse = translator.translate(response);
            if(forwardresponse.echoFinding()!=null){
                Integer range = forwardresponse.echoRange();
                for(int i;i<range;i++){
                    action.fly();
                }
                gridSearch(currdirection);
            } 
            }
        
        

        








        
    }


package OjtAssessment;

import java.util.ArrayList;
 

enum Types {
    Private,
    Mcycle,
    Trailer
  } 
  enum Status {
    Waiting,
    Inprog,
    End
  }
  enum Actions{
        R,C,None
  }

public class Vehicles {
    int  vnum;
    Types type;
    Status status; 
    ArrayList<Actions> actions = new ArrayList<Actions>();
    Actions act;
    Actions act1;
    Actions R;
    Actions C;
    int timer=0;
 
 public  Vehicles (int vnum,Types type, Actions ac,Actions ac1) {
    this.vnum=vnum;
    this.type=type;
    this.act=ac;
    this.act1=ac1;
    this.status=Status.Waiting;    
  }
  
public  Vehicles (int vnum,Types type,  Actions act) {
    this.vnum=vnum;
    this.type=type;
    this.status=Status.Waiting;
     this.act=act; 
     this.act1=Actions.None;
  }
 

public Types getType() {
    return type;
    
}
public Status getStatus() {
    return status;
}
public void setTimer(int timer){

    this.timer=timer;
}
public int getTimer(){

    return this.timer;
}
    

public Actions getActions() {
    return actions.get(0);
    
}
public void setType(Types type) {
    this.type=type;
     
    
}
public void setStatus(Status status) {
    this.status=status;
}
public void setActions(ArrayList<Actions> actions) {

    this.actions=actions;

    
}

}
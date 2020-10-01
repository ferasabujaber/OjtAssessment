package OjtAssessment;
import java.util.ArrayList;

import java.util.concurrent.Semaphore;


 
public class GasStation {
    private static final int MAX_R = 1;
    private static final int MAX_C = 1;
    int timer=0;
    int timerc=0;
    ArrayList<Vehicles> vlist = new ArrayList<Vehicles>();
    ArrayList<Vehicles> Wait = new ArrayList<Vehicles>();



    private final Semaphore LOCK = new Semaphore(MAX_R, true);
    private final Semaphore LOCK1 = new Semaphore(MAX_C, true);

    
    public void runDemo() {
       

            vlist.add(new Vehicles(0,Types.Mcycle ,Actions.R,Actions.C));
            vlist.add(new Vehicles(1,Types.Mcycle ,Actions.R));
            vlist.add(new Vehicles(2,Types.Private ,Actions.R,Actions.C));
            vlist.add(new Vehicles(3,Types.Private ,Actions.C));
            vlist.add(new Vehicles(4,Types.Private ,Actions.C));
            vlist.add(new Vehicles(5,Types.Private ,Actions.R));
            vlist.add(new Vehicles(6,Types.Private ,Actions.R));
            vlist.add(new Vehicles(7,Types.Mcycle ,Actions.R));
            vlist.add(new Vehicles(8,Types.Mcycle ,Actions.R));
            vlist.add(new Vehicles(9,Types.Private ,Actions.R));
            vlist.add(new Vehicles(9,Types.Private ,Actions.C));

            vlist.add(new Vehicles(10,Types.Trailer ,Actions.C));
            vlist.add(new Vehicles(11,Types.Mcycle ,Actions.R));
            vlist.add(new Vehicles(12,Types.Private ,Actions.R));
            vlist.add(new Vehicles(13,Types.Trailer ,Actions.R));
             vlist.add(new Vehicles(13,Types.Trailer ,Actions.C));
            vlist.add(new Vehicles(14,Types.Private ,Actions.R));
      
        for (int i = 0; i < vlist.size(); i++) {
          

            Station station = new Station(vlist.get(i));
            station.start();}
    }
    
    public class Station extends Thread {
        Vehicles x;
        int ind=0;

        

        public Station(Vehicles x){
            this.x=x;
           // this.ind=i;
         //   System.out.println(ind);

        }
        @Override
        public void run() {
            
                 Refueling(x);
                      Cleaning(x); 
        }
        
        public void Refueling(Vehicles x){
            if(x.act.equals(Actions.R)&&x.status.equals(Status.Waiting)){
                try { 
                    LOCK.acquire();
                    x.setStatus(Status.Inprog);
                 } catch (InterruptedException e) {
                    System.out.println("received InterruptedException");
                    return;
                }
               System.out.println(" Vehicle: " + x.vnum+" "+x.type+"  starts refueling in time:"+ timer);
                timer=timercalc(x,timer);
                try {
                 } 
                catch (Exception e) {}       
                finally { 
                    LOCK.release();
                    x.setStatus(Status.Waiting);
                }
            }
            else if(x.act1.equals(Actions.R)&&x.status.equals(Status.Waiting) ){
                try {
                    
                    LOCK.acquire();
                    x.setStatus(Status.Inprog);
                 } catch (InterruptedException e) {
                    System.out.println("received InterruptedException");
                    return;
                }
                
               System.out.println(" Vehicle: " + x.vnum+" "+x.type+"  starts refueling in time:"+ timer);
                timer=timercalc(x,timer);
                try {
                 } 
                catch (Exception e) {}       
                finally {                    
                     LOCK.release();
                    x.setStatus(Status.End);
                }
            }
        }

        public void Cleaning(Vehicles x){
            if(x.act.equals(Actions.C)&&x.status.equals(Status.Waiting)){
                try {
                     LOCK1.acquire();
                    x.setStatus(Status.Inprog);
                } catch (InterruptedException e) {
                    System.out.println("received InterruptedException");
                    return;
                }
                System.out.println(" Vehicle: "+ x.vnum+" "+x.type+"  starts cleaning in time:"+ timerc);
                timerc=timercalc1(x,timerc);
                try {
                 } 
                catch (Exception e) {}       
                finally {
                      LOCK1.release();
                    x.setStatus(Status.Waiting);
                }

            }else if(x.act1.equals(Actions.C)&&x.status.equals(Status.Waiting)){

                try {
                     LOCK1.acquire();
                } catch (InterruptedException e) {
                    System.out.println("received InterruptedException");
                    return;
                }
                System.out.println(" Vehicle: "+ x.vnum+" "+x.type+"  starts cleaning in time:"+ timerc);
                timerc=timercalc1(x,timerc);
                try {
                 } 
                catch (Exception e) {}       
                finally {
                      LOCK1.release();
                    x.setStatus(Status.End);
                }
            }
        } 
        public int timercalc(Vehicles x,int timer){
            if (x.type.equals(Types.Mcycle)){
    
                x.setTimer(timer+1);
                return timer+1;
            }
            if (x.type.equals(Types.Private)){
                x.setTimer(timer+3);
                return timer+3; 
            }
            if (x.type.equals(Types.Trailer)){
                x.setTimer(timer+5);
                 return timer+5;
            }
    
            return timer;
        }
        public int timercalc1(Vehicles x,int t){
            if (x.type.equals(Types.Mcycle)){
                x.setTimer(t+2);    
                return t+2;
            }
            if (x.type.equals(Types.Private)){
                   return t+4; 
            }
            if (x.type.equals(Types.Trailer)){
                x.setTimer(t+6); 
                return t+6;
            }
            return t;
        }       
    }
    public static void main(String[] args) {
        GasStation test = new GasStation();
        test.runDemo();      
    }
}

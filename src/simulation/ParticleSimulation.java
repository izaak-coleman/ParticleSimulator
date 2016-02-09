package simulation;
import java.lang.Math.*;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;

import utils.List;
import utils.MinPriorityQueue;

public class ParticleSimulation implements Runnable, ParticleEventHandler{

    private static final long FRAME_INTERVAL_MILLIS = 40;
    
    private final ParticlesModel          model;
    private final ParticlesView           screen;
    private MinPriorityQueue<Event> queue;
    
    String simName;
    private double ticks;			// current system time
    
    /**
     * Constructor.
     */
    public ParticleSimulation(String name, ParticlesModel m) {
    	simName = name;
    	model = m;
    	Tick t = new Tick(1);
    	ticks = t.time();
    	screen = new ParticlesView(simName, model);
    	queue = new MinPriorityQueue<Event>();
    }

    /**
     * Runs the simulation.
     */
    @Override
    public void run() {
        try {
            SwingUtilities.invokeAndWait(screen);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        Iterable <Collision> unorderedEvents = model.predictAllCollisions(ticks);
        for(Collision c : unorderedEvents) {
        	queue.add(c);
        }
        
        double endTime = Math.ceil(queue.timeOfLastEvent());
        for(int tickTime = 2; tickTime <= endTime; tickTime++){
        	queue.add(new Tick(tickTime));
        }
        
        
        System.out.println("Total number of events: ");
        System.out.print(queue.size()-1);
        //queue.createHeap(unorderedEvents);
        
        while(true){
        	Event nextEvent = queue.remove(); // take min priority event
        	if (nextEvent.isValid() == true){
        		nextEvent.happen(this);
        	}
        }
        	// Event.happen(this);
        	// List newCollisionp1;
        	// List newCollisionp2;
        	// m.(p1, t, newCollisionp1);
        	// m.(p2, t, newCollisionp2);
        	// for (event in collisionlists){
        	// 		queue.add(event)
        	// }
        	// loop ^
    }
    
    public void reactTo(Tick nextTick ){
    	try {
			Thread.sleep(FRAME_INTERVAL_MILLIS);
		} catch (InterruptedException e) {
			// Implement error code if we want 
			e.printStackTrace();
		}
    	screen.update();
    	double lastTime = queue.timeOfLastEvent();
    	Tick newTick = new Tick(Math.ceil(lastTime));
    	queue.add(newTick);
    	ticks = nextTick.time();		// update current time to event
    }
   
    public void reactTo(Collision col){
    	Particle particleArray[] = col.getParticles();
    	
    	if (particleArray.length == 1){
    		Particle.collide(particleArray[0],  col.collisionWall);
    		double ticks = col.time();
    		particleArray[0].move(col.time() - ticks);	
    		Iterable<Collision> newCollisions = model.predictCollisions(particleArray[0], ticks);
    		for(Collision c : newCollisions) {
    			queue.add(c);
    		}
    	}
    	
    	else {
    		Particle.collide(particleArray[0], particleArray[1]);
    		particleArray[0].move(col.time() - ticks);		
    		particleArray[1].move(col.time() - ticks);	
    		Iterable<Collision> newCollisionsP1 = model.predictCollisions(particleArray[0], ticks);
    		Iterable<Collision> newCollisionsP2 = model.predictCollisions(particleArray[1], ticks);
    		for(Collision c : newCollisionsP1) {
    			queue.add(c);
    		}
    		for(Collision c : newCollisionsP2) {
    			queue.add(c);
    		}
    		double ticks = col.time();
    	}
    }
}

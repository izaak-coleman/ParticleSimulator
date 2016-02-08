package simulation;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import utils.MinPriorityQueue;

public class ParticleSimulation implements Runnable, ParticleEventHandler{

    private static final long FRAME_INTERVAL_MILLIS = 40;
    
    private final ParticlesModel          model;
    private final ParticlesView           screen;
    private MinPriorityQueue queue;
    
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
        
        // List unorderedList = m.getAllCollision
        // queue.createHeap(unorderedList);
        
        // Event nextEvent = queue.remove
        // if Event.isValid == True
        	// Event.happen(this);
        	// List newCollisionp1;
        	// List newCollisionp2;
        	// m.(p1, t, newCollisionp1);
        	// m.(p2, t, newCollisionp2);
        	// for (event in collisionlists){
        	// 		queue.add(event)
        	// }
        	// loop ^
       
        // For event in MinPriorityQueue()
        // if Event valid:
        	// Particle particlesInEvent = new event.getParticles;
        	// move particles in event to collision coordinates
        	// update visual display
        	// call reactTo(CurrentEvent) 
        		// Takes in event type and calls collide: particle.collide
        			//particle.collide defines new coordinates post collision
        // regenerate new collision set for each of the two collided particles
        // add just these new collisions to the heap - possibly invalidating later collisions
        // TODO complete implementing this method
    }
    
    public void reactTo(Tick nextTick ){
    	double ticks = nextTick.time();		// update current time to event
    }
   
    public void reactTo(Collision col){
    	Particle particleArray[] = col.getParticles();
    	
    	if (particleArray.length == 1){
    		Particle.collide(particleArray[0],  col.collisionWall);
    	}
    	else {
    		Particle.collide(particleArray[0], particleArray[1]);
    	}

    
    }
}

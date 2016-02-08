package simulation;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import utils.MinPriorityQueue;

public class ParticleSimulation implements Runnable, ParticleEventHandler{

    private static final long FRAME_INTERVAL_MILLIS = 40;
    
    private final ParticlesModel          model;
    private final ParticlesView           screen;
    
    private int ticks;			// current system time
    
    /**
     * Constructor.
     */
    public ParticleSimulation(String name, ParticlesModel m) {
    	// Tick t1 = new Tick(1);		// initialize system time
        // TODO implement constructor
    	
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
       
        // For event in MinPriorityQueue()
        // if Event valid:
        	// Particle particlesInEvent = new event.getParticles;
        	// move particles in event to collision coordinates
        	// update visual display
        	// call reactTo(CurrentEvent) 
        		// Takes in event type and calls collide: particle.collide
        			//particle.collide defines new coordinates post collision
        // regenerate unsorted collision list
        
        
        // TODO complete implementing this method
    }
    
    public void reactTo(Tick nextTick ){
    	double ticks = nextTick.time();		// update current time to event
    }
   
    public void reactTo(Collision col){
    	Particle particleArray[] = col.getParticles();
    	
    	if (particleArray.length() == 1){
    		Particle.collide(particleArray[0],  wall);
    	}
    	else (particleArray.length() == 2){
    		Particle.collide(particleArray[0], particleArray[1]);
    	}
    	// for every particle in collision
    	//	particle.collide();
    	
    }
    
    
}

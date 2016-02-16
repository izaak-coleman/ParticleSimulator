package simulation;

import java.lang.Math;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;

import utils.MinPriorityQueue;

public class ParticleSimulation implements Runnable, ParticleEventHandler{

    private static final long FRAME_INTERVAL_MILLIS = 40;
    
    // ParticleSimulation fields
    private final ParticlesModel          model;
    private final ParticlesView           screen;
    private MinPriorityQueue<Event> queue;
    String simName;
    private double ticks;			// current system time
    private double ticksWindow;		// Max tick in range
    
    /**
     * Constructor.
     */
    public ParticleSimulation(String name, ParticlesModel m) {
    	// initialise fields
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
        
        // generate initial collisions
        Iterable <Collision> unorderedEvents = model.predictAllCollisions(ticks);
        int count = 0;
        
        // add collisions to heap
        for(Collision c : unorderedEvents) {
        	if(count == 0){ ticksWindow = c.time(); }
        	else{
        		if( c.time() > ticksWindow ){
        			ticksWindow = c.time();		// get last tick value
        		}
        	}
        	queue.add(c);
        }
        
        // intersperse ticks
        ticksWindow = Math.ceil(ticksWindow);
       	for(int tickTime = 2; tickTime <= ticksWindow; tickTime++){
        	queue.add(new Tick(tickTime));
        }
       	
       	// run simulation
        while(true){
        	Event nextEvent = queue.remove(); // take min priority event
        	if (nextEvent.isValid() == true){ 
        		// get time delta from next event to system time
        		double absDt = Math.abs(ticks - nextEvent.time());
        		nextEvent.happen(this);
        		for(Particle p : model.getParticles()) {
       				p.move(absDt);
       			}
        		// update system time
        		ticks = nextEvent.time();
        	}
        }
    }
    
    public void reactTo(Tick nextTick ){
    	try {
			Thread.sleep(FRAME_INTERVAL_MILLIS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	screen.update();
    	
    	// extend tick range
    	ticksWindow++;
    	
    	// add a new tick
    	Tick newTick = new Tick(ticksWindow);
    	queue.add(newTick);
    }
   
    public void reactTo(Collision col){
    	Particle particleArray[] = col.getParticles();
    	
    	// collision reaction for particle wall collision
    	if (particleArray.length == 1){
    		Particle.collide(particleArray[0],  col.collisionWall);
    		Iterable<Collision> newCollisions = model.predictCollisions(particleArray[0], ticks);
    		for(Collision c : newCollisions) {
    			queue.add(c);
    		}
    	}    	
    	// collision reaction for particle particle collision
    	else {
    		Particle.collide(particleArray[0], particleArray[1]);
    		Iterable<Collision> newCollisionsP1 = model.predictCollisions(particleArray[0], ticks);
    		Iterable<Collision> newCollisionsP2 = model.predictCollisions(particleArray[1], ticks);
    		for(Collision c : newCollisionsP1) {
    			queue.add(c);
    		}
    		for(Collision c : newCollisionsP2) {
    			queue.add(c);
    		}
    	}
    }
}

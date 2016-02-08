package simulation;

public abstract class Collision extends AbstractEvent implements Event{
	
	Particle [] particles; // how to dimensionalise this array (use ArrayList)
	int [] initialHits;
	Wall collisionWall;

    /**
     * Constructor for Collision
     */
    public Collision(double t, Particle[] ps) { 
    		super(t);
    		particles = ps;
			int [] initialHits = new int[particles.length];
			for(int i =0; i < particles.length; i++){
				initialHits[i] = ps[i].collisions();
			}
    }  
    public Collision(Particle[] ps, Wall w, double t) {
    	super(t);
    	collisionWall = w;
    	particles = ps;
    	
    }


    /**
     * Returns true if this Collision is (still) valid.
     */
    @Override
	public boolean isValid(){
		for(int i = 0; i < particles.length; i++){
			if(particles[i].collisions() != initialHits[i]){
				return false;
			}
		}
		return true;
	}

    

    /**
     * Returns an array containing the Particles involved in this Collision.
     */
    public Particle[] getParticles() {
        return particles;		// returned when event is pulled from list, and particle movement is implemented
    }
}

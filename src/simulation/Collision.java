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
			initialHits = new int[particles.length];
			for(int i =0; i < particles.length; i++){
				initialHits[i] = particles[i].collisions();
			}
    } 
    
    public Collision(Particle[] ps, Wall w, double t) {
    	super(t);
    	collisionWall = w;
    	particles = ps;
    	System.out.println("PWC");
    	System.out.print(particles.length);
		initialHits = new int[particles.length];
		for(int i =0; i < particles.length; i++){
			initialHits[i] = particles[i].collisions();
		}
    }

    /**
     * Returns true if this Collision is (still) valid.
     */
    @Override
	public boolean isValid(){
		for(int i = 0; i < particles.length; i++){
			if(particles[i].collisions() != initialHits[i]){
				System.out.println("Invalid collision ");
				return false;
			}
		}
    	System.out.println("Valid collision ");
		return true;
	}

    

    /**
     * Returns an array containing the Particles involved in this Collision.
     */
    public Particle[] getParticles() {
        return particles;		// returned when event is pulled from list, and particle movement is implemented
    }
}

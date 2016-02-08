package simulation;

public abstract class Collision extends AbstractEvent implements Event{
	
	Particle particles[] = new Particle[]; // how to dimensionalise this array (use ArrayList)
    
    /**
     * Constructor for Collision
     */
    public Collision(double t, Particle[] ps) { 
    	particles = ps;
    }
    
    /** Subclasss
     * public ParticleWallCollision(Wall A, Particle P, double time){
     * 	ParticleList = [P]
     * 	return new Collision(time, ParticleList)
     * }
     * 
     * public TwoParticleCollision(Particle 1, Particle 2, double t){
     * 	ParticleList = [P]	
     * 	return new Collision(time, ParticleList)
     * }
     * 
     * 
     */


    /**
     * Returns true if this Collision is (still) valid.
     */
    @Override
	public boolean isValid(){
    	
	}

    

    /**
     * Returns an array containing the Particles involved in this Collision.
     */
    public Particle[] getParticles() {
        return particles;		// returned when event is pulled from list, and particle movement is implemented
    }
}

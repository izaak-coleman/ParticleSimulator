package simulation

public abstract class Collision extends AbstractEvent implements Event{
	
	Particle [] particles; // how to dimensionalise this array (use ArrayList)
  int [] initialHits;

    /**
     * Constructor for Collision
     */
    public Collision(double t, Particle[] ps) { 
			int [] initialHits = new int[ps.length()];
			for(int i =0; i < particles.length(); i++){
				initialHits[i] = ps[i].hits;
			}
    }  


    /**
     * Returns true if this Collision is (still) valid.
     */
    @Override
	public boolean isValid(){
		for(int i = 0; i < particles.length(); i++){
			if(particles[i] != initialHits[i]){
				return false;
			}
		}
	}

    

    /**
     * Returns an array containing the Particles involved in this Collision.
     */
    public Particle[] getParticles() {
        return particles;		// returned when event is pulled from list, and particle movement is implemented
    }
}

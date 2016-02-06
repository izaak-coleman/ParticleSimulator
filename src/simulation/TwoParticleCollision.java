package simulation;

public class TwoParticleCollision extends Collision implements Event {


	public TwoParticleCollision( Particle p1, Particle p2, double t) {
		Particle ps[] = new Particle[2];		// make final ?
		
		super(t, ps);							// constructor
	}
	
	public void happen(ParticleEventHandler sim){
		sim.reactTo(this);
		
	}
}

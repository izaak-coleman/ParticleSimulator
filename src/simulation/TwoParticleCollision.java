package simulation;

public class TwoParticleCollision extends Collision implements Event {

	public TwoParticleCollision( Particle p1, Particle p2, double t) {
		super(t, returnParticles(p1, p2));
	}
	
	private static Particle [] returnParticles(Particle p1, Particle p2){
		Particle ps[] = new Particle[2];
		ps[0] = p1;
		ps[1] = p2;
		return ps;
	}
	
	// return array of particles involved in collision to Collision constructor
	public void happen(ParticleEventHandler sim){
		sim.reactTo(this);
	}
}

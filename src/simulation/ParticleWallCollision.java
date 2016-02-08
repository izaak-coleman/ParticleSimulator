package simulation;

public class ParticleWallCollision extends Collision implements Event {

	public ParticleWallCollision(Particle p1, Wall wall, double t) {
		super(returnParticle(p1), wall, t); 
	}

	@Override
	public void happen(ParticleEventHandler h) {
	}
	
	private static Particle [] returnParticle(Particle p1) {
		Particle ps[] = new Particle[1];
		ps[0] = p1;
		return ps;
	}

}

package simulation;

public class Tick extends AbstractEvent implements Event{

	public Tick(double time) {
		super(time); 		// AbstractEvent()
	}
	@Override
	public boolean isValid(){
		return true;
	}
	
	public void happen(ParticleEventHandler sim){
		sim.reactTo(this);
	}
}

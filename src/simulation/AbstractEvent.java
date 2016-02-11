package simulation;

public abstract class AbstractEvent implements Event {

	static final int OCCURS_BEFORE = -1;
	static final int OCCURS_AFTER = 1;
	static final int SIMULTANEOUS = 0; 

	double time;

    /**
     * Constructor for AbstractEvent.
     */
    public AbstractEvent(double time) {
				this.time = time;	
    }

    /**
     * Returns the time (in ticks) at which this event will occur.
     */
    @Override
    public double time() {
        return time;
    }

    /**
     * Compares this object with the specified Event.
     */
    @Override
    public int compareTo(Event that) {
		if(time() < that.time()){
			return OCCURS_BEFORE;
		} else if (time() > that.time()){
			return OCCURS_AFTER;
		}
		return SIMULTANEOUS;	
    }

}

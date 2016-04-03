package ai.worlds.vacuumagent;

import ai.worlds.Action;
import ai.worlds.vacuumbase.VacuumAgent;

/**
 * A vacuum agent that simply chooses actions at random
 * @author Jill Zimmerman -- jill.zimmerman@goucher.edu
 *
 */

public class RandomVacuumAgent extends VacuumAgent
{   
	/**
	 * Determine the next action to be performed.
	 */
    public Action getAction() {
    	
	int i = (int)Math.floor(Math.random()*5);
	switch (i) {
	    case 0: return Action.GO_FORWARD;
	    case 1: return Action.TURN_RIGHT;
	    case 2: return Action.TURN_LEFT; 
	    case 3: return Action.SHUT_OFF; 
	    default: return Action.SUCK;
	    
	}
    }
}
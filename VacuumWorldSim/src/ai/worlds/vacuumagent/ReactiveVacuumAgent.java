package ai.worlds.vacuumagent;

import ai.worlds.Action;
import ai.worlds.vacuumbase.VacuumAgent;

/**
 * A vacuum agent which reacts to its percepts.
 * @author Jill Zimmerman -- jill.zimmerman@goucher.edu
 *
 */
public class ReactiveVacuumAgent extends VacuumAgent
{
	/**
	 * Determine the next action to be performed.
	 */
    public Action getAction()
    {
	if (sensor.seesDirt()) action = "suck";
	else if (sensor.isHome()) {
	    int i = (int)Math.floor(Math.random()*3);
	    switch (i) {
		case 0: return Action.SHUT_OFF;
		case 1: return Action.GO_FORWARD;
		case 2: return Action.TURN_LEFT;
	    }
	}
	else if (sensor.bumped()) {
	    int i = (int)Math.floor(Math.random()*2);
	    switch (i) {
		case 0: return Action.TURN_RIGHT;
		case 1: return Action.TURN_LEFT;
	    }	
	}
	else {
	    int i = (int)Math.floor(Math.random()*5);
	    switch (i) {
		case 0: return Action.GO_FORWARD;
		case 1: return Action.GO_FORWARD;
		case 2: return Action.GO_FORWARD;
		case 3: return Action.TURN_RIGHT;
		case 4: return Action.TURN_LEFT; 
	    }
	}
	return  Action.SHUT_OFF;
    }
}
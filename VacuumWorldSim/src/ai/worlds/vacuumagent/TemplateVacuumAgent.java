package ai.worlds.vacuumagent;

import ai.worlds.Action;
import ai.worlds.vacuumbase.VacuumAgent;

/**
 * @author André Marcos
 */
public class TemplateVacuumAgent extends VacuumAgent {

	 /*
	  *  Declare any variable and structure to state and memory strategy
	  **/
	Action action;
	
	@Override
	public Action getAction() {
		
		/* This method can use these possible sensors:
		 *	sensor.bumped(), sensor.isHome(), and  sensor.seesDirt()
		 * 
	     *  and can setup the bellow action according to given strategy:
	     *  Action.SUCK, Action.TURN_RIGHT or Action.TURN_LEFT;  
		 *  Action.GO_FORWARD; and tion.SHUT_OFF;
		 *  
	     *  The Sensor permit the possible state values to determine the agents 
	     *  next action by Action entity. For more information get the project 
	     *  description and documentation.
	     *  
		 *  Implement bellow the agent strategy
		 **/

		if (sensor.seesDirt()) 
			action = Action.SUCK;
		else 
			if (sensor.bumped())
				action = Action.SHUT_OFF;
			else 
				action = Action.GO_FORWARD;

		
		return action;
	}
}

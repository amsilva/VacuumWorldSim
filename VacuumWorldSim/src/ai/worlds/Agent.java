package ai.worlds;

import java.util.Vector;


/**
 * A generic agent.
 * All agents must implement determineAction and takeAction methods
 * @author Jill Zimmerman -- jill.zimmerman@goucher.edu
 *
 */

public abstract class Agent
{
	/**
	 * The body of the agent
	 */
	public  AgentBody body;	
	/**
	* The current score
	*/
	public  int score;	
	/**
	* The current percept
	*/
	protected  Object percept; 
	/**
	* The current action
	*/
	protected  String action;	
	
	public Sensor sensor = new Sensor();
	
	public Agent()
	{
		body = new AgentBody();
		score = 0;
	}

			
	/**
	 * Determines the next action to be taken by the agent.
	 * The action is stored as a string in the field 'action'.
	 */
	public void determineAction(){
		
		action = getAction().getDescription();
		
	}
	
	public abstract Action getAction();

	/**
	 * Perfom the current action.
	 * @param e is the environment the agent is within
	 */
	public abstract void takeAction(Environment e);
	

	
	
	
	
	/**
	 * A inner class Sensor. To a sensor attribute of a Agent
	 * Improving a communication interface to a new agents 
	 * @author André Marcos
	 *
	 */

	public class Sensor {

		/**
		 * A Sensor tells whether or not the vacuum just bumped into a wall.
		 */
		public boolean bumped() {

			return ((Vector<?>) percept).elementAt(0) == "bump";
		}

		/**
		 * A Sensor tells whether or not the current square is dirty.
		 */
		public boolean seesDirt() {

			return ((Vector<?>) percept).elementAt(1) == "dirt";
		}

		/**
		 * A Sensor tells whether or not the current square is the home square..
		 */
		public boolean isHome() {

			return ((Vector<?>) percept).elementAt(2) == "home";
		}

	}
	
	
	
	
}






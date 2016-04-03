package ai.worlds;

public enum Action {

	SUCK ("suck"),
	GO_FORWARD ("forward"),
	TURN_RIGHT ("turn right"),
	TURN_LEFT ("turn left"),
	SHUT_OFF ("shut-off");
	
	
	private String descr;
	
	private Action (String d){
		this.descr = d;
	}
	
	public String getDescription(){
		return descr;
	}

}

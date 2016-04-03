package principal;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import ai.worlds.WorldCreatePanel;
/**
 * The main class for the CS340 AI software package
 * @author Jill Zimmerman -- jill.zimmerman@goucher.edu
 *
 */


public class AI
{
    public static void main(String[] args)
    {
	try {
	    UIManager.setLookAndFeel("WindowsLookAndFeel");//MetalLookAndFeel
	} catch (Exception e) {}
	
	JFrame f = new JFrame("Vacuum World Simulated Environment");
	f.setSize(1024, 650); //(1024, 750)
	f.setResizable(false);
	f.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
		System.exit(0);
	    }
	});
	f.getContentPane().setLayout(new BorderLayout());
	f.getContentPane().add(new AIPanel(f) , BorderLayout.NORTH);
	f.setVisible(true); 
    }
}

class AIPanel extends JPanel {
	public static final Color buttonColor = MetalLookAndFeel.getTextHighlightColor();
	private JFrame holder;
	public JTabbedPane centerPanel = new JTabbedPane();
	private WorldCreatePanel wcp;
    public AIPanel(JFrame h) {
    	holder = h;
    	wcp = new WorldCreatePanel(holder);
    	JPanel aboutPanel = createAboutPanel();
    	JPanel informationPanel = createInformationPanel();
    	centerPanel.addTab("Agents and Environments", wcp);
    	centerPanel.addTab("Information", informationPanel);
    	centerPanel.addTab("About", aboutPanel);
    	setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
    	holder.getContentPane().add(centerPanel, BorderLayout.CENTER);
    }
    private JPanel createAboutPanel() {
    	JPanel aboutPanel = new JPanel(new BorderLayout());
    	JTextPane aboutText = new JTextPane();
    	SimpleAttributeSet set = new SimpleAttributeSet();
    	StyleConstants.setAlignment(set,StyleConstants.ALIGN_CENTER);
    	aboutText.setParagraphAttributes(set,true);
    	aboutText.setText("Artificial Intelligence Software Package\nfor CS 340 at Goucher College\nWritten by Jill Zimmerman in 2000\nRefactored by Jim Segedy (jim.segedy@gmail.com) in 2006"
    			+ "\n\nVersion modified by andre.marcos@gmail.com (2013);"
    			+ "\nImprovement and simplification interface (disposition and decrease of clicks);"
    			+ "\nFunctional simplification (removal of wumpum world, logic and search methods);"
    			+ "\nImproving the Agent abstract class interface (development of sensors);"
    			+ "\nincludes new bumped sensors(), isHome() and seesDirty();"
    			+ "\nInclude information tab;"
    			+ "\nEnumeration to Action setup;"
				+ "\nProbability select button and"
				+ "\nTemplate class.");
    	//aboutText.setBackground(MetalLookAndFeel.getTextHighlightColor());
    	aboutText.setEditable(false);
		aboutPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
    	aboutPanel.add(aboutText, BorderLayout.CENTER);
    	return aboutPanel;
    }
    private JPanel createInformationPanel() {
    	JPanel informationPanel = new JPanel(new BorderLayout());
    	JTextPane informationText = new JTextPane();
    	SimpleAttributeSet set = new SimpleAttributeSet();
    	StyleConstants.setAlignment(set,StyleConstants.ALIGN_LEFT);
    	informationText.setParagraphAttributes(set,true);
    	informationText.setText(""
    			+ "The Vacuum World Project"
    			+ "\n "
    			+ "\nDesign a more sophisticated agent for the vacuum world. "
    			+ "\nYou may assume that the room the agents are to vacuum is a rectangle with no furniture or other obstacles. "
    			+ "\nYou agent may have memory. That is, the agent may have a \"state\" which can change and remember information from previous steps."
    			+ "\n"
    			+ "\nHere is a template that you can use:"
    			+ "\n "
    			+ "\n// My Vacuum Agent"
    			+ "\npackage ai.worlds.vacuum;"
    			+ "\nimport java.util.*;"
    			+ "\n "
    			+ "\nclass MyVacuumAgent extends VacuumAgent"
    			+ "\n{"
    			+ "\n "
    			+ "\n    // declare any state variables here"
    			+ "\n   "
    			+ "\n    void determineAction() {"
    			+ "\n "
    			+ "\n    /* This method uses the percept (stored in the Vector percept)"
    			+ "\n       and state values to determine the agents next action."
    			+ "\n       The member variable action is assigned a string (either \"suck\","
    			+ "\n       \"forward\", \"turn left\", \"turn right\", or \"shut-off\") to indicate"
    			+ "\n       the agents next action */"
    			+ "\n    }"
    			+ "\n}"
    			+ "\n "
    			+ "\nGive your vacuum agent class your name followed by VacuumAgent, for example, MyCoolVacuumAgent. "
    			+ "\nThe class needs to be contained in file with the same name with the .java extension, for example JillVacuumAgent.java. "
    			+ "\nIn order to test out your agent, you will need to add this file to an Eclipse project which contains all the java files that "
    			+ "\nI have provided and then make a couple of small modifications to one of my files."
    			+ "\nDownload the project files. You will need to use Winzip to extract the files."
    			+ "\nYou then should use Eclipse to import the project. Then compile the files and run the class AI."
    			+ "\n"
    			+ "\nYour new VacuumAgent file should be placed in the ai.worlds.vacuum package. "
    			+ "\nAlso in the ai.worlds package select the file WorldCreatePanel.java and make the following changes to include your agent."
    			+ "\n"
    			+ "\nAdd a string with name of your agent to"
    			+ "\n>> String[] vacuumStrings = {\"Random Vacuum Agent\",\"Reactive Vacuum Agent\"};"
    			+ "\n"
    			+ "\nIn the method createAgent, add an else if clause before the else like"
    			+ "\n>> else if (agentName == \"Jill Vacuum Agent\")"
    			+ "\n>>    return new JillVacuumAgent();"
    			+ "\n "
    			+ "\nYou can compare your agent to the stupid ones provided by using the agent trials. "
    			+ "\nSimply select the agents to be in the trials and run the trials. "
    			+ "\nThe average score of trials on identical environments will be displayed."
    			+ "\nWhen you are satisfied with the intelligence of your vacuum agent, send your source code file to me.  "
    			+ "\nWe will run agent trials in class among all your agents to see who has the best vacuum. The top agent will earn bonus points."
    			+ "");
    	
    	//informationText.setBackground(MetalLookAndFeel.getTextHighlightColor());
    	informationText.setEditable(false);
		informationPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
    	informationPanel.add(informationText, BorderLayout.CENTER);
    	return informationPanel;
    }

}



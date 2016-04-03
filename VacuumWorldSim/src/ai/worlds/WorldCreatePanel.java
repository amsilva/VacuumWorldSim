package ai.worlds; 

/*  The Agents and Environment module panel.  This module allows 
    the creation of both vacuum and wumpus agents.  The worlds can
    be built and viewed or several agents within a world can be
    compared 
*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.metal.MetalLookAndFeel;

import net.miginfocom.swing.MigLayout;

//ams simulating receiving and tests
//import ai.worlds.vacuum.MyZigzagVacuumAgent;
//import ai.worlds.vacuum.LucianoVacuumAgent;

import ai.worlds.vacuumagent.RandomVacuumAgent;
import ai.worlds.vacuumagent.ReactiveVacuumAgent;
import ai.worlds.vacuumagent.TemplateVacuumAgent;
import ai.worlds.vacuumbase.VacuumWorld;





import javax.swing.DefaultComboBoxModel;

/**
 * A Panel containing the information for world creation.
 * @author Jill Zimmerman -- jill.zimmerman@goucher.edu
 *
 */

@SuppressWarnings("serial")
public class WorldCreatePanel extends JPanel implements ActionListener, ItemListener {
	private static final Color metalColor = MetalLookAndFeel.getTextHighlightColor(); 
	
    private JComboBox<String> envs;
    private JComboBox<String> agents;
    private JTextField xsize;
    private JTextField ysize;
    private JList<String> trialAgents;
    JTextField numTrials;
    private JCheckBox randomSizes;
    
    private JPanel northPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JPanel northCenterPanel = new JPanel();
    
    private JScrollPane scroll;
    private JTextArea results;
    private JLabel title;
    private JPanel p = new JPanel();
    //private String s = new String();
    //private String filepath, filename;
    public JButton simWorld = new JButton();
    
    private JComboBox<String> amsCbxAgent;
    private JComboBox<String> amsCbxEnv;
    private JComboBox<String> amsCBoxProbab;
    private JSpinner amsSpinnerX;
    JSpinner amsSpinnerY;
    JButton amsBtnStep;
    JButton amsBtnRun;
    JButton amsBtnBuildEnv;
    JButton amsBtnClear;
    
    GridEnvironment world;
    
    /**
     * The list of worlds.
     */
	String[] worldStrings = {"Vacuum World"};
    /**
     * The list of vacuum agents.
     */
	//insert into vacuumStrings the <NewAgentClassName> presentation text, like "New Agent Class Name" 
    String[] vacuumStrings = {"",
    		"Random Vacuum Agent", 
    		"Reactive Vacuum Agent", 
    		//"ZigzagVacuumAgent", //ams simulating receiving and tests
    		//"LucianoVacuumAgent",
    		"Template Vacuum Agent"};
    
    /**
     * The list of wumpus agents
     */
    
    private GridBagLayout gridbag;
    private GridBagConstraints constraints;
    
    private JFrame holder;
    private final JLabel amsLbEnv = new JLabel("Environment:");
    private JTextField amsTextFieldMaxSteps;
    
    public WorldCreatePanel(JFrame f)
    {
    	holder = f;
    	setLayout(new BorderLayout());
    	//setup NorthPanel
    	envs = new JComboBox<String>(worldStrings);
    	envs.addItemListener(this);
    	agents = new JComboBox<String>(vacuumStrings);
    	xsize = new JTextField("5",5);
    	ysize = new JTextField("5",5);
    	gridbag = new GridBagLayout();
	    constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.BOTH;
		northPanel.setBackground(metalColor);
		northPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		northPanel.setLayout(new BorderLayout());
		
		JPanel northNorthPanel = new JPanel();
		
		constraints.gridwidth = 2;
		constraints.insets = new Insets(4, 35, 4, 35);
    	constraints.gridwidth = GridBagConstraints.REMAINDER;
    	constraints.gridwidth = 1;
    	constraints.insets = new Insets(8, 4, 8, 4);
		northNorthPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		northPanel.add(northNorthPanel, BorderLayout.EAST);
		northNorthPanel.setLayout(new MigLayout("", "[][]", "[][23px][]"));
		
		JLabel lblExecution = new JLabel("Execution Panel");
		northNorthPanel.add(lblExecution, "cell 0 0,alignx center");
		
		amsBtnStep = new JButton("Step");
		northNorthPanel.add(amsBtnStep, "cell 0 1,grow");
		amsBtnStep.addActionListener(this);
		amsBtnStep.setEnabled(false);
		
		
		// New ams panel console
		amsBtnRun = new JButton("Run");
		northNorthPanel.add(amsBtnRun, "cell 0 2,grow");
		amsBtnRun.addActionListener(this);
		amsBtnRun.setEnabled(false);
		simWorld.setEnabled(false);
		northNorthPanel.add(simWorld, "cell 1 2,growx,aligny top");
		simWorld.setHorizontalAlignment(SwingConstants.RIGHT);
		simWorld.setVerticalAlignment(SwingConstants.TOP);
		
		simWorld.setText("Stop");
		simWorld.addActionListener(this);
		
		northCenterPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		northCenterPanel.setPreferredSize(new Dimension(450, 75));
		northPanel.add(northCenterPanel, BorderLayout.CENTER);
		northCenterPanel.setLayout(null);
		amsLbEnv.setBounds(34, 31, 68, 14);
		
		northCenterPanel.add(amsLbEnv);
		
		amsCbxEnv = new JComboBox<String>(worldStrings);
		amsCbxEnv.setEnabled(false);
		amsCbxEnv.setBounds(112, 31, 183, 20);
		northCenterPanel.add(amsCbxEnv);
		
		JLabel amsLblAgent = new JLabel("Agent:");
		amsLblAgent.setBounds(66, 59, 36, 14);
		northCenterPanel.add(amsLblAgent);
		
		amsCbxAgent = new JComboBox<String>(vacuumStrings);
		amsCbxAgent.setToolTipText("tool tip text");
		amsCbxAgent.setBounds(112, 59, 183, 20);
		northCenterPanel.add(amsCbxAgent);
		amsCbxAgent.addActionListener(this);

		
		JLabel amsLblXSize = new JLabel("Size x:");
		amsLblXSize.setBounds(305, 34, 46, 14);
		northCenterPanel.add(amsLblXSize);
		amsCbxAgent.addActionListener(this);
		
		JLabel amsLblMaxSteps = new JLabel("Max Steps:");
		amsLblMaxSteps.setBounds(396, 59, 81, 14); 
		northCenterPanel.add(amsLblMaxSteps);
		
		amsSpinnerX = new JSpinner();
		amsSpinnerX.setBounds(349, 31, 37, 20);
		amsSpinnerX.setValue(5);
		northCenterPanel.add(amsSpinnerX);
		((JSpinner.DefaultEditor)amsSpinnerX.getEditor()).getTextField().addFocusListener(
				new MyFocusAdapter());
		
		
		amsSpinnerY = new JSpinner();
		amsSpinnerY.setBounds(349, 56, 37, 20);
		amsSpinnerY.setValue(5);
		northCenterPanel.add(amsSpinnerY);
		((JSpinner.DefaultEditor)amsSpinnerY.getEditor()).getTextField().addFocusListener(
				new MyFocusAdapter());
		
		amsTextFieldMaxSteps = new JTextField();
		amsTextFieldMaxSteps.setText("100");
		amsTextFieldMaxSteps.setBounds(459, 56, 46, 20);
		northCenterPanel.add(amsTextFieldMaxSteps);
		amsTextFieldMaxSteps.setColumns(10);
		
		JLabel lblConstruction = new JLabel("Construction Panel");
		lblConstruction.setBounds(10, 6, 237, 14);
		northCenterPanel.add(lblConstruction);
		
        amsBtnBuildEnv = new JButton("Build");
		amsBtnBuildEnv.setBounds(515, 31, 100, 23);
		amsBtnBuildEnv.setEnabled(false);
		northCenterPanel.add(amsBtnBuildEnv);
		amsBtnBuildEnv.setVerticalAlignment(SwingConstants.BOTTOM);
		amsBtnBuildEnv.addActionListener(this);
		
		//JButton amsBtnClear = new JButton("Destroy"); //ams reload map 
		amsBtnClear = new JButton("Reload"); //ams reload map temp
		amsBtnClear.setEnabled(false);
		amsBtnClear.setBounds(515, 59, 100, 23);
		northCenterPanel.add(amsBtnClear);
		
		JLabel amsLblYSize = new JLabel("Size y:");
		amsLblYSize.setBounds(305, 59, 46, 14);
		northCenterPanel.add(amsLblYSize);
		
		JLabel lblDirtyProb = new JLabel("Dirty Prob:");
		lblDirtyProb.setBounds(396, 31, 81, 14);
		northCenterPanel.add(lblDirtyProb);
		
		amsCBoxProbab = new JComboBox<String>();
		amsCBoxProbab.setModel(new DefaultComboBoxModel<String>(new String[] {"0", ".25", ".5", "1"}));
		amsCBoxProbab.setSelectedIndex(1);
		amsCBoxProbab.setEnabled(false);
		amsCBoxProbab.setBounds(459, 31, 46, 20);
		northCenterPanel.add(amsCBoxProbab);
		amsBtnClear.addActionListener(this);
    	
	    add(northPanel, BorderLayout.NORTH);
		trialAgents = new JList<String>(vacuumStrings);
		JScrollPane scrollPane = new JScrollPane(trialAgents);
		scrollPane.setPreferredSize(new Dimension(175,95));
		scrollPane.setBackground(metalColor);
		numTrials = new JTextField("10",2);
		randomSizes = new JCheckBox("",false);
		randomSizes.setBackground(metalColor);
		//trials = new JButton("Run Trials");
		//trials.addActionListener(this);
	    
		/**JPanel trialChoicePanel = new JPanel();
		trialChoicePanel.add(new JLabel(" Select Trial Agents: "));
		trialChoicePanel.add(scrollPane);
		trialChoicePanel.setBackground(metalColor);
	    */
		
		gridbag = new GridBagLayout();
	    constraints = new GridBagConstraints();
	    constraints.insets = new Insets(2,8,2,8);
	    constraints.gridwidth = 1;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		gridbag.setConstraints(randomSizes, constraints);
		constraints.gridwidth = 1;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		gridbag.setConstraints(numTrials, constraints);
	    
		p.setBackground(metalColor);
		p.setLayout(new BorderLayout());
		results = new JTextArea();
		results.setEditable(false);
		scroll = new JScrollPane(results);
		scroll.setPreferredSize(new Dimension(250, 75));
		title = new JLabel("Scores:");
		title.setFont(new Font("SansSerif",Font.ITALIC + Font.BOLD,14));
		p.add("North",title);
		p.add("Center",scroll);
		
		gridbag = new GridBagLayout();
	    constraints = new GridBagConstraints();
	    constraints.insets = new Insets(8,8,8,8);
		JLabel trialTitle = new JLabel("Agent Trials",0);
		trialTitle.setFont(new Font("SansSerif", Font.ITALIC+Font.BOLD, 14));
		gridbag.setConstraints(trialTitle, constraints);
		gridbag.setConstraints(p, constraints);
		centerPanel.setPreferredSize(new Dimension(250, 100)); //750, 400
		centerPanel.setBackground(Color.white);
		centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		add(centerPanel, BorderLayout.CENTER);
    }
    
    public class MyFocusAdapter extends FocusAdapter {
		public void focusGained(FocusEvent e) {
			amsBtnClear.setEnabled(false);
		}

		public void focusLost(FocusEvent e){} //focusgained is 
    }	
    
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("Build")) {
			build();
		}
		// if(action.equals("Destroy")) destroy();//simulate(); //ams reload map
		if (action.equals("Reload"))
			reload();

		if (action.equals("Run")) {
			amsBtnStep.setEnabled(false);
			amsBtnRun.setEnabled(false);
			amsBtnBuildEnv.setEnabled(false);
			world.maxSteps = Integer.parseInt(amsTextFieldMaxSteps.getText());
			world.run();
			amsBtnBuildEnv.setEnabled(true);
			amsBtnClear.setEnabled(true);
		}
		if (action.equals("Step")) {
			world.maxSteps = Integer.parseInt(amsTextFieldMaxSteps.getText());
			world.takeStep();
		}
		if (action.equals("comboBoxChanged")) {
			amsBtnBuildEnv.setEnabled(true);
		}

	}
    
    public void simulate() {
    	//buildWorld.setBackground(Color.gray.brighter());
    	simWorld.setBackground(metalColor);
    	northPanel.remove(northCenterPanel);
    	gridbag = new GridBagLayout();
    	constraints = new GridBagConstraints();
		
		gridbag = new GridBagLayout();
		constraints = new GridBagConstraints();
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.insets = new Insets(2,2,2,2);
		northCenterPanel = new JPanel(gridbag);
    	northCenterPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
		northCenterPanel.setPreferredSize(new Dimension(750, 75));
		northCenterPanel.setBackground(metalColor);
		northPanel.add(northCenterPanel, BorderLayout.CENTER);
		northPanel.setVisible(false);
		northPanel.setVisible(true);
		
		remove(centerPanel);
    	centerPanel = new JPanel();
    	centerPanel.setPreferredSize(new Dimension(750, 400));
    	centerPanel.setBackground(Color.white);
    	centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
    	centerPanel.setLayout(new BorderLayout());
    	add(centerPanel);
		centerPanel.setVisible(false);
		centerPanel.setVisible(true);
    }
	
    public void itemStateChanged(ItemEvent e) {
    	String world = (String)amsCbxEnv.getSelectedItem();
    	if (world.equals("Vacuum World")) {
    		changeAgentChoices(vacuumStrings);
    		xsize.setText("8");
    		ysize.setText("8");
    		randomSizes.setSelected(true);
    	}
    }
    
    private void buildBase(boolean newmap) {
    	Agent[] a = new Agent[1];
    	a[0] = createAgent((String)amsCbxAgent.getSelectedItem());
    	
        int x = (int)amsSpinnerX.getValue();
        int y = (int)amsSpinnerY.getValue();

		if (newmap) {
			double probDirty = Double.parseDouble(amsCBoxProbab
					.getSelectedItem().toString());
			world = new VacuumWorld(a, x, y, probDirty, holder);
		} else {
			world = new VacuumWorld(a, x, y, holder);
		}
        	
    		holder.setTitle("Vacuum World Simulated Environment");

    	remove(centerPanel);
    	centerPanel = new JPanel();
    	centerPanel.setPreferredSize(new Dimension(750, 400));
		centerPanel.setBackground(Color.white);
		centerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
    	centerPanel.setLayout(new BorderLayout());
    	JPanel center2 = new JPanel(new BorderLayout());
    	center2.add(world.canvas, BorderLayout.CENTER);
		centerPanel.add(center2, BorderLayout.CENTER);
		centerPanel.add("North",world.gridPanel);
		add(centerPanel);
		setVisible(false);
		setVisible(true);
		amsBtnRun.setEnabled(true);
		amsBtnStep.setEnabled(true);
    } 

    private void build() {
    	buildBase(true); //new world map
    } 
    
    private void reload() {
    	buildBase(false); //reload world map
    } 
    
    @SuppressWarnings("unused")
	@Deprecated
    private void destroy() {
    	amsBtnRun.setEnabled(true);
		amsBtnStep.setEnabled(true);
    	remove(centerPanel);
    }
    
    
    private void changeAgentChoices(String[] agentName)
    // changes the agents listed in the agents and trialAgents
    // lists to the Strings given in the array agentName
    {
	agents.removeAllItems();
	trialAgents.removeAll();
	trialAgents.setListData(agentName);
	for (int i=0; i<agentName.length; i++)
	    agents.addItem(agentName[i]);
	
    }
    
    private Agent createAgent(String agentName) {
    	if      (agentName == "Random Vacuum Agent")   return new RandomVacuumAgent();
    	else if (agentName == "Reactive Vacuum Agent") return new ReactiveVacuumAgent();
    	else if (agentName == "Template Vacuum Agent")   return new TemplateVacuumAgent();
    	//insert here information to new agents like model bellow
    	
    	//else if (agentName == "ZigzagVacuumAgent")   return new MyZigzagVacuumAgent(); //ams simulating receiving and tests
    	//else if (agentName == "LucianoVacuumAgent")   return new LucianoVacuumAgent();
    	//
    	else  return new RandomVacuumAgent();
    }
}

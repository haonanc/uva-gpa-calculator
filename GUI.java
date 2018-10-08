//hc4pa Haonan Chen<Assignment Homework-5>
//With the assumption that UVa provides no class with more than 6 credits.
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class GUI extends JFrame{
	
	//fields
	private ArrayList<Course> listClass;
	private JList jListClass;
	private DefaultListModel<String> listModel;

	private JFrame guiFrame;

	private JButton addClass;
	private JButton addBlank;
	private JButton secondGoBack;
	private JButton goToAdd;
	private JButton removeButton;
	private JButton removeAllButton;
	private JButton nextButton;
	private JButton reportButton;
	private JButton goBackButton;

	private JComboBox<String> grade;
	private JComboBox<String> hours;
	private JComboBox<String> status;

	private JLabel labelName;
	private JLabel labelGrade;
	private JLabel labelStatus;
	private JLabel labelHours;
	private JLabel instruction;
	private JLabel intro;
	private JLabel labelTarget;
	private JLabel labelCurrent;
	private JLabel labelReport;
	private JLabel labelTitle;

	private JPanel comboPanelGradeStatus;
	private JPanel buttonsPanel;
	private JPanel buttonsPanel2;
	private JPanel comboPanelNameHours;
	private JPanel ThirdPanel;
	private JTextField classText;
	private JTextField gpaText;
	

	public static final String[] gradeScale ={"Blank","A+","A","A-","B+","B","B-","C+","C","C-","D+","D","D-","F"};
	public static final String[] hoursScale = {"Select your credit hours","6","5","4","3","2","1","0"};
	public static final String[] classList ={};
	public static final String[] classStatus ={"N/A","Past","Current","Anticipanted"};
	public static String text1 = "<html>"+"Instruction: Click 'Add' when you finish adding. Tip: 'Quick Add' will add a 15-credit blank class "+"</html>";
	public static String text2 = "<html>"+"Leaving course name blank is strongly discouraged"+"</html>";
	public static void main(String[] args) {
		new GUI();

	}

	public GUI(){
		Initialization();
	}
	
	public double calculating(double target) {
		double count = 0;
		double sum = 0;
		if(target == -1.0) {
			for(Course c:this.listClass) {
				if(c.getGrade().equals("Blank") != true) {
					sum += c.getHours()*c.getGradeInInt();
					count += c.getHours();
				}
			}
			double value = Math.round (sum/count * 100.0) / 100.0; 
			return value;
		}
		else {
			double countBlank = 0;
			double value = 0;
			for(Course c:this.listClass) {
				if(c.getGrade().equals("Blank") != true) {
					sum += c.getHours()*c.getGradeInInt();
					count += c.getHours();
				}
				else {
					countBlank += c.getHours();
				}
			}
			if(countBlank == 0) {
				return -1.0;
			}
			value =( target*(countBlank + count) - sum) / countBlank;
			value = Math.round (value * 100.0) / 100.0;
			return value;
		}
		
		
	}

	public void Initialization() {
		//Basic
		Course c1 = new Course(10,"A+","CS2110","This is a sample course");//Test
		guiFrame = new JFrame();
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("GPA Calculator");
		guiFrame.setSize(400,300);
		guiFrame.setLocationRelativeTo(null);

		//J Class and ScrollPane
		listClass = new ArrayList<Course>();
		listClass.add(c1);
		listModel = new DefaultListModel<String>();
		listModel.addElement(listClass.get(0).toString());
		jListClass = new JList(listModel); //data has type Object[]
		jListClass.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jListClass.setLayoutOrientation(JList.VERTICAL);
		jListClass.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(jListClass);
		listScroller.setPreferredSize(new Dimension(250, 80));

		//End

		//Buttons
		buttonsPanel = new JPanel();
		buttonsPanel2 = new JPanel();
		buttonsPanel2.setLayout(new BoxLayout(buttonsPanel2, BoxLayout.PAGE_AXIS));
		addClass = new JButton("Add Class");
		addBlank = new JButton("Quick Add");
		secondGoBack = new JButton("Back");
		goToAdd = new JButton("Add Course");
		removeButton = new JButton("Remove");
		removeAllButton = new JButton("Remove All");
		nextButton = new JButton("Next");
		reportButton = new JButton("Report");
		goBackButton = new JButton("Back");
		buttonsPanel.add(addClass);
		buttonsPanel.add(addBlank);
		buttonsPanel.add(secondGoBack);
		buttonsPanel2.add(goToAdd);
		buttonsPanel2.add(removeButton);
		buttonsPanel2.add(removeAllButton);
		buttonsPanel2.add(nextButton);
		removeButton.setEnabled(false);
		removeAllButton.setEnabled(false);
		//End 

		//TextFields
		classText = new JTextField(8);
		gpaText = new JTextField(8);
		//End

		// Label Section
		labelName = new JLabel("Class Name");
		labelHours = new JLabel("Credit Hours(Required):");
		labelGrade = new JLabel("Your Grade:");
		labelStatus = new JLabel("Class Status:");
		labelTarget = new JLabel("Your Target GPA:");
		labelCurrent = new JLabel("Your Current GPA:");
		labelTitle = new JLabel("");
		labelReport = new JLabel("<html>"+"After you enter your target GPA, you will see the GPA you required on your rest of future and blanks courses to reach your target GPA"+"</html>");
		labelReport.setBorder(new LineBorder(Color.BLACK));
		labelReport.setPreferredSize(new Dimension(350, 200));
		instruction = new JLabel(text1);
		instruction.setBorder(new LineBorder(Color.BLACK));
		instruction.setPreferredSize(new Dimension(200, 100));
		intro = new JLabel("<html>"+ "Welcome to Super GPA Calculator! Begin adding class by click 'Add course', click 'Next' when you finish"+"</html>");
		intro.setBorder(new LineBorder(Color.BLACK));
		intro.setPreferredSize(new Dimension(300, 120));

		//End

		//Panels
		comboPanelGradeStatus = new JPanel();
		comboPanelGradeStatus.setLayout(new BoxLayout(comboPanelGradeStatus, BoxLayout.Y_AXIS));
		comboPanelNameHours = new JPanel();
		comboPanelNameHours.setLayout(new BoxLayout(comboPanelNameHours, BoxLayout.Y_AXIS));
		ThirdPanel = new JPanel();
		ThirdPanel.setLayout(new BoxLayout(ThirdPanel, BoxLayout.Y_AXIS));
		//End
		
		//JComboBox
		grade = new JComboBox<String>(gradeScale);
		hours = new JComboBox<String>(hoursScale);
		status = new JComboBox<String>(classStatus);
		comboPanelNameHours.add(labelName);
		comboPanelNameHours.add(classText);
		comboPanelNameHours.add(labelHours);
		comboPanelNameHours.add(hours);
		comboPanelGradeStatus.add(labelGrade);
		comboPanelGradeStatus.add(grade);
		comboPanelGradeStatus.add(labelStatus);
		comboPanelGradeStatus.add(status);
		ThirdPanel.add(goBackButton);
		ThirdPanel.add(labelTitle);
		ThirdPanel.add(labelTarget);
		ThirdPanel.add(gpaText);
		ThirdPanel.add(labelCurrent);
		//End


		//Initialization
		guiFrame.add(listScroller, BorderLayout.WEST);
		guiFrame.add(buttonsPanel2, BorderLayout.EAST);
		guiFrame.add(intro, BorderLayout.CENTER);
		comboPanelNameHours.setVisible(false);
		comboPanelGradeStatus.setVisible(false);
		instruction.setVisible(false);
		buttonsPanel.setVisible(false);
		//End
		
		//The function that leads user to "Add class"
		goToAdd.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				comboPanelNameHours.setVisible(true);
				guiFrame.add(comboPanelNameHours, BorderLayout.WEST);
				guiFrame.add(comboPanelGradeStatus, BorderLayout.CENTER);
				guiFrame.add(instruction, BorderLayout.EAST);
				guiFrame.add(buttonsPanel, BorderLayout.SOUTH);
				comboPanelGradeStatus.setVisible(true);
				instruction.setVisible(true);
				buttonsPanel.setVisible(true);
				listScroller.setVisible(false);
				buttonsPanel2.setVisible(false);
				intro.setVisible(false);
				if(hours.getSelectedIndex() == 0) {
					addClass.setEnabled(false);
				}
			}
		});
		
		//The function for the remove button

		removeButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
			    int index = jListClass.getSelectedIndex();
	            listModel.remove(index);
	            listClass.remove(index);
	            removeButton.setEnabled(false);
	            if(listModel.size() == 0) {
	            	removeAllButton.setEnabled(false);
	            }
			}		
			
		});
		
		//The function for "Remove All"
		removeAllButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				listClass.clear();
				listModel.clear();
				removeAllButton.setEnabled(false);
				removeButton.setEnabled(false);
			}
		});
		
		//The function for add a class
		
		addClass.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				int hour = Integer.parseInt(hoursScale[hours.getSelectedIndex()]);
				String str = classText.getText();
				if(str.equals("")) {
					str = "Blank Course";
				}
				Course newClass = new Course(hour,gradeScale[grade.getSelectedIndex()],str,classStatus[status.getSelectedIndex()]);
				listClass.add(newClass);
				listModel.addElement(newClass.toString());
				hours.setSelectedIndex(0);
				status.setSelectedIndex(0);
				grade.setSelectedIndex(0);
				classText.setText("");
				instruction.setText("Your course has been added.");
				removeAllButton.setEnabled(true);
			}

		});
		
		//The function detects the change in user input for name of class, and notify user when name is blank.

		classText.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				action();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				action();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				action();
			}

			public void action() {
				if(classText.getText().length() == 0) {
					instruction.setText(text2);;
				}
				else {
					instruction.setText(text1);;
				}
			}

		});
		
		//The function that adds blank classes for user 
		addBlank.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				Course newClass = new Course(15,"N/A","Auto-Generated Course","N/A");
				listClass.add(newClass);
				listModel.addElement(newClass.toString());
				instruction.setText("You added a blank course");
				removeAllButton.setEnabled(true);
			}

		});

		//The function that makes sure credit hours is a required field
		hours.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{	if(hours.getSelectedIndex() == 0) {
				addClass.setEnabled(false);
			}
			else {
				addClass.setEnabled(true);
				instruction.setText(text1);
			}
			}
		});


		//The listener detecting the change of selection.
		jListClass.addListSelectionListener(new ListSelectionListener() 
		{

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(jListClass.getSelectedIndex() == -1) {
					removeButton.setEnabled(false);
					intro.setText("<html>"+ "Welcome to Super GPA Calculator! Begin adding class by click 'Add course', click 'Next' when you finish"+"</html>");
				}
				else {
					removeButton.setEnabled(true);
					intro.setText( "<html>"+ listClass.get(jListClass.getSelectedIndex()).fullName()+ "</html>");
				}
					
			}
		});
		
		//The button that leads user to the report 
		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listScroller.setVisible(false);
				buttonsPanel2.setVisible(false);
				intro.setVisible(false);
				addClass.setEnabled(false);
				guiFrame.add(ThirdPanel, BorderLayout.WEST);
				guiFrame.add(labelReport, BorderLayout.CENTER);
				guiFrame.add(reportButton, BorderLayout.SOUTH);
				ThirdPanel.setVisible(true);
				labelReport.setVisible(true);
				reportButton.setVisible(true);
				labelCurrent.setText("Your Current GPA is:"+calculating(-1.0));
				labelReport.setText("<html>"+"After you enter your target GPA, you will see the GPA you required on your rest of future and blanks courses to reach your target GPA"+"</html>");
				
			}
		});
		
		//The report button that does calculation and shows the result
		reportButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				double num;
				try { num = Double.parseDouble(gpaText.getText());
				}
				catch(NumberFormatException e1) {			
					 num = -1;
				}
				if(num <0.0 || num > 4.0) {
					labelReport.setText("Please enter valid GPA value");
				}
				else {
					double value = calculating(num);
					if(value == -1.0) {
						labelReport.setText("<html>"+"You don't have any blank credit hours available. Go back and add more blank courses."+"</html>");	
					}
					else if(value > 4.0) {
						labelReport.setText("<html>"+"Your required GPA is:"+ value +" ,which is practically impossible, so I please add more blank credits hours or lower your target GPA"+"</html>");				
					}
					else if( value < 2.0 && value > 0.0) {
						labelReport.setText("Your required GPA is:"+ value +". You can take less credits");				
					}
					else if( value < 0.0 ) {
						labelReport.setText("<html>"+"Your required GPA is:"+ value +" ,which is practically impossible, so set a higher target GPA!"+"</html>");		
					}
					else {
						labelReport.setText("Your required GPA is:"+ value);
					}	
				}	
				}	
		});

		//The function that leads user back to the main scene.
		goBackButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				ThirdPanel.setVisible(false);
				labelReport.setVisible(false);
				reportButton.setVisible(false);
				listScroller.setVisible(true);
				buttonsPanel2.setVisible(true);
				intro.setVisible(true);
				
			}
		});
		
		//The button that leads user back to the main scene.
		secondGoBack.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent event)
			{
				comboPanelNameHours.setVisible(false);
				comboPanelGradeStatus.setVisible(false);
				instruction.setVisible(false);
				buttonsPanel.setVisible(false);
				listScroller.setVisible(true);
				buttonsPanel2.setVisible(true);
				intro.setVisible(true);
			}
		});


		//make sure the JFrame is visible
		guiFrame.pack();
		guiFrame.setVisible(true); //the JFrame is set to visible
	}



}

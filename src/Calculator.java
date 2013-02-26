/**
 * @Calculator.java - A Java based calculator for simple equations
 *
 *
 * @author Charles Henry
 * @version 1.00 2011/1/29
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

	private JTextField displayJtf;
	private JTextField inputJtf;
	private JTextField historyJtf;
	
	private JButton num0Jb;
	private JButton num1Jb;
	private JButton num2Jb;
	private JButton num3Jb;
	private JButton num4Jb;
	private JButton num5Jb;
	private JButton num6Jb;
	private JButton num7Jb;
	private JButton num8Jb;
	private JButton num9Jb;
	
	private JButton cJb;
	private JButton ceJb;
	private JButton delJb;
	
	private JButton plusJb;
	private JButton subtractJb;
	private JButton multiplyJb;
	private JButton divideJb; 
	private JButton ansJb;
	private JButton decimalJb;
	
	
	private JButton mrJb;
	private JButton mcJb;
	private JButton mPlusJb;
	private JButton mSubJb;
	private JLabel mNot0Jb;
	
	private float mMem=0.0f;			//Used to store a variable for the Memory functions (MR, MC, M+, M-)
	private float mem=0.0f;				//Variable used for processing equations
	private float mem2;					//Variable used for processing equations
	private int lastOp=0;				//Integer used to represent the last operators used where (1='+'),(2='-'),(3='*'),(4='/') and (5='=')
	private boolean newNumber=true;		//Used to show whether the next typed number will overwrite the current input or not
	private boolean newEq=true;			//Boolean used to state when a new equation is 
	private float lastInput;			//Variable used to store the most recent input
	private boolean decUsed=false;		//Boolean used to state when a decimal point has been used in the current input
	


public Calculator() {

		setTitle("Java Calculator");
		setSize(300, 200);
		setResizable(false);

		//Setting the layout
		Container c = getContentPane();							//Gives a name to the content pane of this frame so that it can be accessed
		c.setLayout(new BorderLayout());						//puts a border layout inside the frame (frame 'c' in this case)

		JPanel topGridJp = new JPanel (new GridLayout(3, 1));	//Creates a grid layout called 'topGridJp'
		getContentPane().add(topGridJp, BorderLayout.NORTH);	//adds the JPanel (topGridJp) to the border layout center

		JPanel gridJp = new JPanel (new GridLayout(6, 4));		//Creates a grid layout called 'gridJp'
		getContentPane().add(gridJp, BorderLayout.CENTER);		//adds the JPanel (gridJp) to the border layout center

		Font displayFont = new Font("Calibri", Font.BOLD, 20);	//Creating a font for use on buttons and lables etc

		//Creating and adding elements to the frame
		displayJtf = new JTextField("0");						//Creates a display for the calculator
		displayJtf.setHorizontalAlignment(JTextField.RIGHT);	//Aligns the text in the text field to the right
		displayJtf.setEditable(false);							//Makes the text area display non-editable
		displayJtf.setBackground(Color.white);					//Makes the background colour of the text field white (making displayJtf non-editable caused the bg colour be set to gray)
		displayJtf.setFont(displayFont);						//Set the font the JTextField

		inputJtf = new JTextField("");							//Creates a input display for the calculator
		inputJtf.setHorizontalAlignment(JTextField.RIGHT);		//Aligns the text in the text field to the right
		inputJtf.setEditable(false);							//Makes the text area display non-editable
		inputJtf.setBackground(Color.white);					//Makes the background colour of the text field white

		historyJtf = new JTextField("");						//Creates a history display for the calculator
		historyJtf.setHorizontalAlignment(JTextField.RIGHT);	//Aligns the text in the text field to the right
		historyJtf.setEditable(false);							//Makes the text area display non-editable
		historyJtf.setBackground(Color.white);					//Makes the background colour of the text field white
	
		//creating elements and adding them to the action listener
			num0Jb = new JButton ("0");			//Creating buttons 0 to 9
			num1Jb = new JButton ("1");
			num2Jb = new JButton ("2");
			num3Jb = new JButton ("3");
			num4Jb = new JButton ("4");
			num5Jb = new JButton ("5");
			num6Jb = new JButton ("6");
			num7Jb = new JButton ("7");
			num8Jb = new JButton ("8");
			num9Jb = new JButton ("9");
			num0Jb.addActionListener(this);		//Adding buttons 0 to 9 to the ActionListener
			num1Jb.addActionListener(this);
			num2Jb.addActionListener(this);
			num3Jb.addActionListener(this);
			num4Jb.addActionListener(this);
			num5Jb.addActionListener(this);
			num6Jb.addActionListener(this);
			num7Jb.addActionListener(this);
			num8Jb.addActionListener(this);
			num9Jb.addActionListener(this);
	
			plusJb 		= new JButton ("+");	//Creating the operator and decimal point buttons
			subtractJb 	= new JButton ("-");
			multiplyJb  = new JButton ("*");
			divideJb 	= new JButton ("/");
			ansJb 		= new JButton ("=");
			decimalJb 	= new JButton (".");
			plusJb.addActionListener(this);		//Adding the operator and decimal point buttons to the ActionListener
			subtractJb.addActionListener(this);
			multiplyJb.addActionListener(this);
			divideJb.addActionListener(this);
			ansJb.addActionListener(this);
			decimalJb.addActionListener(this);
	
			delJb	= new JButton ("<-");		//Creating the backspace and clear buttons
			cJb 	= new JButton ("C");
			ceJb	= new JButton ("CE");
			delJb.addActionListener(this);		//Adding the backspace and clear buttons to the ActionListener
			cJb.addActionListener(this);
			ceJb.addActionListener(this);
			
			mcJb 	= new JButton ("MC");		//Creating the memory buttons
			mrJb 	= new JButton ("MR");
			mPlusJb = new JButton ("M+");
			mSubJb  = new JButton ("M-");
			mcJb.addActionListener(this);		//Adding the memory buttons to the ActionListener
			mrJb.addActionListener(this);
			mPlusJb.addActionListener(this);
			mSubJb.addActionListener(this);
	
			mNot0Jb  = new JLabel ("");			//Creating a label for the memory usage display
			mNot0Jb.setHorizontalAlignment(JTextField.CENTER); //Aligns the text in the text field to the centre
			
		//End of creating elements and adding them to the action listener
	
		//Adding elements to the frame
			topGridJp.add(historyJtf);
			topGridJp.add(inputJtf);
			topGridJp.add(displayJtf);

			gridJp.add(mcJb);    	gridJp.add(mrJb); 	gridJp.add(mPlusJb);  gridJp.add(mSubJb);
			gridJp.add(delJb);   	gridJp.add(ceJb); 	gridJp.add(cJb);      gridJp.add(mNot0Jb);
			gridJp.add(num7Jb);  	gridJp.add(num8Jb); gridJp.add(num9Jb);   gridJp.add(divideJb);
			gridJp.add(num4Jb);  	gridJp.add(num5Jb); gridJp.add(num6Jb);   gridJp.add(multiplyJb);
			gridJp.add(num1Jb);  	gridJp.add(num2Jb); gridJp.add(num3Jb);   gridJp.add(subtractJb);
			gridJp.add(decimalJb);  gridJp.add(num0Jb); gridJp.add(ansJb);	  gridJp.add(plusJb);
		//End of creating and adding elements to the frame

		pack();										//Makes the frame as small as it can without cutting off any of the contents inside
		setVisible(true);							//Makes the frame visible to the user (default = false)
		setDefaultCloseOperation(EXIT_ON_CLOSE);	//Makes the program terminate properly when the close button is pressed
    }

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == num0Jb)
		{
			addToDisplay("0");
		}
		else if(e.getSource() == num1Jb)
		{
			addToDisplay("1");
		}
		else if(e.getSource() == num2Jb)
		{
			addToDisplay("2");
		}
		else if(e.getSource() == num3Jb)
		{
			addToDisplay("3");
		}
		else if(e.getSource() == num4Jb)
		{
			addToDisplay("4");
		}
		else if(e.getSource() == num5Jb)
		{
			addToDisplay("5");
		}
		else if(e.getSource() == num6Jb)
		{
			addToDisplay("6");
		}
		else if(e.getSource() == num7Jb)
		{
			addToDisplay("7");
		}
		else if(e.getSource() == num8Jb)
		{
			addToDisplay("8");
		}
		else if(e.getSource() == num9Jb)
		{
			addToDisplay("9");
		}
		
		else if(e.getSource() == ceJb) 			//Clear exsisting button clears the current input
		{
			displayJtf.setText("0");			//Sets the current input to 0
			decUsed=false;						//Sets the 'decUsed' boolean to false as there are now no decimal points in the display
		}
		else if(e.getSource() == cJb)
		{
			displayJtf.setText("0");			//Sets the current input to 0
			inputJtf.setText("");				//Clears the input history
			mem = 0;							//Clears any values in memory (eg if half an equation was performed there would be a vaule in mem)
			decUsed=false;						//Sets the 'decUsed' boolean to false as there are now no decimal points in the display
			newEq=true;
		}
		
		else if(e.getSource() == decimalJb)		//Decimal point button inputs a decimal point into the current input
		{
			if(decUsed)
			{
				//If a decimal point has already been used, the decimal point button will not do anything
			}
			else
			{
				displayJtf.setText(displayJtf.getText() + ".");	//Add a decimal point to the current input
				decUsed=true;									//Set the decUsed boolean to true as a decimal point has been used
			}
		}
		
		else if(e.getSource() == delJb)		//Delete button remove the trailing number from the current input/output
		{
			if(displayJtf.getText().equals("0") || displayJtf.getText().length() == 1)						//If the current display is already zero, or is one digit in length...
			{
				displayJtf.setText("0");																	//Set the current display to '0'
			}
			else
			{
				displayJtf.setText(displayJtf.getText().substring(0,displayJtf.getText().length() - 1));	//Remove the trailing digit from the current input
			}
			
			if(displayJtf.getText().indexOf(".") >= 1)		//If there is a decimal point in the current input...
			{
										//Do nothing where there is a decimal point in the input
			}
			else
			{
				decUsed=false;								//Set decUsed to false if there is no decimal point in the current input
			}
		}
		
		
		else if(e.getSource() == mrJb)		//Memory recall button moves the value from memory variable mMem into the current input
		{
			displayJtf.setText(String.valueOf(mMem));			//Set the current display to whatever is in the mMem variable
			mMemDisplay();										//Makes sure "M" is displayed if memory is not 0
		}
		else if(e.getSource() == mcJb) 	//Memory clear button clears memory by setting it to '0'
		{
			mMem=0.0f;					//Set memory (mMem) to '0'
			mNot0Jb.setText("");		//Sets the memory usage label to "" as memory is now empty
		}
		else if(e.getSource() == mPlusJb)
		{
			mMem= mMem + Float.parseFloat(displayJtf.getText());	//Adds the current display to the memory contents
			
			displayJtf.setText("0");								//Clears the current input so that he user can start a new input
			decUsed=false;											//Sets the 'decUsed' boolean to false as there are now no decimal points in the display
			mMemDisplay();											//Makes sure "M" is displayed if memory is not 0
		}
		else if(e.getSource() == mSubJb)
		{
			mMem= mMem - Float.parseFloat(displayJtf.getText());	//Subtracts the current display from the memory contents
			
			displayJtf.setText("0");								//Clears the current input so that he user can start a new input
			decUsed=false;											//Sets the 'decUsed' boolean to false as there are now no decimal points in the display
			mMemDisplay();											//Makes sure "M" is displayed if memory is not 0
		}
		
		
		else if(e.getSource() == plusJb)								//Plus operator (represented as 1)
		{	
			firstFormular(1);												//Calls the formular with variable '1' for '+'
			inputJtf.setText(inputJtf.getText() + lastInput + " + ");		//Adds the current equation to the input display
		}
		else if(e.getSource() == subtractJb)							//Subtract operator (represented as 2)
		{
			firstFormular(2);												//Calls the formular with variable '2' for '-'
			inputJtf.setText(inputJtf.getText() + lastInput + " - ");		//Adds the current equation to the input display
		}
		else if(e.getSource() == multiplyJb)							//Multiply operator (represented as 3)
		{
			firstFormular(3);												//Calls the formular with variable '3' for '*'
			inputJtf.setText(inputJtf.getText() + lastInput + " x ");		//Adds the current equation to the input display
		}
		else if(e.getSource() == divideJb)								//Divide operator (represented as 4)
		{
			firstFormular(4);												//Calls the formular with variable '4' for '/'
			inputJtf.setText(inputJtf.getText() + lastInput + " / ");		//Adds the current equation to the input display
		}
		else if(e.getSource() == ansJb)									//Equals operator (represented as 5)
		{
			firstFormular(5);												//Calls the formular with variable '5' for '='
			newEq=true;														//Sets the boolean 'newEq' to true so the user can perform a new equaton after pressing the '=' operator
			historyJtf.setText(inputJtf.getText() + lastInput + " = " + displayJtf.getText());	//Adds the current equation to the history display
			inputJtf.setText("");											//Clears the current equation input from inputJtf
		}
	}
    
	/**
	 * Adds a digit to the input/output display
	 * 
	 * @param digit A variable of type String. This variable is the number to be inputted into the display
	 */
	public void addToDisplay(String digit)
	{
		if(displayJtf.getText().equals("0") || newNumber == true )
		{
			displayJtf.setText(digit);
			newNumber=false;
		}
		else
		{
			displayJtf.setText(displayJtf.getText() + digit);
		}
	}
	
	/**
	 * Makes sure "M" is displayed if memory is not 0
	 */
	public void mMemDisplay()
	{
		if(mMem == 0)
		{
			mNot0Jb.setText("");
		}
		else
		{
			mNot0Jb.setText("M");
		}
	}
	
	/**
	 * Method used to perform calculations
	 * @param op Number used to represent an operator (1='+'),(2='-'),(3='*'),(4='/') and (5='=')
	 */
	public void firstFormular(int op)
	{
		lastInput=Float.parseFloat(displayJtf.getText());				//Sets the last input to whatever is in the display
		displayJtf.setText(String.valueOf(finalFormular(lastOp)));		//Calls the method 'finalFormular' with input parameter 'lastOp'
		lastOp = op;													//Sets 'lastOp' to the current operator 'op' where (1='+'),(2='-'),(3='*'),(4='/') and (5='=')
		newNumber=true;													//Sets the boolean 'newNumber' to true so that the user can overwrite the previous number
	}
	
	/**
	 * Method used to perform calculations
	 * @param op Number used to represent an operator (1='+'),(2='-'),(3='*'),(4='/') and (5='=')
	 * @return
	 */
	public float finalFormular(int op)		//Formular used by all operators
	{
		if(op == 1)		//If 1 is passed into the method (representing the '+' operator)
		{
			mem = (mem + Float.parseFloat(displayJtf.getText()));
		}
		else if(op == 2)	//If 2 is passed into the method (representing the '-' operator)
		{
			mem = (mem - Float.parseFloat(displayJtf.getText()));
		}
		else if(op == 3)
		{
			mem2 = Float.parseFloat(displayJtf.getText());
			mem = (mem * mem2);
		}
		else if(op == 4)
		{
			mem2 = Float.parseFloat(displayJtf.getText());
			mem = (mem / mem2);
		}
		else if(op == 5)
		{
			if(newEq)
			{
				if(lastOp == 1)
				{
					mem = mem + lastInput;
				}
				if(lastOp == 2)
				{
					mem = mem - lastInput;
				}
				if(lastOp == 3)
				{
					mem = mem * lastInput;
				}
				if(lastOp == 4)
				{
					mem = mem / lastInput;
				}
				else
				{
					mem = lastInput;
				}
			}                
			else
			{      
				lastInput=Float.parseFloat(displayJtf.getText());
				displayJtf.setText(String.valueOf(mem));
				mem = 0;
			}
			lastOp=0;
		}
		else
		{
			mem = Float.parseFloat(displayJtf.getText());
		}
		decUsed=false;
		return mem;
	}
   

}
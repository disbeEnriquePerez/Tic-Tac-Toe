import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class TicTacToe extends JFrame{
	int WinnerX = 0, WinnerO = 0 ;
	JButton Reset, NewGame;
	JPanel Panel;
	JTextField Field; 
	String[] TIC;
	boolean EmptySpace[];
	JLabel[] Display;
	JLabel Score;
		// XorY is for which player just placed a Tic
	boolean XorY ;
	double LocationX, LocationY;
	String show;
	public static void main(String[] args){
		//creates a TicTacToe object 
		new TicTacToe();
	}	
	public TicTacToe(){
		setupFrame();
		SetUpGame();
		this.setLayout(new GridLayout(4,3));
		/* 
		 reference: the Tic Tac toe game field will be as following 
		 
		 		|-----------------------|
		 		|	0	|	1	|	2	|
		 		|-------|-------|-------|
		 		|	3	|	4	|	5	|
		 		|-------|-------|-------|
		 		|	6	|	7	|	8	|
		 		|-------|-------|-------|
		 		|	9	|	10	|	11	|
		 		|-------|-------|-------|
		 		
		 		where 9 will be the reset button 10 will show the score and 
		 		11 will be the new game button 
	 
		 */
			
		CreateLabels();
		addLabels();
		setVisible(true);
	}	

	public void addLabels(){
		// we add the Score label to the Panel first followed by the TextField 
		Panel.add(Score);
		Panel.add(Field);
		// we then set the whole TIC array to null ; 
		for (int nul = 0 ; nul < TIC.length ; ++ nul){
			TIC[nul] = null;
		}
		// we then set the array  of JLabels that were null and now create an instance of them
		// 9 to be exact 
		for(int num = 0 ; num < Display.length ; ++num){
			Display[num] = new JLabel();
		}
		//we first add 9 JLabels to the JFrame which fills up 0 - 8 of the reference table in main;
		for(int num = 0 ; num <Display.length ; ++num){
			this.add(Display[num]);
		}
		// Reset takes up space 9, Panel that contains Score and the TextField takes space 10
		// and the NewGame button takes space 11 
		this.add(Reset);
		this.add(Panel);
		this.add(NewGame);
		Reset.addActionListener(new ButtonListener());
		NewGame.addActionListener(new ButtonListener());
	
		addMouseListener(new MyMouseListener());
		addMouseMotionListener(new MyMouseMotionListener());

	}
	public void CreateLabels(){
		// set up the panel and button objects that will be added to the Frame
		Panel = new JPanel();
		Reset = new JButton("Reset");
		NewGame = new JButton("New Game");
		// the Field will be placed in box 10 in the reference: 
		Field = new JTextField(10);
		// the TextField will be used to place the Score of the game 
		Field.setFont(new Font("Serif", SwingConstants.CENTER, 16));
		Field.setText("           " + WinnerX + " : " + WinnerO);
		Score = new JLabel("Score X vs O");
		
		
		// Labels array is an array of labels that will be added to the panel 
		// Labels will display the Tic on the screen 
		Display = new JLabel[9];
		// Numbers array of String will just correlates to Labels and will take the String tic type 
		// and will be used to find if there has been a 3 in a row... 
		TIC= new String[9];
		
	}
	public void SetUpGame(){
		// sets who's turn is it.. false means that it is "X" that places a tic 
		// true is when it is "O" turn to place a tic
		XorY = false;
		
		// the boolean array shows if the space has been taken or not.. when it's true it is open to place a tic  
		EmptySpace = new boolean[9];
		for( int num = 0 ; num < 9 ; ++num){
			EmptySpace[num] = true;
		}
	}
	public void setupFrame(){
		// sets up the basic components of the frame 
		this.setSize(400, 400); 
		this.setTitle("Tic Tac Toe");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
	}

	public void Reset(){
		// sets the first tic to be an "X" 
		XorY = false;
		// reset all of the arrays 
		for( int num = 0 ; num < 9 ; ++num){
			EmptySpace[num] = true;
		}
		for(int num = 0 ; num < Display.length ; ++num){
			Display[num].setText("");
		}
		for (int num = 0 ; num < TIC.length ; ++num){
			TIC[num] = null; 
		}
	}
	//Called when Button even occurs
	public void NewGame(){
		//call the Reset function because it resets the arrays and sets the first tic back to 
		// "X"
		Reset();
		// Now reset the scores 
		WinnerX = 0 ; 
		WinnerO = 0;
		Field.setText("           " + WinnerX + " : " + WinnerO);
	}
	
	// place is the index of the array Labels and Places a tic at that location 
	public void Label(int place){
		// player just placed a tic first player is always "X" then it switches to "O" 
		if(XorY == false){
			show = "X";
			XorY = true;
		}
		else{
			show = "O";
			XorY = false;	
		}
		// places a tic at the specific Label 
		Display[place].setFont(new Font("Serif", Font.BOLD + SwingConstants.CENTER, 110));
		Display[place].setText(show);
		// the array index which correlates to labels is now False indicating it is taken 
		EmptySpace[place] = false;
		// the array is given the Tic that was placed at the index.. 
		TIC[place] = show;
	}

	public void Score(String which ){ 
		// up dates the score once either "O" or "X" wins 
		if(which == "O"){
			++WinnerO; 
		}
		if(which == "X"){
			++WinnerX;
		}
		// updates score 
		Field.setFont(new Font("Serif", SwingConstants.CENTER, 16));
		Field.setText("           " + WinnerX + " : " + WinnerO);
	}
	

	public void PlaceTic(   ){
// this series of conditions is repeated 3 times because there are Three rows 

	// outer if conditions refers to the row the tic could be placed at
		// inner if condition checks if the space has been taken
			// most inner if condition checks where the mouse was clicked 
		
		
				
		// checks the requirements if true then it correlates to a certain row 
		// First ROW  0    1    2
			if(LocationY < 94 && LocationY >25)
			{
				// checks the boolean type array if it is false then it means that the space has been taken
				// otherwise it is empty 
				if(EmptySpace[0] != false){
					// checks the location of the x axis and it it meets the requirements call Label 
					if(LocationX > 0 && LocationX <133){
						// calls the Label function and inserts a parameter of where the mouse was clicked 
							Label(0);
						}
					}
				if(EmptySpace[1] != false){
					if(LocationX > 134 && LocationX < 267){
							Label(1);
						}
					}
				if(EmptySpace[2] != false){
					 if(LocationX >268){
						 	Label(2);
					 	}
					}
			}
		
		// Second ROW 3    4     5
			if(LocationY < 189 && LocationY > 95){
				if(EmptySpace[3] != false){
					if(LocationX > 0 && LocationX <133){
							Label(3);
						}
					}
				if(EmptySpace[4] != false){
					if(LocationX > 134 && LocationX < 266){
							Label(4);
						}	
					}
				if(EmptySpace[5] !=false){
					if(LocationX > 267){
							Label(5);					
						}	
					}
			}
		// Third ROW  6    7     8
			if(LocationY < 284 && LocationY > 190){
				if(EmptySpace[6] != false){
					if(LocationX > 0 && LocationX < 133){
							Label(6);
						}
					}
				if(EmptySpace[7] != false){
					if(LocationX > 134 && LocationX < 266){
							Label(7);
						}
					}
				if(EmptySpace[8] != false){
					if(LocationX >267){
							Label(8);
						}	
					}
			}
	}	

public void CheckRules(){
// we set Letter to  "X" because we are going to check X first 
		String Letter = "X";

// everything will be done twice... check for X and check for O 
		for( int times = 0 ; times < 2 ; ++times){
			// we have a arrayVert this is created because if we find a vertical 3 tics they will be either 
			// {0,3,6} , {1,4,7}  or {2,5,8} and these numbers correlate to our 3 other arrays numbers, labels and array(boolean)
					int arrayVert[] = { 0 , 3 , 6};
			// same aspect as Vertical array
					int Rows[] = { 0 , 1 ,2};
					
					int Slanted[] = { 0 , 4 , 8};
					
			
			
	// Vertical check 
		// a vertical win can occur three times due to three columns 
				for(int vertical = 0 ; vertical < 3 ; ++vertical){
		
					if(TIC[arrayVert[0]]==Letter && TIC[arrayVert[1]]==Letter && TIC[arrayVert[2]]==Letter){
						JOptionPane.showMessageDialog(null, "GOOD JOB");
						Score(Letter);
						Reset();
					}
					for(int index = 0 ; index < arrayVert.length ; ++index){
						++arrayVert[index];
					}
		
				}

				
		

	//Rows check
		// a Row win can occur three times since there is 3 rows 
				for(int Row = 0 ; Row < 3 ; ++ Row){
					
					if(TIC[Rows[0]]== Letter && TIC[Rows[1]] == Letter && TIC[Rows[2]]==Letter){
						JOptionPane.showMessageDialog(null, "GOOD JOB");
						Score(Letter);
						Reset();
					}
					for(int index = 0 ; index < Rows.length ; ++ index){
						Rows[index] += 3  ;
					}
		
				}
	
	
	//Diagonal check  
	   // a Diagonal win can occur in two different ways 
				for(int Slant = 0 ; Slant < 2 ; ++Slant){
					
					if(TIC[Slanted[0]]==Letter && TIC[Slanted[1]]== Letter && TIC[Slanted[2]]== Letter){
						JOptionPane.showMessageDialog(null, "GOOD JOB");
						Score(Letter);
						Reset();
					}
					Slanted[0] = 2;
					Slanted[2] = 6;	
				}

		Letter ="O";
	}
	
}
	public class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// resets the current round 
			if(e.getSource()==Reset){
				Reset();
			}
			// resets the entire game 
			if(e.getSource()==NewGame){
				NewGame();
			}
			
		}
		
	}
	public class MyMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// places a tick at a location 
			PlaceTic();
			// checks if there was a 3 in a row 
			CheckRules();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public class MyMouseMotionListener implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		// when the mouse is clicked it finds the location where it was clicked at helping the program 
		// know where to place a tic 
		public void mouseMoved(MouseEvent e) {
			LocationX = e.getX();
			LocationY = e.getY();
		}
	}
}

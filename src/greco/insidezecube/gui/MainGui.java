package greco.insidezecube.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import greco.insidezecube.maze.Main;
import greco.insidezecube.maze.Maze2D;
import greco.insidezecube.utils.Pair;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainGui extends JFrame {

	private JPanel contentPane;
	private static boolean mouseIsPressed = false;
	
	public static enum CellType {
    	WALL, FRONTIER, UNEXPLORED, EXPLORED
    }

	/**
	 * Create the frame.
	 */
	public MainGui() {
		setTitle("InsideZeCube");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("New button");
		buttonPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		buttonPanel.add(btnNewButton_1);
		
		JPanel mazePanel = new mazePane();
		contentPane.add(mazePanel, BorderLayout.CENTER);
		
	}
	
	 public class mazePane extends JPanel {
		 
		 private int nbRow;
		 private int nbCol;

         public mazePane() {
            setLayout(new GridBagLayout());

	        setDimension();
	        setMaze();
        }
	        
        private void setDimension() {
        	Pair<Integer,Integer> dimension = Main.getMazeDimension();
        	nbRow = dimension.first;
        	nbCol = dimension.second;
        }
        
        private void setMaze() {
        	boolean[][] maze = Main.getPrintableMaze();
            for (int row = 0; row < nbRow; row++) {
                for (int col = 0; col < nbCol; col++) {
                	
                	GridBagConstraints gbc = getCellConstraints(row,col);

                    CellPane cellPane = new CellPane(row,col,maze[row][col]);
                    
                    cellPane.setBorder(getCellBorder(row, col));
                    add(cellPane, gbc);
                }
            }
        }
        
        private GridBagConstraints getCellConstraints(int row, int col) {
        	GridBagConstraints gbc = new GridBagConstraints();
        	
            gbc.gridx = col;
            gbc.gridy = row;

            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.BOTH;
            
            return gbc;
        }
        
        private MatteBorder getCellBorder(int row, int col) {
        	int topBorder = 0;
            int leftBorder = 0;
            int bottomBorder = 0;
            int rightBorder = 0;
            
            if(row == 0)
            	topBorder = 1;
            else if (row == nbRow-1)
            	bottomBorder = 1;
            
            if(col == 0)
            	leftBorder = 1;
            else if (col == nbCol-1)
            	rightBorder = 1;
            
            return new MatteBorder(topBorder, leftBorder, bottomBorder, rightBorder, Color.BLACK);
        }
    }

    public class CellPane extends JPanel {
    	
    	private int row;
    	private int col;

        private Color defaultBackground;
        private CellType cellType;

        public CellPane(int row, int col, boolean isPath) {
        	this.row = row;
        	this.col = col;
        	
        	this.cellType = initializeCellType(isPath);
        	
        	changeDefaultBackground();
    		setBackground(defaultBackground);
        	
            addMouseListener(new MouseAdapter() {
            	@Override
            	public void mouseEntered(MouseEvent e) {
            		setBackground(defaultBackground.darker());
            		
            		if (mouseIsPressed) {
                    	Main.changeCell(row,col);
                    	updateCell(true);
            		}
            	}
            	
            	@Override
            	public void mouseExited(MouseEvent e) {
            		setBackground(defaultBackground);
            	}
            	
                @Override
                public void mousePressed(MouseEvent e) {
                	Main.changeCell(row,col);
                	updateCell(true);
                	mouseIsPressed = true;
                }
                
                @Override
                public void mouseReleased(MouseEvent e) {
                	mouseIsPressed = false;
                }
            });
        }
        
        private CellType initializeCellType(boolean isPath) {
        	if (isPath)
        		return CellType.UNEXPLORED;
        	else
        		return CellType.WALL;
        }
        
        private void updateCell(boolean onMousePressed) {
        	boolean isPath = Main.getCellType(row, col);
        	this.cellType = initializeCellType(isPath);
        	changeDefaultBackground();
        	
        	if(onMousePressed)
        		setBackground(defaultBackground.darker());
        	else
        		setBackground(defaultBackground);
        }
        
        private void changeDefaultBackground() {
        	switch(cellType) {
        		case WALL:
        			defaultBackground = Color.DARK_GRAY;
        			break;
        		case UNEXPLORED:
        			defaultBackground = Color.WHITE;
        			break;
        		case FRONTIER:
        			defaultBackground = Color.BLUE;
        			break;
        		case EXPLORED:
        			defaultBackground = Color.CYAN;
        			break;
        		default:
        			defaultBackground = Color.PINK;
        			break;
        	}
        }

    }

}

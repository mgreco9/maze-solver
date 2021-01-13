package greco.insidezecube.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.image.BufferedImage;
import java.io.File;

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Font;

public class MainGui extends JFrame {

	private JPanel contentPane;
	private static boolean mouseIsPressed = false;
	String mazeName;
	Pair<Integer, Integer> mazeDimension;
	
	public static enum CellType {
    	WALL, FRONTIER, UNEXPLORED, EXPLORED
    }

	/**
	 * Create the frame.
	 */
	public MainGui() {
		mazeName = Main.getMazeName();
		mazeDimension = Main.getMazeDimension();
		
		setTitle("InsideZeCube");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		mazePane mazePanel = new mazePane();
		contentPane.add(mazePanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new GridLayout(2, 2, 2, 2));
		
		JPanel importPane = new JPanel();
		buttonPanel.add(importPane);
		importPane.setLayout(new BoxLayout(importPane, BoxLayout.X_AXIS));
		
		Component horizontalGlue = Box.createHorizontalGlue();
		importPane.add(horizontalGlue);
		
		JComboBox comboBox = new JComboBox();
		importPane.add(comboBox);
		File layoutDir = new File("layouts/");
		for (File child : layoutDir.listFiles()) {
			if (child.isFile()) {
				String layoutName = child.getName();
				layoutName = (layoutName.split("\\."))[0];
				
				comboBox.addItem(layoutName);
			}
		}
		comboBox.setSelectedItem(mazeName);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		importPane.add(horizontalStrut_2);
		
		JButton importMazeButton = new JButton("import maze");
		importPane.add(importMazeButton);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(30);
		importPane.add(horizontalStrut_3);
		
		JPanel randomMazePane = new JPanel();
		buttonPanel.add(randomMazePane);
		
		Label rowLabel = new Label("rows:");
		randomMazePane.add(rowLabel);
		
		TextField nbRowField = new TextField();
		nbRowField.setText(mazeDimension.first.toString());
		randomMazePane.add(nbRowField);
		
		Label colLabel = new Label("col:");
		randomMazePane.add(colLabel);
		
		TextField nbColField = new TextField();
		nbColField.setText(mazeDimension.second.toString());
		randomMazePane.add(nbColField);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		randomMazePane.add(horizontalStrut);
		
		JButton btnNewButton = new JButton("Randomize");
		randomMazePane.add(btnNewButton);
		
		JPanel exportPane = new JPanel();
		buttonPanel.add(exportPane);
		exportPane.setLayout(new BoxLayout(exportPane, BoxLayout.X_AXIS));
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		exportPane.add(horizontalGlue_1);
		
		Label label = new Label("Name:");
		label.setAlignment(Label.RIGHT);
		exportPane.add(label);
		
		TextField mazeTitle = new TextField();
		mazeTitle.setFont(new Font("Dialog", Font.PLAIN, 20));
		mazeTitle.setText(mazeName);
		exportPane.add(mazeTitle);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		exportPane.add(horizontalStrut_1);
		
		JButton exportMazeButton = new JButton("export maze");
		exportPane.add(exportMazeButton);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(30);
		exportPane.add(horizontalStrut_4);
		
		
		importMazeButton.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				mazeName = (String)comboBox.getSelectedItem();
		        Main.instantiateMaze(mazeName+".txt");
		        mazePanel.updateMaze();
		        
		        mazeDimension = Main.getMazeDimension();
		        
		        nbRowField.setText(mazeDimension.first.toString());
		        nbColField.setText(mazeDimension.second.toString());
		        mazeTitle.setText(mazeName);
		    }
		});
		
		exportMazeButton.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				mazeName = mazeTitle.getText();
				Main.exportMaze(mazeName + ".txt");
				
				if(((DefaultComboBoxModel)comboBox.getModel()).getIndexOf(mazeName) == -1)
					comboBox.addItem(mazeName);
		    }
		});
		
	}
	
	 public class mazePane extends JPanel {
		 
		 private int nbRow;
		 private int nbCol;

         public mazePane() {
            setLayout(new GridBagLayout());

	        setDimension();
	        setMaze();
        }
         
        public void updateMaze() {
            this.removeAll();
            
        	setDimension();
	        setMaze();
	        
	        revalidate();
	        repaint();
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

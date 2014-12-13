/**
 * CIS 120 HW10
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    public void run() {
	final Playfield playfield = new Playfield();
	final HoldCell holdCell = new HoldCell(playfield.holdBlock);
	final UpNext upNext = new UpNext(playfield.upNextTets);
	final PlayfieldView playfieldView = new PlayfieldView(playfield.field,
		playfield.movingBlock, playfield.ghostBlock);

	// Top-level frame in which game components live
	// Be sure to change "TOP LEVEL FRAME" to the name of your game
	final JFrame frame = new JFrame("TETRIS");
	Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();

	final JPanel centerPanel = new JPanel();
	frame.add(centerPanel, BorderLayout.CENTER);

	// labels
	final JLabel lines = new JLabel();
	final JLabel score = new JLabel();
	final JLabel level = new JLabel();
	final JLabel status = new JLabel();

	lines.setText("  Lines: " + playfield.totalLinesCompleted);
	score.setText("  Score: " + playfield.score);
	level.setText("  Level: " + playfield.level);
	status.setText("");

	// Main playing area
	// create a Score Area
	// create a Playfield
	// create a Hold Space
	// create a Settings Pane

	final Timer timer = new Timer(Levels.getLevelSpeed(playfield.level),
		new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			playfield.timerFired();
			playfieldView.update(playfield.field,
				playfield.movingBlock, playfield.ghostBlock);
			lines.setText("  Lines: "
				+ playfield.totalLinesCompleted);
			score.setText("  Score: " + playfield.score);
			level.setText("  Level: " + playfield.level);
			playfieldView.repaint();
			if (playfield.status == Status.END) {
			    status.setText("  GAME OVER");
			}
		    }
		});
	timer.start();
	playfield.setTimer(timer);

	// Note here that when we add an action listener to the reset
	// button, we define it as an anonymous inner class that is
	// an instance of ActionListener with its actionPerformed()
	// method overridden. When the button is pressed,
	// actionPerformed() will be called.

	// Buttons -------------------------------------------------------------
	final JButton pause = new JButton("Pause");
	pause.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		playfieldView.pause();
		if (timer.isRunning()) {
		    playfield.timer.stop();
		    playfield.status = Status.PAUSE;
		    status.setText("  PAUSED");
		} else {
		    playfield.timer.start();
		    playfield.status = Status.BEGIN;
		    status.setText("");

		}
	    }
	});
	final JButton newGame = new JButton("New Game");
	newGame.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		playfield.clearField();
		playfieldView.update(playfield.field, playfield.movingBlock,
			playfield.ghostBlock);
		playfieldView.repaint();
		holdCell.update(playfield.holdBlock);
		holdCell.repaint();
		upNext.update(playfield.upNextTets);
		status.setText("");

	    }
	});

	// Key Presses ---------------------------------------------------------
	KeyAdapter myKA = new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		if (playfield.status == Status.BEGIN) {
		    int keyCode = e.getKeyCode();
		    switch (keyCode) {
			case KeyEvent.VK_UP: {
			    playfield.keyPressed(KeyboardKey.UP);
			    break;
			}
			case KeyEvent.VK_DOWN: {
			    playfield.keyPressed(KeyboardKey.DOWN);
			    break;
			}
			case KeyEvent.VK_LEFT: {
			    playfield.keyPressed(KeyboardKey.LEFT);
			    break;
			}
			case KeyEvent.VK_RIGHT: {
			    playfield.keyPressed(KeyboardKey.RIGHT);
			    break;
			}
			case KeyEvent.VK_SPACE: {
			    playfield.keyPressed(KeyboardKey.SPACE);
			    break;
			}
			case KeyEvent.VK_SHIFT: {
			    playfield.keyPressed(KeyboardKey.SHIFT);
			    holdCell.update(playfield.holdBlock);
			    holdCell.repaint();
			    break;
			}
			default: {
			    System.out.println("other");
			}
		    }
		} else if (playfield.status == Status.END){
		    status.setText("  GAME OVER");
		}
		playfieldView.update(playfield.field, playfield.movingBlock,
			playfield.ghostBlock);
		playfieldView.repaint();
		upNext.update(playfield.upNextTets);
		upNext.repaint();
	    }
	};

	playfieldView.addKeyListener(myKA);

	newGame.setFocusable(false);
	pause.setFocusable(false);
	holdCell.setFocusable(false);
	upNext.setFocusable(false);
	status.setFocusable(false);

	final JPanel rightPanel = new JPanel(new BorderLayout(10, 10));
	final JPanel leftPanel = new JPanel(new BorderLayout());
	final JPanel scorePanel = new JPanel(new BorderLayout(10, 10));
	final JPanel buttonPanel = new JPanel(new BorderLayout());

	rightPanel.setFocusable(false);
	rightPanel.setVisible(true);
	scorePanel.setFocusable(false);
	scorePanel.setVisible(true);
	buttonPanel.setFocusable(false);
	buttonPanel.setVisible(true);

	rightPanel.add(scorePanel, BorderLayout.PAGE_START);
	rightPanel.add(buttonPanel, BorderLayout.PAGE_END);

	buttonPanel.add(newGame, BorderLayout.PAGE_START);
	buttonPanel.add(pause, BorderLayout.PAGE_END);
	rightPanel.add(status, BorderLayout.CENTER);
	scorePanel.add(level, BorderLayout.NORTH);
	scorePanel.add(score, BorderLayout.CENTER);
	scorePanel.add(lines, BorderLayout.SOUTH);

	leftPanel.add(holdCell, BorderLayout.NORTH);
	leftPanel.add(upNext, BorderLayout.SOUTH);

	frame.add(rightPanel, BorderLayout.EAST);
	frame.add(leftPanel, BorderLayout.WEST);

	// Put the everything on the screen
	frame.add(playfieldView);
	frame.setVisible(true);
	frame.pack();
	double xPosition = (scrSize.getWidth() - frame.getWidth()) / 2;
	double yPosition = (scrSize.getHeight() - frame.getHeight()) / 2;
	frame.setLocation((int) xPosition, (int) yPosition);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
    }

    /*
     * Main method run to start and run the game Initializes the GUI elements
     * specified in Game and runs it IMPORTANT: Do NOT delete! You MUST include
     * this in the final submission of your game.
     */
    public static void main(String[] args) {
	SwingUtilities.invokeLater(new Game());
    }
}

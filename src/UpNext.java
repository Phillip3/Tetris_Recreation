import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class UpNext extends JPanel {
    private ArrayList<Tetrimino> nextTets;

    // Game constants
    public static final int HOLD_WIDTH = 75;
    public static final int HOLD_HEIGHT = 75 * 4;
    public static final int CELL_WIDTH = 15;

    public UpNext(ArrayList<Tetrimino> nextTets) {
	super();
	this.nextTets = nextTets;
    }

    public void update(ArrayList<Tetrimino> nextTets) {
	this.nextTets = nextTets;
	repaint();
    }

    public void pause() {
	repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	setFocusable(false);
	g.setColor(Color.BLACK);
	g.drawLine(0, 0, HOLD_WIDTH, 0);
	g.drawLine(0, HOLD_HEIGHT, HOLD_WIDTH, HOLD_HEIGHT);
	g.drawString("Up Next: ", 3, 15);

	if (nextTets != null) {
	    for (int i = 0; i < nextTets.size(); i++) {
		// draw the main block
		Tetrimino holdBlock = nextTets.get(i);
		if (holdBlock != null) {
		    
		    float blockWidth = 3;
		    if (holdBlock.blockName() == "IBlock"
			    || holdBlock.blockName() == "OBlock") {
			blockWidth = 4;
		    }
		    
		    Location[] loc = holdBlock.locationOfBlock();
		    for (Location l : loc) {
			g.setColor(Color.BLACK);
			g.drawRect(
				(l.x - 3)
					* CELL_WIDTH
					+ (int) ((((HOLD_WIDTH / CELL_WIDTH) - blockWidth) / 2) * CELL_WIDTH),
				-(l.y - 23)
					* CELL_WIDTH
					+ (((HOLD_HEIGHT / 4) / CELL_WIDTH) * i)
					* CELL_WIDTH + 15, CELL_WIDTH,
				CELL_WIDTH);
			g.setColor(holdBlock.getColor());
			g.fillRect(
				(l.x - 3)
					* CELL_WIDTH
					+ (int) ((((HOLD_WIDTH / CELL_WIDTH) - blockWidth) / 2) * CELL_WIDTH),
				-(l.y - 23)
					* CELL_WIDTH
					+ (((HOLD_HEIGHT / 4) / CELL_WIDTH) * i)
					* CELL_WIDTH + 15, CELL_WIDTH,
				CELL_WIDTH);

		    }
		}
	    }
	}
    }

    @Override
    public Dimension getPreferredSize() {
	return new Dimension(HOLD_WIDTH, HOLD_HEIGHT);
    }

}

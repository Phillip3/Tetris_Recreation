import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class HoldCell extends JPanel {
    public Tetrimino holdBlock;

    // Game constants
    public static final int HOLD_WIDTH = 75;
    public static final int HOLD_HEIGHT = 75;
    public static final int CELL_WIDTH = 15;

    public HoldCell(Tetrimino holdBlock) {
	super();
	this.holdBlock = holdBlock;
    }

    public void update(Tetrimino holdBlock) {
	this.holdBlock = holdBlock;
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
	g.drawLine(0, HOLD_HEIGHT, HOLD_WIDTH, HOLD_HEIGHT);
	g.drawString("Hold: ", 3, HOLD_HEIGHT - 3);

	// draw the main block
	if (holdBlock != null) {
	    float blockWidth = 3;
	    if (holdBlock.blockName() == "IBlock"
		    || holdBlock.blockName() == "OBlock") {
		blockWidth = 4;
	    }

	    holdBlock = Tetrimino.newTetriminoWithSameClass(holdBlock);

	    Location[] loc = holdBlock.locationOfBlock();
	    for (Location l : loc) {
		g.setColor(Color.BLACK);
		g.drawRect(
			(l.x - 3)
				* CELL_WIDTH
				+ (int) ((((HOLD_WIDTH / CELL_WIDTH) - blockWidth) / 2) * CELL_WIDTH),
			-(l.y - 23) * CELL_WIDTH, CELL_WIDTH, CELL_WIDTH);
		g.setColor(holdBlock.getColor());
		g.fillRect(
			(l.x - 3)
				* CELL_WIDTH
				+ (int) ((((HOLD_WIDTH / CELL_WIDTH) - blockWidth) / 2) * CELL_WIDTH),
			-(l.y - 23) * CELL_WIDTH, CELL_WIDTH, CELL_WIDTH);

	    }
	}
    }

    @Override
    public Dimension getPreferredSize() {
	return new Dimension(HOLD_WIDTH, HOLD_HEIGHT);
    }
}

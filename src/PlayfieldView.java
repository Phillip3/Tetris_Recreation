import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlayfieldView extends JPanel {
    public Color[][] field;
    public Tetrimino movingBlock;
    public Tetrimino ghostBlock;

    // Game constants
    public static final int COURT_WIDTH = 300;
    public static final int COURT_HEIGHT = 600;
    public static final int CELL_WIDTH = 30;

    public PlayfieldView(Color[][] field, Tetrimino mBlock, Tetrimino gBlock) {
	super();
	this.field = field;
	this.movingBlock = mBlock;
	this.ghostBlock = gBlock;
    }
    
    public void update(Color[][] field, Tetrimino mBlock, Tetrimino gBlock) {
	this.field = field;
	this.movingBlock = mBlock;
	this.ghostBlock = gBlock;
	repaint();
    }
    
    public void pause() {
	repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	for (int x = 0; x < 10; x++) {
	    for (int y = 0; y < 20; y++) {
		g.setColor(Color.BLACK);
		g.drawRect(x * CELL_WIDTH, (19 - y) * CELL_WIDTH, CELL_WIDTH,
			CELL_WIDTH);
		if (field[x][y] != null) {
		    g.setColor(field[x][y]);
		} else {
		    g.setColor(Color.GRAY);
		}
		g.fillRect(x * CELL_WIDTH, (19 - y) * CELL_WIDTH, CELL_WIDTH,
			CELL_WIDTH);
	    }
	}
	
	setFocusable(true);
	//draw the ghost block
	Location[] locg = ghostBlock.locationOfBlock();
	for (Location l : locg) {
	    g.setColor(Color.BLACK);
	    g.drawRect(l.x * CELL_WIDTH, (19 - l.y) * CELL_WIDTH, CELL_WIDTH,
		    CELL_WIDTH);
	    g.setColor(Color.LIGHT_GRAY);
	    g.fillRect(l.x * CELL_WIDTH, (19 - l.y) * CELL_WIDTH, CELL_WIDTH,
		    CELL_WIDTH);
	}
	
	//draw the main block
	Location[] loc = movingBlock.locationOfBlock();
	for (Location l : loc) {
	    g.setColor(Color.BLACK);
	    g.drawRect(l.x * CELL_WIDTH, (19 - l.y) * CELL_WIDTH, CELL_WIDTH,
		    CELL_WIDTH);
	    g.setColor(movingBlock.getColor());
	    g.fillRect(l.x * CELL_WIDTH, (19 - l.y) * CELL_WIDTH, CELL_WIDTH,
		    CELL_WIDTH);
	}
	
	

    }

    @Override
    public Dimension getPreferredSize() {
	return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}

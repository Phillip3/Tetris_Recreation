import java.awt.Color;

public class GhostBlock extends Tetrimino {
    private Tetrimino blockToGhost;
    private Color[][] field;

    public GhostBlock(Tetrimino blockToGhost, Color[][] field) {
	this.blockToGhost = blockToGhost;
	this.field = field;
    }

    @Override
    public Location[] locationOfBlock() {
	Location[] returnLocation = blockToGhost.locationOfBlock();
	int shortestDistance = 40;
	int lowestBlockYVal = 23;
	for (Location loc : returnLocation) {
	    for (int y = 0; y < 23; y++) {
		if (field[loc.x][y] != null && shortestDistance > loc.y - y) {
		    shortestDistance = loc.y - y;
		}
	    }
	    if (lowestBlockYVal > loc.y) {
		lowestBlockYVal = loc.y;
	    }
	}
	if (shortestDistance > lowestBlockYVal) {
	    shortestDistance = lowestBlockYVal;
	} else {
	    shortestDistance -= 1;
	}
	for (int i = 0; i < returnLocation.length; i++) {
	    returnLocation[i] = new Location(returnLocation[i].x,
		    returnLocation[i].y - shortestDistance);
	}
	return returnLocation;
    }

    @Override
    public Location[] rotateRight() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Location[] setOrientation(Orientation o) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Orientation getOrientation() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Location[] translateBlock(int dx, int dy) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setLocation(Location[] l) {
	// TODO Auto-generated method stub

    }

    @Override
    public Color getColor() {
	// TODO Auto-generated method stub
	return TetrisColor.red;
    }

    @Override
    public String blockName() {
	// TODO Auto-generated method stub
	return null;
    }

}

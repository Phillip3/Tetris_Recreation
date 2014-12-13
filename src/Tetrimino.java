import java.awt.Color;

public abstract class Tetrimino {
    // Sets the orientation of the block and returns the coordinates of the
    // newly rotated block
    public Location[] rotateRight() {
	return null;
    };

    // sets the orientation and then returns the locations for all of the blocks
    public Location[] setOrientation(Orientation o) {
	return null;
    }

    // returns the orientation of the block
    public Orientation getOrientation() {
	return null;
    }

    // will return the spaces taken up by the block in the coordinate system
    public Location[] translateBlock(int dx, int dy) {
	return null;
    }

    // returns the location of the block's minos {x, y}
    public Location[] locationOfBlock() {
	return null;
    }

    // sets the position of the block
    public void setLocation(Location[] l) {
    }

    // returns the color of the block
    public Color getColor() {
	return null;
    }

    // returns the name of the block (e.g. returns "LBlock" or "SBlock" etc.)
    public String blockName() {
	return null;
    }

    public static Tetrimino copy(Tetrimino t) {
	Tetrimino returnTet = null;
	switch (t.blockName()) {
	    case "IBlock": {
		returnTet = new IBlock();
		break;
	    }
	    case "JBlock": {
		returnTet = new JBlock();
		break;
	    }
	    case "LBlock": {
		returnTet = new LBlock();
		break;
	    }
	    case "SBlock": {
		returnTet = new SBlock();
		break;
	    }
	    case "OBlock": {
		returnTet = new OBlock();
		break;
	    }
	    case "TBlock": {
		returnTet = new TBlock();
		break;
	    }
	    case "ZBlock": {
		returnTet = new ZBlock();
		break;
	    }
	    default: {
		throw new IllegalArgumentException();
	    }
	}
	returnTet.setLocation(t.locationOfBlock());
	returnTet.setOrientation(t.getOrientation());
	return returnTet;
    }

    public static Tetrimino newTetriminoWithSameClass(Tetrimino t) {
	Tetrimino returnTet = null;
	switch (t.blockName()) {
	    case "IBlock": {
		returnTet = new IBlock();
		break;
	    }
	    case "JBlock": {
		returnTet = new JBlock();
		break;
	    }
	    case "LBlock": {
		returnTet = new LBlock();
		break;
	    }
	    case "SBlock": {
		returnTet = new SBlock();
		break;
	    }
	    case "OBlock": {
		returnTet = new OBlock();
		break;
	    }
	    case "TBlock": {
		returnTet = new TBlock();
		break;
	    }
	    case "ZBlock": {
		returnTet = new ZBlock();
		break;
	    }
	    default: {
		throw new IllegalArgumentException();
	    }
	}
	return returnTet;
    }
}

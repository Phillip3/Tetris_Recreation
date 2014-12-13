import java.awt.Color;

public class OBlock extends Tetrimino {
    private Orientation orientation;
    private Location[] minos;

    public OBlock() {
	orientation = Orientation.UP;
	this.minos = new Location[4];
	minos[0] = new Location(4, 22);
	minos[1] = new Location(5, 22);
	minos[2] = new Location(4, 21);
	minos[3] = new Location(5, 21);
    }

    @Override
    public Location[] rotateRight() {
	return minos.clone();
    }

    @Override
    public Location[] setOrientation(Orientation o) {
	return minos.clone();
    }

    @Override
    public Orientation getOrientation() {
	return orientation;
    }

    @Override
    public Location[] translateBlock(int dx, int dy) {
	boolean shouldMove = true;
	for (int i = 0; i < minos.length; i++) {
	    if ((minos[i].x == 0 && dx < 0) || (minos[i].x == 9 && dx > 0)) {
		shouldMove = false;
	    }
	}
	if (shouldMove) {
	    for (int i = 0; i < minos.length; i++) {
		minos[i] = new Location(minos[i].x + dx, minos[i].y + dy);
	    }
	}
	return minos.clone();
    }

    @Override
    public Location[] locationOfBlock() {
	return minos.clone();
    }

    @Override
    public Color getColor() {
	// TODO Auto-generated method stub
	return TetrisColor.yellow;
    }

    @Override
    public String blockName() {
	return "OBlock";
    }

    @Override
    public void setLocation(Location[] l) {
	if (l != null && l.length == 4) {
	    minos = l.clone();
	} else {
	    throw new IllegalArgumentException();
	}
    }
}

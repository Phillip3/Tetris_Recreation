import java.awt.Color;

public class SBlock extends Tetrimino {
    private Orientation orientation;
    private Location[] minos;

    public SBlock() {
	orientation = Orientation.UP;
	this.minos = new Location[4];
	minos[0] = new Location(3, 21);
	minos[1] = new Location(4, 21);
	minos[2] = new Location(4, 22);
	minos[3] = new Location(5, 22);
    }

    @Override
    public Location[] rotateRight() {
	switch (orientation) {
	    case UP: {
		minos[0] = new Location(minos[0].x + 1, minos[0].y + 1);
		minos[2] = new Location(minos[2].x + 1, minos[2].y - 1);
		minos[3] = new Location(minos[3].x, minos[3].y - 2);
		this.orientation = Orientation.RIGHT;
		break;
	    }
	    case RIGHT: {
		minos[0] = new Location(minos[0].x + 1, minos[0].y - 1);
		minos[2] = new Location(minos[2].x - 1, minos[2].y - 1);
		minos[3] = new Location(minos[3].x - 2, minos[3].y);
		this.orientation = Orientation.DOWN;
		break;
	    }
	    case DOWN: {
		minos[0] = new Location(minos[0].x - 1, minos[0].y - 1);
		minos[2] = new Location(minos[2].x - 1, minos[2].y + 1);
		minos[3] = new Location(minos[3].x, minos[3].y + 2);
		this.orientation = Orientation.LEFT;
		break;
	    }
	    case LEFT: {
		minos[0] = new Location(minos[0].x - 1, minos[0].y + 1);
		minos[2] = new Location(minos[2].x + 1, minos[2].y + 1);
		minos[3] = new Location(minos[3].x + 2, minos[3].y);
		this.orientation = Orientation.UP;
		break;
	    }
	    default: {
		throw new IllegalArgumentException();
	    }
	}
	return minos.clone();
    }

    @Override
    public Location[] setOrientation(Orientation o) {
	if (o == orientation) {
	    return minos.clone();
	} else {
	    switch (o) {
		case UP: {
		    if (orientation == Orientation.RIGHT) {
			rotateRight();
		    } else if (orientation == Orientation.DOWN) {
			rotateRight();
			rotateRight();
		    } else if (orientation == Orientation.LEFT) {
			rotateRight();
			rotateRight();
			rotateRight();
		    } else {
			System.out.println("Aww Dang! Somethign went wrong");
		    }
		    break;
		}
		case RIGHT: {
		    if (orientation == Orientation.DOWN) {
			rotateRight();
		    } else if (orientation == Orientation.LEFT) {
			rotateRight();
			rotateRight();
		    } else if (orientation == Orientation.UP) {
			rotateRight();
			rotateRight();
			rotateRight();
		    } else {
			System.out.println("Aww Dang! Somethign went wrong");
		    }
		    break;
		}
		case DOWN: {
		    if (orientation == Orientation.LEFT) {
			rotateRight();
		    } else if (orientation == Orientation.UP) {
			rotateRight();
			rotateRight();
		    } else if (orientation == Orientation.RIGHT) {
			rotateRight();
			rotateRight();
			rotateRight();
		    } else {
			System.out.println("Aww Dang! Somethign went wrong");
		    }
		    break;
		}
		case LEFT: {
		    if (orientation == Orientation.UP) {
			rotateRight();
		    } else if (orientation == Orientation.RIGHT) {
			rotateRight();
			rotateRight();
		    } else if (orientation == Orientation.DOWN) {
			rotateRight();
			rotateRight();
			rotateRight();
		    } else {
			System.out.println("Aww Dang! Somethign went wrong");
		    }
		    break;
		}
		default: {
		    throw new IllegalArgumentException();
		}
	    }
	}
	this.orientation = o;
	return null;
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
	return TetrisColor.green;
    }

    @Override
    public String blockName() {
	return "SBlock";
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
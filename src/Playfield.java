import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Playfield {
    // This is the coordinate system of the Playfield
    public Color[][] field;
    public Tetrimino movingBlock;
    public Tetrimino holdBlock;
    public Tetrimino ghostBlock;
    public ArrayList<Tetrimino> upNextTets;
    public int level;
    public int score;
    public int totalLinesCompleted;
    public javax.swing.Timer timer;
    public Status status;
    private boolean canHoldBlock;

    public Playfield() {
	field = new Color[10][23];// 3 above for spawning blocks
	upNextTets = new ArrayList<Tetrimino>();
	while (upNextTets.size() <= 4) {
	    upNextTets.add(generateRandomBlock());
	}
	movingBlock = upNextTets.get(0);
	upNextTets.remove(0);
	ghostBlock = new GhostBlock(movingBlock, field);
	level = 1;
	score = 0;
	totalLinesCompleted = 0;
	status = Status.BEGIN;
	canHoldBlock = true;
    }

    public void setTimer(javax.swing.Timer timer) {
	this.timer = timer;
    }

    private void addBlockToField(Tetrimino block) {
	canHoldBlock = true;
	Location[] minos = block.locationOfBlock();
	for (Location l : minos) {
	    if (l.y >= 22) {
		status = Status.END;
		this.timer.stop();
	    } else {
		field[l.x][l.y] = block.getColor();
	    }

	}
	Integer[] completedLines = checkForCompleteRow();
	this.turnCompletedRowsToPoints(completedLines.length);
	while (completedLines.length != 0) {
	    clearRow(completedLines[0]);
	    completedLines = checkForCompleteRow();
	}
    }

    public void timerFired() {
	// if the block is touching another block then place the block
	// if the block is touching another block and above the screen
	// game is over
	// if the block isnt' touching anything move the block down
	if (movingBlock != null) {
	    Location[] minos = movingBlock.locationOfBlock();
	    boolean touchingPlacedBlock = false;

	    for (Location loc : minos) {
		touchingPlacedBlock = touchingPlacedBlock || loc.y == 0;
		if (loc.y > 0 && loc.x >= 0 && loc.x < 10 && loc.y < 23) {
		    touchingPlacedBlock = touchingPlacedBlock
			    || field[loc.x][loc.y - 1] != null;
		}
	    }
	    if (touchingPlacedBlock) {
		addBlockToField(movingBlock);
		this.movingBlock = generateRandomBlock();
		this.ghostBlock = new GhostBlock(movingBlock, field);
	    } else {
		movingBlock.translateBlock(0, -1);
	    }
	} else {
	    this.movingBlock = generateRandomBlock();
	    this.ghostBlock = new GhostBlock(movingBlock, field);
	}

	// // check if a line is complete and if it is, clear it.
	// boolean blockAboveScreen = true;
	// if (touchingPlacedBlock) {
	// // place the block
	// if (blockAboveScreen) {
	// // end the game
	// }
	// } else {
	// // move the block down
	// }
	// Integer[] completedLines = checkForCompleteRow();
	// for (Integer i : completedLines) {
	// clearRow(i);
	// }
    }

    public void keyPressed(KeyboardKey k) {
	// UP - rotate
	// DOWN - move down faster (call the translate method)
	// LEFT - move left
	// RIGHT - move right
	// SPACE - hard drop
	// SHIFT - get the block from hold
	if (status == Status.BEGIN) {
	    switch (k) {
		case UP: {
		    Location[] locs = movingBlock.rotateRight();
		    int amtToTransX = 0;
		    int amtToTransY = 0;
		    boolean canRotate = true;
		    for (Location loc : locs) {
			if (loc.x < 0 && loc.x < amtToTransX) {
			    amtToTransX = loc.x;
			} else if (loc.x > 9 && loc.x - 9 > amtToTransX) {
			    amtToTransX = loc.x - 9;
			}
			if (loc.y < 0 && loc.y < amtToTransY) {
			    amtToTransY = loc.y;
			}
		    }
		    locs = movingBlock.translateBlock(-amtToTransX,
			    -amtToTransY);

		    for (Location loc : locs) {
			if (field[loc.x][loc.y] != null) {
			    canRotate = false;
			}
		    }
		    if (!canRotate) {
			movingBlock.rotateRight();
			movingBlock.rotateRight();
			movingBlock.rotateRight();
			movingBlock.translateBlock(amtToTransX, amtToTransY);

		    }
		    break;
		}
		case DOWN: {
		    this.timerFired();
		    break;
		}
		case LEFT: {
		    boolean canMove = true;
		    for (Location loc : movingBlock.locationOfBlock()) {
			if (loc.x > 0 && loc.x < 10) {
			    canMove = canMove
				    && field[loc.x - 1][loc.y] == null;
			} else {
			    canMove = false;
			}
		    }
		    if (canMove) {
			movingBlock.translateBlock(-1, 0);
		    }
		    break;
		}
		case RIGHT: {
		    boolean canMove = true;
		    for (Location loc : movingBlock.locationOfBlock()) {
			if (loc.x >= 0 && loc.x < 9) {
			    canMove = canMove
				    && field[loc.x + 1][loc.y] == null;
			} else {
			    canMove = false;
			}
		    }
		    if (canMove) {
			movingBlock.translateBlock(1, 0);
		    }
		    break;
		}
		case SPACE: {
		    this.movingBlock.setLocation(this.ghostBlock
			    .locationOfBlock());
		    this.addBlockToField(movingBlock);
		    this.movingBlock = this.generateRandomBlock();
		    this.ghostBlock = new GhostBlock(movingBlock, field);
		    break;
		}
		case SHIFT: {
		    if (canHoldBlock) {
			if (holdBlock != null) {
			    Tetrimino t = Tetrimino
				    .newTetriminoWithSameClass(movingBlock);
			    movingBlock = Tetrimino
				    .newTetriminoWithSameClass(holdBlock);
			    holdBlock = t;
			    this.ghostBlock = new GhostBlock(movingBlock, field);
			} else {
			    holdBlock = Tetrimino.copy(movingBlock);
			    holdBlock.setOrientation(Orientation.UP);
			    movingBlock = generateRandomBlock();
			    this.ghostBlock = new GhostBlock(movingBlock, field);
			}
			canHoldBlock = false;
		    }
		    break;
		}
		default: {
		    throw new IllegalArgumentException();
		}
	    }
	}
    }

    // clears the entire playfield
    public void clearField() {
	field = new Color[10][23];// 3 above for spawning blocks
	upNextTets = new ArrayList<Tetrimino>();
	while (upNextTets.size() <= 4) {
	    upNextTets.add(generateRandomBlock());
	}
	holdBlock = null;
	movingBlock = upNextTets.get(0);
	upNextTets.remove(0);
	ghostBlock = new GhostBlock(movingBlock, field);
	level = 1;
	score = 0;
	totalLinesCompleted = 0;
	status = Status.BEGIN;
	canHoldBlock = true;
	this.timer.setDelay(Levels.getLevelSpeed(level));
	this.timer.start();
    }

    // clears the specified row
    private void clearRow(int j) {
	for (int x = 0; x < 10; x++) {
	    for (int y = j; y < 22; y++) {
		field[x][y] = field[x][y + 1];
	    }
	    field[x][22] = null;
	}
    }

    private Tetrimino generateRandomBlock() {
	// make random number between 0 and 6 and return a corresponding block
	Random r = new Random();
	int randNum = r.nextInt(7);
	Tetrimino[] tets = { new IBlock(), new LBlock(), new JBlock(),
		new OBlock(), new SBlock(), new TBlock(), new ZBlock() };
	this.upNextTets.add(tets[randNum]);
	Tetrimino t = upNextTets.get(0);
	upNextTets.remove(0);
	return t;
    }

    // checks for completed rows in the playfield and returns the array
    // of the y values of the rows that are completed.
    private Integer[] checkForCompleteRow() {
	ArrayList<Integer> returnList = new ArrayList<Integer>();
	for (int y = 0; y < 20; y++) {
	    boolean completed = true;
	    for (int x = 0; x < 10; x++) {
		completed = completed && field[x][y] != null;
	    }
	    if (completed) {
		returnList.add(y);
	    }
	}
	Integer[] returnArray = new Integer[returnList.size()];
	returnList.toArray(returnArray);
	return returnArray;
    }

    // scoring stuff - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    private void turnCompletedRowsToPoints(int completed) {
	if (completed == 1) {
	    this.score += Levels.pointsFor1Line(this.level);
	    totalLinesCompleted += 1;
	} else if (completed == 2) {
	    this.score += Levels.pointsFor2Lines(this.level);
	    totalLinesCompleted += 2;
	} else if (completed == 3) {
	    this.score += Levels.pointsFor3Lines(this.level);
	    totalLinesCompleted += 3;
	} else if (completed == 4) {
	    this.score += Levels.pointsFor4Lines(this.level);
	    totalLinesCompleted += 4;
	}
	if (this.totalLinesCompleted >= this.level * 10) {
	    this.level++;
	    this.timer.setDelay(Levels.getLevelSpeed(this.level));
	    ;
	}
    }

}

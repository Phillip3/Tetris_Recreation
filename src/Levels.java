public class Levels {
    public static int getLevelSpeed(int level) {
	return 1000 / level;
    }

    public static int pointsFor1Line(int level) {
	return 40 * (level + 1);

    }

    public static int pointsFor2Lines(int level) {
	return 100 * (level + 1);
    }

    public static int pointsFor3Lines(int level) {
	return 300 * (level + 1);
    }

    public static int pointsFor4Lines(int level) {
	return 1200 * (level + 1);
    }
}

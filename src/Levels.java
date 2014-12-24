import java.util.prefs.Preferences;

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

    public static void setNameAndScore(String name, int score,
	    int numberOfLines, int level) {
	String input = score + ":" + numberOfLines + ":" + level;
	Preferences.userRoot().put(name, input);
    }

    public static int[] getScoreNumbLinesLevelForName(String name) {
	String output = Preferences.userRoot().get(name, "");
	int[] a = {0, 0, 0};
	if (output.equals("")){
	    //return a;
	} else {
	    String score = output.substring(0, output.indexOf(':'));
	    String numberOfLines = output.substring(output.indexOf(':') + 1, output.lastIndexOf(':'));
	    String level = output.substring(output.lastIndexOf(':') + 1, output.length());
	    a[0] = Integer.parseInt(score);
	    a[1]= Integer.parseInt(numberOfLines);
	    a[2] = Integer.parseInt(level);
	}
	return a;
    }
}

// Global helper class.
// Records finish time upon game completion.

package Manager;

public class Data {
	
	public static long time;
        public static int points;
        public static int type;
	
	public static void setTime(long l) { time = l; }
	public static long getTime() { return time; }
	public static void setPoint(int p) { points = p; }
        public static int getPoint() { return points; }
        public static void setType(int t) { type = t; }
        public static int getType() { return type; }
}

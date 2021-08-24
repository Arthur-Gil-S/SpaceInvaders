import java.util.Random;

/**
 * Represents the params
 * 
 * @author Arthur_Gil - 20101221, Gilberto_Koerbes - 20204011,
 *         Osmar_Filho_20103647
 */

public class Params {
    public static final String WINDOW_TITLE = "My Game V1.0";
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    private static Params params = null;
    private Random rnd;

    private Params() {
        rnd = new Random();
    }

    public static Params getInstance() {
        if (params == null) {
            params = new Params();
        }
        return (params);
    }

    public int nextInt(int lim) {
        return (rnd.nextInt(lim));
    }

}

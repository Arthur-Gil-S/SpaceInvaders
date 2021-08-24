import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;

/**
 * Represents the Ship alien
 * 
 * @author Arthur_Gil - 20101221, Gilberto_Koerbes - 20204011,
 *         Osmar_Filho_20103647
 */
public class Ship extends BasicCharacter implements KeyboardCtrl {
    private int RELOAD_TIME = 100000000; // Time is in nanoseconds
    private int shot_timer = 0;

    Image ship = new Image("ship.png",0,70,true,true);

    public Ship(int px, int py) {
        super(px, py);
    }

    @Override
    public void start() {
        setLimH(20, Params.WINDOW_WIDTH - 20);
        setLimV(Params.WINDOW_HEIGHT - 100, Params.WINDOW_HEIGHT);
    }

    @Override
    public void Update(long deltaTime) {
        if (jaColidiu()) {
            Game.getInstance().setShipMorta();
            deactivate();

        }
        setPosX(getX() + getDirH() * getSpeed());
        if (shot_timer > 0)
            shot_timer -= deltaTime;
    }

    @Override
    public void OnInput(KeyCode keyCode, boolean isPressed) {
        if (keyCode == KeyCode.LEFT) {
            int dh = isPressed ? -3 : 0;
            setDirH(dh);
        }
        if (keyCode == KeyCode.RIGHT) {
            int dh = isPressed ? 3 : 0;
            setDirH(dh);
        }
        if (keyCode == KeyCode.SPACE) {
            if (shot_timer <= 0) {
                Game.getInstance().addChar(new ShotShip(getX() + 16, getY() - 32));
                shot_timer = RELOAD_TIME;
            }
        }
    }

    @Override
    public int getAltura() {
        return 80;
    }

    @Override
    public int getLargura() {
        return 32;
    }

    @Override
    public void Draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(ship, getX(), getY());
    }
}

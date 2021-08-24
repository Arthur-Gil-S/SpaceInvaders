import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Represents a shot that crosses the screen from bottom to up from our ship
 * 
 * @author Arthur_Gil - 20101221, Gilberto_Koerbes - 20204011,
 *         Osmar_Filho_20103647
 */

public class ShotShip extends Shot {
    public ShotShip(int px, int py) {
        super(px, py);
    }

    @Override
    public void start() {
        setDirV(-1);
        setSpeed(5);
    }

    @Override
    public void testaColisao(Character outro) {
        if (outro instanceof Shot) {
            return;
        } else {
            super.testaColisao(outro);
        }
    }

    @Override
    public void Update(long deltaTime) {
        if (jaColidiu()) {
            deactivate();
        } else {
            setPosY(getY() + getDirV() * getSpeed());
            // Se chegou na parte superior da tela ...
            if (getY() <= getLMinV()) {
                // Desaparece
                deactivate();
            }
        }
    }

    @Override
    public int getAltura() {
        return 16;
    }

    @Override
    public int getLargura() {
        return 8;
    }

    public void Draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Paint.valueOf("#00FF00"));
        graphicsContext.fillOval(getX(), getY(), 8, 16);
    }

}

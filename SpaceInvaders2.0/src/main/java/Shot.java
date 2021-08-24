import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Represents an abstract shot
 * 
 * @author Arthur_Gil - 20101221, Gilberto_Koerbes - 20204011,
 *         Osmar_Filho_20103647
 */

public abstract class Shot extends BasicCharacter {
    public Shot(int px, int py) {
        super(px, py);
    }

    @Override
    public void start() {

    }

    @Override
    public void testaColisao(Character outro) {

    }

    @Override
    public void Update(long deltaTime) {

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
    }
}

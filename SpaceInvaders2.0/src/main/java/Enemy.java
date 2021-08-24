import javafx.scene.canvas.GraphicsContext;

/**
 * Represents the basic enemy charachter
 * 
 * @author Arthur_Gil - 20101221, Gilberto_Koerbes - 20204011,
 *         Osmar_Filho_20103647
 */

public abstract class Enemy extends BasicCharacter {

    public Enemy(int startX, int startY) {
        super(startX, startY);
    }

    @Override
    public void start() {
    }

    @Override
    public void Update(long deltaTime) {
    }

    @Override
    public void Draw(GraphicsContext graphicsContext) {
    }
}

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

/**
 * Represents the Tank alien
 * 
 * @author Arthur_Gil - 20101221, Gilberto_Koerbes - 20204011,
 *         Osmar_Filho_20103647
 */

public class Tank extends Enemy {

    private Image tank;

    public Tank(int px, int py) {
        super(px, py);
        try {
            // Carrega a imagem ajustando a altura para 40 pixels
            // mantendo a proporção em ambas dimensões
            tank = new Image("Tank.png", 0, 50, true, true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void start() {
        setDirH(1);
    }

    @Override
    public void Update(long deltaTime) {
        if (jaColidiu()) {
            Game.getInstance().incPontos(1);
            deactivate();

        }

        else {
            setPosX(getX() + getDirH() * getSpeed());
            // Se chegou no lado direito da tela ...
            if (getX() >= getLMaxH() || getX() < getLMinH()) {
                // Reposiciona no lado esquerdo e ...
                setPosX(getLMinH());
                setDirH(getDirH() * 1);
                // Sorteia o passo de avanço [1,5]
                setSpeed(Params.getInstance().nextInt(1) + 3);
            }
        }
    }

    public void Draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(tank, getX(), getY());
    }

    @Override
    public void testaColisao(Character outro) {
        if (outro instanceof Enemy) {
            return;
        }
        if (outro instanceof ShotEnemy) {
            return;
        } else {
            super.testaColisao(outro);
        }
    }
}

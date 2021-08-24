import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Represents the Runner alien
 * 
 * @author Arthur_Gil - 20101221, Gilberto_Koerbes - 20204011,
 *         Osmar_Filho_20103647
 */

public class Runner extends Enemy {
    private Image image;
    private int level = Game.getInstance().getLevel();

    public Runner(int px, int py) {
        super(px, py);
        try {
            // Carrega a imagem ajustando a altura para 40 pixels
            // mantendo a proporção em ambas dimensões
            image = new Image("runner.png", 0, 40, true, true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void start() {
        setDirH(1);
        setSpeed(5);
    }

    @Override
    public void Update(long deltaTime) {
        if (jaColidiu()) {
            Game.getInstance().incPontos(2);
            deactivate();
        } else {
            setPosX(getX() + getDirH() * getSpeed());
            // Se chegou no lado direito da tela ...
            if (getX() >= getLMaxH() - 40 || getX() <= getLMinH()) {
                // Inverte a direção
                setDirH(getDirH() * -1);
                // Sorteia o passo de avanço [1,5]
                if (level == 1)
                    setSpeed(Params.getInstance().nextInt(2) + 4);
                if (level == 2)
                    setSpeed(Params.getInstance().nextInt(3) + 6);
                if (level == 3)
                    setSpeed(Params.getInstance().nextInt(4) + 8);

                // Se ainda não chegou perto do chão, desce
                if (getY() <= 555) {
                    setPosY(getY() + 25);

                }
                if (getY() > 555) {
                    Game.getInstance().setGameOver();
                }
            }

        }

    }

    @Override
    public void Draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image, getX(), getY());
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

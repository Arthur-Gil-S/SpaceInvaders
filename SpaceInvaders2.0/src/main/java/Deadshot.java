
/**
 * Represents the Deadshot alien
 * @author Arthur_Gil - 20101221, Gilberto_Koerbes - 20204011, Osmar_Filho_20103647
 */

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Deadshot extends Enemy {

    Image deadshot;
    private double RELOAD_TIME = 3000000000.0; // Time is in nanoseconds
    private double shot_timer = 0;
    private int level = Game.getInstance().getLevel();

    public Deadshot(int px, int py) {
        super(px, py);
        try {
            // Carrega a imagem ajustando a altura para 40 pixels
            // mantendo a proporção em ambas dimensões
            deadshot = new Image("deadshot.png", -10, 45, true, true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void start() {
        setDirV(1);
        int level = Game.getInstance().getLevel();
        if (level == 1)
            setSpeed(1);
        if (level == 2)
            setSpeed(3);
        if (level == 3)
            setSpeed(7);
    }

    @Override
    public void Update(long deltaTime) {
        if (jaColidiu()) {
            Game.getInstance().incPontos(3);
            deactivate();
        } else {
            setPosY(getY() + getDirV() * getSpeed());
            if (shot_timer > 0)
                shot_timer -= deltaTime;
            if (shot_timer <= 0) {
                Game.getInstance().addChar(new ShotEnemy(getX(), getY()));
                shot_timer = RELOAD_TIME;

            }
            if (getY() >= 550) {
                Game.getInstance().setGameOver();
                // PRINT GAME OVER
            }
        }
    }

    @Override
    public void Draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(deadshot, getX(), getY());
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

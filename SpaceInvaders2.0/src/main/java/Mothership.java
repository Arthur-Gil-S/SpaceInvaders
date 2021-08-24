import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

/**
 * Represents the Mothership alien
 * 
 * @author Arthur_Gil - 20101221, Gilberto_Koerbes - 20204011,
 *         Osmar_Filho_20103647
 */

public class Mothership extends Enemy {

    private int health = 10;
    Image mothership;
    private double RELOAD_TIME = 1500000000.0; // Time is in nanoseconds
    private double shot_timer = 0;

    public Mothership(int px, int py) {
        super(px, py);
        try {
            // Carrega a imagem ajustando a altura para 40 pixels
            // mantendo a proporção em ambas dimensões
            mothership = new Image("mothership.png", 0, 200, true, true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void start() {
        setDirH(1);
        setSpeed(1);
    }

    @Override
    public void Update(long deltaTime) {
        if (jaColidiu()) {
            health = health - 1;
            if (health == 0) {
                Game.getInstance().incPontos(50);
                deactivate();
                Game.getInstance().setMothership();
            }
        } else {
            setPosX(getX() + getDirH() * getSpeed());
            // Se chegou no lado direito da tela ...
            if (getX() >= (getLMaxH() - 350) || getX() <= (getLMinH())) {
                // Inverte a direção
                setDirH(getDirH() * -1);
                // Sorteia o passo de avanço [1,5]
                setSpeed(Params.getInstance().nextInt(1) + 5);
                // Se ainda não chegou perto do chão, desce
                if (shot_timer > 0)
                    shot_timer -= deltaTime;
                if (shot_timer <= 0) {
                    Game.getInstance().addChar(new ShotEnemy(getX(), getY()));
                    shot_timer = RELOAD_TIME;

                }

            }
        }
    }

    @Override
    public void Draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(mothership, getX(), getY());
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

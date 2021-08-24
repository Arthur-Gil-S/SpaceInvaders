import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import java.util.List;
import java.util.LinkedList;

/**
 * Handles the game lifecycle and behavior
 * @author Bernardo Copstein and Rafael Copstein
 */

public class Game {
    private static Game game = null;
    private Ship ship;
    private List<Character> activeChars;
    private boolean gameOver;
    private int pontos;
    private int health = 3;

    private boolean level2 = false;
    private boolean level3 = false;
    private int level = 0;

    private boolean mothership = false;
    private boolean shipMorta = false;

    private Game() {
        gameOver = false;
        pontos = 0;
    }

    public void setShipMorta() {
        shipMorta = true;
    }

    public void setMothership() {
        mothership = true;
    }

    public boolean getMothership() {
        return mothership;
    }

    public void setGameOver() {
        gameOver = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getPontos() {
        return pontos;
    }

    public void incPontos(int n) {
        pontos = pontos + n;
    }

    public int getLevel() {
        return level;
    }

    public static Game getInstance() {
        if (game == null) {
            game = new Game();
        }
        return (game);
    }

    public void addChar(Character c) {
        activeChars.add(c);
        c.start();
    }

    public int getHealth() {
        return health;
    }

    public int decHealth() {
        health = health - 1;
        return health;
    }

    public void eliminate(Character c) {
        activeChars.remove(c);
    }

    public void Start() {
        // Repositório de personagens
        activeChars = new LinkedList<>();

        // Adiciona o canhao
        ship = new Ship(400, 520);
        activeChars.add(ship);

        System.out.println("S T A R T");
        // Fase 1

        for (int i = 0; i < 3; i++) {
            activeChars.add(new Deadshot(Params.getInstance().nextInt(450), Params.getInstance().nextInt(1)));
        }

        for (int i = 0; i < 6; i++) {
            activeChars.add(new Tank(100 + (i * 60), 400));

        }

        for (int i = 0; i < 5; i++) {
            activeChars.add(new Runner(100 + (i * 60), i * 10));
        }

        level = 1;

        for (Character c : activeChars) {
            c.start();
        }
    }

    public void Update(long currentTime, long deltaTime) {
        if (gameOver) {
            return;
        }
        if (shipMorta && health != 0) {
            ship = new Ship(400, 520);
            activeChars.add(ship);
            shipMorta = false;
            health = health - 1;
            for (Character c : activeChars) {
                c.start();
            }
        }

        else if (health == 0) {
            gameOver = true;
        }

        if (pontos >= 25 && pontos < 50) {
            if (!level2) {
                level2();
            }
        }

        // Fase 3
        if (pontos >= 50 && pontos < 169) {
            if (!level3) {
                level3();
            }
        }

        for (int i = 0; i < activeChars.size(); i++) {
            Character este = activeChars.get(i);
            este.Update(deltaTime);
            for (int j = 0; j < activeChars.size(); j++) {
                Character outro = activeChars.get(j);
                if (este != outro) {
                    este.testaColisao(outro);
                }
            }
        }
    }

    public void OnInput(KeyCode keyCode, boolean isPressed) {
        ship.OnInput(keyCode, isPressed);
    }

    public void Draw(GraphicsContext graphicsContext) {
        for (Character c : activeChars) {
            c.Draw(graphicsContext);
        }
    }

    private void level3() {
        level3 = true;
        level = 3;
        for (int i = 0; i < 7; i++) {
            activeChars.add(new Deadshot(Params.getInstance().nextInt(450), Params.getInstance().nextInt(1)));
        }
        for (int i = 0; i < 10; i++) {
            activeChars.add(new Tank(100 + (i * 60), 400));
        }
        for (int i = 0; i < +6; i++) {
            activeChars.add(new Runner(100 + (i * 60), i * 10));
        }
        activeChars.add(new Mothership(30, 20));
        for (Character c : activeChars) {
            c.start();
        }
    }

    private void level2() {
        level2 = true;
        level = 2;
        for (int i = 0; i < 5; i++) {
            activeChars.add(new Deadshot(Params.getInstance().nextInt(450), Params.getInstance().nextInt(1)));
        }
        for (int i = 0; i < 8; i++) {
            activeChars.add(new Tank(100 + (i * 60), 400));
        }
        for (int i = 0; i < 5; i++) {
            activeChars.add(new Runner(100 + (i * 60), i * 10));
        }

        for (Character c : activeChars) {
            c.start();
        }
    }
}

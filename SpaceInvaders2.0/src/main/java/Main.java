import java.awt.Color;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * Handles window initialization and primary game setup
 * 
 * @author Arthur_Gil - 20101221, Gilberto_Koerbes - 20204011, Osmar_Filho_20103647
 */

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Initialize Window
        stage.setTitle(Params.WINDOW_TITLE);
        stage.setResizable(false);

        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Canvas canvas = new Canvas(Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);  

        Image imgFundo = new Image("space.png", 0, 0, true, true);

        root.getChildren().add(canvas);
  
        Game.getInstance().Start();

        scene.setOnKeyPressed((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), true);
        });

        scene.setOnKeyReleased((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), false);
        });
 
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        new AnimationTimer() {
            long lastNanoTime = System.nanoTime();

            @Override
            public void handle(long currentNanoTime) {
                long deltaTime = currentNanoTime - lastNanoTime;

                Game.getInstance().Update(currentNanoTime, deltaTime);
                
                gc.drawImage(imgFundo, 0, 0);
                gc.setFont(new Font("ARIAL", 35));
               
                gc.fillText("Pontos: " + Game.getInstance().getPontos(), 10, 40);
                gc.fillText("Vida: " + Game.getInstance().getHealth(), 10, 80);
                gc.fillText("Level: " + Game.getInstance().getLevel(), 10, 120);

                Game.getInstance().Draw(gc);
                if(Game.getInstance().getMothership()){
                    gc.fillText("W I N N E R", 280, 70);
                    Game.getInstance().setGameOver();
                }
                if (Game.getInstance().isGameOver()){
                    if (!Game.getInstance().getMothership()){
                        gc.fillText("G A M E" + " " + "O V E R", 250, 70);
                    }                 
                    Highscore hs = new Highscore();
                    hs.IncludeNewScore(Game.getInstance().getPontos());
                    ArrayList<Integer> highscores = hs.obtemScores();
                    gc.fillText("HIGHSCORES", 270, 110);
                    int i = 0;
                    
                    while(i<highscores.size()){
                        if(i<10){
                            int salvaScore = highscores.get(i);
                            gc.fillText(""+ (i+1) + " = " + salvaScore, 330, 150+(i*40));
                        }
                        i++;
                    }           
                    hs.persiste();  
                    stop();                    
                }
                lastNanoTime = currentNanoTime;
            }
        }.start();

        stage.show();
    }

    public static void main(String args[]) {
        launch();
    }
}

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Handles the game highscore
 * 
 * @author Arthur_Gil - 20101221, Gilberto_Koerbes - 20204011,
 *         Osmar_Filho_20103647
 */

public class Highscore {

    public ArrayList<Integer> Score;

    public Highscore() {
        Score = new ArrayList<Integer>();
        carrega();
    }

    public ArrayList obtemScores() {
        return Score;
    }

    public void IncludeNewScore(Integer score) {
        Score.add(score);
        OrdenaScore();
        for (int i = 0; i < Score.size(); i++) {
            System.out.println(Score.get(i));
        }
    }

    public void OrdenaScore() {
        Collections.sort(Score, Collections.reverseOrder());
    }

    public void carrega() {
        String fName = "highscore.txt";
        String currDir = Paths.get("").toAbsolutePath().toString();
        String nameComplete = currDir + "\\" + fName;
        Path path = Paths.get(nameComplete);

        try (Scanner sc = new Scanner(Files.newBufferedReader(path, StandardCharsets.UTF_8))) {
            while (sc.hasNext()) {
                String linha = sc.nextLine();
                int score = Integer.parseInt(linha);
                Score.add(score);
            }
            OrdenaScore();

        } catch (IOException x) {
            System.err.format("Erro de E/S: %s%n", x);
        }

    }

    public void persiste() {
        String fName = "highscore.txt";
        String currDir = Paths.get("").toAbsolutePath().toString();
        String nameComplete = currDir + "\\" + fName;
        Path path = Paths.get(nameComplete);
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path, StandardCharsets.UTF_8))) {
            int i = 0;
            while (i < Score.size()) {
                if (i == 10)
                    break;
                if (Score.get(i) != null && i < 10) {
                    int salvaScore = Score.get(i);
                    writer.println(salvaScore);
                }
                i++;
            }

        } catch (IOException x) {
            System.err.format("Erro de E/S: %s%n", x);
        }
    }
}

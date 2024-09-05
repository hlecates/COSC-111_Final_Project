import java.awt.*;
public class GameWorld {
    static void title(int[] starPositionx, int []starPositiony, int numberOfStars, int xScale, int yScale){
        //Draws background of stars at their randomized positions
        StdDraw.setPenColor(Color.black);
        StdDraw.filledRectangle(xScale/2.0,yScale/2.0, xScale/2.0, yScale/2.0);
        for (int i = 0; i < numberOfStars; i++){
            StdDraw.setPenColor(204,229, 255);
            StdDraw.setPenRadius(0.001);
            StdDraw.point(starPositionx[i], starPositiony[i]);
        }
        //Draw title and instructions
        StdDraw.setPenColor(255,255,102);
        //Font title = new Font("Arial", Font.BOLD, 55);
        //StdDraw.setFont(title);
        //StdDraw.text(xScale/2.0,yScale * 0.77, "SPACE INVADERS");
        StdDraw.picture(xScale/2.0, yScale * 0.77, "Title.png", 90,40);
        StdDraw.pause(550);
        Font instructions = new Font("Instructions", Font.ITALIC, 15);
        StdDraw.setFont(instructions);
        StdDraw.text(xScale/2.0, 0.55 * yScale, "A and D control the cannon's horizontal movements.");
        StdDraw.text(xScale/2.0, 0.52 * yScale, "SPACEBAR fires a projectile. Be careful though,");
        StdDraw.text(xScale/2.0, 0.49 * yScale, "only one projectile can be fired at a time. A ");
        StdDraw.text(xScale/2.0, 0.46 * yScale, "fleet of invaders will be advancing down the screen.");
        StdDraw.text(xScale/2.0, 0.43 * yScale, "Take cover beneath the barriers to escape the onslaught.");
        StdDraw.text(xScale/2.0, 0.40 * yScale, "Periodically powerups will fall from the top,");
        StdDraw.text(xScale/2.0, 0.37 * yScale, "granting the player powerful abilities");
        StdDraw.text(xScale/2.0, 0.34 * yScale, "When all the invaders are defeated,");
        StdDraw.text(xScale/2.0, 0.31 * yScale, "the player can advance to the next level.");
        StdDraw.pause(550);
        Font belowtitle = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(belowtitle);
        StdDraw.text(xScale/2.0, yScale * 0.20, "Press the SPACEBAR to begin");
    }
    static void gameScreen (int[] starPositionx, int []starPositiony, int score, int lives, int[]time, int xScale, int yScale){
        //Draw Stars from their randomized positions
        StdDraw.setPenColor(Color.black);
        StdDraw.filledRectangle(xScale/2.0,yScale/2.0, xScale/2.0, yScale/2.0);
        for (int i = 0; i < 90; i++){
            StdDraw.setPenColor(204,229, 255);
            StdDraw.setPenRadius(0.0005);
            StdDraw.point(starPositionx[i], starPositiony[i]);
        }
        //Draw the scores and lives
        StdDraw.setPenColor(255,255,102);
        Font ScoreLives = new Font("Arial", Font.BOLD, 10);
        StdDraw.setFont(ScoreLives);
        StdDraw.text(8,0.95 * yScale,"Score: " + score);
        StdDraw.text(0.90 * xScale,0.95 * yScale,"Lives: ");
        for (int i = 0; i < lives; i++){
            StdDraw.filledCircle((0.95 * xScale) + i, 0.95 * yScale, 0.5);
        }
        if (time[1] < 10){
            StdDraw.text(0.5 * xScale, 0.95 * yScale, time[0] + ":0" + time[1]);
        } else {
            StdDraw.text(0.5 * xScale, 0.95 * yScale, time[0] + ":" + time[1]);
        }
    }
    static void levelFinish(int[] starPositionx, int []starPositiony, int score, int[]time, int difficulty, int xScale, int yScale) {
        //Draw Stars from their randomized positions
        StdDraw.setPenColor(Color.black);
        StdDraw.filledRectangle(xScale / 2.0, yScale / 2.0, xScale / 2.0, yScale / 2.0);
        for (int i = 0; i < 90; i++) {
            StdDraw.setPenColor(204, 229, 255);
            StdDraw.setPenRadius(0.0005);
            StdDraw.point(starPositionx[i], starPositiony[i]);
        }
        //Draw finals scores and instructions
        StdDraw.setPenColor(255,255,102);
        Font title = new Font("Arial", Font.BOLD, 40);
        StdDraw.setFont(title);
        StdDraw.text(0.5 * xScale, 0.65 * yScale, "LEVEL COMPLETE");
        Font belowtitle = new Font("Arial", Font.BOLD, 20);
        StdDraw.setFont(belowtitle);
        StdDraw.text(0.5 * xScale, 0.55 * yScale, "Final Score: " + score);
        if (time[1] < 10) {
            StdDraw.text(0.5 * xScale, 0.45 * yScale, "Time Elapsed: " + time[0] + ":0" + time[1]);
        } else {
            StdDraw.text(0.5 * xScale, 0.45 * yScale, "Time Elapsed: " + time[0] + ":" + time[1]);
        }
        StdDraw.text(0.5 * xScale, 0.35 * yScale, "Press the SPACEBAR to play LEVEL " + (difficulty + 1));
    }
    static void finalScreen(int[] starPositionx, int []starPositiony, int score, int[]time, int xScale, int yScale){
        //Draw Stars from their randomized positions
        StdDraw.setPenColor(Color.black);
        StdDraw.filledRectangle(xScale/2.0,yScale/2.0, xScale/2.0, yScale/2.0);
        for (int i = 0; i < 90; i++){
            StdDraw.setPenColor(204,229, 255);
            StdDraw.setPenRadius(0.0005);
            StdDraw.point(starPositionx[i], starPositiony[i]);
        }
        //Draw finals scores and instructions
        StdDraw.setPenColor(255, 255, 102);
        //Font title = new Font("Arial", Font.BOLD, 40);
        //StdDraw.setFont(title);
        //StdDraw.text(0.5 * xScale,0.65 * yScale,"GAME OVER");
        StdDraw.picture(xScale * 0.541, 0.6 * yScale, "GameOver.png", 50, 40);
        Font belowtitle = new Font("Arial", Font.PLAIN, 20);
        StdDraw.setFont(belowtitle);
        StdDraw.text(0.5 * xScale, 0.45 * yScale, "Final Score: " + score);
        if (time[1] < 10){
            StdDraw.text(0.5 * xScale, 0.4 * yScale, "Time Elapsed: " + time[0] + ":0" + time[1]);
        } else {
            StdDraw.text(0.5 * xScale, 0.4 * yScale, "Time Elapsed: " + time[0] + ":" + time[1]);
        }
        StdDraw.text(0.5 * xScale, 0.30 * yScale, "Press the SPACEBAR to play again");
    }
}
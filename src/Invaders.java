import jdk.jshell.execution.JdiInitiator;
public class Invaders {
    Alien[][] aliens;
    private boolean direction;
    private int numberOfRows;
    private int numberOfAliensInRow;
    private int xScale;
    private int yScale;

    public Invaders(int difficulty, int xScale, int yScale){
        this.numberOfRows = difficulty + 3;
        numberOfAliensInRow = 11;
        aliens = new Alien[numberOfAliensInRow][numberOfRows];
        direction = true;
        this.yScale = yScale;
        this.xScale = xScale;
        //Creates the array of invaders, assigning an alien object to each value
        for (int i = 0; i < numberOfAliensInRow; i++){
            for (int j = 0; j < numberOfRows; j++){
                aliens[i][j] = new Alien((7 * i) + 20, ((7 * j) + 120) - (6 * difficulty), xScale, yScale);
            }
        }
    }
    public void update(int score){
        //Moves each alien at each value of the invaders array left and right
        for (int i = 0; i < numberOfAliensInRow; i++) {
            for (int j = 0; j < numberOfRows; j++) {
                if (direction) {
                    aliens[i][j].alienMoveRight(score);
                } else {
                    aliens[i][j].alienMoveLeft(score);
                }
            }
        }
        //If alien in final column of array has reached right/left of the screen, shifts aliens down and reversing their horizontal movement
        if (changeYDirection()) {
            for (int i = 0; i < numberOfAliensInRow; i++) {
                for (int j = 0; j < numberOfRows; j++) {
                    aliens[i][j].alienMoveDown();
                }
            }
        }
    }
    public boolean changeYDirection() {
        //Checks if aliens on final column of array have reached the right/left of the screen, regardless of lifeState
        if (this.aliens[numberOfAliensInRow - 1][numberOfRows - 1].getX() > 100) {
            direction = false;
            return true;
        } else if (aliens[0][numberOfRows - 1].getX() < 10) {
            direction = true;
            return true;
        }
        return false;
    }
    public boolean reachedBottom(){
        //Checks if the bottom alien has reached y-level 20
        for (int i = 0; i < numberOfAliensInRow; i++) {
            for (int j = 0; j < numberOfRows; j++) {
                if (aliens[i][j].getLifeState() && aliens[i][j].getY() < 20){
                    return true;
                }
            }
        }
        return false;
    }
    public int getNumberOfAliensInRow(){
        return numberOfAliensInRow;
    }
    public int getNumberOfRows(){
        return numberOfRows;
    }
    public void drawInvaders(){
        for (int i = 0; i < numberOfAliensInRow; i++) {
            for (int j = 0; j < numberOfRows; j++) {
                aliens[i][j].drawAlien();
            }
        }
    }
}
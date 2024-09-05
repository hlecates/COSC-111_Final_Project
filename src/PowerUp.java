public class PowerUp {
    private double x;
    private double y;
    private int rand;
    private double speed;
    public PowerUp(int x, int y, int rand){
    this.x = x;
    this.y = y;
    speed = 0.3;
    this.rand = rand;
    }
    public void updatePowerUp(){
        this.y -= speed;
    }
    public boolean reachedBottom(){
        //Checks if the PowerUp has reached the bottom of the screen
        if (this.y < 0){
            return true;
        }
        return false;
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public int getRand(){
        return rand;
    }
    public void drawPowerUp(){
        if (this.rand == 0){
            StdDraw.picture(this.x, this.y, "HeartPowerUp.png", 5, 5);
        } else if (this.rand == 1){
            StdDraw.picture(this.x, this.y, "ShieldPowerUp.png", 5, 5);
        } else if (this.rand == 2){
            StdDraw.picture(this.x, this.y, "BulletPowerUp.png", 8, 8, 180);
        }

    }
}
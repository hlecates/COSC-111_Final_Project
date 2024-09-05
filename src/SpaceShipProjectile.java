import java.awt.*;
public class SpaceShipProjectile {
    private double y;
    private  double x;
    private int xScale;
    private int yScale;
    private double speed;
    public SpaceShipProjectile(double x, int xScale, int yScale){
        this.y = 10;
        this.x = x;
        this.yScale = yScale;
        this.xScale = xScale;
        speed = 1.7;
    }
    public void updateSpaceShipProjectile(){
        this.y += speed;
    }
    public boolean reachedTop(){
        //Checks if the player's SpaceShip projectile has reached the top of the screen
        if (y > yScale){
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
    public void drawSpaceShipProjectile(boolean projBig){
        if (! projBig) {
            StdDraw.setPenColor(Color.white);
            StdDraw.filledCircle(x, y, 0.5);
        } else if (projBig){
            StdDraw.picture(this.x, this.y, "BulletPowerUp.png", 10, 12);
        }
    }
}
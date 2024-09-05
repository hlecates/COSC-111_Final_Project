import java.awt.*;

public class AlienProjectile {
    private double y;
    private double x;
    private double speed;
    private int xScale;
    private int yScale;
    public AlienProjectile(double x, double y, int xScale, int yScale){
        this.y = y;
        this.x = x;
        this.yScale = yScale;
        this.xScale = xScale;
        speed = 0.75;
    }
    public void updateAlienProjectile(){
        this.y -= speed;
    }
    public boolean reachedBottom(){
        //Checks if Alien Projectile has reached the bottom of the screen
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
    public void drawAlienProjectile(){
        StdDraw.setPenColor(Color.orange);
        StdDraw.filledCircle(x, y, 0.5);
    }
}
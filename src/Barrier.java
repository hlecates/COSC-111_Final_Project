import java.awt.*;

public class Barrier {
    public double x;
    public int y;
    public int health;
    public Barrier(double x, int y){
        this.x = x;
        this.y = y;
        this.health = 10;
    }
    public boolean alienProjectileCollision(AlienProjectile pA){
        //Checks collisions between an Alien Projectile and a barrier
        if (pA != null){
            double changeInX = Math.pow(this.x - pA.getX(), 2);
            double changeInY = Math.pow(this.y - pA.getY(), 2);
            //double distance = Math.sqrt(changeInX + changeInY);
            if (changeInX < 30 && changeInY < 1) {
                //Decrements health by 1
                this.health -= 1;
                return true;
            }
        }
        return false;
    }
    public boolean spaceShipProjectileCollision(SpaceShipProjectile pS, boolean bigProj){
        //Checks collisions between an SpaceShip Projectile and a barrier
        if (! bigProj && pS != null && health > 0){
            double changeInX = Math.pow(this.x - pS.getX(), 2);
            double changeInY = Math.pow(this.y - pS.getY(), 2);
            //double distance = Math.sqrt(changeInX + changeInY);
            if (changeInX < 30 && changeInY < 1) {
                //Decrements health by 1
                this.health -= 1;
                return true;
            }
        }
        //Checks collisions between a Big SpaceShip and a barrier
        if (bigProj && pS != null && health > 0){
            double changeInX = Math.pow(this.x - pS.getX(), 2);
            double changeInY = Math.pow(this.y - pS.getY(), 2);
            //double distance = Math.sqrt(changeInX + changeInY);
            if (changeInX < 40 && changeInY < 1) {
                //Decrements health by 1
                this.health -= 1;
                return true;
            }
        }
        return false;
    }
    public boolean powerUpCollision(PowerUp p){
        //Checks collisions between a PowerUp and a barrier
        if (p != null) {
            double changeInX = Math.pow(this.x - p.getX(), 2);
            double changeInY = Math.pow(this.y - p.getY(), 2);
            //double distance = Math.sqrt(changeInX + changeInY);
            if (changeInX < 30 && changeInY < 4) {
                //DOES NOT decrement by 1
                return true;
            }
        }
        return false;
    }
    public int getHealth(){
        return this.health;
    }
    public void draw(){
        if (health > 0) {
            StdDraw.picture(this.x, this.y, "Barrier.png", 12,5);
            StdDraw.setPenColor(Color.white);
            StdDraw.text(this.x, this.y - 2.5, String.valueOf(health));
        }
    }
}
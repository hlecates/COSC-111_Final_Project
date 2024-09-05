public class SpaceShip {
    private double x;
    private double y;
    private boolean shield;
    private double speed;
    private int xScale;
    private int yScale;

    public SpaceShip(int xScale, int yScale) {
        this.x = 55;
        this.y = 7;
        speed = 0.5;
        this.yScale = yScale;
        this.xScale = xScale;
        shield = false;
    }

    public void moveRight() {
        if (this.x < 105) {
            this.x += speed;
        }
    }

    public void moveLeft() {
        if (this.x > 5) {
            this.x -= speed;
        }
    }

    public boolean collisionSpaceShip(AlienProjectile pA) {
        //Checks collisions between the player's SpaceShip and an alien projectile
        if (pA != null) {
            double changeInX = Math.pow(this.x - pA.getX(), 2);
            double changeInY = Math.pow(this.y - pA.getY(), 2);
            double distance = Math.sqrt(changeInX + changeInY);
            if (distance < 3.5) {
                return true;
            }
        }
        return false;
    }

    public boolean powerUpCollision(PowerUp p) {
        //Checks collisions between the player's SpaceShip and a PowerUp
        if (p != null) {
            double changeInX = Math.pow(this.x - p.getX(), 2);
            double changeInY = Math.pow(this.y - p.getY(), 2);
            double distance = Math.sqrt(changeInX + changeInY);
            if (distance < 5.5) {
                return true;
            }
        }
        return false;
    }

    public boolean shieldCollision(AlienProjectile pA) {
        //Checks collisions between Shield PowerUp and an alien projectile
        if (pA != null) {
            double changeInX = Math.pow(this.x - pA.getX(), 2);
            double changeInY = Math.pow((this.y + 3) - pA.getY(), 2);
            double distance = Math.sqrt(changeInX + changeInY);
            if (distance < 5) {
                return true;
            }
        }
        return false;
    }
    public void shieldTrue() {
        shield = true;
    }
    public void shieldFalse() {
        shield = false;
    }
    public double getX() {
        return x;
    }
    public boolean getShield() {
        return shield;
    }
    public void drawSpaceShip() {
        if (shield) {
            StdDraw.setPenColor(51, 255, 255);
            StdDraw.setPenRadius(0.009);
            StdDraw.arc(this.x, this.y + 3, 5, 20, 160);
        }
        StdDraw.picture(this.x, this.y, "SpaceShip.png", 15, 15);
    }
}
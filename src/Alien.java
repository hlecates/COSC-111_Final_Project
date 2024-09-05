public class Alien {
    private double x;
    private double y;
    private double capSpeed;
    private double minSpeed;
    private boolean lifeState;
    private int xScale;
    private int yScale;
    public AlienProjectile pA;
    public Alien(int x, int y, int xScale, int yScale){
        this.x = x;
        this.y = y;
        this.yScale = yScale;
        this.xScale = xScale;
        minSpeed = 0.07;
        capSpeed = 0.13;
        lifeState = true;
        pA = null;
    }
    public void alienMoveRight(int score){
        //Moves the invaders right at a determined rate (function of score)
        //At a score of about 120 the min speed is exceeded
        //At about a score of 4500 the max speed of 0.13 is reached
        double speed = (1 / 19.0) * (Math.log(1 + (score/15.2)));
        if (speed < minSpeed){
            x += minSpeed;
        } else if (speed > capSpeed) {
            x += capSpeed;
        } else {
            x += speed;
        }
    }
    public void alienMoveLeft(int score) {
        //Moves the invaders left at a determined rate (function of score)
        //At a score of about 120 the min speed is exceeded
        //At about a score of 4500 the max speed of 0.13 is reached
        double speed = (1 / 19.0) * (Math.log(1 + (score/15.2)));
        if (speed < minSpeed){
            x -= minSpeed;
        } else if (speed > capSpeed) {
            x -= capSpeed;
        } else {
            x -= speed;
        }
    }
    public void alienMoveDown(){
        y -= 2.5;
    }
    public boolean collisionAlien(SpaceShipProjectile pS, boolean nextProjBig) {
        //Checks collisions between Big SpaceShip Projectile and an alien
        if (nextProjBig && pS != null && lifeState) {
            double changeInXBig = Math.pow((this.x - pS.getX()), 2);
            double changeInYBig = Math.pow((this.y - pS.getY()), 2);
            double distanceBig = Math.sqrt(changeInXBig + changeInYBig);
            if (distanceBig < 7) {
                return true;
            }
        //Checks collisions between SpaceShip Projectile and an alien
        } else if (!nextProjBig && pS != null && lifeState) {
            double changeInX = Math.pow((this.x - pS.getX()), 2);
            double changeInY = Math.pow((this.y - pS.getY()), 2);
            double distance = Math.sqrt(changeInX + changeInY);
            if (distance < 2.55) {
                return true;
            }
        }
        return false;
    }

    public boolean bottomAlien(int k, int j, Invaders i){
        //Checks if a specified alien is currently at the bottom (ie no other aliens are below it)
        if (k == 0){
            //Automatically returns true if alien is on bottom row
            return true;
        }
        if (i.aliens[j][k].getY() > i.aliens[j][k - 1].getY() &&  ! i.aliens[j][k - 1].lifeState){
            return true;
        }
        return false;
    }
    public void newProjectile(){
        //If the aliens is "alive" creates a new projectile
        if (this.lifeState){
            pA = new AlienProjectile(this.x, this.y, xScale, yScale);
        }
    }
    public void setDead(){
        this.lifeState = false;
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public boolean getLifeState(){
        return this.lifeState;
    }
    public void setNull(){
        pA = null;
    }
    public void drawAlien() {
        if (lifeState){
            StdDraw.picture(this.x, this.y, "Invaders.png", 4.5, 4.5);
        }
    }
}
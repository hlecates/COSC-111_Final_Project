import java.awt.event.KeyEvent;
public class Project {


    /** A basic implementation of the game "Space Invaders," done for the first
     * introductory computer science class.**/


    public static void main(String[] args) {
        //Sets the initial difficulty (number of rows + 3 of aliens)
        if (args.length != 1) {
            System.out.println("Error: Only one integer is accepted.");
            System.exit(1);
        }
        int difficulty = Integer.parseInt(args[0]);
        if (difficulty < 0 || difficulty > 6) {
            System.out.println("Error: the initial difficulty must be between 0 and 6.");
            System.exit(1);
        }

        //Sets the scale of the display screen (The coordinates for GameWorld are based on functions of these inputs)
        int xScale = 110;
        int yScale = 150;

        //Creates randomized position for the stars
        int numberOfStars = 800;
        int[] starPositionx = new int[numberOfStars];
        for (int i = 0; i < starPositionx.length; i++) {
            starPositionx[i] = (int) (Math.random() * xScale);
        }
        int[] starPositiony = new int[numberOfStars];
        for (int i = 0; i < starPositiony.length; i++) {
            starPositiony[i] = (int) (Math.random() * yScale);
        }

        //Establishes StdDraw
        StdDraw.setCanvasSize(xScale * 5, yScale * 5);
        StdDraw.setXscale(0, xScale);
        StdDraw.setYscale(0, yScale);
        GameWorld.title(starPositionx, starPositiony, numberOfStars, xScale, yScale);

        while (true) {

            //Initializes Invaders
            Invaders i = new Invaders(difficulty, xScale, yScale);

            //Initializes SpaceShip
            SpaceShip s = new SpaceShip(xScale, yScale);

            //Initializes the SpaceShip projectile(s)
            SpaceShipProjectile pS = null;

            //Initializes the barriers
            Barrier[] b = new Barrier[3];
            for (int j = 0; j < 3; j++) {
                b[j] = new Barrier(21.7 + (33.3 * j), 20);
            }
            //Initializes the PowerUps
            PowerUp p = null;
            boolean nextProjBig = false;
            int bigProjTimeElapsed = 0;
            int shieldTimeElapsed = 0;

            //Initialize score, lives, and time
            int score;
            int lives = 3;
            int[] time = new int[2];
            int count = 0;

            //Title Screen
            while (!StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
                StdDraw.pause(20);
            }

            StdDraw.enableDoubleBuffering();

            //Creates a variable restart, that while true allows each level to begin
            // when false the following while loop is exited, reinitializing the previous components
            boolean restart = true;

            while (restart) {
                //Score
                score = 0;
                for (int j = 0; j < i.getNumberOfAliensInRow(); j++) {
                    for (int k = 0; k < i.getNumberOfRows(); k++) {
                        if (!i.aliens[j][k].getLifeState()) {
                            score += 10 + (k * 10);
                        }
                    }
                }

                //Count increments iteration of the while loop
                //Time only increments when (count % 53 == 0), which is approximately 1 second
                count++;
                if (count % 53 == 0) {
                    time[1] += 1;
                    if (time[1] % 60 == 0) {
                        time[0] += 1;
                        time[1] = 0;
                    }
                }

                StdDraw.clear();
                GameWorld.gameScreen(starPositionx, starPositiony, score, lives, time, xScale, yScale);
                i.drawInvaders();
                s.drawSpaceShip();
                for (int j = 0; j < 3; j++) {
                    b[j].draw();
                }

                //Moves the invaders
                i.update(score);

                //Moves Spaceships based on keyboard inputs
                if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
                    s.moveRight();
                }
                if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
                    s.moveLeft();
                }

                //Creates and updates a player projectile
                if (StdDraw.isKeyPressed((KeyEvent.VK_SPACE)) && (pS == null) && (count > 7)) {
                    pS = new SpaceShipProjectile(s.getX(), xScale, yScale);
                    StdAudio.playInBackground("SpaceShipProjectile.wav");
                }
                if (pS != null) {
                    pS.drawSpaceShipProjectile(nextProjBig);
                    pS.updateSpaceShipProjectile();
                }
                //Set player projectile to null if it reaches the top of the screen
                if (pS != null && pS.reachedTop()) {
                    pS = null;
                }

                //Collisions between player projectiles and aliens
                if (pS != null) {
                    for (int j = 0; j < i.getNumberOfAliensInRow(); j++) {
                        for (int k = 0; k < i.getNumberOfRows(); k++) {
                            if (i.aliens[j][k].collisionAlien(pS, nextProjBig)) {
                                pS = null;
                                i.aliens[j][k].setDead();
                                //Plays a sound effect
                                StdAudio.playInBackground("InvaderDefeated.wav");
                                //Prints message
                                System.out.println("Invader Defeated");
                            }
                        }
                    }
                }

                //Creates alien projectiles
                for (int j = 0; j < i.getNumberOfAliensInRow(); j++) {
                    for (int k = 0; k < i.getNumberOfRows(); k++) {
                        int rand = (int) (Math.random() * 120);
                        if (rand == 0 && i.aliens[j][k].pA == null && i.aliens[j][k].bottomAlien(k, j, i)) {
                            i.aliens[j][k].newProjectile();
                        }
                    }
                }
                //Updates alien projectiles
                for (int j = 0; j < i.getNumberOfAliensInRow(); j++) {
                    for (int k = 0; k < i.getNumberOfRows(); k++) {
                        if (i.aliens[j][k].pA != null) {
                            i.aliens[j][k].pA.updateAlienProjectile();
                            i.aliens[j][k].pA.drawAlienProjectile();
                        }
                        //Sets alien projectiles to null if they reach the bottom of the screen
                        if (i.aliens[j][k].pA != null && i.aliens[j][k].pA.reachedBottom()) {
                            i.aliens[j][k].setNull();
                        }
                    }
                }

                //Collisions between aliens projectiles and player
                for (int j = 0; j < i.getNumberOfAliensInRow(); j++) {
                    for (int k = 0; k < i.getNumberOfRows(); k++) {
                        if (i.aliens[j][k].pA != null && s.collisionSpaceShip(i.aliens[j][k].pA)) {
                            i.aliens[j][k].pA = null;
                            lives -= 1;
                            StdAudio.playInBackground("LostLife.wav");
                            System.out.println("Player Lost A Life");
                        }
                    }
                }

                //Collisions between barriers and all projectiles
                for (int j = 0; j < 3; j++) {
                    //Checks collisions between player projectiles and barriers
                    if (b[j].spaceShipProjectileCollision(pS, nextProjBig) && pS != null && b[j].getHealth() > 0) {
                        pS = null;
                    }
                    //Checks collisions between alien projectiles and barriers
                    for (int t = 0; t < i.getNumberOfAliensInRow(); t++) {
                        for (int k = 0; k < i.getNumberOfRows(); k++) {
                            if (b[j].alienProjectileCollision(i.aliens[t][k].pA) && i.aliens[t][k].pA != null && b[j].getHealth() > 0) {
                                i.aliens[t][k].pA = null;
                            }
                        }
                    }
                }

                //Creates a PowerUp
                int randPowerUp = (int) (Math.random() * 400);
                if (p == null && randPowerUp == 0) {
                    int rand = (int) (Math.random() * 3);
                    p = new PowerUp((5 + (int) (Math.random() * 95)), 140, rand);
                }
                //Updates the PowerUp
                if (p != null) {
                    p.drawPowerUp();
                    p.updatePowerUp();
                }
                //Checks if the PowerUp has reached the bottom of the screen
                if (p != null && p.reachedBottom()) {
                    p = null;
                }

                //Collisions between barriers and PowerUps
                for (int j = 0; j < 3; j++) {
                    if (b[j].powerUpCollision(p) && p != null & b[j].getHealth() > 0) {
                        p = null;
                    }
                }

                //Collisions between PowerUps and players
                if (s.powerUpCollision(p) && p != null) {
                    StdAudio.playInBackground("PowerUpCollection.wav");
                    //Determines the ability of the PowerUp
                    if (p.getRand() == 0) {
                        //Adds one additional life, up to 5 possible
                        if (lives < 5) {
                            lives += 1;
                        }
                    //Creates a shield to block all invader projectiles
                    } else if (p.getRand() == 1) {
                        s.shieldTrue();
                    //Allows next projectiles fired to be larger, making it much easier to hit the invaders
                    } else if (p.getRand() == 2) {
                        nextProjBig = true;
                    }
                    p = null;
                    System.out.println("Powerup Collected");
                }

                //Checks the elapsed time of the Shield PowerUp
                //If approximately 10 seconds has elapsed, sets to false
                if (s.getShield()) {
                    shieldTimeElapsed += 1;
                    if (shieldTimeElapsed % 550 == 0) {
                        s.shieldFalse();
                    }
                }
                //Checks the elapsed time of the Big Projectile PowerUp
                //If approximately 10 seconds has elapsed, sets to false
                if (nextProjBig) {
                    bigProjTimeElapsed += 1;
                    if (bigProjTimeElapsed % 550 == 0) {
                        nextProjBig = false;
                    }
                }

                //Collisions between shield PowerUp and alien projectiles
                for (int j = 0; j < i.getNumberOfAliensInRow(); j++) {
                    for (int k = 0; k < i.getNumberOfRows(); k++) {
                        if (i.aliens[j][k].pA != null && s.getShield() && s.shieldCollision(i.aliens[j][k].pA)) {
                            i.aliens[j][k].pA = null;
                        }
                    }
                }

                //Checks if all invaders have been defeated
                int numberAliensDead = 0;
                for (int j = 0; j < i.getNumberOfAliensInRow(); j++) {
                    for (int k = 0; k < i.getNumberOfRows(); k++) {
                        if (!i.aliens[j][k].getLifeState()) {
                            numberAliensDead++;
                        }
                    }
                }

                //Ends the game if all invaders are defeated, allowing player to advance to the next level
                if (numberAliensDead == (i.getNumberOfAliensInRow() * i.getNumberOfRows())) {
                    difficulty++;
                    GameWorld.levelFinish(starPositionx, starPositiony, score, time, difficulty, xScale, yScale);
                    restart = false;
                    System.out.println("Level Complete: All Invaders Defeated\n");
                }

                //Ends the game if the bottommost alien reaches the bottom (y level 20)
                if (i.reachedBottom()) {
                    GameWorld.finalScreen(starPositionx, starPositiony, score, time, xScale, yScale);
                    restart = false;
                    System.out.println("Game Over: Invaders Reached the Bottom\n");
                }

                //Ends the game if no lives are left
                if (lives == 0) {
                    GameWorld.finalScreen(starPositionx, starPositiony, score, time, xScale, yScale);
                    restart = false;
                    System.out.println("Game Over: No Lives Left\n");
                }

                StdDraw.show();
                StdDraw.pause(12);
            }
        }
    }
}
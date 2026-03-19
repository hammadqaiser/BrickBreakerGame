import java.awt.event.*;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import javax.swing.Timer;
import java.awt.BasicStroke;
import java.awt.Graphics2D;

class GameComponents extends JPanel implements KeyListener, ActionListener {
	/* KeyListener for detecting the Keyboard Keys & ActionListener for moving the ball.
	 * The abstract methods of these interfaces must be implemented in this class.
	 * Otherwise, compiler will give an error.
	 */
    private boolean play = false; // so game will not start by itself.
    private int score = 0;
    private int totalBricks = 10;
    private Timer timer;
    private int delay = 5; // how fast ball should move.
    
    private int paddleX = 300;
    
    /*private void setRandomBallPosition() {
        Random rand = new Random();
        ballposX = rand.nextInt(670) + 1; // Random x position within the game width
        // Calculate the minimum y position below the bricks
        int minYPosition = (map.map.length * map.brickHeight) + 60;
        ballposY = rand.nextInt(550 - minYPosition) + minYPosition; // Random y position below the bricks
    }*/
    
    private int ballposX = 240;
    private int ballposY = 450;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private int level = 1; // Add level variable

    private BrickGenerator map;

    public GameComponents() {        
        map = new BrickGenerator(2, 5, Color.magenta);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
    }

    public void paint(Graphics g) {            
        // background
        g.setColor(Color.black);
        g.fillRect(1, 1, 710, 610);

        // drawing map
        map.draw((Graphics2D) g);

        // borders
        g.setColor(Color.yellow);
        g.setFont(new Font("serif", Font.BOLD, 100));
        g.fillRect(0, 0, 3, 592); // Left border
        g.fillRect(0, 0, 692, 3); // Top border
        g.fillRect(691, 0, 3, 592); // Right Border

        // the scores         
        g.setColor(Color.cyan);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + score, 590, 30);

        // the levels
        g.setColor(Color.cyan);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Level " + level, 20, 30);

        // the paddle
        g.setColor(Color.white);
        g.fillRect(paddleX, 550, 120, 14);

        // the ball
        g.setColor(Color.yellow);
        g.fillOval(ballposX, ballposY, 16, 16);

        // when you won the game
        if (totalBricks <= 0) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.blue);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("You Won", 270, 250);

            g.setColor(Color.blue);
            g.setFont(new Font("serif", Font.BOLD, 25));           
            g.drawString("Your Score: " + score, 240, 280);

            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 20));           
            g.drawString("Press 1 for Level 1", 235, 320); 
            
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 20));           
            g.drawString("Press 2 for Level 2", 235, 350);
            
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 20));           
            g.drawString("Press 3 for Level 3", 235, 380);
        }

        // when you lose the game
        if (ballposY > 570) {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.blue);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("Game Over", 260, 250);

            g.setColor(Color.blue);
            g.setFont(new Font("serif", Font.BOLD, 25));           
            g.drawString("Your Score: " + score, 245, 280);

            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 20));           
            g.drawString("Press 1 for Level 1", 235, 320); 
            
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 20));           
            g.drawString("Press 2 for Level 2", 235, 350);
            
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 20));           
            g.drawString("Press 3 for Level 3", 235, 380);
        }

        g.dispose();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {        
            if (paddleX >= 575) {
                paddleX = 575;
            } else {
                moveRight();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {          
            if (paddleX < 10) {
                paddleX = 10;
            } else {
                moveLeft();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_1) {          
            if (!play) {
                play = true;
               // setRandomBallPosition(); // Set random ball position
                ballposX = 240;
                ballposY = 450;
                ballXdir = -1;
                ballYdir = -2;
                paddleX = 260;
                score = 0;
                totalBricks = 10;
                map = new BrickGenerator(2, 5, Color.magenta);
                delay = 5;
                level = 1; // Set level to 1
                repaint();
            }
        }       

        if (e.getKeyCode() == KeyEvent.VK_2) {          
            if (!play) {
                play = true;
                //setRandomBallPosition(); // Set random ball position
                ballposX = 120;
                ballposY = 350;
                ballXdir = -3;
                ballYdir = -4;
                paddleX = 260;
                score = 0;
                totalBricks = 21;
                map = new BrickGenerator(3, 7, Color.green);
                delay = 3;
                level = 2; // Set level to 2
                repaint();
            }
        }   

        if (e.getKeyCode() == KeyEvent.VK_3) {          
            if (!play) {
                play = true;
                //setRandomBallPosition(); // Set random ball position
                ballposX = 120;
                ballposY = 350;
                ballXdir = -5;
                ballYdir = -6;
                paddleX = 260;
                score = 0;
                totalBricks = 50;
                map = new BrickGenerator(5, 10, Color.red);
                delay = 1;
                level = 3; // Set level to 3
                repaint();
            }
        }       
    }

    // We do not need these two abstract methods of interface KeyListener
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public void moveRight() {
        play = true;
        paddleX += 20;    
    }

    public void moveLeft() {
        play = true;
        paddleX -= 20;     
    }

    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {            
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(paddleX, 550, 30, 8))) {
                ballYdir = -ballYdir;
                ballXdir = -2;
            } else if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(paddleX + 70, 550, 30, 8))) {
                ballYdir = -ballYdir;
                ballXdir = ballXdir + 1;
            } else if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(paddleX + 30, 550, 40, 8))) {
                ballYdir = -ballYdir;
            }

            // check map collision with the ball        
            A: for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {                
                    if (map.map[i][j] > 0) {
                        //scores++;
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;
                        
                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);                    
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickRect = rect;
                        
                        if (ballRect.intersects(brickRect)) {                    
                            map.setBrickValue(0, i, j);
                            score += 1;    
                            totalBricks--;
                            
                            // when ball hit right or left of brick
                            if (ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
                                ballXdir = -ballXdir;
                            } 
                            // when ball hits top or bottom of brick
                            else {
                                ballYdir = -ballYdir;                
                            }
                            
                            break A;
                        }
                    }
                }
            }
            
            ballposX += ballXdir;
            ballposY += ballYdir;
            
            if (ballposX < 0) {
                ballXdir = -ballXdir;
            }
            if (ballposY < 0) {
                ballYdir = -ballYdir;
            }
            if (ballposX > 670) {
                ballXdir = -ballXdir;
            }        
            
            repaint();        
        }
    }
}

class BrickGenerator {
    public int map[][];
    public int brickWidth;
    public int brickHeight;
    private Color BrickColor;
    
    public BrickGenerator(int row, int col, Color color) {
        map = new int[row][col];        
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;
            }            
        }
        
        brickWidth = 540 / col;
        brickHeight = 100 / row;
        this.BrickColor = color; // Set the brick color
    }   
    
    // Brick color
    public void setBrickColor(Color color){
    	this.BrickColor = color;
    }
    
    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                	
                	// Brick color
                    g.setColor(BrickColor);
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                    
                    // this is just to show separate brick, game can still run without it
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);                
                }
            }
        }
    }
    
    public void setBrickValue(int value, int row, int col) {
        map[row][col] = value;
    }
}

public class BrickBreakerGame {
    public static void main(String[] args) {
        JFrame obj = new JFrame();
        GameComponents BrickBreaker = new GameComponents();
        
        obj.setBounds(250, 30, 710, 610);
        obj.setTitle("Brick Breaker Game");        
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(BrickBreaker);
        obj.setVisible(true);        
    }
}
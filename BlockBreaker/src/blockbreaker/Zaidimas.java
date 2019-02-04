
package blockbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

public class Zaidimas extends JPanel implements KeyListener,ActionListener{

    private boolean play = false;
    private BufferedImage slate;
    
    private int totalBricks = 21;
    
    private Timer timer; 
    private int delay = 8;
    
    private int playerX = 310;
    
    private int ballposX = 200;
    private int ballposY = 300;
    private int ballXdir = -1;
    private int ballYdir = -2;
    
    
    
    
    
    private BlokuAtvaizdavimas map;
    
    public Zaidimas(){ 
        map = new BlokuAtvaizdavimas(3,7); 
        addKeyListener(this); 
        setFocusable(true);
        timer = new Timer(delay,this); 
        timer.start();
    }
    
    public void paint(Graphics g){
        
        g.setColor(Color.orange);
        g.fillRect(1,1,692,592);
        
        map.draw((Graphics2D)g);
        
        
        // borderiai
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3); 
        g.fillRect(691, 0, 3, 592);
        
        // the paddle
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8); 
        
        g.setColor(Color.blue);
        g.fillOval(ballposX,ballposY,20,20); 
        
        if(ballposY > 570){
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.blue);
            g.setFont(new Font("Serif",Font.BOLD,30));
            g.drawString("Ačiū už žaidimą",230,300);
            if(totalBricks==0){
                g.setColor(Color.red);
             g.setFont(new Font("Serif",Font.BOLD,30));
            g.drawString("Jus laimėjote",180,250);
            
            }
        }
        

        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(playerX>=600){
                playerX = 600;
            }else{
                moveRight();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(playerX<10){
                playerX = 10;
            }else{
                moveLeft();
            }
        }
        
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play){
            if( new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))){
                ballYdir = -ballYdir;                                                                   //kai kamuoliukas paliecia rakete
            }
             for(int i = 0;i<map.map.length;i++){
                
                for(int j=0;j<map.map[0].length;j++){
                    
                    if(map.map[i][j]>0){
                        int brickX=j*map.brickWidth +80;
                        int brickY=j*map.brickHeight +50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;
                        
                        Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX,ballposY,20,20);
                        Rectangle brickRect = rect;
                        
                        if(ballRect.intersects(brickRect)){
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            if(ballposX +19<= brickRect.x|| ballposX +1>= brickRect.width){
                                ballXdir = -ballXdir;
                            }else{
                                ballYdir = - ballYdir;
                            }
                            
                        }
                    }
                }
            }
            
            
            ballposX +=ballXdir;
            ballposY +=ballYdir;
            
            if(ballposX<0){
                ballXdir=-ballXdir; // left 
            }
            if(ballposY<0){
                ballYdir=-ballYdir;  //top 
            }
            if(ballposX>670){
                ballXdir=-ballXdir; //right keicia kripti kai paliecia siena 
            }
        
        repaint();
    }
    }
        
    
    
    
    
    public void moveRight(){
        play = true;
        playerX+=20;
    }
    
     public void moveLeft(){
        play = true;
        playerX-=20;
    }

   
    
    
    
    
}

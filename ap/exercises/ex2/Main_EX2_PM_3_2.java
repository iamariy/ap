package ap.exercises.ex2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main_EX2_PM_3_2 {

    public static class Packman extends JFrame implements KeyListener
    {
        final int k=15,boxSize=15;
        final int width=(k+2) * boxSize,height=(k+2) * boxSize;
        int xDot=k/2,yDot=k/2;

        public Packman()
        {
            addKeyListener(this);
            setSize(width,height);
        }

        public void paint(Graphics g)
        {
            super.paint(g);
            Graphics2D g2d=(Graphics2D)g;

            for (int i=0;i<k+2;i++)
            {
                for (int j=0;j<k+2;j++)
                {
                    if (i==0 || i==k+1 || j==0 || j==k+1)
                    {
                        g.setColor(Color.BLACK);
                        g.fillRect(j*boxSize,i*boxSize,boxSize,boxSize);
                    }
                }
            }
            drawPackman(g2d);
        }

        public void drawPackman(Graphics2D g2d)
        {
            g2d.setColor(Color.RED);
            g2d.fillRect(xDot*boxSize+2,yDot*boxSize+2,boxSize-4,boxSize-4);
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            int newX=xDot,newY=yDot;
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_W:
                    newX--;
                    break;
                case  KeyEvent.VK_D:
                    newY++;
                    break;
                case KeyEvent.VK_S:
                    newX++;
                    break;
                case KeyEvent.VK_A:
                    newY--;
                    break;
                case KeyEvent.VK_Q:
                    System.out.println("EXIT");
                    return;
                default:
                    System.out.println("WARNING");
                    break;
            }
            if (newX==0 || newX==k+1 || newY==0 || newY==k+1)
            {
                System.out.println("Hitting the game wall");
            } else
            {
                xDot=newX;
                yDot=newY;
                repaint();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        public static void main(String[] args)
        {
            Packman frame=new Packman();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}

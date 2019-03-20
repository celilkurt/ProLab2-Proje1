
package prolab1;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class myOwnKeyListener implements KeyListener{
    
    @Override
    public void keyTyped(KeyEvent e) {
    
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        
        float health;
        if(ProLab1.goodChar.getName().equals("LukeSkywalker"))
            health = ((LukeSkywalker)ProLab1.goodChar).getHealth();
        else
            health = ((MasterYoda)ProLab1.goodChar).getHealth();
        
        if(health != 0 && !ProLab1.isWin){
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    //System.out.println("Yukarı");
                    ProLab1.upMove();
                    break;
                case KeyEvent.VK_RIGHT:
                    //System.out.println("saga");
                    ProLab1.rightMove();
                    break;
                case KeyEvent.VK_LEFT:
                    //System.out.println("sola");
                    ProLab1.leftMove();
                    break;
                case KeyEvent.VK_DOWN:
                    //System.out.println("aşağı");
                    ProLab1.downMove();
                    break;
            
            }
        }
        
        
        
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
        System.exit(0);
    
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
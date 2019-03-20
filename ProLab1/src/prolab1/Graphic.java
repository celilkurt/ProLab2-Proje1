
package prolab1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Graphic extends JPanel{
    
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        this.setBackground(Color.white);
        
        JLayeredPane EverythingButPlayer = null;
        BufferedImage img = null;  
        float health;
        if(ProLab1.goodChar.getName().equals("LukeSkywalker"))
            health = ((LukeSkywalker)ProLab1.goodChar).getHealth();
        else
            health = ((MasterYoda)ProLab1.goodChar).getHealth();
        
        
        
        for(int i = 0; i < 11; i++ ){
            
            for(int j = 0; j < 14; j++){
                
                //labirent çizimi
                if(ProLab1.Map[i][j]){
                    
                    g.setColor(Color.ORANGE);
                    g.fill3DRect(50 + j*40 , 150 + i*40, 40, 40, true);
                    g.setColor(Color.WHITE);
                    g.drawString("1", 80 + j*40, 180 + i*40);  
                    
                }else{
                    
                    g.setColor(Color.DARK_GRAY);
                    g.fill3DRect(50 + j*40 , 150 + i*40, 40, 40, true);
                    g.setColor(Color.WHITE);
                    g.drawString("0", 80 + j*40, 180 + i*40); 
                    
                }
                
                //Girişler
                if(j == 4 && i == 0){//B girişi
                    
                    try {
                        img = ImageIO.read(new File("okUp.jpg"));
                        g.drawImage(img, 50 + j*40 , 110 + i*40, 40, 40, EverythingButPlayer);
                    } catch (IOException e) {
                    }
                    
                    g.setColor(Color.LIGHT_GRAY);
                    g.fill3DRect(50 + j*40 , 150 + i*40, 40, 40, true);
                    g.setColor(Color.BLACK);
                    g.drawString("B", 80 + j*40, 180 + i*40); 
                       
                }else if(j == 12 && i == 0){//C girişi
                    
                    try {
                        img = ImageIO.read(new File("okUp.jpg"));
                        g.drawImage(img, 50 + j*40 , 110 + i*40, 40, 40, EverythingButPlayer);
                    } catch (IOException e) {
                    }
                    
                    g.setColor(Color.LIGHT_GRAY);
                    g.fill3DRect(50 + j*40 , 150 + i*40, 40, 40, true);
                    g.setColor(Color.BLACK);
                    g.drawString("C", 80 + j*40, 180 + i*40); 
                      
                }else if(j == 0 && i == 5){//A girişi
                    
                    try {
                        img = ImageIO.read(new File("okRight.jpg"));
                        g.drawImage(img, 10 + j*40 , 150 + i*40, 40, 40, EverythingButPlayer);
                    } catch (IOException e) {
                    }
                     
                    g.setColor(Color.LIGHT_GRAY);
                    g.fill3DRect(50 + j*40 , 150 + i*40, 40, 40, true);
                    g.setColor(Color.BLACK);
                    g.drawString("A", 80 + j*40, 180 + i*40); 
                     
                }else if(j == 13 && i == 5){//D girişi
                    
                    try {
                        img = ImageIO.read(new File("okLeft.jpg"));
                        g.drawImage(img, 90 + j*40 , 150 + i*40, 40, 40, EverythingButPlayer);
                    } catch (IOException e) {
                    }
                    
                    g.setColor(Color.LIGHT_GRAY);
                    g.fill3DRect(50 + j*40 , 150 + i*40, 40, 40, true);
                    g.setColor(Color.BLACK);
                    g.drawString("D", 80 + j*40, 180 + i*40); 
                       
                }else if(j == 4 && i == 10){//E girişi
                    
                    try {
                        img = ImageIO.read(new File("okDown.jpg"));
                        g.drawImage(img, 50 + j*40 , 190 + i*40, 40, 40, EverythingButPlayer);
                    } catch (IOException e) {
                    }
                    
                    g.setColor(Color.LIGHT_GRAY);
                    g.fill3DRect(50 + j*40 , 150 + i*40, 40, 40, true);
                    g.setColor(Color.BLACK);
                    g.drawString("E", 80 + j*40, 180 + i*40); 
                     
                }else if(j == 6 && i == 5){
                    
                    g.setColor(Color.YELLOW);
                    g.fill3DRect(50 + j*40 , 150 + i*40, 40, 40, true);
                    g.setColor(Color.BLACK);
                    g.drawString("1", 80 + j*40, 180 + i*40); 
                    
                }
            }
        }
        
        ///////////Kalpler///////////////
        
        
        
        int tempHealth ;
        
        if(ProLab1.goodChar.getName().equals("MasterYoda")){
            try {
                img = ImageIO.read(new File("kalp.png"));
            } catch (IOException e) {
            }            
            tempHealth = (int)((MasterYoda)ProLab1.goodChar).getHealth();
            int k;
        
            for(k = 0; k < tempHealth; k++)
                g.drawImage(img, 500 + k*40 , 40, 30, 30, EverythingButPlayer);
                        
        
            //////////Yarım kalp varsa çizer
            if(((float)tempHealth)<((MasterYoda)ProLab1.goodChar).getHealth()){
                try {
                    img = ImageIO.read(new File("yarimKalp.png"));
                    g.drawImage(img, 500 + k*40 , 40, 30, 30, EverythingButPlayer);
                    } catch (IOException e) {
                }
            }
        
        }else if(ProLab1.goodChar.getName().equals("LukeSkywalker")){
                    
            try {
                img = ImageIO.read(new File("kalp.png"));
            } catch (IOException e) {
            } 
            
            tempHealth = ((LukeSkywalker)ProLab1.goodChar).getHealth();
              
            for(int k = 0; k < tempHealth; k++)
            g.drawImage(img, 500 + k*40 , 40, 30, 30, EverythingButPlayer);
                        
            
        }
        ////////////Kupa
        try {
            img = ImageIO.read(new File("cup.png"));
            g.drawImage(img, 620 , 510 , 35, 35, EverythingButPlayer);
        } catch (IOException e) {
        }
        
        //////////En kısa yollar
        
        for(int i = 0; i < ProLab1.pathList.size(); i++){
            //System.out.println(ProLab1.badList.get(i).getName() + " için");
            for(int j = 1; j < ProLab1.pathList.get(i).size(); j++){
                
                String[] str = ProLab1.pathList.get(i).get(j).split(",");
                //System.out.println(str[0] + " " + str[1]);
                int x = Integer.parseInt(str[0]);
                int y = Integer.parseInt(str[1]);
                String boxValue;
                
                if(ProLab1.Map[y][x])
                    boxValue = "1";
                else
                    boxValue = "0";
                
                g.setColor(Color.GRAY);
                g.fill3DRect(55 + x*40 , 155 + y*40, 30, 30, true);
                g.setColor(Color.white);
                g.drawString(boxValue, 80 + x*40, 180 + y*40); 
                
            }
        }
        
        
        
        
        
        
        
        
        ////////////Kötü karakterler
        
        for(int i = 0; i < ProLab1.badList.size(); i++){
            int x = ProLab1.badList.get(i).getLocation().getX();
            int y = ProLab1.badList.get(i).getLocation().getY();
            switch(ProLab1.badList.get(i).getName()){
                //50 + j*40 , 150 + i*40
                case "DarthVader":
                    try {
                        img = ImageIO.read(new File("vader.jpg"));
                        g.drawImage(img, 50 + x*40 , 150 + y*40, 40, 40, EverythingButPlayer);
                    } catch (IOException e) {
                    }
                    break;
                case "KyloRen":
                    try {
                        img = ImageIO.read(new File("kylo.jpg"));
                        g.drawImage(img, 50 + x*40 , 150 + y*40, 40, 40, EverythingButPlayer);
                    } catch (IOException e) {
                    }
                    break;
                case "Stormtrooper":
                    try {
                        img = ImageIO.read(new File("stormtrooper.jpg"));
                        g.drawImage(img, 50 + x*40 , 150 + y*40, 40, 40, EverythingButPlayer);
                    } catch (IOException e) {
                    }
                    break;
                    
            }
            
        }
        
        ///////////iyi karakter
        
        if(health != 0){
            
            int x = ProLab1.goodChar.getLocation().getX();
            int y = ProLab1.goodChar.getLocation().getY();
            
            switch(ProLab1.goodChar.getName()){
            
                case "MasterYoda":
                    try {
                            img = ImageIO.read(new File("yoda.jpg"));
                            g.drawImage(img, 50 + x*40 , 150 + y*40, 40, 40, EverythingButPlayer);
                        } catch (IOException e) {
                        }
                    break;
                case "LukeSkywalker":
                    try {
                            img = ImageIO.read(new File("luke.jpg"));
                            g.drawImage(img, 50 + x*40 , 150 + y*40, 40, 40, EverythingButPlayer);
                        } catch (IOException e) {
                        }
                    break;

            }
        }
        
        
        ///////////isGameOver
        
        
        if(health == 0){
            
            try {
                img = ImageIO.read(new File("youLose.jpg"));
                g.drawImage(img, 660 , 380, 325, 225, EverythingButPlayer);
            } catch (IOException e) {
            }
            
        }
        
        if(ProLab1.isWin){
            try {
                img = ImageIO.read(new File("youWin.jpg"));
                g.drawImage(img, 660 , 380, 325, 325, EverythingButPlayer);
            } catch (IOException e) {
            }
        }
        
        ////////////Kötü adamların en kısa yolunun kaç adım olduğunu yazar.
        
        for(int i = 0; i < ProLab1.badList.size();i++){
            
            if(ProLab1.badList.get(i).getName().equals("KyloRen")){
                String str = ProLab1.gateList.get(i) + " kapısındaki " +  ProLab1.badList.get(i).getName() + " en kısa yolu " + (ProLab1.pathList.get(i).size()-3) + " adımdır.";
                g.setColor(Color.black);
                g.drawString(str, 650, 200 + i*30); 
            }else{
                String str = ProLab1.gateList.get(i) + " kapısındaki " +  ProLab1.badList.get(i).getName() + " en kısa yolu " + (ProLab1.pathList.get(i).size()-2) + " adımdır.";
                g.setColor(Color.black);
                g.drawString(str, 650, 200 + i*30); 
            }
            
        } 
    }
}



package prolab1;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;

public class ProLab1 {
    static JFrame jf;
    static Graphic graphic;
    public static ArrayList<Character> badList ;
    public static ArrayList<String> gateList ;
    public static boolean[][] Map;
    public static Character goodChar;
    static int mapMeter1, mapMeter2;
    static Scanner scan;
    public static boolean isWin;
    public static ArrayList<ArrayList<String>> pathList;
    
    public static void main(String[] args)  {
        //Karakter sınıfında karakterin ilerlediği yol tutulacakmış.
        mapMeter2 = 0;
        mapMeter1 = 0;
        isWin =false;
        badList = new ArrayList<>();
        gateList = new ArrayList<>();
        Map = new boolean[11][14];
        scan = new Scanner(System.in);
        pathList = new ArrayList<>();
        jf = new JFrame("Star Wars");
        graphic = new Graphic();
        
        //createGoodChar();
        fileReader();
        createShortestPaths();
        graphic();
        
    }
    
    static void createGoodChar(){//Kullanıcının seçimine göre iyi karakteri oluşturur.
        
        System.out.println("Luke Skywalker için 1'e, "
                        + "\nMaster Yoda için 2'e basiniz.");
        int choice = scan.nextInt();
        
        if(choice == 1)
            goodChar = new LukeSkywalker("LukeSkywalker","good",new Location(6,5));//6,5
        else if(choice == 2)
            goodChar = new MasterYoda("MasterYoda","good",new Location(6,5));
            
        goodChar.path.add(new Location(6,5));
        
    }//Kullanıcının seçimine göre iyi karakteri oluşturur.
    
    static void fileReader(){//Dosyadan verileri okur ve ilgili metoda atar.
        
        try{
            File dosya = new File("Harita.txt");
 
            BufferedReader oku = new BufferedReader(
            new InputStreamReader(
            new FileInputStream(dosya), "UTF8"));
            
            String line;
 
            while ((line = oku.readLine()) != null) {
                
                String[] str = line.split(":");
                if(str[0].equals("Karakter")){
                    createBadChar(line);  
                }else
                    createMap(line);
                
            }
 
            oku.close();
            
        }catch(Exception e){
            System.out.println(e);
        }
    }//Dosyadan verileri okur ve ilgili metoda atar.
    
    static void createBadChar(String line) {//Kötü karakterin tanımladığı satırı, uygun bir şekilde böler ve kötü karakteri oluşturur.
        
        String[] array1 = line.split(",");//ornek k ile başlayan line = Karakter:Stormtrooper,Kapi:A
        Character Bad ;
        
        if(array1.length == 1){//Kötü karakterin tanımlandığı satır değildir.
            
            array1 = line.split(":");
            switch(array1[1]){
                
                case "LukeSkywalker":
                    goodChar = new LukeSkywalker("LukeSkywalker","good",new Location(6,5));
                    goodChar.path.add(new Location(6,5));
                    break;
                case "MasterYoda":
                    goodChar = new MasterYoda("MasterYoda","good",new Location(6,5));
                    goodChar.path.add(new Location(6,5));
                    break;
                }
        }else{
            String[] array2 = array1[0].split(":");//ornek array1[0] = Karakter:Stormtrooper
            String[] array3 = array1[1].split(":");//ornek array1[1] = Kapi:A;
            
            switch(array2[1]){
                
                case "Stormtrooper":
                    gateList.add(array3[1]);
                    Bad = new Stormtrooper("Stormtrooper","bad",createLocation(array3[1]));
                    Bad.path.add(new Location(Bad.getLocation().getX(),Bad.getLocation().getY()));
                    badList.add(Bad);
                    break;
                case "DarthVader":
                    gateList.add(array3[1]);
                    Bad = new DarthVader("DarthVader","bad",createLocation(array3[1]));
                    Bad.path.add(new Location(Bad.getLocation().getX(),Bad.getLocation().getY()));
                    badList.add(Bad);
                    break;
                case "KyloRen":
                    gateList.add(array3[1]);
                    Bad = new KyloRen("KyloRen","bad",createLocation(array3[1]));
                    Bad.path.add(new Location(Bad.getLocation().getX(),Bad.getLocation().getY()));
                    badList.add(Bad);
                    break;
            
            }
            
        }
        
    }//Kötü karakterin tanımladığı satırı, uygun bir şekilde böler ve kötü karakteri oluşturur.
    
    static Location createLocation(String gateName){
        switch(gateName){
            case "A":
                return  new Location(0,5);
            case "B":
                return new Location(4,0);
            case "C":
                return new Location(12,0);
            case "D":
                return new Location(13,5);
            case "E":
                return new Location(4,10);
            default:
               return new Location(0,0);
            }
    }
    
    static void createMap(String line){//Harita bilgilerinin bulunduğu stringden harita dizisini oluşturur.
        
        mapMeter2 = 0;
        
        for(int i = 0; i < line.length();i++){
            
            if(line.charAt(i)=='0' ){
                
                Map[mapMeter1][mapMeter2] = false;
                mapMeter2++;
                
            }else if(line.charAt(i)=='1'){
                
                Map[mapMeter1][mapMeter2] = true;
                mapMeter2++;
            }
            
        }
        mapMeter1++;
        //System.out.println(mapMeter1 + ". satir bitti.");
            
        
    }//Harita bilgilerinin bulunduğu stringden harita dizisini oluşturur.

    static void graphic(){//Grafik penceresini oluşturur.
        
        myOwnKeyListener klavye = new myOwnKeyListener();
        graphic.setBackground(Color.white);
        jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
        jf.setSize(1000,700);
        jf.setVisible(true);
        jf.addKeyListener(klavye);
        jf.add(graphic);               
        
    }//Grafik penceresini oluşturur.
    
    public static void rightMove(){
        
        int x = goodChar.getLocation().getX();
        int y = goodChar.getLocation().getY();
        if((x+1)<=13){//Sağa hareketinden sonra labirentte kalıyormu diye sorguluyor.
            if(ProLab1.Map[y][x+1]){//sağa yol varmı diye sorguluyor.
            
                pathList = new ArrayList<>();
                goodChar.getLocation().setX(x+1);
                goodChar.path.add(new Location((x+1),y));
                if(isGameOver())//Koşulun sağlanması için kötü karakterlerden birinin konumunun iyi karakterle aynı olması gerek.
                    resetLocations();//konumları ilk haline getirir ve iyi karakterin canını azaltır.
                    
                else{
                    createShortestPaths();
                    moveBadChar();
                    
                    if(isGameOver())
                        resetLocations();
                    
                }
                
                jf.getContentPane().repaint();
            
            }else
                System.out.println("Sağa gidemezsiniz.");
            
            }else if((x+1)==14 && y == 9){//kupaya ulaştı demek
                isWin = true;
                jf.getContentPane().repaint();
            }else
                System.out.println("Sağa gidemezsiniz.");
        
    }
    
    public static void leftMove(){
        
        int x = goodChar.getLocation().getX();
        int y = goodChar.getLocation().getY();
        
        if((x-1)>=0){
            
            if(Map[y][x-1]){
            
                pathList = new ArrayList<>();
                goodChar.getLocation().setX(x-1);
                goodChar.path.add(new Location((x-1),y));
                if(isGameOver())//Koşulun sağlanması için kötü karakterlerden birinin konumunun iyi karakterle aynı olması gerek.
                    resetLocations();
                            
                else{
                    createShortestPaths();
                    moveBadChar();
                    
                    if(isGameOver())
                        resetLocations();
                     
                }
                jf.getContentPane().repaint();
            
            }else
                System.out.println("Sola gidemezsiniz.");
            
        }else
            System.out.println("sola gidemezsin");
        
        
    }
    
    public static void upMove(){
        
        int x = goodChar.getLocation().getX();
        int y = goodChar.getLocation().getY();
        
        if((y-1)>=0){
            
            if(Map[y-1][x]){
            
                pathList = new ArrayList<>();
                goodChar.getLocation().setY(y-1);
                goodChar.path.add(new Location(x,(y-1)));
                if(isGameOver())//Koşulun sağlanması için kötü karakterlerden birinin konumunun iyi karakterle aynı olması gerek.
                    resetLocations();
                    
                else{
                    
                    createShortestPaths();
                    moveBadChar();
                    
                    if(isGameOver())
                        resetLocations();
                    
                       
                }
                jf.getContentPane().repaint();
            
            }else
                System.out.println("Yukarı gidemezsiniz.");
        }else
            System.out.println("Yukarı gidemezsiniz.");
        
        
    }
    
    public static void downMove(){
        
        int x = goodChar.getLocation().getX();
        int y = goodChar.getLocation().getY();
        
        if((y+1)<=10){
            
            if(Map[y+1][x]){
            
                pathList = new ArrayList<>();
                goodChar.getLocation().setY(y+1);
                goodChar.path.add(new Location(x,(y+1)));
                if(isGameOver())//Koşulun sağlanması için kötü karakterlerden birinin konumunun iyi karakterle aynı olması gerek.
                    resetLocations();
                    
                else{
                    createShortestPaths();
                    moveBadChar();
                    
                    if(isGameOver())
                        resetLocations();
                    
                }
                jf.getContentPane().repaint();
                
            
            }else
                System.out.println("Aşağı gidemezsiniz.");
        }else
            System.out.println("Aşağı gidemezsiniz.");
        
    }
    
    public static void resetLocations(){//Konumları resetler ve iyi karakterin canını azaltır.
        
        goodChar.getLocation().setX(6);
        goodChar.getLocation().setY(5);
        goodChar.path = new ArrayList<>();        
        if(goodChar.getName().equals("MasterYoda"))
            ((MasterYoda)goodChar).setHealth((((MasterYoda)goodChar).getHealth()-(float)(0.5)));
        else
            ((LukeSkywalker)goodChar).setHealth(((LukeSkywalker)goodChar).getHealth()-1);
                
        for(int j = 0;j < badList.size(); j++){      
            
            Location location;
            badList.get(j).path = new ArrayList<>(); 
            
            switch(gateList.get(j)){//i. gate, i. kötü karakterin giriş kapısıdır.
                    
                case "A":
                    location = new Location(0,5);
                    badList.get(j).setLocation(location);
                    break;
                case "B":
                    location = new Location(4,0);
                    badList.get(j).setLocation(location);
                    break;
                case "C":
                    location = new Location(12,0);
                    badList.get(j).setLocation(location);
                    break;
                case "D":
                    location = new Location(13,5);
                    badList.get(j).setLocation(location);
                    break;
                case "E":
                    location = new Location(4,10);
                    badList.get(j).setLocation(location);
                    break;
            }
        }
        pathList = new ArrayList<>();
        createShortestPaths();
        
    }//Konumları resetler ve iyi karakterin canını azaltır.
    
    public static boolean isGameOver(){//Herhangi bir kötü karakterle iyi karakterin konumu aynı ise true döner.
        
        for(int i = 0; i < badList.size(); i++){
            
            if(badList.get(i).getLocation().getX() == goodChar.getLocation().getX() && badList.get(i).getLocation().getY() == goodChar.getLocation().getY())
            //GAME OVER
                return true;
               
        }        
        
        return false;
        
    }//Herhangi bir kötü karakterle iyi karakterin konumu aynı ise true döner.
    
    public static void moveBadChar(){//Her bir kötü karakteri, oluşturulan en kısa yol dizisine göre hareket ettirir.
        
        for(int i = 0; i < badList.size();i++){
            String[] str;
            switch(badList.get(i).getName()){
                
                case "DarthVader":
                case "Stormtrooper":
                //pathList(i), i. kötü karakterin en kısa yolunu verir. pathlist(i).get(1) yapabileceği ilk hamleyi verir.
                    str = pathList.get(i).get(1).split(",");
                    badList.get(i).getLocation().setX(Integer.parseInt(str[0]));
                    badList.get(i).getLocation().setY(Integer.parseInt(str[1]));
                    badList.get(i).path.add(new Location(badList.get(i).getLocation().getX(), badList.get(i).getLocation().getY()));
                    break;
                case "KyloRen":
                    
                    if(pathList.get(i).size()>2){
                        str = pathList.get(i).get(2).split(",");
                        badList.get(i).getLocation().setX(Integer.parseInt(str[0]));
                        badList.get(i).getLocation().setY(Integer.parseInt(str[1]));
                        badList.get(i).path.add(new Location(badList.get(i).getLocation().getX(), badList.get(i).getLocation().getY()));
                    }else{
                        str = pathList.get(i).get(1).split(",");
                        badList.get(i).getLocation().setX(Integer.parseInt(str[0]));
                        badList.get(i).getLocation().setY(Integer.parseInt(str[1]));
                        badList.get(i).path.add(new Location(badList.get(i).getLocation().getX(), badList.get(i).getLocation().getY()));
                    }
                    break;
            }
        }
    }//Her bir kötü karakteri, en kısa yol dizisine göre hareket ettirir.
    
    static void createShortestPaths(){//Her bir kötü karakter için en kısa yol bulma metodunu çalıştırır. Bulunan kısa yolları pathList'e atar.
        
        for(int i = 0; i < badList.size(); i++){
            
            switch(badList.get(i).getName()){
                
                case "DarthVader":
                    ((DarthVader)badList.get(i)).shortestPath(badList.get(i).getLocation().getX(), badList.get(i).getLocation().getY());
                    pathList.add(((DarthVader)badList.get(i)).shortPath);
                    //System.out.println(badList.get(i).getName());
                    //System.out.println(((DarthVader)badList.get(i)).shortPath.toString());
                    break;
                case "KyloRen"://shortPath'in her hareketten sonra sıfırlanması gerek. Ağacında sıfırlanması gerek.
                    ((KyloRen)badList.get(i)).createShortestPath(((KyloRen)badList.get(i)).root, badList.get(i).getLocation().getX(), badList.get(i).getLocation().getY());
                    pathList.add(((KyloRen)badList.get(i)).shortPath);
                    System.out.println(badList.get(i).getName() + " için " + ((KyloRen)badList.get(i)).meter + " tane dugum olusturuldu.");
                    ((KyloRen)badList.get(i)).meter = 0;
                    //System.out.println(badList.get(i).getName());
                    //System.out.println(((KyloRen)badList.get(i)).shortPath.toString());
                    
                    ((KyloRen)badList.get(i)).root = new pathTree();
                    ((KyloRen)badList.get(i)).shortPath = new ArrayList<>();
                    break;
                case "Stormtrooper"://shortPath'in her hareketten sonra sıfırlanması gerek. Ağacında sıfırlanması gerek.
                    ((Stormtrooper)badList.get(i)).createShortestPath(((Stormtrooper)badList.get(i)).root, badList.get(i).getLocation().getX(), badList.get(i).getLocation().getY());
                    pathList.add(((Stormtrooper)badList.get(i)).shortPath);
                    System.out.println(badList.get(i).getName() + " için " + ((Stormtrooper)badList.get(i)).meter + " tane dugum olusturuldu.");
                    ((Stormtrooper)badList.get(i)).meter = 0;
                    //System.out.println(badList.get(i).getName());
                    //System.out.println(((Stormtrooper)badList.get(i)).shortPath);
                    
                    ((Stormtrooper)badList.get(i)).root = new pathTree();
                    ((Stormtrooper)badList.get(i)).shortPath = new ArrayList<>();
                    break;
                
            }
            
        }
        
    }//Her bir kötü karakter için en kısa yol bulma metodunu çalıştırır. Bulunan kısa yolları pathList'e atar.
    
    static void printShortestPaths(){//Oluşturulan en kısa yolları yazdırır.
        
        for(int i = 0; i < pathList.size(); i++)
            System.out.println("yol uzunluğu: " + (pathList.get(i).size()-1) + " " + pathList.get(i).toString());
        
    }//Oluşturulan en kısa yolları yazdırır.
    
    static void printBadChar(){//Oluşturulan kötü karakterleri yazdırır.
        
        for(int i = 0; i < badList.size(); i++){
            
            System.out.print("name: " + badList.get(i).getName()
            + "\ntype: " + badList.get(i).getType() 
            + "\nlocation x: " + badList.get(i).getLocation().getX()
            + "\nlocation y: " + badList.get(i).getLocation().getY());
            System.out.println("\nkapi: " + gateList.get(i) + "");
            
        }
    }//Oluşturulan kötü karakterleri yazdırır.
    
    
}

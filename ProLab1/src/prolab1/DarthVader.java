


package prolab1;

import java.util.ArrayList;



public class DarthVader extends Character {
    
    public ArrayList<String> shortPath;
    ArrayList<String> tempShortPath;
    
    public DarthVader(String name, String type, Location location) {
        super(name, type, location);
        shortPath = new ArrayList<>();
        tempShortPath = new ArrayList<>();
    }
    
    public void shortestPath(int tempX,int tempY){//Butun yollardan gidebilir.
        
        int goodX = ProLab1.goodChar.getLocation().getX();
        int goodY = ProLab1.goodChar.getLocation().getY();
        tempShortPath.add(tempX + "," + tempY);
        
        if(!(tempX == goodX && tempY == goodY)){
            
            if(tempY != goodY){
                
                if(tempY < goodY)
                    shortestPath(tempX,tempY+1);
                else
                    shortestPath(tempX,tempY-1);
            }else{
                
                if(tempX < goodX)
                    shortestPath(tempX+1,tempY);
                else
                    shortestPath(tempX-1,tempY);
            }
            
        }else{
            //System.out.println("Hedefe ulaşıldı.");
            shortPath = (ArrayList <String>)tempShortPath.clone();
            tempShortPath = new ArrayList<>();
            //System.out.println(shortPath.toString());
        }
            
    }
    
}

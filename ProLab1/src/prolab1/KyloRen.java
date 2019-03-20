
package prolab1;


import java.util.ArrayList;

public class KyloRen extends Character {
    
    public static pathTree root;//Gidilebilecek farklı yolları barındıran ağaç.
    public static ArrayList<String> shortPath;//Gidilebilecek en kısa yol.
    public static int meter = 0;


    public KyloRen(String name, String type, Location location) {
        super(name, type, location);
        
        root = new pathTree();
        shortPath = new ArrayList<>();
    }
    
    
    public void createShortestPath(pathTree tempRoot,int tempX,int tempY){
        
        tempRoot.path.add(tempX + "," + tempY);
        
        if(tempX != ProLab1.goodChar.getLocation().getX() || tempY != ProLab1.goodChar.getLocation().getY()){
        //geçici lokasyon, goodChar'ın lokasyonuna eşit olmadığı sürece
            
            if((!shortPath.isEmpty() && shortPath.size()>tempRoot.path.size()) || (shortPath.isEmpty()))
                //shortPath boş yada shortPath boş değil, üzerinde çalışılan yoldan daha uzun
                move(tempRoot,tempX,tempY);
                
        }else{
            //System.out.println("goodChar'a ULAŞILDI.");

            if(shortPath.isEmpty())
                shortPath = tempRoot.path;
                
            else if(shortPath.size() > tempRoot.path.size())
                shortPath = tempRoot.path;
                
            
        } 
    }
    
    private void move(pathTree tempRoot,int tempX,int tempY){
        
        int goodX = ProLab1.goodChar.getLocation().getX();
        int goodY = ProLab1.goodChar.getLocation().getY();
        
        if(tempX <= goodX && tempY <= goodY){
            //kötü karakter, iyi karakterin sol üstündeyse
            downMove(tempRoot,tempX,tempY);
            rightMove(tempRoot,tempX,tempY);
            leftMove(tempRoot,tempX,tempY);
            upMove(tempRoot,tempX,tempY);
            
            
        }else if(tempX >= goodX && tempY <= goodY){
            //kötü karakter, iyi karakterin sağ üstündeyse
            downMove(tempRoot,tempX,tempY);
            leftMove(tempRoot,tempX,tempY);
            rightMove(tempRoot,tempX,tempY);
            upMove(tempRoot,tempX,tempY);
            
        }else if(tempX <= goodX && tempY >= goodY){
            //kötü karakter, iyi karakterin sol altındaysa
            rightMove(tempRoot,tempX,tempY);
            upMove(tempRoot,tempX,tempY);
            downMove(tempRoot,tempX,tempY);
            leftMove(tempRoot,tempX,tempY);
            
        }else if(tempX >= goodX && tempY >= goodY){
            //kötü karakter, iyi karakterin sağ altındaysa
            upMove(tempRoot,tempX,tempY);
            leftMove(tempRoot,tempX,tempY);
            downMove(tempRoot,tempX,tempY);
            rightMove(tempRoot,tempX,tempY);
            
        }
        
            
    }
    
    private int numOfMoves(int tempX, int tempY ,pathTree tempRoot){
        
        int meter2 = 0;
        
        if(tempX != 13){
            if(ProLab1.Map[tempY][tempX+1] && tempRoot.path.indexOf((tempX+1) + "," + tempY) == -1)
                    //true ise gidilebilir demektir.
                    meter2++;
        }
        
        if(tempX != 0){
            if(ProLab1.Map[tempY][tempX-1] && tempRoot.path.indexOf((tempX-1) + "," + tempY) == -1)
                //true ise gidilebilir demektir.
                   meter2++;
        }
        
        if(tempY != 0){
            if(ProLab1.Map[tempY-1][tempX] && tempRoot.path.indexOf(tempX + "," + (tempY-1)) == -1)
                //true ise gidilebilir demektir.
                meter2++;
        }
        
        if(tempY != 10){
            if(ProLab1.Map[tempY+1][tempX] && tempRoot.path.indexOf(tempX + "," + (tempY+1)) == -1)
                //true ise gidilebilir demektir.
                meter2++;
        }
        
        return meter2;
    }
    
    private void rightMove(pathTree tempRoot,int tempX,int tempY){
        
        if(tempX != 13){
            
                if(ProLab1.Map[tempY][tempX+1] && tempRoot.path.indexOf((tempX+1) + "," + tempY) == -1){//true ise gidilebilir demektir.
                    //System.out.println("sağ için");
                    if(numOfMoves(tempX,tempY,tempRoot)>1){
                        meter++;
                        pathTree newNode = new pathTree();
                        newNode.path = (ArrayList<String>)tempRoot.path.clone();
                        tempRoot.childList.add(newNode);
                        createShortestPath(newNode,tempX+1,tempY);
                    }else
                        createShortestPath(tempRoot,tempX+1,tempY);
                }
            }
    }
    
    private void leftMove(pathTree tempRoot,int tempX,int tempY){
        
        if(tempX != 0){
            
                if(ProLab1.Map[tempY][tempX-1] && tempRoot.path.indexOf((tempX-1) + "," + tempY) == -1){//true ise gidilebilir demektir.
                    //System.out.println("sol için");
                    if(numOfMoves(tempX,tempY,tempRoot)>1){
                        meter++;
                        pathTree newNode = new pathTree();
                        newNode.path = (ArrayList<String>)tempRoot.path.clone();
                        tempRoot.childList.add(newNode);
                        createShortestPath(newNode,tempX-1,tempY);
                    }else
                        createShortestPath(tempRoot,tempX-1,tempY);
                
                }
            }
        
        
    }
    
    private void upMove(pathTree tempRoot,int tempX,int tempY){
        
        if(tempY != 0){
            
                if(ProLab1.Map[tempY-1][tempX] && tempRoot.path.indexOf(tempX + "," + (tempY-1)) == -1){//true ise gidilebilir demektir.
                    //System.out.println("yukarı için");
                    if(numOfMoves(tempX,tempY,tempRoot)>1){
                        meter++;
                        pathTree newNode = new pathTree();
                        newNode.path = (ArrayList<String>)tempRoot.path.clone();
                        tempRoot.childList.add(newNode);
                        createShortestPath(newNode,tempX,tempY-1);
                    }else
                        createShortestPath(tempRoot,tempX,tempY-1);
                
                }
            }
        
    }
    
    private void downMove(pathTree tempRoot,int tempX,int tempY){
        
        if(tempY != 10){
            
                if(ProLab1.Map[tempY+1][tempX] && tempRoot.path.indexOf(tempX + "," + (tempY+1)) == -1){//true ise gidilebilir demektir.
                    //System.out.println("aşağı için");
                    if(numOfMoves(tempX,tempY,tempRoot)>1){
                        meter++;
                        pathTree newNode = new pathTree();
                        newNode.path = (ArrayList<String>)tempRoot.path.clone();
                        tempRoot.childList.add(newNode);
                        createShortestPath(newNode,tempX,tempY+1);
                    }else
                        createShortestPath(tempRoot,tempX,tempY+1);
                
                }
            }
        
    }
    
    
}



package prolab1;

import java.util.ArrayList;


public class Character {
    
    private String name;
    private String type;
    private Location location;
    public ArrayList<Location> path = new ArrayList<>();
    
    Character (String name, String type, Location location){
        this.name = name;
        this.type = type;
        this.location = location;        
    }
    
    public void setName(String name){this.name = name;}
    
    public String getName(){return name;}
    
    public void setType(String type){this.type = type;}
    
    public String getType(){return type;}
    
    public void setLocation(Location location){this.location = location;}
    
    public Location getLocation(){return location;}
    
}

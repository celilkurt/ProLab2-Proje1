

package prolab1;



public class LukeSkywalker extends Character {
    
    public int health;
    
    public LukeSkywalker(String name, String type, Location location) {
        super(name, type, location);
        health = 3;
    }
    
    public void setHealth(int health){ 
        if(health <= 3 &&health >= 0)
            this.health = health;}
    
    public int getHealth(){return health;}
    
}

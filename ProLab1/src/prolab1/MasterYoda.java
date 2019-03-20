

package prolab1;


public class MasterYoda extends Character {
    
    private float health;
    
    public MasterYoda(String name, String type, Location location) {
        super(name, type, location);
        health = 3;
    }
    
    public void setHealth(float health){
        if(health <= 3 && health >= 0)
            this.health = health;
    }
    
    public float getHealth(){return health;}
    
}

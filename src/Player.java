import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Koen on 26-8-2016.
 */
public class Player {
    private ArrayList<String> hand = new ArrayList<String>();
    public String name = "";
    public boolean AI = false;
    private GUI gui;

    public Player(String name, GUI gui){
        this.name = name;
        this.gui = gui;
    }

    public void setHand(ArrayList<String> hnd){
        hand = hnd;
        //refreshHand();
    }

    public ArrayList<String> getHand(){
        return hand;
    }

    public int handSize(){
        return hand.size();
    }

    public void addTileToHand(String tile){
        hand.add(tile);
        //refreshHand();
    }

    public String showHand(){
        //refreshHand();
        return Arrays.toString(hand.toArray());
    }

    public void removeTileFromHand(String tile){
        hand.remove(hand.indexOf(tile));
    }

    public void refreshHand(){
        gui.clearHand();
        for(String item: hand){
            gui.addHand(item);
        }
    }
}

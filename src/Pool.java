import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Koen on 26-8-2016.
 */
public class Pool {
    private ArrayList<String> pool = new ArrayList<String>();
    private ArrayList<String> drawablePool = new ArrayList<String>();
    private Random randomGenerator = new Random();
    private GUI gui;

    public Pool(GUI gui){
        pool.addAll(Arrays.asList(
                "0-0",
                "0-1", "1-1",
                "0-2", "1-2", "2-2",
                "0-3", "1-3", "2-3", "3-3",
                "0-4", "1-4", "2-4", "3-4", "4-4",
                "0-5", "1-5", "2-5", "3-5", "4-5", "5-5",
                "0-6", "1-6", "2-6", "3-6", "4-6", "5-6", "6-6"));
        drawablePool.addAll(pool);
        this.gui = gui;
    }

    public int getDrawablePoolSize(){
        //return 0;
        return drawablePool.size();
    }

    public void dividePool(Player player1, Player player2){
        ArrayList<String> listPlayer1 = new ArrayList<String>();
        ArrayList<String> listPlayer2 = new ArrayList<String>();
        //ArrayList<Integer> randomPool = new ArrayList<Integer>();
        for(int i = 0; i < 14; i++){
            int index = randomGenerator.nextInt(drawablePool.size());
            String tile = drawablePool.get(index);
            drawablePool.remove(index);
            if(i % 2 == 0){
                listPlayer1.add(tile);
            } else {
                listPlayer2.add(tile);
            }
        }

        player1.setHand(listPlayer1);
        player2.setHand(listPlayer2);
    }

    public void draw(Player player){
        int index = randomGenerator.nextInt(drawablePool.size());
        String tile = drawablePool.get(index);
        drawablePool.remove(index);
        player.addTileToHand(tile);
    }

    public String getStarter(){
        int index = randomGenerator.nextInt(drawablePool.size());
        String tile = drawablePool.get(index);
        drawablePool.remove(index);
        return tile;
    }
}

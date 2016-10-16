import javax.swing.*;

/**
 * Created by Koen on 26-8-2016.
 */
public class Game {
    private Player player1;
    private Player player2;
    private Pool pool;

    private int current = 0;
    private String chain = "";
    private boolean finished = false;

    private Player winner = null;

    private boolean debug = false;

    private GUI gui;

    public Game(boolean player1AI, boolean player2AI, GUI gui){

        this.gui = gui;

        player1 = new Player("Player 1", this.gui);
        player2 = new Player("Player 2", this.gui);

        player2.AI = player2AI;
        player1.AI = player1AI;

        System.out.println("LET'S GET READY TO RUMBLE!");

        pool = new Pool(this.gui);
        //System.out.println(pool.getDrawablePoolSize());

        pool.dividePool(player1, player2);

        String starterTile = pool.getStarter();
        chain = starterTile;
        this.gui.addDomino(starterTile, "f", false);

        gui.setDrawableTiles(pool.getDrawablePoolSize());

        while (!finished) {
            if (current == 0) {
                doMove(player1);
                current = 1;
            } else if (current == 1){
                doMove(player2);
                current = 0;
            }
            if(player1.handSize() == 0) {
                System.out.println(player1.name + " has won");
                winner = player1;
                finished = true;
            } else if (player2.handSize() == 0) {
                System.out.println(player2.name + " has won");
                winner = player2;
                finished = true;
            }
        }
        gui.revalidate();
        gui.repaint();
        JOptionPane.showMessageDialog(gui, "The game has finished. The winner is " + winner.name, "Game finished", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public boolean doMove(Player player){
        gui.setCurrentPlayerLabel(player.name);
        if(!player.AI || debug) {
            System.out.println("=== " + player.name + " ===");
            System.out.println("Current tile chain:");
            System.out.println(chain);
            System.out.println("Player's tiles:");
            System.out.println(player.showHand());
            System.out.println("Choose a tile or click 'draw' to draw a tile: ");
        }

        if (doAction(player)){

        }
        return true;
    }

    public boolean doAction(Player player){
        if(player.AI){
            if(player1.AI && player2.AI) {
                //System.out.println("AI battle!");
                player.refreshHand();
            }
            try {
                Thread.sleep(1250);
            } catch(InterruptedException e) {
                System.out.println(e.getMessage());
            }
            boolean setAction = false;
            for(String tile: player.getHand()){
                if(testAnswer(player, tile)){
                    setAction = true;
                    break;
                }
            }
            if(!setAction){
                answerDraw(player);
            }
            if(player1.AI && player2.AI) {
                //System.out.println("AI battle!");
                player.refreshHand();
            }
            return true;
        } else {
            System.out.println("Awaiting input");
            player.refreshHand();
            String answer = gui.playTile();
            if (answer.toUpperCase().equals("DRAW")) {
                return answerDraw(player);
            } else {
                if (player.getHand().contains(answer)) {
                    if(!testAnswer(player, answer)){
                        if(!player.AI  || debug){
                            System.out.println("Nope, that doesn't fit... Try again");
                        }
                        return doAction(player);
                    } else {
                        player.refreshHand();
                        return true;
                    }
                } else {
                    System.out.println("Input not recognised, please try again");
                    return doAction(player);
                }
            }
        }
    }

    public boolean testAnswer(Player player, String answer){
        if (chain.endsWith(String.valueOf(answer.charAt(0)))) {
            chain = chain + " | " + answer;
            player.removeTileFromHand(answer);
            gui.addDomino(answer, "r", false);
            return true;
        } else if (chain.endsWith(String.valueOf(answer.charAt(2)))) {
            chain = chain + " | " + new StringBuilder(answer).reverse().toString();
            player.removeTileFromHand(answer);
            gui.addDomino(answer, "r", true);
            return true;
        } else if (chain.startsWith(String.valueOf(answer.charAt(0)))) {
            chain = new StringBuilder(answer).reverse().toString() + " | " + chain;
            player.removeTileFromHand(answer);
            gui.addDomino(answer, "f", true);
            return true;
        } else if (chain.startsWith(String.valueOf(answer.charAt(2)))) {
            chain = answer + " | " + chain;
            player.removeTileFromHand(answer);
            gui.addDomino(answer, "f", false);
            return true;
        } else {
            return false;
        }
    }

    public boolean answerDraw(Player player){
        if (pool.getDrawablePoolSize() == 0) {
            if(player.name.equals("Player 1")){
                winner = player2;
            } else {
                winner = player1;
            }
            finished = true;
        } else {
            pool.draw(player);
            gui.setDrawableTiles(pool.getDrawablePoolSize());
            if(!player.AI  || debug) {
                System.out.println(player.showHand());
            }
            System.out.println(player.name + " drew a tile. " + pool.getDrawablePoolSize() +  " tiles left.");
        }
        if(player1.AI && player2.AI || !player.AI) {
            player.refreshHand();
        }
        return true;
    }
}

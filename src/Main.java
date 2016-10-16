import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }
        GUI gui = new GUI();

        System.out.println("\n" +
                "8888888b.                      d8b                        \n" +
                "888  \"Y88b                     Y8P                        \n" +
                "888    888                                                \n" +
                "888    888 .d88b. 88888b.d88b. 88888888b.  .d88b. .d8888b \n" +
                "888    888d88\"\"88b888 \"888 \"88b888888 \"88bd88\"\"88b88K     \n" +
                "888    888888  888888  888  888888888  888888  888\"Y8888b.\n" +
                "888  .d88PY88..88P888  888  888888888  888Y88..88P     X88\n" +
                "8888888P\"  \"Y88P\" 888  888  888888888  888 \"Y88P\"  88888P'");
        System.out.println("==========================================================");
        System.out.println("Hi there sunshine, want to play some dominos in a console like l33t h4ckz0rz?");
        System.out.println("Then use the console version, this one is for N00bs cuz it haz a GUI");
        //System.out.println("So... Do you want play again some scary, real human next to you or the cosy beeping of an AI?");
        //System.out.println("(Please enter 'AI' or 'human')");
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(gui, "Is player 1 an AI?", "AI or human?", dialogButton);
        boolean player1AI = false;
        if(dialogResult == 0) {
            player1AI = true;
        } else {
            player1AI = false;
        }
        dialogResult = JOptionPane.showConfirmDialog(gui, "Is player 2 an AI?", "AI or human?", dialogButton);
        boolean player2AI = false;
        if(dialogResult == 0) {
            player2AI = true;
        } else {
            player2AI = false;
        }

        /*
        System.out.println("Do you want to enable the GUI? (yes/no)");
        answer = scanner.next();
        boolean GUI = false;
        if(answer.toUpperCase().equals("yes")){
            GUI = true;
        }
        */
        boolean GUI = true;
        Game game = new Game(player1AI, player2AI, gui);
    }
}

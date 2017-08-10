import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Koen on 26-8-2016.
 */
public class GUI extends JFrame{
    public JPanel jpanel;
    public JPanel jpanelTop;
    public JPanel jpanelCenter;
    public JPanel jpanelBottom;
    public JButton drawButton;
    public JPanel jpanelHand;
    public JPanel jpanelRight;
    public JLabel tilesLeftLabel;
    public JLabel currentPlayerLabel;

    private String playTile = "";

    public GUI(){
        super("Dominos");
        setContentPane(jpanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
        drawButton.addActionListener(e -> playTile = "draw");
        revalidate();
        repaint();
    }

    public void addDomino(String tile, String location, boolean reverse){
        ImageIcon image = new ImageIcon(this.getClass().getResource("/img/" + tile + ".png"));
        RotatedIcon ri = new RotatedIcon(image, RotatedIcon.Rotate.UPSIDE_DOWN);
        JLabel picLabel = new JLabel();
        if(reverse) {
            picLabel.setIcon(ri);
        } else {
            picLabel.setIcon(image);
        }
        if(location.equals("r")) {
            jpanelCenter.add(picLabel);
        } else if (location.equals("f")){
            jpanelCenter.add(picLabel, 0);
        }
        revalidate();
        repaint();
    }

    public String playTile(){
        while (playTile.isEmpty()){
            try {
                Thread.sleep(200);
            } catch(InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        String response = playTile;
        playTile = "";
        return response;
    }

    public void clearHand(){
        jpanelHand.removeAll();
        revalidate();
        repaint();
    }

    public void addHand(String tile){
        JButton button = new JButton();
        ImageIcon image = new ImageIcon(this.getClass().getResource("/img/" + tile + ".png"));
        RotatedIcon ri = new RotatedIcon(image, RotatedIcon.Rotate.DOWN);

        button.setIcon(ri);
        //button.setName("btn" + tile);
        button.addActionListener(e -> playTile = tile);
        jpanelHand.add(button);
        revalidate();
        repaint();
    }

    public void setDrawableTiles(int drawableTiles){
        tilesLeftLabel.setText(Integer.toString(drawableTiles));
        revalidate();
        repaint();
    }

    public void setCurrentPlayerLabel(String currentPlayer){
        currentPlayerLabel.setText(currentPlayer);
        revalidate();
        repaint();
    }
}


package view;

import model.ChessPiece;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {
    private JLabel playerLabel;
    private JLabel scoreLabel;
    private JLabel modeLabel;

    public StatusPanel(int width, int height) {
        this.setSize(width, height);
        this.setLayout(null);
        this.setVisible(true);

        this.playerLabel = new JLabel();
        this.playerLabel.setLocation(0, 10);
        this.playerLabel.setSize((int) (width * 0.4), height);
        this.playerLabel.setFont(new Font("Calibri", Font.BOLD, 30));
        this.setPlayerText(ChessPiece.BLACK.name());
        add(playerLabel);

        this.scoreLabel = new JLabel();
        this.scoreLabel.setLocation((int) (width * 0.4), 10);
        this.scoreLabel.setSize((int) (width * 0.5), height);
        this.scoreLabel.setFont(new Font("Calibri", Font.ITALIC, 25));
        this.setScoreText(2,2);
        add(scoreLabel);

        this.modeLabel = new JLabel();
        this.modeLabel.setLocation((int) (width * 0.4)+240, 10);
        this.modeLabel.setSize((int) (width * 0.4), height);
        this.modeLabel.setFont(new Font("Calibri", Font.BOLD, 30));
        this.setModeText(true);
        add(modeLabel);
    }

    public void setScoreText(int black, int white) {
        this.scoreLabel.setText(String.format("BLACK: %d\tWHITE: %d", black, white));
    }

    public void setPlayerText(String playerText) {
        this.playerLabel.setText(playerText + "'s turn");
    }

    public void setModeText(boolean GameMode){
        String mode = "Normal";
        if(!GameMode){
            mode = "Cheating";
        }
        this.modeLabel.setText(String.format("%s Mode",mode));
    }
    
}

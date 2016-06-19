package viewcontrol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Johannes on 17.06.2016.
 */
public class GameOverPopup extends JPanel{

    private SaveButton save;
    private BackButton back;
    private JLabel message;

    Frame frame;

    public GameOverPopup(Frame frame, String inputMessage){
        this.frame = frame;

        JPanel thisPanel = this;

        setBackground(new Color(204, 122, 0));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if (screenSize.getWidth() < 1920 && screenSize.getHeight() < 1080){
            gbc.insets = new Insets(20, 50, 20, 50);
        }else{
            gbc.insets = new Insets(5, 15, 5, 15);
        }

        message = new JLabel();
        message.setHorizontalTextPosition(SwingConstants.CENTER);
        message.setVerticalTextPosition(SwingConstants.CENTER);
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setVerticalAlignment(SwingConstants.CENTER);
        message.setText(inputMessage);
        if (screenSize.getWidth() > 1920 && screenSize.getHeight() > 1080){
            message.setPreferredSize(new Dimension(1000,100));
            message.setFont(new Font("Century", 0, 50));
        }else{
            message.setPreferredSize(new Dimension(500,50));
            message.setFont(new Font("Century", 0, 25));
        }

        save = new SaveButton();
        back = new BackButton();

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                thisPanel.setVisible(false);
                frame.toggleMainGame(false);
            }
        });

        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(message, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(back, gbc);

        this.setVisible(true);
    }



}

package viewcontrol;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Johannes on 17.06.2016.
 */
public class SavePopup extends JPanel{

    private SaveButton save;
    private BackButton back;
    private JTextField input;

    public SavePopup(){

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if (screenSize.getWidth() < 1920 && screenSize.getHeight() < 1080){
            gbc.insets = new Insets(20, 50, 20, 50);
        }else{
            gbc.insets = new Insets(5, 15, 5, 15);
        }

        input = new JTextField();
        if (screenSize.getWidth() < 1920 && screenSize.getHeight() < 1080){
            input.setPreferredSize(new Dimension(1000,100));
            input.setFont(new Font("Century", 0, 50));
        }else{
            input.setPreferredSize(new Dimension(500,40));
            input.setFont(new Font("Century", 0, 25));
        }

        save = new SaveButton();
        back = new BackButton();

        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(input, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(save, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        add(back, gbc);


    }
}

package viewcontrol;

import exceptions.FieldException;
import model.SaveLoad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Johannes on 17.06.2016.
 */
public class SavePopup extends JPanel{

    private SaveButton save;
    private BackButton back;
    private JTextField input;

    Frame frame;

    public SavePopup(Frame frame){
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

        input = new JTextField();
        if (screenSize.getWidth() > 1920 && screenSize.getHeight() > 1080){
            input.setPreferredSize(new Dimension(1000,100));
            input.setFont(new Font("Century", 0, 50));
            input.setMargin(new Insets(5,20,5,20));
        }else{
            input.setPreferredSize(new Dimension(500,50));
            input.setFont(new Font("Century", 0, 25));
            input.setMargin(new Insets(5,10,5,10));
        }

        save = new SaveButton();
        back = new BackButton();

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thisPanel.setVisible(false);
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveLoad sl = new SaveLoad(frame.getPanel4().getGame(), frame.getPanel4());
                try {
                    sl.save(getInput().getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (FieldException e1) {
                    e1.printStackTrace();
                }

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                thisPanel.setVisible(false);
            }
        });

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

    public SaveButton getSave() {
        return save;
    }

    public void setSave(SaveButton save) {
        this.save = save;
    }

    public BackButton getBack() {
        return back;
    }

    public void setBack(BackButton back) {
        this.back = back;
    }

    public JTextField getInput() {
        return input;
    }

    public void setInput(JTextField input) {
        this.input = input;
    }
}

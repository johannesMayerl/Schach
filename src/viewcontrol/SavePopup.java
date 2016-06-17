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

        save = new SaveButton();
        back = new BackButton();




    }
}

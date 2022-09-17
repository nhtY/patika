package com.patikaDev.View;

import com.patikaDev.Helper.Config;
import com.patikaDev.Helper.Helper;
import com.patikaDev.Model.Path;

import javax.swing.*;

public class UpdatePathGUI extends JFrame{
    private JTextField fld_new_path_name;
    private JLabel lbl_update_path;
    private JButton btn_update_path_name;
    private JPanel wrapper;

    private int pathID;
    public UpdatePathGUI(int pathID){
        this.pathID = pathID;

        add(wrapper);
        setSize(300, 160);
        setLocation(Helper.getScreenCenterPoint("x", getSize()), Helper.getScreenCenterPoint("y", getSize()));
        setTitle(Config.PROJECT_TITLE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        btn_update_path_name.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_new_path_name)){
                Helper.showMessage("fill");
            }else{
                String newName = fld_new_path_name.getText();
                if(Path.checkPathExists(newName)){
                    Helper.showMessage("A path having the same name already exists!");
                }else if(Path.updatePath(newName, pathID)){
                    Helper.showMessage("done");
                    dispose();
                }
            }
        });
    }
}

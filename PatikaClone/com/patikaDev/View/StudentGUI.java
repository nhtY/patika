package com.patikaDev.View;

import com.patikaDev.Helper.Config;
import com.patikaDev.Helper.Helper;

import javax.swing.*;

public class StudentGUI extends JFrame{
    private JPanel wrapper;
    private JLabel fld_screen_title;

    public StudentGUI(){
        this.add(wrapper);
        this.setSize(400, 400);
        this.setLocation(Helper.getScreenCenterPoint("x", this.getSize()), Helper.getScreenCenterPoint("y", this.getSize()));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
    }
}

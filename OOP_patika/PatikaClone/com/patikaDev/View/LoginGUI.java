package com.patikaDev.View;

import com.patikaDev.Helper.Config;
import com.patikaDev.Helper.Helper;
import com.patikaDev.Model.Educator;
import com.patikaDev.Model.Operator;
import com.patikaDev.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame{
    private JPanel wrapper;
    private JPanel pnl_top;
    private JPanel pnl_bottom;
    private JLabel lbl_logo;
    private JLabel lbl_user_login;
    private JTextField fld_username;
    private JPasswordField fld_password;
    private JButton btn_login;
    private JLabel lbl_username;
    private JLabel lbl_password;

    public LoginGUI(){
        this.add(wrapper);
        this.setSize(400, 400);
        this.setLocation(Helper.getScreenCenterPoint("x", this.getSize()), Helper.getScreenCenterPoint("y", this.getSize()));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        // when login button clicked, check if fields are empty and then user exists:
        btn_login.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_username) || Helper.isFieldEmpty(fld_password)){
                Helper.showMessage("fill");
            }else{
                String username = fld_username.getText();
                String password = String.valueOf(fld_password.getPassword());

                User user = User.fetchUserForLogin(username, password);
                if(user == null){
                    Helper.showMessage("The entered username or password is wrong!");
                }else{
                    //Helper.showMessage(user.getName() + "\n" + user.getPassword());

                    switch (user.getRole()){
                        case "operator":
                            System.out.println(user.getClass() + "| is it operator? " + (user instanceof Operator));
                            OperatorGUI operatorGUI = new OperatorGUI((Operator) user);
                            break;
                        case "educator":
                            EducatorGUI educatorGUI = new EducatorGUI((Educator) user);
                            break;
                        case "student":
                            StudentGUI studentGUI = new StudentGUI();
                            break;
                    }
                    this.dispose();
                }
            }
        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        LoginGUI loginGUI = new LoginGUI();
    }
}

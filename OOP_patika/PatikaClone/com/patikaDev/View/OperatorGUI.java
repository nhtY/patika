package com.patikaDev.View;

import com.patikaDev.Helper.Config;
import com.patikaDev.Helper.Helper;
import com.patikaDev.Model.*;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class OperatorGUI extends JFrame {

    private JPanel wrapper;
    private JTabbedPane tab_operator;
    private JPanel pnl_user_list;
    private JPanel pnl_top;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JScrollPane scrl_user_list;
    private JTable tbl_user_list;
    private JPanel pnl_user_form;
    private JLabel lbl_name_surname;
    private JTextField fld_name_surname;
    private JLabel lbl_username;
    private JTextField fld_username;
    private JLabel lbl_password;
    private JTextField fld_password;
    private JComboBox cmb_user_role;
    private JLabel lbl_user_role;
    private JButton btn_add_user;
    private JLabel lbl_username_to_delete;
    private JTextField fld_username_delete;
    private JButton btn_delete;
    private JTextField fld_search_by_name;
    private JLabel lbl_serach_by_name;
    private JLabel lbl_search_by_username;
    private JTextField fld_search_by_username;
    private JLabel lbl_search_by_role;
    private JComboBox cmb_search_by_role;
    private JButton btn_search;
    private JPanel pnl_path_list;
    private JScrollPane scrl_path_list;
    private JTable tbl_path_list;
    private JPanel pnl_paths_top;
    private JTextField fld_path_name;
    private JButton btn_add_path;
    private JPanel pnl_course_list;
    private JScrollPane scrl_course_list;
    private JTable tbl_course_list;
    private JTextField fld_course_name;
    private JTextField fld_programming_language;
    private JComboBox cmb_path;
    private JComboBox cmb_educator;
    private JButton btn_add_course;
    // we need a TableModel to use tbl_user_list
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;
    // we need a TableModel to use tbl_course_list
    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;

    // we need a TableModel to use tbl_path_list
    private DefaultTableModel mdl_path_list;
    private Object[] row_path_list;
    private JPopupMenu path_popup_menu;
    private final Operator operator;

    private String previousPassword;

    public OperatorGUI(Operator operator){
        this.operator = operator;

        // add the component into the main frame
        add(wrapper);

        // set the size of main frame:
        setSize(600, 500);

        // Locate the frame on the determined position
        int x = Helper.getScreenCenterPoint("x", this.getSize());
        int y = Helper.getScreenCenterPoint("y", this.getSize());

        this.setLocation(x, y);

        // Change the setting that determines what will happen when frame is closed:
        // DISPOSE_ON_CLOSE => close the window and stop the running program.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);

        setVisible(true);

        lbl_welcome.setText("Welcome " + operator.getName());


        // ########## USER - START ###################

        // Model UserList:
        mdl_user_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                // you cannot edit values at the first column
                if(column==0){
                    return false;
                }

                return super.isCellEditable(row, column);
            }
        };
        // columns of the model:
        Object[] col_user_list = {"ID", "name-surname", "username", "password", "Role"};
        mdl_user_list.setColumnIdentifiers(col_user_list);

        // A row is an array of Objects. Each row has the same number of cells as the number of columns.
        row_user_list = new Object[col_user_list.length];
        // add rows:
        loadUserTableModel();

        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);

        // When a row from the table is selected:
        // put the selected row's ID value into the text ID text field for delete operation
        tbl_user_list.getSelectionModel().addListSelectionListener(e -> {
            int selectedRowIndex = tbl_user_list.getSelectedRow();

            String selectedUsername = selectedRowIndex != -1 ?
                    tbl_user_list.getValueAt(selectedRowIndex, 2).toString() : null;

            fld_username_delete.setText(selectedUsername);
        });

        // ##### password issue ####
        // It is problematic to update password: if you do not change the password it causes problems due to sha2().
        // it tries to update every field even if you update one of them. It gives old password's hashed version as
        // a parameter to update method. This causes doubly hashed password, and it goes like that.
        // So, it is helpful to keep the previous password for the selected row.
        tbl_user_list.getSelectionModel().addListSelectionListener(e -> {
            int selectedRowIndex = tbl_user_list.getSelectedRow();

            previousPassword = selectedRowIndex != -1 ?
                    tbl_user_list.getValueAt(selectedRowIndex, 3).toString() : null;
        });

        // update the values on the table when changed. change value + ENTER => this method works:
        tbl_user_list.getModel().addTableModelListener(e -> {

            if(e.getType() == TableModelEvent.UPDATE){
                // get the values at that row:
                int selectedRowIndex = tbl_user_list.getSelectedRow();

                String ID = tbl_user_list.getValueAt(selectedRowIndex, 0).toString();
                String nameSurname = tbl_user_list.getValueAt(selectedRowIndex, 1).toString();
                String username = tbl_user_list.getValueAt(selectedRowIndex, 2).toString();
                String password = tbl_user_list.getValueAt(selectedRowIndex, 3).toString();
                String userRole = tbl_user_list.getValueAt(selectedRowIndex, 4).toString();

                System.out.println(ID + ", " + nameSurname + ", " + username + ", " + password + ", " + userRole);

                boolean isUpdated = (this.previousPassword == password)? User.updateUserWithoutPassword(ID, nameSurname, username, userRole):
                                            User.updateUserWithPassword(ID, nameSurname, username, password, userRole);

                if(isUpdated){
                    Helper.showMessage("done");
                }else{
                    Helper.showMessage("Update operation failed!");
                }
                loadUserTableModel();
                loadEducatorComboItems();
                loadCourseTableModel();
            }
        });

        // when the add button is clicked, check input fields and add a new user
        btn_add_user.addActionListener(e->{

            if(Helper.isFieldEmpty(fld_name_surname) || Helper.isFieldEmpty(fld_username) || Helper.isFieldEmpty(fld_password)){
                Helper.showMessage("fill");
            }else{
                // if the fields are not empty get their values
                String name = fld_name_surname.getText();
                String username = fld_username.getText();
                String password = fld_password.getText();
                String role = cmb_user_role.getSelectedItem().toString();

                // if no user exists with entered data, add a new user
                if(User.checkUserExists(username)){
                    Helper.showMessage("A user with entered username already exists!");
                }else if(User.add(name, username, password, role)){
                    Helper.showMessage("done");
                    loadUserTableModel();
                    loadEducatorComboItems();
                    clearInputFields();
                }else{
                    Helper.showMessage("The user could not be added.");
                }
            }

        });

        // when click on the delete button, delete the user with selected username
        btn_delete.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_username_delete)){
                Helper.showMessage("fill");
            }else{
                if(Helper.confirm("sure")){
                    String selectedUsername = fld_username_delete.getText();
                    System.out.println(selectedUsername);
                    if(User.deleteUser(selectedUsername)){
                        Helper.showMessage("done");
                        loadUserTableModel();
                        loadEducatorComboItems();
                        loadCourseTableModel();
                    }else{
                        Helper.showMessage("Delete operation failed!");
                    }
                }
            }
        });

        // When click on the search button, make a search:
        btn_search.addActionListener(e -> {
            String name = fld_search_by_name.getText();
            String username = fld_search_by_username.getText();
            String role = cmb_search_by_role.getSelectedItem().toString();

            //System.out.println(name + ", " + username + ", " + role);
            loadUserTableModel(User.searchUsers(name, username, role));
            clearSearchInputFields();
        });

        // ########## USER - END ############

        // ########## PATH - START ########################

        // PopupMenu for path list:
        path_popup_menu = new JPopupMenu();
        JMenuItem update_path_menu = new JMenuItem("Update");
        JMenuItem delete_path_menu = new JMenuItem("Delete");
        path_popup_menu.add(update_path_menu);
        path_popup_menu.add(delete_path_menu);

        // initialize the table model for path table:
        mdl_path_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column==0 || column==1){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        // columns of the model:
        Object[] col_path_list = {"ID", "Path Name"};
        mdl_path_list.setColumnIdentifiers(col_path_list);

        // add rows: fetch data from database
        row_path_list = new Object[col_path_list.length];
        loadPathTableModel();


        tbl_path_list.setModel(mdl_path_list);

        tbl_path_list.setComponentPopupMenu(path_popup_menu);
        tbl_path_list.getColumnModel().getColumn(0).setMaxWidth(60);
        tbl_path_list.getTableHeader().setReorderingAllowed(false);

        tbl_path_list.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON3){

                    tbl_path_list.clearSelection();

                    Point point = e.getPoint();
                    int selectedRowIndex = tbl_path_list.rowAtPoint(point);
                    if(selectedRowIndex != -1){
                        tbl_path_list.setRowSelectionInterval(selectedRowIndex, selectedRowIndex);
                    }
                }
            }

        });

        update_path_menu.addActionListener(e -> {
            int selectedRowIndex = tbl_path_list.getSelectedRow();
            if(selectedRowIndex != -1){
                int pathID = (Integer) tbl_path_list.getValueAt(selectedRowIndex, 0);
                UpdatePathGUI updatePathGUI = new UpdatePathGUI(pathID);
                updatePathGUI.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadPathTableModel();
                        loadPathComboItems();
                        loadPathTableModel();
                    }
                });
            }
        });

        delete_path_menu.addActionListener(e -> {
            int selectedRowIndex = tbl_path_list.getSelectedRow();
            if(selectedRowIndex != -1 && Helper.confirm("sure")){
                int pathID = (Integer) tbl_path_list.getValueAt(selectedRowIndex, 0);
                if(Path.deletePath(pathID)){
                    Helper.showMessage("done");
                    loadPathTableModel();
                    loadPathComboItems();
                    loadCourseTableModel();
                }else {
                    Helper.showMessage("error");
                }
            }
        });

        // When add path button is clicked:
        btn_add_path.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_path_name)){
                Helper.showMessage("fill");
            }else{
                String name = fld_path_name.getText();
                if(Path.checkPathExists(name)){
                    Helper.showMessage("A path with entered name already exists!");
                }else if(Path.addPath(name)){
                    Helper.showMessage("done");
                    loadPathTableModel();
                    loadPathComboItems();
                }else{
                    Helper.showMessage("error");
                }
                // clear the input field for path name:
                fld_path_name.setText(null);
            }
        });

        // ########## PATH - END ##########

        // ########## COURSE - START ##################

        mdl_course_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
                //return super.isCellEditable(row, column);
            }
        };
        Object[] col_course_list = {"ID", "Course Name", "Programming Language", "Path", "Instructor"};
        mdl_course_list.setColumnIdentifiers(col_course_list);
        row_course_list = new Object[col_course_list.length];
        loadCourseTableModel();

        tbl_course_list.setModel(mdl_course_list);
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);

        loadPathComboItems();
        loadEducatorComboItems();

        btn_add_course.addActionListener(e -> {
            Item path = (Item) cmb_path.getSelectedItem();
            Item educator = (Item) cmb_educator.getSelectedItem();
            if(Helper.isFieldEmpty(fld_course_name) || Helper.isFieldEmpty(fld_programming_language)){
                Helper.showMessage("fill");
            }else{
                String courseName = fld_course_name.getText();
                String lang = fld_programming_language.getText();
                String userID = (String) educator.getKey();
                int pathID = (int) path.getKey();

                if(Course.addCourse(userID, pathID, courseName, lang)){
                    Helper.showMessage("done");
                    loadCourseTableModel();
                }else{
                    Helper.showMessage("Course could not added!");
                }

            }
        });

        // ########## COURSE - END ###########


        // When logout button is clicked go back to the login page:
        btn_logout.addActionListener(e -> {
            this.dispose();
            LoginGUI loginGUI = new LoginGUI();
        });

    }

    public void loadPathComboItems(){
        cmb_path.removeAllItems();

        for (Path path:Path.getPathList()){
            cmb_path.addItem(new Item(path.getID(), path.getName()));
        }
    }

    public void loadEducatorComboItems(){
        cmb_educator.removeAllItems();

        for (User user:User.getListOfEducators()){
            cmb_educator.addItem(new Item(user.getId(), user.getName()));
        }
    }

    private void loadCourseTableModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_course_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for(Course course:Course.getCourseList()){
            i = 0;
            row_course_list[i++] = course.getId();
            row_course_list[i++] = course.getName();
            row_course_list[i++] = course.getLang();
            row_course_list[i++] = course.getPath().getName();
            row_course_list[i++] = course.getEducator().getName();

            mdl_course_list.addRow(row_course_list);
        }
    }

    private void loadPathTableModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_path_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for(Path path : Path.getPathList()){
            i=0;
            row_path_list[i++] = path.getID();
            row_path_list[i++] = path.getName();

            mdl_path_list.addRow(row_path_list);
        }

    }

    // Clears the table. Then, fetches data from db and adds them to the table as a row.
    public void loadUserTableModel(){
        // clear the table to avoid showing duplicated rows:
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);

        for(User user : User.getListOfUsers()){
            row_user_list[0] =  user.getId();
            row_user_list[1] = user.getName();
            row_user_list[2] = user.getUsername();
            row_user_list[3] = user.getPassword();
            row_user_list[4] = user.getRole();

            mdl_user_list.addRow(row_user_list);
        }
    }

    // Clears the table. Then, fetches data from db and adds them to the table as a row.
    // method Overloaded.
    public void loadUserTableModel(ArrayList<User> filteredUsers){
        // clear the table to avoid showing duplicated rows:
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);

        for(User user : filteredUsers){
            row_user_list[0] =  user.getId();
            row_user_list[1] = user.getName();
            row_user_list[2] = user.getUsername();
            row_user_list[3] = user.getPassword();
            row_user_list[4] = user.getRole();

            mdl_user_list.addRow(row_user_list);
        }
    }

    // clears the entered data within the fields
    public void clearInputFields(){
        fld_name_surname.setText(null);
        fld_username.setText(null);
        fld_password.setText(null);

        // if given index is -1, it shows nothing selected.
        cmb_user_role.setSelectedIndex(0);
    }

    public void clearSearchInputFields(){
        fld_search_by_name.setText(null);
        fld_search_by_username.setText(null);
        cmb_search_by_role.setSelectedIndex(0);
    }


}

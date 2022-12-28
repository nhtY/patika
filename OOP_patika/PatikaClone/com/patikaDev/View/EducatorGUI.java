package com.patikaDev.View;

import com.patikaDev.Helper.Config;
import com.patikaDev.Helper.Helper;
import com.patikaDev.Model.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class EducatorGUI extends JFrame{
    private Educator educator;
    private JPanel wrapper;
    private JLabel lbl_welcome;
    private JPanel pnl_educator_top;
    private JTabbedPane tab_educator;
    private JPanel pnl_course_list;
    private JScrollPane scrl_course_list;
    private JTable tbl_course_list;
    private JButton btn_logout;
    private JPanel pnl_content_list;
    private JScrollPane scrl_content_list;
    private JTable tbl_content_list;
    private JLabel lbl_title;
    private JTextField fld_content_title;
    private JLabel lbl_content_description;
    private JLabel lbl_content_link;
    private JTextField fld_content_link;
    private JComboBox cmb_content_course_name;
    private JButton btn_content_add;
    private JTextArea fld_content_description;
    private JLabel lbl_content_update;
    private JTextField fld_content_id_update;
    private JButton btn_update_content;
    private JPanel pnl_quiz;
    private JPanel pnl_quiz_top;
    private JComboBox cmb_quiz_course_name_search;
    private JButton btn_search_quiz;
    private JPanel pnl_quiz_bleft;
    private JPanel pnl_quiz_bright;
    private JScrollPane scrl_quiz_list;
    private JTable tbl_quiz_list;
    private JComboBox cmb_content_name;
    private JComboBox cmb_course_name_quiz;
    private JScrollPane scrl_questions;
    private JTable tbl_question_list;
    private JTextArea txt_question;
    private JTextField fld_choice_A;
    private JTextField fld_choice_B;
    private JTextField fld_choice_C;
    private JTextField fld_choice_D;
    private JButton btn_add_question;
    private JComboBox cmb_correct_choice;
    private JButton btn_update_question;
    private JTextField fld_selected_question_ID;
    private JButton btn_delete;
    private JButton btn_content_delete;
    private JTextField fld_quiz_id_delete;
    private JButton btn_delete_quiz;
    private DefaultTableModel mdl_course_list;
    private Object[] col_course_list;
    private Object[] row_course_list;

    private DefaultTableModel mdl_content_list;
    private Object[] col_content_list;
    private Object[] row_content_list;

    private DefaultTableModel mdl_quiz_list;
    private Object[] col_quiz_list;
    private Object[] row_quiz_list;

    private DefaultTableModel mdl_question_list;
    private Object[] col_question_list;
    private Object[] row_question_list;

    public EducatorGUI(Educator educator) {
        this.educator = educator;
        this.add(wrapper);
        this.setSize(500, 400);
        this.setLocation(Helper.getScreenCenterPoint("x", this.getSize()), Helper.getScreenCenterPoint("y", this.getSize()));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);

        lbl_welcome.setText("WELCOME " + educator.getUsername());

        setVisible(true);


        // #### COURSES - START ####
        mdl_course_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        col_course_list = new Object[]{"ID", "Path Name", "Course Name", "Language", "Content Number"};
        mdl_course_list.setColumnIdentifiers(col_course_list);

        row_course_list = new Object[col_course_list.length];
        loadCourseTableModel();

        tbl_course_list.setModel(mdl_course_list);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(75);

        // ## COURSE - END ##


        // #### CONTENT - START ####

        mdl_content_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        col_content_list = new Object[]{"ID", "Course Name", "Title", "Description", "Link"};
        mdl_content_list.setColumnIdentifiers(col_content_list);

        row_content_list = new Object[col_content_list.length];
        loadContentTableModel();
        loadComboCourseItems();

        tbl_content_list.setModel(mdl_content_list);
        tbl_content_list.getTableHeader().setReorderingAllowed(false);
        tbl_content_list.getColumnModel().getColumn(0).setMaxWidth(75);

        fld_content_description.setLineWrap(true);

        btn_content_add.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_content_title) || Helper.isFieldEmpty(fld_content_description) || Helper.isFieldEmpty(fld_content_link)){
                Helper.showMessage("fill");
            }else{
                Item courseItem = (Item) cmb_content_course_name.getSelectedItem();
                int course_id = (int) courseItem.getKey();
                String title = fld_content_title.getText();
                String description = fld_content_description.getText();
                String link = fld_content_link.getText();

                if(Content.doesContentExist(title, link)){
                    Helper.showMessage("A content with the entered title OR link already exists!");
                }else if(Content.addContent(course_id, title, description, link)){
                    Helper.showMessage("done");
                    loadContentTableModel();
                    loadCourseTableModel(); // course's num of content info should be updated.
                }else{
                    Helper.showMessage("error");
                }
            }
        });

        tbl_content_list.getSelectionModel().addListSelectionListener(e -> {
            int selectedRowIndex = tbl_content_list.getSelectedRow();

            if(selectedRowIndex != -1){
                int selectedContentID = (int) tbl_content_list.getValueAt(selectedRowIndex, 0);
                fld_content_id_update.setText(String.valueOf(selectedContentID));
            }
        });

        btn_update_content.addActionListener(e -> {
            // it is enough to have at least one field to execute update operation
            if(Helper.isFieldEmpty(fld_content_title) && Helper.isFieldEmpty(fld_content_description) && Helper.isFieldEmpty(fld_content_link)){
                Helper.showMessage("At least one of the fields must be filled for update");
            }else{

                int selectedRowIndex = tbl_content_list.getSelectedRow();

                if(selectedRowIndex != -1){
                    int selectedContentID = (int) tbl_content_list.getValueAt(selectedRowIndex, 0);
                    fld_content_id_update.setText(String.valueOf(selectedContentID));

                    String selectedCourseName = (String) tbl_content_list.getValueAt(selectedRowIndex, 1);
                    Course course = Course.fetchOne(selectedCourseName);
                    int selectedCourseID = course==null? -1: course.getId();

                    String selectedTitle = (String) tbl_content_list.getValueAt(selectedRowIndex, 2);
                    String selectedDescription = (String) tbl_content_list.getValueAt(selectedRowIndex, 3);
                    String selectedLink = (String) tbl_content_list.getValueAt(selectedRowIndex, 4);

                    Item courseItem = (Item) cmb_content_course_name.getSelectedItem();
                    int course_id = (int) courseItem.getKey() == selectedCourseID? selectedCourseID
                            : (int) courseItem.getKey();

                    String title = fld_content_title.getText();
                    title = title.isBlank() || title.isEmpty()?
                            selectedTitle : title;


                    String description = fld_content_description.getText();
                    description = description.isBlank() || description.isEmpty()?
                            selectedDescription : description;

                    String link = fld_content_link.getText();
                    link = link.isBlank() || link.isEmpty()?
                            selectedLink : link;

                    if(Content.updateContent(selectedContentID, course_id, title, description, link)){
                        Helper.showMessage("done");
                        loadContentTableModel();
                    }else{
                        Helper.showMessage("error");
                    }

                }else{
                    Helper.showMessage("No row selected to update");
                }

            }
        });

        btn_content_delete.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_content_id_update)){
                Helper.showMessage("fill");
            }else{
                int contentID = Integer.valueOf(fld_content_id_update.getText());
                if(Content.deleteContent(contentID)){
                    Helper.showMessage("done");
                    loadContentTableModel();
                    loadCourseTableModel(); // because num of contents is decremented.
                    loadQuizTableModel(); // if content is deleted, its quiz's will also be deleted.
                }else{
                    Helper.showMessage("error");
                }
            }
        });

        // ## CONTENT - END ##


        // #### QUIZ - START ####
        loadComboCourseItemsForQuiz();
        loadComboContentItemsForQuiz();

        mdl_quiz_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        col_quiz_list = new Object[]{"ID", "Course Name", "Content Title", "Num Of Questions"};
        mdl_quiz_list.setColumnIdentifiers(col_quiz_list);

        row_quiz_list = new Object[col_quiz_list.length];
        loadQuizTableModel();

        tbl_quiz_list.setModel(mdl_quiz_list);
        tbl_quiz_list.getTableHeader().setReorderingAllowed(false);
        tbl_quiz_list.getColumnModel().getColumn(0).setMaxWidth(40);

        // search quiz by course name:
        btn_search_quiz.addActionListener(e -> {
            Item courseItem = (Item) cmb_quiz_course_name_search.getSelectedItem();
            int courseID = (int) courseItem.getKey();
            if(courseID != -1){
                ArrayList<Quiz> quizes = Quiz.getQuizListByCourseName(courseID);
                loadQuizTableModel(quizes);
            }else{
                loadQuizTableModel();
            }
        });


        // get the questions according to selected quiz row on the table:
        tbl_quiz_list.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRowIndex = tbl_quiz_list.getSelectedRow();
                if(selectedRowIndex != -1){
                    int quizID = (int) tbl_quiz_list.getValueAt(selectedRowIndex, 0);
                    loadQuestionTableModel(quizID);
                }
            }
        });


        // Set the content combo items based on selected course:
        cmb_course_name_quiz.addActionListener(e -> {
            //System.out.println(cmb_course_name_quiz.getSelectedItem().toString());
            loadComboContentItemsForQuiz();
        });

        tbl_quiz_list.getSelectionModel().addListSelectionListener(e -> {
            int selectedRowIndex = tbl_quiz_list.getSelectedRow();

            if(selectedRowIndex != -1){
                int selectedQuizID = (int) tbl_quiz_list.getValueAt(selectedRowIndex, 0);
                fld_quiz_id_delete.setText(String.valueOf(selectedQuizID));
            }
        });

        btn_delete_quiz.addActionListener(e -> {
            int rowIndex = tbl_quiz_list.getSelectedRow();
            if(rowIndex != -1){
                int quizID = (int) tbl_quiz_list.getValueAt(rowIndex, 0);
                if(Helper.isFieldEmpty(fld_quiz_id_delete)){
                    Helper.showMessage("fill");
                }else{
                    if(Quiz.deleteQuiz(quizID)){
                        Helper.showMessage("done");
                        loadQuizTableModel();
                        loadQuestionTableModel(quizID);
                        fld_quiz_id_delete.setText(null);
                    }else{
                        Helper.showMessage("error");
                    }
                }
            }
        });

        // ## QUIZ - END ##


        // #### QUESTION - START ####
        mdl_question_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        col_question_list = new Object[]{"ID", "Question", "Correct Choice"};
        mdl_question_list.setColumnIdentifiers(col_question_list);

        row_question_list = new Object[col_question_list.length];

        // loadModel method will be called when a quiz selected.

        tbl_question_list.setModel(mdl_question_list);
        tbl_question_list.getTableHeader().setReorderingAllowed(false);
        tbl_question_list.getColumnModel().getColumn(0).setMaxWidth(40);

        btn_add_question.addActionListener(e -> {
            Item selectedContent = (Item) cmb_content_name.getSelectedItem();
            assert selectedContent != null;
            int contentID = -1;
            if(selectedContent != null){
                contentID = (int) selectedContent.getKey();
            }

            if(contentID == -1 || Helper.isFieldEmpty(txt_question) || Helper.isFieldEmpty(fld_choice_A)
              || Helper.isFieldEmpty(fld_choice_B) || Helper.isFieldEmpty(fld_choice_C) || Helper.isFieldEmpty(fld_choice_D))
            {
                Helper.showMessage("fill");
            }else{
                String questionText = txt_question.getText();
                String correctChoice= (String) cmb_correct_choice.getSelectedItem(); // Item selectedChoice
                //String correctChoice = selectedChoice.getValue().toString();

                String A = fld_choice_A.getText();
                String B = fld_choice_B.getText();
                String C = fld_choice_C.getText();
                String D = fld_choice_D.getText();


                if(Question.addQuestion(contentID, questionText, correctChoice, A, B, C, D)){
                    Helper.showMessage("done");
                    loadQuizTableModel();
                    loadQuestionTableModel(Quiz.doesQuizExist(contentID));
                }else{
                    Helper.showMessage("error");
                }

            }

        });


        tbl_question_list.getSelectionModel().addListSelectionListener(e -> {
            int selectedRowIndex = tbl_question_list.getSelectedRow();

            if(selectedRowIndex != -1){
                int selectedQuestionID = (int) tbl_question_list.getValueAt(selectedRowIndex, 0);
                fld_selected_question_ID.setText(String.valueOf(selectedQuestionID));

                String q_text = (String) tbl_question_list.getValueAt(selectedRowIndex, 1);
                txt_question.setText(q_text);

                String correctChoice = (String) tbl_question_list.getValueAt(selectedRowIndex, 2);
                int cmbIndex = -1;
                switch (correctChoice){
                    case "A":
                        cmbIndex = 0;
                        break;
                    case "B":
                        cmbIndex = 1;
                        break;
                    case "C":
                        cmbIndex = 2;
                        break;
                    case "D":
                        cmbIndex = 3;
                        break;
                }
                cmb_correct_choice.setSelectedIndex(cmbIndex);

                ArrayList<Choice> choices = Choice.getChoices(selectedQuestionID);
                fld_choice_A.setText(choices.get(0).getChoice_text());
                fld_choice_B.setText(choices.get(1).getChoice_text());
                fld_choice_C.setText(choices.get(2).getChoice_text());
                fld_choice_D.setText(choices.get(3).getChoice_text());
            }
        });

        btn_delete.addActionListener(e -> {
            int selectedRowIndex = tbl_question_list.getSelectedRow();
            if(selectedRowIndex != -1){
                int questionID = (int) tbl_question_list.getValueAt(selectedRowIndex, 0);
                if(Question.delete(questionID)){
                    Helper.showMessage("done");
                    loadQuestionTableModel(getSelectedQuizID());
                    loadQuizTableModel();
                    fld_selected_question_ID.setText(null);
                }else {
                    Helper.showMessage("error");
                }
            }
        });

        btn_update_question.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_selected_question_ID)){
                Helper.showMessage("fill");
            }else{
                int questionID = Integer.parseInt(fld_selected_question_ID.getText());

                if(Helper.isFieldEmpty(txt_question) || Helper.isFieldEmpty(fld_choice_A) || Helper.isFieldEmpty(fld_choice_B)
                    || Helper.isFieldEmpty(fld_choice_C) || Helper.isFieldEmpty(fld_choice_D))
                {
                    Helper.showMessage("fill");
                }else{
                    String qText = txt_question.getText();
                    String correctChoice = (String) cmb_correct_choice.getSelectedItem();

                    String A = fld_choice_A.getText();
                    String B = fld_choice_B.getText();
                    String C = fld_choice_C.getText();
                    String D = fld_choice_D.getText();

                    if(Question.update(questionID, qText, correctChoice, A, B, C, D)){
                        Helper.showMessage("done");
                        loadQuestionTableModel(getSelectedQuizID());
                    }else{
                        Helper.showMessage("error");
                    }
                }
            }
        });

        // ## QUESTION - END ##




        btn_logout.addActionListener(e -> {
            this.dispose();
            LoginGUI loginGUI = new LoginGUI();
        });


        // When tabs are first clicked, set the size of window so elements in it can be shown easily.
        pnl_content_list.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                int width = (int) getSize().getWidth();
                int height = (int) getSize().getHeight();
                if (width<600 || height<600){
                    width = width<600? 600 : width;
                    height = height<600? 600 : height;
                    setSize(width, height);
                    // relocateWindow(); ekranın ortasına al (gereksiz sanki)
                }
            }
        });


        pnl_quiz.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                int width = (int) getSize().getWidth();
                int height = (int) getSize().getHeight();
                if (width<600 || height<600){
                    width = width<600? 600 : width;
                    height = height<600? 600 : height;
                    setSize(width, height);
                    // relocateWindow(); ekranın ortasına al (gereksiz sanki)
                }
            }
        });

    }

    private int getSelectedQuizID(){
        int selectedID = -1;
        int index = tbl_quiz_list.getSelectedRow();
        if (index != -1){
            selectedID = (int) tbl_quiz_list.getValueAt(index, 0);
        }
        System.out.println(selectedID);
        return selectedID;
    }

    private void loadQuizTableModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_quiz_list.getModel();
        clearModel.setRowCount(0);

        int i;
        for(Quiz q: Quiz.getQuizList(educator.getId())){
            i = 0;
            row_quiz_list[i++] = q.getId();
            row_quiz_list[i++] = q.getCourseName();
            row_quiz_list[i++] = q.getContentTitle();
            row_quiz_list[i++] = q.getNumOfQuestion();

            mdl_quiz_list.addRow(row_quiz_list);
        }
    }

    private void loadQuizTableModel(ArrayList<Quiz> quizes) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_quiz_list.getModel();
        clearModel.setRowCount(0);

        int i;
        for(Quiz q: quizes){
            i = 0;
            row_quiz_list[i++] = q.getId();
            row_quiz_list[i++] = q.getCourseName();
            row_quiz_list[i++] = q.getContentTitle();
            row_quiz_list[i++] = q.getNumOfQuestion();

            mdl_quiz_list.addRow(row_quiz_list);
        }
    }

    private void loadComboCourseItems() {
        cmb_content_course_name.removeAllItems();
        cmb_course_name_quiz.removeAllItems();
        for(Course course:Course.getCourseList(educator.getId())){
            cmb_content_course_name.addItem(new Item(course.getId(), course.getName()));
            cmb_course_name_quiz.addItem(new Item(course.getId(), course.getName()));
        }
    }

    private void loadComboContentItemsForQuiz(){
        cmb_content_name.removeAllItems();
        Item selectedCourse = (Item) cmb_course_name_quiz.getSelectedItem();
        int courseID = (int) selectedCourse.getKey();
        for(Content content : Content.getCourseContents(courseID)){
            cmb_content_name.addItem(new Item(content.getId(), content.getTitle()));
        }
    }

    private void loadComboCourseItemsForQuiz(){
        cmb_quiz_course_name_search.removeAllItems();
        cmb_quiz_course_name_search.addItem(new Item(-1, "ALL"));
        for (Course course:Course.getCourseList(educator.getId())){
            cmb_quiz_course_name_search.addItem(new Item(course.getId(), course.getName()));
        }
    }

    private void loadContentTableModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_content_list.getModel();
        clearModel.setRowCount(0);

        int i;
        for(Content content:Content.getContents(educator.getId())){
           i=0;
           row_content_list[i++] = content.getId();
           row_content_list[i++] = Course.fetchOne(content.getCourse_id()).getName();
           row_content_list[i++] = content.getTitle();
           row_content_list[i++] = content.getDescription();
           row_content_list[i++] = content.getLink();

           mdl_content_list.addRow(row_content_list);
        }
    }

    private void loadCourseTableModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_course_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for(Course course: Course.getCourseList(educator.getId())){
           i=0;
           row_course_list[i++] = course.getId();
           row_course_list[i++] = course.getPath().getName();
           row_course_list[i++] = course.getName();
           row_course_list[i++] = course.getLang();
           row_course_list[i++] = Content.getCourseContents(course.getId()).size();//course.getContents().size(); // number of contents.

           mdl_course_list.addRow(row_course_list);
        }
    }

    private void loadQuestionTableModel(int quizID){
        if (quizID == -1) return;
        DefaultTableModel clearModel = (DefaultTableModel) tbl_question_list.getModel();
        clearModel.setRowCount(0);

        int i;
        for (Question question : Question.getQuestions(quizID)){
            i=0;

            row_question_list[i++] = question.getId();
            row_question_list[i++] = question.getQ_text();
            row_question_list[i++] = question.getCorrect_choice();

            mdl_question_list.addRow(row_question_list);
        }
    }

    private void relocateWindow(){
        this.setLocation(Helper.getScreenCenterPoint("x", this.getSize()), Helper.getScreenCenterPoint("y", this.getSize()));
    }
}

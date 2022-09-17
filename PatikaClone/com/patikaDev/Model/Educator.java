package com.patikaDev.Model;

import com.patikaDev.Helper.DBConnector;

import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Educator extends User{
    public Educator(String ID, String name, String username, String password, String role){
        super(ID, name, username, password, role);
    }

    public Educator(){

    }

}

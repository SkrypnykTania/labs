package com.laba.task5;

import javax.sql.DataSource;
import java.sql.*;

public class Db {


    DataSource dataSource;

    public Db() {
        this.dataSource = BaseDataSource.getDataSource();
    }

    private void createFaculty(){
        String sql = "create table IF NOT EXISTS Faculties ("+
                 "code varchar(10) not null PRIMARY KEY,"+
                 "name varchar(100) not null,"+
                " dean varchar(150) not null)";
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void createSpeciality(){
        String sql = "create table IF NOT EXISTS Specialities ("+
                "code varchar(10) not null PRIMARY KEY,"+
                "name varchar(100) not null,"+
                "info text  null,"+
                "faculty_code varchar(10) not null," +
                "CONSTRAINT Faculties_fk \n" +
                "    FOREIGN KEY (faculty_code)  REFERENCES Faculties (code))";
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void createGroup(){
        String sql = "create table IF NOT EXISTS Groups ("+
                "code varchar(10) not null PRIMARY KEY,"+
                " course int not null,"+
                "speciality_code varchar(10) not null," +
                "CONSTRAINT Specialities_fk \n" +
                "    FOREIGN KEY (speciality_code)  REFERENCES Specialities (code))";
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void createStudent(){
        String sql = "create table IF NOT EXISTS Students ("+
                "credit_book varchar(10) not null PRIMARY KEY,"+
                "first_name varchar(100) not null,"+
                "last_name varchar(100) not null,"+
                "birth_day date not null,"+
                "group_code varchar(10) not null," +
                "CONSTRAINT Groups_fk \n" +
                "    FOREIGN KEY (group_code)  REFERENCES Groups (code))";
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void initTables(){
        createFaculty();
        createSpeciality();
        createGroup();
        createStudent();
    }
    public void showTables(){
        DatabaseMetaData meta = null;
        try {
            meta = dataSource.getConnection().getMetaData();
            ResultSet rs1 = meta.getTables(null, null, null,new String[] {"TABLE"});
            System.out.println("Список таблиць");
            while (rs1.next())
            {
                System.out.println(rs1.getString("TABLE_NAME"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

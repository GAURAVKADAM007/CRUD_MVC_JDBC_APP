package com.stackninjas.crudapp.dao;

import com.stackninjas.crudapp.exception.DAOException;
import com.stackninjas.crudapp.model.Student;
import com.stackninjas.crudapp.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private static final String INSERT_SQL = "INSERT INTO student(name,email,mobile) VALUES(?,?,?)";
    private static final String DELETE_SQL = "DELETE FROM student WHERE id=?";
    private static final String UPDATE_SQL = "UPDATE student SET name=? , email=? , mobile=? WHERE id=?";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM Student WHERE id=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM student ORDER BY id";

    @Override
    public void insert(Student student) {
//        VIEW ---> CONTROLLER ---> MODEL(DAO) ----> DATABASE

        try(Connection connection = JDBCUtils.fetchConnection();
            PreparedStatement stmt = connection.prepareStatement(INSERT_SQL)){

            stmt.setString(1,student.getName());
            stmt.setString(2,student.getEmail());
            stmt.setString(3,student.getMobile());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Unable to Insert Student",e);
        }
    }

    @Override
    public void delete(int id) {
        try(Connection connection = JDBCUtils.fetchConnection();
            PreparedStatement stmt = connection.prepareStatement(DELETE_SQL)){

            stmt.setInt(1,id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Unable to Delete Student",e);
        }
    }

    @Override
    public Student getStudentById(int id) {
        Student student = null;
        try(Connection connection = JDBCUtils.fetchConnection();
            PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID_SQL)){

            stmt.setInt(1,id);

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    student = new Student(
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("mobile"));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Unable to Fetch Student",e);
        }
        return student;
    }

    @Override
    public void update(Student student) {
        try(Connection connection = JDBCUtils.fetchConnection();
            PreparedStatement stmt = connection.prepareStatement(UPDATE_SQL)){

            stmt.setString(1,student.getName());
            stmt.setString(2,student.getEmail());
            stmt.setString(3,student.getMobile());
            stmt.setInt(4,student.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Unable to Update Student",e);
        }

    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        try(Connection connection = JDBCUtils.fetchConnection();
            PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_SQL);
            ResultSet rs = stmt.executeQuery()){

            while(rs.next()){
                studentList.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("mobile"))
                    );
            }
        } catch (SQLException e) {
            throw new DAOException("Unable to fetch Student",e);
        }
        return studentList;
    }
}

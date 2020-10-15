package com.sistec.student;

import com.sistec.db.StudentDb;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import javax.servlet.jsp.tagext.TryCatchFinally;

public class StudentDao {

    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;

    public boolean addStudent(StudentDto dto, InputStream photo) {
        boolean flag = false;
        if (conn == null) {
            conn = StudentDb.getStudentDb();
        }
        try {
            // Create query to insert data into std_master table
            String query = "insert into student_master(name, email, pwd, mno, dob, city, gender, photo, hobby) values(?,?,?,?,?,?,?,?,?)";
            // create prepareStatement
            ps = conn.prepareStatement(query);
            // read data from dto and set data to parameters of query
            ps.setString(1, dto.getName());
            ps.setString(2, dto.getEmail());
            ps.setString(3, dto.getPwd());
            ps.setString(4, dto.getMno());
            ps.setString(5, dto.getDob());
            ps.setString(6, dto.getCity());
            ps.setString(7, dto.getGebnder());
            ps.setBlob(8, photo);
            ps.setString(9, dto.getHobby());
            // Execute the query
            if (ps.executeUpdate() > 0) {
                flag = true;
            }
        } catch (Exception e) {
            System.out.println("Exception at addStudent():" + e);
        } finally {
            ps = null;
            conn = null;
            return flag;
        }
    }

    public ArrayList<StudentDto> getAllStudents() {
        ArrayList<StudentDto> list = new ArrayList<>();
        StudentDto dto = null;
        if (conn == null) {
            conn = StudentDb.getStudentDb();
        }
        try {
            String query = "select *from student_master";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                dto = new StudentDto();
                dto.setCity(rs.getString("city"));
                dto.setEmail(rs.getString("email"));
                dto.setSid(rs.getInt("sid"));
                dto.setDob(rs.getString("dob"));
                dto.setPwd(rs.getString("pwd"));
                dto.setGebnder(rs.getString("gender"));
                dto.setHobby(rs.getString("hobby"));
                dto.setName(rs.getString("name"));
                dto.setMno(rs.getString("mno"));

                byte arr[] = rs.getBytes("photo");
                Encoder encoder = Base64.getEncoder();
                String photo = encoder.encodeToString(arr);

                dto.setPhoto(photo);
                list.add(dto);
            }
        } catch (Exception e) {
            System.out.println("Exception at getAllStudents():" + e);
        } finally {
            rs = null;
            ps = null;
            conn = null;
            if (list.isEmpty()) {
                list = null;
            }
            return list;
        }
    }

    public StudentDto getStudent(int sid) {
        StudentDto dto = null;
        if(conn==null){
            conn=StudentDb.getStudentDb();
        }
        try {
            String sql="select*from student_master where sid=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1, sid);
            rs=ps.executeQuery();
            if(rs.next()){
                dto = new StudentDto();
                dto.setCity(rs.getString("city"));
                dto.setEmail(rs.getString("email"));
                dto.setSid(rs.getInt("sid"));
                dto.getPwd(rs.getString("pwd"));
                dto.setDob(rs.getString("dob"));
                dto.setGebnder(rs.getString("gender"));
                dto.setHobby(rs.getString("hobby"));
                dto.setName(rs.getString("name"));
                dto.setMno(rs.getString("mno"));

            }
        } catch (Exception e) {
            System.out.println("Exception at getStudent():"+e);
        } finally {
            rs = null;
            ps = null;
            conn = null;
            return dto;
        }

    }
}

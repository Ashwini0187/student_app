package com.sistec.student;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(urlPatterns = {"/registration"})
@MultipartConfig(maxFileSize = 23456789)

public class Registrationservlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String pwd = req.getParameter("pwd");
        String mno = req.getParameter("mno");
        String city = req.getParameter("city");
        String gender = req.getParameter("gender");
        String hobbies[] = req.getParameterValues("hobby");
        String hobby = "";
        String dob = req.getParameter("dob");

        if (hobbies != null && 0 < hobbies.length) {
            hobby = hobbies[0];
        }
        for (int i = 1; i < hobbies.length; i++) {
            hobby = hobby + "," + hobbies[i];
        }
        Part p = req.getPart("photo");
        InputStream inputStream = p.getInputStream();
        StudentDto dto = new StudentDto();
        dto.setName(name);
        dto.setEmail(email);
        dto.setCity(city);
        dto.setGebnder(gender);
        dto.setHobby(hobby);
        dto.setPwd(pwd);
        dto.setDob(dob);
        dto.setMno(mno);
        StudentDao dao = new StudentDao();
        if (dao.addStudent(dto, inputStream)) {
            System.out.println("success");
        } else {
            System.out.println("faild");
        }
    }
}

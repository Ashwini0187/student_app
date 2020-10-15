
<%@page import="java.util.ArrayList"%>
<%@page import="com.sistec.student.StudentDto"%>
<%@page import="com.sistec.student.StudentDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Insert title here</title>
    </head>
    <body>
        <%
            StudentDao dao = new StudentDao();
            ArrayList<StudentDto> al = dao.getAllStudents();
            if (al != null) {
        %>
        <table>
            <tr>
                <th>S.NO.</th>
                <th>Name</th>
                <th>Email</th>
                <th>M.NO.</th>
                <th>Pwd</th>
                <th>Gender</th>
                <th>City</th>
                <th>Hobbies</th>
                <th>D.O.B</th>
                <th>Photo</th>
                <th>Action</th>
            </tr>
            <%
                int s = 0;
                for (StudentDto dto : al) {
            %>
            <tr>
                <td><%=++s%></td>
                <td><%=dto.getName()%></td>
                <td><%=dto.getEmail()%></td>
                <td><%=dto.getMno()%></td>
                <td><%=dto.getPwd()%></td>>
                <td><%=dto.getGebnder()%></td>
                <td><%=dto.getCity()%></td>
                <td><%=dto.getHobby()%></td>
                <td><%=dto.getDob()%></td>
                <td>
                    <img src="data:image/jpeg;base64,<%=dto.getPhoto()%>" width="50" height="50">
                </td>

                <td><a href="editStudent.jsp?sid=<%=dto.getSid()%>">Edit</a> 
                 <a>Delete</a></td>
            </tr>
            <%
                }
            %>

        </table>

        <%
        } else {
        %>
        <h1>No Records Available</h1>
        <%
            }
        %>
    </body>
</html>
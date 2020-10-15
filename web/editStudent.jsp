<%-- 
    Document   : editStudent
    Created on : Mar 12, 2020, 12:04:27 PM
    Author     : hp
--%>

<%@page import="com.sistec.student.StudentDto"%>
<%@page import="com.sistec.student.StudentDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <%
        String sid=request.getParameter("sid");
        if(sid!=null){
            int s=Integer.parseInt(sid);
            StudentDao dao=new StudentDao();
             StudentDto dto=dao.getStudent(s);
             if(dto!=null){
                 
             %>
             <form action="" method="post">
             <label>Name</label>
             <input type="text" name="name" value="<%=dto.getName()%>">
             <label>Email</label>
             <input>
             </form>
             <%  }else{
             %>
             <h1>Student Not Available in Database</h1>
             <%
                 }
                    }else{      

            %>
            <h1>please select Student</h1>
            <%
        }
        
        %>
    </body>
</html>

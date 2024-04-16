<%

if (session != null) {

    session.setAttribute("LogFlag", false);
}

response.sendRedirect("index.jsp");
%>

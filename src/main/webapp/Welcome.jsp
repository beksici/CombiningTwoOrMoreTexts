<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="FormStyle.css" rel="stylesheet" type="text/css">
<title>WordsApp</title>
</head>
<body>
<% int count =  (int)session.getAttribute("count");
session.setAttribute("count",count);
 %>
<script type="text/javascript">
function clearInput(target){
        target.value= "";
}</script>
<div>
<form action="ConnectServlet" method="get" id="Form">
 <% for(int i=0;i<count;i++){ %>
 <input type="text" name="<%=i%>" placeholder="enter sentences or word " value="<% if (session.getAttribute(String.valueOf(i))!=null){%> <%=session.getAttribute(String.valueOf(i)) %> <%} %> "  onfocus= "clearInput(this)"> <br>
 <%}%>
  <input type="submit" value="Connect">
 </form>
 <br>
 <form action="SaveDb" method="get">
 <input type="submit" value="Save">
 </form>
 </div>
 <h2>Result</h2>
 <textarea rows="10" cols="50"> <% if (session.getAttribute("sonuc1")!=null){%>  <%=session.getAttribute("sonuc1")%><%} out.println(); %> <% if (session.getAttribute("time") !=null){%> <%=session.getAttribute("time")%> <% out.print(" ms");} %> 
 </textarea>
</body>
</html>
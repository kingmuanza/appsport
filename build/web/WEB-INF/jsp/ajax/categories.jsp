<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<option>Catégorie</option>
<c:forEach items="${categories}" var="d" varStatus="loop">
<option value="${d.idcategorie}">${d.code} ${d.libelle}</option>
</c:forEach>
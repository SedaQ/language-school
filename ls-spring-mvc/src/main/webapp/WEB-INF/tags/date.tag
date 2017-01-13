<%@ tag body-content="empty" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="date" required="true" type="java.time.LocalDate" %>
<%@ attribute name="patternDate" required="false" type="java.lang.String" %>

<%@ attribute name="dateTime" required="true" type="java.time.LocalDateTime" %>
<%@ attribute name="patternDateTime" required="false" type="java.lang.String" %>

<c:if test="${empty pattern}">
    <c:set var="patternDate" value="MM/dd/yyyy"/>
</c:if>

<fmt:parseDate value="${date}" pattern="yyyy-MM-dd" var="parsedDate" type="date"/>
<fmt:formatDate value="${parsedDate}" type="date" pattern="${pattern}"/>



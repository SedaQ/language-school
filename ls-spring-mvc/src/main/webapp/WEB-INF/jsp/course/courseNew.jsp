<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Language School Course</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<%@ include file="../common/header.jsp"%>

          <form:form method="post" action="${pageContext.request.contextPath}/course/create"
                     modelAttribute="courseCreate" cssClass="form-horizontal">

                <div>
                  <form:label path="name" >Name</form:label>
                  <div class="col-sm-10">
                     <form:input path="name" />
                  </div>
               </div>
                <div>
                 <form:label path="language" >Language</form:label>
                 <div class="col-sm-10">
                        <form:input path="language" />
                 </div>
              </div>
               <div>
                 <form:label path="proficiencyLevel" >Proficiency level</form:label>
            <div class="col-sm-10">
                <form:select path="proficiencyLevel">
                    <c:forEach items="${proficiencylevels}" var="proflvl">
                        <form:option value="${proflvl}">${proflvl}</form:option>
                    </c:forEach>
                </form:select>
            </div>
</div>


                <button class="btn btn-primary" type="submit">Create course</button>
            </form:form>

	<%@ include file="../common/footer.jsp"%>
</body>
</html>

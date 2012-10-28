<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h2>Doctors</h2>

<table>
  <thead>
    <th>Name</th>
  </thead>
  <c:forEach var="doctor" items="${doctors}">
    <c:url var="doctorUrl" value="/doctors/${doctor.id}" />
    <tr>
      <td><a href="${fn:escapeXml(doctorUrl)}">${doctor.firstName} ${doctor.lastName}</a></td>
    </tr>
  </c:forEach>
</table>

<br />
<a href='<spring:url value="/doctors/search" htmlEscape="true"/>'>Search Doctors</a>
<br />
<a href="<spring:url value="/" htmlEscape="true" />">Home</a>

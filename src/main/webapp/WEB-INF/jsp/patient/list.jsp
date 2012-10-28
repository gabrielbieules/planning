<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h2>Patients</h2>

<table>
  <thead>
    <th>Name</th>
    <th>Telephone</th>
  </thead>
  <c:forEach var="patient" items="${patients}">
    <c:url var="patientUrl" value="/patients/${patient.id}" />
    <tr>
      <td>
          <a href="${fn:escapeXml(patientUrl)}">${patient.firstName} ${patient.lastName}</a>
      </td>
      <td>${patient.phone}</td>
    </tr>
  </c:forEach>
</table>

<a href='<spring:url value="/patients/new" htmlEscape="true"/>'>Add Patient</a>
<a href='<spring:url value="/patients/search" htmlEscape="true"/>'>Search Patient</a>
<a href="<spring:url value="/" htmlEscape="true" />">Home</a>

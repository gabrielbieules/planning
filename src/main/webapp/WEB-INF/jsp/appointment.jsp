<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<title>Hospital</title>
</head>

<body>

  <div id="main">

    <h2>Patient Information</h2>

    <c:url var="addUrl" value="/patients/${patient.id}/appointments/new" />
    <form:form modelAttribute="appointment" method="post" action="${addUrl}">
      <table>
        <tr>
          <th>Patient: ${patient.firstName} ${patient.lastName}</th>
        </tr>
        <tr>
          <th>Date (yyyy-mm-dd): <br /> <form:input path="date" size="10" maxlength="10" />
          </th>
        </tr>
        <tr>
          <th>Doctor: <br /> <form:select path="doctor" items="${doctorsList}" />
          </th>
        </tr>
        <tr>
          <td>
            <p class="submit">
              <input type="submit" value="Add appointment" />
            </p>
          </td>
        </tr>
      </table>
      <form:errors path="date" />
    </form:form>

    <b>Appointments:</b>
    <table width="333">
      <tr>
        <th>Date</th>
        <th>Doctor</th>
        <th>Manage</th>
      </tr>
      <c:forEach var="appointment" items="${patient.appointments}">
        <c:url var="deleteUrl" value="/patients/${patient.id}/appointments/${appointment.id}/delete" />
        <tr>
          <td>${appointment.date}</td>
          <td>${appointment.doctor.firstName} ${appointment.doctor.lastName}</td>
          <td><a href="${fn:escapeXml(deleteUrl)}">Delete</a></td>
        </tr>
      </c:forEach>
    </table>

    <br /> <a href='<spring:url value="/patients/search" />'>Search Patient</a> <br /> 
    <a href='<spring:url value="/doctors/search" />'>Search Doctor</a> <br /> 
    <a href="<spring:url value="/" htmlEscape="true" />">Home</a>
</body>

</html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<title>Hospital</title>
</head>

<body>

  <div id="main">

    <h2>Doctor Information</h2>

    <table>
      <tr>
        <th>Name</th>
        <td><b>${doctor.firstName} ${doctor.lastName} </b></td>
      </tr>
    </table>

    <br /> <b>Appointments:</b>
    <table width="333">
      <tr>
        <th>Date</th>
        <th>Patient</th>
      </tr>
      <c:forEach var="appointment" items="${doctor.appointments}">
        <tr>
          <td>${appointment.date}</td>
          <td>${appointment.patient.firstName} ${appointment.patient.lastName}</td>
        </tr>
      </c:forEach>
    </table>
  </div>

  <br />
  <a href='<spring:url value="/doctors/search" />'>Search Doctor</a>
  <br />
  <a href="<spring:url value="/" htmlEscape="true" />">Home</a>
</body>

</html>

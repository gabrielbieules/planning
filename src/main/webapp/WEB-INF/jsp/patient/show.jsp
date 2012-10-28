<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body>

  <div id="main">

    <h2>Patient Information</h2>

    <table>
      <tr>
        <th>Name</th>
        <td><b>${patient.firstName} ${patient.lastName} </b></td>
      </tr>
      <tr>
        <th>Telephone</th>
        <td>${patient.phone}</td>
      </tr>
    </table>

    <br /> <b>Appointments:</b>
    <table width="333">
      <tr>
        <th>Date</th>
        <th>Doctor</th>
      </tr>
      <c:forEach var="appointment" items="${patient.appointments}">
        <tr>
          <td>${appointment.date}</td>
          <td>${appointment.doctor.firstName} ${appointment.doctor.lastName}</td>
        </tr>
      </c:forEach>
    </table>
  </div>

  <br />
  <a href='<spring:url value="/patients/${patient.id}/appointments/new" htmlEscape="true"/>'>Manage Appointment</a>
  <br />
  <a href='<spring:url value="/patients/search" />'>Search Patient</a>
  <br />
  <a href="<spring:url value="/" htmlEscape="true" />">Home</a>
</body>

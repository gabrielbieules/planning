<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2>New Patient</h2>

<form:form modelAttribute="patient" method="post">
  <table>
    <tr>
      <th>First Name: <form:errors path="firstName" /> <br /> <form:input path="firstName" size="30"
          maxlength="80" />
      </th>
    </tr>
    <tr>
      <th>Last Name: <form:errors path="lastName" /> <br /> <form:input path="lastName" size="30" maxlength="80" />
      </th>
    </tr>
    <tr>
      <th>Telephone: <form:errors path="phone" /> <br /> <form:input path="phone" size="20" maxlength="20" />
      </th>
    </tr>
    <tr>
      <td>
        <p class="submit">
          <input type="submit" value="Add patient" />
        </p>
      </td>
    </tr>
  </table>
</form:form>

<br />
<a href='<spring:url value="/patients/search" />'>Search Patient</a>
<br />
<a href="<spring:url value="/" htmlEscape="true" />">Home</a>
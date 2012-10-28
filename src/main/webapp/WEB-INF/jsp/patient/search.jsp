<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2>Find patient</h2>

<form:form modelAttribute="patient" method="post">
  <table>
    <tr>
      <td>First name: <form:errors path="*" cssClass="errors" /> <form:input path="firstName" size="30"
          maxlength="50" />
      </td>
    </tr>
    <tr>
      <td>Last name: <form:errors path="*" cssClass="errors" /> <form:input path="lastName" size="30"
          maxlength="50" />
      </td>
    </tr>
    <tr>
      <td>
        <p class="submit">
          <input type="submit" value="Find" />
        </p>
      </td>
    </tr>
  </table>
</form:form>

<br />
<a href='<spring:url value="/patients/new" htmlEscape="true"/>'>Add Patient</a>
<a href="<spring:url value="/" htmlEscape="true" />">Home</a>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<title>Hospital planning</title>
</head>

<body>
  <h1>HOSPITAL</h1>
  <ul>
    <li><a href='<spring:url value="/patients/new" />'>Add Patient</a></li>
    <li><a href='<spring:url value="/patients/search" />'>Search Patient</a></li>
    <li><a href='<spring:url value="/doctors/search" />'>Search Doctor</a></li>
  </ul>

</body>
</html>

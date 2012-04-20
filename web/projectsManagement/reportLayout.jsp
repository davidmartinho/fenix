<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html xhtml="true">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=<%= net.sourceforge.fenixedu._development.PropertiesManager.DEFAULT_CHARSET %>" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/CSS/layout.css"  media="screen"  />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/CSS/general.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/CSS/color.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/CSS/print.css" media="print" />
	<link href="<%= request.getContextPath() + "/CSS/report.css" %>" rel="stylesheet" type="text/css" />
	<link href="<%= request.getContextPath() %>/CSS/report_print.css" rel="stylesheet" media="print" type="text/css" />
	<bean:define id="title" value="title.progectsManagement" />
	<logic:present name="infoCostCenter" scope="request">
		<bean:define id="title" value="title.institucionalProgectsManagement" />
	</logic:present>
	<title><bean:message key="dot.title" bundle="GLOBAL_RESOURCES" /> - <bean:message key="<%=title%>" /></title>
</head>

<body>
<%-- Layout component parameters : title, context, header, navGeral, navLocal, body, footer --%>

<!-- Context -->
<tiles:insert attribute="context" ignore="true" />
<!--End Context -->


<!-- Header -->
<div id="top">
	<h1 id="logo">
		<img alt="<bean:message key="institution.logo" bundle="IMAGE_RESOURCES" />" src="<bean:message key="dot.logo" bundle="GLOBAL_RESOURCES" arg0="<%= request.getContextPath() %>"/>"/>
	</h1>

	<%--
	<tiles:getAsString name="serviceName" />
	--%>
	
	<bean:define id="supportLink" type="java.lang.String">mailto:<bean:message key="suporte.mail" bundle="GLOBAL_RESOURCES"/></bean:define>
	<ul>
		<li style="display: none;"><a href="">Skip to Content</a> |</li>
		<li class="support"><a href="<%= supportLink %>">Suporte</a></li>
		<li class="logout"><a href="<%= request.getContextPath() %>/logoff.do">Logout</a></li>
	</ul>
	<p id="user">
		<tiles:insert page="/commons/personalInfoTitleBar.jsp" />
	</p>
</div>
<!-- End Header -->


<!-- NavGeral -->
<div id="navtop">
	<tiles:insert attribute="navGeral" />
</div>
<!-- End NavGeral -->


<!-- Container -->
<div id="container">

	<!-- NavLateral -->
	<div id="navlateral">
		<tiles:insert attribute="navLocal" ignore="true"/>
	</div>
	<!-- End NavLateral -->
	


	<!-- Content -->
	<div id="content">
	
	  	<tiles:insert attribute="body-context" ignore="true"/>
	  	<tiles:insert attribute="body" ignore="true"/>
	  	
	</div>
	<!-- End Content -->

	<!-- Footer -->
	<div id="footer">
		<tiles:insert attribute="footer" />
	</div>
	<!--End Footer -->

</div>
<!-- End Container -->

<script type="text/javascript">
	hideButtons();
</script>

</body>
</html:html>

<%@ page language="java" %>
<%@ page import="ServidorApresentacao.Action.sop.utils.SessionConstants" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<span class="error"><html:errors/></span>
<html:form action="/creditsManagement">
	<html:hidden property="page" value="1"/>
	<html:hidden property="method" value="processForm"/>
	<html:hidden property="teacherOID"/>
	<h2 class="infoop"><bean:write name="infoCreditsTeacher" property="infoTeacher.infoPerson.nome"/></h2>
	<table cellpadding="5" cellspacing="1">
		<tr>
			<td class="listClasses" colspan="2"><b><bean:message key="label.tfc.students.number"/></b></td>
			<td class="listClasses" style="text-align:left"><html:text property="tfcStudentsNumber" size="5"/></td>
		</tr>
		<tr>
			<td class="listClasses" rowspan="2"><b><bean:message key="label.additional.credits"/></b></td>
			<td class="listClasses"><b><bean:message key="label.credits"/></b></td>
			<td class="listClasses" style="text-align:left"><html:text property="additionalCredits" size="5"/></td>
		</tr>
		<tr>
			<td class="listClasses"><b><bean:message key="label.additional.credits.reason	"/></b></td>
			<td class="listClasses"><html:textarea property="additionalCreditsJustification" rows="2" cols="50"/></td>
		</tr>
	</table>	
	
	<html:submit styleClass="inputbutton">
		<bean:message key="button.save"/>
	</html:submit>
	<bean:define id="link">
		/executionCourseShiftsPercentageManager.do?method=show&amp;teacherOID=<bean:write name="infoCreditsTeacher" property="infoTeacher.idInternal"/>
	</bean:define>
	<tiles:insert definition="teacher-professor-ships">
		<tiles:put name="title" value="label.professorships"/>
		<tiles:put name="link" value="<%= link %>"/>
	</tiles:insert>	
</html:form>

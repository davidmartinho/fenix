<%--

    Copyright © 2002 Instituto Superior Técnico

    This file is part of FenixEdu Core.

    FenixEdu Core is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FenixEdu Core is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with FenixEdu Core.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html:xhtml />
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://fenix-ashes.ist.utl.pt/fenix-renderers" prefix="fr"%>

<h2><bean:message bundle="MANAGER_RESOURCES" key="label.payments.annulTransaction" /></h2>

<bean:define id="personId" name="annulAccountingTransactionBean" property="person.externalId" />
<bean:define id="eventId" name="annulAccountingTransactionBean" property="transaction.event.externalId" />

<logic:messagesPresent message="true">
	<ul class="nobullet list6">
		<html:messages id="messages" message="true" bundle="APPLICATION_RESOURCES">
			<li><span class="error0"><bean:write name="messages" /></span></li>
		</html:messages>
	</ul>
</logic:messagesPresent>

<fr:edit id="annulAccountingTransactionBean"
	name="annulAccountingTransactionBean"
	schema="AnnulAccountingTransactionBean.edit"
	action="<%="/paymentsManagement.do?method=annulTransaction&amp;personId=" + personId%>" >

	<fr:layout name="layout">
		<fr:property name="classes" value="tstyle2 thmiddle thright thlight mtop05" />
		<fr:property name="columnClasses" value=",,tdclear tderror1" />
		<fr:destination name="invalid" path="/paymentsManagement.do?method=prepareAnnulTransactionInvalid" />
		<fr:destination name="cancel" path="<%="/paymentsManagement.do?method=showPaymentsForEvent&eventId=" + eventId %>" />
	</fr:layout>
</fr:edit>

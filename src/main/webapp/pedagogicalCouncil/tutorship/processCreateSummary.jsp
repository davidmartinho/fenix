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
<%@ page language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html:xhtml/>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://fenix-ashes.ist.utl.pt/fenix-renderers" prefix="fr"%>

<h2><bean:message key="label.tutorshipSummary" bundle="APPLICATION_RESOURCES" />: <bean:write name="createSummaryBean" property="teacher.person.name" /></h2>
<h3>
<bean:message key="label.curricular.course.semester" bundle="APPLICATION_RESOURCES" /> 
<bean:write name="createSummaryBean" property="executionSemester.semester" /> -
<bean:write name="createSummaryBean" property="executionSemester.executionYear.year" />
</h3>
<h3>
<bean:message key="label.degree.name" bundle="APPLICATION_RESOURCES" />:
<bean:write name="createSummaryBean" property="degree.sigla" />
</h3>

<p class="mtop2 mbottom1 separator2"/>
	<b><bean:message key="label.tutorshipSummary.create" bundle="APPLICATION_RESOURCES"/></b>
</p>

<fr:form action="/tutorshipSummary.do">
	<html:hidden property="method" value="processCreateSummary" />
	<html:hidden property="confirm" value="true" />
	
	<!-- first data: name, course, year, indicators -->
	<table class="mtop15" width="98%" cellpadding="0" cellspacing="0">
		<tr>
			<td class="infoop" width="25"><span class="emphasis-box">1</span></td>
			<td class="infoop"><strong><bean:message key="label.tutorshipSummary.create.contacts" bundle="APPLICATION_RESOURCES"/></strong></td>
		</tr>
	</table>
	<br />

	<fr:view name="createSummaryBean" layout="tabular" schema="tutorship.tutorate.createSummary">
	<fr:layout>
		<fr:property name="classes" value="tstyle1 thlight mtop0 mbottom15 tdcenter"/>
		<fr:property name="columnClasses" value="aleft,acenter"/>
	</fr:layout>
	</fr:view>

	<!-- student participation -->
	<table class="mtop15" width="98%" cellpadding="0" cellspacing="0">
		<tr>
			<td class="infoop" width="25"><span class="emphasis-box">2</span></td>
			<td class="infoop"><strong><bean:message key="label.tutorshipSummary.create.studentParticipation" bundle="APPLICATION_RESOURCES"/></strong></td>
		</tr>
	</table>	
	
	<fr:view name="createSummaryBean" layout="tabular" property="tutorshipRelations" schema="tutorship.tutorate.createSummaryRelation">
		<fr:layout>
		    <fr:property name="classes" value="tstyle1 printborder tpadding1"/>
			<fr:property name="columnClasses" value="bgcolor3 aleft,acenter,acenter,acenter,acenter,acenter,acenter,acenter" />
			<fr:property name="headerClasses" value="acenter" />
		</fr:layout>
	</fr:view>
	
	<!-- program participation -->
	<table class="mtop15" width="98%" cellpadding="0" cellspacing="0">
		<tr>
			<td class="infoop" width="25"><span class="emphasis-box">3</span></td>
			<td class="infoop"><strong><bean:message key="label.tutorshipSummary.create.programParticipation" bundle="APPLICATION_RESOURCES"/></strong></td>
		</tr>
	</table>

	<h3>Principais problemas apresentados <u>pelos alunos</u></h3>
	
	<table class="tstyle1 printborder tpadding1">
		<tr>
			<td>
			<fr:view name="createSummaryBean" property="problemsR1" layout="boolean-icon" /></td>
			<td>Horários/Inscrições</td>
			<td><fr:view name="createSummaryBean" property="problemsR2" layout="boolean-icon" /></td>
			<td>Métodos de Estudo</td>
			<td><fr:view name="createSummaryBean" property="problemsR3" layout="boolean-icon" /></td>
			<td>Gestão de Tempo/Volume de Trabalho</td>
		</tr>
		<tr>
			<td><fr:view name="createSummaryBean" property="problemsR4" layout="boolean-icon" /></td>
			<td>Acesso a Informação (ex.:aspectos administrativos; ERASMUS; etc.)</td>
			<td><fr:view name="createSummaryBean" property="problemsR5" layout="boolean-icon" /></td>
			<td>Transição Ensino Secundário/Ensino Superior</td>
			<td><fr:view name="createSummaryBean" property="problemsR6" layout="boolean-icon" /></td>
			<td>Problemas Vocacionais</td>
		</tr>
		<tr>
			<td><fr:view name="createSummaryBean" property="problemsR7" layout="boolean-icon" /></td>
			<td>Relação Professor - Aluno</td>
			<td><fr:view name="createSummaryBean" property="problemsR8" layout="boolean-icon" /></td>
			<td>Desempenho Académico (ex.: taxas de aprovação)</td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><fr:view name="createSummaryBean" property="problemsR9" layout="boolean-icon" /></td>
			<td>Avaliação (ex.: metodologia, datas de exames; etc.)</td>
			<td><fr:view name="createSummaryBean" property="problemsR10" layout="boolean-icon" /></td>
			<td>Adaptação ao <%=net.sourceforge.fenixedu.domain.organizationalStructure.Unit.getInstitutionAcronym()%></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td colspan="6"><b>Outro:</b>
				<fr:view name="createSummaryBean" property="problemsOther">
					<fr:layout>
						<fr:property name="size" value="80" />
					</fr:layout>
				</fr:view>
			</td>
		</tr>
	</table>	

	
	<h3>Principais ganhos percepcionados <u>para os alunos</u></h3>
	
	<table class="tstyle1 printborder tpadding1">
		<tr>
			<td><fr:view name="createSummaryBean" property="gainsR1" layout="boolean-icon" /></td>
			<td>Maior responsabilização/autonomização do Aluno</td>
			<td><fr:view name="createSummaryBean" property="gainsR2" layout="boolean-icon" /></td>
			<td>Alteração dos métodos de estudo</td>
			<td><fr:view name="createSummaryBean" property="gainsR3" layout="boolean-icon" /></td>
			<td>Planeamento do semestre/Avaliação</td>
		</tr>
		<tr>
			<td><fr:view name="createSummaryBean" property="gainsR4" layout="boolean-icon" /></td>
			<td>Acompanhamento mais individualizado</td>
			<td><fr:view name="createSummaryBean" property="gainsR5" layout="boolean-icon" /></td>
			<td>Maior motivação para o curso</td>
			<td><fr:view name="createSummaryBean" property="gainsR6" layout="boolean-icon" /></td>
			<td>Melhor desempenho académico</td>
		</tr>
		<tr>
			<td><fr:view name="createSummaryBean" property="gainsR7" layout="boolean-icon" /></td>
			<td>Maior proximidade Professor-Aluno</td>
			<td><fr:view name="createSummaryBean" property="gainsR8" layout="boolean-icon" /></td>
			<td>Transição do Ensino Secundário para o Ensino Superior mais fácil</td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><fr:view name="createSummaryBean" property="gainsR9" layout="boolean-icon" /></td>
			<td>Melhor adaptação ao <%=net.sourceforge.fenixedu.domain.organizationalStructure.Unit.getInstitutionAcronym()%></td>
			<td><fr:view name="createSummaryBean" property="gainsR10" layout="boolean-icon" /></td>
			<td>Apoio na tomada de decisões/Resolução de problemas</td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td colspan="6"><b>Outro:</b>
				<fr:view name="createSummaryBean" property="gainsOther">
					<fr:layout>
						<fr:property name="size" value="80" />
					</fr:layout>
				</fr:view>
			</td>
		</tr>
	</table>

	<!-- conclusions -->
	<table class="mtop15" width="98%" cellpadding="0" cellspacing="0">
		<tr>
			<td class="infoop" width="25"><span class="emphasis-box">4</span></td>
			<td class="infoop"><strong><bean:message key="label.tutorshipSummary.create.conclusions" bundle="APPLICATION_RESOURCES"/></strong></td>
		</tr>
	</table>
	<br />		

	<table class="tstyle1 thlight mtop0 mbottom15 tdleft">
		<tr>
			<td>Apreciação Global do Programa:</td>
			<td><fr:view name="createSummaryBean" property="tutorshipSummaryProgramAssessment" type="net.sourceforge.fenixedu.domain.TutorshipSummaryProgramAssessment" /></td>
		</tr>
	</table>
	
	<table class="tstyle1 thlight mtop0 mbottom15 tdleft">
		<tr>
			<td>Dificuldades sentidas <u>pelo(a) Tutor(a)</u> no acompanhamento dos estudantes</td>
			<td>
				<fr:view name="createSummaryBean" property="difficulties">
					<fr:layout name="longText">
						<fr:property name="columns" value="60" />
						<fr:property name="rows" value="3" />
					</fr:layout>
				</fr:view>
			</td>
		</tr>
		<tr>
			<td>Ganhos sentidos <u>pelo(a) Tutor(a)</u> no acompanhamento dos estudantes</td>
			<td>
				<fr:view name="createSummaryBean" property="gains">
					<fr:layout name="longText">
						<fr:property name="columns" value="60" />
						<fr:property name="rows" value="3" />
					</fr:layout>
				</fr:view>
			</td>
		</tr>
	</table>

	<table class="tstyle1 thlight mtop0 mbottom15 tdleft">
		<tr>
			<td>Sugestões</td>
			<td>
				<fr:view name="createSummaryBean" property="suggestions">
					<fr:layout name="longText">
						<fr:property name="columns" value="60" />
						<fr:property name="rows" value="10" />
					</fr:layout>
				</fr:view>
			</td>
		</tr>
	</table>
	
	<fr:edit id="createSummaryBean" name="createSummaryBean" visible="false" />
	
	<html:submit onclick="this.form.method.value='createSummary';this.form.submit();"><bean:message key="button.back" bundle="APPLICATION_RESOURCES"/></html:submit>
	<html:submit><bean:message key="button.confirm" bundle="APPLICATION_RESOURCES"/></html:submit>
	
</fr:form>
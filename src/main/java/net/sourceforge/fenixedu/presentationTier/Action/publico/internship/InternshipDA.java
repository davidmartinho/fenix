/**
 * Copyright © 2002 Instituto Superior Técnico
 *
 * This file is part of FenixEdu Core.
 *
 * FenixEdu Core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu Core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu Core.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.sourceforge.fenixedu.presentationTier.Action.publico.internship;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.fenixedu.dataTransferObject.internship.InternshipCandidacyBean;
import net.sourceforge.fenixedu.domain.internship.DuplicateInternshipCandidacy;
import net.sourceforge.fenixedu.domain.internship.InternshipCandidacy;
import net.sourceforge.fenixedu.domain.internship.InternshipCandidacySession;
import net.sourceforge.fenixedu.presentationTier.Action.base.FenixDispatchAction;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pt.ist.fenixWebFramework.struts.annotations.Mapping;

@Mapping(path = "/internship", module = "publico")
public class InternshipDA extends FenixDispatchAction {

    private final ActionForward forward(HttpServletRequest request, String page) {
        request.setAttribute("actual$page", "/publico/internship/" + page + ".jsp");
        return new ActionForward("/internship/frame.jsp");
    }

    public ActionForward prepareCandidacy(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        InternshipCandidacySession session = InternshipCandidacySession.getMostRecentCandidacySession();
        if (session.getCandidacyInterval().containsNow()) {
            request.setAttribute("candidacy", new InternshipCandidacyBean(session));
        }
        return forward(request, "candidacy");
    }

    public ActionForward submitCandidacy(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        InternshipCandidacyBean bean = getRenderedObject();
        request.setAttribute("candidacy", bean);
        return forward(request, "candidacyRules");
    }

    public ActionForward confirmCandidacyRules(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        try {
            InternshipCandidacyBean bean = getRenderedObject();
            request.setAttribute("candidacy", bean);
            Integer candidacyNumber = InternshipCandidacy.create(bean);
            request.setAttribute("candidacyNumber", candidacyNumber);
            return forward(request, "candidacyFinal");
        } catch (DuplicateInternshipCandidacy e) {
            addActionMessage(request, "error.internationalrelations.internship.candidacy.duplicateStudentNumber", e.getNumber(),
                    e.getUniversity());
            return forward(request, "candidacyRules");
        }
    }

    public ActionForward backToCandidacy(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        InternshipCandidacyBean bean = getRenderedObject();
        request.setAttribute("candidacy", bean);
        return forward(request, "candidacy");
    }
}

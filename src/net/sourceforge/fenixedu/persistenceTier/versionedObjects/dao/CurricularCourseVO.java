package net.sourceforge.fenixedu.persistenceTier.versionedObjects.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.sourceforge.fenixedu.domain.CurricularCourse;
import net.sourceforge.fenixedu.domain.DegreeCurricularPlan;
import net.sourceforge.fenixedu.domain.ICurricularCourse;
import net.sourceforge.fenixedu.domain.ICurricularCourseScope;
import net.sourceforge.fenixedu.domain.IDegreeCurricularPlan;
import net.sourceforge.fenixedu.domain.IExecutionCourse;
import net.sourceforge.fenixedu.domain.degree.degreeCurricularPlan.DegreeCurricularPlanState;
import net.sourceforge.fenixedu.domain.degreeStructure.CurricularStage;
import net.sourceforge.fenixedu.persistenceTier.ExcepcaoPersistencia;
import net.sourceforge.fenixedu.persistenceTier.IPersistentCurricularCourse;
import net.sourceforge.fenixedu.persistenceTier.versionedObjects.VersionedObjectsBase;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

public class CurricularCourseVO extends VersionedObjectsBase implements IPersistentCurricularCourse {

    public List readByCurricularStage(CurricularStage curricularStage) throws ExcepcaoPersistencia {
        List<ICurricularCourse> result = new ArrayList<ICurricularCourse>();
        for (ICurricularCourse cc : (List<ICurricularCourse>) readAll(CurricularCourse.class)) {
            if (cc.getCurricularStage().equals(curricularStage)) {
                result.add(cc);
            }
        }
        return result;
    }
    
    public List readCurricularCoursesByDegreeCurricularPlan(final String name, final String degreeName,
            final String degreeSigla) throws ExcepcaoPersistencia {

        final Collection curricularCourses = readByCurricularStage(CurricularStage.OLD);

        return (List) CollectionUtils.select(curricularCourses, new Predicate() {
            public boolean evaluate(Object obj) {
                String nameEval = ((ICurricularCourse) obj).getDegreeCurricularPlan().getName();
                String degreeNameEval = ((ICurricularCourse) obj).getDegreeCurricularPlan().getDegree()
                        .getNome();
                String degreeSiglaEval = ((ICurricularCourse) obj).getDegreeCurricularPlan().getDegree()
                        .getSigla();

                return (nameEval.equals(name) && degreeNameEval.equals(degreeName) && degreeSiglaEval
                        .equals(degreeSigla));
            }
        });
    }

    public List readCurricularCoursesByDegreeCurricularPlanAndBasicAttribute(
            final Integer degreeCurricularPlanKey, final Boolean basic) throws ExcepcaoPersistencia {

        final Collection curricularCourses = readByCurricularStage(CurricularStage.OLD);

        return (List) CollectionUtils.select(curricularCourses, new Predicate() {
            public boolean evaluate(Object obj) {
                return ((ICurricularCourse) obj).getDegreeCurricularPlan().getIdInternal().equals(
                        degreeCurricularPlanKey)
                        && ((ICurricularCourse) obj).getBasic().equals(basic);
            }
        });
    }

    public List readbyCourseNameAndDegreeCurricularPlan(final String curricularCourseName,
            final Integer degreeCurricularPlanKey) throws ExcepcaoPersistencia {

        final Collection curricularCourses = readByCurricularStage(CurricularStage.OLD);

        return (List) CollectionUtils.select(curricularCourses, new Predicate() {
            public boolean evaluate(Object obj) {
                return ((ICurricularCourse) obj).getName().equals(curricularCourseName)
                        && ((ICurricularCourse) obj).getDegreeCurricularPlan().getIdInternal().equals(
                                degreeCurricularPlanKey);
            }
        });
    }

    public List readExecutedCurricularCoursesByDegreeAndExecutionYear(final Integer degreeKey,
            final Integer executionYearKey) throws ExcepcaoPersistencia {

        final Collection curricularCourses = readByCurricularStage(CurricularStage.OLD);

        return (List) CollectionUtils.select(curricularCourses, new Predicate() {
            public boolean evaluate(Object obj) {
                List executionCourses = ((ICurricularCourse) obj).getAssociatedExecutionCourses();

                if (((ICurricularCourse) obj).getDegreeCurricularPlan().getDegree().getIdInternal()
                        .equals(degreeKey)
                        && ((ICurricularCourse) obj).getDegreeCurricularPlan().getState().equals(
                                DegreeCurricularPlanState.ACTIVE)) {

                    for (Iterator iterator = executionCourses.iterator(); iterator.hasNext();) {
                        if (((IExecutionCourse) iterator.next()).getExecutionPeriod().getExecutionYear()
                                .getIdInternal().equals(executionYearKey))
                            return true;

                    }
                }
                return false;

            }
        });
    }

    public List readExecutedCurricularCoursesByDegreeAndYearAndExecutionYear(final Integer degreeKey,
            final Integer year, final Integer executionYearKey) throws ExcepcaoPersistencia {

        final Collection curricularCourses = readByCurricularStage(CurricularStage.OLD);

        return (List) CollectionUtils.select(curricularCourses, new Predicate() {
            public boolean evaluate(Object obj) {
                List executionCourses = ((ICurricularCourse) obj).getAssociatedExecutionCourses();
                List scopes = ((ICurricularCourse) obj).getScopes();

                if (((ICurricularCourse) obj).getDegreeCurricularPlan().getDegree().getIdInternal()
                        .equals(degreeKey)
                        && ((ICurricularCourse) obj).getDegreeCurricularPlan().getState().equals(
                                DegreeCurricularPlanState.ACTIVE)) {

                    for (Iterator iterator = executionCourses.iterator(); iterator.hasNext();) {
                        if (((IExecutionCourse) iterator.next()).getExecutionPeriod().getExecutionYear()
                                .getIdInternal().equals(executionYearKey))

                            for (Iterator iteratorScope = scopes.iterator(); iteratorScope.hasNext();) {
                                if (((ICurricularCourseScope) iterator.next()).getCurricularSemester()
                                        .getCurricularYear().getYear().equals(year))
                                    return true;
                            }
                    }
                }
                return false;
            }
        });
    }

    public ICurricularCourse readCurricularCourseByDegreeCurricularPlanAndNameAndCode(
            final Integer degreeCurricularPlanId, final String name, final String code)
            throws ExcepcaoPersistencia {

        final IDegreeCurricularPlan degreeCurricularPlan = (IDegreeCurricularPlan) readByOID(
                DegreeCurricularPlan.class, degreeCurricularPlanId);

        if (degreeCurricularPlan != null) {
            List<ICurricularCourse> curricularCourses = degreeCurricularPlan.getCurricularCourses();
            for (final ICurricularCourse curricularCourse : curricularCourses) {
                if (curricularCourse.getName().equals(name) && curricularCourse.getCode().equals(code)) {
                    return curricularCourse;
                }
            }
        }
        return null;
    }

    public List readbyCourseCodeAndDegreeCurricularPlan(final String curricularCourseCode,
            final Integer degreeCurricularPlanID) throws ExcepcaoPersistencia {

        final List<ICurricularCourse> result = new ArrayList<ICurricularCourse>();
        final IDegreeCurricularPlan degreeCurricularPlan = (IDegreeCurricularPlan) readByOID(
                DegreeCurricularPlan.class, degreeCurricularPlanID);

        if (degreeCurricularPlan != null) {
            List<ICurricularCourse> curricularCourses = degreeCurricularPlan.getCurricularCourses();
            for (final ICurricularCourse curricularCourse : curricularCourses) {
                if (curricularCourse.getCode().equals(curricularCourseCode)) {
                    result.add(curricularCourse);
                }
            }
        }
        return result;
    }
}

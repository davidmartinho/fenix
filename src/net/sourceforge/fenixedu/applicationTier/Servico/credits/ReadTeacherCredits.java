package net.sourceforge.fenixedu.applicationTier.Servico.credits;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.FenixServiceException;
import net.sourceforge.fenixedu.dataTransferObject.InfoExecutionPeriodWithInfoExecutionYear;
import net.sourceforge.fenixedu.dataTransferObject.InfoTeacherWithPerson;
import net.sourceforge.fenixedu.dataTransferObject.credits.InfoCredits;
import net.sourceforge.fenixedu.dataTransferObject.credits.InfoCreditsWithTeacherAndExecutionPeriod;
import net.sourceforge.fenixedu.domain.ExecutionPeriod;
import net.sourceforge.fenixedu.domain.Teacher;
import net.sourceforge.fenixedu.persistenceTier.ExcepcaoPersistencia;
import net.sourceforge.fenixedu.persistenceTier.IPersistentExecutionPeriod;
import net.sourceforge.fenixedu.persistenceTier.IPersistentTeacher;
import net.sourceforge.fenixedu.persistenceTier.ISuportePersistente;
import net.sourceforge.fenixedu.persistenceTier.PersistenceSupportFactory;
import pt.utl.ist.berserk.logic.serviceManager.IService;

/**
 * @author jpvl
 * 
 */
public class ReadTeacherCredits implements IService {

    public List run(Integer teacherOID) throws FenixServiceException, ExcepcaoPersistencia {
        ISuportePersistente sp = PersistenceSupportFactory.getDefaultPersistenceSupport();

        IPersistentTeacher teacherDAO = sp.getIPersistentTeacher();
        IPersistentExecutionPeriod executionPeriodDAO = sp.getIPersistentExecutionPeriod();

        Teacher teacher = (Teacher) teacherDAO.readByOID(Teacher.class, teacherOID);
        ExecutionPeriod startExecutionPeriod = (ExecutionPeriod) executionPeriodDAO.readByOID(
                ExecutionPeriod.class, new Integer(2));
        ExecutionPeriod executionPeriod = executionPeriodDAO.readActualExecutionPeriod();

        InfoTeacherWithPerson infoTeacher = new InfoTeacherWithPerson();
        infoTeacher.copyFromDomain(teacher);

        List list = new ArrayList();
        ExecutionPeriod executionPeriod2 = null;
        do {
            InfoExecutionPeriodWithInfoExecutionYear infoExecutionPeriod = new InfoExecutionPeriodWithInfoExecutionYear();
            infoExecutionPeriod.copyFromDomain(executionPeriod);

            InfoCredits infoCredits = teacher.getExecutionPeriodCredits(executionPeriod);
            InfoCreditsWithTeacherAndExecutionPeriod infoCreditsWrapper = new InfoCreditsWithTeacherAndExecutionPeriod(
                    infoCredits);
            infoCreditsWrapper.setInfoExecutionPeriod(infoExecutionPeriod);
            infoCreditsWrapper.setInfoTeacher(infoTeacher);
            list.add(infoCreditsWrapper);
            executionPeriod2 = executionPeriod;
            executionPeriod = executionPeriod.getPreviousExecutionPeriod();
        } while (!startExecutionPeriod.equals(executionPeriod2));
        return list;
    }

}
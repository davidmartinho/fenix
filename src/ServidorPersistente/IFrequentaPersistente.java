/*
 * IFrequentaPersistente.java
 *
 * Created on 20 de Outubro de 2002, 15:28
 */

package ServidorPersistente;

/**
 *
 * @author  tfc130
 */
import java.util.List;

import Dominio.IDisciplinaExecucao;
import Dominio.IEnrolment;
import Dominio.IFrequenta;
import Dominio.IStudent;
import ServidorPersistente.exceptions.ExistingPersistentException;

public interface IFrequentaPersistente extends IPersistentObject {
    public IFrequenta readByAlunoAndDisciplinaExecucao(IStudent aluno, IDisciplinaExecucao disciplinaExecucao) throws ExcepcaoPersistencia;
    public void lockWrite(IFrequenta frequenta) throws ExcepcaoPersistencia, ExistingPersistentException;
    public void delete(IFrequenta frequenta) throws ExcepcaoPersistencia;
    public void deleteAll() throws ExcepcaoPersistencia;
	public Integer countStudentsAttendingExecutionCourse(IDisciplinaExecucao executionCourse)
			throws ExcepcaoPersistencia;    
	public List readByStudentNumberInCurrentExecutionPeriod(Integer number)
			throws ExcepcaoPersistencia;
	// FIXME: Must read by Username, not by Student Number
	public List readByStudentNumber(Integer id) throws ExcepcaoPersistencia;
	public List readByExecutionCourse(IDisciplinaExecucao executionCourse) throws ExcepcaoPersistencia;

	public IFrequenta readByEnrolment(IEnrolment enrolment) throws ExcepcaoPersistencia;
}
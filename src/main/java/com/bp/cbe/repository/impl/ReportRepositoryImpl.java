package com.bp.cbe.repository.impl;

import com.bp.cbe.repository.ReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Repository
@AllArgsConstructor
public class ReportRepositoryImpl implements ReportRepository {
  private final EntityManager entityManager;

  @Override
  public List getReport(long tribeId) {
    StringBuilder sql = new StringBuilder();
    sql.append(
            "SELECT REP.ID_REPOSITORY, REP.NAME, TRI.NAME AS TRIBE, ORG.NAME AS ORGANIZATION, MET.COVERAGE, ")
        .append("MET.CODE_SMELLS, MET.BUGS, MET.VULNERABILITIES, MET.HOTSPOT, REP.STATE ")
        .append("FROM REPOSITORY REP ")
        .append("INNER JOIN TRIBE TRI ON TRI.ID_TRIBE = REP.ID_TRIBE ")
        .append("INNER JOIN ORGANIZATION ORG ON ORG.ID_ORGANIZATION = TRI.ID_ORGANIZATION ")
        .append("INNER JOIN METRICS MET ON MET.ID_REPOSITORY = REP.ID_REPOSITORY  ")
        .append("WHERE ")
        .append("TRI.ID_TRIBE = :tribeId AND ")
        .append("REP.STATE = 'E' AND ")
        .append("MET.COVERAGE > 0.75 AND ")
        .append("REP.CREATE_TIME BETWEEN :startDate AND :endDate ")
        .append("ORDER BY CREATE_TIME ");

    Query query = entityManager.createNativeQuery(sql.toString());
    query.setParameter("tribeId", tribeId);
    query.setParameter("startDate", getStartDate());
    query.setParameter("endDate", getEndDate());
    return query.getResultList();
  }

  private Date getStartDate() {
    return new GregorianCalendar(LocalDate.now().getYear(), Calendar.JANUARY, 01).getTime();
  }

  private Date getEndDate() {
    return new GregorianCalendar(LocalDate.now().getYear(), Calendar.DECEMBER, 31).getTime();
  }
}

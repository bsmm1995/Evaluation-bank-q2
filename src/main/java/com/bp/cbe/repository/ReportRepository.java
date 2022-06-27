package com.bp.cbe.repository;

import java.util.List;

public interface ReportRepository {
  List<Object[]> getReport(long tribeId);
}

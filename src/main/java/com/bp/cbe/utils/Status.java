package com.bp.cbe.utils;

import com.bp.cbe.domain.dto.webclient.RepositoryStatusDetail;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Utility to map entities and data
 *
 * @author: Bladimir Minga <bsminga@pichincha.com>
 * @version: 24/06/2022
 */
public class Status {
    private static final Map<Integer, String> statusMap = Map.of(604, "Verificado", 605, "En espera", 606, "Aprobado");

    private Status() {
        throw new IllegalStateException("Utility class");
    }

    public static String getState(String state) {
        switch (state) {
            case "A":
                return "Archived";
            case "D":
                return "Disable";
            case "E":
                return "Enable";
            default:
                return "None";
        }
    }

    public static String getVerificationState(long id, List<RepositoryStatusDetail> status) {
        AtomicReference<String> verificationStatus = new AtomicReference<>("");
        Optional<RepositoryStatusDetail> optional = status.stream().filter(e -> e.getId() == id).findFirst();
        optional.ifPresent(detail -> verificationStatus.set(statusMap.get((int) detail.getState())));
        return verificationStatus.get();
    }
}

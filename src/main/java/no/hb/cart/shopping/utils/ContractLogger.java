package no.hb.cart.shopping.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public class ContractLogger {
    private static Logger AUDITLOG = LoggerFactory.getLogger("AUDITLOGGER");

    private static final String requestLogFormat = "[method=%-6s] - %s: %s";
    private static final String responseLogFormat = "[method=%-6s][rescode=%s] - %s: %s";

    public static void logRequest(HttpMethod httpMethod, String method, Object message) {

        AUDITLOG.info(String.format(requestLogFormat, httpMethod, method, message.toString()));
    }
    public static void logRequest(HttpMethod httpMethod, String method, String message) {

        AUDITLOG.info(String.format(requestLogFormat, httpMethod, method, message));
    }

    public static void logRequestSecretively(HttpMethod httpMethod, String method) {
        String secretRequestLogFormat = "[method=%-6s] - %s";
        AUDITLOG.info(String.format(secretRequestLogFormat, httpMethod, method));
    }

    public static void logResponse(HttpMethod httpMethod, HttpStatus status, String method, Object message) {

        AUDITLOG.info(String.format(responseLogFormat, httpMethod, method, status, message.toString()));
    }
    public static void logResponse(HttpMethod httpMethod, HttpStatus status, String method, String message) {

        AUDITLOG.info(String.format(responseLogFormat, httpMethod, method, status, message));
    }

    public static void logResponseSecretively(HttpMethod httpMethod, HttpStatus status, String method) {
        String secretResponseLogFormat = "[method=%-6s][rescode=%s] - %s";
        AUDITLOG.info(String.format(secretResponseLogFormat, httpMethod, status, method));
    }


}

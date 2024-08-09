package az.ingress.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionConstants {
    ORDER_NOT_FOUND("ORDER_NOT_FOUND","Order not found"),
    UNEXPECTED_EXCEPTION("UNEXPECTED_EXCEPTION", "Unexpected exception occured");

    private String code;
    private String message;
}

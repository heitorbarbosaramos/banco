package com.banco.progresso.service.validacoes;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = CpfCnpjValidator.class)
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidaCpfCnpj {
    String message() default "Erro de validação do campo cpfOuCnpj";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

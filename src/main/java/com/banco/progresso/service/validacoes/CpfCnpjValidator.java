package com.banco.progresso.service.validacoes;

import com.banco.progresso.entidade.dto.ClienteDTO;
import com.banco.progresso.enums.TipoCliente;
import com.banco.progresso.exceptions.FieldMessage;
import com.banco.progresso.service.utils.ValidaDocumentos;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class CpfCnpjValidator implements ConstraintValidator<ValidaCpfCnpj, ClienteDTO> {

    @Override
    public void initialize(ValidaCpfCnpj constraintAnnotation) {
    }

    @Override
    public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext context) {

        List<FieldMessage> errors = new ArrayList<>();

        if(clienteDTO.getTipoCliente().equals(TipoCliente.PESSOAFISICA) && !ValidaDocumentos.validaCPF(clienteDTO.getCpfOuCnpj())){
            errors.add(new FieldMessage("cpfOuCnpj", "CPF invalido"));
        }

        if(clienteDTO.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA) && !ValidaDocumentos.validaCNPJ(clienteDTO.getCpfOuCnpj())){
            errors.add(new FieldMessage("cpfOuCnpj", "CNPJ invalido"));
        }

        for (FieldMessage e : errors) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return errors.isEmpty();
    }
}

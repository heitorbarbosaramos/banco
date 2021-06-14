package com.banco.progresso.entidade.dto;

import com.banco.progresso.entidade.Endereco;
import com.banco.progresso.enums.TipoCliente;
import com.banco.progresso.service.validacoes.ValidaCpfCnpj;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Data                   //para gerar os getteres e seteres
@Builder                //Builder vai nos prover uma maneira de criar objetos sem precisarmos de construtores e sem métodos setters em nossas classes
@AllArgsConstructor     //insere os construtores automaticamente
@NoArgsConstructor      //insere os construtores automaticamente
@ValidaCpfCnpj
public class ClienteDTO {

    private Long id;

    @NotEmpty(message = "campo nome completo é preenchimento obrigatorio")
    @Size(min = 5, max = 100, message = "campo nome completo deve conter de 5 á 100 caracteres")
    private String nomeCompleto;
    @NotEmpty(message = "campo nome cnpj ou cpf é obrigatorio")
    @Column(unique = true)
    private String cpfOuCnpj;
    @NotEmpty(message = "campo tipo cliente é obrigatorio")
    private String tipoCliente;
    @NotBlank(message = "campo campo telefone é obrigatório")
    private String telefone;
    private Date dataCriacao;
    private Date dataVenceTaxa;

    @NotNull(message = "cliente deve conter um endereço")
    @OneToOne(fetch = FetchType.EAGER)
    private Endereco endereco;

}

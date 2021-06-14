package com.banco.progresso.entidade;

import com.banco.progresso.enums.TipoCliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data                   //para gerar os getteres e seteres
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "campo nome completo é preenchimento obrigatorio")
    @Size(min = 5, max = 100, message = "campo nome completo deve conter de 5 á 100 caracteres")
    private String nomeCompleto;
    @NotBlank(message = "campo nome cnpj ou cpf é obrigatorio")
    @Column(unique=true)
    private String cpfOuCnpj;
    @NotNull(message = "campo tipo cliente é obrigatorio")
    private String tipoCliente;
    @NotBlank(message = "campo campo telefone é obrigatório")
    private String telefone;
    private Date dataCriacao;
    private Date dataVenceTaxa;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private Set<ContaCliente> contas = new LinkedHashSet<>();

    @NotNull(message = "cliente deve conter um endereço")
    @OneToOne(fetch = FetchType.EAGER)
    private Endereco endereco;
}
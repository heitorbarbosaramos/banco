package com.banco.progresso.repository;

import com.banco.progresso.entidade.ContaCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaClienteRepository extends JpaRepository<ContaCliente, Long> {

    ContaCliente findByNumeroConta(Integer numeroConta);
}

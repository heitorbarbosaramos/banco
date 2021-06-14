package com.banco.progresso.repository;

import com.banco.progresso.entidade.Extrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExtratoRepository extends JpaRepository<Extrato, Long> {

    @Query("select e from Extrato e where e.contaCliente.numeroConta = :numeroConta and e.dataOperacao between :dataInicio and :dataFim")
    List<Extrato> findAccountTimeCourse(@Param(value = "numeroConta") Integer numeroConta, @Param(value = "dataInicio") Date dataInicio, @Param(value = "dataFim") Date dataFim);
}

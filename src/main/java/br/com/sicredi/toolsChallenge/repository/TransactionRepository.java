package br.com.sicredi.toolsChallenge.repository;

import br.com.sicredi.toolsChallenge.model.response.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransacaoEntity, String> {
}

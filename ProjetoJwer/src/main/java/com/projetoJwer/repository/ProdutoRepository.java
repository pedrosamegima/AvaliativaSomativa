package com.projetoJwer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoJwer.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}

package com.projetoJwer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoJwer.entities.Produto;
import com.projetoJwer.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name= "Produto", description = "API REST DE GERENCIAMENTO  DE Produto")
@RestController
@RequestMapping("/produto")
public class ProdutoController {
	private final ProdutoService produtoService;
	
	@Autowired
	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	@GetMapping("/{id}")
	@Operation(summary = "Localiza pedido por ID")
	public ResponseEntity<Produto> buscaPedidoControlId(@PathVariable Long id){
		Produto produto = produtoService.buscaProdutoId(id);
		if(produto !=null) {
			return ResponseEntity.ok(produto);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/")
	@Operation(summary = "Apresenta todos os pedidos")
	public ResponseEntity<List<Produto>> buscaTodosProdutosControl(){
		List<Produto> Produto = produtoService.buscaTodosProdutos();
		return ResponseEntity.ok(Produto);
	}
	@PostMapping("/")
	@Operation(summary = "Cadastra um pedido")
	public ResponseEntity<Produto> salvaProdutosControl(@RequestBody @Valid Produto produto){
		Produto salvaProduto = produtoService.salvaPrduto(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaProduto);
}
	@PutMapping("/{id}")
	@Operation(summary= "Altera pedido")
	public ResponseEntity<Produto> alteraProdutoControl(@PathVariable Long id, @RequestBody @Valid Produto produto){
		Produto alteraProduto = produtoService.alterarProduto(id, produto);
		if(alteraProduto !=null) {
			return ResponseEntity.ok(produto);
		}
		else {
			return ResponseEntity.notFound().build();
		}
		
	}
	@DeleteMapping("/{id}")
	@Operation (summary = "Deleta pedido")
	public ResponseEntity<Produto> apagaProdutoControl(@PathVariable Long id){
		boolean apagar = produtoService.apagarProduto(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}

	

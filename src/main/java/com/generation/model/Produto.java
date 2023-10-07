package com.generation.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

	@Entity
	@Table(name = "tb_games")
	public class Produto {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;

	    @NotBlank(message = "O nome do produto é obrigatório")
	    @Size(min = 5, max = 100, message = "O nome do produto deve ter entre 5 a 100 caracteres")
	    private String nome;

	    @NotBlank(message = "O atributo descrição é obrigatório")
	    @Size(min = 10, max = 1000, message = "Apresente breve descrição entre 10 e 1000 caracteres")
	    private String descricao;

	    @NotBlank()
	    @Column(precision = 10, scale = 2)
	    @Positive(message = "O preço deve ser maior que zero")
	    private BigDecimal preco;
	    @Positive
	    private int quantidade;

	    @UpdateTimestamp
	    private LocalDate data;

	    @ManyToOne
	    @JoinColumn(name = "categoria")
	    private Categoria categoria;

	    public long getId() {
	        return id;
	    }

	    public void setId(long id) {
	        this.id = id;
	    }

	    public String getNome() {
	        return nome;
	    }

	    public void setNome(String nome) {
	        this.nome = nome;
	    }

	    public String getDescricao() {
	        return descricao;
	    }

	    public void setDescricao(String descricao) {
	        this.descricao = descricao;
	    }

	    public BigDecimal getPreco() {
	        return preco;
	    }

	    public void setPreco(BigDecimal preco) {
	        this.preco = preco;
	    }

	    public int getQuantidade() {
	        return quantidade;
	    }

	    public void setQuantidade(int quantidade) {
	        this.quantidade = quantidade;
	    }

	    public LocalDate getData() {
	        return data;
	    }

	    public void setData(LocalDate data) {
	        this.data = data;
	    }

	    public Categoria getCategoria() {
	        return categoria;
	    }

	    public void setCategoria(Categoria categoria) {
	        this.categoria = categoria;
	    }
	}



package br.com.ccs.mudi.dto;

import javax.validation.constraints.NotBlank;

import br.com.ccs.mudi.model.Pedido;
import br.com.ccs.mudi.model.StatusPedido;

public class RequisicaoDTO {

	@NotBlank(message = "Informe o nome do Produto.")
	private String nomeProduto;

	@NotBlank(message = "Informe a Url do Produto.")
	private String urlProduto;

	@NotBlank(message = "Informe a Url da imagem do Produto.")
	private String urlImagem;

	private String descricao;

	private StatusPedido statusPedido;

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getUrlProduto() {
		return urlProduto;
	}

	public void setUrlProduto(String urlProduto) {
		this.urlProduto = urlProduto;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusPedido getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}

	public Pedido toPedido() {

		Pedido p = new Pedido();

		p.setDescricaoPedido(descricao);
		p.setNomeProduto(nomeProduto);
		p.setUrlImagem(urlImagem);
		p.setUrlProduto(urlProduto);

		if (statusPedido == null) {
			p.setStatusPedido(StatusPedido.AGUARDANDO);
		}

		return p;
	}

}

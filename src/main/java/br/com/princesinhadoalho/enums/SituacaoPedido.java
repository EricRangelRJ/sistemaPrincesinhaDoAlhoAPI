package br.com.princesinhadoalho.enums;

import br.com.princesinhadoalho.exceptions.BadRequestException;

public enum SituacaoPedido {

	AGUARDANDO_PAGAMENTO(1),
	PAGO(2),
	CANCELADO(3);
	
	private int codigo;
	
	private SituacaoPedido(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public static SituacaoPedido valueOf(int codigo) {
		for(SituacaoPedido result : SituacaoPedido.values()) {
			if(result.getCodigo() == codigo) {
				return result;
			}
		}
		throw new BadRequestException("Código inválido");
	}
}

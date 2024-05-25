package com.compass.application.controllers.checkout;

import static com.compass.utils.LeitorDeDados.imprimirMap;

import java.util.ArrayList;

import org.hibernate.mapping.List;

import com.compass.domain.Estoque;
import com.compass.domain.ItemPedido;
import com.compass.domain.Pedido;
import com.compass.services.EstoqueService;
import com.compass.services.ItemPedidoService;
import com.compass.services.PedidoService;
import com.compass.utils.LeitorDeDados;


public class CheckoutController {

    private PedidoService service = new PedidoService();
    private ItemPedidoService service2 = new ItemPedidoService();
    private EstoqueService serviceEstoque = new EstoqueService();
    


    public Pedido listarPedidosPendentes() {
        Pedido pedido = LeitorDeDados.selecionarItem("Selecione um pedido", "Pedido", service.findAll());
        return pedido;
    }

    public java.util.List<ItemPedido> listarItensPedido(int pedidoId) {
        java.util.Map<Integer, ItemPedido> list = service2.findByPedidoId(pedidoId);
        imprimirMap(list);
        return new ArrayList<>(list.values());
    }

    public void abaterEstoque(java.util.List<ItemPedido> lista, Pedido pedido) {               
        for (ItemPedido itemPedido : lista) {
            Estoque estoque = serviceEstoque.findByProdutoId(itemPedido.getProduto().getId());
            int qtde = estoque.getQuantidade()-itemPedido.getQuantidade();
            estoque.setQuantidade(qtde);
            serviceEstoque.save(estoque);

        }
        pedido.setAceite(true);
        service.save(pedido);
    }







    public void motivoRecusa(String motivo) {           // se nao aceito
        // incluir motivo em algum lugar?
    }








    
        


    //const listaFiltrada         // copiar a lista ja filtrada

    // metodo para listar pedido com id impressa - (aceite false)
 

    // metodo para selecionar o pedido - escolher pelo id
    // metodo para listar os itens do pedido selecionado - mostrar o nome do abrigo (filtro pelo id do pedido)
    // metodo para aceitar o pedido ou nao

    // se aceito:
				// metodo para abater do estoque do CD
				// metodo para colocar true na coluna de aceite
				// metodo para armazenar a qtde de itens enviados
	
	// se nao aceito:
				// informar motivo
}

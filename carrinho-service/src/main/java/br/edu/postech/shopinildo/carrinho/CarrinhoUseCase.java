package br.edu.postech.shopinildo.carrinho;

import java.util.List;
import br.edu.postech.shopinildo.carrinho.dto.CarrinhoResponse;
import br.edu.postech.shopinildo.carrinho.integration.estoque.EstoqueClient;
import br.edu.postech.shopinildo.carrinho.integration.estoque.ItemAvailableDTO;
import br.edu.postech.shopinildo.carrinho.integration.item.ItemClient;
import br.edu.postech.shopinildo.carrinho.integration.item.ItemResponse;
import br.edu.postech.shopinildo.carrinho.dto.ItemDTO;

public class CarrinhoUseCase {

    public static CarrinhoResponse returnCompleteCart(Carrinho carrinho, ItemClient itemClient, EstoqueClient estoqueClient) {
        List<String> itemIds = carrinho.getItems().keySet().stream().toList();
        List<ItemAvailableDTO> availableItems = estoqueClient.available(itemIds).availability();
        List<ItemResponse> items = itemClient.findByIds(itemIds);

        List<ItemDTO> entries = itemIds.stream()
                .map(itemId -> {
                    ItemResponse itemResponse = items.stream().filter(i -> i.id().equals(itemId)).findFirst().get();
                    ItemAvailableDTO estoqueResponse = availableItems.stream().filter(i -> i.itemId().equals(itemId)).findFirst().get();
                    return new ItemDTO(itemId, carrinho.getItemQuantity(itemId), estoqueResponse, itemResponse);
                })
                .toList();

        return new CarrinhoResponse(entries);
    }
}

package br.edu.postech.shopinildo.pedido.integration.carrinho;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.edu.postech.shopinildo.pedido.model.Item;

public class CarrinhoResponse {
    public BigDecimal total;
    public List<Item> items = new ArrayList<>();
}

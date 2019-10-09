package com.vinicius.springboot.mc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vinicius.springboot.mc.model.Cliente;
import com.vinicius.springboot.mc.model.ItemPedido;
import com.vinicius.springboot.mc.model.PagamentoComBoleto;
import com.vinicius.springboot.mc.model.Pedido;
import com.vinicius.springboot.mc.model.enums.SituacaoPagamento;
import com.vinicius.springboot.mc.repositories.ItemPedidoRepository;
import com.vinicius.springboot.mc.repositories.PagamentoRepository;
import com.vinicius.springboot.mc.repositories.PedidoRepository;
import com.vinicius.springboot.mc.security.UserSpringSecurity;
import com.vinicius.springboot.mc.services.exception.AuthorizationException;
import com.vinicius.springboot.mc.services.exception.ObjectNotFoundException;

/**
 * Service responsável por nossas operações de CRUD.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	
	/**
	 * Metodo para buscar pelo id do pedido.
	 * @param id - Integer - id do pedido.
	 * @return Pedido.
	 */		
	public Pedido buscar( Integer id )  {
		
		Optional<Pedido> pedidoObj = pedidoRepository.findById(id);
		return pedidoObj.orElseThrow(() -> new ObjectNotFoundException (
				"Pedido não encontrado! Identificador: " + id + " Tipo do objeto: " + Pedido.class.getName()));
	}
	
	/**
	 * Metodo para inserir um pedido.
	 * @param id - Integer - id do pedido.
	 * @return obj.
	 */
	public Pedido insert(Pedido obj) {
		
		obj.setIdPedido(null);
		
		obj.setHorarioPedido(new Date());
		
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		
		obj.getPagamento().setSituacaoPagamento(SituacaoPagamento.PAGAMENTO_APROVADO);
		
		obj.getPagamento().setPedido(obj);
		
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			
			PagamentoComBoleto pagamentoComBoleto = ( PagamentoComBoleto ) obj.getPagamento();
			
			boletoService.preencherPagamentoComBoleto(pagamentoComBoleto, obj.getHorarioPedido());
		}
		
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for (ItemPedido ip : obj.getItems()) {
			
			ip.setDesconto(0.0);
			
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			
			ip.setPreco(ip.getProduto().getPreco());
			
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItems());
		
		emailService.sendOrderConfirmationEmail(obj);
		
		return obj;
	}
	
	/**
	 * Metodo para paginação.
	 * @param page - Integwe - quantidade de paginas por paginação.
	 * @param linesPerPage - Integer - quantidade de linhas por pagina.
	 * @param orderBy - String - inidica a ordenaçãop (ordenado por).
	 * @param direction - String - indica a direção (ascendente ou descendente).
	 * @return pageRequest.
	 */
	public Page<Pedido> findPage( Integer page, Integer linesPerPage, String orderBy, String direction ) {
		
		UserSpringSecurity user = UserService.getUserLogado();
		if (user == null) {
			throw new AuthorizationException("Acesso Negado!");
		}
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy); 
		
		Cliente cliente = clienteService.find(user.getId());
		
		return pedidoRepository.findByCliente(cliente, pageRequest);
		
	}
}

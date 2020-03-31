package com.vinicius.springboot.mc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vinicius.springboot.mc.model.Categoria;
import com.vinicius.springboot.mc.model.Cidade;
import com.vinicius.springboot.mc.model.Cliente;
import com.vinicius.springboot.mc.model.Endereco;
import com.vinicius.springboot.mc.model.Estado;
import com.vinicius.springboot.mc.model.ItemPedido;
import com.vinicius.springboot.mc.model.Pagamento;
import com.vinicius.springboot.mc.model.PagamentoComBoleto;
import com.vinicius.springboot.mc.model.PagamentoComCartao;
import com.vinicius.springboot.mc.model.Pedido;
import com.vinicius.springboot.mc.model.Produto;
import com.vinicius.springboot.mc.model.enums.Perfil;
import com.vinicius.springboot.mc.model.enums.SituacaoPagamento;
import com.vinicius.springboot.mc.model.enums.SituacaoProduto;
import com.vinicius.springboot.mc.model.enums.TipoCliente;
import com.vinicius.springboot.mc.repositories.CategoriaRepository;
import com.vinicius.springboot.mc.repositories.CidadeRepository;
import com.vinicius.springboot.mc.repositories.ClienteRepository;
import com.vinicius.springboot.mc.repositories.EnderecoRepository;
import com.vinicius.springboot.mc.repositories.EstadoRepository;
import com.vinicius.springboot.mc.repositories.ItemPedidoRepository;
import com.vinicius.springboot.mc.repositories.PagamentoRepository;
import com.vinicius.springboot.mc.repositories.PedidoRepository;
import com.vinicius.springboot.mc.repositories.ProdutoRepository;

/**
 * Service responsável por instanciar nossas classes de modelo.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@Service
public class DatabaseService {
	
	@Autowired
	private BCryptPasswordEncoder encoderPassword;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	/**
	 * Metodo que instancia nossos modelos.
	 * @throws ParseException - Exceção para SimpleDateFormat.
	 */
	public void instantiateTestDatabase() throws ParseException {
		
		Categoria categoria1 = new Categoria(null, "Informática");
		Categoria categoria2 = new Categoria(null, "Escritório");
		Categoria categoria3 = new Categoria(null, "Roupas e Cassacos");
		Categoria categoria4 = new Categoria(null, "Games, Consoles e Acessorios");
		Categoria categoria5 = new Categoria(null, "Decoração");
		Categoria categoria6 = new Categoria(null, "Eletronicos");
		Categoria categoria7 = new Categoria(null, "Calçados");
		
		Produto produto1 = new Produto(null, "Computador", 2000.00, 30.05, "Cor Preta", SituacaoProduto.DISPONIVEL);
		Produto produto2 = new Produto(null, "Impressora", 800.00, 25.10, "Cor Prata", SituacaoProduto.NAO_DISPONIVEL);
		Produto produto3 = new Produto(null, "Mouse wireless", 45.00, 15.00, "Cor Preta", SituacaoProduto.DISPONIVEL);
		Produto produto4 = new Produto(null, "Mesa Escritório", 450.00, 90.00, "Cor Preta", SituacaoProduto.DISPONIVEL);
		Produto produto5 = new Produto(null, "Jacketa de couro", 100.00, 5.00, "Cor Marrom", SituacaoProduto.DISPONIVEL);
		Produto produto6 = new Produto(null, "Playstation 4 Slim 1TB", 1250.00, 100.00, "Cor Preta", SituacaoProduto.DISPONIVEL);
		Produto produto7 = new Produto(null, "Game The Last of Us Part II", 170.00, 15.00, "Cor Azul", SituacaoProduto.NAO_DISPONIVEL);
		Produto produto8 = new Produto(null, "Luminária", 45.00, 40.00, "Cor Verde", SituacaoProduto.DISPONIVEL);
		Produto produto9 = new Produto(null, "Samsung Galaxy S8 128GB", 2300.00, 30.00, "Cor Prata", SituacaoProduto.DISPONIVEL);
		Produto produto10 = new Produto(null, "Calça Jeans Sport", 45.00, 15.00, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto11 = new Produto(null, "Samsung Galaxy S10 128GB", 2500.00, 30.00, "Cor Azul", SituacaoProduto.DISPONIVEL);
		
		// Jogos Playstation 4
		Produto produto12 = new Produto(null, "9 Monkeys of Shaolin (Pré-venda) - PS4", 129.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto13 = new Produto(null, "Agents Of Mayhem Day One Edition - Ps4", 64.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto14 = new Produto(null, "Agony - Ps4", 99.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto15 = new Produto(null, "Air Conflicts Double Pack - Ps4", 97.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto16 = new Produto(null, "Airheart Tales of Broken Wings - PS4", 319.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto17 = new Produto(null, "Anthem - PS4", 125.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto18 = new Produto(null, "Assassins Creed Chronicles - Ps4", 89.00, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto19= new Produto(null, "Assassins Creed IV Black Flag - Ps4", 89.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto20 = new Produto(null, "Assassins Creed Rogue Remastered - Ps4", 99.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto21 = new Produto(null, "Assassins Creed Syndicate - Ps4", 94.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto22 = new Produto(null, "Assassins Creed Unity: Limited Edition - Ps4", 59.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto23 = new Produto(null, "Attack On Titan 2 Final Battle - Ps4", 249.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto24 = new Produto(null, "Axiom Verge - Ps4", 94.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto25 = new Produto(null, "Battlefield 1 - Ps4", 74.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto26 = new Produto(null, "Battlefield 1 Revolution (Europeu) - Ps4", 89.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto27 = new Produto(null, "Battlefield 1 Revolution - Ps4", 94.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto28 = new Produto(null, "Battlefield V - Ps4", 139.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto29 = new Produto(null, "Black Mirror - Ps4", 84.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto30 = new Produto(null, "Call Of Duty: Infinite Warfare Legacy Edition - Ps4", 74.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto31 = new Produto(null, "Call of Duty: Modern Warfare - Ps4", 229.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto32 = new Produto(null, "Dark Souls III The Fire Fades Edition - Ps4", 99.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto33 = new Produto(null, "Dark Souls Ii: Scholar Of The First Sin - Ps4", 97.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto34 = new Produto(null, "Dead By Daylight Special Edition - Ps4", 102.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto35 = new Produto(null, "Dead Island 2 (Pré-venda) - Ps4", 219.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto36 = new Produto(null, "Death Stranding (Pré-venda) - PS4", 234.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto37 = new Produto(null, "Detroit Become Human - Ps4", 94.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto38 = new Produto(null, "Devil May Cry Hd Collection - Ps4", 102.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto39 = new Produto(null, "Doom - Ps4", 99.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto40 = new Produto(null, "Doom Eternal (Pré-venda) - PS4", 229.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto41 = new Produto(null, "Dynasty Warriors 9 - Ps4", 209.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto42 = new Produto(null, "Exile's End - Ps4", 239.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto43 = new Produto(null, "Gundam Versus - Ps4", 79.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto44 = new Produto(null, "Hellblade Senua's Sacrifice - Ps4", 149.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto45 = new Produto(null, "Hitman Definitive Edition - Ps4", 99.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto46 = new Produto(null, "Hunting Simulator - Ps4", 129.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto47 = new Produto(null, "Jurassic World Evolution - Ps4", 287.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto48 = new Produto(null, "Immortal Unchained - Ps4", 229.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto49 = new Produto(null, "Just Cause 3 - Ps4", 94.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto50 = new Produto(null, "Just Cause 4 - Ps4", 99.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		Produto produto51 = new Produto(null, "Left Alive - Ps4", 58.99, 05.10, "Cor Azul", SituacaoProduto.DISPONIVEL);
		
		categoria1.getProdutos().addAll(Arrays.asList(produto1, produto3));
		categoria2.getProdutos().addAll(Arrays.asList(produto2, produto4));
		categoria3.getProdutos().addAll(Arrays.asList(produto5));
		categoria4.getProdutos().addAll(Arrays.asList(produto6, produto7, produto12, produto13, produto14, produto15, produto16, produto17, produto18, produto19, produto20, produto21, produto22,
				produto23, produto24, produto25, produto26, produto27, produto28, produto29, produto30, produto31, produto32, produto33, produto34, produto35, produto36, produto37, produto38, produto39,
				produto40, produto41, produto42, produto43, produto44, produto45, produto46, produto47, produto48, produto49, produto50, produto51));
		categoria5.getProdutos().addAll(Arrays.asList(produto8));
		categoria6.getProdutos().addAll(Arrays.asList(produto9, produto11));
		categoria7.getProdutos().addAll(Arrays.asList(produto10));
		
		
		produto1.getCategorias().addAll(Arrays.asList(categoria1));
		produto2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
		produto3.getCategorias().addAll(Arrays.asList(categoria1));
		produto4.getCategorias().addAll(Arrays.asList(categoria2));
		produto5.getCategorias().addAll(Arrays.asList(categoria3));
		produto6.getCategorias().addAll(Arrays.asList(categoria4));
		produto7.getCategorias().addAll(Arrays.asList(categoria4));
		produto8.getCategorias().addAll(Arrays.asList(categoria5));
		produto9.getCategorias().addAll(Arrays.asList(categoria6));
		produto10.getCategorias().addAll(Arrays.asList(categoria7));
		produto11.getCategorias().addAll(Arrays.asList(categoria6));
		
		produto12.getCategorias().addAll(Arrays.asList(categoria4));
		produto13.getCategorias().addAll(Arrays.asList(categoria4));
		produto14.getCategorias().addAll(Arrays.asList(categoria4));
		produto15.getCategorias().addAll(Arrays.asList(categoria4));
		produto16.getCategorias().addAll(Arrays.asList(categoria4));
		produto17.getCategorias().addAll(Arrays.asList(categoria4));
		produto18.getCategorias().addAll(Arrays.asList(categoria4));
		produto19.getCategorias().addAll(Arrays.asList(categoria4));
		produto20.getCategorias().addAll(Arrays.asList(categoria4));
		produto21.getCategorias().addAll(Arrays.asList(categoria4));
		produto22.getCategorias().addAll(Arrays.asList(categoria4));
		produto23.getCategorias().addAll(Arrays.asList(categoria4));
		produto24.getCategorias().addAll(Arrays.asList(categoria4));
		produto25.getCategorias().addAll(Arrays.asList(categoria4));
		produto26.getCategorias().addAll(Arrays.asList(categoria4));
		produto27.getCategorias().addAll(Arrays.asList(categoria4));
		produto28.getCategorias().addAll(Arrays.asList(categoria4));
		produto29.getCategorias().addAll(Arrays.asList(categoria4));
		produto30.getCategorias().addAll(Arrays.asList(categoria4));
		produto31.getCategorias().addAll(Arrays.asList(categoria4));
		produto32.getCategorias().addAll(Arrays.asList(categoria4));
		produto33.getCategorias().addAll(Arrays.asList(categoria4));
		produto34.getCategorias().addAll(Arrays.asList(categoria4));
		produto35.getCategorias().addAll(Arrays.asList(categoria4));
		produto36.getCategorias().addAll(Arrays.asList(categoria4));
		produto37.getCategorias().addAll(Arrays.asList(categoria4));
		produto38.getCategorias().addAll(Arrays.asList(categoria4));
		produto39.getCategorias().addAll(Arrays.asList(categoria4));
		produto40.getCategorias().addAll(Arrays.asList(categoria4));
		produto41.getCategorias().addAll(Arrays.asList(categoria4));
		produto42.getCategorias().addAll(Arrays.asList(categoria4));
		produto43.getCategorias().addAll(Arrays.asList(categoria4));
		produto44.getCategorias().addAll(Arrays.asList(categoria4));
		produto45.getCategorias().addAll(Arrays.asList(categoria4));
		produto46.getCategorias().addAll(Arrays.asList(categoria4));
		produto47.getCategorias().addAll(Arrays.asList(categoria4));
		produto48.getCategorias().addAll(Arrays.asList(categoria4));
		produto49.getCategorias().addAll(Arrays.asList(categoria4));
		produto50.getCategorias().addAll(Arrays.asList(categoria4));
		produto51.getCategorias().addAll(Arrays.asList(categoria4));
		
		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2, categoria3, categoria4, categoria5, categoria6, categoria7));
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3, produto4, produto5, produto6, produto7, produto8, produto9, produto10, produto11,
				produto12, produto13, produto14, produto15, produto16, produto17, produto18, produto19, produto20, produto21, produto22, produto23, produto24, produto25, produto26, produto27, produto28, produto29,
				produto30, produto31, produto32, produto33, produto34, produto35, produto36, produto37, produto38, produto39, produto40, produto41, produto32, produto43, produto44, produto45, produto46,
				produto47, produto48, produto49, produto50, produto51));
		
		Estado estado1  = new Estado(null, "Minas-gerais");
		Estado estado2 = new Estado(null, "São Paulo");
		
		Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
		Cidade cidade2 = new Cidade(null, "Barueri", estado2);
		Cidade cidade3 = new Cidade(null, "Sorocaba'", estado2);
		
		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));
		
		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		
		Cliente cliente01 = new Cliente(null, "Vinicius Torres Pascucci", "vinicius.pascucci1@gmail.com", "421.554.358-43", TipoCliente.PESSOA_FISICA, encoderPassword.encode("123"));
		
		Cliente cliente02 = new Cliente(null, "Ana Costa Silveira", "ana.costa@gmail.com", "731.758.640-40", TipoCliente.PESSOA_FISICA, encoderPassword.encode("456"));
		
		cliente02.addPerfil(Perfil.ADMIN);
		
		cliente01.getTelefones().addAll(Arrays.asList("112544-9669", "1194558-9975"));
		cliente02.getTelefones().addAll(Arrays.asList("114457-8970", "1199674-5580"));
		
		Endereco endereco01 = new Endereco(null, "Rua Hortêncio Moraes, Cazeca", "300", "Apto 303", "Jardim", "38400-012", cliente02, cidade1);
		Endereco endereco02 = new Endereco(null, "Avenida Aruanã", "851", "Apto 182 b", "Barueri-SP", "06460-010", cliente01, cidade2);
		
		cliente01.getEnderecos().addAll(Arrays.asList(endereco02));
		cliente02.getEnderecos().addAll(Arrays.asList(endereco01));
		
		clienteRepository.saveAll(Arrays.asList(cliente01, cliente02));
		enderecoRepository.saveAll(Arrays.asList(endereco01, endereco02));
		
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		Pedido pedido1 = new Pedido(null, formatoData.parse("30/09/2019 10:32:55"), endereco01, cliente02);
		Pedido pedido2 = new Pedido(null, formatoData.parse("22/05/2019 20:34:10"), endereco02, cliente01);
		
		Pagamento pagamento01 = new PagamentoComCartao(null, SituacaoPagamento.QUITADO, pedido1, 6);
		pedido1.setPagamento(pagamento01);
		
		Pagamento pagamento02 = new PagamentoComBoleto(null, SituacaoPagamento.PENDENTE, pedido2, formatoData.parse("25/05/2019 20:30:55"), 
					null);
		pedido2.setPagamento(pagamento02);
		
		cliente01.getPedidos().addAll(Arrays.asList(pedido1, pedido2));
		
		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagamento01, pagamento02));
		
		ItemPedido item1 = new ItemPedido(pedido1, produto1, 0.00, 2000.00, 2, "Computador gamer AOC HyperX");
		ItemPedido item2 = new ItemPedido(pedido1, produto3, 0.00, 80.00, 5, "Mouse Wireless Gamer Razer X");
		ItemPedido item3 = new ItemPedido(pedido2, produto2	, 100.00, 800.00, 1, "Impressora HP Full color");
		
		pedido1.getItems().addAll(Arrays.asList(item1, item2));
		pedido2.getItems().addAll(Arrays.asList(item3));
		
		produto1.getItems().addAll(Arrays.asList(item1));
		produto2.getItems().addAll(Arrays.asList(item3));
		produto3.getItems().addAll(Arrays.asList(item2));
		
		itemPedidoRepository.saveAll(Arrays.asList(item1, item2, item3));	

	}
}

package com.vinicius.springboot.mc.resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.springboot.mc.dto.ProdutoDTO;
import com.vinicius.springboot.mc.model.Produto;
import com.vinicius.springboot.mc.resources.util.UrlAuxiliarUtil;
import com.vinicius.springboot.mc.services.ProdutoService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Classe ProdutoResource que representa os nossos serviços REST.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;
	
	@Autowired
	private DataSource dataSource;
	
	/**
	 * Metodo GET para requisições de consulta
	 * @param id - Integer - id do produto.
	 * @return ResponseEntity<Produto>
	 */	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> find( @PathVariable Integer id ) {
		
		Produto produtoiaObj = service.find(id);
		
		return ResponseEntity.ok().body(produtoiaObj);
		
	}
	
	/**
	 * Metodo GET para paginação dos produtos.
	 * @return ResponseEntity.ok().body(listaDto).
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "nomeProduto", defaultValue = "") String nomeProduto,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nomeProduto") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		
		String nomeDecoded = UrlAuxiliarUtil.decodeParam(nomeProduto);
		
		List<Integer> ids = UrlAuxiliarUtil.decodeList(categorias);
		
		Page<Produto> lista = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		
		Page<ProdutoDTO> listaDto = lista.map(produtoObj -> new ProdutoDTO(produtoObj)); // Assim convertemos uma lista para outta lista
		
		return ResponseEntity.ok().body(listaDto);
	}
	
	/**
	 * Exibindo relatorio em formato PDF com Jasper Reports.
	 * @param response - HttpServletResponse - response.
	 * @throws JRException - Exceção JRException.
	 * @throws SQLException - Exceção SQLException.
	 */
	@RequestMapping( value = "/relatorio", method = RequestMethod.GET)
	public void printPdfJasperProduto( HttpServletResponse response ) throws JRException, IOException, SQLException {
		
		// Capturar o dados transformando em Stream
		InputStream relatorioStream = this.getClass().getResourceAsStream("/relatorios/Produto_Modelo.jasper");
		
		//Criando um Map
		Map<String, Object> params = new HashMap<String, Object>();
		
		// Outra maneira de se trabalhar com relatorios utilizando jasper é usar o HashMap params.
//		params.put("PARAM_1", "CUSTOM PARAM");
		
		// Criar o objeto Jasper Reports
		// O trecho abaixo é o necessarios para geração de relatorio em formato PDF.
		// Faço a captura dos meu item(JasperReports)
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(relatorioStream);
		
		// Com o objeto jasper aqui capturado, proximo passo é mandar os dados para dentro do (Detail1) = Jasper Reports
		//Preencho com os meus dados.
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());
		
		// Setar nosso content type no response passando o tipo de arquivo que será gerado.
		response.setContentType("application/pdf");
		
		response.setHeader("Content-Disposition", "inline; filename=produtos_relatorios.pdf");
		
		try {
			//Em um bloco try/catch finalizo com OutPutStream
			//Obs: poderia usar ByteArrayOutPutStream também
			OutputStream outputStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package com.vinicius.springboot.mc.resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vinicius.springboot.mc.dto.CategoriaDTO;
import com.vinicius.springboot.mc.model.Categoria;
import com.vinicius.springboot.mc.services.CategoriaService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Classe CategoriaResource que representa os nossos serviços REST.
 * @author Vinicius-PC - Vinicius Torres Pascucci.
 */
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	@Autowired
	private DataSource dataSource;
	
	/**
	 * Metodo GET para requisições de consulta
	 * @param id - Integer - id da categoria.
	 * @return ResponseEntity<Categoria>
	 */ 
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find( @PathVariable Integer id ) {
		
		Categoria categoriaObj = service.find(id);
		
		return ResponseEntity.ok().body(categoriaObj);
	}
	
	/**
	 * Metodo POST para inserir uma nova categoria.
	 * @param categoriaObj - Object - categoriaObj.
	 * @return ResponseEntity.created.
	 */
	@PreAuthorize("hasAnyRole('ADMIN')") 
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert( @Valid @RequestBody CategoriaDTO objDto ) {
		
		Categoria objeto = service.convertFromDTO(objDto);
		
		objeto = service.insert( objeto );
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(objeto.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	/**
	 * Metodo PUT para atualizar uma nova categoria.
	 * @param categoriaObj - Object - categoriaObj.
	 * @return ResponseEntity.created.
	 */
	@PreAuthorize("hasAnyRole('ADMIN')") 	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update( @Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id ) {
		
		Categoria objeto = service.convertFromDTO(objDto);
		
		objeto.setId( id ); 
		
		objeto = service.update( objeto );
		
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Metodo DELETE para excluir uma categoria.
	 * @param id - Integer - id da categoria.
	 * @return ResponseEntity<Void>
	 */
	@PreAuthorize("hasAnyRole('ADMIN')") 
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete( @PathVariable Integer id ) {
		
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	

	/**
	 * Metodo GET para listar todas as categorias.
	 * @return ResponseEntity<Categoria>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		
		List<Categoria> lista = service.findAll();
		
		List<CategoriaDTO> listaDto = lista.stream()
					.map(categoriaObj -> new CategoriaDTO(categoriaObj)).collect(Collectors.toList()); // Assim convertemos uma lista para outta lista
		
		return ResponseEntity.ok().body(listaDto);
	}
	
	/**
	 * Metodo GET para paginação das categorias.
	 * @return ResponseEntity.ok().body(listaDto).
	 */
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nomeCategoria") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		
		Page<Categoria> lista = service.findPage(page, linesPerPage, orderBy, direction);
		
		Page<CategoriaDTO> listaDto = lista.map(categoriaObj -> new CategoriaDTO(categoriaObj)); // Assim convertemos uma lista para outta lista
		
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

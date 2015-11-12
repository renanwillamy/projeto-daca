package com.projetodaca.core;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.projetodaca.entities.Categoria;
import com.projetodaca.entities.Cliente;
import com.projetodaca.entities.Contato;
import com.projetodaca.entities.Fornecedor;
import com.projetodaca.entities.Produto;
import com.projetodaca.entities.Usuario;
import com.projetodaca.services.CategoriaService;
import com.projetodaca.services.ClienteService;
import com.projetodaca.services.ContatoService;
import com.projetodaca.services.FornecedorService;
import com.projetodaca.services.ProdutoService;
import com.projetodaca.services.UsuarioService;

public class Fachada implements Serializable {

	@Inject
	private UsuarioService usuService;
	@Inject
	private ClienteService cliService;
	@Inject
	private FornecedorService fornService;


	/**
	 * Persiste produto no banco de dados
	 * 
	 * @param produto
	 */
	public Produto saveProduto(Produto produto) throws Exception {
		ProdutoService service = new ProdutoService();
		Produto prod = service.save(produto);
		service = null;
		return prod;
	}

	public void updateProduto(Produto produto) throws Exception {
		ProdutoService service = new ProdutoService();
		service.update(produto);
		service = null;
	}

	public void deleteProduto(Produto produto) throws Exception {
		ProdutoService service = new ProdutoService();
		service.delete(produto);
		service = null;
	}

	public List<Produto> listProduto() throws Exception {
		List<Produto> listProduto = null;
		ProdutoService service = new ProdutoService();
		listProduto = service.list();
		service = null;
		return listProduto;
	}

	public List<Produto> listProdutoPorNome(String nome) throws Exception {
		List<Produto> listProduto = null;
		ProdutoService service = new ProdutoService();
		listProduto = service.listaProdutoPorNome(nome);
		return listProduto;
	}

	public Produto getProdutoById(int id) throws Exception {
		Produto produto = null;
		ProdutoService service = new ProdutoService();
		produto = service.getById(id);
		service = null;
		return produto;
	}

	public Categoria saveCategoria(Categoria categoria) throws Exception {
		CategoriaService service = new CategoriaService();
		Categoria cat = service.save(categoria);
		service = null;
		return cat;
	}

	public void updateCategoria(Categoria categoria) throws Exception {
		CategoriaService service = new CategoriaService();
		service.update(categoria);
		service = null;
	}

	public void deleteCategoria(Categoria categoria) throws Exception {
		CategoriaService service = new CategoriaService();
		service.delete(categoria);
		service = null;
	}

	public List<Categoria> listCategoria() throws Exception {
		List<Categoria> listCategoria = null;
		CategoriaService service = new CategoriaService();
		listCategoria = service.list();
		service = null;
		return listCategoria;
	}

	public List<Categoria> listCategoria(String where) throws Exception {

		List<Categoria> listCategoria = null;
		CategoriaService service = new CategoriaService();
		listCategoria = service.list(where);
		service = null;
		return listCategoria;
	}

	public Categoria getCategoriaById(int id) throws Exception {
		CategoriaService service = new CategoriaService();
		Categoria cat = service.getById(id);
		service = null;
		return cat;
	}

	/**
	 * Persiste fornecedor no banco de dados
	 * 
	 * @param fornecedor
	 */
	public Fornecedor saveFornecedor(Fornecedor fornecedor) throws Exception {	
		fornecedor = fornService.save(fornecedor);		
		return fornecedor;
	}

	public void updateFornecedor(Fornecedor fornecedor) throws Exception {		
		fornService.update(fornecedor);		
	}

	public void deleteFornecedor(Fornecedor fornecedor) throws Exception {		
		fornService.delete(fornecedor);	
	}

	public List<Fornecedor> listFornecedor() throws Exception {	
		return fornService.list();
	}

	public Fornecedor getFornecedorById(int id) throws Exception {		
		
		return fornService.getById(id);
	}

	/**
	 * Persiste contato no banco de dados
	 * 
	 * @param contato
	 */
	public Contato save(Contato contato) throws Exception {
		ContatoService service = new ContatoService();
		contato = service.save(contato);
		service = null;
		return contato;
	}

	public void update(Contato contato) throws Exception {
		ContatoService service = new ContatoService();
		service.update(contato);
		service = null;
	}

	public void delete(Contato contato) throws Exception {
		ContatoService service = new ContatoService();
		service.delete(contato);
		service = null;
	}

	public List<Fornecedor> listaFornecedorPorNomeFantasia(String nomeFantasia) throws Exception {		
		 
		return fornService.listaFornecedorPorNomeFantasia(nomeFantasia);
	}

	/**
	 * Persiste cliente no banco de dados
	 * 
	 * @param cliente
	 */
	public Cliente saveCliente(Cliente cliente) throws Exception {
		return cliService.save(cliente);			
	}

	public void updateCliente(Cliente cliente) throws Exception {		
		cliService.update(cliente);		
	}

	public void deleteCliente(Cliente cliente) throws Exception {		
		cliService.delete(cliente);		
	}

	public List<Cliente> listCliente() throws Exception {		
		return cliService.list();				
	}

	public List<Cliente> listaClientePorNome(String nome) throws Exception {		
		return cliService.listaClientePorNome(nome);	
	}

	public Cliente getClienteById(int id) throws Exception {		
		return  cliService.getById(id);		

	}

	public Usuario saveUsuario(Usuario usuario) throws Exception {
		usuario = usuService.save(usuario);
		return usuario;
	}

	public void updateUsuario(Usuario usuario) throws Exception {
		usuService.update(usuario);

	}

	public void deleteUsuario(Usuario usuario) throws Exception {
		usuService.delete(usuario);

	}

	public List<Usuario> listUsuario() throws Exception {

		List<Usuario> lista = usuService.list();

		return lista;
	}

	public List<Usuario> listaUsuarioPorNome(String nome) throws Exception {

		List<Usuario> lista = usuService.listaUsuarioPorNome(nome);

		return lista;
	}

	public Usuario getUsuarioById(int id) throws Exception {

		Usuario usuario = usuService.getById(id);
		return usuario;
	}

	public Usuario autenticaUsuario(String login, String senha) throws Exception {

		Usuario usuario = usuService.autenticaUsuario(login, senha);
		return usuario;
	}

}

package com.projetodaca.core;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.projetodaca.entities.Categoria;
import com.projetodaca.entities.Cliente;
import com.projetodaca.entities.Contato;
import com.projetodaca.entities.Fornecedor;
import com.projetodaca.entities.Pagamento;
import com.projetodaca.entities.Pedido;
import com.projetodaca.entities.Produto;
import com.projetodaca.entities.Promissoria;
import com.projetodaca.entities.Usuario;
import com.projetodaca.services.CategoriaService;
import com.projetodaca.services.ClienteService;
import com.projetodaca.services.ContatoService;
import com.projetodaca.services.FornecedorService;
import com.projetodaca.services.PedidoService;
import com.projetodaca.services.ProdutoService;
import com.projetodaca.services.UsuarioService;

public class Fachada implements Serializable {

	@Inject
	private UsuarioService usuService;
	@Inject
	private ClienteService cliService;
	@Inject
	private FornecedorService fornService;
	@Inject
	private ProdutoService prodService;
	@Inject
	private CategoriaService catService;
	@Inject
	private PedidoService pedService;


	/**
	 * Persiste produto no banco de dados
	 * 
	 * @param produto
	 */
	public Produto saveProduto(Produto produto) throws Exception {			
		return prodService.save(produto);
	}

	public void updateProduto(Produto produto) throws Exception {		
		prodService.update(produto);		
	}

	public void deleteProduto(Produto produto) throws Exception {		
		prodService.delete(produto);		
	}

	public List<Produto> listProduto() throws Exception {		
		return prodService.list();
	}

	public List<Produto> listProdutoPorNome(String nome) throws Exception {			
		 
		return prodService.listaProdutoPorNome(nome);
	}

	public Produto getProdutoById(int id) throws Exception {	
		return prodService.getById(id);
	}
	
	public List<Produto> listaProdutosAtivos() throws Exception {
		return prodService.listaProdutoAtivos();
	}

	public Categoria saveCategoria(Categoria categoria) throws Exception {	
		return catService.save(categoria);
	}

	public void updateCategoria(Categoria categoria) throws Exception {		
		catService.update(categoria);	
	}

	public void deleteCategoria(Categoria categoria) throws Exception {		
		catService.delete(categoria);	
	}

	public List<Categoria> listCategoria() throws Exception {			
		return catService.list();
	}

	public List<Categoria> listCategoria(String where) throws Exception {	
		return catService.list(where);
	}

	public Categoria getCategoriaById(int id) throws Exception {			
		return catService.getById(id);
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
	
		return contato;
	}

	public void update(Contato contato) throws Exception {
		ContatoService service = new ContatoService();
		service.update(contato);
	
	}

	public void delete(Contato contato) throws Exception {
		ContatoService service = new ContatoService();
		service.delete(contato);
	
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
	
	 public Pedido savePedido(Pedido pedido) throws Exception{
		return pedService.save(pedido);		 
	 }
	 
	 public void updatePedido(Pedido pedido) throws Exception{
		 pedService.update(pedido);
	 }
	 
	 public void deletePedido(Pedido pedido) throws Exception{
		 pedService.delete(pedido);
	 }
	 public List<Pedido> listPedidos() throws Exception{
		return pedService.list();
	 }
	 
	 public Pedido getPedidoById(int id) throws Exception{
		 return pedService.getById(id);
	 }

	public void savePedido(Pedido pedido, Pagamento pagamento) throws Exception {		
		pedService.save(pedido,pagamento);	
	}

	public List<Pedido> listPedidoPorId(String filtro) throws Exception {		
		return pedService.listaPedidoPorId(filtro);
	}
	
	public void updatePedido(Pedido pedido, Pagamento pagamento) throws Exception {
		pedService.update(pedido, pagamento);
	}

}

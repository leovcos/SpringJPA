package br.gov.sp.fatec.service;

import br.gov.sp.fatec.model.Usuario;

public interface UsuarioService {
	
	public Usuario incluirUsuario(String nome, String senha, String nomeAutorizacao);
	
	public void incluirDoisUsuarios(String nomeUsuario1, String nomeUsuario2);
	
	public void excluirUsuario(String nome);

}

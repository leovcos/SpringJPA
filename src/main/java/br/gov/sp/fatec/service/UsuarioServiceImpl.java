package br.gov.sp.fatec.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Autorizacao;
import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.repository.AutorizacaoRepository;
import br.gov.sp.fatec.repository.UsuarioRepository;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private AutorizacaoRepository autorizacaoRepo;

	/**
	 * @param usuarioRepo the usuarioRepo to set
	 */
	public void setUsuarioRepo(UsuarioRepository usuarioRepo) {
		this.usuarioRepo = usuarioRepo;
	}
	
	/**
	 * @param autorizacaoRepo the autorizacaoRepo to set
	 */
	public void setAutorizacaoRepo(AutorizacaoRepository autorizacaoRepo) {
		this.autorizacaoRepo = autorizacaoRepo;
	}

	@Override
	@Transactional
	public Usuario incluirUsuario(String nome, String senha, String nomeAutorizacao) {
		Autorizacao autorizacao = autorizacaoRepo.findByNome(nomeAutorizacao);
		// Se nao existe
		if(autorizacao == null) {
			// Cria nova
			autorizacao = new Autorizacao();
			autorizacao.setNome(nomeAutorizacao);
			autorizacaoRepo.save(autorizacao);
		}
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setSenha(senha);
		usuario.setAutorizacoes(new ArrayList<Autorizacao>());
		usuario.getAutorizacoes().add(autorizacao);
		usuarioRepo.save(usuario);
		return usuario;
	}

	@Override
	@Transactional
	public void incluirDoisUsuarios(String nomeUsuario1, String nomeUsuario2) {
		Usuario usuario = new Usuario();
		usuario.setNome(nomeUsuario1);
		usuario.setSenha(nomeUsuario1);
		usuarioRepo.save(usuario);
		usuario = new Usuario();
		usuario.setNome(nomeUsuario2);
		usuario.setSenha(nomeUsuario2);
		usuarioRepo.save(usuario);		
	}

	@Override
	@Transactional
	public void excluirUsuario(String nome) {
		List<Usuario> usuarios = usuarioRepo.buscaUsuario(nome);
		usuarioRepo.deleteAll(usuarios);
	}

}

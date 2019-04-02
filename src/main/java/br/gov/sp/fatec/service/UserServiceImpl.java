package br.gov.sp.fatec.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Authority;
import br.gov.sp.fatec.model.User;
import br.gov.sp.fatec.repository.AuthorityRepository;
import br.gov.sp.fatec.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AuthorityRepository authRepo;

	@Override
	@Transactional
	public User save(User user) {

		user.setAuthorities(new ArrayList<Authority>());

		Authority userAuthority = authRepo.findByName("USER");

		if (userAuthority == null) {
			userAuthority = new Authority();
			userAuthority.setName("USER");
			authRepo.save(userAuthority);
			user.addAuthority(userAuthority);
		}
		user.setPassword(md5(user.getPassword()));

		return userRepo.save(user);
	}

	public static String md5(String senha) {
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

			StringBuilder hexString = new StringBuilder();
			hexString.append("{MD5}");
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException exception) {
			exception.printStackTrace();
			// Unexpected - do nothing
		} catch (UnsupportedEncodingException exception) {
			exception.printStackTrace();
			// Unexpected - do nothing
		}
		return senha;
	}

}

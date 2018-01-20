package persistence;

import org.eclipse.jdt.internal.compiler.batch.Main;

import entity.Usuario;

public class UsuarioDao extends Dao {

	public void create(Usuario usuario) throws Exception {
		open();
		stmt = con.prepareStatement("insert into usuario values (null,?,md5(?) )");
		stmt.setString(1, usuario.getLogin());
		stmt.setString(2, usuario.getSenha());
		stmt.execute();
		stmt.close();
		close();
	}

	public Usuario login(Usuario u) throws Exception {
		open();
		stmt = con.prepareStatement("select * from usuario where login=? and senha=md5(?)");
		stmt.setString(1, u.getLogin());
		stmt.setString(2, u.getSenha());
		rs = stmt.executeQuery();
		Usuario usu = null;
		if (rs.next()) {
			usu = new Usuario(rs.getInt("id"), rs.getString("login"), rs.getString("senha"));

		}
		close();
		return usu;
	}

	public static void main(String[] args) {
		try {
			// Usuario u = new Usuario(null,"minimim@gmail.com","123");
			// new UsuarioDao().create(u);
			// System.out.println("Dados Gravados");

			Usuario u = new UsuarioDao().login(new Usuario(null, "abc", "123"));

			if (u != null) {
				System.out.println("logado :" + u.getLogin());
			} else {
				System.out.println("nao logado");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
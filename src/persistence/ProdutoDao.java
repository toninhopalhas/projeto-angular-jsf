package persistence;

import java.util.ArrayList;
import java.util.List;

import entity.Produto;

public class ProdutoDao extends Dao {

	public void create(Produto p) throws Exception {
		open();
		stmt = con.prepareStatement("insert into produto values(null,?,?)");
		stmt.setString(1, p.getNome());
		stmt.setDouble(2, p.getPreco());
		stmt.execute();
		close();
	}

	public void update(Produto p) throws Exception {
		open();
		stmt = con.prepareStatement("update produto  set nome=?,preco=? where codigo=?");
		stmt.setString(1, p.getNome());
		stmt.setDouble(2, p.getPreco());
		stmt.setInt(3, p.getCodigo());
		stmt.execute();
		close();
	}

	public void delete(Integer cod) throws Exception {
		open();
		stmt = con.prepareStatement("delete from produto where codigo=?");
		stmt.setInt(1, cod);
		stmt.execute();
		stmt.close();
		close();
	}

	public List<Produto> findAll() throws Exception {
		open();
		stmt = con.prepareStatement("select * from produto");
		rs = stmt.executeQuery();
		List<Produto> lst = new ArrayList<Produto>();
		while (rs.next()) {
			Produto p = new Produto();
			p.setCodigo(rs.getInt("codigo"));
			p.setNome(rs.getString("nome"));
			p.setPreco(rs.getDouble("preco"));
			lst.add(p);
		}
		close();
		return lst;
	}

	public Produto findByCode(Integer cod) throws Exception {
		open();
		stmt = con.prepareStatement("select * from produto where codigo=?");
		stmt.setInt(1, cod);
		rs = stmt.executeQuery();
		Produto p = null;
		if (rs.next()) {
			p = new Produto();
			p.setCodigo(rs.getInt("codigo"));
			p.setNome(rs.getString("nome"));
			p.setPreco(rs.getDouble("preco"));
		}
		close();
		return p;
	}
	
	public static void main(String[] args) {
		try {
			
			ProdutoDao dao = new ProdutoDao();
			
			Produto p1 = new Produto (null, "motorola",400.);
			Produto p2 = new Produto (null, "nextel",20.);
			
			dao.create(p1);
			dao.create(p2);
			
			System.out.println("produto gravado");
			
			
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
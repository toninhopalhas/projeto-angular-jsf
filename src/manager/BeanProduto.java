package manager;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import entity.Produto;
import entity.Usuario;
import persistence.ProdutoDao;
import persistence.UsuarioDao;

@ManagedBean(name = "mb")
@RequestScoped
public class BeanProduto {

	private Usuario usuario;
	private Usuario logado;
	private Produto produto;
	private List<Produto> produtos;

	public BeanProduto() {
		produto = new Produto();
		usuario = new Usuario();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		try {
			produtos = new ProdutoDao().findAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return produtos;
	}

	public void gravarUsuario() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			new UsuarioDao().create(usuario);
			usuario = new Usuario();
			fc.addMessage(null, new FacesMessage("Dados gravados"));
		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage("Error :" + ex.getMessage()));
		}
	}

	public String logar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
			logado = (Usuario) new UsuarioDao().login(usuario);
			if (logado != null) {
				// Filtro tenho que tratar Session ...
				session.setAttribute("logado", logado);
				fc.addMessage(null, new FacesMessage("usuario Logado"));
				return "logado.jsf?faces-redirect=true";
			} else {
				fc.addMessage(null, new FacesMessage("Login Invalido"));
				session.invalidate();
				return null;
			}

		} catch (Exception ex) {
			return null;
		}

	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getLogado() {
		return logado;
	}

	public void setLogado(Usuario logado) {
		this.logado = logado;
	}

}
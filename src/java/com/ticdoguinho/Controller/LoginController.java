package com.ticdoguinho.controller;

import com.ticdoguinho.model.dao.ManagerDao;
import com.ticdoguinho.model.negocio.Tutor;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ALUNO
 */
@ManagedBean
@SessionScoped
public class LoginController {

    private Tutor usuarioLogado;
    
    public String realizarLogin(String login, String senha) {

        try {
            Tutor uLogin = (Tutor) ManagerDao.getCurrentInstance()
                    .read("select u from Tutor u"
                            + " where u.email = '" + login
                            + "' and u.senha = '" + senha+"'", Tutor.class).get(0);
            
            
            this.usuarioLogado = uLogin;
            
            return "indexUsuario";
        } catch (Exception e) {
            
            e.printStackTrace();
            FacesContext.getCurrentInstance()
                    .addMessage(null, 
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao Logar","Usuário e/ou senha estão incorretos"));
            return null;
        }

    }
    
    public String logout(){
        this.usuarioLogado = null;
        
        return "login";
    }

    public Tutor getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Tutor usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
    
    

}

package com.ticdoguinho.Controller;

import com.ticdoguinho.model.dao.ManagerDao;
import com.ticdoguinho.model.negocio.Tutor;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ALUNO
 */

@ManagedBean
@SessionScoped
public class TutorController {
    
    public  Tutor tutorCadastro;
    private Tutor selection;
    private String modalType;
    
    @PostConstruct
    public void init(){
        this.tutorCadastro = new Tutor();
        this.modalType = "create";
    }
    
    public void inserir(String confirma){
        
        if(!this.tutorCadastro.getSenha().equals(confirma)){
            
            FacesContext.getCurrentInstance().addMessage("formCadUsuario:pswSenha", 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro Severo","A senha e a confirmação não batem!"));
            
            return;
        }
        
        ManagerDao.getCurrentInstance().insert(this.tutorCadastro);
        
        this.tutorCadastro = new Tutor();
        
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Tutor cadastrado com sucesso!"));
        
    }
    
    public List<Tutor> readUsuarios(){
        
        List<Tutor> usuarios = null;
        
        usuarios = ManagerDao.getCurrentInstance()
                .read("select u from Tutor u", Tutor.class);
        
        return usuarios;
        
    }

    public Tutor getTutorCadastro() {
        return tutorCadastro;
    }

    public void setTutorCadastro(Tutor usuarioCadastro) {
        this.tutorCadastro = usuarioCadastro;
    }

    public Tutor getSelection() {
        return selection;
    }

    public void setSelection(Tutor selection) {
        this.selection = selection;
    }
    
    public String alterar(){
        
        ManagerDao.getCurrentInstance().update(this.selection);
        
        FacesContext.getCurrentInstance()
                .addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Sucesso!", "Tutor Cadastrado com Sucesso"));
        
        return "usuarios";
    }
    
    public void editarPerfil(){
        
        ManagerDao.getCurrentInstance().update(this.selection);
        
        FacesContext.getCurrentInstance()
                .addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Sucesso!", "usuario alterado com Sucesso"));
        
        
    }
    
    public void deletar(){
        
        ManagerDao.getCurrentInstance().delete(this.selection);
        
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage("Sucesso!", "Infelizmente você deletou seu usuario estimado snif snif snif"));
        
    }
    
    public void alterarSenha(String senha, String novaSenha, String confirma){
        
        //código para recuperar qualquer atributo na sessão
        Tutor uLogado = ((LoginController)((HttpSession)FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true))
                .getAttribute("loginController")).getUsuarioLogado();
        
    
        if(!uLogado.getSenha().equals(senha)){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("A senha digitada está incorreta. "
                            + "Por favor, digite novamente de forma correta, seu animal"));
            return ;
        }
        
        if(!novaSenha.equals(confirma)){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("A nova senha não bate com a confirmação. "
                            + "Por favor, digite novamente de forma correta, seu animal"));
            return ;
        }
        
        uLogado.setSenha(novaSenha);
        
        ManagerDao.getCurrentInstance().update(uLogado);
        
        FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Senha alterada com sucesso!"));
    }
    
    //gambiarra para controlar a abertura do modal, porque Ana Paulo
    //exigiu... porque a graça da gambiarra é ser auto explicativa
    public void modalType(String type){
        this.modalType = type;
    }
    
    public String getModalType() {
        return modalType;
    }
    
}

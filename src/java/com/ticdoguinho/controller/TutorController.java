package com.ticdoguinho.controller;

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

@ManagedBean(name="tutorController")
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

    public void setTutorCadastro(Tutor tutorCadastro) {
        this.tutorCadastro = tutorCadastro;
    }

    public Tutor getSelection() {
        return selection;
    }

    public void setSelection(Tutor selection) {
        this.selection = selection;
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

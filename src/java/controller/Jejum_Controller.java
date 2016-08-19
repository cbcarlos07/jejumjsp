/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Jejum;
import java.util.List;
import model.Jejum_DAO;

/**
 *
 * @author CARLOS
 */
public class Jejum_Controller {
    Jejum_DAO jd = new Jejum_DAO();
    List lista;
    public List<Jejum> lista(){
        lista = jd.lista();
        return lista;
    }
}

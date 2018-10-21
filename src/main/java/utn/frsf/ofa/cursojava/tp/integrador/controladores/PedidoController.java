/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.tp.integrador.controladores;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Pedido;
import utn.frsf.ofa.cursojava.tp.integrador.servicio.PedidoService;
import utn.frsf.ofa.cursojava.tp.integrador.servicio.RecetaService;

/**
 *
 * @author federicoaugustotschopp
 */
@Named("PedidoController")
@SessionScoped
public class PedidoController implements Serializable{
    @Inject
    PedidoService pedidoSrv;
    @Inject
    RecetaService recetaSrv;
    @Inject
    RecetaController recetaCtrl;
    Pedido pedidoSeleccionado;
    
    public String guardar(){
        
        /*pedidoSeleccionado.setRecetasPedidas(this.recetaCtrl.getLstRecetasSeleccionadas());*/
        this.pedidoSeleccionado=pedidoSrv.guardar(pedidoSeleccionado);
        this.pedidoSeleccionado=null;
        return null;
    }
    public String nuevo() {
        this.pedidoSeleccionado = new Pedido();
        this.pedidoSeleccionado.setRecetasPedidas(this.recetaCtrl.getLstRecetasSeleccionadas());
        return null;
    }
    public Pedido getPedidoSeleccionado() {
        return pedidoSeleccionado;
    }

    public void setPedidoSeleccionado(Pedido pedidoSeleccionado) {
        this.pedidoSeleccionado = pedidoSeleccionado;
    }
    
}

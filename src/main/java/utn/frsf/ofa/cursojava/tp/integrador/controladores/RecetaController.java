package utn.frsf.ofa.cursojava.tp.integrador.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DualListModel;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Autor;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Ingrediente;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Receta;
import utn.frsf.ofa.cursojava.tp.integrador.servicio.IngredienteService;
import utn.frsf.ofa.cursojava.tp.integrador.servicio.RecetaService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mdominguez
 */

// TODO: definir anotacion de ambito 
@Named("recetaController")
@SessionScoped
public class RecetaController implements Serializable {

    @Inject
    RecetaService recetaSrv;

    @Inject
    IngredienteService ingredienteSrv;

    private Receta recetaSeleccionada;
    private Autor autorSeleccionado;
    private Ingrediente ingredienteSeleccionado;
    private Date fechaDesde;
    private Date fechaHasta;
    private Double precioMin;
    private Double precioMax;
    private String nomAutor;
    private String nomIngrediente;

    
    
    private List<Receta> listaRecetas;
    private List<Receta> lstBusqRecetas;
    private List<Receta> lstRecetasSeleccionadas;

    private DualListModel<Ingrediente> ingredientesDisponibles;
    
    public Receta getRecetaSeleccionada() {
        return recetaSeleccionada;
    }

    public void setRecetaSeleccionada(Receta recetaSeleccionada) {
        
        this.recetaSeleccionada = recetaSeleccionada;
        this.recetaSeleccionada .setIngredientes(recetaSrv.ingredientesPorIdReceta(recetaSeleccionada.getId()));
        this.ingredientesDisponibles.setTarget(recetaSeleccionada.getIngredientes());       
    }

    public List<Receta> getListaRecetas() {
        return this.listaRecetas;
    }

    public void setListaRecetas(List<Receta> listaRecetas) {
        this.listaRecetas = listaRecetas;
    }

    @PostConstruct
    public void init() {
        this.recetaSeleccionada = null;
        this.autorSeleccionado=null;
        this.listaRecetas = recetaSrv.listar();
        List<Ingrediente> origen = ingredienteSrv.listar();
        List<Ingrediente> destino = new ArrayList<Ingrediente>();
        this.ingredientesDisponibles = new DualListModel<>(origen, destino);        
    }

    public DualListModel<Ingrediente> getIngredientesDisponibles() {
        return ingredientesDisponibles;
    }

    public void setIngredientesDisponibles(DualListModel<Ingrediente> ingredientesDisponibles) {
        this.ingredientesDisponibles = ingredientesDisponibles;
    }

    public String guardar() {
        recetaSeleccionada.setIngredientes(this.ingredientesDisponibles.getTarget());
        // TODO completar el metodo guardar
        // setear el autor de la receta seleccionada
        // invocar al metodo qeu guarda la receta
        this.recetaSeleccionada.setAutor(this.autorSeleccionado);
        this.recetaSrv.guardar(this.recetaSeleccionada);
        this.recetaSeleccionada = null;
        return null;
    }

    public String nuevo() {
        this.init();
        
        this.recetaSeleccionada = new Receta();
        this.recetaSeleccionada.setIngredientes(new ArrayList<>());
        this.ingredientesDisponibles.setTarget(new ArrayList<Ingrediente>());
        return null;
    }

    public Autor getAutorSeleccionado() {
        return autorSeleccionado;
    }

    public void setAutorSeleccionado(Autor autorSeleccionado) {
        this.autorSeleccionado = autorSeleccionado;
    }

    public Ingrediente getIngredienteSeleccionado() {
        return ingredienteSeleccionado;
    }

    public void setIngredienteSeleccionado(Ingrediente ingredienteSeleccionado) {
        this.ingredienteSeleccionado = ingredienteSeleccionado;
    }
    
    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }
    
    public Double getPrecioMin() {
        return precioMin;
    }

    public void setPrecioMin(Double precioMin) {
        this.precioMin = precioMin;
    }

    public Double getPrecioMax() {
        return precioMax;
    }

    public void setPrecioMax(Double precioMax) {
        this.precioMax = precioMax;
    }
    public String doBusquedaAvanzada(){
        
        
        this.lstBusqRecetas=recetaSrv.busquedaAvanzada1(nomAutor, nomIngrediente, precioMin, precioMax, fechaDesde, fechaDesde);
        if ( this.lstBusqRecetas!=null)
            this.recetaSeleccionada=this.lstBusqRecetas.get(0);
        return "busquedaAvanzada.xhtml";
    }

    public String getNomAutor() {
        return nomAutor;
    }

    public void setNomAutor(String nomAutor) {
        this.nomAutor = nomAutor;
    }

    public String getNomIngrediente() {
        return nomIngrediente;
    }

    public void setNomIngrediente(String nomIngrediente) {
        this.nomIngrediente = nomIngrediente;
    }
    public String limpiar(){
        this.nomAutor="";
        this.nomIngrediente="";
        this.precioMin=null;
        this.precioMax=null;
        this.fechaDesde=null;
        this.fechaHasta=null;
        return "busquedaAvanzada.xhtml";
        
    }

    public List<Receta> getLstBusqRecetas() {
        return lstBusqRecetas;
    }

    public void setLstBusqRecetas(List<Receta> lstBusqRecetas) {
        this.lstBusqRecetas = lstBusqRecetas;
    }

    public List<Receta> getLstRecetasSeleccionadas() {
        return lstRecetasSeleccionadas;
    }

    public void setLstRecetasSeleccionadas(List<Receta> lstRecetasSeleccionadas) {
        this.lstRecetasSeleccionadas = lstRecetasSeleccionadas;
    }
    
}

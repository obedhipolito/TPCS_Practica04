/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.tpcs_practica04.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author obed
 */
@Entity
@Table(name="venta")
public class Venta implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="venta_ventaId_seq")
    @SequenceGenerator(name="venta_ventaId_seq", sequenceName="venta_ventaId_seq", initialValue=1, allocationSize=1)
    @Column(name="ventaId")
    private long ventaId;
    
    @Column
    private Date fecha;
    
    @ManyToOne()
    @JoinColumn(name="clienteId")
    private Cliente cliente;
    
    @OneToMany(mappedBy="venta", cascade={CascadeType.REMOVE, CascadeType.MERGE}, /*orphanRemoval=true,*/ fetch=FetchType.EAGER)
    private List<VentaDetalle> detalles;

    @Column
    private BigDecimal total;

    public long getVentaId() {
        return ventaId;
    }

    public void setVentaId(long ventaId) {
        this.ventaId = ventaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<VentaDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<VentaDetalle> detalles) {
        this.detalles = detalles;
        this.total=new BigDecimal(0);
        for(VentaDetalle detalle:detalles){
            this.total=this.total.add(detalle.getPrecio().multiply(BigDecimal.valueOf(detalle.getCantidad())));
        }
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}

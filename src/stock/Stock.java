/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author ahmet
 */
@Entity
@Table(name = "STOCK", catalog = "", schema = "USE")
@NamedQueries({
    @NamedQuery(name = "Stock.findAll", query = "SELECT s FROM Stock s"),
    @NamedQuery(name = "Stock.findByStockkey", query = "SELECT s FROM Stock s WHERE s.stockkey = :stockkey"),
    @NamedQuery(name = "Stock.findByStockname", query = "SELECT s FROM Stock s WHERE s.stockname = :stockname"),
    @NamedQuery(name = "Stock.findByStockprice", query = "SELECT s FROM Stock s WHERE s.stockprice = :stockprice"),
    @NamedQuery(name = "Stock.findByStockquantity", query = "SELECT s FROM Stock s WHERE s.stockquantity = :stockquantity")})
public class Stock implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "STOCKKEY")
    private String stockkey;
    @Column(name = "STOCKNAME")
    private String stockname;
    @Column(name = "STOCKPRICE")
    private Integer stockprice;
    @Column(name = "STOCKQUANTITY")
    private Integer stockquantity;

    public Stock() {
    }

    public Stock(String stockkey) {
        this.stockkey = stockkey;
    }

    public String getStockkey() {
        return stockkey;
    }

    public void setStockkey(String stockkey) {
        String oldStockkey = this.stockkey;
        this.stockkey = stockkey;
        changeSupport.firePropertyChange("stockkey", oldStockkey, stockkey);
    }

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        String oldStockname = this.stockname;
        this.stockname = stockname;
        changeSupport.firePropertyChange("stockname", oldStockname, stockname);
    }

    public Integer getStockprice() {
        return stockprice;
    }

    public void setStockprice(Integer stockprice) {
        Integer oldStockprice = this.stockprice;
        this.stockprice = stockprice;
        changeSupport.firePropertyChange("stockprice", oldStockprice, stockprice);
    }

    public Integer getStockquantity() {
        return stockquantity;
    }

    public void setStockquantity(Integer stockquantity) {
        Integer oldStockquantity = this.stockquantity;
        this.stockquantity = stockquantity;
        changeSupport.firePropertyChange("stockquantity", oldStockquantity, stockquantity);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stockkey != null ? stockkey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stock)) {
            return false;
        }
        Stock other = (Stock) object;
        if ((this.stockkey == null && other.stockkey != null) || (this.stockkey != null && !this.stockkey.equals(other.stockkey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "stock.Stock[ stockkey=" + stockkey + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}

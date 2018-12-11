/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author alumno
 */
public class Pagares {
    private int id_pagare;
    FacturaVentas ventas;
    private String montoenletras;

    public Pagares() {
    }

    public Pagares(int id_pagare, FacturaVentas ventas, String montoenletras) {
        this.id_pagare = id_pagare;
        this.ventas = ventas;
        this.montoenletras = montoenletras;
    }

    public int getId_pagare() {
        return id_pagare;
    }

    public void setId_pagare(int id_pagare) {
        this.id_pagare = id_pagare;
    }

    public FacturaVentas getVentas() {
        return ventas;
    }

    public void setVentas(FacturaVentas ventas) {
        this.ventas = ventas;
    }

    public String getMontoenletras() {
        return montoenletras;
    }

    public void setMontoenletras(String montoenletras) {
        this.montoenletras = montoenletras;
    }

    
}

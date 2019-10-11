package DAM.models;

import java.util.Date;

public class Partido {
    private String local;
    private String visitante;
    private String division;
    private String resultado;
    private Date fecha;

    public Partido (String local, String visitante, String division, String resultado, Date fecha){
        this.local = local;
        this.visitante = visitante;
        this.division = division;
        this.resultado = resultado;
        this.fecha = fecha;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String toString() {
        return "Partido [local=" + local + ", visitante=" + visitante + ", division="+ division +" , resultado="+ resultado +", Fecha="+ fecha +"]";
    }
}

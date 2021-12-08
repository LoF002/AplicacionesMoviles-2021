package com.example.ejemplobd;

public class Operacion {

    private int id=0;
    private float factor1, factor2, resultado;

    public Operacion(int id, float factor1, float factor2, float resultado) {
        this.id = id;
        this.factor1 = factor1;
        this.factor2 = factor2;
        this.resultado = resultado;
    }

    public Operacion() {
        this.id = 0;
        this.factor1 = 0;
        this.factor2 = 0;
        this.resultado = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getFactor1() {
        return factor1;
    }

    public void setFactor1(float factor1) {
        this.factor1 = factor1;
    }

    public float getFactor2() {
        return factor2;
    }

    public void setFactor2(float factor2) {
        this.factor2 = factor2;
    }

    public float getResultado() {
        return resultado;
    }

    public void setResultado(float resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "Identificador:" + id +
                " - El factor 1 =" + factor1 +
                " - El factor 2 =" + factor2 +
                "\nResultado =" + resultado;
    }
}//Fin clase

package com.example.estudiante_java_estudiantes;

public class Estudiante {
    private String nombre;
    private String asignatura;
    private String fecha;
    private float corte1;
    private float corte2;
    private float corte3;

    public Estudiante(String nombre, String asignatura, String fecha, float corte1, float corte2, float corte3) {
        this.nombre = nombre;
        this.asignatura = asignatura;
        this.fecha = fecha;
        this.corte1 = corte1;
        this.corte2 = corte2;
        this.corte3 = corte3;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getAsignatura() { return asignatura; }
    public void setAsignatura(String asignatura) { this.asignatura = asignatura; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public float getCorte1() { return corte1; }
    public void setCorte1(float corte1) { this.corte1 = corte1; }

    public float getCorte2() { return corte2; }
    public void setCorte2(float corte2) { this.corte2 = corte2; }

    public float getCorte3() { return corte3; }
    public void setCorte3(float corte3) { this.corte3 = corte3; }
}

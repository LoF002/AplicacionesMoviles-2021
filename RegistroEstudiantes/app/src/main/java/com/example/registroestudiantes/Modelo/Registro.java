package com.example.registroestudiantes.Modelo;

import java.util.ArrayList;

public class Registro {

    ArrayList <Estudiante> listaEstudiantes;

    public Registro(){
        this.listaEstudiantes = new ArrayList<Estudiante>();
    }//fin constructor

    public int buscarPosicion(String carnet){
        if(carnet!=null){
            for (int i = 0; i < listaEstudiantes.size() ; i++) {
                if (listaEstudiantes.get(i).getCarnet().equalsIgnoreCase(carnet)){
                    return i;
                }//fin for
            }//fin for
        }//fin if
        return -1;
    }//fin metodo buscar

    public String agregarEstudiante(Estudiante estudiante){
        if(estudiante!=null){
            if(buscarPosicion(estudiante.getCarnet())==-1){
                listaEstudiantes.add(estudiante);
                return "Agregado correctamente";
            }//fin if
            else{
                return "Ya existe";
            }//fin else
        }//Fin if
        return "Error al agregar";
    }//fin metodo agregar

    public String modificarEstudiante(Estudiante estudiante, int posicion){
        if(estudiante!=null && posicion!=-1){
            listaEstudiantes.get(posicion).setNombre(estudiante.getNombre());
            listaEstudiantes.get(posicion).setCarrera(estudiante.getCarrera());
            listaEstudiantes.get(posicion).setTelefono(estudiante.getTelefono());
            return "Modificado correctamente";
        }//Fin if
        return "Error al modificar";
    }//Fin metodo modificar

    public String eliminarEstudiante(int posicion){
        if(posicion!=-1){
            listaEstudiantes.remove(posicion);
            return "Eliminado correctamente";
        }//Fin if
        return "Error al eliminar";
    }//Fin metodo eliminar

    public String getInformacionEstudiante(int posicion){
        if(posicion!=-1){
            return listaEstudiantes.get(posicion).toString();
        }//Fin if
        else{
            return "No existe";
        }//Fin else
    }//Fin metodo getInformacion

    public Estudiante devolverEstudiante(int posicion){
        if(posicion!=-1){
            return listaEstudiantes.get(posicion);
        }//Fin if
        else{
            return null;
        }//Fin else
    }//fin metodo devolver

    public ArrayList<Estudiante> devolverLista(){
        return listaEstudiantes;
    }//Fin metodo devolverlista

}//Fin clase

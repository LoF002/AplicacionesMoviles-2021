package com.example.practicalogin.Modelo;

import java.util.ArrayList;

public class RegistroCliente {

    ArrayList <Cliente> listaClientes;

    public RegistroCliente(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public RegistroCliente() {
        listaClientes = new ArrayList<Cliente>();
    }//Fin constructor

    public int buscarPosicion(String usuario){
        if(usuario!=null){
            for (int i = 0; i < listaClientes.size() ; i++) {
                if (listaClientes.get(i).getUsuario().equalsIgnoreCase(usuario)){
                    return i;
                }//fin if
            }//fin for
        }//fin if
        return -1;
    }//Fin buscarPosicion

    public String agregarCliente(Cliente cliente){
        if(cliente!=null){
            if(buscarPosicion(cliente.getUsuario())==-1){
                listaClientes.add(cliente);
                return "Agregado correctamente";
            }//fin if
            else{
                return "Ya existe";
            }//fin else
        }//Fin if
        return "Error al agregar";
    }//Fin agregarCliente

    public String actualizarCliente(Cliente cliente, int posicion){
        if(cliente!=null && posicion!=-1){
            listaClientes.get(posicion).setNombre(cliente.getNombre());
            listaClientes.get(posicion).setApellidos(cliente.getApellidos());
            listaClientes.get(posicion).setConstrasena(cliente.getConstrasena());
            return "Modificado correctamente";
        }//Fin if
        return "Ese usuario no existe";
    }//Fin metodo modificar

    public String getInformacionCliente(int posicion){
        if(posicion!=-1){
            return listaClientes.get(posicion).toString();
        }//Fin if
        else{
            return "No existe";
        }//Fin else
    }//Fin metodo getInformacion

    public ArrayList<Cliente> devolverLista(){
        return listaClientes;
    }//Fin metodo devolverLista

    public void igualarListas(ArrayList<Cliente> lista){
        this.listaClientes=lista;
    }//Fin metodo

    public int buscarUsuario(ArrayList<Cliente> listaClientes, String usuario){
        if(usuario != null){
            for (int i=0; i<listaClientes.size(); i++){
                if (listaClientes.get(i).getUsuario().equalsIgnoreCase(usuario)){
                    return i;
                }//Fin if
            }//Fin for
        }//Fin if
        return -1;
    }//Fin metodo

    public boolean verificarContrasena(ArrayList<Cliente> listaClientes, int posicion, String contrasena){
        if(posicion!=-1 && contrasena!=null){
            listaClientes.get(posicion).getConstrasena().equalsIgnoreCase(contrasena);
            return true;
        }//Fin if
        return false;
    }//Fin metodo

    public String agregarFoto(ArrayList<Cliente> lista, Cliente cliente, int posicion){
        if(cliente!=null && posicion!=-1){
            lista.get(posicion).setDescripcion(cliente.getDescripcion());
            lista.get(posicion).setImgFoto(cliente.getImgFoto());
            return "Foto agregada correctamente";
        }//Fin if
        return "Usuario incorrecto";
    }//Fin metodo modificar

}//Fin clase

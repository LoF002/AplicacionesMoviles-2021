import { StatusBar } from 'expo-status-bar';
import React, { useState } from 'react';
import { Modal, Pressable, Text, View, TextInput, FlatList, TouchableOpacity, Touchable } from 'react-native';
import { globalStyles } from './styles/global';
import { Formik } from "formik";

export default function App(){

  const [modalVisible, setModalVisible] = useState(false);

  const [tareas, setTareas] = useState([
    {nombre: 'Investigacion', fecha: 'sabado 23', curso: 'Videojuegos', key: '1'},
    {nombre: 'Investigacion', fecha: 'sabado 23', curso: 'Videojuegos', key: '2'},
    {nombre: 'Investigacion', fecha: 'sabado 23', curso: 'Videojuegos', key: '3'},
  ]);

  const eliminarTarea = (key) => {
    setTareas((prevTareas) =>{
      return prevTareas.filter(tarea => tarea.key != key)
    });
  }

  const agregarTarea = (tarea) => {
    tarea.key = Math.random().toString();
    setTareas((currentTareas) => {
      return [tarea, ...currentTareas]
    });
    setModalVisible(false);
  }

  return (
    <View style={globalStyles.container}>

      <Text style={globalStyles.title}>Lista de tareas</Text>

      <Modal
        animationType="slide"
        transparent={true}
        visible={modalVisible}
        onRequestClose={() => {
          Alert.alert("Modal has been closed.");
          setModalVisible(!modalVisible);
        }}
      >
        <View style={globalStyles.centeredView}>
          <View style={globalStyles.modalView}>

            <Formik
              initialValues={{ nombre: '', fecha: '', curso: '' }}
              onSubmit={(values, actions) => {
                actions.resetForm();
                agregarTarea(values);
              }}
            >
              {(props) => (
                <View>

                  <Pressable
                    style={globalStyles.button, globalStyles.buttonClose}
                    onPress={() => setModalVisible(!modalVisible)}
                  >
                    <Text style={globalStyles.textStyle}>X</Text>
                  </Pressable>

                  <TextInput
                    style={globalStyles.input}
                    placeholder='Nombre de tarea'
                    onChangeText={props.handleChange('nombre')}
                    value={props.values.nombre}
                  />

                  <TextInput
                    style={globalStyles.input}
                    placeholder='Fecha de Entrega'
                    onChangeText={props.handleChange('fecha')}
                    value={props.values.fecha}
                  />

                  <TextInput
                    style={globalStyles.input}
                    placeholder='Curso'
                    onChangeText={props.handleChange('curso')}
                    value={props.values.curso}
                  />

                  <Pressable
                    style={[globalStyles.button, globalStyles.buttonAdd]}
                    onPress={props.handleSubmit}
                  >
                    <Text style={globalStyles.textStyle}>Agregar</Text>
                  </Pressable>
                </View>
              )}
            </Formik>

          </View>
        </View>
      </Modal>

      <Pressable
        style={[globalStyles.button, globalStyles.buttonOpen]}
        onPress={() => setModalVisible(true)}
      >
        <Text style={globalStyles.textStyle}>Agregar tarea</Text>
      </Pressable>

      <View style={globalStyles.containerList}>
        <FlatList 
          data={tareas}
          renderItem={({ item }) => (
            <TouchableOpacity onPress={() => eliminarTarea(item.key)}>
              <Text style={globalStyles.item}>Nombre: {item.nombre} - Fecha de entrega: {item.fecha} - Curso: {item.curso}</Text>
            </TouchableOpacity>
          )}
        />
      </View>

      <StatusBar style="auto" />
    </View>
  );
}
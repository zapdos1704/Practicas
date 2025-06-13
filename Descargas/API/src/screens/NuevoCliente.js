import { Alert, Platform, StyleSheet, Text, View } from 'react-native'
import React, { useEffect, useState } from 'react'
import { Button, Headline, TextInput } from 'react-native-paper'
import globalStyles from '../styles/GlobalStyles'
import axios from 'axios'
import zxcvbn from 'zxcvbn'

const NuevoCliente = ({ navigation, route }) => {

  const mensaje = () => {
    switch (strength) {
      case 0:
        return 'Contraseña muy vulnerable' + ' Valor de ' + strength
      case 1:
        return 'Contraseña poco vulnerable' + ' Valor de ' + strength
      case 2:
        return 'Contraseña vulnerable' + ' Valor de ' + strength
      case 3:
        return 'Contraseña fuerte' + ' Valor de ' + strength
      case 4:
        return 'Contraseña robusta' + ' Valor de ' + strength
    }
  }
  const { setConsultarApi } = route.params
  const [nombreError, svErrName] = useState(false)
  const [telError, svErrTel] = useState(false)
  const [emailError, svErrEM] = useState(false)
  const [passwdError, svErrPasswd] = useState(false)

  const [nombre, guardarNombre] = useState('')
  const [telefono, guardarTelefono] = useState('')
  const [correo, guardarCorreo] = useState('')
  const [clave, guardarClave] = useState('')
  const [strength, setStrength] = useState(0)

  //Verificar si se quiere editar e Insertar
  useEffect(() => {
    if (route.params.cliente) {
      const { nombre, telefono, correo, clave } = route.params.cliente
      guardarNombre(nombre)
      guardarTelefono(telefono)
      guardarClave(clave)
      guardarCorreo(correo)
      const result = zxcvbn(clave);
      setStrength(result.score);
    }
  }, [])
  const validarEmail = (myEmail) => {

    const validEmail = /^\w+([.-_+]?\w+)@\w+([.-]?\w+)(\.\w{2,10})+$/

    if (validEmail.test(myEmail)) {

      return true

    } else {

      return false

    }
  }

  const cliente = {
    nombre,
    telefono,
    correo,
    clave
  }

  const guardarCliente = async () => {
    if (nombre === '') {
      Alert.alert('Error', 'Debe escribir un nombre válido')
      svErrName(true)
      return
    }
    else if (telefono === '') {
      Alert.alert('Error', 'Debe escribir un teléfono')
      svErrTel(true)
      return
    }
    else if (correo === '' || !validarEmail(correo)) {
      Alert.alert('Error', 'Debe escribir un correo válido')
      svErrEM(true)
      return
    }
    else if (clave.length < 8) {
      Alert.alert('Error', 'La clave debe ser mayor o igual a 8 caracteres')
      svErrPasswd(true)
      return
    }
    console.log('Cliente: ', cliente)

    if (route.params.cliente) {
      const { id } = route.params.cliente
      cliente.id = id
      try {
        if (Platform.OS === 'ios') {
          await axios.put(`http://localhost:5000/clientes/${id}`, cliente)
          console.log('hola')
        }
        else {
          await axios.put(`http://10.0.2.2:5000/clientes/${id}`, cliente)
          console.log('hola')
        }
      }
      catch (error) {
        console.log(error)
      }


    } else {
      try {
        if (Platform.OS === 'ios') {
          await axios.post('http://localhost:5000/clientes', cliente)
          console.log('hola')
        }
        else {
          await axios.post('http://10.0.2.2:5000/clientes', cliente)
          console.log('hola')
        }
      }
      catch (error) {
        console.log(error)
      }

    }

    setConsultarApi(true)
    //redireccionar
    navigation.navigate('Inicio')
    //limpiar controles
    guardarNombre = ('')
    guardarTelefono = ('')
    guardarCorreo = ('')
    guardarClave = ('')
    setStrength = (0)
    //echar a andar el servidor JSON
    //json-server db.json

    //activar la bandera de consultar api

  }

  return (
    <View style={globalStyles.contenedor}>
      <Headline style={globalStyles.titulo}>Añadir Nuevo Cliente</Headline>

      <TextInput
        label='Nombre'
        placeholder='Nombre de Usuario'
        keyboardType='default'
        error={nombreError}
        mode='flat'
        style={styles.input}
        value={nombre}
        onChangeText={(nombre) => guardarNombre(nombre)}
        onBlur={() => svErrName(false)}
      />

      <TextInput
        label='Correo'
        placeholder='Correo Electrónico'
        keyboardType='email-address'
        error={emailError}
        mode='flat'
        style={styles.input}
        value={correo}
        onChangeText={(correo) => guardarCorreo(correo)}
        onBlur={() => svErrEM(false)}
      />

      <TextInput
        label='Contraseña'
        placeholder='Introduzca su contraseña'
        keyboardType='default'
        secureTextEntry={true}
        error={passwdError}
        mode='flat'
        style={styles.input}
        value={clave}
        onChangeText={(clave) => {
          guardarClave(clave)
          const result = zxcvbn(clave);
          setStrength(result.score);
        }}
        onBlur={() => svErrPasswd(false)}
      />
      <Text
      >
        {mensaje()}
      </Text>
      <TextInput
        label='Teléfono'
        placeholder='Número telefónico'
        keyboardType='number-pad'
        error={telError}
        mode='flat'
        style={styles.input}
        value={telefono}
        onChangeText={(telefono) => guardarTelefono(telefono)}
        onBlur={() => svErrTel(false)}
      />

      <Button
        mode='contained'
        style={{ borderRadius: 5, marginTop: 30 }}
        icon='lead-pencil'
        onPress={() => {

          if (strength < 3) {
            Alert.alert(
              'Error en la contraseña',
              'Contrseña poco segura, intente con una contraseña un poco mas segura'
            )
          }
          guardarCliente()
        }}
      >
        Gaurdar Cliente
      </Button>

    </View>
  )
}

const styles = StyleSheet.create({
  input: {
    marginBottom: 20,
    backgroundColor: 'transparent'
  }
})

export default NuevoCliente
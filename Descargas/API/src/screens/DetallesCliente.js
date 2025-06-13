import { Alert, Platform, StyleSheet, Text, View } from 'react-native'
import React from 'react'
import globalStyles from '../styles/GlobalStyles'
import { Button, FAB, Headline, Subheading } from 'react-native-paper'
import Icon from 'react-native-vector-icons/Ionicons'
import axios from 'axios'

const DetallesCliente = ({ route, navigation }) => {
  const { setConsultarApi } = route.params
  const confirmarEliminarCliente = () => {
    Alert.alert(
      '¿Deseas eliminar este cliente?',
      'Esta acción es irreversible',
      [
        { text: 'Si, eliminar', onPress: () => EliminarCliente() },
        { text: 'Cancelar', style: 'cancel' }
      ]
    )
  }

  const EliminarCliente = async () => {
    try {
      if (Platform.OS === 'ios') {
        datos = await axios.delete(`http://localhost:5000/clientes/${id}`)
      }
      else {
        datos = await axios.delete(`http://10.0.2.2:5000/clientes/${id}`)
      }
    }
    catch (error) {
      console.log(error)
    }
    setConsultarApi(true)
    navigation.navigate('Inicio')


  }
  const { nombre, telefono, correo, id } = route.params.item
  return (
    <View style={globalStyles.contenedor}>
      <Headline style={globalStyles.titulo}>{nombre}</Headline>
      <Text style={styles.texto}>Telefono: <Subheading>{telefono}</Subheading></Text>
      <Text style={styles.texto}>Correo: <Subheading>{correo}</Subheading></Text>

      <Button
        node='contained'
        item={() => { <Icon name="trash" size={30} color='#FFF' /> }}
        style={styles.btn}
        onPress={() => confirmarEliminarCliente()}
      >
        Eliminar cliente
      </Button>

      <FAB
        icon={
          () => (<Icon name="pencil" size={25} color="red" />)
        }
        style={globalStyles.fab}
        onPress={() => navigation.navigate('NuevoCliente', { setConsultarApi, cliente: route.params.item })}
      />
    </View>
  )
}

const styles = StyleSheet.create({
  texto: {
    marginBottom: 20,
    fontSize: 15
  },
  btn: {
    borderRadius: 10,
    backgroundColor: 'red'
  }
})

export default DetallesCliente


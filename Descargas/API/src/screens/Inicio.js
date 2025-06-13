import { FlatList, StyleSheet, Text, View } from 'react-native'
import React, { useEffect, useState } from 'react'
import { Button, FAB, Headline, List } from 'react-native-paper'
import axios from 'axios'
import Icon from 'react-native-vector-icons/Ionicons';
import globalStyles from '../styles/GlobalStyles';


const Inicio = ({ navigation }) => {
  //guarda los clientes
  const [clientes, guardarClientes] = useState([])
  const [consultarApi, setConsultarApi] = useState(true)
  //leer datos desde el servidor
  useEffect(() => {
    const obtenerCLientesAPI = async () => {
      try {
        if (Platform.OS === 'ios') {
          datos = await axios.get('http://localhost:5000/clientes')
        }
        else {
          datos = await axios.get('http://10.0.2.2:5000/clientes')
        }
        guardarClientes(datos.data)
        setConsultarApi(false)
      }
      catch (error) {
        console.log(error)
      }
    }
    if (consultarApi) {
      obtenerCLientesAPI()
    }

  }, [consultarApi])

  return (
    <View style={globalStyles.contenedor}>
      <Headline style={globalStyles.titulo}>
        {clientes.length > 0 ? 'Lista de clientes' : 'Aun no hay clientes'}
      </Headline>
      <FlatList
        data={clientes}
        keyExtractor={item => (item.id).toString()}
        renderItem={({ item }) => (
          <List.Item
            title={item.nombre}
            description={item.correo}
            onPress={() => navigation.navigate('DetallesCliente', { item, setConsultarApi })}
          />
        )}
      />

      <FAB
        icon={
          () => (<Icon name="add-circle-outline" size={25} color="red" />)
        }
        style={styles.fab}
        onPress={() => navigation.navigate('NuevoCliente', { setConsultarApi })}
      />
    </View>
  )
}

const styles = StyleSheet.create({
  fab: {
    position: 'absolute',
    margin: 20,
    right: 0,
    bottom: 30
  }
})

export default Inicio
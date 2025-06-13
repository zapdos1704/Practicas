import 'react-native-gesture-handler'
import React from 'react'
import { NavigationContainer } from '@react-navigation/native'
import { StyleSheet } from 'react-native'
import { createStackNavigator } from '@react-navigation/stack'
import { DefaultTheme, Provider as PaperProvider } from 'react-native-paper'

import Inicio from './src/screens/Inicio'
import NuevoCliente from './src/screens/NuevoCliente'
import DetallesCliente from './src/screens/DetallesCliente'


const Stack = createStackNavigator()
const theme = {
  ...DefaultTheme,
  colors: {
    ...DefaultTheme.colors,
    primary: '#1774F2',
    accent: '#655BF'
  }
}

const App = () => {
  return (
    <PaperProvider>
    <NavigationContainer>
      <Stack.Navigator
        initialRouteName='Inicio'
        screenOptions={{
          headerStyle: {
            backgroundColor: theme.colors.primary
          },
          headerTintColor: theme.colors.surface,
          headerTitleStyle: {
            fontWeight: 'bold'
          }
        }}
      >
        
        <Stack.Screen
            name = 'Inicio'
            component = {Inicio}
            options = {{
              headerTitleAlign: 'center'
            }}
        />

        <Stack.Screen
            name = 'NuevoCliente'
            component = {NuevoCliente}
            options = {{
              headerTitleAlign: 'center'
            }}
        />

        <Stack.Screen
            name = 'DetallesCliente'
            component = {DetallesCliente}
            options = {{
              headerTitleAlign: 'center'
            }}
        />

      </Stack.Navigator>
    </NavigationContainer>
    </PaperProvider>
  )
}

const style = StyleSheet.create({

})

export default App

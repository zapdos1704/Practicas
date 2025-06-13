import 'react-native-gesture-handler'
import React from 'react'
import Navigation from './src/components/Navigation'
import { NavigationContainer } from '@react-navigation/native'
import Inicio from './src/components/Inicio'

const App = () => {
  return (
    <NavigationContainer>
      <Inicio />
      <Navigation />
    </NavigationContainer>
  )
}

export default App

import React from 'react';
import { StyleSheet, View, Text, Image, TouchableOpacity, ImageBackground} from 'react-native';
import { YellowBox } from 'react-native';
YellowBox.ignoreWarnings(['Remote debugger']);
// Creating Login Screen
const LoginScreen = props => {
  return (
    <ImageBackground source={require('./image/images6.png')} style={styles.imagebg}>
    <View style={styles.mainBody}>
      <View style={{ alignItems: 'center' }}>
        {<Image
          source={require('./image/Logo.png')}
          style={{
            width: '100%',
            height: 200,
            resizeMode: 'contain',
            margin: 30,
          }}
        /> }
        
        <Text
          style={{
            fontSize: 40,
            color: 'yellow',
            fontWeight: 'bold',
            paddingVertical: 20,
          }}>
         
        </Text>
      </View>
      
      <TouchableOpacity
        style={styles.buttonStyle}
        activeOpacity={0.5}
        onPress={() =>
          props.navigation.navigate('DrawerNavigationRoutes', {
            login: 'guest',
          })
        }>
        <Text style={styles.buttonTextStyle}> LOGIN</Text>
      </TouchableOpacity>
      
    </View>
    </ImageBackground>
    
  );
};
export default LoginScreen;

const styles = StyleSheet.create({
  
    
  
  buttonStyle: {
    backgroundColor: '#FFBF00',
    borderWidth: 0,
    color: '#FFFFFF',
    borderColor: '#000',
    height: 40 ,
    alignItems: 'center',
    borderRadius: 10,
    marginLeft: 35,
    marginRight: 35,
    marginTop: 20,
    marginBottom: 20
  },
  
  buttonTextStyle: {
    color: '#000',
    paddingVertical: 8,
    fontSize: 16,
  },
  imagebg: {
    flex: 1,
    alignItems:'center',
    justifyContent:'center'

  },
 
});
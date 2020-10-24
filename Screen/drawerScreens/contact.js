import React, { Component } from 'react';
import { Text, StyleSheet, View, Linking, Platform, TouchableOpacity, ImageBackground ,Image,Button} from 'react-native';
import { color } from 'react-native-reanimated';


export default class App extends Component {
  
  makeCall = () => {

    let phoneNumber = '';

    if (Platform.OS === 'android') {
      phoneNumber = 'tel:${ 010 228 7089}';
    } else {
      phoneNumber = 'telprompt:${}';
    }

    Linking.openURL(phoneNumber);
  };

  render() {
    let {container, cardText, card, cardImage} = styles
    return (
      
      <View style={styles.container} >
      <TouchableOpacity style={card}>
        <Image style={cardImage} source={{uri: 'http://www.hops.fi/img/image.php?id=46140'}}/>
        
        <Text style={styles.header}>

</Text>
      
        <TouchableOpacity 
      onPress={() => Linking.openURL('http://www.hops.fi/en/apartments')} style={styles.touchableButton}>
  <Text style={styles.TextStyle}> Check More Info</Text>
</TouchableOpacity>
        
      </TouchableOpacity>
       
       
        <TouchableOpacity onPress={this.makeCall} activeOpacity={0.7} style={styles.touchableButton} >
          <Text style={styles.TextStyle}> HOPS CONTACT</Text>
        </TouchableOpacity>
      
        
    </View>
    
      
    
    );
  }
}


const styles = StyleSheet.create(
  {
    container: {
      flex: 1,
      justifyContent: 'center',
      alignItems: 'center',
     
      backgroundColor:'#90908A'
    },
    touchableButton: {
      width: '80%',
      padding: 10,
      marginBottom:20,
      backgroundColor: '#FFBF00',
      borderRadius:20,
      
    },
    TextStyle: {
      color: '#fff',
      fontSize: 18,
      textAlign: 'center',
    },
    cardText: {
      fontSize:16,
      padding: 10 ,
      color: '#000',
      alignItems:"center",
      justifyContent:'space-evenly'
    },
    card: {
     
      marginBottom: 10,
      marginLeft: '2%',
      alignItems:'center',
      borderRadius:50,
      justifyContent:'space-evenly',

  
      
      width: '96%',
      shadowColor: '#000',
      shadowOpacity: 20,
      shadowOffset: {
        width: 10,
        height: 3
      }
      },
      cardImage: {
        width: '110%',
        height: 250,
        resizeMode: 'cover',
        borderRadius:30
      
    },
    

  });
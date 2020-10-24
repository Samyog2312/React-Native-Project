import React, {PureComponent} from 'react'

import {StyleSheet, Text, View, TouchableOpacity, Image, Button,navigation, ScrollView, ImageBackground} from 'react-native'
import { color } from 'react-native-reanimated';

import { createStackNavigator } from 'react-navigation';
export default class contact extends PureComponent {
render () {
  let {container ,card, cardImage} = styles
  return (
    <View style={container}>
      <ImageBackground
        style={{ flex: 1 }}
        //We are using online image to set background
        source={{
          uri:
            'https://a.rgbimg.com/users/r/ro/rosebfischer/300/qzEYEnu.jpg',
        }}
        >
      <ScrollView>
      <TouchableOpacity style={card}>
        <Image style={cardImage} source={{uri: 'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/laundry-room-with-a-washing-machine-royalty-free-image-1586287137.jpg'}}/>
        
        <Text style={styles.header}>Book Your laundry</Text>
      
        <Button 
          title="Book Time"
          color="#000"
          borderRadius='40'
        
          
          onPress={() => this.props.navigation.navigate('SettingsScreen')}
        />
       <Button
          title="Booked Time"
          color="#000"
          
          
          onPress={() => this.props.navigation.navigate('BookTime')} color="#000"/>
        
      </TouchableOpacity>
       
        
    
      <TouchableOpacity style={card}>
        <Image style={cardImage} source={{uri: 'https://www.nicepng.com/png/detail/360-3607244_complaint-on-the-quality-of-the-product-emprediem.png'}}/>
        
        <Text style={styles.header}>Report A Problem</Text>
        <Button
          title="Report"
          color="#000"
          onPress={() => this.props.navigation.navigate('Report')}
        />
      </TouchableOpacity>

      <TouchableOpacity style={card}>
        <Image style={cardImage} source={{uri: 'https://www.hotellikainuu.com/wp-content/uploads/2019/11/Sauna-1-scaled.jpg'}}/>
        <Text style={styles.header}>Book Your Sauna</Text>
        <Button
          title="Book a Time "
          color="#000"
          onPress={() => this.props.navigation.navigate('GET')  }
        />
      </TouchableOpacity>

      
 
      </ScrollView>
      </ImageBackground>
    </View>
    
    
  )
  }
}

const styles = StyleSheet.create({
  container:{
    flex:3,
    marginTop:20
    
   
  },
  header: {
    fontSize: 18,
    fontWeight: 'bold',
    margin: 15,
    justifyContent:"center",
    color:'#fff'
  },
  cardText: {
    fontSize:18,
    padding: 10 ,
    color: '#eee',
    alignItems:"center",
    justifyContent:'space-evenly'
  },
  card: {
   
    marginBottom: 10,
    marginLeft: '2%',
    alignItems:'center',
    borderRadius:10,
    justifyContent:'space-evenly',

    
    width: '96%',
    shadowColor: '#000',
    shadowOpacity: 3,
    shadowOffset: {
      width: 10,
      height: 3
    }
    },
    cardImage: {
      width: '100%',
      height: 200,
      resizeMode: 'cover',
      borderRadius:30
    
  },
  
  
 
  
 
})
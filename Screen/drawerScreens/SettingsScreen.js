import React, { useState, useEffect } from "react";
import { Button, TextInput, View, Text, FlatList, StyleSheet, Alert, ActivityIndicator, Image, TouchableOpacity } from 'react-native';
// import { TextInput } from "react-native-gesture-handler";

const Add = () => {
  const [customerName, setcustomerName] = useState([]);
  const [newCustomerName, setnewCustomerName] = useState('');
  const [newCustomerDate, setnewCustomerDate] = useState('');
  const [newCustomerTime, setnewCustomerTime] = useState('');

  const customerInputHandler = (enteredText) => {
    setnewCustomerName(enteredText);
  }

  const dateInputHandler = (enteredText) => {
    setnewCustomerDate(enteredText);
  }
  const timeInputHandler = (enteredText) => {
    setnewCustomerTime(enteredText);
  }
  const addCustomerHandler = () => {
    // setcustomerName(customerName=>[...customerName, {breed:newCustomerDate, weight:newCustomerTime}]);
    setcustomerName([{ name: newCustomerName, date: newCustomerDate, time: newCustomerTime }]);
    saveCustomer();
  }


  async function saveCustomer() {
    const response = await fetch("https://samproject-291218.appspot.com/rest/customerservice/addjsoncustomer",
      {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ name: newCustomerName, date: newCustomerDate, time: newCustomerTime })
      });
    Alert.alert("Your Booking was Successful 😊 (Its Valid For 2 Hours)");
    // const responseData = await response.json();
    // console.log(responseData);
    // setcustomerName(customerName=>[...customerName, responseData]);
  }

  //   useEffect(() => {
  //     if (isLoading==true){
  //       setLoading(false);
  //       addData();
  //     }
  //   });

  return (
    
      <View style={styles.inputcontainer}>
         <Image
      source={require('../image/Logo.png')}
      style={{
        width: '30%',
        height: 40,
        resizeMode: 'contain',
        margin: 2,
      }}
    />
        <TextInput placeholder="Name"
          style={styles.inputStyle}
          onChangeText={customerInputHandler}
          value={newCustomerName} />
        <TextInput placeholder="--/--/----"
          style={styles.inputStyle}
          onChangeText={dateInputHandler}
          value={newCustomerDate} />
        <TextInput placeholder="00:00"
          style={styles.inputStyle}
          onChangeText={timeInputHandler}
          value={newCustomerTime} />
      
      <View style={styles.buttoncontainer}>
        <Button title="Add" onPress={addCustomerHandler} color="#FFBF00"/>
      </View>

   
    
          
          
    </View>
  );
};

const styles = StyleSheet.create({
  listItem: {
    borderWidth: 2,
    borderColor: '#000',
    backgroundColor: '#000',
  },
  inputcontainer: {
    flex: 1,
    backgroundColor: '#90908A',
    alignItems: 'center',
    justifyContent: 'center',
    justifyContent: 'space-evenly'

  },
  buttoncontainer: {
    backgroundColor: '#000',
    alignItems: 'center',
    justifyContent: 'center',
    marginBottom:100,
  
  },
  inputStyle: {
    width: 300,
    height: 50,
    padding: 10,
    borderWidth: 2,
    borderColor: '#FFBF00',
    borderRadius:20,
    backgroundColor:'#fff'
    
  },

});

export default Add;
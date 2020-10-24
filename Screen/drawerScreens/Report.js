import React, { useState, useEffect } from "react";
import { Button, TextInput, View, Text, FlatList, StyleSheet, Alert, ActivityIndicator, Image, TouchableOpacity } from 'react-native';

// import { TextInput } from "react-native-gesture-handler";

const Add = () => {
  const [reportName, setreportName] = useState([]);
  const [newReportName, setnewReportName] = useState('');
  const [newReportEmail, setnewReportEmail] = useState('');
  const [newReportProblem, setnewReportProblem] = useState('');

  const NameInputHandler = (enteredText) => {
    setnewReportName(enteredText);
  }

  const EmailInputHandler = (enteredText) => {
    setnewReportEmail(enteredText);
  }
  const ProblemInputHandler = (enteredText) => {
    setnewReportProblem(enteredText);
  }
  const addReportHandler = () => {
    // setcustomerName(customerName=>[...customerName, {breed:newCustomerDate, weight:newCustomerTime}]);
    setreportName([{ name: newReportName, email: newReportEmail, problem: newReportProblem }]);
    saveReport();
  }
  


  async function saveReport() {
    const response = await fetch("https://samproject-291218.appspot.com/rest/reportservice/addjsonreport",
      {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ name: newReportName, email: newReportEmail, problem: newReportProblem })
      });
    Alert.alert("Submit We Will Get Back To You Soon! ✔️ ") ;
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
          onChangeText={NameInputHandler}
          value={newReportName} />
        <TextInput placeholder="abc-----@gmail.com"
          style={styles.inputStyle}
          onChangeText={EmailInputHandler}
          value={newReportEmail} />
           <View style={styles.problemcontainer}>
        <TextInput placeholder="problem"
          style={styles.inputStyle}
          onChangeText={ProblemInputHandler}
          value={newReportProblem} />
          </View>
      
      <View style={styles.buttoncontainer}>
        <Button title="Submit" onPress={addReportHandler} color="#FFBF00"/>
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
    justifyContent: 'space-evenly',

  },
  buttoncontainer: {
   
    backgroundColor: '#fff',
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
  problemcontainer:{
   alignItems:'stretch',
  },

});

export default Add;
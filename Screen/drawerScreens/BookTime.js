import React, {useState, useEffect} from 'react';
 
// import all the components we are going to use
import {
 SafeAreaView,
 Text,
 StyleSheet,
 View,
 FlatList,
 TextInput,
} from 'react-native';
 
const App = () => {
 const [search, setSearch] = useState('');
 const [filteredDataSource, setFilteredDataSource] = useState([]);
 const [masterDataSource, setMasterDataSource] = useState([]);
 
 useEffect(() => {
 fetch('https://samproject-291218.appspot.com/rest/customerservice/getAll')
 .then((response) => response.json())
 .then((responseJson) => {
 setFilteredDataSource(responseJson);
 setMasterDataSource(responseJson);
 })
 .catch((error) => {
 console.error(error);
 });
 }, []);
 
 const searchFilterFunction = (text) => {
 // Check if searched text is not blank
 if (text) {
 // Inserted text is not blank
 // Filter the masterDataSource
 // Update FilteredDataSource
 const newData = masterDataSource.filter(
 function (item) {
 const itemData = item.name
 ? item.name.toUpperCase()
 : ''.toUpperCase();
 const textData = text.toUpperCase();
 return itemData.indexOf(textData) > -1;
 });
 setFilteredDataSource(newData);
 setSearch(text);
 } else {
 // Inserted text is blank
 // Update FilteredDataSource with masterDataSource
 setFilteredDataSource(masterDataSource);
 setSearch(text);
 }
 };
 
 const ItemView = ({item}) => {
 return (
 // Flat List Item
 <Text
 style={styles.itemStyle}
 onPress={() => getItem(item)}>
{'Name :'}
 {item.name}
 {'   Date :'}
 {item.date.toUpperCase()}
 {'   Time :'}
 {item.time.toUpperCase() }
 {'     ✔️'}
 </Text>
 );
 };
 
 const ItemSeparatorView = () => {
 return (
 // Flat List Item Separator
 <View
 style={{
 height: 0.5,
 width: '100%',
 backgroundColor: '#C8C8C8',
 }}
 />
 );
 };
 
 const getItem = (item) => {
 // Function for click on an item
 alert('Name : ' + item.name +  'Date : ' + item.date + 'Time : ' +item.time +'✔️');
 };
 
 return (
 <SafeAreaView style={{flex: 1}}>
 <View style={styles.container}>
 <TextInput
 style={styles.textInputStyle}
 onChangeText={(text) => searchFilterFunction(text)}
 value={search}
 underlineColorAndroname="transparent"
 placeholder="Search Here"
 
 />
 <FlatList
 data={filteredDataSource}
 keyExtractor={(_item, index) => index.toString()}
 ItemSeparatorComponent={ItemSeparatorView}
 renderItem={ItemView}
 />
 </View>
 </SafeAreaView>
 );
};
 
const styles = StyleSheet.create({
 container: {
 backgroundColor: '#90908A',
 },
 itemStyle: {
 padding: 5,
 height: 30,
 borderWidth: 2,
 margin: 5,
 borderColor: '#FFBF00',
 justifyContent:'space-evenly',
 borderRadius:20
 },
 textInputStyle: {
   
 height: 40,
 marginTop:80,
 borderWidth: 3,
 paddingLeft:10,
 margin: 9,
 borderColor: '#000',
 backgroundColor: '#FFFFFF',
 marginTop:20,
 borderRadius:20,
 color:'#FFBF00'
 
 },
});
 
export default App;
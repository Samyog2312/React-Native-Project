
import React, { useEffect, useState } from 'react';
import { View, StyleSheet, Text, Image } from 'react-native';
import { YellowBox } from 'react-native';
YellowBox.ignoreWarnings(['Remote debugger']);
const CustomSidebarMenu = props => {
  let [loginAs, setLoginAs] = useState('');

  useEffect(() => {
    setLoginAs(props.navigation.getParam('login', 'defaultValue'));
  }, []);

 

  let itemsGuest = [
    {
      navOptionName: '🏠 Home',
      screenToNavigate: 'HomeScreen',
    },
    {
      navOptionName: '👔 Laundary',
      screenToNavigate: 'SettingsScreen',
    },
    {
      navOptionName: '📜 Report',
      screenToNavigate: 'Report',
    },
   
    {
      navOptionName: '💁🏾‍♂️ LocalInfo ',
      screenToNavigate: 'LocalInfo',
    },
    
    {
      navOptionName: '🔙 Logout',
      screenToNavigate: 'logout',
    },
  ];

  const handleClick = (index, screenToNavigate) => {
    if (screenToNavigate == 'logout') {
      props.navigation.toggleDrawer();
      props.navigation.navigate('Login');
    } else if (screenToNavigate == 'ChangeOptionGuest') {
      props.navigation.toggleDrawer();
      setLoginAs('guest');
    } else if (screenToNavigate == 'ChangeOptionUser') {
      props.navigation.toggleDrawer();
      setLoginAs('user');
    } else {
      props.navigation.toggleDrawer();
      global.currentScreenIndex = screenToNavigate;
      props.navigation.navigate(screenToNavigate);
    }
  };
  return (
    <View style={stylesSidebar.sideMenuContainer}>
      
      <View style={stylesSidebar.profileHeader}>
        <View style={stylesSidebar.profileHeaderPicCircle}>
        <Image
          source={require('../image/Logo.png')}
          style={{
            width: '140%',
            height: 200,
            resizeMode: 'contain',
            marginLeft:20,
          }}
        />
        </View>
       
      </View>
      <View style={stylesSidebar.profileHeaderLine} />
      <View style={{ width: '100%', flex: 1 }}>
        {(loginAs === 'user' ? itemsUser : itemsGuest).map((item, key) => (
          <View
            style={{
              flexDirection: 'row',
              alignItems: 'center',
              padding: 20,
              color: '#FFBF00',
              backgroundColor:
                global.currentScreenIndex === item.screenToNavigate
                  ? '#FFBF00'
                  : '#FFBF00',
            }}
            key={key}
            onStartShouldSetResponder={() =>
              handleClick(key, item.screenToNavigate)
            }>
            <Text style={{ fontSize:15, color: '#000', fontWeight:'bold' }}>
              {item.navOptionName}
            </Text>
          </View>
        ))}
      </View>
    </View>
  );
};

const stylesSidebar = StyleSheet.create({
  sideMenuContainer: {
    width: '100%',
    height: '100%',
    backgroundColor: '#FFBF00',
    paddingTop: 40,
    color: '#FFBF00',
  },
  profileHeader: {
    flexDirection: 'row',
    backgroundColor: '#FFBF00',
    padding: 15,
    textAlign: 'center',
  },
  profileHeaderPicCircle: {
    width: 60,
    height: 60,
    borderRadius: 60 / 2,
    color: 'white',
    backgroundColor: '#FFBF00',
    textAlign: 'center',
    justifyContent: 'center',
    alignItems: 'center',
  },
  
  profileHeaderLine: {
    height: 1,
    marginHorizontal: 20,
    backgroundColor: '#000',
    marginTop: 15,
    marginBottom: 10,
    padding:1
  }
});
export default CustomSidebarMenu;
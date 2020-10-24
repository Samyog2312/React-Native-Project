import React from 'react';
import { View, Image, TouchableOpacity , Button} from 'react-native';
import { YellowBox } from 'react-native';
YellowBox.ignoreWarnings(['Remote debugger']);
const NavigationDrawerHeader = props => {
  const toggleDrawer = () => {
    props.navigationProps.toggleDrawer();
  };

  return (
    <View style={{ flexDirection: 'row' }}>
      <TouchableOpacity onPress={toggleDrawer}>
        <Image
          source={{
            uri:
              'https://img.icons8.com/nolan/2x/menu.png',
          }}
          style={{ width: 30, height: 30, marginLeft: 15 }}
        />
        
      </TouchableOpacity>
    </View>
  );
};
export default NavigationDrawerHeader;
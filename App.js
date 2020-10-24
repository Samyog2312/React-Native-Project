
import React from 'react';
import { YellowBox } from 'react-native';
YellowBox.ignoreWarnings(['Remote debugger']);
//Import Navigators from React Navigation
import { createAppContainer } from 'react-navigation';
import { createStackNavigator } from 'react-navigation-stack';
import { createDrawerNavigator} from 'react-navigation-drawer';

//Import External Screens
import LoginScreen from './Screen/LoginScreen';
import HomeScreen from './Screen/drawerScreens/HomeScreen';
import SettingsScreen from './Screen/drawerScreens/SettingsScreen';
import contact from './Screen/drawerScreens/contact';
import Report from './Screen/drawerScreens/Report';
import CustomSidebarMenu from './Screen/Components/CustomSidebarMenu';
import NavigationDrawerHeader from './Screen/Components/NavigationDrawerHeader';
import BookTime from './Screen/drawerScreens/BookTime';



const FirstActivity_StackNavigator = createStackNavigator({
  First: {
    screen: HomeScreen,
    navigationOptions: ({ navigation }) => ({
      title: 'Home',
      headerLeft: ()=> <NavigationDrawerHeader navigationProps={navigation} />,
      headerStyle: {
        backgroundColor: '#FFBF00',
      
      },
      headerTintColor: '#90908A',
    }),
  },
});

const SecondActivity_StackNavigator = createStackNavigator({
  First: {
    screen: SettingsScreen,
    navigationOptions: ({ navigation }) => ({
      title: 'Laundry',
      headerLeft: ()=> <NavigationDrawerHeader navigationProps={navigation} />,
      headerStyle: {
        backgroundColor: '#FFBF00',
      },
      headerTintColor: '#90908A',
    }),
  },
});

const ThirdActivity_StackNavigator = createStackNavigator({
  First: {
    screen: Report,
    navigationOptions: ({ navigation }) => ({
      title: 'Report',
      headerLeft: ()=> <NavigationDrawerHeader navigationProps={navigation} />,
      headerStyle: {
        backgroundColor: '#FFBF00',
      },
      headerTintColor: '#90908A',
    }),
  },
});
const forthActivity_StackNavigator = createStackNavigator({
  First: {
    screen: contact,
    navigationOptions: ({ navigation }) => ({
      title: 'Localinfo',
      headerLeft: ()=> <NavigationDrawerHeader navigationProps={navigation} />,
      headerStyle: {
        backgroundColor: '#FFBF00',
      },
      headerTintColor: '#90908A',
    }),
  },
});
const fifthActivity_StackNavigator = createStackNavigator({
  First: {
    screen: BookTime,
    navigationOptions: ({ navigation }) => ({
      title:"Booked Time",
      headerLeft: ()=> <NavigationDrawerHeader navigationProps={navigation} />,
      headerStyle: {
        backgroundColor: '#FFBF00',
      },
      headerTintColor: '#90908A',
    }),
  },
});

const DrawerNavigationRoutes = createDrawerNavigator({
  HomeScreen: {
    screen: FirstActivity_StackNavigator,
    navigationOptions: {
      drawerLabel: 'Home ',
    },
  },
  SettingsScreen: {
    screen: SecondActivity_StackNavigator,
    navigationOptions: {
      drawerLabel: 'Setting Screen',
    },
  },
  Report : {
    screen: ThirdActivity_StackNavigator,
    navigationOptions: {
      drawerLabel: 'Report',
    },
  },
  LocalInfo : {
    screen: forthActivity_StackNavigator,
    navigationOptions: {
      drawerLabel: 'LocalInfo',
      
    },
  },
  BookTime : {
    screen: fifthActivity_StackNavigator,
    navigationOptions: {
      drawerLabel: 'Booked Time',
      
    },
  },
},
{
    contentComponent: CustomSidebarMenu,
    drawerOpenRoute: 'DrawerOpen',
    drawerCloseRoute: 'DrawerClose',
    drawerToggleRoute: 'DrawerToggle'
});

const App = createStackNavigator({
  //Stack Navigator for Login and Sign up Screen
  Login: {
    screen:LoginScreen ,
    navigationOptions: {
     
      headerStyle: {
        backgroundColor: '#5F5E59',
        alignItems:'center'
      },
      headerTintColor: '#000',
    },
  },
  DrawerNavigationRoutes: {
    screen: DrawerNavigationRoutes,
    navigationOptions: {
      headerShown: false,
    },
  },
 
    
        });
export default createAppContainer(App);
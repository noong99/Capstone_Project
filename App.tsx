import React from 'react'
import { StatusBar } from 'expo-status-bar';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import { Platform, Dimensions, SafeAreaView, StyleSheet, Text, View, TouchableOpacity } from 'react-native';
import camera2 from './screens/camera/camera2';


const {width, height} = Dimensions.get('window')
const Stack = createStackNavigator();

export default function App() {
  return (

  <View style={[styles.view]}>


    <SafeAreaView style={[styles.SafeAreaView]}>
      
      <View style={[styles.headerbox, {borderWidth:5, borderColor: '#C4E0B2'}]}>
      <Text style={[styles.text]}>BugWiki</Text>
      </View>
    </SafeAreaView>

    <View style={[{flex :0.3, marginTop:20, flexDirection : 'row', justifyContent: 'space-evenly'}]}>
      <TouchableOpacity style={[styles.box, styles.border]}><Text style={[styles.text]}>카메라</Text></TouchableOpacity> 
      <TouchableOpacity style={[styles.box, styles.border]}><Text style={[styles.text]}>마이페이지</Text></TouchableOpacity> 
    </View>
    
    <TouchableOpacity style={[{height :20, width: width/2,alignItems :'center'}]}>
      <Text style={[styles.underline,{fontSize:15, color:'#B8B7AF'}]}>사진을 찍지 못하셨나요?</Text>
      </TouchableOpacity>
    
    <View style={[{flex :0.3, marginTop:30,flexDirection : 'row',justifyContent: 'space-evenly'}]}>
      <TouchableOpacity style={[styles.box, styles.border]}><Text style={[styles.text]}>Q&A</Text></TouchableOpacity> 
      <TouchableOpacity style={[styles.box, styles.border]}><Text style={[styles.text]}>내 주변 {'\n'}방역업체</Text></TouchableOpacity> 

      <StatusBar style="auto" />
    </View>  
    
  </View> 
  );
}

const styles = StyleSheet.create({

  view : {flex:1, backgroundColor:'#fff'},
  text: {margin :20, fontSize:20, color:'black',textAlign:'center'},
  SafeAreaView: {backgrouondColor :'#C4E0B2', flex :0.2, alignItems :'center',padding:Platform.select({ios:10, android:20})},
  box :{height :200, width:150,  backgroundColor : '#F8FBF5', borderRadius :20, margin:5, justifyContent: 'center'},
  headerbox :{height :100, width:'90%',  backgroundColor : '#fff', marginTop:20, borderRadius :20, alignItems :'center',justifyContent: 'center'},
  border:{borderWidth:5, borderColor: '#C4E0B2', borderStyle : 'dotted'},
  underline: {textDecorationLine: 'underline'}
  
  
});
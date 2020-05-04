/*
直接更新state的多个方法的对象
*/

import {
  RECEIVE_ADDRESS,
  RECEIVE_CATEGORYS,
  RECEIVE_SHOP_LIST,
  RECEIVE_USER_INFO,
  RECEIVE_GOOD_LIST,
  RECEIVE_RATING_LIST,
  RECEIVE_SHOP_INFO,
  RECEIVE_SEARCH_SHOPS,
  SET_GEOHASH
} from './mutation-type'


export default {
  [RECEIVE_ADDRESS] (state,{address}) {
    state.address = address
  },
  [RECEIVE_CATEGORYS](state, {categorys}){
    state.categorys = categorys;
  },
  [RECEIVE_SHOP_LIST](state, {shopList}){
    state.shopsList = shopList;
  },
  [RECEIVE_USER_INFO](state, {userInfo}){
    state.userInfo = userInfo;
  },
  [RECEIVE_GOOD_LIST](state, {goodList}){
    state.goodList = goodList;
  },
  [RECEIVE_RATING_LIST](state, {ratingList}){
    state.ratingList = ratingList;
  },
  [RECEIVE_SHOP_INFO](state, {shopInfo}){
    state.shopInfo = shopInfo;
  },
  [RECEIVE_SEARCH_SHOPS](state, {searchShops}){
    state.searchShops = searchShops;
  },
  [SET_GEOHASH](state, {latitude, longitude}){
    state.latitude = latitude;
    state.longitude = longitude;
  }
}

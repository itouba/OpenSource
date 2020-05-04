/*
Action:通过操作mutation间接更新state的多个方法的对象
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

import {
  reqAddress,
  reqCategorys,
  reqShopList
} from '../api'

export default {
  // 异步获取地址
  async getAddress({commit,state}) {
    // 发送异步ajax请求
    const geohash = state.latitude + ',' + state.longitude
    const result = await reqAddress(geohash)
    // 提交一个mutation
    if (result.code===0) {
      const address = result.data
      commit(RECEIVE_ADDRESS,{address})
    }
  },
  async getCategorys({commit}){
    //提交ajax请求获得数据
    const result = await reqCategorys();
    //提交mutation更新数据
    if (result.code == 0) {
      commit(RECEIVE_CATEGORYS, {categorys: result.data});
    }
  },
  async getShopList({commit, state}){
    //提交ajax请求获得数据
    const result = await reqShopList(state.latitude, state.longitude);
    //提交mutation更新数据
    if (result.code == 0) {
      commit(RECEIVE_SHOP_LIST, {shopList: result.data});
    }
  },
  setGeohash({commit},  {latitude, longitude}){
    commit(SET_GEOHASH, {latitude, longitude})
  }
}

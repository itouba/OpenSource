/*
与后台交互模块 （依赖已封装的ajax函数）
包含n个接口请求函数的模块，函数的返回值是promise对象
 */
import ajax from './ajax'

const BASE_URL = '/api'

export const reqUserInfo = () => ajax(BASE_URL + '/userinfo');

//注意使用键盘左上角向上的点
export const reqAddress = (geohash) => ajax(BASE_URL + `/position/${geohash}`);

export const reqCategorys = () => ajax(BASE_URL + '/index_category');

export const reqShopList = (latitude, longitude) => ajax(BASE_URL + '/shops', {latitude, longitude});

## 创建项目

### 01命令

~~~
npm install -g vue-cli
vue init webpack vue_demo
cd vue_demo
npm install
npm run dev
访问：http://localhost:8080
~~~



## js语法

### 01 对象复制

~~~javascript
//Object类型

var obj1 = { a: 1 };
var obj2 = Object.assign({}, obj1);

// 通过 for in 循环赋值
var obj1={ a: 1, b: { c: 2 }, c: 0 }
var obj2={}
for( var key in obj1 ){
    obj2[key]=obj1[key]
}

//数组类型

//方法一：
var data1 = new Array();
data1[0] = 'a';
data1[1] = 'b';
data1[2] = 'c';
var data2 = JSON.parse(JSON.stringify(data1))
//方法二
var data2 = data1.slice()
//方法三
var data2 = data1.concat(); 


~~~

### 删除指定对象

~~~javascript
array.splice(index, 1);
dataArry.splice(dataArry.indexOf(obj), 1);
~~~

### 更新对象

~~~
array.splice(index, 1, newObject);
~~~



## VUE语法

### 01 { } = this

~~~javascript
const {dispatch} = this.props

等价于

var dispatch = this.props.dispatch
~~~

### 02回调

this.$emit('父窗口方法名称')

### 03 data的使用方法

~~~
不使用return包裹的数据会在项目的全局可见，会造成变量污染；使用return包裹后数据中变量只在当前组件中生效，不会影响其他组件。

1、在简单的vue实例中看到的Vue实例中data属性，如下所示：
let app= newVue({

    el:"#app",
    data:{
        msg:''
    },
    methods:{
       
    }
})
2、在使用组件化的项目中，如下所示：
export default{
    data(){
        return {
　　　　　　　msg: 'hello vue',
        }
    },
    methods:{
       
    }
}
~~~

### 04 VUE优化

1、缓存路由组件

**<keep-alive> </keep-alive>**

2、路由组件懒加载

cont goods = () => import('page/goods/goods.vue')

3、图片组件懒加载

github搜索vue-lazyload了解相关使用

4、使用moment时间过滤器

~~~
1、安装moment 
npm install --save moment
2、编写过滤器
import moment form 'moment'
Vue.filter('date-format', function (value, formatStr='YYYY-MM-DD HH:mm:ss') {
	return moment(value).format(formatStr)
})
3、main导入过滤器
import 'filter/index.js'
4、页面格式使用
{{obj.date | date-format}} 或者 {{obj.date | date-format('YYYY-MM-DD HH:mm:ss')}}
5、优化改用date-fns组件,并只导入format函数
imort format 'date-fns/format'
Vue.fileter('date-format', function(value, formatStr){
  return format(value, formatStr || 'YYYY-MM-DD HH:mm:ss')
})
~~~

### 05 解决先显示{{message}}问题

~~~
<p v-cloak>{{message}}</p>
<style>
	[v-cloak]{
      display:none;
	}
</style>
~~~

### 06 自定义指令

~~~
全局指令
Vue.directive('upper-text', function (el, binding) {
    // 各种逻辑操做，这个指令中就是滑动的逻辑
    el.textContent = binding.value.toupperCase()
})

局部指令，通过配置实现
new Vue({
  el: '#test1',
  data:{
    
  },
  directive:{  //这里添加指令
    'upper-text': function (el, binding){
      el.textContent = binding.value.toupperCase()
    }
  }
})
注意：属性名称upper-text含特殊字符时需要用单引号，否则可以省略
~~~

### 07 列表搜索过滤

~~~
//返回名字包含'唐'的新数组
arrayList.filter(function (obj){
  return obj.name.indexOf('唐')
})
~~~

### 08 列表排序

~~~
//升序排列
arrayList.sor(function (obj1, obj2){
  return obj1.age - obj2.age
})
~~~



### 09 解构赋值

~~~
1、基本对象赋值
let name = '张三', age = 22, sex = '男';//常规写法
let [name,age,sex] = ['张三',22,'男'];//解构写法

2、对象的解构赋值
let {name,age,friends,pet} = {name:'张三',age:22,friends:['lulu','wwww'],pet:{name:'toutou',age:2}};

3、数组的解构赋值
let [a,[b,[c,d]]] = [1,[2,[3,4]]];
~~~

### 10 箭头函数

~~~
//此时this代表window
setInterval(function(){
  this.isShow = !this.isShow
}, 1000)

//此时this代表调用setInterval的对象，Vue中即Vue对象
setInterval(() =>  {this.isShow = !this.isShow
}, 1000)

总结：只要是回调函数最好写箭头函数，这样函数内部没有自己的this，调用的this是外部的
~~~

### 11 匿名函数自调用

~~~
//匿名函数自调用对外隐藏了实现过程
(function(x,y){
    alert(x+y);
    return x+y;
})(3,4); 
  javascript中: (function(){})()是匿名函数，主要利用函数内的变量作用域，避免产生全局变量，影响整体页面环境，增加代码的兼容性。
  (function(){})是一个标准的函数定义，但是没有复制给任何变量。所以是没有名字的函数，叫匿名函数。没有名字就无法像普通函数那样随时随地调用了，所以在他定义完成后就马上调用他，后面的括号()是运行这个函数的意思.
Vue中匿名函数使用this变量：

  methods: {
    cancelMask: function () {
      var that = this;
      setTimeout(function () {
        that.bottom = 0;
        that.left = 0;
      }, 500);
    }
~~~

## 12 Vue文档笔记

~~~
1、箭头函数并没有 this，this 会作为变量一直向上级词法作用域查找，直至找到为止，经常导致错误
2、render: h => h(App)解释：
  render: function (createElement) {
      return createElement(App);
  }
  h为createElement 方法别名
  ES6解释：
  // ES5语法  
  (function (h) {  
    return h(App);  
  });  
  // ES6语法  
  h => h(App);
3、v-html指令将内容转换为html代码，默认输出为v-text
4、{{}}表达式可为单个表达式
  {{ number + 1 }}

  {{ ok ? 'YES' : 'NO' }}

  {{ message.split('').reverse().join('') }}

  <div v-bind:id="'list-' + id"></div>
5、动态属性绑定(2.6.0以上版本)
	<a v-bind:[attributeName]="url"> ... </a>
	将变量attributeName的值作为绑定属性,在这个示例中，当 attributeName 的值为 "href" 时，v-bind:[attributeName] 将等价于 v-bind:href，attributeName值为null时显性地移除该属性。
	在 DOM 中使用模板时 (直接在一个 HTML 文件里撰写模板)，还需要避免使用大写字符来命名键名，因为浏览器会把 attribute 名全部强制转为小写：
6、修饰符.prevent 修饰符告诉 v-on 指令对于触发的事件调用 event.preventDefault()：
	<form v-on:submit.prevent="onSubmit">...</form>
7、 v-bind缩写“:”，v-on缩写：“@”。
8、侦听属性：
	watch: {
      firstName: function (value) {
        this.fullName = value + ' ' + this.lastName
      }
   }
    watch: {
      // 如果 `question` 发生改变，这个函数就会运行
      question: function (newQuestion, oldQuestion) {
        this.answer = 'Waiting for you to stop typing...'
      }
    }
9、计算属性
  computed: {
    fullName: {
      // getter
      get: function () {
        return this.firstName + ' ' + this.lastName
      },
      // setter
      set: function (newValue) {
      }
    }
  }
10、Class 绑定
	单一值：
	<div v-bind:class="active"></div>
	多个值：
	<div v-bind:class="[activeClass, errorClass]"></div>
    data: {
      activeClass: 'active',
      errorClass: 'text-danger'
    }
	对象语法动态绑定：
	<div v-bind:class="{ active: isActive }"></div>
	<div class="static" v-bind:class="{ active: isActive, 'text-danger': hasError }"></div>
	常用做法：
	<div v-bind:class="classObject"></div>
	data: {
      classObject: {
        active: true,
        'text-danger': false
      }
    }
    或
    computed: {
      classObject: function () {
        return {
          active: this.isActive && !this.error,
          'text-danger': this.error && this.error.type === 'fatal'
        }
      }
    }
11、Style 绑定
	v-bind:style属性名称使用驼峰命名，如：
	<div v-bind:style="{ color: activeColor, fontSize: fontSize + 'px' }"></div>
	<div v-bind:style="[baseStyles, overridingStyles]"></div>
	或
	<div v-bind:style="styleObject"></div>
    data: {
      styleObject: {
        color: 'red',
        fontSize: '13px'
      }
    }
    多重值使用数组：
    <div :style="{ display: ['-webkit-box', '-ms-flexbox', 'flex'] }"></div>
    这样写只会渲染数组中最后一个被浏览器支持的值。
12、v-for
    <li v-for="item in items">
      {{ item.message }}
    </li>
    带索引
    <li v-for="(item, index) in items">
      {{ parentMessage }} - {{ index }} - {{ item.message }}
    </li>
    对象遍历
    <div v-for="(value, name) in object">
      {{ name }}: {{ value }}
    </div>
    在遍历对象时，会按 Object.keys() 的结果遍历，但是不能保证它的结果在不同的 JavaScript 引擎下都一致。
13、数组触发更新的方法
    push()
    pop()
    shift()
    unshift()
    splice()
    sort()
    reverse()
    以下返回一个新数组，不触发更新
    filter()、concat() 和 slice()
14、非更新变更
    由于 JavaScript 的限制，Vue 不能检测以下数组的变动：
    当你利用索引直接设置一个数组项时，例如：vm.items[indexOfItem] = newValue
	当你修改数组的长度时，例如：vm.items.length = newLength
	解决方法：
	vm.items.splice(newLength)
	// Vue.set
	Vue.set(vm.items, indexOfItem, newValue)
	// Array.prototype.splice
	vm.items.splice(indexOfItem, 1, newValue)
	你也可以使用 vm.$set 实例方法，该方法是全局方法 Vue.set 的一个别名：
	vm.$set(vm.items, indexOfItem, newValue)
15、监视对象添加属性
    对于已经创建的实例，Vue 不允许动态添加根级别的响应式属性。但是，可以使用 Vue.set(object, propertyName, value) 方法向嵌套对象添加响应式属性。
    var vm = new Vue({
      data: {
        userProfile: {
          name: 'Anika'
        }
      }
    })
    Vue.set(vm.userProfile, 'age', 27)
    或
    vm.$set(vm.userProfile, 'age', 27)
16、Object.assign()使用
    Object.assign({}, data1, data2);
    由于assign会拼接到第一个对象上，因此使用空对象。
17、数组过滤
    <li v-for="n in evenNumbers">{{ n }}</li>	
    computed: {
      evenNumbers: function () {
        return this.numbers.filter(function (number) {
          return number % 2 === 0
        })
      }
    }
    或自定义方法searchNumber返回数组
    <li v-for="n in searchNumber(numbers)">{{ n }}</li>
18、v-for重复模板内容
	<span v-for="n in 10">{{ n }} </span>
19、在 <template> 上使用 v-for
	循环渲染一段包含多个元素的内容：
    <ul>
      <template v-for="item in items">
        <li>{{ item.msg }}</li>
        <li class="divider" role="presentation"></li>
      </template>
    </ul>
20、v-for 与 v-if 一同使用
    <li v-for="todo in todos" v-if="!todo.isComplete">
      {{ todo }}
    </li>
21、事件监听
	可以用 v-on 指令监听 DOM 事件，并在触发时运行一些 JavaScript 代码
  <div id="example-3">
    <button v-on:click="say('hi')">Say hi</button>
    <button v-on:submit="sumbData">提交</button>
  </div>
22、事件修饰符
  .stop
  .prevent
  .capture
  .self
  .once
  .passive
  <!-- 阻止单击事件继续传播 -->
  <a v-on:click.stop="doThis"></a>

  <!-- 提交事件不再重载页面 -->
  <form v-on:submit.prevent="onSubmit"></form>
23、keyup
  <input v-on:keyup.13="submit">
  为了在必要的情况下支持旧浏览器，Vue 提供了绝大多数常用的按键码的别名：
  .enter
  .tab
  .delete (捕获“删除”和“退格”键)
  .esc
  .space
  .up
  .down
  .left
  .right
  .ctrl
  .alt
  .shift
  .meta
  // 可以使用 `v-on:keyup.f1`
  Vue.config.keyCodes.f1 = 112
24、组合键
  <!-- Alt + C -->
  <input @keyup.alt.67="clear">
  <!-- Ctrl + Click -->
  <div @click.ctrl="doSomething">Do something</div>
25、V-MODE
  选择框
  <div id="example-5">
    <select v-model="selected">
      <option disabled value="">请选择</option>
      <option>A</option>
      <option>B</option>
      <option>C</option>
    </select>
    <span>Selected: {{ selected }}</span>
  </div>

  data: {
    selected: ''
  }
~~~

## 13 原型方法

~~~
	function Animal() {
      //实例属性
      this.name = name || 'Animal';
      //实例方法
      this.sleep = function () {
        console.log(this.name + "正在睡觉")
      }
	  //定义实例方法会覆盖原型方法
      //this.play=function(play){
      //  console.log(this.name+'正在玩'+ play)
      //}
    }
     //类方法
    Animal.eat = function (food) {
      console.log(food)
    }
    //原型方法
    Animal.prototype.play = function (play) {
      console.log(play)
    }

    //test1 类调用方法
    Animal.eat('food')//food  可以调用类方法
    console.log(Animal.name)//Animal 可以调用实例属性
    //Animal.play('warter') //not a function 不能调用原型方法
    //Animal.sleep()//not a function  不能调用实例方法

    //test2 new实例化后调用方法
    let Cat = new Animal();
    Cat.sleep() //可以调用实例方法
    Cat.play('warter') //可以调用原型方法（如果有实例方法会覆盖原型方法  优先级高于原型）
    
    总结：
    1、原型中的方法可以被所有的实例共享， 实例化的时候不会在实例内存中再复制一份，占有的内存消耗少。
    2、原型方法可以扩展原来定义类，使新定义的类变量能使用
    3、Cat.__proto__等于Animal.prototype ,Animal.prototype.__proto__等于Object.prototype.
~~~

### 14 其他

~~~
1、HTML 中的 attribute 名是大小写不敏感的，所以浏览器会把所有大写字符解释为小写字符。这意味着当你使用 DOM 中的模板时，camelCase (驼峰命名法) 的 prop 名需要使用其等价的 kebab-case (短横线分隔命名) 命名
~~~

### 15 stylus使用

~~~
npm install stylus stylus-loader --save-dev
stylus包将代码转义成js代码，stylus-loader包解析stylus的js代码
~~~

### 16 npm install

~~~
npm install moduleName # 安装模块到项目目录下
 
npm install -g moduleName # -g 的意思是将模块安装到全局，具体安装到磁盘哪个位置，要看 npm config prefix 的位置。
 
npm install -save moduleName # -save 的意思是将模块安装到项目目录下，并在package文件的dependencies节点写入依赖。
 
npm install -save-dev moduleName # -save-dev 的意思是将模块安装到项目目录下，并在package文件的devDependencies节点写入依
~~~

### 17 解决0.3秒的延迟问题 

~~~
在vue项目中有时会有移动端300毫秒事件点击延迟问题，需要安装fastclick库来解决

1.打开Vue项目，npm install fastclick --save
2.在项目main.js文件夹，引入fastclick
// 引入fastclick并绑定到body 解决移动端点击响应延迟0.3s的问题
import FastClick from 'fastclick'
FastClick.attach(document.body)
这样就可以正确引入fastclick库，解决300毫秒点击延迟问题~

或使用以下代码
  <script src="https://as.alipayobjects.com/g/component/fastclick/1.0.6/fastclick.js"></script>
  <script>
    if ('addEventListener' in document) {
      document.addEventListener('DOMContentLoaded', function () {
        FastClick.attach(document.body);
      }, false);
    }
    if (!window.Promise) {
      document.writeln('<script src="https://as.alipayobjects.com/g/component/es6-promise/3.2.2/es6-promise.min.js"' + '>' + '<' + '/' + 'script>');
    }
  </script>
~~~

### 18 Promise顺序异步执行，条件分支解耦

~~~
Promise 对条件验证结果处理进行封装，动态实现成功与失败处理过程：
第一步构造Promise验证：
  var promise = new Promise(function(resolve, reject){
      // 验证条件，得到value或error
      if (/* 验证成功条件 */) {
          resolve(data);
      } else {
          throw new Error('test')
          //或者reject('TEST');
      }
  })
第二步对Promise成功调用函数实际赋值：
  promise.then((response) => console.log(response));
  或对Promise失败调用函数实际赋值：
  promise.catch((error) => console.log(error));
  由此可见实现对验证结果实现动态处理，实际调用由将来条件决定。

promise的异步执行特性，看以下代码的执行顺序：
  let promise = new Promise(function(resolve, reject){
    if (1!=1) {
      console.log("AAA")
      resolve('SUCCESS');
    } else {
      console.log("BBB")
      throw new Error('ERROR')
      //reject('ERROR'); //或者不报错处理异常
    }
  });
  //then，catch异步执行，先向下执行再执行then、catch
  promise.then((data) => console.log(data));
  promise.catch((error) => console.log(error));
  console.log("CCC")

  // AAA
  // DDD
  // BBB
~~~

### 19 git版本管理

~~~
第一步建立本地仓库，在文件目录下cmd：
git init
git config user.name "someone"
git config user.email "someone@someplace.com"
git add *
git commit -m '备注内容'
第二步建立远程仓库，并在本地执行以下命令
git remote add origin https://github.com/itouba/waimai.git
第三步：解决ssl问题
set GIT_SSL_NO_VERIFY=true git push （linux命令: env GIT_SSL_NO_VERIFY=true git push）
git push -u origin master
~~~

### 20 VUEX使用

~~~
第一步：npm安装命令：npm install vuex --save
第二步：建立各js文件:index.js state.js mutations.js mutation-type.js getters.js actions.js
第三步：index.js 注册vuex.Store，参数顺序不能错
Vue.use(Vuex);
export default new Vuex.Store({
  state,
  mutations,
  actions,
  getters
})
调用vuex的action
action定义，参数为两个对象：
setGeohash({commit},  {latitude, longitude}){
	commit(SET_GEOHASH, {latitude, longitude})
}
mutations定义：
[SET_GEOHASH](state, {latitude, longitude}){
  state.latitude = latitude;
  state.longitude = longitude;
}
Vue组件调用：
 import {mapActions} from 'vuex'
 mounted() {
   const position  = {latitude: 25.1127, longitude:113.41724}
   this.setGeohash(position)
   this.getAddress();
 },
 methods:{
   ...mapActions(['getAddress', 'setGeohash']),
 },
state值局部变量化：
import { mapState} from 'vuex'
computed: mapState({
  // 箭头函数可以让代码非常简洁
  address: state => state.address,
}),
~~~

## jason数据引用

~~~
function compute(){
  const data = require('../data/shops.json')
}
~~~


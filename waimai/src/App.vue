<template>
  <div id="app">
    <keep-alive><router-view v-if="$route.meta.keepAlive"></router-view></keep-alive>
    <router-view v-if="!$route.meta.keepAlive"></router-view>
    <FooterGuide v-show="$route.meta.showFooter"/>
  </div>
</template>

<script>
  import FooterGuide from './components/FooterGuide/FooterGuide.vue'
  import {mapActions} from 'vuex'

  export default {
    mounted() {
/*      const position  = {latitude: 25.1127, longitude:113.41724}
      this.setGeohash(position)*/
      this.getAddress();
    },
    methods:{
      ...mapActions(['getAddress', 'setGeohash']),
    },
    components:{
      FooterGuide: FooterGuide
    }
  }
</script>

<style lang="stylus" ref="stylesheet/stylus">
  #app
    width 100%
    height 100%
    background #f5f5f5
</style>



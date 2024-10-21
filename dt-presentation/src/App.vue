<template>
  <Header/>
  <RouterView/>


</template>

<script>
import {RouterView} from 'vue-router'
import Header from './components/Header.vue'

export default {
  name: 'App',
  components: {
    Header
  }
}
</script>
<style>
</style>







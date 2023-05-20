<template>
  <div id="app">
    <header-nav-bar></header-nav-bar>
    <router-view></router-view>
    <footer-nav-bar v-if="$route.name != 'HotplaceCreate'"></footer-nav-bar>
  </div>
</template>

<script>
import HeaderNavBar from "@/components/commons/HeaderNavBar";
import FooterNavBar from "@/components/commons/FooterNavBar.vue";

export default {
  components: {
    HeaderNavBar,
    FooterNavBar,
  },
};
</script>



<style>
  @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap');
#app {
  /* background-color: #d5f5ff; */
  font-family: 'Noto Sans KR', sans-serif;
}
</style>

<template>
  <div id="app">
    <header-nav-bar></header-nav-bar>
    <router-view></router-view>
    <footer-nav-bar></footer-nav-bar>
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

<style></style>

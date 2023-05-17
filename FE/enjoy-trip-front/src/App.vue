<template>
  <div id="app">
    <router-view></router-view>
    <footer-nav-bar></footer-nav-bar>
    <!-- <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </nav>
    <router-view /> -->
  </div>
</template>

<script>
import FooterNavBar from "@/components/commons/FooterNavBar.vue";

export default {
  components: {
    FooterNavBar,
  },
};
</script>

<style></style>

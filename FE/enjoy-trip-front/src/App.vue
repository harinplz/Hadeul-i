<template>
  <div id="app">
    <header-nav-bar></header-nav-bar>
    <router-view></router-view>
    <!-- <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </nav>
    <router-view /> -->
  </div>
</template>

<script>
import HeaderNavBar from "@/components/commons/HeaderNavBar";

export default {
  components: {
    HeaderNavBar,
  },
};
</script>

<style></style>

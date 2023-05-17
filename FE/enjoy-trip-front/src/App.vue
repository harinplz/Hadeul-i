<template>
  <div id="app">
    <router-view></router-view>
    <nav>
      <router-link to="/attractions">관광지로 이동</router-link> |
      <router-link to="/admin">회원 관리로 이동</router-link>
    </nav>
    <footer-nav-bar></footer-nav-bar>
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

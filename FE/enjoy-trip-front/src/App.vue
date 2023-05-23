<template>
  <div id="app">
    <header-nav-bar></header-nav-bar>
    <router-view></router-view>
    <footer-nav-bar
      v-if="
        $route.name != 'HotplaceCreate' && $route.name != 'TravelRouteCreate'
      "></footer-nav-bar>
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
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap");

@font-face {
  font-family: "SUITE-Regular";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-2@1.0/SUITE-Regular.woff2")
    format("woff2");
  font-weight: 400;
  font-style: normal;
}
#app {
  /* background-color: #d5f5ff; */
  font-family: "Noto Sans KR", sans-serif;
}

.bAddr {
  width: 300px;
  height: 100px;
  border: none;
}
</style>

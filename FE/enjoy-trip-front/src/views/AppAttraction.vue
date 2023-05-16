<template>
  <div>
    <attraction-search-map></attraction-search-map>
    <attraction-search-list></attraction-search-list>
  </div>
</template>
<script>
export default {
  components: {
    "attraction-search-map": () =>
      import("@/components/attractions/AttractionSearchMap.vue"),
    "attraction-search-list": () =>
      import("@/components/attractions/AttractionSearchList.vue"),
  },
};
</script>

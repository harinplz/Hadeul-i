<template>
  <div>
    <attraction-search-map></attraction-search-map>
  </div>
</template>
<script>
export default {
  components: {
    "attraction-search-map": () =>
      import("@/components/attractions/AttractionSearchMap.vue"),
  },
};
</script>

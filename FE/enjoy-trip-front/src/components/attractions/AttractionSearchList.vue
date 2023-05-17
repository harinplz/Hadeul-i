<template>
  <table class="table table-striped">
    <thead>
      <tr>
        <th scope="col">관광지명</th>
        <th scope="col">주소</th>
        <th scope="col">위도</th>
        <th scope="col">경도</th>
      </tr>
    </thead>
    <tbody>
      <attraction-search-row
        v-for="attraction in attractions"
        :key="attraction.no"
        :attraction="attraction"></attraction-search-row>
    </tbody>
  </table>
</template>

<script>
export default {
  props: {
    attractions: Array,
  },
  components: {
    "attraction-search-row": () =>
      import("@/components/attractions/include/AttarctionSearchRow.vue"),
  },
};
</script>

<template>
  <table class="table table-striped">
    <thead>
      <tr>
      <th scope="col">사진</th>
      <th scope="col">관광지명</th>
      <th scope="col">주소</th>
      <th scope="col">좋아요 수</th>
      <th scope="col">지도로</th>
      </tr>
    </thead>
    <tbody id="table-body">
      <attraction-search-row
        v-for="attraction in attractions"
        :key="attraction.no"
        :attraction="attraction" @moveCenterEmit2="moveCenterEmit2"></attraction-search-row>
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
  methods: {
    moveCenterEmit2(lat, longi){
      
      this.$emit("moveCenter", lat, longi);
    }
  },
};
</script>

<template>
  <div>
    <travel-route-header></travel-route-header>
    <div
      id="submitSearch"
      class="row g-0 align-items-center flex-column-reverse flex-md-row">
      <div class="col-md-6">
        <router-link :to="{ name: 'TravelRouteCreate' }">
          <button type="button" class="btn" style="background-color: #c3e5e5">
            <b>글쓰기</b>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {
    "travel-route-header": () =>
      import("@/components/travelroutes/include/TravelRouteHeader"),
  },
};
</script>

<style scoped>
#submitSearch {
  width: 83%;
  margin: 30px auto;
}
</style>

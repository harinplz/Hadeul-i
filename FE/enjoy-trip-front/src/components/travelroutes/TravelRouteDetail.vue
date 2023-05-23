<template>
  <div>
    <travel-route-header></travel-route-header>
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

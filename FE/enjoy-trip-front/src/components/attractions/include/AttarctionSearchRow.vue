<template>
  <tr>
    <td scope="col">
      <img
        style="width: 100px"
        :src="attraction.imageUrl"
        @error="defaultImg" />
    </td>
    <td scope="col">{{ attraction.attractionName }}</td>
    <td scope="col">{{ attraction.address }}</td>
    <td scope="col">{{ attraction.hit }}</td>
    <td
      scope="col"
      @click="moveCenterEmit(attraction.latitude, attraction.longitude)"
      class="btn btn-outline-success"
      type="button">
      위치 보기
    </td>
  </tr>
</template>

<script>
export default {
  props: {
    attraction: Object,
  },
  methods: {
    moveCenterEmit(lat, longi) {
      console.log(this.attraction);
      this.$emit("moveCenterEmit2", lat, longi);
    },
    defaultImg(e) {
      e.target.src = "http://enjoytrip.jaen.kr:9000/img/no-image.b6aeb20b.png";
    },
  },
};
</script>

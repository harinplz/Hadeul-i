<template>
  <div class="cardView wow fadeInUp" @click="goHotplaceDetail">
    <div class="cardView_img">
      <img class="cardView_img_de" :src="this.imgSrc" />
    </div>
    <div class="cardView_desc">
      <b-icon icon="geo-alt-fill" style="color: #58d3f7"></b-icon>
      <span class="cardView_title">
        [{{ input.category }}] {{ input.hotplaceName }}</span
      >
      <br />
      <div class="cardView_addr">
        <span class="cardView_jibun">{{ input.jibun }}</span>
        <span v-if="input.hotplaceAddr != 'null'">
          {{ input.hotplaceAddr }}</span
        >
      </div>

      <p class="cardView_content">{{ input.hotplaceContent }}</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      imgSrc: null,
    };
  },
  props: {
    hotplace: Object,
  },
  methods: {
    goHotplaceDetail() {
      this.$router.push({
        name: "HotplaceDetail",
        params: { hotplaceNo: this.input.hotplaceNo },
      });
    },
  },
  computed: {
    input() {
      return { ...this.hotplace };
    },
  },
  created() {
    this.imgSrc =
      "http://localhost/hotplace/display?filename=" + this.input.img;
  },
};
</script>

<style scoped>
.cardView {
  background-color: #ffffff;
  width: 32%;
  height: 400px;
  display: inline-block;
  margin: 0px 5px;
  border-radius: 20px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
  transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);
}

.cardView:hover {
  box-shadow: 0 14px 20px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
}

.cardView_img {
  width: 100%;
  height: 60%;
  background-color: brown;
  border-radius: 20px 20px 0px 0px;
}

.cardView_img_de {
  width: 100%;
  height: 100%;
  border-radius: 20px 20px 0px 0px;
}

.cardView_desc {
  padding: 10px;
}

.cardView_title {
  font-size: 20px;
  font-weight: bold;
}

.cardView_addr {
  margin-top: 10px;
}

.cardView_content {
  color: #7c7877;
}
</style>

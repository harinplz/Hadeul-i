<template>
  <div id="bg">
    <div class="black-bg" v-if="openModal == true" @click="close($event)">
      <div class="white-bg">
        <button class="close">닫기</button>
      </div>
    </div>

    <div class="travelRt_create_title wow fadeInUp" data-wow-delay="0.05s">
      <div class="travelRt_create_title_text">
        함께 <b>여행 계획</b>을 세워봐요!
      </div>
    </div>

    <div class="map-and-create row wow fadeInUp">
      <div class="search_area col-3">
        <div class="search_area_text">
          <span class="highlight">관광지 검색</span>
        </div>
        <p class="search_area_desc_text">여행을 떠날 관광지를 검색해주세요!</p>
        <div class="search">
          <input class="searchInput" type="text" placeholder="검색어 입력" />
          <button
            type="button"
            class="btn searchBtn"
            style="background-color: #ffd5e3">
            <b>검색</b>
          </button>
        </div>
      </div>
      <div class="map_area col-6">
        <div id="map"></div>
      </div>
      <div class="route_area col-3">
        <div class="search_area_text">
          <span class="highlight_pink">나만의 여행 계획</span>
        </div>
        <p class="search_area_desc_text">관광지를 통해 여행 계획을 세워봐요!</p>
        <div class="createBtn_div">
          <button
            type="button"
            class="btn searchBtn"
            style="background-color: #c3e5ee"
            @click="openModal = true">
            <b>등록</b>
          </button>
        </div>
      </div>
      <!-- </transition> -->
    </div>

    <div class="create-list-btns wow fadeInUp">
      <router-link :to="{ name: 'TravelRouteList' }">
        <button
          type="button"
          class="btn travelRtBtn"
          style="background-color: #ffd5e3">
          <b>목록</b>
        </button>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      map: null,
      openModal: false,
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=70697613147c4c88e83fb654db4eed6e&libraries=services";
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      var mapContainer = document.getElementById("map"),
        mapOption = {
          center: new kakao.maps.LatLng(35.094846, 128.856055),
          level: 5,
        };
      this.map = new window.kakao.maps.Map(mapContainer, mapOption);
    },
    close(event) {
      if (
        event.target.classList.contains("black-bg") ||
        event.target.classList.contains("close")
      ) {
        this.openModal = false;
      } else if (event.target.classList.contains("white-bg")) {
        this.openModal = true;
      }
    },
  },
};
</script>

<style scoped>
.black-bg {
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.6);
  position: fixed;
  z-index: 1;
}

.white-bg {
  width: 70%;
  height: 500px;
  margin: 80px auto;
  background: white;
  border-radius: 5px;
  padding: 20px 0;
}

.close {
  cursor: pointer;
  border: none;
  background: #6667ab;
  color: white;
  font-weight: bold;
  padding: 5px 15px;
  border-radius: 5px;
}

.close:hover {
  color: white;
  font-weight: bold;
  transform: scale(1.1);
  transition: all 0.5s;
}

#bg {
  /* background-color: aqua; */
  background-image: url("@/assets/img/hotplace-create-bg.jpg");
  height: 1100px;
  margin-top: -50px;
}

#map {
  width: 100%;
  height: 100%;
}

.create-list-btns {
  margin-top: 60px;
  text-align: center;
}

.travelRtBtn {
  font-size: 20px;
  margin: 0px 10px;
  padding: 10px 30px;
}

.travelRt_create_title {
  font-size: 25px;
  padding-top: 50px;
  margin-bottom: 30px;
  text-align: center;
  color: #7c7877;
  font-family: "SUITE-Regular";
}

.travelRt_create_title_text {
  width: 470px;
  padding: 20px;
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 30px;
  margin: auto;
}

.map-and-create {
  height: 700px;
  width: 95%;
  margin: auto;
}

.search_area {
  background-color: rgb(255, 255, 255);
  width: 100%;
  height: 100%;
  border-radius: 20px;
  border: 1px solid #cccccc;
  border-collapse: collapse;
}

.map_area {
  width: 100%;
  height: 100%;
}

.route_area {
  background-color: rgb(253, 253, 253);
  width: 100%;
  height: 100%;
  border: 1px solid #cccccc;
  border-collapse: collapse;
  border-radius: 20px;
}

.search_area_text {
  font-family: "SUITE-Regular";
  text-align: center;
  font-size: 25px;
  font-weight: bolder;
  padding-top: 15px;
}

.highlight {
  display: inline;
  box-shadow: inset 0 -10px 0 #c3e5ee;
}

.highlight_pink {
  display: inline;
  box-shadow: inset 0 -10px 0 #ffd5e3;
}

.search {
  padding-top: 10px;
  text-align: center;
}

.search_area_desc_text {
  text-align: center;
  font-family: "SUITE-Regular";
  padding-top: 5px;
  font-size: 17px;
}

.searchInput {
  margin-left: 5px;
  width: 250px;
  border: 1px solid #bbb;
  border-radius: 8px;
  padding: 10px 12px;
  font-size: 14px;
  display: inline;
}
.searchBtn {
  margin-left: 5px;
  display: inline;
}

.createBtn_div {
  text-align: center;
}
</style>

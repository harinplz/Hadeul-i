<template>
  <div id="bg">
    <div class="hotpl_create_title wow fadeInUp" data-wow-delay="0.05s">
      <div class="hotpl_create_title_text">당신의 <b>핫플레이스</b>를 자랑해주세요!</div>
    </div>
    <div class="map-and-create row g-0 align-items-center flex-column-reverse flex-md-row">
      <div class="wow fadeInUp col-md-6" data-wow-delay="0.1s">
        <div id="map"></div>
      </div>
      <!-- <transition name="fade"> -->
      <div class="wow fadeInUp create col-md-6" data-wow-delay="0.1s">
        <div class="create-content">
          <label for="hotpl-cate-label">핫플레이스 유형</label>
          <div style="margin-bottom: 20px">
            <select class="searchSelect">
              <option>핫플레이스 유형</option>
              <option>관광지</option>
              <option>문화시설</option>
              <option>축제공연행사</option>
              <option>여행코스</option>
              <option>레포츠</option>
              <option>숙박</option>
              <option>쇼핑</option>
              <option>음식점</option>
            </select>
          </div>
        </div>
        <div class="input-box">
          <label for="hotpl-name-label">핫플레이스 이름</label> <br />
          <input class="hotplace-name" type="text" placeholder="핫플레이스 이름" />
        </div>
        <div class="hotpl-img">
          <label for="hotpl-img-label">핫플레이스 사진</label> <br />
          <!-- <input type="file"> -->
          <b-form-file multiple>
            <template slot="file-name" slot-scope="{ names }">
              <b-badge variant="dark">{{ names[0] }}</b-badge>
              <b-badge v-if="names.length > 1" variant="dark" class="ml-1">
                + {{ names.length - 1 }} More files
              </b-badge>
            </template>
          </b-form-file>
        </div>
        <div class="hotpl-desc">
          <label for="hotpl-desc-label">핫플레이스 설명</label> <br />
          <textarea class="hotpl-desc-text" placeholder="나만의 핫플레이스를 자랑해주세요!" />
        </div>
      </div>
      <!-- </transition> -->
    </div>
    <div class="create-list-btns wow fadeInUp" data-wow-delay="0.5s">
      <router-link :to="{ name: 'HotplaceList' }">
        <button type="button" class="btn hotplBtn" style="background-color: #c3e5e5">
          <b>목록</b>
        </button>
      </router-link>
      <button type="button" class="btn hotplBtn" style="background-color: #ffd5e3">
        <b>등록</b>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      map: null,
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.loadMap();
    } else {
      this.loadScript();
    }
  },
  methods: {
    loadScript() {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=70697613147c4c88e83fb654db4eed6e&libraries=services,clusterer,drawing";

      script.onload = () => window.kakao.maps.load(this.loadMap);
      document.head.appendChild(script);
    },
    loadMap() {
      var mapContainer = document.getElementById("map"),
        mapOption = {
          center: new window.kakao.maps.LatLng(33.450701, 126.570667),
          level: 5,
        };
      this.map = new window.kakao.maps.Map(mapContainer, mapOption);
    },
  },
};
</script>

<style scoped>
@font-face {
  font-family: "Dovemayo_gothic";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2302@1.1/Dovemayo_gothic.woff2")
    format("woff2");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "SUITE-Regular";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2304-2@1.0/SUITE-Regular.woff2")
    format("woff2");
  font-weight: 400;
  font-style: normal;
}

#bg {
  /* background-color: aqua; */
  background-image: url("@/assets/img/hotplace-create-bg.jpg");
  height: 1000px;
  margin-top: -50px;
}

.hotpl_create_title {
  font-size: 25px;
  padding-top: 50px;
  text-align: center;
  color: #7c7877;
  font-family: "SUITE-Regular";
}

.hotpl_create_title_text {
  width: 470px;
  padding: 20px;
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 30px;
  margin: auto;
}

.map-and-create {
  height: 630px;
  width: 90%;
  margin: auto;
  padding-top: 70px;
}

#map {
  height: 100%;
  padding: 250px;
  border-radius: 30px;
}

.create {
  height: 100%;
  background-color: #fafafa;
  border-radius: 30px;
  padding: 0px 30px;
}

.create-content {
  padding-top: 30px;
}

.input-box {
  position: relative;
  margin: 10px 0;
}

.input-box > input {
  border: 1px solid #bbb;
  border-radius: 8px;
  padding: 10px 12px;
  font-size: 16px;
  width: 100%;
}

.input-box > input:focus {
  border: 2px solid #c3e5e5;
  outline: none;
}

.searchSelect {
  width: 100%;
  border: 1px solid #bbb;
  border-radius: 8px;
  padding: 10px 12px;
  font-size: 16px;
}

.searchSelect:focus {
  border: 2px solid #c3e5e5;
  outline: none;
}

.hotpl-img {
  margin-top: 20px;
}

.hotpl-desc {
  margin-top: 20px;
}

.hotpl-desc-text {
  width: 100%;
  height: 150px;
  border: 1px solid #bbb;
  border-radius: 8px;
  font-size: 16px;
  resize: none;
  padding: 20px;
}

.hotpl-desc-text:focus {
  border: 2px solid #c3e5e5;
  outline: none;
  padding: 20px;
}

.create-list-btns {
  margin-top: 60px;
  text-align: center;
}

.hotplBtn {
  font-size: 20px;
  margin: 0px 10px;
  padding: 10px 30px;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease-out;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}
</style>

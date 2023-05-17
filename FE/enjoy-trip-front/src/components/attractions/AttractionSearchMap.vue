<template>
  <b-container>
    <div style="height: 70px"></div>

    <!-- 중앙 center content end -->
    <div class="col-md-12">
      <div class="alert alert-primary mt-3 text-center fw-bold" role="alert">
        전국 관광지 정보
      </div>
      <!-- 관광지 검색 start -->
      <form class="d-flex my-5" action="" id="search-form">
        <select
          id="search-area"
          name="search-area"
          class="form-select me-2"
          v-model="searchArea">
          <option value="0">검색 할 지역 선택</option>
          <option value="1">서울</option>
          <option value="2">인천</option>
          <option value="3">대전</option>
          <option value="4">대구</option>
          <option value="5">광주</option>
          <option value="6">부산</option>
          <option value="7">울산</option>
          <option value="8">세종특별자치시</option>
          <option value="31">경기도</option>
          <option value="32">강원도</option>
          <option value="33">충청북도</option>
          <option value="34">충청남도</option>
          <option value="35">경상북도</option>
          <option value="36">경상남도</option>
          <option value="37">전라북도</option>
          <option value="38">전라남도</option>
          <option value="39">제주도</option>
        </select>
        <select
          id="search-content-id"
          name="search-content-id"
          class="form-select me-2"
          v-model="searchContentId">
          <option value="0">관광지 유형</option>
          <option value="12">관광지</option>
          <option value="14">문화시설</option>
          <option value="15">축제공연행사</option>
          <option value="25">여행코스</option>
          <option value="28">레포츠</option>
          <option value="32">숙박</option>
          <option value="38">쇼핑</option>
          <option value="39">음식점</option>
        </select>
        <select
          id="sort-name"
          name="sort-name"
          class="form-select me-2"
          v-model="sortName">
          <option value="0">정렬 방법</option>
          <option value="1">이름 순</option>
          <option value="2">역 이름 순</option>
        </select>
        <input
          id="search-keyword"
          class="form-control me-2"
          name="search-keyword"
          type="text"
          placeholder="검색어"
          aria-label="검색어"
          v-model="searchKeyword" />

        <button
          id="btn-search"
          class="btn btn-outline-success"
          type="button"
          @click="searchTrips">
          검색
        </button>
      </form>

      <!-- kakao map start -->
      <div id="map" class="mt-3" style="width: 100%; height: 600px"></div>
      <!-- kakao map end -->

      <div v-if="attractions.length > 0">
        <attraction-search-list
          :attractions="attractions"></attraction-search-list>
      </div>
      <!-- 관광지 검색 end -->
    </div>
  </b-container>
</template>

<script>
let markers = [];

export default {
  name: "AttractionSearchMap",
  data() {
    return {
      map: null,
      searchArea: "0",
      searchContentId: "0",
      searchKeyword: "",
      sortName: "0",
    };
  },
  components: {
    "attraction-search-list": () =>
      import("@/components/attractions/AttractionSearchList.vue"),
  },
  computed: {
    attractions() {
      //getter: attractions 정보 얻어옴
      return this.$store.getters.attractions;
    },
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.loadMap();
    } else {
      this.loadScript();
    }
  },
  methods: {
    searchTrips() {
      const payload = {
        attractionSearchInfo: {
          "search-area": this.searchArea,
          "search-content-id": this.searchContentId,
          "search-keyword": this.searchKeyword,
          "sort-name": this.sortName,
        },
      };
      //액션 호출
      this.$store.dispatch("attractionSearch", payload);

      //0.6초후 이동하도록 처리..
      setTimeout(() => {
        this.loadMarkers();
      }, 600);
    },
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

    loadMarkers() {
      if (this.map == undefined) {
        this.loadMap();
      }
      for (let i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
      }

      var positions = [];

      for (let i = 0; i < this.attractions.length; i++) {
        let markerInfo = {
          title: this.attractions[i].title,

          latlng: new window.kakao.maps.LatLng(
            this.attractions[i].latitue,
            this.attractions[i].longitude
          ),
        };

        positions.push(markerInfo);
      }

      var imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

      for (var i = 0; i < positions.length; i++) {
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new window.kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다
        var markerImage = new window.kakao.maps.MarkerImage(
          imageSrc,
          imageSize
        );

        // 마커를 생성합니다
        var marker = new window.kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: positions[i].latlng, // 마커를 표시할 위치
          title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage, // 마커 이미지
        });
        marker.setMap(this.map);
        markers.push(marker);
      }

      //0번 항목이 중앙으로 오도록 이동
      this.map.setCenter(
        new window.kakao.maps.LatLng(
          positions[0].latlng.Ma,
          positions[0].latlng.La
        )
      );
    },
  },
};
</script>

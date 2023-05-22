<template>
  <b-container>
    <!-- 중앙 center content end -->
    <div class="col-md-12" id="parent-sticky">
      <div id="alertinfo" class="alert alert-primary mt-3 mb-0 text-center fw-bold" role="alert">
        전국 관광지 정보
      </div>
      <!-- 관광지 검색 start -->
      <div class="sticky">
      <br>
      <form class="mb-3" action="" id="search-form">
        <div class="d-flex justify-content-center mb-3">
        <select
          id="search-area"
          name="search-area"
          class="form-select me-2 mr-2"
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

        <b-form-group class="mb-0">
          <b-form-checkbox-group
            v-model="selected"
            :options="options"
            name="flavour-1"
          ></b-form-checkbox-group>
        </b-form-group>
        
        <!-- <div class="form-check form-check-inline">
          <input class="form-check-input" type="checkbox" value="12" id="checkbox-12" checked>
          <label class="form-check-label" for="checkbox-12">관광지</label>
        </div>
        <div class="form-check form-check-inline">
          <input class="form-check-input" type="checkbox" value="14" id="checkbox-14" checked>
          <label class="form-check-label" for="checkbox-14">문화시설</label>
        </div>
        <div class="form-check form-check-inline">
          <input class="form-check-input" type="checkbox" value="15" id="checkbox-15" checked>
          <label class="form-check-label" for="checkbox-15">축제공연행사</label>
        </div>
        <div class="form-check form-check-inline">
          <input class="form-check-input" type="checkbox" value="25" id="25" checked>
          <label class="form-check-label" for="25">여행코스</label>
        </div>
        <div class="form-check form-check-inline">
          <input class="form-check-input" type="checkbox" value="28" id="28" checked>
          <label class="form-check-label" for="28">레포츠</label>
        </div>
        <div class="form-check form-check-inline">
          <input class="form-check-input" type="checkbox" value="32" id="32" checked>
          <label class="form-check-label" for="32">숙박</label>
        </div>
        <div class="form-check form-check-inline">
          <input class="form-check-input" type="checkbox" value="38" id="38" checked>
          <label class="form-check-label" for="38">쇼핑</label>
        </div>
        <div class="form-check form-check-inline">
          <input class="form-check-input" type="checkbox" value="39" id="39" checked>
          <label class="form-check-label" for="39">음식점</label>
        </div>   -->

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
          </div>
          <div class="d-flex">
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
          </div>
        </form>

        <!-- kakao map start -->
        <div id="map" class="mt-3" style="width: 100%; height: 400px"></div>
        <!-- kakao map end -->
        <div id="table-header"></div>
      </div>
      <!-- 관광지 검색 end -->

      <div v-if="attractions.length > 0">
        <attraction-search-list
          :attractions="attractions"
          @moveCenter="moveCenter"
          ref="tableList"></attraction-search-list>
      </div>
    </div>
    <!-- 상세보기 모달 start -->
    <b-modal
      v-model="modalOpen"
      title="관광지 상세 보기"
      header-bg-variant="info"
      header-text-variant="light"
      centered
      hide-footer
      id="detailModal"
      ref="detail-modal">
      <b-container v-if="modalContent">
        {{ modalContent.addr1 }}
      </b-container>
      <b-input-group style="width: 500px" prepend="주소"> </b-input-group>
    </b-modal>
  </b-container>
</template>

<script>
//let markers = [];
export default {
  name: "AttractionSearchMap",
  data() {
    return {
      map: null,
      modalOpen: false,
      modalContent: null,
      searchArea: "0",
      searchContentId: "0",
      searchKeyword: "",
      sortName: "0",
      options: [
        { text: "관광지", value: "12" },
        { text: "문화시설", value: "14" },
        { text: "축제공연행사", value: "15" },
        { text: "여행코스", value: "25" },
        { text: "레포츠", value: "28" },
        { text: "숙박", value: "32" },
        { text: "쇼핑", value: "38" },
        { text: "음식점", value: "39" },
      ],
      selected: [],
    };
  },
  watch: {
    selected: function () {
      //console.log(this.selected);
      this.loadMarkers();
    },
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
      if (this.attractions.length > 0) {
        this.loadMarkers();
      }
    },

    loadMarkers() {
      if (this.map == undefined) {
        this.loadMap();
      }
      // for (let i = 0; i < markers.length; i++) {
      //   markers[i].setMap(null);
      // }

      var positions = [];

      for (let i = 0; i < this.attractions.length; i++) {
        if (this.selected.includes(this.attractions[i].contentId)) {
          console.log("포함하고 있습니다.");
          continue;
        }
        let markerInfo = {
          title: this.attractions[i].title,

          //마커 마우스 오버시 나오는 메세지 작성 부분
          content:
            '<div class="overlaybox">' +
            '<div id="overTitle">' +
            this.attractions[i].title +
            "</div>" +
            "<div>" +
            '<div><img style="width:120px" src="data:image/jpeg;base64,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"></div>' +
            "<div>" +
            this.attractions[i].title +
            "<br>" +
            this.attractions[i].addr1 +
            "</div>" +
            "</div>" +
            "<div>클릭 시 상세보기</div>" +
            "</div>",

          latlng: new window.kakao.maps.LatLng(
            this.attractions[i].latitue,
            this.attractions[i].longitude
          ),
        };

        positions.push(markerInfo);
      }

      var imageSrc =
        "https://cdn4.iconfinder.com/data/icons/map-navigation-navy-vol-1/512/Destination_Marker-256.png";
      //"https://cdn1.iconfinder.com/data/icons/location-colors/91/Location_01-64.png";
      //"https://cdn2.iconfinder.com/data/icons/vivid/48/map-marker-64.png";
      //"https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
      // "https://cdn0.iconfinder.com/data/icons/valentines-day-15/32/location-romance-love-valentines-day-marker-64.png";
      // "https://cdn4.iconfinder.com/data/icons/map-location-19/512/200_Location_Map_Marker_Pin-256.png";

      for (var i = 0; i < positions.length; i++) {
        // 마커 이미지의 이미지 크기 입니다
        //var imageSize = new window.kakao.maps.Size(24, 35);
        var imageSize = new window.kakao.maps.Size(30, 35);

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

          //마커 클릭 가능하도록 설정. 마커 클릭 -> 지도의 클릭 이벤트 발생하지 않음
          clickable: true,
        });

        //마커 마우스 오버 시 메세지를 infowindow에 등록
        var infowindow = new window.kakao.maps.InfoWindow({
          content: positions[i].content,
        });

        //마커에 마우스오버, 마우스아웃 이벤트 리스너 등록
        window.kakao.maps.event.addListener(
          marker,
          "mouseover",
          this.makeOverListener(this.map, marker, infowindow)
        );
        window.kakao.maps.event.addListener(
          marker,
          "mouseout",
          this.makeOutListener(infowindow)
        );

        //마커 클릭에 대한 이벤트..
        window.kakao.maps.event.addListener(
          marker,
          "click",
          this.makeDetailModal(this, this.attractions[i])
        );
        //marker.setMap(this.map);//없어도 동작하는듯..!
        //markers.push(marker);
      }

      //0번 항목이 중앙으로 오도록 이동
      if (positions.length > 0) {
        this.map.setCenter(
          new window.kakao.maps.LatLng(
            positions[0].latlng.Ma,
            positions[0].latlng.La
          )
        );
      }
    },

    makeDetailModal(thiz, attInfo) {
      //클릭 시 모달을 띄우는 이벤트 등록
      return function () {
        console.log(attInfo);
        // this.$refs['detail-modal'].show()
        thiz.modalOpen = true;
        thiz.modalContent = attInfo;
      };
    },

    makeOverListener(map, marker, infowindow) {
      return function () {
        infowindow.open(map, marker);
      };
    },
    makeOutListener(infowindow) {
      return function () {
        infowindow.close();
      };
    },

    //searchRow에서 클릭 시 지도이동하는 메서드
    moveCenter(lat, longi) {
      var moveLatLon = new window.kakao.maps.LatLng(lat, longi);
      this.map.panTo(moveLatLon);

      let marker = new window.kakao.maps.Marker({
        position: new window.kakao.maps.LatLng(lat, longi),
      });

      //임시 마커 지도에 표시
      marker.setMap(this.map);

      //00초 후 해당 마커 삭제
      setTimeout(() => {
        marker.setMap(null);
      }, 800);
    },
  },
};
</script>

<style>
.sticky {
  position: sticky;
  top: 0px;
  background: #fff;
}

.overlaybox {
  overflow: hidden;
  background: #ffd5e3;
}
</style>

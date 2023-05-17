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
          <option value="0" selected>검색 할 지역 선택</option>
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
          <option value="0" selected>관광지 유형</option>
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
          <option value="0" selected>정렬 방법</option>
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
          @click="searchTrips"></button>
      </form>

      <!-- kakao map start -->
      <div id="map" class="mt-3" style="width: 100%; height: 600px"></div>
      <!-- kakao map end -->
      <div class="row">
        <table class="table table-striped">
          <thead>
            <tr>
              <th>관광지명</th>
              <th>주소</th>
              <th>위도</th>
              <th>경도</th>
            </tr>
          </thead>
          <tbody id="trip-list"></tbody>
        </table>
      </div>
      <!-- 관광지 검색 end -->
    </div>
  </b-container>
</template>

<script>
// // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
// var map = new kakao.maps.Map(mapContainer, mapOption);

// var control = new kakao.maps.ZoomControl();
// map.addControl(control, kakao.maps.ControlPosition.TOPRIGHT);

// function displayMarker(datas) {
//   var positions = [];

//   datas.forEach(function (data) {
//     let markerInfo = {
//       title: data.title,
//       latlng: new kakao.maps.LatLng(data.latitue, data.longitude),
//     };
//     positions.push(markerInfo);
//   });

//   // 마커 이미지의 이미지 주소입니다
//   var imageSrc =
//     "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

//   for (var i = 0; i < positions.length; i++) {
//     // 마커 이미지의 이미지 크기 입니다
//     var imageSize = new kakao.maps.Size(24, 35);

//     // 마커 이미지를 생성합니다
//     var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

//     // 마커를 생성합니다
//     var marker = new kakao.maps.Marker({
//       map: map, // 마커를 표시할 지도
//       position: positions[i].latlng, // 마커를 표시할 위치
//       title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
//       image: markerImage, // 마커 이미지
//     });
//   }

//   // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
//   map.setCenter(positions[0].latlng);
// }

// function moveCenter(lat, lng) {
//   map.setCenter(new kakao.maps.LatLng(lat, lng));
// }

// function makeList(datas) {
//   let tbody = ``;

//   datas.forEach(function (data) {
//     tbody += `<tr>
//                             <td>${data.title}</td>
//                             <td>${data.addr1}</td>
//                             <td>${data.latitue}</td>
//                             <td>${data.longitude}</td>
//                           </td>`;
//   });
//   document.querySelector("#trip-list").innerHTML = tbody;
// }

// function searchTrips() {
//   console.log("searchTrips 호출됨");

//   var searchForm = document.getElementById("search-form");

//   let config = {
//     method: "POST",
//     headers: {
//       "Content-Type": "application/json",
//     },
//     body: JSON.stringify(tripInfo),
//   };

//   fetch("http://localhost/trips", config)
//     .then((response) => {
//       switch (response.status) {
//         case 200:
//           return response.json();
//         case 204:
//           throw new Error("검색 데이터가 없습니다");
//       }
//     })
//     .then((data) => {
//       console.log(data);
//       displayMarker(data);
//       makeList(data);
//     })
//     .catch((error) => {
//       alert(error);
//     });
// }

//===============================================
//import { mapGetters } from "vuex";

export default {
  name: "AttractionSearchMap",
  data() {
    return {
      map: null,
      searchArea: "",
      searchContentId: "",
      searchKeyword: "",
      sortName: "",
    };
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
      this.loadMarkers();
    },
    loadScript() {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=70697613147c4c88e83fb654db4eed6e&libraries=services,clusterer,drawing";

      script.onload = () => window.kakao.maps.load(this.loadMap);
      document.head.appendChild(script);

      ///* global kakao */
      // script.addEventListener("load", () => {
      //   console.log("loaded", kakao);
      // });
    },

    loadMap() {
      var mapContainer = document.getElementById("map"),
        mapOption = {
          center: new window.kakao.maps.LatLng(33.450701, 126.570667),
          level: 5,
        };

      this.map = new window.kakao.maps.Map(mapContainer, mapOption);

      //this.loadMarker();
      //this.loadMarkers();
    },

    // loadMarker() {
    //   const markerPosition = new window.kakao.maps.LatLng(
    //     33.450701,
    //     126.570667
    //   );

    //   const marker = new window.kakao.maps.Marker({
    //     position: markerPosition,
    //   });
    //   marker.setMap(this.map);
    // },
    loadMarkers() {
      var positions = [];
      console.log(this.attractions.length);
      for (let i = 0; i < this.attractions.length; i++) {
        let markerInfo = {
          title: this.attractions[i].title,

          latlng: new window.kakao.maps.LatLng(
            this.attractions[i].latitue,
            this.attractions[i].longitude
          ),
        };
        console.log(markerInfo);
        positions.push(markerInfo);
      }

      var imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

      var markers = [];

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
        markers.push(marker);

        marker.setMap(this.map);
      }

      //marker.setMap(this.map);
    },
  },
};
</script>

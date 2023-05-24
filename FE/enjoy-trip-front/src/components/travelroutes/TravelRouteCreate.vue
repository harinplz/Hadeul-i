<template>
  <div id="bg">
    <div class="black-bg" v-if="openModal == true" @click="close($event)">
      <div class="white-bg">
        <div>
          <button class="btn close">x</button>
        </div>
        <div class="travelroute_create_top" style="margin-bottom: 20px">
          <span class="highlight">여행 경로 등록하기</span>
        </div>
        <span class="travelroute_create_title">제목</span>
        <input
          class="create_title_input"
          type="text"
          placeholder="제목 입력"
          v-model="routeTitle" />
        <div class="row create_calender">
          <div class="col-md-6 start_date_div">
            <div class="start_date_text">시작일</div>
            <input
              type="date"
              data-placeholder="날짜 선택"
              required
              aria-required="true"
              v-model="routeStartDate" />
          </div>
          <div class="col-md-6 end_date_div">
            <div class="end_date_text">종료일</div>
            <input
              type="date"
              data-placeholder="날짜 선택"
              required
              aria-required="true"
              v-model="routeEndDate" />
          </div>
        </div>
        <div class="travelroute_create_content_text">내용</div>
        <textarea
          class="travelroute_create_content"
          placeholder="여행 계획을 설명해주세요!"
          v-model="routeContent" />
        <div class="travelroute_create_btn">
          <button
            class="btn searchBtn"
            type="button"
            style="background-color: #ffd5e3"
            @click="createFinalTravelRoute">
            등록
          </button>
        </div>
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
          <input
            class="searchInput"
            type="text"
            placeholder="검색어 입력"
            v-model="searchTitle" />
          <button
            type="button"
            class="btn searchBtn"
            style="background-color: #ffd5e3"
            @click="searchStart">
            <b>검색</b>
          </button>
        </div>
        <div class="container">
          <div
            class="content"
            v-for="routeAttraction in routeAttractions"
            :key="routeAttraction.attractionNo"
            :routeAttraction="routeAttraction">
            <div class="attraction_title">
              {{ routeAttraction.attractionName }}
            </div>
            <div class="attraction_jibun">{{ routeAttraction.address }}</div>
            <div class="attraction_btns">
              <button
                class="btn deleteBtn"
                @click="showLocation(routeAttraction)">
                위치확인
              </button>
              <button class="btn addBtn" @click="addRoute(routeAttraction)">
                추가
              </button>
            </div>
          </div>
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
        <div class="container">
          <div
            class="content"
            v-for="travelRoute in travelRoutes"
            :key="travelRoute.attractionNo"
            :travelRoute="travelRoute">
            <div class="attraction_title">
              {{ travelRoute.attractionName }}
            </div>
            <div class="attraction_jibun">{{ travelRoute.address }}</div>
            <div class="attraction_btns">
              <button class="btn addBtn" @click="showLocation(travelRoute)">
                위치확인
              </button>
              <button class="btn deleteBtn" @click="deleteRoute(travelRoute)">
                삭제
              </button>
            </div>
          </div>
        </div>
        <div class="createBtn_div">
          <button
            type="button"
            class="btn searchBtn"
            style="background-color: #c3e5ee; margin-top: 20px"
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
import { mapActions, mapGetters } from "vuex";
export default {
  data() {
    return {
      map: null,
      openModal: false,
      searchTitle: null,
      travelRoutes: [],
      markers: [],
      lines: [],
      routeTitle: null,
      routeStartDate: null,
      routeEndDate: null,
      routeContent: null,
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
    ...mapActions(["getRouteAttractions", "createTravelRoute"]),
    initMap() {
      var mapContainer = document.getElementById("map"),
        mapOption = {
          center: new kakao.maps.LatLng(35.094846, 128.856055),
          level: 5,
        };
      this.map = new window.kakao.maps.Map(mapContainer, mapOption);
    },
    createFinalTravelRoute() {
      // console.log(this.travelRoutes);
      const payload = {
        travelRoutes: {
          userNo: this.userInfo.no,
          title: this.routeTitle,
          departDate: this.routeStartDate,
          arriveDate: this.routeEndDate,
          description: this.routeContent,
          attractionList: this.travelRoutes,
        },
        callback: (status) => {
          if (status == 200) {
            this.$bvToast.toast("여행 계획 등록 완료!", {
              title: "여행 계획",
              variant: "primary",
              toaster: "b-toaster-bottom-center",
              autoHideDelay: 2000,
              solid: true,
            });

            this.$router.push({ name: "TravelRouteList" });
          } else if (status == 500) {
            // 서버 오류 Toast 출력
            this.$bvToast.toast("서버 오류 발생!", {
              title: "여행 계획 알림",
              variant: "danger",
              toaster: "b-toaster-bottom-center",
              autoHideDelay: 3000,
              solid: true,
            });
          }
        },
      };
      this.createTravelRoute(payload);
      console.log("등록시: ", this.userInfo.no);
      console.log(
        this.routeTitle,
        this.routeStartDate,
        this.routeEndDate,
        this.routeContent
      );
      console.log(this.travelRoutes);
    },
    showLocation(routeAttraction) {
      this.map.setCenter(
        new kakao.maps.LatLng(
          routeAttraction.latitude,
          routeAttraction.longitude
        )
      );
    },
    addRoute(routeAttraction) {
      console.log(routeAttraction.attractionName, "추가되었습니다.");
      this.travelRoutes.push(routeAttraction);
      console.log(this.travelRoutes);

      var marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(
          routeAttraction.latitude,
          routeAttraction.longitude
        ),
      });

      this.map.setCenter(
        new kakao.maps.LatLng(
          routeAttraction.latitude,
          routeAttraction.longitude
        )
      );
      marker.setMap(this.map);

      this.markers.push(marker);

      // this.makeLine();
      this.makeLine(this.markers, "add");
    },
    deleteRoute(routeAttraction) {
      var flag = false;
      for (var i = 0; i < this.travelRoutes.length; i++) {
        if (this.travelRoutes[i].attractionNo == routeAttraction.attractionNo) {
          console.log(routeAttraction.attractionName, "삭제되었습니다.");
          this.travelRoutes.splice(i, 1);
          flag = true;
          break;
        }
      }

      console.log("삭제 여부", flag);
      console.log(this.travelRoutes);

      for (var m = 0; m < this.markers.length; m++) {
        console.log(this.markers[m].getPosition().getLat());
        console.log(routeAttraction.latitude);
        if (
          Math.abs(
            this.markers[m].getPosition().getLat() - routeAttraction.latitude
          ) <= 0.000000001 &&
          Math.abs(
            this.markers[m].getPosition().getLng() - routeAttraction.longitude
          ) <= 0.000000001
        ) {
          console.log("같은 마커 찾음!!!");

          this.markers[m].setMap(null);
          this.markers.splice(m, 1);
        }
      }

      // this.makeLine();
      this.makeLine(this.markers, "delete");
    },
    makeLine(markers, cmdName) {
      if (this.lines.length > 0) {
        for (var k = 0; k < this.lines.length; k++) {
          this.lines[k].setMap(null);
        }
      }

      var linePath = [];
      console.log(markers.length);
      for (var j = 0; j < markers.length; ++j) {
        linePath.push(markers[j].getPosition());
      }

      var polyLine = new kakao.maps.Polyline({
        path: linePath,
        strokeWeight: 4,
        strokeColor: "red",
        strokeOpacity: 0.7,
        strokeStyle: "solid",
      });

      this.lines.push(polyLine);

      polyLine.setMap(this.map);
      console.log(cmdName);
      // console.log(markers);
      // if (cmdName == "add") {
      // }

      // lines.push(polyLine);
      // if (cmdName == "delete") {
      //   console.log("삭제할 때 경로");

      // }
      // var linePath;
      // var lineLine = new kakao.maps.Polyline();
      // var distance;
      // console.log(distance);

      // for (var j = 0; j < this.travelRoutes.length; j++) {
      //   // console.log(this.travelRoutes[j].);
      //   if (j != 0) {
      //     linePath = [
      //       new kakao.maps.LatLng(
      //         this.travelRoutes[j - 1].latitude,
      //         this.travelRoutes[j - 1].longitude
      //       ),
      //       new kakao.maps.LatLng(
      //         this.travelRoutes[j].latitude,
      //         this.travelRoutes[j].longitude
      //       ),
      //     ];
      //   }
      //   lineLine.setPath(linePath);

      //   var drawLine = new kakao.maps.Polyline({
      //     map: this.map,
      //     path: linePath,
      //     strokeWeight: 3,
      //     strokeColor: "#db4040",
      //     strokeOpacity: 1,
      //     strokeStyle: "solid",
      //   });
      //   console.log(drawLine);

      //   distance = Math.round(lineLine.getLength());
      // }
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
    searchStart() {
      this.getRouteAttractions({
        searchTitle: this.searchTitle,
      });
    },
  },
  created() {},
  computed: {
    ...mapGetters(["routeAttractions", "userInfo"]),
  },
};
</script>

<style scoped>
.black-bg {
  width: 100%;
  height: 100%;
  position: fixed;
  z-index: 1;
}

.white-bg {
  font-family: "SUITE-Regular";
  width: 40%;
  height: 540px;
  margin: 80px auto;
  background: white;
  border-radius: 20px;
  padding: 30px 40px 20px 40px;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
}

.travelroute_create_title {
  font-size: 20px;
  font-weight: bold;
  color: #3a3a3a;
}

.create_title_input {
  margin-left: 5px;
  width: 548px;
  border: 1px solid #bbb;
  border-radius: 8px;
  padding: 10px 10px;
  font-size: 18px;
  display: inline;
  margin-left: 10px;
}

.create_calender {
  margin-top: 15px;
  color: #3a3a3a;
}

.start_date_text {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 7px;
}

.travelroute_create_content_text {
  font-size: 20px;
  font-weight: bold;
  color: #3a3a3a;
  margin-top: 20px;
}

.travelroute_create_content {
  width: 100%;
  height: 150px;
  border: 1px solid #bbb;
  border-radius: 8px;
  font-size: 16px;
  resize: none;
  padding: 20px;
}

.end_date_text {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 7px;
}

input[type="date"] {
  background-color: #f5f5f5;
  border-radius: 5px;
  border: 1px solid #cccccc;
  border-collapse: collapse;
  padding: 10px 10px 10px 10px;
  color: #555151;
}

input[type="date"]::before {
  content: attr(data-placeholder);
  width: 100%;
}

input[type="date"]:focus::before,
input[type="date"]:valid::before {
  display: none;
}

.travelroute_create_top {
  margin-bottom: 30px;
  font-size: 25px;
  font-weight: bolder;
  text-align: center;
  padding-left: 30px;
}

.close {
  cursor: pointer;
  border: none;
  background: #e94646;
  color: black;
  padding: 5px 15px;
  border-radius: 5px;
  font-size: 18px;
}

.close:hover {
  font-weight: bold;
  transform: scale(1.1);
  transition: all 0.5s;
}

.travelroute_create_btn {
  text-align: center;
  margin-top: 10px;
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

.container {
  margin-top: 30px;
  height: 500px;
  overflow-y: scroll;
  border: 1px solid #cccccc;
  border-collapse: collapse;
}

/* 아래의 모든 코드는 영역::코드로 사용 */
.container::-webkit-scrollbar {
  width: 10px; /* 스크롤바의 너비 */
}

.container::-webkit-scrollbar-thumb {
  height: 30%; /* 스크롤바의 길이 */
  background: #c3e5ee; /* 스크롤바의 색상 */

  border-radius: 10px;
}

.container::-webkit-scrollbar-track {
  background: #c3e5ee44; /*스크롤바 뒷 배경 색상*/
}

.content {
  width: 100%;
  height: 120px;
  padding: 20px 0px;
  border-collapse: collapse;
  margin-bottom: 15px;
}

.attraction_title {
  font-size: 18px;
  font-weight: bold;
  font-family: "SUITE-Regular";
}

.attraction_jibun {
  font-family: "SUITE-Regular";
  font-size: 16px;
}

.attraction_btns {
  margin-top: 15px;
  text-align: right;
  padding-right: 10px;

  font-size: 10px;
}

.addBtn {
  margin: 0px 5px;
  padding: 5px 10px;
  background-color: #c3e5ee;
}

.deleteBtn {
  margin: 0px 5px;
  padding: 5px 10px;
  background-color: #ffd5e3;
}
</style>

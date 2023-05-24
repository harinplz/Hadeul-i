<template>
  <div>
    <travel-route-header></travel-route-header>
    <div style="margin-top: 60px"></div>
    <div class="travelrt_map_desc_div row">
      <div class="map_div col-md-7">
        <div id="map"></div>
      </div>
      <div class="desc_div col-md-5">
        <div class="desc_title">{{ travelRoute.title }}</div>
        <div class="desc_time">
          시작일: {{ travelRoute.departDate }} <br />
          종료일: {{ travelRoute.arriveDate }}
        </div>
        <div class="desc_content">{{ travelRoute.description }}</div>
      </div>
    </div>
    <div class="goodBtn-div">
      <button
        type="button"
        class="btn goodBtn"
        style="background-color: #ffd5e3">
        <b>좋아요 <span>1</span></b>
      </button>
    </div>

    <!-- 댓글 구현 부분 -->
    <div class="travelrt_comment">
      <!-- 댓글 갯수 -->
      <div class="travelrt_comment_title">댓글 1</div>
      <!-- 댓글 등록 -->
      <div class="travelrt_comment_create row">
        <div class="col-md-10">
          <textarea
            class="travelrt_comment_create_input"
            placeholder="댓글을 입력해주세요 ... "></textarea>
        </div>
        <div class="col-md-2">
          <button
            type="button"
            class="btn commentBtn"
            style="background-color: #c3e5ee">
            입력
          </button>
        </div>
      </div>
    </div>
    <travel-route-comment></travel-route-comment>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  data() {
    return {
      map: null,
      travelRouteNo: "",
      travelRouteAttractions: [],
    };
  },
  components: {
    "travel-route-header": () =>
      import("@/components/travelroutes/include/TravelRouteHeader"),
    "travel-route-comment": () =>
      import("@/components/travelroutes/include/TravelRouteComment.vue"),
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
    ...mapActions(["getTravelRoute"]),
    initMap() {
      var mapContainer = document.getElementById("map"),
        mapOption = {
          center: new kakao.maps.LatLng(35.094846, 128.856055),
          level: 5,
        };
      this.map = new window.kakao.maps.Map(mapContainer, mapOption);
      // 경로 가져오는 함수 실행
      setTimeout(() => {
        this.showTravelRoutes();
      }, 300);
    },
    showTravelRoutes() {
      var markers = [];
      for (var i = 0; i < this.travelRouteAttractions.length; i++) {
        if (i == 0) {
          this.map.setCenter(
            new kakao.maps.LatLng(
              this.travelRouteAttractions[i].latitude,
              this.travelRouteAttractions[i].longitude
            )
          );
        }
        console.log(i);
        var marker = new kakao.maps.Marker({
          position: new kakao.maps.LatLng(
            this.travelRouteAttractions[i].latitude,
            this.travelRouteAttractions[i].longitude
          ),
        });
        markers.push(marker);
        marker.setMap(this.map);
      }

      var linePath = [];
      for (var j = 0; j < markers.length; j++) {
        linePath.push(markers[j].getPosition());
      }
      var polyLine = new kakao.maps.Polyline({
        path: linePath,
        strokeWeight: 4,
        strokeColor: "red",
        strokeOpacity: 0.7,
        strokeStyle: "solid",
      });

      polyLine.setMap(this.map);

      console.log(markers);
    },
  },
  created() {
    const travelRouteNo = this.$route.params.travelRouteNo;
    this.travelRouteNo = travelRouteNo;

    this.getTravelRoute({
      travelRouteNo,
    });

    setTimeout(() => {
      this.travelRouteAttractions = this.travelRoute.attractionList;
      console.log(this.travelRouteAttractions);
    }, 300);
  },
  computed: {
    ...mapGetters(["travelRoute", "userInfo"]),
  },
};
</script>

<style scoped>
.travelrt_map_desc_div {
  width: 70%;
  height: 500px;
  margin: auto;
}

#map {
  width: 100%;
  height: 100%;
}

.desc_div {
  font-family: "SUITE-Regular";
  background-color: rgb(255, 252, 246);
  padding: 30px;
  border-radius: 20px;
  box-shadow: 0 3px 6px rgba(0, 0, 0, 0.16), 0 3px 6px rgba(0, 0, 0, 0.23);
}
.goodBtn-div {
  margin-top: 50px;
  text-align: center;
}

.goodBtn {
  padding: 10px 30px;
}

.travelrt_comment {
  width: 80%;
  margin: 100px auto 30px auto;
}

.travelrt_comment_title {
  font-size: 18px;
  font-weight: bold;
  padding-bottom: 15px;
}

.travelrt_comment_create_input {
  width: 100%;
  height: 100px;
  border: 1px solid #bbb;
  border-radius: 8px;
  font-size: 16px;
  resize: none;
  padding: 20px;
}

.commentBtn {
  margin-top: 7px;
  width: 80%;
  height: 80%;
  font-size: 15px;
  font-weight: bold;
}

.desc_title {
  font-weight: bold;
  font-size: 30px;
  border-bottom: 1px solid #cacaca;
  padding-bottom: 5px;
}

.desc_time {
  font-size: 20px;
  margin: 20px 0px;
  padding-bottom: 15px;
  border-bottom: 1px solid #cacaca;
}

.desc_content {
  font-size: 18px;
}
</style>

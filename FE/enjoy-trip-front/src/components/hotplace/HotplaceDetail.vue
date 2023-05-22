<template>
  <div>
    <hotplace-header></hotplace-header>
    <!-- 지도와 설명 div  -->
    <div style="margin-top: 50px">
      <!-- 제목과 작성자 div -->
      <div class="hotpl_desc_title_area">
        <div class="hotpl-desc-title" v-if="hotplace">
          [{{ hotplace.category }}] {{ hotplace.hotplaceName }}
        </div>
        <div class="hotpl-desc-writer" v-if="user">{{ user.name }}님</div>
      </div>

      <div class="row hotpl-map-desc">
        <div class="col-md-6 hotpl-img wow fadeInLeft">
          <img class="img polaroid" :src="this.imgSrc" />
        </div>
        <div></div>
        <div class="col-md-6 hotpl-map">
          <label
            for="hotpl-pos"
            style="padding-left: 20px; font-size: 18px; font-family: 'SUITE-Regular'"
            ><b>📍 핫플레이스 위치</b></label
          >
          <p class="hotpl_pos_jibun" v-if="hotplace">
            {{ hotplace.jibun }}
            <span v-if="hotplace.hotplaceAddr != 'null'"> {{ hotplace.hotplaceAddr }}</span>
          </p>

          <br />
          <div id="map"></div>
          <label
            for="hotpl-desc-content"
            style="
              padding-left: 20px;
              margin-top: 20px;
              font-size: 18px;
              font-family: 'SUITE-Regular';
            "
            ><b>📍 핫플레이스 설명</b></label
          >
          <br />
          <div class="hotpl-desc-content" v-if="hotplace">{{ hotplace.hotplaceContent }}</div>
        </div>
      </div>

      <!-- 좋아요 버튼 -->
      <div class="goodBtn-div">
        <button type="button" class="btn goodBtn" style="background-color: #ffd5e3">
          <b>좋아요 1</b>
        </button>
      </div>
      <!-- 삭제 버튼 -->
      <div class="list_delete_btns">
        <span
          ><router-link :to="{ name: 'HotplaceList' }"
            ><button type="button" class="btn listBtn" style="background-color: #c3e5ee">
              목록
            </button></router-link
          ></span
        >
        <span v-if="userInfo && user">
          <span v-if="userInfo.no == user.no || userInfo.id == 'admin'" class="deleteBtn-div">
            <button
              type="button"
              class="btn deleteBtn"
              style="background-color: #c3e5ee"
              @click="showModalDelete"
            >
              <b>삭제</b>
            </button>
          </span>
        </span>
      </div>
    </div>

    <!-- 댓글 구현 부분 -->
    <!-- <div class="hotpl_comment"> -->
    <!-- 댓글 갯수 -->
    <!-- <div class="hotpl_comment_title">댓글 1</div> -->
    <!-- 댓글 등록 -->
    <!-- <div class="hotpl_comment_create row">
        <div class="col-md-10">
          <textarea
            class="hotpl_comment_create_input"
            placeholder="댓글을 입력해주세요 ... "
          ></textarea>
        </div>
        <div class="col-md-2">
          <button type="button" class="btn commentBtn" style="background-color: #c3e5ee">
            입력
          </button>
        </div>
      </div>
    </div> -->
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  data() {
    return {
      map: null,
      hotplaceNo: "",
      userNo: "",
      imgSrc: null,
    };
  },
  components: {
    "hotplace-header": () => import("@/components/hotplace/include/HotplaceHeader.vue"),
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
    ...mapActions(["getHotplace", "getUser", "deleteHotplace"]),
    initMap() {
      var mapContainer = document.getElementById("map"),
        mapOption = {
          center: new window.kakao.maps.LatLng(this.hotplace.latitude, this.hotplace.longitude),
          level: 5,
        };
      this.map = new window.kakao.maps.Map(mapContainer, mapOption);

      var markerPosition = new kakao.maps.LatLng(this.hotplace.latitude, this.hotplace.longitude);

      var marker = new kakao.maps.Marker({
        position: markerPosition,
      });

      marker.setMap(this.map);

      // 커스텀 오버레이에 표시할 컨텐츠 입니다
      // 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
      // 별도의 이벤트 메소드를 제공하지 않습니다

      setTimeout(() => {
        var content =
          '<div style="background-color: #C3E5EE;margin-bottom: 120px; padding: 6px 10px; border-radius: 20px;' +
          'font-weight: bold; color: #616161; font-size: 14px; ">' +
          this.hotplace.hotplaceName;
        +"</div>";

        // 마커 위에 커스텀오버레이를 표시합니다
        // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
        var overlay = new kakao.maps.CustomOverlay({
          content: content,
          map: this.map,
          position: marker.getPosition(),
        });

        // 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
        kakao.maps.event.addListener(marker, "click", function () {
          overlay.setMap(this.map);
        });
      }, 50);
    },
    showModalDelete() {
      this.$bvModal
        .msgBoxConfirm(`핫플레이스를 삭제하시겠습니까?`, {
          centered: true,
          size: "sm",
          buttonSize: "sm",
          okVariant: "danger",
          okTitle: "네",
          cancelTitle: "아니오",
        })
        .then((value) => {
          if (value) {
            this.delete();
          }
        });
    },
    delete() {
      const payload = {
        hotplaceNo: this.hotplaceNo,
        callback: (status) => {
          if (status == 200) {
            // 삭제 완료 toast 출력
            setTimeout(() => {
              this.$bvToast.toast(`핫플레이스가 삭제되었습니다.`, {
                title: "핫플레이스 삭제 완료!",
                variant: "danger",
                toaster: "b-toaster-bottom-center",
                autoHideDelay: 3000,
                solid: true,
              });
            }, 500);

            this.$router.push({ name: "HotplaceList" });
          } else if (status == 500) {
            // 서버 오류 Toast 출력
            this.$bvToast.toast("서버 오류 발생!", {
              title: "서버 오류 발생",
              variant: "danger",
              toaster: "b-toaster-bottom-center",
              autoHideDelay: 3000,
              solid: true,
            });
          }
        },
      };
      this.deleteHotplace(payload);
    },
  },
  created() {
    const hotplaceNo = this.$route.params.hotplaceNo;
    this.hotplaceNo = hotplaceNo;

    this.getHotplace({
      hotplaceNo,
    });

    this.imgSrc = "http://localhost/hotplace/display?filename=";
    setTimeout(() => {
      const userNo = this.hotplace.userNo;
      this.userNo = userNo;
      this.getUser({
        userNo,
      });

      this.imgSrc += this.hotplace.img;
    }, 100);
  },
  computed: {
    ...mapGetters(["hotplace", "user", "userInfo"]),
  },
};
</script>

<style scoped>
.overlay {
  background-color: white;
  border: 1px solid black;
  padding: 10px;
}

.hotpl_desc_title_area {
  border-bottom: 1px solid #c4c4c4;
  width: 80%;
  height: 100px;
  margin: auto;
  font-family: "SUITE-Regular";
  color: #414141;
}

.hotpl-desc-writer {
  padding-top: 50px;
  padding-right: 20px;
  font-size: 20px;
  float: right;
}

.hotpl-map-desc {
  height: 600px;
  padding-top: 20px;
  margin: auto;
  width: 80%;
}

#map {
  height: 40%;
}

.hotpl-desc {
  height: 54%;
}

.hotpl-img {
  width: 100%;
  height: 100%;
  margin: auto;
}

.img {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}

.polaroid {
  background: #ffffff; /*Change this to a background image or remove*/
  border: solid #fff;
  border-width: 25px 25px 100px 25px;
  box-shadow: 1px 1px 10px #333; /* Standard blur at 5px. Increase for more depth */
  -webkit-box-shadow: 1px 1px 7px #7e7e7e;
  -moz-box-shadow: 1px 1px 7px #7e7e7e;
}

.hotpl_pos_jibun {
  margin-bottom: -10px;
  padding-left: 20px;
}

.hotpl-desc {
  padding-left: 20px;
}

.hotpl-desc-title {
  font-size: 30px;
  font-weight: bolder;
  padding-top: 30px;
  float: left;
}

.hotpl-desc-content {
  padding-left: 20px;
}

.goodBtn-div {
  margin-top: 100px;
  text-align: center;
}

.goodBtn {
  padding: 10px 30px;
}

.deleteBtn {
  padding: 10px 30px;
}

.listBtn {
  padding: 10px 30px;
  margin-right: 5px;
}

.list_delete_btns {
  text-align: right;
  width: 90%;
  margin-top: 100px;
  margin-bottom: 200px;
}
.hotpl_comment {
  width: 80%;
  margin: 100px auto 0px auto;
}

.hotpl_comment_title {
  font-size: 18px;
  font-weight: bold;
  padding-bottom: 15px;
}

.hotpl_comment_create_input {
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

.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 288px;
  height: 132px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  line-height: 1.5;
}
.wrap * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  width: 286px;
  height: 120px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}
.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info .title {
  padding: 5px 0 0 10px;
  height: 30px;
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
}
.info .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
}
.info .close:hover {
  cursor: pointer;
}
.info .body {
  position: relative;
  overflow: hidden;
}
.info .desc {
  position: relative;
  margin: 13px 0 0 90px;
  height: 75px;
}
.desc .ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.desc .jibun {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}
.info .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  border: 1px solid #ddd;
  color: #888;
  overflow: hidden;
}
.info:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.info .link {
  color: #5085bb;
}
</style>

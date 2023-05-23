<template>
  <div id="bg">
    <div class="hotpl_create_title wow fadeInUp" data-wow-delay="0.05s">
      <div class="hotpl_create_title_text">
        당신의 <b>핫플레이스</b>를 자랑해주세요!
      </div>
    </div>
    <div
      class="map-and-create row g-0 align-items-center flex-column-reverse flex-md-row">
      <div class="wow fadeInUp col-md-6" data-wow-delay="0.1s">
        <div id="map"></div>
      </div>
      <!-- <transition name="fade"> -->
      <div class="wow fadeInUp create col-md-6" data-wow-delay="0.1s">
        <div class="create-content">
          <label for="hotpl-cate-label">핫플레이스 유형</label>
          <div style="margin-bottom: 20px">
            <select id="searchSelect" v-model="hotplace.category">
              <option selected>핫플레이스 유형</option>
              <option value="관광지">관광지</option>
              <option value="문화시설">문화시설</option>
              <option value="축제공연행사">축제공연행사</option>
              <option value="여행코스">여행코스</option>
              <option value="레포츠">레포츠</option>
              <option value="숙박">숙박</option>
              <option value="쇼핑">쇼핑</option>
              <option value="음식점">음식점</option>
            </select>
          </div>
        </div>
        <div class="input-box">
          <label for="hotpl-name-label">핫플레이스 이름</label> <br />
          <input
            class="hotplace-name"
            type="text"
            placeholder="핫플레이스 이름"
            v-model="hotplace.hotplaceName" />
        </div>
        <div class="input-box">
          <label for="hotpl-addr-label">핫플레이스 추가 주소</label> <br />
          <input
            class="hotplace-addr"
            type="text"
            placeholder="핫플레이스 추가 주소"
            v-model="hotplace.hotplaceAddr" />
        </div>
        <div class="hotpl-img">
          <label for="hotpl-img-label">핫플레이스 사진</label> <br />
          <!-- <input type="file"> -->
          <form enctype="multipart/form-data">
            <input
              type="file"
              id="upfile"
              name="upfile"
              @change="fileSelect()"
              ref="upfile" />
          </form>
          <!-- <b-form-file multiple v-model="upfile">
            <template slot="file-name" slot-scope="{ names }">
              <b-badge variant="dark">{{ names[0] }}</b-badge>
              <b-badge v-if="names.length > 1" variant="dark" class="ml-1">
                + {{ names.length - 1 }} More files
              </b-badge>
            </template>
          </b-form-file> -->
        </div>
        <div class="hotpl-desc">
          <label for="hotpl-desc-label">핫플레이스 설명</label> <br />
          <textarea
            class="hotpl-desc-text"
            placeholder="나만의 핫플레이스를 자랑해주세요!"
            v-model="hotplace.hotplaceContent" />
        </div>
      </div>
      <!-- </transition> -->
    </div>
    <div class="create-list-btns wow fadeInUp">
      <router-link :to="{ name: 'HotplaceList' }">
        <button
          type="button"
          class="btn hotplBtn"
          style="background-color: #c3e5e5">
          <b>목록</b>
        </button>
      </router-link>
      <button
        type="button"
        class="btn hotplBtn"
        style="background-color: #ffd5e3"
        @click="testUser">
        <b>등록</b>
      </button>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

export default {
  data() {
    return {
      map: null,
      hotplace: {
        userNo: null,
        category: "핫플레이스 유형",
        hotplaceName: null,
        hotplaceAddr: null,
        hotplaceContent: null,
        jibun: null,
        latitude: null,
        longitude: null,
      },
      upfile: null,
      frm: null,
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      console.log("2번쨰");
      this.initMap();
    } else {
      console.log("첫번쨰");
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=70697613147c4c88e83fb654db4eed6e&libraries=services";
      document.head.appendChild(script);
    }
  },
  methods: {
    ...mapActions(["createHotplace"]),
    fileSelect() {
      console.log(this.$refs);
      this.upfile = this.$refs.upfile.files[0];
      console.log(this.upfile);
    },
    upload() {
      this.frm = new FormData();
      this.frm.append("upfile", this.upfile);
      this.frm.append("userNo", this.hotplace.userNo);
      this.frm.append("category", this.hotplace.category);
      this.frm.append("hotplaceName", this.hotplace.hotplaceName);
      this.frm.append("hotplaceAddr", this.hotplace.hotplaceAddr);
      this.frm.append("hotplaceContent", this.hotplace.hotplaceContent);
      this.frm.append("jibun", this.hotplace.jibun);
      this.frm.append("latitude", this.hotplace.latitude);
      this.frm.append("longitude", this.hotplace.longitude);

      for (let key of this.frm.entries()) {
        console.log(`${key}`);
      }

      const payload = {
        hotplace: this.hotplace,
        frm: this.frm,
        callback: (status) => {
          if (status == 201) {
            setTimeout(() => {
              this.$bvToast.toast(`핫플레이스가 등록 되었습니다.`, {
                title: "핫플레이스 등록 완료!",
                variant: "primary",
                toaster: "b-toaster-bottom-center",
                autoHideDelay: 3000,
                solid: true,
              });
            }, 500);

            this.$router.push({ name: "HotplaceList" });

            console.log("등록 완료!!");
          } else if (status == 500) {
            console.log("서버 오류 발생!!");
          }
        },
      };
      this.createHotplace(payload);
    },
    testUser() {
      this.hotplace.userNo = this.userInfo.no;
      this.$bvModal
        .msgBoxConfirm(`핫플레이스를 등록하시곘습니까?`, {
          centered: true,
          size: "sm",
          buttonSize: "sm",
          okVariant: "primary ",
          okTitle: "네",
          cancelTitle: "아니오",
        })
        .then((value) => {
          if (value) {
            this.upload();
          }
        });
    },
    initMap() {
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(35.163759, 129.159066),
        level: 5,
      };

      var map = new kakao.maps.Map(container, options);

      // 주소-좌표 변환 객체를 생성합니다
      var geocoder = new kakao.maps.services.Geocoder();

      var marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
        infowindow = new kakao.maps.InfoWindow({ zindex: 1 }); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다

      // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
      searchAddrFromCoords(map.getCenter(), displayCenterInfo);

      const thiz = this;
      // 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
      kakao.maps.event.addListener(map, "click", function (mouseEvent) {
        searchDetailAddrFromCoords(
          mouseEvent.latLng,
          function (result, status) {
            if (status === kakao.maps.services.Status.OK) {
              thiz.hotplace.jibun = result[0].address.address_name;
              console.log("jibun:", result[0].address.address_name);

              thiz.hotplace.latitude = mouseEvent.latLng.getLat();
              thiz.hotplace.longitude = mouseEvent.latLng.getLng();

              var detailAddr = "";
              if (result[0].road_address) {
                detailAddr =
                  "<div>도로명주소 : " +
                  result[0].road_address.address_name +
                  "</div>";
              }
              detailAddr +=
                "<div>지번 주소 : " + result[0].address.address_name + "</div>";

              var content =
                '<div class="bAddr">' +
                '<span class="title"><b>법정동 주소정보</b></span>' +
                detailAddr +
                "</div>";

              // 마커를 클릭한 위치에 표시합니다
              marker.setPosition(mouseEvent.latLng);
              marker.setMap(map);

              // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
              infowindow.setContent(content);
              infowindow.open(map, marker);
            }
          }
        );
      });

      // 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
      kakao.maps.event.addListener(map, "idle", function () {
        searchAddrFromCoords(map.getCenter(), displayCenterInfo);
      });

      function searchAddrFromCoords(coords, callback) {
        // 좌표로 행정동 주소 정보를 요청합니다
        geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
      }

      function searchDetailAddrFromCoords(coords, callback) {
        // 좌표로 법정동 상세 주소 정보를 요청합니다
        geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
      }

      // 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
      function displayCenterInfo(result, status) {
        if (status === kakao.maps.services.Status.OK) {
          for (var i = 0; i < result.length; i++) {
            // 행정동의 region_type 값은 'H' 이므로
            if (result[i].region_type === "H") {
              console.log(result[i].address_name);
              break;
            }
          }
        }
      }
    },
  },
  computed: {
    ...mapGetters(["userInfo"]),
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
  height: 690px;
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

#searchSelect {
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

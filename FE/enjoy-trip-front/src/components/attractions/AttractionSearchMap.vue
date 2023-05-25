<template>
  <b-container>
    <!-- 중앙 center content end -->
    <div class="col-md-12" id="parent-sticky">
      <div class="hotpl_create_title wow fadeInUp">
        <div class="hotpl_create_title_text">전국 관광지 정보</div>
      </div>
      <div style="margin-bottom: 50px"></div>
      <!-- 관광지 검색 start -->
      <div class="sticky">
        <br />
        <form action="" id="search-form">
          <div class="select_div">
            <select
              id="search-area"
              name="search-area"
              class="searchSelect"
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
              class="searchSelect"
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
              id="table-type"
              name="table-type"
              class="searchSelect"
              v-model="tableType">
              <option value="0">검색필터</option>
              <option value="1">유명관광지</option>
              <option value="2">핫플레이스</option>
            </select>
          </div>
          <div style="text-align: center; margin-top: 10px">
            <input
              id="search-keyword"
              class="searchInput"
              name="search-keyword"
              type="text"
              placeholder="검색어"
              aria-label="검색어"
              v-model="searchKeyword" />

            <button
              id="btn-search"
              class="btn searchBtn"
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
      <!-- 
      <the-pagination
        v-if="this.tableType == '1'"
        :pageSetting="pageDataSetting(total1, limit, block, this.page)"
        @paging="pagingMethod" />
      <the-pagination
        v-else-if="this.tableType == '2'"
        :pageSetting="pageDataSetting(total2, limit, block, this.page)"
        @paging="pagingMethod" />
      <the-pagination
        v-else
        :pageSetting="pageDataSetting(total, limit, block, this.page)"
        @paging="pagingMethod" /> -->
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
      <b-container v-if="modalContent" fluid>
        <img :src="modalContent.imageUrl" style="width: 100%; height: 100%" />

        <b-row class="text-center">
          <b-col><h5>주소</h5></b-col>
        </b-row>
        <p align="center">{{ modalContent.address }}</p>

        <b-row class="text-center">
          <b-col><h5>관광지명</h5></b-col>
        </b-row>
        <p align="center">{{ modalContent.attractionName }}</p>

        <b-row class="text-center mt-2">
          <b-col><h5>상세 설명</h5></b-col>
        </b-row>
        <p align="center">
          {{ modalContent.description }}
        </p>

        <hr style="background-color: rgb(23, 162, 184)" />
        <b-row class="text-center">
          <b-col>
            <button
              type="button"
              class="btn"
              style="background-color: #ffd5e3"
              @click="
                likeEvent(
                  modalContent.attractionNo,
                  modalContent.attractionType
                )
              ">
              <b
                >좋아요<span>
                  {{ attractionLike }}
                </span>
              </b>
            </button>
          </b-col>
        </b-row>
      </b-container>
    </b-modal>
    <div style="margin-bottom: 100px"></div>
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
      tableType: "0",
      total: 0,
      total1: 0,
      total2: 0,
      page: 1,
      limit: 20,
      block: 10,
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
    // "the-pagination": () =>
    //   import("@/components/attractions/include/ThePagination.vue"),
  },
  computed: {
    attractions() {
      //getter: attractions 정보 얻어옴
      /* eslint-disable */
      return this.$store.getters.attractions;
    },

    attractionLikeCheck() {
      return this.$store.getters.attractionLikeCheck;
    },

    attractionLike() {
      return this.$store.getters.attractionLike;
    },

    attractionDetail() {
      return this.$store.getters.attractionDetail;
    },

    userInfo() {
      return this.$store.getters.userInfo;
    },
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.loadMap();
    } else {
      this.loadScript();
    }

    this.pagingMethod(this.page);
  },
  created() {
    //페이지네이션을 위한 모든 record 조회
    // http.get(`/trips/type/1`).then(({ status, data }) => {
    //   if (status == 200) {
    //     this.total1 = data;
    //     console.log("1번 테이블의 total : ", this.total1);
    //     this.pagingMethod(1);
    //   }
    // });
    // http.get(`/trips/type/2`).then(({ status, data }) => {
    //   if (status == 200) {
    //     this.total2 = data;
    //     console.log("2번 테이블의 total : ", this.total2);
    //     this.pagingMethod(1);
    //   }
    // });
  },
  methods: {
    getAttractionCheckLike(attractionLike) {
      this.$store.dispatch("getAttractionCheckLike", { attractionLike });
    },
    getAttractionDetail(attractionInfo) {
      this.$store.dispatch("getAttractionDetail", { attractionInfo });
    },
    getAttractionLike(attractionInfo) {
      this.$store.dispatch("getAttractionLike", { attractionInfo });
    },
    createAttractionLike(attractionInfo) {
      this.$store.dispatch("createAttractionLike", { attractionInfo });
    },
    deleteAttractionLike(attractionInfo) {
      this.$store.dispatch("deleteAttractionLike", { attractionInfo });
    },

    pagingMethod(page) {
      //페이지 클릭 시 일어나는 일들..!

      // this.listData = this.paymentInfo.slice(
      //   (page - 1) * this.limit,
      //   page * this.limit
      // );

      //attractions 새로 불러오기 되려나?

      this.page = page;
      this.pageDataSetting(this.total, this.limit, this.block, page);
      //this.getList(page);
    },

    pageDataSetting(total, limit, block, page) {
      //이전, 다음 클릭 시, 페이지 이동이 되도록 함..
      //페이지를 블록 단위로 보여주기..!
      const totalPage = Math.ceil(total / limit); //총 몇 개의 페이지가 나와야 하는지?

      console.log(total, " 개 있습니다");
      let currentPage = page; //현재 페이지

      const first =
        currentPage > 1 ? parseInt(currentPage, 10) - parseInt(1, 10) : null;
      const end =
        totalPage !== currentPage
          ? parseInt(currentPage, 10) + parseInt(1, 10)
          : null;

      let startIndex = (Math.ceil(currentPage / block) - 1) * block + 1;
      let endIndex =
        startIndex + block > totalPage ? totalPage : startIndex + block - 1;
      let list = [];
      for (let index = startIndex; index <= endIndex; index++) {
        list.push(index);
      }
      return { first, end, list, currentPage };
    },

    likeEvent(no, type) {
      if (this.attractionLikeCheck == 0) {
        this.createGoodBtn(no, type);
      } else {
        let flag = confirm("좋아요를 누르셨습니다 삭제하시겠습니까?");
        if (flag) {
          this.deleteGoodBtn(no, type);
        }
      }
    },

    deleteGoodBtn(no, type) {
      const payload = {
        info: {
          attractionNo: no,
          attractionType: type,
          userNo: this.userInfo.no,
        },

        callback: (status) => {
          if (status == 200) {
            this.getAttractionLike({
              attractionNo: no,
              attractionType: type,
            });

            this.getAttractionCheckLike({
              attractionNo: no,
              attractionType: type,
              userNo: this.userInfo.no,
            });
          } else if (status == 500) {
            this.$bvToast.toast("서버 오류 발생!", {
              title: "trip like 서버 오류 발생",
              variant: "danger",
              toaster: "b-toaster-bottom-center",
              autoHideDelay: 3000,
              solid: true,
            });
          }
        },
      };

      this.deleteAttractionLike(payload);
    },

    createGoodBtn(no, type) {
      const payload = {
        info: {
          attractionNo: no,
          attractionType: type,
          userNo: this.userInfo.no,
        },

        callback: (status) => {
          if (status == 200) {
            console.log("11111111111");
            this.getAttractionLike({
              attractionNo: no,
              attractionType: type,
            });

            console.log("222222222");

            this.getAttractionCheckLike({
              attractionNo: no,
              attractionType: type,
              userNo: this.userInfo.no,
            });

            console.log(
              "created 시 getAttractionCheckLike됐나 확인 : ",
              this.attractionLikeCheck
            );
          } else if (status == 500) {
            this.$bvToast.toast("서버 오류 발생!", {
              title: "trip like 서버 오류 발생",
              variant: "danger",
              toaster: "b-toaster-bottom-center",
              autoHideDelay: 3000,
              solid: true,
            });
          }
        },
      };

      this.createAttractionLike(payload);
    },

    searchTrips() {
      const payload = {
        attractionSearchInfo: {
          "search-area": this.searchArea,
          "search-content-id": this.searchContentId,
          "search-keyword": this.searchKeyword,
          "table-type": this.tableType,
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
        let markerInfo = {
          title: this.attractions[i].title,

          //마커 마우스 오버시 나오는 메세지 작성 부분
          content:
            '<div class="overlaybox">' +
            '<div id="overTitle" style="text-align:center">관광 정보</div>' +
            '<div id="overlayContent" style="display:flex; align-items: center">' +
            '<div style="margin-right:6px"><img style="width:120px; height:100%" src="' +
            this.attractions[i].imageUrl +
            '" style="justify-content: center"></div>' +
            '<div style="margin-top : 8px;">' +
            this.attractions[i].attractionName +
            "<br>" +
            this.attractions[i].address +
            "<br>" +
            '<div class="text-right" style="margin-left: auto; text-decoration: underline;">클릭 시 상세보기</div>' +
            "</div>" +
            "</div>",

          latlng: new window.kakao.maps.LatLng(
            this.attractions[i].latitude,
            this.attractions[i].longitude
          ),
        };

        positions.push(markerInfo);
      }

      var imageSrc =
        "https://cdn4.iconfinder.com/data/icons/map-navigation-navy-vol-1/512/Destination_Marker-256.png";

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
        const userNo = thiz.userInfo.no;

        let info = {
          attractionNo: attInfo.attractionNo,
          attractionType: attInfo.attractionType,
          userNo: userNo,
        };
        thiz.getAttractionCheckLike(info);

        thiz.getAttractionDetail(info);

        thiz.getAttractionLike(info);

        attInfo.description = thiz.attractionDetail;

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

b-container {
  font-family: "SUITE-Regular";
}

.select_div {
  text-align: center;
}
.overlaybox {
  overflow: hidden;
  width: 300px;
  height: auto;
  padding: 3px;
}

overlaybox div,
ul {
  overflow: hidden;
  margin: 0;
  padding: 0;
}

#overTitle {
  background: #ffd5e3;
  font-weight: bold;
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
  background-color: rgba(243, 243, 243, 0.6);
  border-radius: 30px;
  margin: auto;
}

.searchSelect {
  border: 1px solid #bbb;
  border-radius: 8px;
  padding: 10px 12px;
  font-size: 14px;
  margin: 0px 2px;
}

.searchInput {
  margin-right: 5px;
  width: 500px;
  border: 1px solid #bbb;
  border-radius: 8px;
  padding: 10px 12px;
  font-size: 14px;
  display: inline;
}

.searchBtn {
  padding: 10px 30px;
  background-color: #c3e5ee;
}
</style>

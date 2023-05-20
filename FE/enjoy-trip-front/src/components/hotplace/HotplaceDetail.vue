<template>
  <div>
    <hotplace-header></hotplace-header>
    <!-- 지도와 설명 div  -->
    <div style="margin-top: 50px">
      <!-- 제목과 작성자 div -->
      <div class="hotpl_desc_title_area">
        <div class="hotpl-desc-title">[유형]제목</div>
        <div class="hotpl_desc_writer_regtime">
          <div class="hotpl-desc-writer">작성자</div>
          <div class="hotpl_desc_regtime">작성날짜</div>
        </div>
      </div>

      <div class="row hotpl-map-desc">
        <div class="col-md-6 hotpl-img">
          <img class="img" src="@/assets/img/hotplace-header-img1.jpg" />
        </div>
        <div></div>
        <div class="col-md-6 hotpl-map">
          <label for="hotpl-pos" style="padding-left: 20px; font-size: 18px"
            ><b>📍 핫플레이스 위치</b></label
          >
          <br />
          <div id="map"></div>
          <label
            for="hotpl-desc-content"
            style="padding-left: 20px; margin-top: 20px; font-size: 18px"
            ><b>📍 핫플레이스 설명</b></label
          >
          <br />
          <div class="hotpl-desc-content">설명입니다.</div>
        </div>
      </div>

      <!-- 좋아요 버튼 -->
      <div class="goodBtn-div">
        <button type="button" class="btn goodBtn" style="background-color: #ffd5e3">
          <b>좋아요 1</b>
        </button>
      </div>
    </div>

    <!-- 댓글 구현 부분 -->
    <div class="hotpl_comment">
      <!-- 댓글 갯수 -->
      <div class="hotpl_comment_title">댓글 1</div>
      <!-- 댓글 등록 -->
      <div class="hotpl_comment_create row">
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
    </div>

    <div style="margin-top: 100px; height: 300px"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      map: null,
    };
  },
  components: {
    "hotplace-header": () => import("@/components/hotplace/include/HotplaceHeader.vue"),
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
.hotpl_desc_title_area {
  border-bottom: 1px solid #c4c4c4;
  width: 80%;
  height: 100px;
  margin: auto;
}

.hotpl-desc-writer {
  padding-top: 30px;
  padding-right: 20px;
  font-size: 20px;
}

.hotpl_desc_writer_regtime {
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
  background-color: aqua;
}

.img {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
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
  margin-top: 50px;
  text-align: center;
}

.goodBtn {
  padding: 10px 30px;
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
</style>

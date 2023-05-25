<template>
  <div>
    <div class="background">
      <div class="img_div">
        <div
          class="title_desc wow fadeInUp"
          data-wow-duration="2s"
          data-wow-delay="0.3s">
          하하 웃음이 나는 나들이
        </div>
        <div
          class="title wow fadeInUp"
          data-wow-duration="2s"
          data-wow-delay="0.8s">
          하들이
        </div>
        <div
          class="desc wow fadeInUp"
          data-wow-duration="2s"
          data-wow-delay="1.2s">
          <div>여러분들은 어떤 여행을 떠나실 계획이신가요?</div>
          <div>좋아하는 장소가 있으신가요?</div>
          <div>하들이를 통해 추억을 만들어봐요!</div>
        </div>
        <div
          class="go wow fadeInUp"
          data-wow-duration="2s"
          data-wow-delay="1.5s">
          <router-link :to="{ name: 'TravelRoute' }" class="no_link">
            <div class="travelroute">
              <div style="height: 75px"></div>
              <span class="travelroute_text">여행 계획하기</span>
            </div>
          </router-link>
          <router-link :to="{ name: 'Hotplace' }" class="no_link">
            <div class="hotplace">
              <div style="height: 90px"></div>
              <span>핫플레이스 자랑하기</span>
            </div>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.background {
  width: 100%;
  height: 850px;
  background-color: rgb(255, 255, 255);
  font-family: "SUITE-Regular";
  margin-top: -50px;
}

.img_div {
  text-align: center;
  height: 900px;
  background-image: url("@/assets/img/blue-5136251_960_720.jpg");
  background-repeat: no-repeat;
  background-size: cover;
}

.title_desc {
  padding-top: 320px;
  font-size: 20px;
}

.title {
  font-size: 50px;
  font-weight: bolder;
}

.highlight {
  display: inline;
  box-shadow: inset 0 -10px 0 #ffffff54;
}

.desc {
  margin-top: 30px;
  font-size: 18px;
}

.go {
  margin-top: 150px;
  height: 180px;
}

.travelroute {
  display: inline-block;
  text-align: center;
  height: 100%;
  width: 300px;
  margin-right: 500px;
  background-image: url("@/assets/img/left_cloud.png");
  background-repeat: no-repeat;
  background-size: cover;
}

.travelroute_text {
  /* float: left; */
  font-size: 18px;
  font-weight: bold;
}

.hotplace {
  display: inline-block;
  /* float: right; */
  font-weight: bold;
  font-size: 18px;
  height: 100%;
  width: 280px;
  background-image: url("@/assets/img/right_cloud.png");
  background-repeat: no-repeat;
  background-size: cover;
}

.no_link {
  color: rgb(31, 31, 31);
  text-decoration: none;
}
</style>

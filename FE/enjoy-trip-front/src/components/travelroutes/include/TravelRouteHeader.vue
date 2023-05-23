<template>
  <div>
    <div id="travel_route_header" class="container-fluid header p-0 wow fadeIn">
      <div class="row g-0 align-items-center flex-column-reverse flex-md-row">
        <div id="travel_route_desc" class="col-md-6">
          <div class="travel_route_text">
            <div class="travel_route_desc_subject">여행 계획</div>
            <div>당신만의 <b>여행 계획</b>을 자랑해보세요!</div>
          </div>
        </div>
        <div id="travel_route_pic" class="col-md-6">
          <img class="img" src="@/assets/img/travelroute-header-img.jpg" />
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
#travel_route_header {
  height: 450px;
  width: 80%;
  margin: auto;
}
#travel_route_desc {
  background-color: #fafafa;
  height: 450px;
}
#travel_route_pic {
  height: 450px;
}
.travel_route_text {
  padding-top: 180px;
  padding-left: 30px;
}
.travel_route_desc_subject {
  font-size: 30px;
}
.img {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
</style>

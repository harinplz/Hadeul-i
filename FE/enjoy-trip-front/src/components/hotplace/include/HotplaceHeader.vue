<template>
    <div>
        <div id="hotplace-header" class="container-fluid header p-0">
            <div class="row g-0 align-items-center flex-column-reverse flex-md-row">
                <div id="hotplace-desc" class="col-md-6">
                    <div class="hotplace-desc-text">
                        <div class="hotplace-desc-subject">핫플레이스</div>
                        <div>당신만의 <b>핫플레이스</b>를 자랑해보세요!</div>
                    </div>
                </div>
                <div id="hotplace-pic" class="col-md-6">
                    <img class="img" src="@/assets/img/hotplace-header-img.jpg">
                </div>
            </div>
        </div>
    </div>
</template>



<style scoped>
#hotplace-header {
    height: 450px;
    width: 80%;
    margin: auto;
}
#hotplace-desc {
    background-color: #FAFAFA;
    height: 450px;
}
#hotplace-pic {
    height: 450px;
}
.hotplace-desc-text{
    padding-top: 180px;
    padding-left: 30px;
}
.hotplace-desc-subject{
    font-size: 30px;
}
.img {
    width: 100%;
    height: 100%;
    overflow: hidden;
    margin: 0 auto;
}
</style>
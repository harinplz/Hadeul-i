<template>
  <div>
    <hotplace-header></hotplace-header>
    <div id="submitSearch" class="row g-0 align-items-center flex-column-reverse flex-md-row">
        <div class="col-md-6">
            <router-link :to="{name: 'HotplaceCreate'}">
            <button type="button" class="btn" style="background-color: #c3e5e5;"><b>글쓰기</b></button>
            </router-link>
        </div>
        <div class="search col-md-6">
            <select class="searchSelect">
                <option>제목</option>
                <option>인기순</option>
            </select>
            <input class="searchInput" type="text" placeholder="검색어 입력">
            <button type="button" class="btn searchBtn" style="background-color: #FFD5E3"><b>검색</b></button>
        </div>
    </div>
  </div>
</template>

<script>
export default {
    components: {
        "hotplace-header": () => import("@/components/hotplace/include/HotplaceHeader.vue"),
    }
}
</script>

<style scoped>
#submitSearch{
    width: 83%;
    margin: 30px auto;
}
.search{
    position: relative;
    text-align: right;
    padding-right: 10px;
}
.searchSelect {
    border: 1px solid #bbb;
    border-radius: 8px;
    padding: 10px 12px;
    font-size: 14px;
}
.searchInput {
    margin-left: 5px;
    width: 300px;
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


</style>
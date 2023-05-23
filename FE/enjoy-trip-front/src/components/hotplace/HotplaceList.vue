<template>
  <div>
    <hotplace-header></hotplace-header>
    <div id="submitSearch" class="row g-0 align-items-center flex-column-reverse flex-md-row">
      <div class="col-md-6">
        <router-link :to="{ name: 'HotplaceCreate' }">
          <button type="button" class="btn" style="background-color: #c3e5e5">
            <b>글쓰기</b>
          </button>
        </router-link>
      </div>
      <div class="search col-md-6">
        <select class="searchSelect" v-model="clientkey">
          <option selected>검색 조건</option>
          <option>제목</option>
          <option>카테고리</option>
        </select>
        <input class="searchInput" type="text" placeholder="검색어 입력" v-model="clientword" />
        <button
          type="button"
          class="btn searchBtn"
          style="background-color: #ffd5e3"
          @click="goSearch"
        >
          <b>검색</b>
        </button>
      </div>

      <!-- 글 목록 영역 -->
      <div class="hotpl_all_list">
        <hotplace-list-cardview
          v-for="hotplace in hotplaces"
          :key="hotplace.hotplaceNo"
          :hotplace="hotplace"
        ></hotplace-list-cardview>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  data() {
    return {
      searchCondition: {
        key: null,
        word: null,
      },
      clientkey: "검색 조건",
      clientword: null,
    };
  },
  components: {
    "hotplace-header": () => import("@/components/hotplace/include/HotplaceHeader.vue"),
    "hotplace-list-cardview": () => import("@/components/hotplace/include/HotplaceListCardview"),
  },
  methods: {
    ...mapActions(["getHotplaces", "searchHotplaces"]),
    goSearch() {
      if (this.clientkey == "제목") this.searchCondition.key = "hotplace_name";
      else if (this.clientkey == "카테고리") this.searchCondition.key = "category";
      this.searchCondition.word = this.clientword;
      console.log(this.searchCondition);
      this.searchHotplaces({
        searchCondition: this.searchCondition,
        callback: (status) => {
          if (status == 200) {
            this.$bvToast.toast("검색 완료!", {
              title: "o(*^＠^*)o",
              variant: "primary",
              toaster: "b-toaster-bottom-center",
              autoHideDelay: 2000,
              solid: true,
            });
          } else if (status == 500) {
            alert("서버 오류 발생!");
          }
        },
      });
    },
  },
  created() {
    this.getHotplaces();
  },
  computed: {
    ...mapGetters(["hotplaces"]),
  },
};
</script>

<style scoped>
#submitSearch {
  width: 83%;
  margin: 30px auto;
}
.search {
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

.hotpl_all_list {
  width: 100%;
  margin-top: 50px;
  margin-left: 100px;
}
</style>

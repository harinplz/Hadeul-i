<template>
  <div>
    <community-header></community-header>
    <div style="margin-top: 50px"></div>
    <b-container>
      <b-row class="mb-1">
        <b-col class="search">
          <select class="searchSelect">
            <option selected>검색 조건</option>
            <option>제목</option>
            <option>카테고리</option>
          </select>
          <input class="searchInput" type="text" placeholder="검색어 입력" />
          <button
            type="button"
            class="btn searchBtn"
            style="background-color: #ffd5e3">
            <b>검색</b>
          </button>
        </b-col>
        <b-col class="text-right">
          <b-button variant="outline-secondary" @click="movePage"
            >등록</b-button
          >
        </b-col>
      </b-row>

      <b-table
        v-if="communities.length"
        class="text-center"
        style="margin-bottom: 50px"
        striped
        hover
        head-variant="dark"
        :items="communities"
        :fields="fields"
        @row-clicked="rowClickListener"></b-table>
      <div v-else class="text-center">게시글이 없습니다.</div>
    </b-container>
    <div style="text-align: center">
      <div class="pagination">
        <a href="#">&laquo;</a>
        <a class="active" href="#">1</a>
        <a href="#">&raquo;</a>
      </div>
    </div>
    <div style="margin-bottom: 20px"></div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  data: function () {
    return {
      fields: [
        {
          key: "boardNo",
          label: "번호",
          sortable: true,
        },
        {
          key: "title",
          label: "제목",
          sortable: false,
        },
        {
          key: "regtime",
          label: "작성날짜",
          sortable: false,
          formatter: (value) => value.split(" ")[0],
        },
      ],
      perPage: 1,
      currentPage: 1,
    };
  },
  components: {
    "community-header": () =>
      import("@/components/community/include/CommunityHeader.vue"),
  },
  methods: {
    ...mapActions(["getCommunities"]),
    movePage() {
      this.$router.push({ name: "CommunityCreate" });
    },
    rowClickListener(row, index) {
      console.log(index, row);

      this.$router.push({
        name: "CommunityDetail",
        params: { boardNo: row.boardNo },
      });
    },
  },
  created() {
    this.getCommunities();
    console.log(this.communities);
  },
  computed: {
    ...mapGetters(["communities"]),
  },
};
</script>

<style scoped>
.search {
  position: relative;
  text-align: right;
  padding-right: 140px;
  margin-bottom: 20px;
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

.pagination {
  display: inline-block;
}

.pagination a {
  color: black;
  float: left;
  padding: 8px 16px;
  text-decoration: none;
}

.pagination a.active {
  background-color: #c3e5ee;
  color: black;
}

.pagination a:hover:not(.active) {
  background-color: #ddd;
}
</style>

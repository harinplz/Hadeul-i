<template>
  <b-container>
    <b-row>
      <b-col class="text-center">
        <h3>글 정보</h3>
      </b-col>
    </b-row>
    <b-row>
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listCommunity"
          >목록</b-button
        >
      </b-col>
      <b-col class="text-right">
        <b-button variant="outline-info" size="sm" @click="moveModifyCommuntiy"
          >수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteCommuntiy"
          >삭제</b-button
        >
      </b-col>
    </b-row>
    <view-detail :community="community"></view-detail>
  </b-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  components: {
    "view-detail": () => import("@/components/community/include/ViewDetail"),
  },
  methods: {
    ...mapActions(["getCommunity"]),
    listCommunity() {
      this.$router.push({ name: "CommunityList" });
    },
    moveModifyCommuntiy() {
      this.$router.push({
        name: "BookModify",
        params: this.book.isbn,
      });
    },
    deleteCommuntiy() {
      this.$router.push({
        name: "BookDelete",
        params: this.book.isbn,
      });
    },
  },
  created() {
    const boardNo = this.$route.params.boardNo;
    // action 함수 호출
    this.getCommunity({
      boardNo,
    });
  },
  computed: {
    ...mapGetters(["community"]),
  },
};
</script>

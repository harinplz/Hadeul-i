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
        <b-button variant="outline-danger" size="sm" @click="deleteCom"
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
  data: function () {
    return {
      boardNo: "",
    };
  },
  components: {
    "view-detail": () => import("@/components/community/include/ViewDetail"),
  },
  methods: {
    ...mapActions(["getCommunity", "deleteCommunity"]),
    listCommunity() {
      this.$router.push({ name: "CommunityList" });
    },
    moveModifyCommuntiy() {
      this.$router.push({
        name: "CommunityModify",
        params: this.community.boardNo,
      });
    },
    deleteCom() {
      console.log("삭제실행");
      const payload = {
        boardNo: this.boardNo,
        callback: (status) => {
          if (status == 200) {
            this.$router.push({ name: "CommunityList" });
          } else if (status == 500) {
            alert("서버 오류 발생!");
          }
        },
      };
      this.deleteCommunity(payload);
    },
  },
  created() {
    const boardNo = this.$route.params.boardNo;
    this.boardNo = boardNo;
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

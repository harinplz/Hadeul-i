<template>
  <div>
    <div class="hotpl_create_title wow fadeInUp">
      <div class="hotpl_create_title_text">커뮤니티 글 조회</div>
    </div>
    <div style="margin-bottom: 50px"></div>
    <b-container>
      <b-row>
        <b-col class="text-left">
          <b-button variant="outline-primary" @click="listCommunity"
            >목록</b-button
          >
        </b-col>
        <b-col v-if="userInfo.no == community.userNo" class="text-right">
          <b-button
            variant="outline-info"
            size="sm"
            @click="moveModifyCommuntiy"
            >수정</b-button
          >
          <b-button variant="outline-danger" size="sm" @click="deleteCom"
            >삭제</b-button
          >
        </b-col>
      </b-row>

      <div style="margin-bottom: 20px"></div>
      <view-detail :community="community"></view-detail>
      <div style="margin-bottom: 50px"></div>

      <!-- 댓글 구현 부분 -->
      <div class="travelrt_comment">
        <!-- 댓글 갯수 -->
        <div class="travelrt_comment_title">댓글 {{ comments.length }}</div>
        <!-- 댓글 등록 -->
        <div class="travelrt_comment_create row">
          <div class="col-md-10">
            <textarea
              class="travelrt_comment_create_input"
              placeholder="댓글을 입력해주세요 ... "
              v-model="comment"></textarea>
          </div>
          <div class="col-md-2">
            <button
              type="button"
              class="btn commentBtn"
              style="background-color: #c3e5ee"
              @click="InsertComment">
              입력
            </button>
          </div>
        </div>
      </div>
      <community-comment :comments="comments"></community-comment>
      <div style="margin-bottom: 100px"></div>
    </b-container>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import http from "@/util/http-commons";

export default {
  data: function () {
    return {
      boardNo: "",
      comments: [],
      comment: "",
    };
  },
  components: {
    "view-detail": () => import("@/components/community/include/ViewDetail"),
    "community-comment": () =>
      import("@/components/community/include/CommunityComment"),
  },
  methods: {
    ...mapActions(["getCommunity", "deleteCommunity"]),

    InsertComment() {
      const info = {
        userNo: this.userInfo.no,
        boardNo: this.boardNo,
        comment: this.comment,
      };

      http.post(`/community/comment`, info).then((response) => {
        this.comments = response.data;
        this.comment = "";
      });
    },

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

    /* 
      댓글 부분 구현 시작.. 추후에 vuex로..
    */

    http.get(`/community/comment/${boardNo}`).then((response) => {
      console.log("서버에서 어떤걸 보내줬을까?", response);
      if (response.status == 200) {
        this.comments = response.data;
      }
    });
  },
  computed: {
    ...mapGetters(["community", "userInfo"]),
  },
};
</script>

<style scoped>
.travelrt_comment {
  width: 90%;
  margin: 100px auto 50px auto;
}

.travelrt_comment_title {
  font-size: 18px;
  font-weight: bold;
  padding-bottom: 15px;
}

.travelrt_comment_create_input {
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
</style>

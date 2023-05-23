<template>
  <b-container>
    <b-row>
      <b-col class="text-center">
        <h1>{{ writeFormTitle }}</h1>
      </b-col>
    </b-row>
    <b-row>
      <b-col class="text-left">
        <b-form>
          <b-form-group
            label-cols="12"
            id="title-group"
            label="제목:"
            label-for="title"
            description="제목을 입력하세요."
          >
            <b-form-input
              id="title"
              ref="title"
              v-model="title"
              type="text"
              required
              placeholder="제목 입력..."
            />
          </b-form-group>
          <b-form-group
            label-cols="12"
            id="content-group"
            label="내용:"
            label-for="content"
            ref="content"
          >
            <b-form-textarea
              id="content"
              v-model="content"
              placeholder="내용 입력..."
              rows="10"
              max-rows="15"
            ></b-form-textarea>
          </b-form-group>
          <div style="text-align: center; margin-bottom: 20px">
            <button
              type="button"
              v-if="type == 'create'"
              class="m-1 btn"
              style="background-color: #ffd5e3; padding: 10px 30px"
              @click="validate"
            >
              등록
            </button>
            <button
              type="button"
              v-else
              class="m-1 btn"
              style="background-color: #ff5de3; padding: 10px 30px"
              @click="validate"
            >
              수정
            </button>
            <button
              type="button"
              class="m-1 btn"
              style="background-color: #c3e5ee; padding: 10px 30px"
              @click="moveList"
            >
              목록
            </button>
          </div>
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  props: {
    type: String, // create, modify
  },
  data: function () {
    return {
      boardNo: "",
      title: "",
      userId: "",
      content: "",
    };
  },
  methods: {
    ...mapActions(["createCommunity", "modifyCommunity", "getCommunity"]),
    validate() {
      let isValid = true; // 유효하면 true
      let errMsg = "";

      !this.title
        ? ((isValid = false), (errMsg = "제목을 입력해주세요."), this.$refs.title.focus())
        : !this.content
        ? ((isValid = false), (errMsg = "내용을 입력해주세요."), this.$refs.content.focus())
        : (isValid = true);

      if (!isValid) {
        alert(errMsg);
      } else {
        const payload = {
          community: {
            title: this.title,
            userNo: this.userInfo.no,
            content: this.content,
          },
          callback: (status) => {
            switch (status) {
              case 200:
                if (this.type == "create") {
                  alert("등록 되었습니다.");
                } else {
                  alert("수정 되었습니다.");
                }
                break;

              case 400:
                alert("잘못된 요청입니다.");
                break;

              case 500:
                alert("서버 오류!!!");
                break;
            }
            this.moveList();
          },
        };
        if (this.type == "create") {
          this.createCommunity(payload);
        } else {
          this.modifyCommunity(payload);
        }
      }
    },
    moveList() {
      this.$router.push({ name: "CommunityList" });
    },
  },
  created: function () {
    if (this.type == "modify") {
      this.boardNo = this.community.boardNo;
      this.title = this.community.title;
      this.userId = this.community.userId;
      this.content = this.community.content;
    }

    console.log(this.userInfo);
  },
  computed: {
    ...mapGetters(["community", "userInfo"]),
    writeFormTitle: function () {
      return this.type == "create" ? "글 등록" : "글 수정";
    },
  },
};
</script>

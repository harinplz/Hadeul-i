<template>
  <b-container>
    <b-row>
      <b-col class="text-center">
        <h3>글 목록</h3>
      </b-col>
    </b-row>
    <!-- <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="movePage">등록</b-button>
      </b-col>
    </b-row> -->

    <b-table
      v-if="communities.length"
      class="text-center"
      striped
      hover
      head-variant="dark"
      :items="communities"
      :fields="fields"></b-table>
    <div v-else class="text-center">게시글이 없습니다.</div>
  </b-container>
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
          key: "userId",
          label: "작성자",
          sortable: false,
        },
        {
          key: "regtime",
          label: "작성일",
          sortable: false,
        },
      ],
    };
  },
  methods: {
    ...mapActions(["getCommunities"]),
  },
  created() {
    this.getCommunities();
  },
  computed: {
    ...mapGetters(["communities"]),
  },
};
</script>

<style scoped></style>

<template>
  <b-container>
    <b-row>
      <b-col class="text-center">
        <h3>글 목록</h3>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-secondary" @click="movePage">등록</b-button>
      </b-col>
    </b-row>

    <b-table
      v-if="communities.length"
      class="text-center"
      striped
      hover
      head-variant="dark"
      :items="communities"
      :fields="fields"
      @row-clicked="rowClickListener"></b-table>
    <div v-else class="text-center">게시글이 없습니다.</div>
    <div style="margin-bottom: 80px"></div>
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
          key: "regtime",
          label: "작성날짜",
          sortable: false,
        },
      ],
    };
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

<style scoped></style>

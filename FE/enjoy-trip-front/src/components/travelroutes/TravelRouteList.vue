<template>
  <div>
    <travel-route-header></travel-route-header>
    <div
      id="submitSearch"
      class="row g-0 align-items-center flex-column-reverse flex-md-row">
      <div class="col-md-6">
        <router-link :to="{ name: 'TravelRouteCreate' }">
          <button type="button" class="btn" style="background-color: #c3e5e5">
            <b>글쓰기</b>
          </button>
        </router-link>
      </div>
    </div>

    <div style="width: 80%; margin: 0px auto 100px auto">
      <b-table
        v-if="travelRoutes.length"
        class="text-center"
        striped
        hover
        :items="travelRoutes"
        :fields="fields"
        @row-clicked="rowClickListener"></b-table>
      <div v-else class="if_empty_route_text">계획을 등록해주세요!</div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  data: function () {
    return {
      fields: [
        {
          key: "travelRouteNo",
          label: "번호",
          sortable: true,
        },
        {
          key: "title",
          label: "제목",
          sortable: false,
        },
        {
          key: "departDate",
          label: "시작일",
          sortable: false,
        },
        {
          key: "arriveDate",
          label: "종료일",
          sortable: false,
        },
        {
          key: "regTime",
          label: "작성날짜",
          sortable: false,
        },
      ],
    };
  },
  components: {
    "travel-route-header": () =>
      import("@/components/travelroutes/include/TravelRouteHeader"),
  },
  methods: {
    ...mapActions(["getTravelRoutes"]),
    rowClickListener(row, index) {
      console.log(index, row);

      this.$router.push({
        name: "TravelRouteDetail",
        params: { travelRouteNo: row.travelRouteNo },
      });
    },
  },
  created() {
    this.getTravelRoutes();
    console.log(this.travelRoutes);
  },
  computed: {
    ...mapGetters(["travelRoutes"]),
  },
};
</script>

<style scoped>
#submitSearch {
  width: 83%;
  margin: 30px auto;
}

.if_empty_route_text {
  font-family: "SUITE-Regular";
  text-align: center;
  margin-bottom: 100px;
  font-weight: bold;
  font-size: 25px;
}
</style>

<template>
  <tr>
    <td>{{ input.no }}</td>
    <td>{{ input.id }}</td>
    <td>{{ input.name }}</td>
    <td>{{ input.pw }}</td>
    <td>관리</td>
  </tr>
  <!-- <b-list-group-item class="flex-column align-items-start">
    <div class="table">
      <div>
        <div class="tr">
          <p class="td">{{ input.no }}</p>
          <p class="td">{{ input.name }}</p>
          <p class="td">{{ input.id }}</p>
          <p class="td">{{ input.pw }}</p>
        </div>
      </div>
    </div>
  </b-list-group-item> -->
</template>

<script>
export default {
  props: {
    user: Object,
  },
  computed: {
    input() {
      return { ...this.user };
    },
  },
};
</script>

<style scoped>
.table {
  display: table;
}
.tr {
  display: table-row;
}
.td {
  display: table-cell;
}
</style>

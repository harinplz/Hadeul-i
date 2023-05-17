<template>
  <tr>
    <td scope="col">{{ attraction.title }}</td>
    <td scope="col">{{ attraction.addr1 }}</td>
    <td scope="col">{{ attraction.latitue }}</td>
    <td scope="col">{{ attraction.longitude }}</td>
  </tr>
</template>

<script>
export default {
  props: {
    attraction: Object,
  },
};
</script>

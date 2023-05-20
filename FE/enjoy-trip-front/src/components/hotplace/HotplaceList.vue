<template>
  <div>
    HotplaceList.vue 입니다.
    <hotplace-header></hotplace-header>
  </div>
</template>

<script>
export default {
    components: {
        "hotplace-header": () => import("@/components/hotplace/include/HotplaceHeader.vue"),
    }
}
</script>

<style>

</style>
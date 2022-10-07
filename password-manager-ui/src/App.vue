<template>
  <Sidebar />
</template>


<style lang="scss">
  @import "~bulma/bulma.sass";
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #2c3e50;
  }
</style>

<script lang="ts">
  import Sidebar from "@/components/Sidebar.vue";
  export default {
    components: {Sidebar}
  }
</script>

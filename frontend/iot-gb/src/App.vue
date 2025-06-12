<script setup>
import { RouterLink, RouterView } from 'vue-router'
import SideNav from './components/SideNav.vue'
</script>

<template>
  <div class="nav">
    <SideNav />
  </div>
  <main>
    <RouterView />
  </main>
</template>

<style scoped>

.nav {
  width: 16%;
}

main {
  flex: 1;
  padding: 1rem;
  overflow-y: scroll;
}
</style>

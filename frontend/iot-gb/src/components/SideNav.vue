<script>
export default {
  data() {
    return {
      msg: 'teste'
    }
  }
}
</script>

<template>
  <p class="title">Menu</p>
  <nav class="navWrapper">
    <RouterLink to="/" class="link">Dashboard</RouterLink>
    <RouterLink to="/factory" class="link">Galpão</RouterLink>
    <RouterLink to="/create" class="link">Cadastros</RouterLink>
  </nav>
</template>

<style scoped>
.navWrapper {
  display: flex;
  flex-direction: column;
  padding: 1rem;
  padding-top: 0;
}
.link {
  padding: .5rem;
  font-size: 1rem;
}
.title {
  padding: 1.5rem;
  color: #FFF;
  font-size: 1.5rem;
  font-weight: 600;
}
</style>

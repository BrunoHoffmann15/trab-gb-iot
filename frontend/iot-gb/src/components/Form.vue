<script>
export default {
  props: {
    title: {
      type: String,
      required: true
    },
    buttonTitle: {
      type: String,
      default: 'Submit'
    }
  },
  emits: ['submit'],
  methods: {
    handleSubmit() {
      this.$emit('submit')
    }
  }
}
</script>

<template>
  <h2 class="subtitle">{{ title }}</h2>
  <form @submit.prevent="handleSubmit" class="form-container">
    <slot />
    <button type="submit">{{ buttonTitle }}</button>
  </form>
</template>

<style scoped>
.form-container {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  border: solid 1px #00bd7e;
  border-radius: 5px;
  padding: 1rem; 
  max-width: 90%;
  margin-bottom: 2rem;
}

button {
  width: 250px;
  padding: 1rem 2rem;
  background-color: #00bd7e;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  color: #FFF;
  font-weight: 600;
  font-size: 1rem;
  align-self: flex-end;
}

button:hover {
  filter: brightness(85%);
}
</style>

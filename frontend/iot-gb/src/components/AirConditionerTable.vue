<script>
import { mapState, mapActions } from 'vuex'

export default {
  computed: {
    ...mapState('state', ['airConditioners']),
    computedDevices() {
      return this.airConditioners
    },
    headers() {
      return Object.keys(this.airConditioners[0])
    }
  },
  created() {
    this.fetchDevices()
  },
  methods: {
    ...mapActions('state', ['fetchAirConditioners'])
  }
}
</script>

<template>
  <div>
    <h1>AirConditioners</h1>
    <table class="table">
      <tbody>
        <tr>
          <th v-for="key in headers" :key="key" class="tableHeaderCell">{{ key }}</th>
        </tr>
        <tr v-for="value in airConditioners">
          <td class="tableRowCell">{{ value.id }}</td>
          <td class="tableRowCell">
            <div class="list">
              <span v-for="key in Object.keys(value.location)" :key class="item">{{ `${key}: ${value.location[key]}` }}</span>
            </div>
          </td>
          <td class="tableRowCell">{{ value.idAirConditioner }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
.list {
  display: flex;
  justify-content: space-evenly;
}

.table {
  width: 100%;
}

.item {
  padding-left: 1rem;
  padding-right: 1rem;
}

.tableHeaderCell {
  font-weight: 600;
  border: 1px solid #333;
  padding: .5rem;
  color: #fff;
}
.tableRowCell {
  border: 1px solid #333;
  padding-left: 1.5rem;
  padding-right: 1.5rem;
}
</style>
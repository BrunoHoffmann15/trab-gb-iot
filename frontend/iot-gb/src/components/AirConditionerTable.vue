<script>
import { mapState, mapActions } from 'vuex'

export default {
  computed: {
    ...mapState('state', ['airConditioners']),
    headers() {
      return Object.keys(this.airConditioners?.[0] || {})
    }
  },
  created() {
    this.fetchAirConditioners()
  },
  methods: {
    ...mapActions('state', ['fetchAirConditioners'])
  }
}
</script>

<template>
  <div>
    <h1>Air Conditioners</h1>
    <table class="table">
      <tbody>
        <tr>
          <th v-for="key in headers" :key="key" class="tableHeaderCell">{{ key }}</th>
        </tr>
        <tr v-for="value in airConditioners" class="row">
          <td class="tableRowCell">{{ value.id }}</td>
          <td class="tableRowCell">{{ value.referencedAirConditioner }}</td>
          <td class="tableRowCell">
            <div class="list">
              <span v-for="key in Object.keys(value.temperatureRange) || {}" :key class="item">{{ `${key}: ${value.temperatureRange[key]}` }}</span>
            </div>
          </td>
          <td class="tableRowCell">
            <div class="list">
              <span v-for="key in Object.keys(value.location || {})" :key class="item">{{ `${key}: ${value.location[key]}` }}</span>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

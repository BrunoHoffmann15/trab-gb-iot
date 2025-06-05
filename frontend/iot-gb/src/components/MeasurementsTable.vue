<script>
import { mapState, mapActions } from 'vuex'

export default {
  computed: {
    ...mapState('state', ['measurements', 'devices']),
    headers() {
      return Object.keys(this.measurements?.[0] || [])
    }
  },
  watch: {
    devices: {
      handler() {
        if (this.devices) {
          this.devices.forEach((device) => {
            this.fetchMeasurements(device.id)
          })
        }
      },
      deep: true,
    }
  },
  methods: {
    ...mapActions('state', ['fetchMeasurements'])
  }
}
</script>

<template>
  <div>
    <h1>Measurements</h1>
    <table class="table">
      <tbody>
        <tr>
          <th v-for="key in headers" :key="key" class="tableHeaderCell">{{ key }}</th>
        </tr>
        <tr v-for="value in measurements" class="row">
          <td class="tableRowCell">{{ value.id }}</td>
          <td class="tableRowCell">{{ value.idDevice }}</td>
          <td class="tableRowCell">{{ new Date(value.createdAt).toLocaleString("default", { day: 'numeric', month: 'long', year: 'numeric'}) }}</td>
          <td class="tableRowCell">{{ value.temperature }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

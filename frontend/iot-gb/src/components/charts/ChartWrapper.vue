<script>
import { mapState } from 'vuex'
import { chartData, options } from './chartConfig.js'
import LineChart from './LineChart.vue'

export default {
  components: {
    LineChart
  },
  computed: {
    ...mapState('state', ['devices', 'measurements']),
    chartConfig() {
      if (!this.devices?.length || !this.measurements?.length) return null
      const measurementsByDevice = []
      this.devices.forEach((device) => {
        measurementsByDevice[device.referencedDevice] = this.measurements.filter((measurement) => measurement.idDevice === device.id)
      })

      if (!measurementsByDevice) return null

      const data = chartData(measurementsByDevice)

      return data
    }
  }
}
</script>

<template>
  <div v-if="chartConfig && devices.length && measurements.length" class="chart">
    <h1 class="title">Medidas por device (com horário)</h1>
    <LineChart :data="chartConfig" :options="options" />
  </div>
</template>

<style scoped>
.title {
  margin-bottom: 1rem;
}

.chart {
  padding: 1rem 1rem 2rem 1rem;
}
</style>

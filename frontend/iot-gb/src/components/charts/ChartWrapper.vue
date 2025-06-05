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
        measurementsByDevice[device.id] = this.measurements.filter((measurement) => measurement.idDevice === device.id)
      })

      if (!measurementsByDevice) return null

      const data = chartData(measurementsByDevice)
      console.debug(data)

      return data
    }
  }
}
</script>

<template>
  <div v-if="chartConfig && devices.length && measurements.length">
    <LineChart :data="chartConfig" :options="options" />
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import Device from '../components/Device.vue'

// seria parametrizável em um cenário real
const WIDTH = 800
const HEIGHT = 400

export default {
  components: { Device },
  data() {
    return {
      scheme: null,
      numberOfDevicesInARow: WIDTH / 100
    }
  },
  computed: {
    ...mapState('state', ['devices', 'airConditioners', 'measurements']),
    measurementsByDevice() {
      const array = []
      this.devices?.forEach((device) => {
        array[device.id] = this.measurements.findLast((measurement) => measurement.idDevice === device.id)?.temperature
      })

      return array
    }
  },
  created() {
    if (!this.devices?.length) this.fetchDevices()
    if (!this.airConditioners?.length) this.fetchAirConditioners()
    this.$nextTick(() => {
      this.mountDeviceScheme()
    })
  },
  watch: {
    devices: {
      handler() {
        if (this.devices && !this.measurements?.length) {
          this.devices.forEach((device) => {
            this.fetchMeasurements(device.id)
          })
        }
      },
      deep: true,
    }
  },
  methods: {
    ...mapActions('state', ['fetchDevices', 'fetchAirConditioners', 'fetchMeasurements']),
    mountDeviceScheme() {
      const numberOfRows = this.devices?.length / this.numberOfDevicesInARow
      const grid = []

      let deviceIndex = 0
      for (let rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
        grid.push([])
        for (let columnIndex = 0; columnIndex < this.numberOfDevicesInARow; columnIndex++) {
          grid[rowIndex].push(this.devices[deviceIndex])
          deviceIndex++
        }
      }
      this.scheme = grid
    },
  }
}
</script>

<template>
  <h1 class="title">Factory</h1>
  <div>
    <div class="scheme">

      <div v-for="row in scheme" class="row">
        <device v-for="device in row" :device="device" :last-measurement="measurementsByDevice[device.id]" />
      </div>
    </div>
    <div>
      <h2>Legenda de cores</h2>
      <ul>
        <li>
          <div class="subtitleColor tooCold" />
          <span>Temperatura abaixo de 0</span>
        </li>
        <li>
          <div class="subtitleColor cold" />
          <span>Temperatura entre 0 e 22</span>
        </li>
        <li>
          <div class="subtitleColor ideal" />
          <span>Temperatura entre 22 e 24</span>
        </li>
        <li>
          <div class="subtitleColor warm" />
          <span>Temperatura entre 24 e 34</span>
        </li>
        <li>
          <div class="subtitleColor hot" />
          <span>Temperatura acima de 34</span>
        </li>
      </ul>
    </div>
  </div>
</template>

<style scoped>
.subtitleColor {
  width: 20px;
  height: 20px;
  background-color: #ffffff3b;
}

.tooCold {
  background-color: #b36bcf3b;
}

.cold {
  background-color: #6b6dcf3b;
}

.ideal {
  background-color: #6bcfb13b;
}

.warm {
  background-color: #fd8c0a3b;
}

.hot {
  background-color: #fd0a0a3b;
}

.scheme {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.row {
  background-color:rgba(206, 206, 206, 0.14);
  display: flex;
}


</style>
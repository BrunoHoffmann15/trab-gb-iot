<script>
import { mapState, mapActions } from 'vuex'

export default {
  computed: {
    ...mapState('state', ['devices']),
    headers() {
      return Object.keys(this.devices?.[0] || [])
    }
  },
  created() {
    this.fetchDevices()
  },
  methods: {
    ...mapActions('state', ['fetchDevices'])
  }
}
</script>

<template>
  <div>
    <h1>Devices</h1>
    <table class="table">
      <tbody>
        <tr>
          <th v-for="key in headers" :key="key" class="tableHeaderCell">{{ key }}</th>
        </tr>
        <tr v-for="value in devices" class="row">
          <td class="tableRowCell">{{ value.id }}</td>
          <td class="tableRowCell">{{ value.referencedDevice }}</td>
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

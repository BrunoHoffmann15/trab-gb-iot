<script>
import { mapState, mapActions } from 'vuex'
import IotForm from '../components/Form.vue'

export default {
  components: {
    IotForm,
  },
  data() {
    return {
      airConditioner: {
        referencedAirConditioner: null,
        location: {
          x: 0,
          y: 0,
        },
        temperatureRange: {
          min: 22,
          max: 24
        }
      },
      device: {
        referencedDevice: null,
        location: {
          x: 0,
          y: 0,
        },
        idAirConditioner: null
      },
      measurement: {
        createdAt: null,
        temperature: null,
        idDevice: null,
      }
    }
  },
  computed: {
    ...mapState('state', ['airConditioners', 'devices']),
  },
  created() {
    this.fetchAirConditioners()
  },
  methods: {
    ...mapActions('state', ['fetchAirConditioners', 'fetchDevices', 'addNewAirConditioner', 'addNewDevice', 'addNewMeasurement']),
    addAirConditioner() {
      console.debug(this.airConditioner)
      this.addNewAirConditioner(this.airConditioner)
    },
    addDevice() {
      console.debug(this.device)
      this.addNewDevice(this.device)
    },
    getDateTime(time) {
      const today = new Date();

      const [hours, minutes] = time.split(":");
      const dateWithTime = new Date(
        today.getFullYear(),
        today.getMonth(),
        today.getDate(),
        hours,
        minutes
      );

      return dateWithTime
    },
    addMeasurement() {
      const dateTime = this.getDateTime(this.measurement.createdAt)

      this.addNewMeasurement({ ...this.measurement, dateTime })
    },
  }
}
</script>

<template>
  <iot-form title="Air Conditioner" button-title="Save Air Conditioner" @submit="addAirConditioner">
    <div>
      <div class="form-row">
        <div class="form-column">
          <label for="referencedAirConditioner">Referenced Air Conditioner</label>
          <input id="referencedAirConditioner" v-model="airConditioner.referencedAirConditioner" type="text" />
        </div>
      </div>
      <div class="form-row">
        <div class="form-column">
          <label for="locationX">Location (X)</label>
          <input id="locationX" v-model="airConditioner.location.x" type="numeric" />
        </div>
        <div class="form-column">
          <label for="locationY">Location (Y)</label>
          <input id="locationY" v-model="airConditioner.location.y" type="numeric" />
        </div>
      </div>
      <div class="form-row">
        <div class="form-column">
          <label for="temperatureRangeMin">Temperature Range (MIN)</label>
          <input id="temperatureRangeMin" v-model="airConditioner.temperatureRange.min" type="numeric" />
        </div>
        <div class="form-column">
          <label for="temperatureRangeMax">Temperature Range (MAX)</label>
          <input id="temperatureRangeMax" v-model="airConditioner.temperatureRange.max" type="numeric" />
        </div>
      </div>
    </div>
  </iot-form>

  <iot-form title="Device" button-title="Save Device" @submit="addDevice">
    <div>
      <div class="form-row">
        <div class="form-column">
          <label for="referencedDevice">Referenced Device</label>
          <input id="referencedDevice" v-model="device.referencedDevice" type="text" />
        </div>
      </div>
      <div class="form-row">
        <div class="form-column">
          <label for="locationX">Location (X)</label>
          <input id="locationX" v-model="device.location.x" type="numeric" />
        </div>
        <div class="form-column">
          <label for="locationY">Location (Y)</label>
          <input id="locationY" v-model="device.location.y" type="numeric" />
        </div>
      </div>
      <div class="form-row">
        <div class="form-column">
          <label for="idAirConditioner">Id Air Conditioner</label>
          <select id="idAirConditioner" v-model="device.idAirConditioner">
            <option disabled value="">Please select one</option>
            <option v-for="airConditioner in airConditioners" :value="airConditioner.id">
              {{ airConditioner.referencedAirConditioner }} - {{ airConditioner.id }}
            </option>
          </select>
        </div>
      </div>
    </div>
  </iot-form>

  <iot-form title="Measurement" button-title="Save Measurement" @submit="addMeasurement">
    <div>
      <div class="form-row">
        <div class="form-column">
          <label for="temperature">Temperature</label>
          <input id="temperature" v-model="measurement.temperature" type="numeric" />
        </div>
        <div class="form-column">
          <label for="createdAt">Created at</label>
          <input id="createdAt" v-model="measurement.createdAt" type="time" class="time-picker" />
        </div>
      </div>
      <div class="form-row">
        <div class="form-column">
          <label for="idDevice">Id Device</label>
          <select id="idDevice" v-model="measurement.idDevice">
            <option disabled value="">Please select one</option>
            <option v-for="device in devices" :value="device.id">
              {{ device.referencedDevice }} - {{ device.id }}
            </option>
          </select>
        </div>
      </div>
    </div>
  </iot-form>
</template>

<style scoped>
.form-row {
  padding: 1rem 0;
  border-bottom: dashed 1px rgba(0, 189, 126, 0.15);
  display: flex;
  gap: 1rem;
}

.form-column {
  flex: 1;
  display: flex;
  flex-direction: row;
}

label {
  margin-right: 1rem;
}

input, select {
  background-color: #14362b;
  border: none;
  border-bottom: solid 1px #00bd7e;
  color: #FFF;
  padding: .5rem 1rem;
  flex: 1;
  border-radius: 5px;
}

input[type="time"]::-webkit-calendar-picker-indicator {
  background-color: #FFF;
  border-radius: 100%;
  cursor: pointer;
}

</style>

import * as api from '../../services/api.js'

// initial state
const state = () => ({
  devices: [],
  airConditioners: [],
  measurements: [],
})

// getters
// returns the values of current state
const getters = {
}

// actions
// api fetches + logic
const actions = {
  async fetchDevices ({ commit }) {
    try {
      const { data } = await api.getDevices()
      commit('setDevices', data)
    } catch (e) {
      console.error(e)
    }
  },
  async fetchAirConditioners ({ commit }) {
    try {
      const { data } = await api.getAirConditioners()
      commit('setAirConditioners', data)
    } catch (e) {
      console.error(e)
    }
  },
  async fetchMeasurements({ commit }, deviceId) {
    try {
      const { data } = await api.getMeasurements(deviceId)
      commit('setMeasurements', data)
    } catch (e) {
      console.error(e)
    }
  },
  async addNewAirConditioner({ dispatch }, data) {
    try {
      console.debug(data)
      await api.addAirConditioner(data)
      dispatch('fetchAirConditioners')
    } catch (e) {
      console.error(e)
    }
  },
  async addNewDevice({ dispatch }, data) {
    try {
      await api.addDevice(data)
      dispatch('fetchDevices')
    } catch (e) {
      console.error(e)
    }
  },
  async addNewMeasurement(context, data) {
    try {
      const { dateTime, temperature, idDevice } = data
      await api.addMeasurement({ createdAt: dateTime, temperature}, idDevice)
    } catch (e) {
      console.error(e)
    }
  }
}

// mutations
// alters the state
const mutations = {
  setDevices (state, devices) {
    state.devices = devices
  },
  setAirConditioners (state, airConditioners) {
    state.airConditioners = airConditioners
  },
  setMeasurements (state, measurements) {
    if (!state.measurements.length) state.measurements = measurements
    else {
      state.measurements.push(...measurements)
    }
  }
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
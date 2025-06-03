import * as api from '../../services/api.js'

// initial state
const state = () => ({
  devices: [],
  airConditioners: []
})

// getters
// returns the values of current state
const getters = {
  fetchedDevices: (state) => {
    return state.devices
  },
  fetchedAirConditioners: (state) => {
    return state.airConditioners
  }
}

// actions
// api fetches + logic
const actions = {
  async fetchDevices ({ commit }) {
    console.debug(' oi')
    try {
      const { data } = await api.getDevices()
      console.debug(data)
      commit('setDevices', data)
    } catch (e) {
      console.error(e)
    }
  },
  async fetchAirConditioners ({ commit }) {
    console.debug(' oi')
    try {
      const { data } = await api.getAirConditioners()
      console.debug(data)
      commit('setAirConditioners', data)
    } catch (e) {
      console.error(e)
    }
  },
}

// mutations
// alters the state
const mutations = {
  setDevices (state, devices) {
    state.devices = devices
  },
  setDevices (state, airConditioners) {
    state.airConditioners = airConditioners
  }
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
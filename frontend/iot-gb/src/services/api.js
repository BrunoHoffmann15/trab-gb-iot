import axios from "axios"

const API_PREFIX = 'http://localhost:8080'

const api = axios.create({
  baseURL: API_PREFIX,
  timeout: 1000,
  headers: {'accept': '*/*'}
});

async function getDevices() {
  return api.get(`/devices`)
}

async function getAirConditioners() {
  return api.get('/air-conditioners')
}

async function getMeasurements(deviceId) {
return api.get(`/devices/${deviceId}/temperatures`)
}

async function addAirConditioner(data) {
  console.debug(data)
  return api.post('/air-conditioners', data)
}

async function addDevice(data) {
  console.debug(data)
  return api.post('/devices', data)
}

async function addMeasurement(data, deviceId) {
  console.debug(data, deviceId)
  return api.post(`/devices/${deviceId}/temperatures`, data)
}

export {
  getDevices,
  getAirConditioners,
  getMeasurements,
  addAirConditioner,
  addDevice,
  addMeasurement
}
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

export {
  getDevices,
  getAirConditioners,
}
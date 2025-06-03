import { createStore } from 'vuex'
import state from './modules/state'


export default createStore({
  modules: {
    state
  },
})
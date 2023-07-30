import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
  state: {
    musicName: '未知',
    url: '',
    searchmsg: ''
  },
  mutations: {
    setAudioUrl(state, playload) {
      state.url = playload.url;
      state.musicName = playload.name;
    },
    setSearchmsg(state, data) {
      state.searchmsg = data.searchmsg
    }
  },
  getters: {
    getAudioUrl(state) {
      return state.url;
    },
    getMusicName(state) {
      return state.musicName;
    },
    getSearchmsg(state) {
      return state.searchmsg;
    }
  },
  watch: {
    'state.url' (newValue, oldValue) {
      console.log('count changed from', oldValue, 'to', newValue)
    }
  }
})

export default store

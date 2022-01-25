import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    token: localStorage.getItem("token") || null,
  },
  mutations: {
    RETRIEVE_TOKEN(state, token) {
      state.token = token;
    },
    DESTROY_TOKEN(state) {
      state.token = null;
    },
  },
  actions: {
    retrieveToken({ commit }, credentials) {
      return new Promise((resolve, reject) => {
        axios
          .post("/api/authenticate/login", {
            username: credentials.username,
            password: credentials.password,
          })
          .then((res) => {
            const token = res.data;
            localStorage.setItem("token", token);
            commit("RETRIEVE_TOKEN", token);
            resolve(res);
          })
          .catch((err) => {
            console.log(err);
            reject(err);
          });
      });
    },
    destroyToken({ commit, getters, state }) {
      if (getters.loggedin) {
        axios.defaults.headers.common["Authorization"] =
          "Bearer " + state.token;
        return new Promise((resolve, reject) => {
          axios
            .post("/api/logout")
            .then((res) => {
              localStorage.removeItem("token");
              commit("DESTROY_TOKEN");
              resolve(res);
            })
            .catch((err) => {
              localStorage.removeItem("token");
              commit("DESTROY_TOKEN");
              reject(err);
            });
        });
      }
    },
    getUsername({ getters, state }) {
      if (getters.loggedin) {
        axios.defaults.headers.common["Authorization"] =
          "Bearer " + state.token;
        return new Promise((resolve, reject) => {
          axios
            .get("/api/user")
            .then((res) => {
              resolve(res);
            })
            .catch((err) => {
              reject(err);
            });
        });
      }
    },
  },
  getters: {
    loggedin(state) {
      return state.token != null;
    },
  },
  modules: {},
});

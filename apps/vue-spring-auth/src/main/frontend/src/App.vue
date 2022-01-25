<template>
  <v-app>
    <v-app-bar app color="black" dark>
      <div class="logo clickable" @click="$router.push({name:'home'})">
        <h3>VueSpringAuth</h3>
      </div>
      <v-spacer></v-spacer>
      <v-btn v-if="!loggedin" :to="{name:'login'}" plain>Login</v-btn>
      <v-btn v-if="!loggedin" :to="{name:'register'}" plain>Register</v-btn>
      <v-btn v-if="loggedin" :to="{name:'logout'}">Logout</v-btn>
    </v-app-bar>

    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "App",

  data: () => ({
    //
  }),
  computed: {
    loggedin() {
      return this.$store.getters.loggedin
    }
  },
};
</script>
<style>
.clickable {
  cursor: pointer;
}
</style>
<template>
  <v-container fill-height>
    <v-row align="center" justify="center">
      <v-col cols="4">
        <v-alert
            v-if="error"
            dense
            outlined
            type="error"
        >
          {{ error }}
        </v-alert>

        <v-sheet class="rounded-lg pa-5" elevation="2">
          <h2 class="d-flex justify-center">Login</h2>
          <v-form
              ref="form"
              v-model="valid"
              lazy-validation
          >
            <v-text-field
                v-model="username"
                :rules="usernameRules"
                label="Username"
                required
            ></v-text-field>

            <v-text-field
                v-model="password"
                :rules="passwordRules"
                label="Password"
                required
            ></v-text-field>

            <v-btn
                :disabled="!valid"
                block
                class="mt-4"
                color="primary"
                @click="login"
            >
              Login
            </v-btn>
          </v-form>
        </v-sheet>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data: () => ({
    valid: true,
    error: null,
    username: 'user1',
    password: 'user',
    usernameRules: [
      v => !!v || 'Username is required',
    ],
    passwordRules: [
      v => !!v || 'Password is required',
    ],
  }),
  methods: {
    login() {
      this.$refs.form.validate()
      this.$store.dispatch('retrieveToken', {
        username: this.username,
        password: this.password,
      }).then(() => {
        this.$router.push({name: 'home'})
      }).catch((err) => {
        this.error = err.response.data.message
      })
    },
  },
}
</script>
<style>
.container {
  max-width: 1200px;
}
</style>
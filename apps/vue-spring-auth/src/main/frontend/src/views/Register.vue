<template>
  <v-container fill-height>
    <v-row align="center" justify="center">
      <v-col cols="4">
        <v-alert v-if="error" dense outlined type="error">
          {{ error }}
        </v-alert>

        <v-sheet class="rounded-lg pa-5" elevation="2">
          <h2 class="d-flex justify-center">Register</h2>
          <v-form ref="form" v-model="valid" lazy-validation>
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

            <v-text-field
                v-model="name"
                :rules="nameRules"
                label="Name"
                required
            ></v-text-field>

            <v-select
                v-model="roles"
                :items="roleItems"
                label="Role"
            ></v-select>

            <v-btn
                :disabled="!valid"
                block
                class="mt-4"
                color="primary"
                @click="register"
            >
              Register
            </v-btn>
          </v-form>
        </v-sheet>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  data: () => ({
    valid: true,
    error: null,
    roleItems: ["USER", "ADMIN"],
    username: "user1",
    password: "user",
    name: null,
    roles: [],
    usernameRules: [(v) => !!v || "Username is required"],
    passwordRules: [(v) => !!v || "Password is required"],
    nameRules: [(v) => !!v || "Name is required"],
    rolesRules: [(v) => !!v || "Roles are required"],
  }),
  methods: {
    register() {
      this.$refs.form.validate();
      console.log(this.roles)
      axios
          .post("/api/authenticate/register", {
            username: this.username,
            password: this.password,
            name: this.name,
            role: this.roles,
          })
          .then(() => {
            this.$router.push({name: "login"});
          })
          .catch((err) => {
            this.error = err.response.data.message;
          });
    },
  },
};
</script>
<style>
.container {
  max-width: 1200px;
}
</style>

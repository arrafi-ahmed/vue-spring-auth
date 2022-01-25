<template>
  <v-container>
    <v-row>
      <v-col>
        <h3 v-if="user.username" class="d-flex justify-center py-5">Welcome {{ user.username }}</h3>
      </v-col>
    </v-row>
    <v-row justify="center">
      <v-col cols="4">
        <h4 class="text-center">User Details</h4>
        <v-simple-table class="pt-5">
          <template #default>
            <tbody>
            <tr v-for="[keyname,value] of Object.entries(user)" :key="keyname">
              <td class="text-center">{{ keyname }}:</td>
              <td>{{ value }}</td>
            </tr>
            </tbody>
          </template>
        </v-simple-table>
      </v-col>
    </v-row>

  </v-container>
</template>

<script>

export default {
  name: "Home",
  data: () => ({
    user: {},
  }),
  mounted() {
    this.$store.dispatch("getUsername").then((res) => {
      this.user = res.data
      delete this.user.password
      delete this.user.token
    }).catch((err) => {
      console.log(err)
    })
  }
};
</script>